//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.2-hudson-jaxb-ri-2.2-63- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.29 at 05:09:52 \uc624\ud6c4 KST 
//


package net.ion.open.oadr2.model.v20b.greenbutton;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 * [extension] Interharmonics are represented as a rational number 'numerator' / 'denominator', and harmonics are represented using the same mechanism and identified by 'denominator'=1.
 * 
 * <p>Java class for ReadingInterharmonic complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReadingInterharmonic">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numerator" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="denominator" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReadingInterharmonic", propOrder = {
    "numerator",
    "denominator"
})
public class ReadingInterharmonic implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 1L;
    protected BigInteger numerator;
    protected java.lang.Object denominator;

    /**
     * Default no-arg constructor
     * 
     */
    public ReadingInterharmonic() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public ReadingInterharmonic(final BigInteger numerator, final java.lang.Object denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Gets the value of the numerator property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumerator() {
        return numerator;
    }

    /**
     * Sets the value of the numerator property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumerator(BigInteger value) {
        this.numerator = value;
    }

    /**
     * Gets the value of the denominator property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Object }
     *     
     */
    public java.lang.Object getDenominator() {
        return denominator;
    }

    /**
     * Sets the value of the denominator property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Object }
     *     
     */
    public void setDenominator(java.lang.Object value) {
        this.denominator = value;
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
            BigInteger theNumerator;
            theNumerator = this.getNumerator();
            strategy.appendField(locator, this, "numerator", buffer, theNumerator);
        }
        {
            java.lang.Object theDenominator;
            theDenominator = this.getDenominator();
            strategy.appendField(locator, this, "denominator", buffer, theDenominator);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, java.lang.Object object, EqualsStrategy strategy) {
        if (!(object instanceof ReadingInterharmonic)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ReadingInterharmonic that = ((ReadingInterharmonic) object);
        {
            BigInteger lhsNumerator;
            lhsNumerator = this.getNumerator();
            BigInteger rhsNumerator;
            rhsNumerator = that.getNumerator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "numerator", lhsNumerator), LocatorUtils.property(thatLocator, "numerator", rhsNumerator), lhsNumerator, rhsNumerator)) {
                return false;
            }
        }
        {
            java.lang.Object lhsDenominator;
            lhsDenominator = this.getDenominator();
            java.lang.Object rhsDenominator;
            rhsDenominator = that.getDenominator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "denominator", lhsDenominator), LocatorUtils.property(thatLocator, "denominator", rhsDenominator), lhsDenominator, rhsDenominator)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(java.lang.Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            BigInteger theNumerator;
            theNumerator = this.getNumerator();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "numerator", theNumerator), currentHashCode, theNumerator);
        }
        {
            java.lang.Object theDenominator;
            theDenominator = this.getDenominator();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "denominator", theDenominator), currentHashCode, theDenominator);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public ReadingInterharmonic withNumerator(BigInteger value) {
        setNumerator(value);
        return this;
    }

    public ReadingInterharmonic withDenominator(java.lang.Object value) {
        setDenominator(value);
        return this;
    }

}
