package net.ion.oadr2.specification;

import javax.jws.WebService;

@WebService(name = "EiRegisterParty", targetNamespace = "http://services/")
public interface EiRegisterParty extends Profile20B {
	public net.ion.open.oadr2.model.v20b.OadrCreatedPartyRegistration oadrQueryRegistration(net.ion.open.oadr2.model.v20b.OadrQueryRegistration payload);

	public net.ion.open.oadr2.model.v20b.OadrCreatedPartyRegistration oadrCreatePartyRegistration(net.ion.open.oadr2.model.v20b.OadrCreatePartyRegistration payload) throws Throwable;

	public net.ion.open.oadr2.model.v20b.OadrCanceledPartyRegistration oadrCancelPartyRegistration(net.ion.open.oadr2.model.v20b.OadrCancelPartyRegistration payload) throws Throwable;

	public net.ion.open.oadr2.model.v20b.OadrResponse oadrCanceledPartyRegistration(net.ion.open.oadr2.model.v20b.OadrCanceledPartyRegistration payload);

	public net.ion.open.oadr2.model.v20b.OadrResponse oadrResponse(net.ion.open.oadr2.model.v20b.OadrResponse payload);

}