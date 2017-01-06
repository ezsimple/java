package ion.net.common.db;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import oracle.jdbc.OracleTypes;

import org.springframework.jdbc.core.SqlReturnType;

public class DefaultSqlReturnType implements SqlReturnType{
    private Class<?> type ;
    
    public DefaultSqlReturnType(Class<?> atype) {
        this.type = atype ;
    }

    public Object getTypeValue(CallableStatement cs, int paramIndex, int sqlType, String typeName) throws SQLException {
        Object value ;
        switch (sqlType) {
        case OracleTypes.NUMBER:{
            if(type == Long.class){
                value = new Long(cs.getLong(paramIndex)) ;
                break ;
            }
        }
        case OracleTypes.INTEGER:{
            value = new Integer(cs.getInt(paramIndex)) ;
            break;
        }
        case OracleTypes.FLOAT:{
            value = new Float(cs.getFloat(paramIndex)) ;
            break;
        }
        case OracleTypes.VARCHAR:
        case OracleTypes.CHAR:
        case OracleTypes.LONGVARCHAR:            
        case OracleTypes.CLOB:{
            value = cs.getString(paramIndex);
            break;            
        }
        case OracleTypes.DATE:            
        case OracleTypes.TIME:
        case OracleTypes.TIMESTAMP:{
            if(type == Date.class){
                value = new Date(cs.getTimestamp(paramIndex).getTime()) ;
                break ;
            }
            if(type == Long.class){
                value = new Long(cs.getTimestamp(paramIndex).getTime()) ;
                break ;
            }
            if(type == String.class){
                Date adate = new Date(cs.getTimestamp(paramIndex).getTime()) ;
                value = new SimpleDateFormat("yyyyMMdd").format(adate) ;
                break ;
            }            
            value = cs.getTimestamp(paramIndex) ;
            break;
        }
        default:
            value = cs.getString(paramIndex);
            break;
        }
        return value;
    }

}
