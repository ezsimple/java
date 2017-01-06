package ion.net.common.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.ResultSetExtractor;

public class BeanExtractor implements ResultSetExtractor {
	protected final boolean isList;
	@SuppressWarnings("unchecked")
	protected final Class beanType;

	@SuppressWarnings("unchecked")
	public BeanExtractor(Class beanType, boolean isList) {
		this.beanType = beanType;
		this.isList = isList;
	}

	public boolean isList() {
		return isList;
	}

	public Object extractData(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		final List<String> columnNames = DBUtils.getColumnNames(rsmd);

		final List<Setter> fields = new ArrayList<Setter>();

//		ClassFields clazzFields = ClassCache.getClassFields(beanType);
//
//		for (String colName : columnNames) {
//			Setter setter = clazzFields.getSetter(colName);
//			if (setter != null) {
//				fields.add(setter);
//			}
//		}

		if (isList) {
			ArrayList<Object> beanList = new ArrayList<Object>();
			while (rs.next()) {
				beanList.add(createBean(rs, fields));
			}
			return beanList;
		} else {
			Object bean = BeanUtils.instantiateClass(beanType);
			if (rs.next()) {
				createBean(rs, fields, bean);
			}
			return bean;
		}
	}

	private Object createBean(ResultSet rs, List<Setter> fields) {
		Object bean = BeanUtils.instantiateClass(beanType);
		createBean(rs, fields, bean);
		return bean;
	}

	private void createBean(ResultSet rs, List<Setter> fields, Object bean) {
		for (Setter field : fields) {
			try {
			    Object value = DBUtils.getResultSetValue(rs, field.getField().getName()) ;
			    if(value !=null)
			        field.set(bean, value);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
