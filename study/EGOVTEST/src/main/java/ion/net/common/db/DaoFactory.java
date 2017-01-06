package ion.net.common.db;

import java.util.Hashtable;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;

@Slf4j
public class DaoFactory {
	
	private static Map<String,Object> daos = new Hashtable<String, Object>();
	private static Map<String,Class<?>> daoNames = new Hashtable<String, Class<?>>();

	protected static Object getDao(String name) {
		return getDao(daoNames.get(name));
	}

	@SuppressWarnings("unchecked")
	public synchronized static <T> T getDao(Class<T> c) {
		log.warn(c.getName());
		T t = (T) daos.get(c.getName());
		if(t == null) {
			t = constructDao(c);
			addDao(c, t);
		}
		return t;
	}
	
	public static void addDao(Class<?> c, Object dao) {
		String name = c.getSimpleName().toLowerCase();
		if(dao instanceof DynamicDao && !((DynamicDao)dao).getPackageName().isEmpty())
			name = ((DynamicDao)dao).getPackageName().toLowerCase();
		daoNames.put(name, c);
		daos.put(c.getName(),dao);
	}
	
	public static <T> T constructDao(Class<T> c) {
		T t = null;
		t = (T) BeanUtils.instantiateClass(c);
		try {
			if(t instanceof DbDao) {
				DbDao dao = (DbDao) t;
				dao.construct();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

}
