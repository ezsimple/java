package ion.net.common.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlCall;

public class SqlProcedure<T> extends SqlCall {
	public SqlProcedure(DataSource ds, String sql, List<SqlParameter> paramList) {
		this(sql, paramList) ;
		setDataSource(ds) ;
		compile() ;
	}

	public SqlProcedure(String sql, List<SqlParameter> paramList) {
		setFunction(false) ;
		sql = "{ call " + sql + "}";
		setSql(sql) ;
		setSqlReadyForUse(true) ;
		for(SqlParameter param : paramList){
			declareParameter(param);
		}
	}

	public Map<String, Object> execute(Map<String, ?> inParams){
		validateParameters(inParams.values().toArray());
		return getJdbcTemplate().call(newCallableStatementCreator(inParams), getDeclaredParameters());
	}

	@SuppressWarnings("unchecked")
	public T execute(Object... params){
		if(!this.isCompiled()) compile() ;

		List delaredParams = getDeclaredParameters() ;
		Map<String, Object> inParams = new HashMap<String, Object>();
		for(int i=0; i<delaredParams.size(); i++){
			SqlParameter delaredParam = (SqlParameter) delaredParams.get(i);
			inParams.put(delaredParam.getName(),  params[i]) ;
		}
		execute(inParams) ;
		return (T) new Integer(1) ;
	}

	@SuppressWarnings("unchecked")
	public T executeMap(Map<String, Object> params) {
		if(!this.isCompiled()) compile() ;
		execute(params) ;
		return (T) new Integer(1) ;
	}

	@SuppressWarnings("unchecked")
	public List getDeclaredInputParameters() {
		return getDeclaredParameters() ;
	}

}
