package ion.net.common.db;

import java.util.Map;

public class ClassFields{
	private Map<String,Setter> setter;
	private Map<String,Getter> getter;
	public ClassFields(Map<String,Setter> setter,Map<String,Getter> getter){
		this.setter = setter;
		this.getter = getter;
	}

	public Setter getSetter(String fieleName){
		return setter.get(fieleName);
	}

	public Getter getGetter(String fieleName){
		return getter.get(fieleName);
	}
	
	public Map<String, Getter> getGetters(){
	    return getter ;
	}
}
