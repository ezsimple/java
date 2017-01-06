package ion.net.common.db;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
public class PackageService {
	
	@Getter
	@Setter
	@Autowired
	private static DataSource dataSource;
	
	static String pkgSpecSql = "select * from user_source "
					+"where name = upper(?) "
					+"and type = 'PACKAGE' order by name, line";

	static String pkgBodySql = "select * from user_source "
			+"where name = upper(?) "
			+"and type = 'PACKAGE BODY' order by name,line";

	public static List<FunctionSpec> getPackageFunctions(String packageName) {
		String pkgHeader = getPackage(packageName, true);
		return SqlPackageUtils.getParsedSqlPackageHeader(pkgHeader);
	}
	
	public static String getPackage(String packageName, boolean isSpec) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(pkgSpecSql, packageName);

		StringBuilder buf = new StringBuilder();
		buf.append("create or replace ");
		for(Map<String, Object> s:list)
			buf.append(s.get("TEXT").toString());
		log.debug(buf.toString());

		return buf.toString();
	}
}
