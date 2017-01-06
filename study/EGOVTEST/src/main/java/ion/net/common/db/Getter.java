package ion.net.common.db;

import java.lang.reflect.Field;

public abstract class Getter {
	private static final Getter nullGetter = new Getter(){
		@Override
		public Object get(Object object) {
			return null;
		}
		@Override
		public Field getField() {
			return null;
		}} ;

		abstract public Field getField();
		abstract public Object get(Object object);
}
