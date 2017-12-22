package net.ion.open.oadr2.xmpp.v20b;

import javax.xml.bind.JAXBException;

import net.ion.open.oadr2.xmpp.PullUnmarshaller;


/**
 * Same as the 2.0a {@link net.ion.open.oadr2.xmpp.XMPPExtensionProvider} but
 * initializes using the {@link JAXBManager} for the 2.0B payloads. 
 */
public class XMPPExtensionProvider extends net.ion.open.oadr2.xmpp.XMPPExtensionProvider {

	public XMPPExtensionProvider() {
        try {
            this.jaxb = new JAXBManager();
            this.unmarshaller = new PullUnmarshaller(jaxb.getContext());
        }
        catch ( JAXBException ex ) {
            throw new RuntimeException("Error initializing JAXB context",ex);
        }
	}
}
