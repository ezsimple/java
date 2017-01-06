package ion.net.common.db.utils;

import ion.net.common.db.DaoFactory;
import ion.net.common.db.PackageService;
import ion.net.common.db.Sample;
import ion.net.common.db.SampleDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;

@Slf4j
@Controller
public class CreatePkgController extends AbstractController {
	
	@Autowired DataSource dataSource;

	@Override
	@RequestMapping(value = "/createpkg.do")
	protected ModelAndView handleRequestInternal(
			HttpServletRequest request,
			HttpServletResponse response) {

		Class clazz = dataSource.getClass();
		MethodAccess ma = MethodAccess.get(clazz);
//		for(String s : ma.getMethodNames())
//			log.debug(s);

		// String s = (String) ma.invoke(dataSource, "getPassword", null);
		// log.debug(s);
		
		// PackageService.getPackage("sample_pkg", dataSource, false);
		PackageService.setDataSource(dataSource);
		SampleDao sampleDao = DaoFactory.getDao(SampleDao.class);
		
		clazz = sampleDao.getClass();
		ma = MethodAccess.get(clazz);
		for(String s : ma.getMethodNames())
			log.debug(s);
		
		log.debug("sampleDao : "+sampleDao);
		String id = "10";
		List<Sample> ret = sampleDao.retrieveBy(id);
		log.debug("ret.size() : "+ret);
//		for(Sample s1 : ret) {
//			log.debug(s1.getDescription());
//		}
		
		
		Connection con = null ;        
		PreparedStatement pstmt = null ;
		ResultSet rs_cont = null ;

		String pkg_header = "" ;
		String pkg_body = "" ;

		ModelAndView mv = new ModelAndView("/sample/createpkg") ;
		try {
			if(null == request.getParameter("table_name") || ("").equals(request.getParameter("table_name"))){
				mv.addObject("pkg_header", null) ;
				mv.addObject("pkg_body", null) ;
				return mv ;
			}else if(null == request.getParameter("table_pk") || ("").equals(request.getParameter("table_pk"))){
				mv.addObject("pkg_header", null) ;
				mv.addObject("pkg_body", null) ;
				return mv ;
			}else if(null == request.getParameter("pkg_nm") || ("").equals(request.getParameter("pkg_nm"))){
				mv.addObject("pkg_header", null) ;
				mv.addObject("pkg_body", null) ;
				return mv ;
			}

			String table_name = request.getParameter("table_name").toUpperCase() ; //Table Name. 파라미터로 받을 것
			String table_pk = request.getParameter("table_pk") ;	//Table Primary Key. 콤마(,) 구분. 파라미터로 받을 것
			String pkg_nm = request.getParameter("pkg_nm").toUpperCase() ;	//Package Name. 파리미터로 받을 것

			CreatePkgController cop = new CreatePkgController() ;

			StringBuffer query = new StringBuffer(" SELECT COLUMN_NAME, DATA_TYPE \n")
			.append(" FROM USER_TAB_COLUMNS \n") 
			.append(" WHERE TABLE_NAME = ? order by column_id") ;     

			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query.toString()) ;
			pstmt.setString(1, table_name) ;

		// log.debug("getNumActive : "+ma.invoke(dataSource, "getNumActive", null));
		// log.debug("getNumIdle : "+ma.invoke(dataSource, "getNumIdle", null));

			rs_cont = pstmt.executeQuery() ;

			List<String> lsCol = new ArrayList<String>() ;
			List<String> lsDataType = new ArrayList<String>() ;

			while(rs_cont.next()){           	
				lsCol.add(rs_cont.getString("COLUMN_NAME")) ;
				lsDataType.add(rs_cont.getString("DATA_TYPE")) ;
			}
			String declareHeaderStr = "CREATE OR REPLACE PACKAGE "+pkg_nm+" IS\n";
			String declareBodyStr = "CREATE OR REPLACE PACKAGE BODY "+pkg_nm+" IS\n" ;
			String endStr = "END "+pkg_nm+";"+"\n/";
			
			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");

			//Package Header
			StringBuffer resultHeader = new StringBuffer(declareHeaderStr)
			.append(cop.pkgHeader(lsCol, lsDataType, table_pk)) 
			.append(endStr) ;


			System.out.println("Package Header");
			System.out.println("---------------------------------------------------------------------------------------------");
			System.out.println(resultHeader) ;
			System.out.println("---------------------------------------------------------------------------------------------");

			//Package Body
			StringBuffer resultBody = new StringBuffer(declareBodyStr)
			.append("/******************************************************************************\n")
			.append("\t작성자   : 작성자이름\n")
			.append("\t내용     : 개발자 배포용 샘플 trigger\n")
			.append("\t테이블   : ")
			.append(table_name.toUpperCase())
			.append("\n")
			.append("\t변경내역 : \n")
			.append("\t-- ")
			.append(format.format(now)+" : 최초 작성(작성자 이름)\n")
			.append("******************************************************************************/\n\n")

			.append(cop.pkgBody(table_name, lsCol, lsDataType, table_pk))
			.append(endStr) ;

			System.out.println("Package Body");  
			System.out.println("---------------------------------------------------------------------------------------------");
			System.out.println(resultBody) ;

			pkg_header = resultHeader.toString() ;
			pkg_body = resultBody.toString() ;

			pstmt.close() ;
			con.close() ;          	  
			
			

		} catch (Exception e){
			e.printStackTrace();
		} finally {
		}
		mv.addObject("pkg_header", pkg_header) ;
		mv.addObject("pkg_body", pkg_body) ;
		return mv ;
	}

	public StringBuffer pkgHeader(List lsCol, List lsDataType, String table_pk){
		StringBuffer pkgHeader = new StringBuffer() ;
		String[] table_pks = table_pk.split(",") ;

		String param = pkgParam(lsCol, lsDataType).toString() ;
		String param_pk = pkgParam(lsCol, lsDataType, table_pks).toString() ;
		String rtnNum = "return number ;" ;
		pkgHeader.append("FUNCTION createWith( ")
		.append(param)
		.append(" )\n")
		.append(rtnNum)
		.append("\n") ;

		pkgHeader.append("FUNCTION updateWith( ")
		.append(param)
		.append(" )\n")
		.append(rtnNum) 
		.append("\n") ;

		pkgHeader.append("FUNCTION deleteWith( ")
		.append(param_pk)
		.append(" )\n")
		.append(rtnNum) 
		.append("\n") ;

		pkgHeader.append("FUNCTION retrieveBy( ")
		.append(param_pk)
		.append(" )\n")
		.append("return types.cursorType;\n")
		.append(rtnNum)
		.append("\n");
		return pkgHeader ;
	}

	public StringBuffer pkgBody(String table_name, List lsCol, List lsDataType, String table_pk){
		StringBuffer pkgBody = new StringBuffer() ;
		String[] table_pks = table_pk.split(",") ;

		String param = pkgParam(lsCol, lsDataType).toString() ;
		String param_pk = pkgParam(lsCol, lsDataType, table_pks).toString() ;
		String rtnNum = "return NUMBER" ;
		String rtnRowCnt = "return SQL%ROWCOUNT;" ;

		pkgBody.append("FUNCTION createWith(")
		.append(param)
		.append(")\n"+rtnNum+"\nis \nbegin\n")
		.append(insertStr(table_name, lsCol, lsDataType))
		.append("\t"+rtnRowCnt+"\n")
		.append("END createWith ;\n")
		.append("\n") ;

		pkgBody.append("FUNCTION updateWith( ")
		.append(param)
		.append(")\n"+rtnNum+"\nis \nbegin\n")
		.append(updateStr(table_name, table_pk, lsCol, lsDataType))
		.append("\t"+rtnRowCnt+"\n")
		.append("END updateWith ;\n")
		.append("\n") ;

		pkgBody.append("FUNCTION deleteWith(")
		.append(param_pk)
		.append(")\n"+rtnNum+"\nis \nbegin\n")
		.append(deleteStr(table_name, lsCol, table_pks))
		.append("\t"+rtnRowCnt+"\n")
		.append("END deleteWith ;\n")
		.append("\n") ;

		pkgBody.append("FUNCTION retrieveBy(")
		.append(param_pk)
		.append(")\n"+"return types.cursorType is \n")
		.append("rtn_cursor types.cursorType;\n")
		.append("begin \n")
		.append("OPEN rtn_cursor FOR \n")
		.append(retrieveStr(table_name, lsCol, table_pks))		
		.append("\treturn rtn_cursor;\n")
		.append("END retrieveBy;")
		.append("\n") ;


		return pkgBody ;
	}


	public StringBuffer retrieveStr(String table_name, List lsCol, String[] table_pks){
		StringBuffer retveStr = new StringBuffer() ;

		retveStr.append("\tselect\n")
		.append(insertColumns(lsCol)+"\n")
		.append("\tfrom "+table_name+"\n")
		.append("\twhere "+whereStr(lsCol, table_pks)+";\n") ;

		return retveStr ;
	}


	public StringBuffer deleteStr(String table_name, List lsCol, String[] table_pks){
		StringBuffer deleteStr = new StringBuffer() ;

		deleteStr.append("\tdelete from "+table_name+"\n")
		.append("\twhere "+whereStr(lsCol, table_pks)+";\n") ;

		return deleteStr ;
	}

	public StringBuffer updateStr(String table_name, String table_pk, List lsCol, List lsDataType){
		StringBuffer updateStr = new StringBuffer() ;		

		updateStr.append("\tupdate "+table_name+"\n\tset \n")
		.append(updateColumns(table_pk, lsCol)+";\n") ;

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
					uStr.append("\t\t"+lsCol.get(i).toString().toLowerCase()+" = v_"+lsCol.get(i).toString().toLowerCase()) ;	
				}else{
					uStr.append("\t\t"+lsCol.get(i).toString().toLowerCase()+" = v_"+lsCol.get(i).toString().toLowerCase())
					.append(", \n") ;
				}
			}
		}
		uStr.append("\n")
		.append("\twhere "+whereStr(lsCol, table_pks)) ;
		return uStr ;
	}

	public StringBuffer insertStr(String table_name, List lsCol, List lsDataType){
		StringBuffer insertStr = new StringBuffer() ;
		insertStr.append("\tinsert into "+table_name+" (\n")
		.append(insertColumns(lsCol)+")\n")
		.append("\tvalues(\n")
		.append(pkgParam(lsCol)+");\n") ;		
		return insertStr ;
	}

	public StringBuffer insertColumns(List lsCol){
		StringBuffer iStr = new StringBuffer() ;
		for(int i=0; i<lsCol.size();i++){
			if(i == lsCol.size()-1){				
				iStr.append("\t\t"+lsCol.get(i).toString().toLowerCase());				
			}
			else{
				iStr.append("\t\t"+lsCol.get(i).toString().toLowerCase())
				.append(", \n") ;
			}			
		}
		return iStr ;
	}

	//파라미터 인자 생성
	public StringBuffer pkgParam(List lsCol){
		StringBuffer content = new StringBuffer() ;
		for(int i=0; i<lsCol.size();i++){
			if(i == lsCol.size()-1){
				content.append("\t\tv_"+lsCol.get(i).toString().toLowerCase()) ;
			}
			else{
				content.append("\t\tv_"+lsCol.get(i).toString().toLowerCase())
				.append(", \n") ;
			}			
		}		
		return content ;
	}

	//파라미터 인자 생성
	public StringBuffer pkgParam(List lsCol, List lsDataType){
		StringBuffer content = new StringBuffer() ;
		for(int i=0; i<lsCol.size();i++){
			if(i == lsCol.size()-1){
				content.append("v_"+lsCol.get(i).toString().toLowerCase()+" in "+lsDataType.get(i).toString().toLowerCase()) ;
			}
			else{
				content.append("v_"+lsCol.get(i).toString().toLowerCase()+" in "+lsDataType.get(i).toString().toLowerCase())
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
						pkCol.append(table_pks[i].trim()+" = v_"+table_pks[i].trim()) ;
					}else{
						pkCol.append(table_pks[i].trim()+" = v_"+table_pks[i].trim())
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
						pkCol.append("v_"+table_pks[i].trim().toLowerCase()+" in "+lsDataType.get(j).toString().toLowerCase()) ;
					}else{
						pkCol.append("v_"+table_pks[i].trim().toLowerCase()+" in "+lsDataType.get(j).toString().toLowerCase())
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
