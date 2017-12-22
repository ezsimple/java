package net.ion.oadr2.specification;

import javax.jws.WebService;

import net.ion.open.oadr2.model.v20b.OadrCanceledReport;
import net.ion.open.oadr2.model.v20b.OadrResponse;


@WebService(name = "EiReport", targetNamespace = "http://services/")
public interface EiReport extends Profile20B{
	public net.ion.open.oadr2.model.v20b.OadrRegisteredReport oadrRegisterReport(net.ion.open.oadr2.model.v20b.OadrRegisterReport payload) throws Throwable;

	public OadrResponse oadrCreatedReport(net.ion.open.oadr2.model.v20b.OadrCreatedReport payload) throws Throwable;

	public net.ion.open.oadr2.model.v20b.OadrCreatedReport oadrCreateReport(net.ion.open.oadr2.model.v20b.OadrCreateReport payload) throws Throwable;

	public net.ion.open.oadr2.model.v20b.OadrUpdatedReport oadrUpdateReport(net.ion.open.oadr2.model.v20b.OadrUpdateReport payload) throws Throwable;

	public OadrCanceledReport oadrCancelReport(net.ion.open.oadr2.model.v20b.OadrCancelReport payload) throws Throwable;

	OadrResponse oadrCanceledReport(OadrCanceledReport payload) throws Throwable;
}
