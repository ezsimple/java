package ion.net.common.db;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class SqlPackageUtils {
	static Pattern FunctionPattern = Pattern.compile("(((FUNCTION|PROCEDURE)(\\s)+([^\\s(]+)(\\s*)\\(?([^);]+)\\)?(\\s)*([^;]*);))",Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);
	static Pattern FunctionNamePattern = Pattern.compile("((FUNCTION|PROCEDURE)(\\s)+([^\\s()]+)(\\s*))",Pattern.CASE_INSENSITIVE|Pattern.MULTILINE );
	public static List<FunctionSpec> getParsedSqlPackageHeader(String headerString){		
		Matcher m = FunctionPattern.matcher(headerString);
		
		List<FunctionSpec> heads = new ArrayList<FunctionSpec>();
		while(m.find()){	
			String all = m.group();
			Matcher m2 = FunctionNamePattern.matcher(all);
			
			while(m2.find()){
				String name = StringUtils.strip(m2.group(4));
				heads.add(new FunctionSpec(name, all)) ;
			}			

		}
		return heads;
	}	
	 
	
}
