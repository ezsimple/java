package ion.net.common.db;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.SqlParameter;

public class ParameterParser {
    static final String ThothParamPatternString = "(\\@\\{((\\\\}|[^}])*)\\})(([ ]+[inIN]+[ ]+)([^\\(][\\w]*))*";
    static final Pattern thothParamPattern = Pattern.compile(ThothParamPatternString);
    static final String DefParamPatterString = "\\?" ;
    static final Pattern defParamPattern = Pattern.compile(DefParamPatterString);
    static final String PackageParamPatternString = "(v\\_([\\w]+))(([ ]+[inIN]+[ ]+)([^\\(][\\w]*))";
    static final Pattern packageParamPattern = Pattern.compile(PackageParamPatternString) ;
    
    static final String ThothEscapePatternString = "\\@\\{(\\\\}|[^}])*\\}" ;
    static final Pattern thothEscapePattern = Pattern.compile(ThothEscapePatternString);
    static final String ConstantEscapePatternString = "\\'[^']*\\'" ;
    static final Pattern constantEscapePattern = Pattern.compile(ConstantEscapePatternString);
    
    public static String getParamList(String sql, List<SqlParameter> paramList) {
        ArrayList<String> tempString = new ArrayList<String>() ;
        int idx = 0;

        String tmpSql = "" ;
        int lastChar = 0;
        Matcher thothEscapeMatcher = thothEscapePattern.matcher(sql) ;
        while (thothEscapeMatcher.find()) {
            tempString.add(thothEscapeMatcher.group(0));
           	tmpSql += sql.substring(lastChar, thothEscapeMatcher.start(0)) ; 
            tmpSql +=  "#{" + idx++ + "}" ;
            lastChar = thothEscapeMatcher.end(0) ;
        }  
        if(lastChar > 0){
        	tmpSql +=  sql.substring(lastChar) ;
        	sql = tmpSql ;
        	tmpSql = "" ;
        	lastChar = 0 ;
        }
        
        Matcher constantEscapeMatcher = constantEscapePattern.matcher(sql) ;
        while (constantEscapeMatcher.find()) {
            tempString.add(constantEscapeMatcher.group(0));
           	tmpSql += sql.substring(lastChar, constantEscapeMatcher.start(0)) ; 
            tmpSql +=  "#{" + idx++ + "}" ;
            lastChar = constantEscapeMatcher.end(0) ;
        }             
        if(lastChar > 0){
        	tmpSql +=  sql.substring(lastChar) ;
        	sql = tmpSql ;
        	tmpSql = "" ;
        	lastChar = 0 ;
        }       
        
        Matcher defMatcher = defParamPattern.matcher(sql) ;
        idx = 0;
        while (defMatcher.find()) {
           	tmpSql += sql.substring(lastChar, defMatcher.start(0)) ; 
            tmpSql +=  "@{" + idx++ + "}";
            lastChar = defMatcher.end(0) ;
        }
        
        if(lastChar > 0){
        	tmpSql +=  sql.substring(lastChar) ;
        	sql = tmpSql ;
        	tmpSql = "" ;
        	lastChar = 0 ;
        }   
        
        Matcher pkgMatcher = packageParamPattern.matcher(sql) ;
        while (pkgMatcher.find()) {
           	tmpSql += sql.substring(lastChar, pkgMatcher.start(1)) ; 
            tmpSql +=  "@{" + pkgMatcher.group(2) + "}";
            lastChar = pkgMatcher.end(1) ;
        }        
        
        if(lastChar > 0){
        	tmpSql +=  sql.substring(lastChar) ;
        	sql = tmpSql ;
        	tmpSql = "" ;
        	lastChar = 0 ;
        }           
        for(int i=0; i<tempString.size(); i++){
            sql = sql.replaceFirst("\\#\\{" + i + "\\}", tempString.get(i)) ;
        }
        
        Matcher matcher = thothParamPattern.matcher(sql) ;
        while (matcher.find()) {
            String paramName = matcher.group(2).trim() ;
            String paramType = StringUtils.isBlank(matcher.group(6))? "UNKNOWN" :  matcher.group(6).trim().toUpperCase();
            paramList.add(new SqlParameter(paramName, DBUtils.getSqlType(paramType), paramType));
            sql = StringUtils.replaceOnce(sql, matcher.group(0), "?") ;
        }        
        
        return sql ;
    }
}
