package ion.net.common.db;

import java.lang.reflect.Field;

public abstract class Setter {
	private static final Setter nullSetter = new Setter(){
		@Override
		public Field getField() {
			return null;
		}
		@Override
		public void set(Object object, Object value) {
		}
	} ;
	abstract public Field getField();
	abstract public void set(Object object,Object value);
}
