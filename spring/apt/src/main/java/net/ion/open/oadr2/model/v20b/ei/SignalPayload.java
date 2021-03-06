//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.2-hudson-jaxb-ri-2.2-63- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.29 at 05:09:52 \uc624\ud6c4 KST 
//


package net.ion.open.oadr2.model.v20b.ei;

import java.io.Serializable;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

import net.ion.open.oadr2.model.v20b.OadrPayloadResourceStatusType;
import net.ion.open.oadr2.model.v20b.strm.StreamPayloadBase;

import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for signalPayloadType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="signalPayloadType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ietf:params:xml:ns:icalendar-2.0:stream}StreamPayloadBaseType">
 *       &lt;choice>
 *         &lt;element ref="{http://docs.oasis-open.org/ns/energyinterop/201110}payloadBase"/>
 *       &lt;/choice>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signalPayloadType", propOrder = {
    "payloadBase"
})
public class SignalPayload
    extends StreamPayloadBase
    implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElementRef(name = "payloadBase", namespace = "http://docs.oasis-open.org/ns/energyinterop/201110", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends PayloadBaseType> payloadBase;

    /**
     * Default no-arg constructor
     * 
     */
    public SignalPayload() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public SignalPayload(final JAXBElement<? extends PayloadBaseType> payloadBase) {
        super();
        this.payloadBase = payloadBase;
    }

    /**
     * Gets the value of the payloadBase property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PayloadBaseType }{@code >}
     *     {@link JAXBElement }{@code <}{@link OadrPayloadResourceStatusType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PayloadFloatType }{@code >}
     *     
     */
    public JAXBElement<? extends PayloadBaseType> getPayloadBase() {
        return payloadBase;
    }

    /**
     * Sets the value of the payloadBase property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PayloadBaseType }{@code >}
     *     {@link JAXBElement }{@code <}{@link OadrPayloadResourceStatusType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PayloadFloatType }{@code >}
     *     
     */
    public void setPayloadBase(JAXBElement<? extends PayloadBaseType> value) {
        this.payloadBase = ((JAXBElement<? extends PayloadBaseType> ) value);
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        super.appendFields(locator, buffer, strategy);
        {
            JAXBElement<? extends PayloadBaseType> thePayloadBase;
            thePayloadBase = this.getPayloadBase();
            strategy.appendField(locator, this, "payloadBase", buffer, thePayloadBase);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SignalPayload)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final SignalPayload that = ((SignalPayload) object);
        {
            JAXBElement<? extends PayloadBaseType> lhsPayloadBase;
            lhsPayloadBase = this.getPayloadBase();
            JAXBElement<? extends PayloadBaseType> rhsPayloadBase;
            rhsPayloadBase = that.getPayloadBase();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "payloadBase", lhsPayloadBase), LocatorUtils.property(thatLocator, "payloadBase", rhsPayloadBase), lhsPayloadBase, rhsPayloadBase)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            JAXBElement<? extends PayloadBaseType> thePayloadBase;
            thePayloadBase = this.getPayloadBase();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "payloadBase", thePayloadBase), currentHashCode, thePayloadBase);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public SignalPayload withPayloadBase(JAXBElement<? extends PayloadBaseType> value) {
        setPayloadBase(value);
        return this;
    }

}
