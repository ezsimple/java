//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.2-hudson-jaxb-ri-2.2-63- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.29 at 05:09:52 \uc624\ud6c4 KST 
//


package net.ion.open.oadr2.model.v20b.power;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.ion.open.oadr2.model.v20b.gml.FeatureCollection;

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
 * A customer ServiceLocation has one or more ServiceDeliveryPoint(s), which in turn relate to Meters. The location may be a point or a polygon, depending on the specific circumstances. For distribution, the ServiceLocation is typically the location of the utility customer's premise. 
 * 
 * <p>Java class for ServiceLocationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceLocationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml/3.2}FeatureCollection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceLocationType", propOrder = {
    "featureCollection"
})
@XmlRootElement(name = "serviceLocation")
public class ServiceLocation implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "FeatureCollection", namespace = "http://www.opengis.net/gml/3.2", required = true)
    protected FeatureCollection featureCollection;

    /**
     * Default no-arg constructor
     * 
     */
    public ServiceLocation() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public ServiceLocation(final FeatureCollection featureCollection) {
        this.featureCollection = featureCollection;
    }

    /**
     * Gets the value of the featureCollection property.
     * 
     * @return
     *     possible object is
     *     {@link FeatureCollection }
     *     
     */
    public FeatureCollection getFeatureCollection() {
        return featureCollection;
    }

    /**
     * Sets the value of the featureCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link FeatureCollection }
     *     
     */
    public void setFeatureCollection(FeatureCollection value) {
        this.featureCollection = value;
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
            FeatureCollection theFeatureCollection;
            theFeatureCollection = this.getFeatureCollection();
            strategy.appendField(locator, this, "featureCollection", buffer, theFeatureCollection);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ServiceLocation)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ServiceLocation that = ((ServiceLocation) object);
        {
            FeatureCollection lhsFeatureCollection;
            lhsFeatureCollection = this.getFeatureCollection();
            FeatureCollection rhsFeatureCollection;
            rhsFeatureCollection = that.getFeatureCollection();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "featureCollection", lhsFeatureCollection), LocatorUtils.property(thatLocator, "featureCollection", rhsFeatureCollection), lhsFeatureCollection, rhsFeatureCollection)) {
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
            FeatureCollection theFeatureCollection;
            theFeatureCollection = this.getFeatureCollection();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "featureCollection", theFeatureCollection), currentHashCode, theFeatureCollection);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public ServiceLocation withFeatureCollection(FeatureCollection value) {
        setFeatureCollection(value);
        return this;
    }

}