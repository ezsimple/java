package ion.net.common.db;

import java.util.ArrayList;

import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;

@SuppressWarnings("unchecked")
public class SqlGenerator {
    public static final String FUNCTION = "FUNCTION" ;
    public static final String PROCEDURE = "PROCEDURE" ;
    public static final String RETURN = "RETURN" ;
    public static final String START_PARAM = "(" ;
    public static final String END_PARAM = ")" ;
    public static final String IN = "IN" ;

    public static final String RESULT = "RESULT" ;

    public static SqlObject createSqlDao(DataSource ds, String packageName, String callString, Class parent, boolean isList){
        String upperCall = callString.toUpperCase() ;

        if(upperCall.startsWith(PROCEDURE)){
        	return createSqlDaoProcedure(ds, packageName, callString) ;
        }else {
        	return createSqlDaoFunction(ds, packageName, callString, parent, isList) ;
        }
    }

	public static SqlObject createSqlDaoFunction(DataSource ds, String packageName, String callString, Class parent, boolean isList){
        ArrayList<SqlParameter> paramList = new ArrayList<SqlParameter>();
        String call = StringUtils.substringBeforeLast(ParameterParser.getParamList(callString, paramList), ";").trim();
        String upperCall = call.toUpperCase() ;

        if(!StringUtils.contains(upperCall, RETURN)){
            upperCall = upperCall + " RETURN CURSORTYPE" ;
            call = call + " RETURN CURSORTYPE" ;
        }
        call = call.substring(0, upperCall.indexOf(RETURN)) ;
        // FUNCTION  nodeType.columnBy( @{tId} IN VARCHAR2 )  return varchar2 ;
        if(upperCall.startsWith(FUNCTION) ){
            call = call.substring(FUNCTION.length()) ;
        }
        // nodeType.columnBy( ? IN VARCHAR2 )  return types.cursorType ; or nodeType.columnBy( @{tId} )
        
        String returnStr = StringUtils.strip(StringUtils.substringAfter(upperCall, RETURN)) ;
        SqlOutParameter returnType = getReturnType(parent, null, isList, returnStr);
        SqlObject sqlObject = (SqlObject) new SqlFunction(ds, packageName + "." + call, paramList, returnType) ;

        return sqlObject ;
    }

	
    public static SqlObject createSqlDaoProcedure(DataSource ds, String packageName, String callString){
        ArrayList<SqlParameter> paramList = new ArrayList<SqlParameter>();
        String call = StringUtils.substringBeforeLast(ParameterParser.getParamList(callString, paramList), ";").trim();
        String sql = StringUtils.substringBefore(call, START_PARAM) ;

        sql = sql.substring(PROCEDURE.length()) ;
        SqlObject sqlObject = (SqlObject) new SqlProcedure(ds, packageName + "." + sql, paramList) ;

        return sqlObject ;
    }

    private static SqlOutParameter getReturnType(Class parent, Class child, boolean isList, String returnStr) {
    	SqlOutParameter returnType ;
    	if(!(returnStr.equals("TYPES.CURSORTYPE") || returnStr.equals("CURSORTYPE"))){
    		returnType = new SqlOutParameter(RESULT, DBUtils.getSqlType(returnStr), null, new DefaultSqlReturnType(parent));    
    	}else{
    		returnType = new SqlOutParameter(RESULT, DBUtils.getSqlType(returnStr), new BeanExtractor(parent, isList));
    	}
    	return returnType;
    }

}
