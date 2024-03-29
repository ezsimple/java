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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import net.ion.open.oadr2.model.v20b.currency.ISO3AlphaCurrencyCodeContentType;
import net.ion.open.oadr2.model.v20b.emix.ItemBaseType;

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
 * currency
 * 
 * <p>Java class for currencyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="currencyType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://docs.oasis-open.org/ns/emix/2011/06}ItemBaseType">
 *       &lt;sequence>
 *         &lt;element name="itemDescription" type="{http://openadr.org/oadr-2.0b/2012/07}currencyItemDescriptionType"/>
 *         &lt;element name="itemUnits" type="{urn:un:unece:uncefact:codelist:standard:5:ISO42173A:2010-04-07}ISO3AlphaCurrencyCodeContentType"/>
 *         &lt;element ref="{http://docs.oasis-open.org/ns/emix/2011/06/siscale}siScaleCode"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "currencyType", propOrder = {
    "itemDescription",
    "itemUnits",
    "siScaleCode"
})
public class CurrencyType
    extends ItemBaseType
    implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected CurrencyItemDescriptionType itemDescription;
    @XmlElement(required = true)
    protected ISO3AlphaCurrencyCodeContentType itemUnits;
    @XmlElement(namespace = "http://docs.oasis-open.org/ns/emix/2011/06/siscale", required = true)
    protected String siScaleCode;

    /**
     * Default no-arg constructor
     * 
     */
    public CurrencyType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public CurrencyType(final CurrencyItemDescriptionType itemDescription, final ISO3AlphaCurrencyCodeContentType itemUnits, final String siScaleCode) {
        super();
        this.itemDescription = itemDescription;
        this.itemUnits = itemUnits;
        this.siScaleCode = siScaleCode;
    }

    /**
     * Gets the value of the itemDescription property.
     * 
     * @return
     *     possible object is
     *     {@link CurrencyItemDescriptionType }
     *     
     */
    public CurrencyItemDescriptionType getItemDescription() {
        return itemDescription;
    }

    /**
     * Sets the value of the itemDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyItemDescriptionType }
     *     
     */
    public void setItemDescription(CurrencyItemDescriptionType value) {
        this.itemDescription = value;
    }

    /**
     * Gets the value of the itemUnits property.
     * 
     * @return
     *     possible object is
     *     {@link ISO3AlphaCurrencyCodeContentType }
     *     
     */
    public ISO3AlphaCurrencyCodeContentType getItemUnits() {
        return itemUnits;
    }

    /**
     * Sets the value of the itemUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO3AlphaCurrencyCodeContentType }
     *     
     */
    public void setItemUnits(ISO3AlphaCurrencyCodeContentType value) {
        this.itemUnits = value;
    }

    /**
     * Gets the value of the siScaleCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiScaleCode() {
        return siScaleCode;
    }

    /**
     * Sets the value of the siScaleCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiScaleCode(String value) {
        this.siScaleCode = value;
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
            CurrencyItemDescriptionType theItemDescription;
            theItemDescription = this.getItemDescription();
            strategy.appendField(locator, this, "itemDescription", buffer, theItemDescription);
        }
        {
            ISO3AlphaCurrencyCodeContentType theItemUnits;
            theItemUnits = this.getItemUnits();
            strategy.appendField(locator, this, "itemUnits", buffer, theItemUnits);
        }
        {
            String theSiScaleCode;
            theSiScaleCode = this.getSiScaleCode();
            strategy.appendField(locator, this, "siScaleCode", buffer, theSiScaleCode);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CurrencyType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final CurrencyType that = ((CurrencyType) object);
        {
            CurrencyItemDescriptionType lhsItemDescription;
            lhsItemDescription = this.getItemDescription();
            CurrencyItemDescriptionType rhsItemDescription;
            rhsItemDescription = that.getItemDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "itemDescription", lhsItemDescription), LocatorUtils.property(thatLocator, "itemDescription", rhsItemDescription), lhsItemDescription, rhsItemDescription)) {
                return false;
            }
        }
        {
            ISO3AlphaCurrencyCodeContentType lhsItemUnits;
            lhsItemUnits = this.getItemUnits();
            ISO3AlphaCurrencyCodeContentType rhsItemUnits;
            rhsItemUnits = that.getItemUnits();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "itemUnits", lhsItemUnits), LocatorUtils.property(thatLocator, "itemUnits", rhsItemUnits), lhsItemUnits, rhsItemUnits)) {
                return false;
            }
        }
        {
            String lhsSiScaleCode;
            lhsSiScaleCode = this.getSiScaleCode();
            String rhsSiScaleCode;
            rhsSiScaleCode = that.getSiScaleCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "siScaleCode", lhsSiScaleCode), LocatorUtils.property(thatLocator, "siScaleCode", rhsSiScaleCode), lhsSiScaleCode, rhsSiScaleCode)) {
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
            CurrencyItemDescriptionType theItemDescription;
            theItemDescription = this.getItemDescription();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "itemDescription", theItemDescription), currentHashCode, theItemDescription);
        }
        {
            ISO3AlphaCurrencyCodeContentType theItemUnits;
            theItemUnits = this.getItemUnits();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "itemUnits", theItemUnits), currentHashCode, theItemUnits);
        }
        {
            String theSiScaleCode;
            theSiScaleCode = this.getSiScaleCode();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "siScaleCode", theSiScaleCode), currentHashCode, theSiScaleCode);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public CurrencyType withItemDescription(CurrencyItemDescriptionType value) {
        setItemDescription(value);
        return this;
    }

    public CurrencyType withItemUnits(ISO3AlphaCurrencyCodeContentType value) {
        setItemUnits(value);
        return this;
    }

    public CurrencyType withSiScaleCode(String value) {
        setSiScaleCode(value);
        return this;
    }

}
