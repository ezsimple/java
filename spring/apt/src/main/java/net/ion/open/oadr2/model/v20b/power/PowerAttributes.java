//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.2-hudson-jaxb-ri-2.2-63- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.29 at 05:09:52 \uc624\ud6c4 KST 
//


package net.ion.open.oadr2.model.v20b.power;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 * <p>Java class for PowerAttributesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PowerAttributesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hertz" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="voltage" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ac" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PowerAttributesType", propOrder = {
    "hertz",
    "voltage",
    "ac"
})
@XmlRootElement(name = "powerAttributes")
public class PowerAttributes implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected BigDecimal hertz;
    @XmlElement(required = true)
    protected BigDecimal voltage;
    protected boolean ac;

    /**
     * Default no-arg constructor
     * 
     */
    public PowerAttributes() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public PowerAttributes(final BigDecimal hertz, final BigDecimal voltage, final boolean ac) {
        this.hertz = hertz;
        this.voltage = voltage;
        this.ac = ac;
    }

    /**
     * Gets the value of the hertz property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHertz() {
        return hertz;
    }

    /**
     * Sets the value of the hertz property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHertz(BigDecimal value) {
        this.hertz = value;
    }

    /**
     * Gets the value of the voltage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVoltage() {
        return voltage;
    }

    /**
     * Sets the value of the voltage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVoltage(BigDecimal value) {
        this.voltage = value;
    }

    /**
     * Gets the value of the ac property.
     * 
     */
    public boolean isAc() {
        return ac;
    }

    /**
     * Sets the value of the ac property.
     * 
     */
    public void setAc(boolean value) {
        this.ac = value;
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
            BigDecimal theHertz;
            theHertz = this.getHertz();
            strategy.appendField(locator, this, "hertz", buffer, theHertz);
        }
        {
            BigDecimal theVoltage;
            theVoltage = this.getVoltage();
            strategy.appendField(locator, this, "voltage", buffer, theVoltage);
        }
        {
            boolean theAc;
            theAc = (true?this.isAc():false);
            strategy.appendField(locator, this, "ac", buffer, theAc);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PowerAttributes)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PowerAttributes that = ((PowerAttributes) object);
        {
            BigDecimal lhsHertz;
            lhsHertz = this.getHertz();
            BigDecimal rhsHertz;
            rhsHertz = that.getHertz();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hertz", lhsHertz), LocatorUtils.property(thatLocator, "hertz", rhsHertz), lhsHertz, rhsHertz)) {
                return false;
            }
        }
        {
            BigDecimal lhsVoltage;
            lhsVoltage = this.getVoltage();
            BigDecimal rhsVoltage;
            rhsVoltage = that.getVoltage();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "voltage", lhsVoltage), LocatorUtils.property(thatLocator, "voltage", rhsVoltage), lhsVoltage, rhsVoltage)) {
                return false;
            }
        }
        {
            boolean lhsAc;
            lhsAc = (true?this.isAc():false);
            boolean rhsAc;
            rhsAc = (true?that.isAc():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ac", lhsAc), LocatorUtils.property(thatLocator, "ac", rhsAc), lhsAc, rhsAc)) {
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
            BigDecimal theHertz;
            theHertz = this.getHertz();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hertz", theHertz), currentHashCode, theHertz);
        }
        {
            BigDecimal theVoltage;
            theVoltage = this.getVoltage();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "voltage", theVoltage), currentHashCode, theVoltage);
        }
        {
            boolean theAc;
            theAc = (true?this.isAc():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "ac", theAc), currentHashCode, theAc);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public PowerAttributes withHertz(BigDecimal value) {
        setHertz(value);
        return this;
    }

    public PowerAttributes withVoltage(BigDecimal value) {
        setVoltage(value);
        return this;
    }

    public PowerAttributes withAc(boolean value) {
        setAc(value);
        return this;
    }

}
