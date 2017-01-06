package ion.net.common.db;

public class FunctionSpec extends PLSQLPackage {

	public FunctionSpec(String name, String specification) {
		setName( name );
		setType("FUNCTION");
		setPlsql( specification );
	}
	
}
