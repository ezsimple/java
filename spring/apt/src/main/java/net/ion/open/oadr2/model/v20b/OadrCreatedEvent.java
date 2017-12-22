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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.ion.open.oadr2.model.v20b.pyld.EiCreatedEvent;

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
 * <p>Java class for oadrCreatedEventType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="oadrCreatedEventType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://docs.oasis-open.org/ns/energyinterop/201110/payloads}eiCreatedEvent"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://docs.oasis-open.org/ns/energyinterop/201110}schemaVersion"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "oadrCreatedEventType", propOrder = {
    "eiCreatedEvent"
})
@XmlRootElement(name = "oadrCreatedEvent")
public class OadrCreatedEvent implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(namespace = "http://docs.oasis-open.org/ns/energyinterop/201110/payloads", required = true)
    protected EiCreatedEvent eiCreatedEvent;
    @XmlAttribute(name = "schemaVersion", namespace = "http://docs.oasis-open.org/ns/energyinterop/201110")
    protected String schemaVersion;

    /**
     * Default no-arg constructor
     * 
     */
    public OadrCreatedEvent() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public OadrCreatedEvent(final EiCreatedEvent eiCreatedEvent, final String schemaVersion) {
        this.eiCreatedEvent = eiCreatedEvent;
        this.schemaVersion = schemaVersion;
    }

    /**
     * Gets the value of the eiCreatedEvent property.
     * 
     * @return
     *     possible object is
     *     {@link EiCreatedEvent }
     *     
     */
    public EiCreatedEvent getEiCreatedEvent() {
        return eiCreatedEvent;
    }

    /**
     * Sets the value of the eiCreatedEvent property.
     * 
     * @param value
     *     allowed object is
     *     {@link EiCreatedEvent }
     *     
     */
    public void setEiCreatedEvent(EiCreatedEvent value) {
        this.eiCreatedEvent = value;
    }

    /**
     * Gets the value of the schemaVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchemaVersion() {
        return schemaVersion;
    }

    /**
     * Sets the value of the schemaVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchemaVersion(String value) {
        this.schemaVersion = value;
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
        {
            EiCreatedEvent theEiCreatedEvent;
            theEiCreatedEvent = this.getEiCreatedEvent();
            strategy.appendField(locator, this, "eiCreatedEvent", buffer, theEiCreatedEvent);
        }
        {
            String theSchemaVersion;
            theSchemaVersion = this.getSchemaVersion();
            strategy.appendField(locator, this, "schemaVersion", buffer, theSchemaVersion);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OadrCreatedEvent)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final OadrCreatedEvent that = ((OadrCreatedEvent) object);
        {
            EiCreatedEvent lhsEiCreatedEvent;
            lhsEiCreatedEvent = this.getEiCreatedEvent();
            EiCreatedEvent rhsEiCreatedEvent;
            rhsEiCreatedEvent = that.getEiCreatedEvent();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eiCreatedEvent", lhsEiCreatedEvent), LocatorUtils.property(thatLocator, "eiCreatedEvent", rhsEiCreatedEvent), lhsEiCreatedEvent, rhsEiCreatedEvent)) {
                return false;
            }
        }
        {
            String lhsSchemaVersion;
            lhsSchemaVersion = this.getSchemaVersion();
            String rhsSchemaVersion;
            rhsSchemaVersion = that.getSchemaVersion();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "schemaVersion", lhsSchemaVersion), LocatorUtils.property(thatLocator, "schemaVersion", rhsSchemaVersion), lhsSchemaVersion, rhsSchemaVersion)) {
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
        int currentHashCode = 1;
        {
            EiCreatedEvent theEiCreatedEvent;
            theEiCreatedEvent = this.getEiCreatedEvent();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "eiCreatedEvent", theEiCreatedEvent), currentHashCode, theEiCreatedEvent);
        }
        {
            String theSchemaVersion;
            theSchemaVersion = this.getSchemaVersion();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "schemaVersion", theSchemaVersion), currentHashCode, theSchemaVersion);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public OadrCreatedEvent withEiCreatedEvent(EiCreatedEvent value) {
        setEiCreatedEvent(value);
        return this;
    }

    public OadrCreatedEvent withSchemaVersion(String value) {
        setSchemaVersion(value);
        return this;
    }

}
