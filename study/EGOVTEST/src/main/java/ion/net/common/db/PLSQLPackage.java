package ion.net.common.db;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class PLSQLPackage implements SqlStatement{
	private String name;
	private String type;
	private String plsql;
	
	public String toStatement() {
		return getPlsql();
	}
}
