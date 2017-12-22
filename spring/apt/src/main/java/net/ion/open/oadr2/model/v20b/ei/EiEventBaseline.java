//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.2-hudson-jaxb-ri-2.2-63- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.29 at 05:09:52 \uc624\ud6c4 KST 
//


package net.ion.open.oadr2.model.v20b.ei;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.ion.open.oadr2.model.v20b.BaseUnitType;
import net.ion.open.oadr2.model.v20b.CurrencyType;
import net.ion.open.oadr2.model.v20b.CurrentType;
import net.ion.open.oadr2.model.v20b.FrequencyType;
import net.ion.open.oadr2.model.v20b.OadrGBItemBase;
import net.ion.open.oadr2.model.v20b.PulseCountType;
import net.ion.open.oadr2.model.v20b.TemperatureType;
import net.ion.open.oadr2.model.v20b.ThermType;
import net.ion.open.oadr2.model.v20b.emix.ItemBaseType;
import net.ion.open.oadr2.model.v20b.power.EnergyApparentType;
import net.ion.open.oadr2.model.v20b.power.EnergyItemType;
import net.ion.open.oadr2.model.v20b.power.EnergyReactiveType;
import net.ion.open.oadr2.model.v20b.power.EnergyRealType;
import net.ion.open.oadr2.model.v20b.power.PowerApparentType;
import net.ion.open.oadr2.model.v20b.power.PowerItemType;
import net.ion.open.oadr2.model.v20b.power.PowerReactiveType;
import net.ion.open.oadr2.model.v20b.power.PowerRealType;
import net.ion.open.oadr2.model.v20b.power.VoltageType;
import net.ion.open.oadr2.model.v20b.strm.Intervals;
import net.ion.open.oadr2.model.v20b.xcal.Dtstart;
import net.ion.open.oadr2.model.v20b.xcal.DurationPropType;

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
 * <p>Java class for eiEventBaselineType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="eiEventBaselineType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:ietf:params:xml:ns:icalendar-2.0}dtstart"/>
 *         &lt;element ref="{urn:ietf:params:xml:ns:icalendar-2.0}duration"/>
 *         &lt;element ref="{urn:ietf:params:xml:ns:icalendar-2.0:stream}intervals"/>
 *         &lt;element name="baselineID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://docs.oasis-open.org/ns/energyinterop/201110}resourceID" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="baselineName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://docs.oasis-open.org/ns/emix/2011/06}itemBase" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eiEventBaselineType", propOrder = {
    "dtstart",
    "duration",
    "intervals",
    "baselineID",
    "resourceIDs",
    "baselineName",
    "itemBase"
})
@XmlRootElement(name = "eiEventBaseline")
public class EiEventBaseline implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(namespace = "urn:ietf:params:xml:ns:icalendar-2.0", required = true)
    protected Dtstart dtstart;
    @XmlElement(namespace = "urn:ietf:params:xml:ns:icalendar-2.0", required = true)
    protected DurationPropType duration;
    @XmlElement(namespace = "urn:ietf:params:xml:ns:icalendar-2.0:stream", required = true)
    protected Intervals intervals;
    @XmlElement(required = true)
    protected String baselineID;
    @XmlElement(name = "resourceID")
    protected List<String> resourceIDs;
    @XmlElement(required = true)
    protected String baselineName;
    @XmlElementRef(name = "itemBase", namespace = "http://docs.oasis-open.org/ns/emix/2011/06", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends ItemBaseType> itemBase;

    /**
     * Default no-arg constructor
     * 
     */
    public EiEventBaseline() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public EiEventBaseline(final Dtstart dtstart, final DurationPropType duration, final Intervals intervals, final String baselineID, final List<String> resourceIDs, final String baselineName, final JAXBElement<? extends ItemBaseType> itemBase) {
        this.dtstart = dtstart;
        this.duration = duration;
        this.intervals = intervals;
        this.baselineID = baselineID;
        this.resourceIDs = resourceIDs;
        this.baselineName = baselineName;
        this.itemBase = itemBase;
    }

    /**
     * Gets the value of the dtstart property.
     * 
     * @return
     *     possible object is
     *     {@link Dtstart }
     *     
     */
    public Dtstart getDtstart() {
        return dtstart;
    }

    /**
     * Sets the value of the dtstart property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dtstart }
     *     
     */
    public void setDtstart(Dtstart value) {
        this.dtstart = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link DurationPropType }
     *     
     */
    public DurationPropType getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link DurationPropType }
     *     
     */
    public void setDuration(DurationPropType value) {
        this.duration = value;
    }

    /**
     * Gets the value of the intervals property.
     * 
     * @return
     *     possible object is
     *     {@link Intervals }
     *     
     */
    public Intervals getIntervals() {
        return intervals;
    }

    /**
     * Sets the value of the intervals property.
     * 
     * @param value
     *     allowed object is
     *     {@link Intervals }
     *     
     */
    public void setIntervals(Intervals value) {
        this.intervals = value;
    }

    /**
     * Gets the value of the baselineID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaselineID() {
        return baselineID;
    }

    /**
     * Sets the value of the baselineID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaselineID(String value) {
        this.baselineID = value;
    }

    /**
     * Gets the value of the resourceIDs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceIDs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceIDs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getResourceIDs() {
        if (resourceIDs == null) {
            resourceIDs = new ArrayList<String>();
        }
        return this.resourceIDs;
    }

    /**
     * Gets the value of the baselineName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaselineName() {
        return baselineName;
    }

    /**
     * Sets the value of the baselineName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaselineName(String value) {
        this.baselineName = value;
    }

    /**
     * This is the unit of the signal.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CurrentType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EnergyItemType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PowerReactiveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CurrencyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link FrequencyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EnergyRealType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PulseCountType }{@code >}
     *     {@link JAXBElement }{@code <}{@link BaseUnitType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PowerRealType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EnergyReactiveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CurrencyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CurrencyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ItemBaseType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PowerApparentType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CurrencyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EnergyApparentType }{@code >}
     *     {@link JAXBElement }{@code <}{@link VoltageType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PowerItemType }{@code >}
     *     {@link JAXBElement }{@code <}{@link OadrGBItemBase }{@code >}
     *     {@link JAXBElement }{@code <}{@link ThermType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TemperatureType }{@code >}
     *     
     */
    public JAXBElement<? extends ItemBaseType> getItemBase() {
        return itemBase;
    }

    /**
     * Sets the value of the itemBase property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CurrentType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EnergyItemType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PowerReactiveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CurrencyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link FrequencyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EnergyRealType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PulseCountType }{@code >}
     *     {@link JAXBElement }{@code <}{@link BaseUnitType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PowerRealType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EnergyReactiveType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CurrencyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CurrencyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ItemBaseType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PowerApparentType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CurrencyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EnergyApparentType }{@code >}
     *     {@link JAXBElement }{@code <}{@link VoltageType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PowerItemType }{@code >}
     *     {@link JAXBElement }{@code <}{@link OadrGBItemBase }{@code >}
     *     {@link JAXBElement }{@code <}{@link ThermType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TemperatureType }{@code >}
     *     
     */
    public void setItemBase(JAXBElement<? extends ItemBaseType> value) {
        this.itemBase = ((JAXBElement<? extends ItemBaseType> ) value);
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
            Dtstart theDtstart;
            theDtstart = this.getDtstart();
            strategy.appendField(locator, this, "dtstart", buffer, theDtstart);
        }
        {
            DurationPropType theDuration;
            theDuration = this.getDuration();
            strategy.appendField(locator, this, "duration", buffer, theDuration);
        }
        {
            Intervals theIntervals;
            theIntervals = this.getIntervals();
            strategy.appendField(locator, this, "intervals", buffer, theIntervals);
        }
        {
            String theBaselineID;
            theBaselineID = this.getBaselineID();
            strategy.appendField(locator, this, "baselineID", buffer, theBaselineID);
        }
        {
            List<String> theResourceIDs;
            theResourceIDs = (((this.resourceIDs!= null)&&(!this.resourceIDs.isEmpty()))?this.getResourceIDs():null);
            strategy.appendField(locator, this, "resourceIDs", buffer, theResourceIDs);
        }
        {
            String theBaselineName;
            theBaselineName = this.getBaselineName();
            strategy.appendField(locator, this, "baselineName", buffer, theBaselineName);
        }
        {
            JAXBElement<? extends ItemBaseType> theItemBase;
            theItemBase = this.getItemBase();
            strategy.appendField(locator, this, "itemBase", buffer, theItemBase);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof EiEventBaseline)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final EiEventBaseline that = ((EiEventBaseline) object);
        {
            Dtstart lhsDtstart;
            lhsDtstart = this.getDtstart();
            Dtstart rhsDtstart;
            rhsDtstart = that.getDtstart();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dtstart", lhsDtstart), LocatorUtils.property(thatLocator, "dtstart", rhsDtstart), lhsDtstart, rhsDtstart)) {
                return false;
            }
        }
        {
            DurationPropType lhsDuration;
            lhsDuration = this.getDuration();
            DurationPropType rhsDuration;
            rhsDuration = that.getDuration();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "duration", lhsDuration), LocatorUtils.property(thatLocator, "duration", rhsDuration), lhsDuration, rhsDuration)) {
                return false;
            }
        }
        {
            Intervals lhsIntervals;
            lhsIntervals = this.getIntervals();
            Intervals rhsIntervals;
            rhsIntervals = that.getIntervals();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "intervals", lhsIntervals), LocatorUtils.property(thatLocator, "intervals", rhsIntervals), lhsIntervals, rhsIntervals)) {
                return false;
            }
        }
        {
            String lhsBaselineID;
            lhsBaselineID = this.getBaselineID();
            String rhsBaselineID;
            rhsBaselineID = that.getBaselineID();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "baselineID", lhsBaselineID), LocatorUtils.property(thatLocator, "baselineID", rhsBaselineID), lhsBaselineID, rhsBaselineID)) {
                return false;
            }
        }
        {
            List<String> lhsResourceIDs;
            lhsResourceIDs = (((this.resourceIDs!= null)&&(!this.resourceIDs.isEmpty()))?this.getResourceIDs():null);
            List<String> rhsResourceIDs;
            rhsResourceIDs = (((that.resourceIDs!= null)&&(!that.resourceIDs.isEmpty()))?that.getResourceIDs():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "resourceIDs", lhsResourceIDs), LocatorUtils.property(thatLocator, "resourceIDs", rhsResourceIDs), lhsResourceIDs, rhsResourceIDs)) {
                return false;
            }
        }
        {
            String lhsBaselineName;
            lhsBaselineName = this.getBaselineName();
            String rhsBaselineName;
            rhsBaselineName = that.getBaselineName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "baselineName", lhsBaselineName), LocatorUtils.property(thatLocator, "baselineName", rhsBaselineName), lhsBaselineName, rhsBaselineName)) {
                return false;
            }
        }
        {
            JAXBElement<? extends ItemBaseType> lhsItemBase;
            lhsItemBase = this.getItemBase();
            JAXBElement<? extends ItemBaseType> rhsItemBase;
            rhsItemBase = that.getItemBase();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "itemBase", lhsItemBase), LocatorUtils.property(thatLocator, "itemBase", rhsItemBase), lhsItemBase, rhsItemBase)) {
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
            Dtstart theDtstart;
            theDtstart = this.getDtstart();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dtstart", theDtstart), currentHashCode, theDtstart);
        }
        {
            DurationPropType theDuration;
            theDuration = this.getDuration();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "duration", theDuration), currentHashCode, theDuration);
        }
        {
            Intervals theIntervals;
            theIntervals = this.getIntervals();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "intervals", theIntervals), currentHashCode, theIntervals);
        }
        {
            String theBaselineID;
            theBaselineID = this.getBaselineID();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "baselineID", theBaselineID), currentHashCode, theBaselineID);
        }
        {
            List<String> theResourceIDs;
            theResourceIDs = (((this.resourceIDs!= null)&&(!this.resourceIDs.isEmpty()))?this.getResourceIDs():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "resourceIDs", theResourceIDs), currentHashCode, theResourceIDs);
        }
        {
            String theBaselineName;
            theBaselineName = this.getBaselineName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "baselineName", theBaselineName), currentHashCode, theBaselineName);
        }
        {
            JAXBElement<? extends ItemBaseType> theItemBase;
            theItemBase = this.getItemBase();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "itemBase", theItemBase), currentHashCode, theItemBase);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public EiEventBaseline withDtstart(Dtstart value) {
        setDtstart(value);
        return this;
    }

    public EiEventBaseline withDuration(DurationPropType value) {
        setDuration(value);
        return this;
    }

    public EiEventBaseline withIntervals(Intervals value) {
        setIntervals(value);
        return this;
    }

    public EiEventBaseline withBaselineID(String value) {
        setBaselineID(value);
        return this;
    }

    public EiEventBaseline withResourceIDs(String... values) {
        if (values!= null) {
            for (String value: values) {
                getResourceIDs().add(value);
            }
        }
        return this;
    }

    public EiEventBaseline withResourceIDs(Collection<String> values) {
        if (values!= null) {
            getResourceIDs().addAll(values);
        }
        return this;
    }

    public EiEventBaseline withBaselineName(String value) {
        setBaselineName(value);
        return this;
    }

    public EiEventBaseline withItemBase(JAXBElement<? extends ItemBaseType> value) {
        setItemBase(value);
        return this;
    }

}
