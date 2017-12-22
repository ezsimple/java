//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.2-hudson-jaxb-ri-2.2-63- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.29 at 05:09:52 \uc624\ud6c4 KST 
//


package net.ion.open.oadr2.model.v20b.greenbutton;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
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
 * This is a root class to provide common naming attributes for all classes needing naming attributes
 * 
 * <p>Java class for IdentifiedObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdentifiedObject">
 *   &lt;complexContent>
 *     &lt;extension base="{http://naesb.org/espi}Object">
 *       &lt;sequence>
 *         &lt;element name="batchItemInfo" type="{http://naesb.org/espi}BatchItemInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentifiedObject", propOrder = {
    "batchItemInfo"
})
@XmlSeeAlso({
    ReadingType.class,
    IntervalBlock.class,
    ElectricPowerQualitySummary.class,
    LocalTimeParameters.class,
    ElectricPowerUsageSummary.class,
    UsagePoint.class,
    MeterReading.class
})
public class IdentifiedObject
    extends Object
    implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 1L;
    protected BatchItemInfo batchItemInfo;

    /**
     * Default no-arg constructor
     * 
     */
    public IdentifiedObject() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public IdentifiedObject(final List<java.lang.Object> extensions, final BatchItemInfo batchItemInfo) {
        super(extensions);
        this.batchItemInfo = batchItemInfo;
    }

    /**
     * Gets the value of the batchItemInfo property.
     * 
     * @return
     *     possible object is
     *     {@link BatchItemInfo }
     *     
     */
    public BatchItemInfo getBatchItemInfo() {
        return batchItemInfo;
    }

    /**
     * Sets the value of the batchItemInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BatchItemInfo }
     *     
     */
    public void setBatchItemInfo(BatchItemInfo value) {
        this.batchItemInfo = value;
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
            BatchItemInfo theBatchItemInfo;
            theBatchItemInfo = this.getBatchItemInfo();
            strategy.appendField(locator, this, "batchItemInfo", buffer, theBatchItemInfo);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, java.lang.Object object, EqualsStrategy strategy) {
        if (!(object instanceof IdentifiedObject)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final IdentifiedObject that = ((IdentifiedObject) object);
        {
            BatchItemInfo lhsBatchItemInfo;
            lhsBatchItemInfo = this.getBatchItemInfo();
            BatchItemInfo rhsBatchItemInfo;
            rhsBatchItemInfo = that.getBatchItemInfo();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "batchItemInfo", lhsBatchItemInfo), LocatorUtils.property(thatLocator, "batchItemInfo", rhsBatchItemInfo), lhsBatchItemInfo, rhsBatchItemInfo)) {
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
            BatchItemInfo theBatchItemInfo;
            theBatchItemInfo = this.getBatchItemInfo();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "batchItemInfo", theBatchItemInfo), currentHashCode, theBatchItemInfo);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public IdentifiedObject withBatchItemInfo(BatchItemInfo value) {
        setBatchItemInfo(value);
        return this;
    }

    @Override
    public IdentifiedObject withExtensions(java.lang.Object... values) {
        if (values!= null) {
            for (java.lang.Object value: values) {
                getExtensions().add(value);
            }
        }
        return this;
    }

    @Override
    public IdentifiedObject withExtensions(Collection<java.lang.Object> values) {
        if (values!= null) {
            getExtensions().addAll(values);
        }
        return this;
    }

}