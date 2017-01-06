package ion.net.common.db;

import java.beans.PropertyDescriptor;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.support.JdbcUtils;

@SuppressWarnings("unchecked")
public abstract class DBUtils {
	public static final int PROPERTY_NOT_FOUND = -1;

	public static int getSqlType(String paramType) {
		if (paramType.equals("UNKNOWN")) {
			return JdbcUtils.TYPE_UNKNOWN;
		} else if (paramType.equals("VARCHAR2")) {
			return OracleTypes.VARCHAR;
		} else if (paramType.equals("NUMBER")) {
			return OracleTypes.NUMBER;
		} else if (paramType.equals("TYPES.CURSORTYPE")) {
			return OracleTypes.CURSOR;
		} else if (paramType.equals("CURSORTYPE")) {
			return OracleTypes.CURSOR;
		} else if (paramType.equals("DATE")) {
			return Types.TIMESTAMP;
		} else if (paramType.equals("CLOB")) {
			return Types.CLOB;
		} else if (paramType.equals("BLOB")) {
			return Types.BLOB;
		} else if (paramType.equals("CHAR")) {
			return Types.CHAR;
		} else if (paramType.equals("INTEGER")) {
			return Types.INTEGER;
		} else if (paramType.equals("FLOAT")) {
			return Types.FLOAT;
		}
		return Types.OTHER;
	}

	public static boolean isCompatibleType(Object value, Class type) {

		// Do object check first, then primitives
		if (type.isInstance(value)) {
			return true;
		} else if (value == null && !type.isPrimitive()) {
			return true;
		} else if (type.equals(Integer.TYPE) && Integer.class.isInstance(value)) {
			return true;
		} else if (type.equals(Long.TYPE) && Long.class.isInstance(value)) {
			return true;
		} else if (type.equals(Double.TYPE) && Double.class.isInstance(value)) {
			return true;
		} else if (type.equals(Float.TYPE) && Float.class.isInstance(value)) {
			return true;
		} else if (type.equals(Short.TYPE) && Short.class.isInstance(value)) {
			return true;
		} else if (type.equals(Byte.TYPE) && Byte.class.isInstance(value)) {
			return true;
		} else if ((type.equals(Character.TYPE) && Character.class.isInstance(value)) || type.equals(char.class)) {
			return true;
		} else if (type.equals(Boolean.TYPE) && Boolean.class.isInstance(value)) {
			return true;
		} else {
			return false;
		}
	}
	
    public static Object getResultSetStringValue(ResultSet rs, int index) throws SQLException {
        Object obj = rs.getObject(index);
        if (obj instanceof Blob) {
            obj = rs.getBytes(index);
        }
        else if (obj instanceof Clob) {
            obj = rs.getString(index);
        }
        else if (obj != null && obj.getClass().getName().startsWith("oracle.sql.TIMESTAMP")) {
            obj = rs.getTimestamp(index);
        }
        else if (obj != null && obj.getClass().getName().startsWith("oracle.sql.DATE")) {
            String metaDataClassName = rs.getMetaData().getColumnClassName(index);
            if ("java.sql.Timestamp".equals(metaDataClassName) ||
                    "oracle.sql.TIMESTAMP".equals(metaDataClassName)) {
                obj = rs.getTimestamp(index);
            }
            else {
                obj = rs.getDate(index);
            }
        }
        else if (obj != null && obj instanceof java.sql.Date) {
            if ("java.sql.Timestamp".equals(rs.getMetaData().getColumnClassName(index))) {
                obj = rs.getTimestamp(index);
            }
        }
        if(obj == null) return "" ;
        return obj;
    }	
	public static Object getResultSetValue(ResultSet rs, int index) throws SQLException {
		Object obj = rs.getObject(index);
		if (obj instanceof Blob) {
			obj = rs.getBytes(index);
		}
		else if (obj instanceof Clob) {
			obj = rs.getString(index);
		}
		else if (obj != null && obj.getClass().getName().startsWith("oracle.sql.TIMESTAMP")) {
			obj = rs.getTimestamp(index);
		}
		else if (obj != null && obj.getClass().getName().startsWith("oracle.sql.DATE")) {
			String metaDataClassName = rs.getMetaData().getColumnClassName(index);
			if ("java.sql.Timestamp".equals(metaDataClassName) ||
					"oracle.sql.TIMESTAMP".equals(metaDataClassName)) {
				obj = rs.getTimestamp(index);
			}
			else {
				obj = rs.getDate(index);
			}
		}
		else if (obj != null && obj instanceof java.sql.Date) {
			if ("java.sql.Timestamp".equals(rs.getMetaData().getColumnClassName(index))) {
				obj = rs.getTimestamp(index);
			}
		}
		return obj;
	}
	
	public static Object getResultSetValue(ResultSet rs, String columName) throws SQLException {
		Object obj = rs.getObject(columName);
		if (obj instanceof Blob) {
			obj = rs.getBytes(columName);
		}
		else if (obj instanceof Clob) {
			obj = rs.getString(columName);
		}
		else if (obj != null && obj.getClass().getName().startsWith("oracle.sql.TIMESTAMP")) {
			obj = rs.getTimestamp(columName);
		}
		else if (obj != null && obj.getClass().getName().startsWith("oracle.sql.DATE")) {
				obj = rs.getTimestamp(columName);
		}
		else if (obj != null && obj instanceof java.sql.Date) {
				obj = rs.getTimestamp(columName);
		}
		return obj;
	}
	
	public static int[] mapColumnsToProperties(ResultSetMetaData rsmd, PropertyDescriptor[] props) throws SQLException {
		int cols = rsmd.getColumnCount();
		int columnToProperty[] = new int[cols + 1];

		for (int col = 1; col <= cols; col++) {
			String columnName = rsmd.getColumnName(col);
			for (int i = 0; i < props.length; i++) {
				if (columnName.equalsIgnoreCase(props[i].getName())) {
					columnToProperty[col] = i;
					break;
				} else if (columnName.equalsIgnoreCase("is" + props[i].getName())) {
					columnToProperty[col] = i;
					break;
				} else {
					columnToProperty[col] = PROPERTY_NOT_FOUND;
				}
			}
		}
		return columnToProperty;
	}

	public static List<String> getColumnNames(ResultSetMetaData rsmd) throws SQLException {
		List<String> columnNames = new ArrayList<String>();
		for (int col = 1; col <= rsmd.getColumnCount(); col++) {
			columnNames.add(rsmd.getColumnName(col).toLowerCase());
		}

		return columnNames;
	}
	
}
