package net.ion.oadr2.specification;

import javax.jws.WebService;

import net.ion.open.oadr2.model.v20b.OadrCreatedEvent;
import net.ion.open.oadr2.model.v20b.OadrDistributeEvent;
import net.ion.open.oadr2.model.v20b.OadrRequestEvent;
import net.ion.open.oadr2.model.v20b.OadrResponse;


@WebService(name = "EiEvent", targetNamespace = "http://services/")
public interface EiEvent20B extends Profile20B{
	public OadrDistributeEvent oadrRequestEvent(OadrRequestEvent payload) throws Throwable;
	public OadrResponse oadrCreatedEvent(OadrCreatedEvent payload);
}