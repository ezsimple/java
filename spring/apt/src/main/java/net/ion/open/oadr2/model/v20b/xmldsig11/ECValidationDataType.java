//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.2-hudson-jaxb-ri-2.2-63- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.29 at 05:09:52 \uc624\ud6c4 KST 
//


package net.ion.open.oadr2.model.v20b.xmldsig11;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 * <p>Java class for ECValidationDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ECValidationDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seed" type="{http://www.w3.org/2000/09/xmldsig#}CryptoBinary"/>
 *       &lt;/sequence>
 *       &lt;attribute name="hashAlgorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ECValidationDataType", propOrder = {
    "seed"
})
public class ECValidationDataType implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected byte[] seed;
    @XmlAttribute(name = "hashAlgorithm", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String hashAlgorithm;

    /**
     * Default no-arg constructor
     * 
     */
    public ECValidationDataType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public ECValidationDataType(final byte[] seed, final String hashAlgorithm) {
        this.seed = seed;
        this.hashAlgorithm = hashAlgorithm;
    }

    /**
     * Gets the value of the seed property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getSeed() {
        return seed;
    }

    /**
     * Sets the value of the seed property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setSeed(byte[] value) {
        this.seed = ((byte[]) value);
    }

    /**
     * Gets the value of the hashAlgorithm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHashAlgorithm() {
        return hashAlgorithm;
    }

    /**
     * Sets the value of the hashAlgorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHashAlgorithm(String value) {
        this.hashAlgorithm = value;
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
            byte[] theSeed;
            theSeed = this.getSeed();
            strategy.appendField(locator, this, "seed", buffer, theSeed);
        }
        {
            String theHashAlgorithm;
            theHashAlgorithm = this.getHashAlgorithm();
            strategy.appendField(locator, this, "hashAlgorithm", buffer, theHashAlgorithm);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ECValidationDataType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ECValidationDataType that = ((ECValidationDataType) object);
        {
            byte[] lhsSeed;
            lhsSeed = this.getSeed();
            byte[] rhsSeed;
            rhsSeed = that.getSeed();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "seed", lhsSeed), LocatorUtils.property(thatLocator, "seed", rhsSeed), lhsSeed, rhsSeed)) {
                return false;
            }
        }
        {
            String lhsHashAlgorithm;
            lhsHashAlgorithm = this.getHashAlgorithm();
            String rhsHashAlgorithm;
            rhsHashAlgorithm = that.getHashAlgorithm();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hashAlgorithm", lhsHashAlgorithm), LocatorUtils.property(thatLocator, "hashAlgorithm", rhsHashAlgorithm), lhsHashAlgorithm, rhsHashAlgorithm)) {
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
            byte[] theSeed;
            theSeed = this.getSeed();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "seed", theSeed), currentHashCode, theSeed);
        }
        {
            String theHashAlgorithm;
            theHashAlgorithm = this.getHashAlgorithm();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hashAlgorithm", theHashAlgorithm), currentHashCode, theHashAlgorithm);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public ECValidationDataType withSeed(byte[] value) {
        setSeed(value);
        return this;
    }

    public ECValidationDataType withHashAlgorithm(String value) {
        setHashAlgorithm(value);
        return this;
    }

}
