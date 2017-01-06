package ion.net.common.db;

import javax.sql.DataSource;

import junit.framework.TestCase;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;

@Slf4j
public class Test extends TestCase {
	
	@Autowired
	static DataSource dataSource;

	public static void testAranga() throws ClassNotFoundException {
		Class<TestUser> clazz = TestUser.class;
		ConstructorAccess<TestUser> ca = ConstructorAccess.get(clazz);
		FieldAccess fa = FieldAccess.get(clazz);
		MethodAccess ma = MethodAccess.get(clazz);
		for(String s : fa.getFieldNames()) {
			System.out.println(s);
		}
		TestUser user2 = ca.newInstance();
		fa.set(user2, "email", "mkeasy2@gmail.com");
		String email = (String) fa.get(user2, "email");
		System.out.println(email);
		
		for(String s : ma.getMethodNames()) {
			System.out.println(s);
		}
		
		log.debug("dataSource : "+dataSource);
		
		Class<?> clazz2 = Class.forName(DataSource.class.getName());
		FieldAccess fa2 = FieldAccess.get(clazz2);
		for(String s : fa2.getFieldNames()) {
			System.out.println(s);
		}
		MethodAccess ma2 = MethodAccess.get(clazz2);
		for(String s : ma.getMethodNames()) {
			System.out.println(s);
		}
		
	}
}
