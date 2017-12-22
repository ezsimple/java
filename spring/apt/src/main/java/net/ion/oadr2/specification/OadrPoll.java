package net.ion.oadr2.specification;

import javax.jws.WebService;

@WebService(name = "OadrPoll", targetNamespace = "http://services/")
public interface OadrPoll extends Profile20B {

	public Object oadrPoll(net.ion.open.oadr2.model.v20b.OadrPoll oadrPoll) throws Throwable;

}
