// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CreateOraPackage.java

package net.ion.plugin.cstore.commUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreateOraQuery
{

    public CreateOraQuery()
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
            CreateOraQuery cop = new CreateOraQuery();
            StringBuffer query = (new StringBuffer(" SELECT COLUMN_NAME, DATA_TYPE \n")).append(" FROM USER_TAB_COLUMNS \n").append(" WHERE TABLE_NAME = ? ");
            String table_name = "cs_contents_main";
            String table_pk = "cid,mid";
            table_name = table_name.toUpperCase();
            table_pk = table_pk.toLowerCase();
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pstmt = con.prepareStatement(query.toString());
            pstmt.setString(1, table_name);
            rs_cont = pstmt.executeQuery();
            List lsCol = new ArrayList();
            List lsDataType = new ArrayList();
            for(; rs_cont.next(); lsDataType.add(rs_cont.getString("DATA_TYPE")))
                lsCol.add(rs_cont.getString("COLUMN_NAME"));
            String declareBodyStr = (new StringBuilder("**************SQL 문*************")).append("\n\n").toString();
            StringBuffer resultBody = (new StringBuffer(declareBodyStr)).append(cop.pkgBody(table_name, lsCol, lsDataType, table_pk));
            System.out.println(resultBody);
            pstmt.close();
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public StringBuffer pkgBody(String table_name, List lsCol, List lsDataType, String table_pk){
        StringBuffer pkgBody = new StringBuffer() ;
        String[] table_pks = table_pk.split(",") ;
        
        String param = pkgParam(lsCol, lsDataType).toString() ;
        String param_pk = pkgParam(lsCol, lsDataType, table_pks).toString() ;
        String declareBodyStr = "String sql = \" \\n \"\n";    
        String line = "---------------------------------------------------------------------------------------------";
        
        pkgBody.append("-- INSERT문 --\n\n")
        .append(declareBodyStr)
        .append(insertStr(table_name, lsCol, lsDataType))
        .append("\n\n")
        .append(line)
        .append("\n\n");
        
        pkgBody.append("-- UPDATE문 --\n\n")
        .append(declareBodyStr)
        .append(updateStr(table_name, table_pk, lsCol, lsDataType))
        .append("\n\n")
        .append(line)
        .append("\n\n");
        
        pkgBody.append("-- DELETE문 --\n\n")
        .append(declareBodyStr)
        .append(deleteStr(table_name, lsCol, table_pks))
        .append("\n\n")
        .append(line)
        .append("\n\n");
        
        pkgBody.append("-- SELECT문 --\n\n")
        .append(declareBodyStr)
        .append(retrieveStr(table_name, lsCol, table_pks));
        
        return pkgBody ;
    }
    
    
    public StringBuffer retrieveStr(String table_name, List lsCol, String[] table_pks){
        StringBuffer retveStr = new StringBuffer() ;
        
        retveStr.append("\t+ \" select \"\n")
        .append(insertColumns(lsCol))
        .append("\t+ \" from "+table_name+" \"\n")
        .append("\t+ \" where "+whereStr(lsCol, table_pks)) ;
        
        return retveStr ;
    }
    
    
    public StringBuffer deleteStr(String table_name, List lsCol, String[] table_pks){
        StringBuffer deleteStr = new StringBuffer() ;
        
        deleteStr.append("\t+ \" delete from "+table_name+" \"\n")
        .append("\t+ \" where "+whereStr(lsCol, table_pks)+"\t+ \" \\n \";") ;
        
        return deleteStr ;
    }
    
    public StringBuffer updateStr(String table_name, String table_pk, List lsCol, List lsDataType){
        StringBuffer updateStr = new StringBuffer() ;       
        
        updateStr.append("\t+ \" update "+table_name+" set \"\n")
        .append(updateColumns(table_pk, lsCol)+"\t+ \" \\n \";") ;
        
        return updateStr ;
    }
    
    public StringBuffer updateColumns(String table_pk, List lsCol){
        StringBuffer uStr = new StringBuffer() ;
        String[] table_pks = table_pk.split(",") ;
        
        int isPk = 0 ;
        for(int i=0; i<lsCol.size(); i++){
            isPk = 0 ;
            for(int j=0; j<table_pks.length; j++){          
                if(lsCol.get(i).toString().toLowerCase().equals(table_pks[j].trim())){
                    isPk++ ;
                }
            }
            if(isPk == 0){
                if(i == lsCol.size()-1){
                    uStr.append("\t+ \"\t"+lsCol.get(i).toString().toLowerCase()+" = :"+lsCol.get(i).toString().toLowerCase()).append(" \"\n") ;   
                }else{
                    uStr.append("\t+ \"\t"+lsCol.get(i).toString().toLowerCase()+" = :"+lsCol.get(i).toString().toLowerCase()).append(", \" \n") ;
                }
            }
        }
        uStr.append("\t+ \" where "+whereStr(lsCol, table_pks)) ;
        return uStr ;
    }
    
    public StringBuffer insertStr(String table_name, List lsCol, List lsDataType){
        StringBuffer insertStr = new StringBuffer() ;
        insertStr.append("\t+ \" Insert into "+table_name+" ( \"\n")
        .append(insertColumns(lsCol)+"\t+ \" ) ")
        .append("values ( \"\n")
        .append(pkgParam(lsCol)+"\t+ \" ) \"\n").append("\t+ \" \\n \";") ;       
        return insertStr ;
    }
    
    public StringBuffer insertColumns(List lsCol){
        StringBuffer iStr = new StringBuffer() ;
        for(int i=0; i<lsCol.size();i++){
            if(i == lsCol.size()-1){                
                iStr.append("\t+ \"\t"+lsCol.get(i).toString().toLowerCase())
                .append(" \"\n");              
            }
            else{
                iStr.append("\t+ \"\t"+lsCol.get(i).toString().toLowerCase())
                .append(", \" \n") ;
            }           
        }
        return iStr ;
    }
    
    //파라미터 인자 생성
    public StringBuffer pkgParam(List lsCol){
        StringBuffer content = new StringBuffer() ;
        for(int i=0; i<lsCol.size();i++){
            if(i == lsCol.size()-1){
                content.append("\t+ \"\t:"+lsCol.get(i).toString().toLowerCase())
                .append(" \"\n") ;
            }
            else{
                content.append("\t+ \"\t:"+lsCol.get(i).toString().toLowerCase())
                .append(", \" \n") ;
            }           
        }       
        return content ;
    }
    
    //파라미터 인자 생성
    public StringBuffer pkgParam(List lsCol, List lsDataType){
        StringBuffer content = new StringBuffer() ;
        for(int i=0; i<lsCol.size();i++){
            if(i == lsCol.size()-1){
                content.append(":"+lsCol.get(i).toString().toLowerCase()+" in "+lsDataType.get(i).toString().toLowerCase()) ;
            }
            else{
                content.append(":"+lsCol.get(i).toString().toLowerCase()+" in "+lsDataType.get(i).toString().toLowerCase())
                .append(", ") ;
            }           
        }       
        return content ;
    }
    
    public StringBuffer whereStr(List lsCol, String[] table_pks){
        StringBuffer pkCol = new StringBuffer() ;
        for(int j=0; j<lsCol.size();j++){
            for(int i=0; i< table_pks.length;i++){
                if(lsCol.get(j).toString().toLowerCase().equals(table_pks[i].trim())){  
                    if(i == table_pks.length-1){
                        pkCol.append(table_pks[i].trim()+" = :"+table_pks[i].trim())
                        .append(" \"\n") ;
                    }else{
                        pkCol.append(table_pks[i].trim()+" = :"+table_pks[i].trim())
                        .append(" and ") ;
                    }
                }
            }           
        }
        return pkCol ;
    }
    
    //파라미터 인자 생성( PK )
    public StringBuffer pkgParam(List lsCol, List lsDataType, String[] table_pks){
        StringBuffer pkCol = new StringBuffer() ;
        
        for(int j=0; j<lsCol.size();j++){
            for(int i=0; i< table_pks.length;i++){
                if(lsCol.get(j).toString().toLowerCase().equals(table_pks[i].trim().toLowerCase())){    
                    if(i == table_pks.length-1){
                        pkCol.append(":"+table_pks[i].trim().toLowerCase()+" in "+lsDataType.get(j).toString().toLowerCase()) ;
                    }else{
                        pkCol.append(":"+table_pks[i].trim().toLowerCase()+" in "+lsDataType.get(j).toString().toLowerCase())
                        .append(", ") ;
                    }
                }else{
                    //throw new Exception("Wrong PK") ;
                }
            }           
        }
        return pkCol ;
    }
}
