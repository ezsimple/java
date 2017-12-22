package net.ion.oadr2.specification;

import javax.jws.WebService;

import net.ion.open.oadr2.model.v20b.OadrCancelOpt;
import net.ion.open.oadr2.model.v20b.OadrCanceledOpt;
import net.ion.open.oadr2.model.v20b.OadrCreateOpt;
import net.ion.open.oadr2.model.v20b.OadrCreatedOpt;


@WebService(name = "EiOpt", targetNamespace = "http://services/")
public interface EiOpt extends Profile20B {

	public OadrCreatedOpt oadrCreateOpt(OadrCreateOpt payload);
	public OadrCanceledOpt oadrCancelOpt(OadrCancelOpt payload);
}
