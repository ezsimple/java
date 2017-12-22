package net.ion.open.oadr2.xmpp.v20b;

import javax.xml.bind.JAXBException;

/**
 * Convenience class for using JAXB with OpenADR 2.0b profile
 * generated classes.  See {@link net.ion.open.oadr2.xmpp.JAXBManager}
 * @author tnichols
 */
public class JAXBManager extends net.ion.open.oadr2.xmpp.JAXBManager {

    public static final String DEFAULT_JAXB_CONTEXT_PATH =
        "net.ion.open.oadr2.model.v20b" +
        ":net.ion.open.oadr2.model.v20b.atom" +
        ":net.ion.open.oadr2.model.v20b.currency" +
        ":net.ion.open.oadr2.model.v20b.ei" +
        ":net.ion.open.oadr2.model.v20b.emix" +
        ":net.ion.open.oadr2.model.v20b.gml" +
        ":net.ion.open.oadr2.model.v20b.greenbutton" +
        ":net.ion.open.oadr2.model.v20b.power" +
        ":net.ion.open.oadr2.model.v20b.pyld" +
        ":net.ion.open.oadr2.model.v20b.siscale" +
        ":net.ion.open.oadr2.model.v20b.strm" +
        ":net.ion.open.oadr2.model.v20b.xcal" +
        ":net.ion.open.oadr2.model.v20b.xmldsig" +
        ":net.ion.open.oadr2.model.v20b.xmldsig11";

    public JAXBManager() throws JAXBException {
        super(DEFAULT_JAXB_CONTEXT_PATH);
    }

    @Override
    protected OADR2NamespacePrefixMapper createPrefixMapper() {
        return new OADR2NamespacePrefixMapper();
    }
}
