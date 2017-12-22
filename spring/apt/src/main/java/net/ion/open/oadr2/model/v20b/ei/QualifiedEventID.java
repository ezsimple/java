//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.2-hudson-jaxb-ri-2.2-63- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.29 at 05:09:52 \uc624\ud6c4 KST 
//


package net.ion.open.oadr2.model.v20b.ei;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
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
 * Fully qualified event ID includes the eventID and the modificationNumber.
 * 
 * <p>Java class for QualifiedEventIDType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QualifiedEventIDType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://docs.oasis-open.org/ns/energyinterop/201110}eventID"/>
 *         &lt;element ref="{http://docs.oasis-open.org/ns/energyinterop/201110}modificationNumber"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QualifiedEventIDType", propOrder = {
    "eventID",
    "modificationNumber"
})
public class QualifiedEventID implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected String eventID;
    @XmlSchemaType(name = "unsignedInt")
    protected long modificationNumber;

    /**
     * Default no-arg constructor
     * 
     */
    public QualifiedEventID() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public QualifiedEventID(final String eventID, final long modificationNumber) {
        this.eventID = eventID;
        this.modificationNumber = modificationNumber;
    }

    /**
     * Gets the value of the eventID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * Sets the value of the eventID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventID(String value) {
        this.eventID = value;
    }

    /**
     * Gets the value of the modificationNumber property.
     * 
     */
    public long getModificationNumber() {
        return modificationNumber;
    }

    /**
     * Sets the value of the modificationNumber property.
     * 
     */
    public void setModificationNumber(long value) {
        this.modificationNumber = value;
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
            String theEventID;
            theEventID = this.getEventID();
            strategy.appendField(locator, this, "eventID", buffer, theEventID);
        }
        {
            long theModificationNumber;
            theModificationNumber = (true?this.getModificationNumber(): 0L);
            strategy.appendField(locator, this, "modificationNumber", buffer, theModificationNumber);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof QualifiedEventID)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final QualifiedEventID that = ((QualifiedEventID) object);
        {
            String lhsEventID;
            lhsEventID = this.getEventID();
            String rhsEventID;
            rhsEventID = that.getEventID();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eventID", lhsEventID), LocatorUtils.property(thatLocator, "eventID", rhsEventID), lhsEventID, rhsEventID)) {
                return false;
            }
        }
        {
            long lhsModificationNumber;
            lhsModificationNumber = (true?this.getModificationNumber(): 0L);
            long rhsModificationNumber;
            rhsModificationNumber = (true?that.getModificationNumber(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "modificationNumber", lhsModificationNumber), LocatorUtils.property(thatLocator, "modificationNumber", rhsModificationNumber), lhsModificationNumber, rhsModificationNumber)) {
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
            String theEventID;
            theEventID = this.getEventID();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "eventID", theEventID), currentHashCode, theEventID);
        }
        {
            long theModificationNumber;
            theModificationNumber = (true?this.getModificationNumber(): 0L);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "modificationNumber", theModificationNumber), currentHashCode, theModificationNumber);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public QualifiedEventID withEventID(String value) {
        setEventID(value);
        return this;
    }

    public QualifiedEventID withModificationNumber(long value) {
        setModificationNumber(value);
        return this;
    }

}
