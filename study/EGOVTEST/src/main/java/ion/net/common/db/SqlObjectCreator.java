package ion.net.common.db;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class SqlObjectCreator {

	private final Field sqlObjectField;
	private boolean isList;
	private Class<?> beanType;

	public SqlObjectCreator(Field field) {
		this.sqlObjectField = field;
		ParameterizedType sqlObjectType = (ParameterizedType) field.getGenericType();
		if (sqlObjectType.getActualTypeArguments().length > 0) {
			java.lang.reflect.Type firstType = sqlObjectType.getActualTypeArguments()[0];
			Class<?> firstTypeClass = null;
			if (firstType instanceof ParameterizedType) {
				firstTypeClass = (Class<?>) ((ParameterizedType) firstType).getRawType() ;
				if (firstTypeClass == List.class) {
					isList = true;
					Class<?> secondTypeClass = (Class<?>) ((ParameterizedType) firstType).getActualTypeArguments()[0];
					if (!secondTypeClass.getPackage().getName().startsWith("java.lang") && secondTypeClass != Map.class) {
						beanType = secondTypeClass;
					}
				}

			} else {
				getBeanType(firstType);
			}
		}
	}

    private void getBeanType(java.lang.reflect.Type type) {
        Class<?> typeClass;
        typeClass = (Class<?>) type;
        if (typeClass != Map.class) {
        	beanType = typeClass;
        }
    }

	public boolean isSqlObjectMethod(Method method) {
		return sqlObjectField.getName().equalsIgnoreCase(method.getName());
	}

	public void setReturnType(Class<?> returnType) {
		if (List.class.isAssignableFrom(returnType)) {
			this.isList = true;
		}
	}

	public void create(DataSource ds, Object dao, String packageName, String callString) {
		try {
			sqlObjectField.setAccessible(true) ;
			sqlObjectField.set(dao, SqlGenerator.createSqlDao(ds, packageName, callString, beanType, isList));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return (isList ? "List " : "Bean ") + sqlObjectField.getName();
	}

	public String getName() {
		return sqlObjectField.getName();
	}

	public Field getField() {
		return this.sqlObjectField;
	}

}
