package net.ion.oadr2.specification;

public interface Profile {
	public enum Resource {EiEvent,EiOpt,EiReport,EiRegisterParty,OadrPoll}
	public enum Version {v20a,v20b}
	public static enum Protocol {HTTP,HTTP_PUSH,XMPP}

//	public static final String ENDPOINT_20a_EiEvent 		= "/" + Function.EiEvent.name();
//	public static final String ENDPOINT_20b_EiEvent 		= "/2.0/" + Function.EiEvent.name();
//	public static final String ENDPOINT_20b_EiOpt 			= "/2.0/" + Function.EiOpt.name();
//	public static final String ENDPOINT_20b_EiReport 		= "/2.0/" + Function.EiReport.name();
//	public static final String ENDPOINT_20b_EiRegisterParty = "/2.0/" + Function.EiRegisterParty.name();
//	public static final String ENDPOINT_20b_OadrPoll 		= "/2.0/" + Function.OadrPoll.name();

	public Object handle(Object payload) throws Throwable;

//	public boolean support(Class clazz);
}
