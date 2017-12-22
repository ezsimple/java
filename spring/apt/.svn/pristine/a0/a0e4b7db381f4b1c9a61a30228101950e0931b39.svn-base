package net.ion.oadr2.specification;

import javax.jws.WebService;

import net.ion.open.oadr2.model.OadrCreatedEvent;
import net.ion.open.oadr2.model.OadrDistributeEvent;
import net.ion.open.oadr2.model.OadrRequestEvent;
import net.ion.open.oadr2.model.OadrResponse;


@WebService(name = "EiEvent", targetNamespace = "http://services/")
public interface EiEvent extends Profile20B{
	public OadrDistributeEvent oadrRequestEvent(OadrRequestEvent payload) throws Throwable;
	public OadrResponse oadrCreatedEvent(OadrCreatedEvent payload);
}