package ion.net.common.db;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public abstract class DynamicDao extends DbDao {
	public abstract String getPackageName();

	protected void initDao() {
		String packageName = getPackageName();
		if(packageName == null || packageName.isEmpty()) {
			log.error("packageName is null or Empty");
			return;
		}
	}

	protected void bindDao() {
		String packageName = this.getPackageName();
		if(packageName == null || packageName.isEmpty()) return;
		
		List<FunctionSpec> functions = PackageService.getPackageFunctions(packageName);
		if(functions.size()==0) throw new NotExistPackage(packageName);
		
		for(FunctionSpec s : functions)
			log.debug(s.getName());
		
		Class<?> c = this.getClass();
		ReflectionUtils.doWithFields(c, new ReflectionUtils.FieldCallback() {
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				log.debug(field.toString());
			}
		}, new ReflectionUtils.FieldFilter(){
			public boolean matches(Field field) {
				if(SqlObject.class.isAssignableFrom(field.getType()) 
						&& field.getGenericType() instanceof ParameterizedType)
					return true ;
				return false;
			}
		});
		
	}

}
