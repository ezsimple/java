//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.2-hudson-jaxb-ri-2.2-63- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.29 at 05:09:52 \uc624\ud6c4 KST 
//


package net.ion.open.oadr2.model.v20b.greenbutton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
 * Specific value measured by a meter or other asset. Each Reading is associated with a specific ReadingType.
 * 
 * <p>Java class for IntervalReading complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IntervalReading">
 *   &lt;complexContent>
 *     &lt;extension base="{http://naesb.org/espi}Object">
 *       &lt;sequence>
 *         &lt;element name="cost" type="{http://naesb.org/espi}Int48" minOccurs="0"/>
 *         &lt;element name="ReadingQuality" type="{http://naesb.org/espi}ReadingQuality" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="timePeriod" type="{http://naesb.org/espi}DateTimeInterval" minOccurs="0"/>
 *         &lt;element name="value" type="{http://naesb.org/espi}Int48" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IntervalReading", propOrder = {
    "cost",
    "readingQualities",
    "timePeriod",
    "value"
})
@XmlRootElement(name = "IntervalReading")
public class IntervalReading
    extends Object
    implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 1L;
    protected Long cost;
    @XmlElement(name = "ReadingQuality")
    protected List<ReadingQuality> readingQualities;
    protected DateTimeInterval timePeriod;
    protected Long value;

    /**
     * Default no-arg constructor
     * 
     */
    public IntervalReading() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public IntervalReading(final List<java.lang.Object> extensions, final Long cost, final List<ReadingQuality> readingQualities, final DateTimeInterval timePeriod, final Long value) {
        super(extensions);
        this.cost = cost;
        this.readingQualities = readingQualities;
        this.timePeriod = timePeriod;
        this.value = value;
    }

    /**
     * Gets the value of the cost property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCost() {
        return cost;
    }

    /**
     * Sets the value of the cost property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCost(Long value) {
        this.cost = value;
    }

    /**
     * Gets the value of the readingQualities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the readingQualities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReadingQualities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReadingQuality }
     * 
     * 
     */
    public List<ReadingQuality> getReadingQualities() {
        if (readingQualities == null) {
            readingQualities = new ArrayList<ReadingQuality>();
        }
        return this.readingQualities;
    }

    /**
     * Gets the value of the timePeriod property.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeInterval }
     *     
     */
    public DateTimeInterval getTimePeriod() {
        return timePeriod;
    }

    /**
     * Sets the value of the timePeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeInterval }
     *     
     */
    public void setTimePeriod(DateTimeInterval value) {
        this.timePeriod = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setValue(Long value) {
        this.value = value;
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
            Long theCost;
            theCost = this.getCost();
            strategy.appendField(locator, this, "cost", buffer, theCost);
        }
        {
            List<ReadingQuality> theReadingQualities;
            theReadingQualities = (((this.readingQualities!= null)&&(!this.readingQualities.isEmpty()))?this.getReadingQualities():null);
            strategy.appendField(locator, this, "readingQualities", buffer, theReadingQualities);
        }
        {
            DateTimeInterval theTimePeriod;
            theTimePeriod = this.getTimePeriod();
            strategy.appendField(locator, this, "timePeriod", buffer, theTimePeriod);
        }
        {
            Long theValue;
            theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, java.lang.Object object, EqualsStrategy strategy) {
        if (!(object instanceof IntervalReading)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final IntervalReading that = ((IntervalReading) object);
        {
            Long lhsCost;
            lhsCost = this.getCost();
            Long rhsCost;
            rhsCost = that.getCost();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "cost", lhsCost), LocatorUtils.property(thatLocator, "cost", rhsCost), lhsCost, rhsCost)) {
                return false;
            }
        }
        {
            List<ReadingQuality> lhsReadingQualities;
            lhsReadingQualities = (((this.readingQualities!= null)&&(!this.readingQualities.isEmpty()))?this.getReadingQualities():null);
            List<ReadingQuality> rhsReadingQualities;
            rhsReadingQualities = (((that.readingQualities!= null)&&(!that.readingQualities.isEmpty()))?that.getReadingQualities():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "readingQualities", lhsReadingQualities), LocatorUtils.property(thatLocator, "readingQualities", rhsReadingQualities), lhsReadingQualities, rhsReadingQualities)) {
                return false;
            }
        }
        {
            DateTimeInterval lhsTimePeriod;
            lhsTimePeriod = this.getTimePeriod();
            DateTimeInterval rhsTimePeriod;
            rhsTimePeriod = that.getTimePeriod();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "timePeriod", lhsTimePeriod), LocatorUtils.property(thatLocator, "timePeriod", rhsTimePeriod), lhsTimePeriod, rhsTimePeriod)) {
                return false;
            }
        }
        {
            Long lhsValue;
            lhsValue = this.getValue();
            Long rhsValue;
            rhsValue = that.getValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue)) {
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
        int currentHashCode = super.hashCode(locator, strategy);
        {
            Long theCost;
            theCost = this.getCost();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "cost", theCost), currentHashCode, theCost);
        }
        {
            List<ReadingQuality> theReadingQualities;
            theReadingQualities = (((this.readingQualities!= null)&&(!this.readingQualities.isEmpty()))?this.getReadingQualities():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "readingQualities", theReadingQualities), currentHashCode, theReadingQualities);
        }
        {
            DateTimeInterval theTimePeriod;
            theTimePeriod = this.getTimePeriod();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "timePeriod", theTimePeriod), currentHashCode, theTimePeriod);
        }
        {
            Long theValue;
            theValue = this.getValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public IntervalReading withCost(Long value) {
        setCost(value);
        return this;
    }

    public IntervalReading withReadingQualities(ReadingQuality... values) {
        if (values!= null) {
            for (ReadingQuality value: values) {
                getReadingQualities().add(value);
            }
        }
        return this;
    }

    public IntervalReading withReadingQualities(Collection<ReadingQuality> values) {
        if (values!= null) {
            getReadingQualities().addAll(values);
        }
        return this;
    }

    public IntervalReading withTimePeriod(DateTimeInterval value) {
        setTimePeriod(value);
        return this;
    }

    public IntervalReading withValue(Long value) {
        setValue(value);
        return this;
    }

    @Override
    public IntervalReading withExtensions(java.lang.Object... values) {
        if (values!= null) {
            for (java.lang.Object value: values) {
                getExtensions().add(value);
            }
        }
        return this;
    }

    @Override
    public IntervalReading withExtensions(Collection<java.lang.Object> values) {
        if (values!= null) {
            getExtensions().addAll(values);
        }
        return this;
    }

}
