//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.2-hudson-jaxb-ri-2.2-63- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.29 at 05:09:52 \uc624\ud6c4 KST 
//


package net.ion.open.oadr2.model.v20b;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import net.ion.open.oadr2.model.v20b.ei.PayloadBaseType;

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
 * This is the payload for reports that require a status.
 * 
 * <p>Java class for oadrPayloadResourceStatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="oadrPayloadResourceStatusType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://docs.oasis-open.org/ns/energyinterop/201110}PayloadBaseType">
 *       &lt;sequence>
 *         &lt;element name="oadrOnline" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="oadrManualOverride" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element ref="{http://openadr.org/oadr-2.0b/2012/07}oadrLoadControlState" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "oadrPayloadResourceStatusType", propOrder = {
    "oadrOnline",
    "oadrManualOverride",
    "oadrLoadControlState"
})
public class OadrPayloadResourceStatusType
    extends PayloadBaseType
    implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 1L;
    protected boolean oadrOnline;
    protected boolean oadrManualOverride;
    protected OadrLoadControlState oadrLoadControlState;

    /**
     * Default no-arg constructor
     * 
     */
    public OadrPayloadResourceStatusType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public OadrPayloadResourceStatusType(final boolean oadrOnline, final boolean oadrManualOverride, final OadrLoadControlState oadrLoadControlState) {
        super();
        this.oadrOnline = oadrOnline;
        this.oadrManualOverride = oadrManualOverride;
        this.oadrLoadControlState = oadrLoadControlState;
    }

    /**
     * Gets the value of the oadrOnline property.
     * 
     */
    public boolean isOadrOnline() {
        return oadrOnline;
    }

    /**
     * Sets the value of the oadrOnline property.
     * 
     */
    public void setOadrOnline(boolean value) {
        this.oadrOnline = value;
    }

    /**
     * Gets the value of the oadrManualOverride property.
     * 
     */
    public boolean isOadrManualOverride() {
        return oadrManualOverride;
    }

    /**
     * Sets the value of the oadrManualOverride property.
     * 
     */
    public void setOadrManualOverride(boolean value) {
        this.oadrManualOverride = value;
    }

    /**
     * Gets the value of the oadrLoadControlState property.
     * 
     * @return
     *     possible object is
     *     {@link OadrLoadControlState }
     *     
     */
    public OadrLoadControlState getOadrLoadControlState() {
        return oadrLoadControlState;
    }

    /**
     * Sets the value of the oadrLoadControlState property.
     * 
     * @param value
     *     allowed object is
     *     {@link OadrLoadControlState }
     *     
     */
    public void setOadrLoadControlState(OadrLoadControlState value) {
        this.oadrLoadControlState = value;
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
            boolean theOadrOnline;
            theOadrOnline = (true?this.isOadrOnline():false);
            strategy.appendField(locator, this, "oadrOnline", buffer, theOadrOnline);
        }
        {
            boolean theOadrManualOverride;
            theOadrManualOverride = (true?this.isOadrManualOverride():false);
            strategy.appendField(locator, this, "oadrManualOverride", buffer, theOadrManualOverride);
        }
        {
            OadrLoadControlState theOadrLoadControlState;
            theOadrLoadControlState = this.getOadrLoadControlState();
            strategy.appendField(locator, this, "oadrLoadControlState", buffer, theOadrLoadControlState);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OadrPayloadResourceStatusType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final OadrPayloadResourceStatusType that = ((OadrPayloadResourceStatusType) object);
        {
            boolean lhsOadrOnline;
            lhsOadrOnline = (true?this.isOadrOnline():false);
            boolean rhsOadrOnline;
            rhsOadrOnline = (true?that.isOadrOnline():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "oadrOnline", lhsOadrOnline), LocatorUtils.property(thatLocator, "oadrOnline", rhsOadrOnline), lhsOadrOnline, rhsOadrOnline)) {
                return false;
            }
        }
        {
            boolean lhsOadrManualOverride;
            lhsOadrManualOverride = (true?this.isOadrManualOverride():false);
            boolean rhsOadrManualOverride;
            rhsOadrManualOverride = (true?that.isOadrManualOverride():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "oadrManualOverride", lhsOadrManualOverride), LocatorUtils.property(thatLocator, "oadrManualOverride", rhsOadrManualOverride), lhsOadrManualOverride, rhsOadrManualOverride)) {
                return false;
            }
        }
        {
            OadrLoadControlState lhsOadrLoadControlState;
            lhsOadrLoadControlState = this.getOadrLoadControlState();
            OadrLoadControlState rhsOadrLoadControlState;
            rhsOadrLoadControlState = that.getOadrLoadControlState();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "oadrLoadControlState", lhsOadrLoadControlState), LocatorUtils.property(thatLocator, "oadrLoadControlState", rhsOadrLoadControlState), lhsOadrLoadControlState, rhsOadrLoadControlState)) {
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
            boolean theOadrOnline;
            theOadrOnline = (true?this.isOadrOnline():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "oadrOnline", theOadrOnline), currentHashCode, theOadrOnline);
        }
        {
            boolean theOadrManualOverride;
            theOadrManualOverride = (true?this.isOadrManualOverride():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "oadrManualOverride", theOadrManualOverride), currentHashCode, theOadrManualOverride);
        }
        {
            OadrLoadControlState theOadrLoadControlState;
            theOadrLoadControlState = this.getOadrLoadControlState();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "oadrLoadControlState", theOadrLoadControlState), currentHashCode, theOadrLoadControlState);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public OadrPayloadResourceStatusType withOadrOnline(boolean value) {
        setOadrOnline(value);
        return this;
    }

    public OadrPayloadResourceStatusType withOadrManualOverride(boolean value) {
        setOadrManualOverride(value);
        return this;
    }

    public OadrPayloadResourceStatusType withOadrLoadControlState(OadrLoadControlState value) {
        setOadrLoadControlState(value);
        return this;
    }

}
