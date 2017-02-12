// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CreateOraPackage.java

package net.ion.plugin.cstore.commUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreateOraPackage
{

    public CreateOraPackage()
    {
    }

    public static void main(String args[])
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs_cont = null;
        String DB_URL = null;
        String DB_USER = "icafe";
        String DB_PASSWORD = "icafe";
        String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
        try
        {
            DB_URL = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS_LIST = (ADDRESS = (PROTOCOL = TCP)(HOST = 221.148.247.196)(PORT = 1521)))(CONNECT_DATA =  (SERVICE_NAME = CASSORA)))";
            Class.forName(DB_DRIVER);
            CreateOraPackage cop = new CreateOraPackage();
            StringBuffer query = (new StringBuffer(" SELECT COLUMN_NAME, DATA_TYPE \n")).append(" FROM USER_TAB_COLUMNS \n").append(" WHERE TABLE_NAME = ? ");
            String table_name = "CS_CONTENTS_MAIN";
            String table_pk = "cid";
            String pkg_nm = "test_master";
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = con.prepareStatement(query.toString());
            pstmt.setString(1, table_name);
            rs_cont = pstmt.executeQuery();
            List lsCol = new ArrayList();
            List lsDataType = new ArrayList();
            for(; rs_cont.next(); lsDataType.add(rs_cont.getString("DATA_TYPE")))
                lsCol.add(rs_cont.getString("COLUMN_NAME"));

            String declareHeaderStr = (new StringBuilder("CREATE OR REPLACE PACKAGE ")).append(pkg_nm).append(" IS\n").toString();
            String declareBodyStr = (new StringBuilder("CREATE OR REPLACE PACKAGE BODY ")).append(pkg_nm).append(" IS\n").toString();
            String endStr = (new StringBuilder("END ")).append(pkg_nm).append(";").toString();
            StringBuffer resultHeader = (new StringBuffer(declareHeaderStr)).append(cop.pkgHeader(lsCol, lsDataType, table_pk)).append(endStr);
            System.out.println("Package Header");
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println(resultHeader);
            System.out.println("---------------------------------------------------------------------------------------------");
            StringBuffer resultBody = (new StringBuffer(declareBodyStr)).append(cop.pkgBody(table_name, lsCol, lsDataType, table_pk)).append(endStr);
            System.out.println("Package Body");
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println(resultBody);
            pstmt.close();
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public StringBuffer pkgHeader(List lsCol, List lsDataType, String table_pk)
    {
        StringBuffer pkgHeader = new StringBuffer();
        String table_pks[] = table_pk.split(",");
        String param = pkgParam(lsCol, lsDataType).toString();
        String param_pk = pkgParam(lsCol, lsDataType, table_pks).toString();
        String rtnNum = "return number ;";
        pkgHeader.append("FUNCTION createWith( ").append(param).append(" )\n").append(rtnNum).append("\n");
        pkgHeader.append("FUNCTION updateWith( ").append(param).append(" )\n").append(rtnNum).append("\n");
        pkgHeader.append("FUNCTION deleteWith( ").append(param_pk).append(" )\n").append(rtnNum).append("\n");
        pkgHeader.append("FUNCTION retrieveBy( ").append(param_pk).append(" )\n").append("return types.cursorType;\n").append(rtnNum).append("\n");
        return pkgHeader;
    }

    public StringBuffer pkgBody(String table_name, List lsCol, List lsDataType, String table_pk)
    {
        StringBuffer pkgBody = new StringBuffer();
        String table_pks[] = table_pk.split(",");
        String param = pkgParam(lsCol, lsDataType).toString();
        String param_pk = pkgParam(lsCol, lsDataType, table_pks).toString();
        String rtnNum = "return NUMBER";
        String rtnRowCnt = "return SQL%ROWCOUNT;";
        pkgBody.append("FUNCTION createWith(").append(param).append((new StringBuilder(")\n")).append(rtnNum).append(" is begin\n").toString()).append(insertStr(table_name, lsCol, lsDataType)).append((new StringBuilder(String.valueOf(rtnRowCnt))).append("\n").toString()).append("END createWith ;\n").append("\n");
        pkgBody.append("FUNCTION updateWith( ").append(param).append((new StringBuilder(")\n")).append(rtnNum).append(" is begin\n").toString()).append(updateStr(table_name, table_pk, lsCol, lsDataType)).append((new StringBuilder(String.valueOf(rtnRowCnt))).append("\n").toString()).append("END updateWith ;\n").append("\n");
        pkgBody.append("FUNCTION deleteWith(").append(param_pk).append((new StringBuilder(")\n")).append(rtnNum).append(" is begin\n").toString()).append(deleteStr(table_name, lsCol, table_pks)).append((new StringBuilder(String.valueOf(rtnRowCnt))).append("\n").toString()).append("END deleteWith ;\n").append("\n");
        pkgBody.append("FUNCTION retrieveBy( ").append(param_pk).append(")\nreturn types.cursorType is \n").append("rtn_cursor types.cursorType;\n").append("begin \n").append("OPEN rtn_cursor FOR \n").append(retrieveStr(table_name, lsCol, table_pks)).append("return rtn_cursor;\n").append("END retrieveBy;").append("\n");
        return pkgBody;
    }

    public StringBuffer retrieveStr(String table_name, List lsCol, String table_pks[])
    {
        StringBuffer retveStr = new StringBuffer();
        retveStr.append("select\n").append((new StringBuilder()).append(insertColumns(lsCol)).append("\n").toString()).append((new StringBuilder("from ")).append(table_name).append("\n").toString()).append((new StringBuilder("where ")).append(whereStr(lsCol, table_pks)).append(";\n").toString());
        return retveStr;
    }

    public StringBuffer deleteStr(String table_name, List lsCol, String table_pks[])
    {
        StringBuffer deleteStr = new StringBuffer();
        deleteStr.append((new StringBuilder("delete from ")).append(table_name).append("\n").toString()).append((new StringBuilder("where ")).append(whereStr(lsCol, table_pks)).append(";\n").toString());
        return deleteStr;
    }

    public StringBuffer updateStr(String table_name, String table_pk, List lsCol, List lsDataType)
    {
        StringBuffer updateStr = new StringBuffer();
        updateStr.append((new StringBuilder("update ")).append(table_name).append(" set\n").toString()).append((new StringBuilder()).append(updateColumns(table_pk, lsCol)).append(";\n").toString());
        return updateStr;
    }

    public StringBuffer updateColumns(String table_pk, List lsCol)
    {
        StringBuffer uStr = new StringBuffer();
        String table_pks[] = table_pk.split(",");
        int isPk = 0;
        for(int i = 0; i < lsCol.size(); i++){
            isPk = 0;
            for(int j = 0; j < table_pks.length; j++){
                if(lsCol.get(i).toString().toLowerCase().equals(table_pks[j].trim())){
                    isPk++;
                }

	            if(isPk == 0){
	                if(i == lsCol.size() - 1){
	                    uStr.append((new StringBuilder(String.valueOf(lsCol.get(i).toString().toLowerCase()))).append(" = v_").append(lsCol.get(i).toString().toLowerCase()).toString());
	                }else{
	                    uStr.append((new StringBuilder(String.valueOf(lsCol.get(i).toString().toLowerCase()))).append(" = v_").append(lsCol.get(i).toString().toLowerCase()).toString()).append(", ");
	                }
	            }
            }
        }

        uStr.append("\n").append((new StringBuilder("where ")).append(whereStr(lsCol, table_pks)).toString());
        return uStr;
    }

    public StringBuffer insertStr(String table_name, List lsCol, List lsDataType)
    {
        StringBuffer insertStr = new StringBuffer();
        insertStr.append((new StringBuilder("insert into ")).append(table_name).append(" (").toString()).append((new StringBuilder()).append(insertColumns(lsCol)).append(")\n").toString()).append("values(").append((new StringBuilder()).append(pkgParam(lsCol)).append(");\n").toString());
        return insertStr;
    }

    public StringBuffer insertColumns(List lsCol)
    {
        StringBuffer iStr = new StringBuffer();
        for(int i = 0; i < lsCol.size(); i++)
            if(i == lsCol.size() - 1)
                iStr.append(lsCol.get(i).toString().toLowerCase());
            else
                iStr.append(lsCol.get(i).toString().toLowerCase()).append(", ");

        return iStr;
    }

    public StringBuffer pkgParam(List lsCol)
    {
        StringBuffer content = new StringBuffer();
        for(int i = 0; i < lsCol.size(); i++)
            if(i == lsCol.size() - 1)
                content.append((new StringBuilder("v_")).append(lsCol.get(i).toString().toLowerCase()).toString());
            else
                content.append((new StringBuilder("v_")).append(lsCol.get(i).toString().toLowerCase()).toString()).append(", ");

        return content;
    }

    public StringBuffer pkgParam(List lsCol, List lsDataType)
    {
        StringBuffer content = new StringBuffer();
        for(int i = 0; i < lsCol.size(); i++)
            if(i == lsCol.size() - 1)
                content.append((new StringBuilder("v_")).append(lsCol.get(i).toString().toLowerCase()).append(" in ").append(lsDataType.get(i).toString().toLowerCase()).toString());
            else
                content.append((new StringBuilder("v_")).append(lsCol.get(i).toString().toLowerCase()).append(" in ").append(lsDataType.get(i).toString().toLowerCase()).toString()).append(", ");

        return content;
    }

    public StringBuffer whereStr(List lsCol, String table_pks[])
    {
        StringBuffer pkCol = new StringBuffer();
        for(int j = 0; j < lsCol.size(); j++)
        {
            for(int i = 0; i < table_pks.length; i++)
                if(lsCol.get(j).toString().toLowerCase().equals(table_pks[i].trim()))
                    if(i == table_pks.length - 1)
                        pkCol.append((new StringBuilder(String.valueOf(table_pks[i].trim()))).append(" = v_").append(table_pks[i].trim()).toString());
                    else
                        pkCol.append((new StringBuilder(String.valueOf(table_pks[i].trim()))).append(" = v_").append(table_pks[i].trim()).toString()).append(" and ");

        }

        return pkCol;
    }

    public StringBuffer pkgParam(List lsCol, List lsDataType, String table_pks[])
    {
        StringBuffer pkCol = new StringBuffer();
        for(int j = 0; j < lsCol.size(); j++)
        {
            for(int i = 0; i < table_pks.length; i++)
                if(lsCol.get(j).toString().toLowerCase().equals(table_pks[i].trim()))
                    if(i == table_pks.length - 1)
                        pkCol.append((new StringBuilder("v_")).append(table_pks[i].trim()).append(" in ").append(lsDataType.get(j).toString().toLowerCase()).toString());
                    else
                        pkCol.append((new StringBuilder("v_")).append(table_pks[i].trim()).append(" in ").append(lsDataType.get(j).toString().toLowerCase()).toString()).append(", ");

        }

        return pkCol;
    }
}
