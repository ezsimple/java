package net.ion.oadr2.service;

import net.ion.open.oadr2.model.v20b.OadrTransportType;


public interface ADRConstants {

	String PROFILE_20A = "2.0a";
	String PROFILE_20B = "2.0b";

	String TRANSPORT_NAME_SIMPLE_HTTP = OadrTransportType.SIMPLE_HTTP.value();
	String TRANSPORT_NAME_XMPP = OadrTransportType.XMPP.value();

}
