package ion.net.common.db;

import org.springframework.core.NestedRuntimeException;

public class NotExistPackage extends NestedRuntimeException {

	private static final long serialVersionUID = -2059593657329818693L;

	public NotExistPackage(String msg) {
		super("Not Exist Package or Function : "+msg);
	}
}
