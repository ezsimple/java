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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
 * [extension] Contains attributes related to the configuration of the time service.
 * 
 * <p>Java class for TimeConfiguration complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TimeConfiguration">
 *   &lt;complexContent>
 *     &lt;extension base="{http://naesb.org/espi}IdentifiedObject">
 *       &lt;sequence>
 *         &lt;element name="dstEndRule" type="{http://naesb.org/espi}DstRuleType"/>
 *         &lt;element name="dstOffset" type="{http://naesb.org/espi}TimeType"/>
 *         &lt;element name="dstStartRule" type="{http://naesb.org/espi}DstRuleType"/>
 *         &lt;element name="tzOffset" type="{http://naesb.org/espi}TimeType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeConfiguration", propOrder = {
    "dstEndRule",
    "dstOffset",
    "dstStartRule",
    "tzOffset"
})
@XmlRootElement(name = "LocalTimeParameters")
public class LocalTimeParameters
    extends IdentifiedObject
    implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] dstEndRule;
    protected long dstOffset;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] dstStartRule;
    protected long tzOffset;

    /**
     * Default no-arg constructor
     * 
     */
    public LocalTimeParameters() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public LocalTimeParameters(final List<java.lang.Object> extensions, final BatchItemInfo batchItemInfo, final byte[] dstEndRule, final long dstOffset, final byte[] dstStartRule, final long tzOffset) {
        super(extensions, batchItemInfo);
        this.dstEndRule = dstEndRule;
        this.dstOffset = dstOffset;
        this.dstStartRule = dstStartRule;
        this.tzOffset = tzOffset;
    }

    /**
     * Gets the value of the dstEndRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getDstEndRule() {
        return dstEndRule;
    }

    /**
     * Sets the value of the dstEndRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDstEndRule(byte[] value) {
        this.dstEndRule = ((byte[]) value);
    }

    /**
     * Gets the value of the dstOffset property.
     * 
     */
    public long getDstOffset() {
        return dstOffset;
    }

    /**
     * Sets the value of the dstOffset property.
     * 
     */
    public void setDstOffset(long value) {
        this.dstOffset = value;
    }

    /**
     * Gets the value of the dstStartRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getDstStartRule() {
        return dstStartRule;
    }

    /**
     * Sets the value of the dstStartRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDstStartRule(byte[] value) {
        this.dstStartRule = ((byte[]) value);
    }

    /**
     * Gets the value of the tzOffset property.
     * 
     */
    public long getTzOffset() {
        return tzOffset;
    }

    /**
     * Sets the value of the tzOffset property.
     * 
     */
    public void setTzOffset(long value) {
        this.tzOffset = value;
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
            byte[] theDstEndRule;
            theDstEndRule = this.getDstEndRule();
            strategy.appendField(locator, this, "dstEndRule", buffer, theDstEndRule);
        }
        {
            long theDstOffset;
            theDstOffset = (true?this.getDstOffset(): 0L);
            strategy.appendField(locator, this, "dstOffset", buffer, theDstOffset);
        }
        {
            byte[] theDstStartRule;
            theDstStartRule = this.getDstStartRule();
            strategy.appendField(locator, this, "dstStartRule", buffer, theDstStartRule);
        }
        {
            long theTzOffset;
            theTzOffset = (true?this.getTzOffset(): 0L);
            strategy.appendField(locator, this, "tzOffset", buffer, theTzOffset);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, java.lang.Object object, EqualsStrategy strategy) {
        if (!(object instanceof LocalTimeParameters)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final LocalTimeParameters that = ((LocalTimeParameters) object);
        {
            byte[] lhsDstEndRule;
            lhsDstEndRule = this.getDstEndRule();
            byte[] rhsDstEndRule;
            rhsDstEndRule = that.getDstEndRule();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dstEndRule", lhsDstEndRule), LocatorUtils.property(thatLocator, "dstEndRule", rhsDstEndRule), lhsDstEndRule, rhsDstEndRule)) {
                return false;
            }
        }
        {
            long lhsDstOffset;
            lhsDstOffset = (true?this.getDstOffset(): 0L);
            long rhsDstOffset;
            rhsDstOffset = (true?that.getDstOffset(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dstOffset", lhsDstOffset), LocatorUtils.property(thatLocator, "dstOffset", rhsDstOffset), lhsDstOffset, rhsDstOffset)) {
                return false;
            }
        }
        {
            byte[] lhsDstStartRule;
            lhsDstStartRule = this.getDstStartRule();
            byte[] rhsDstStartRule;
            rhsDstStartRule = that.getDstStartRule();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dstStartRule", lhsDstStartRule), LocatorUtils.property(thatLocator, "dstStartRule", rhsDstStartRule), lhsDstStartRule, rhsDstStartRule)) {
                return false;
            }
        }
        {
            long lhsTzOffset;
            lhsTzOffset = (true?this.getTzOffset(): 0L);
            long rhsTzOffset;
            rhsTzOffset = (true?that.getTzOffset(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "tzOffset", lhsTzOffset), LocatorUtils.property(thatLocator, "tzOffset", rhsTzOffset), lhsTzOffset, rhsTzOffset)) {
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
            byte[] theDstEndRule;
            theDstEndRule = this.getDstEndRule();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dstEndRule", theDstEndRule), currentHashCode, theDstEndRule);
        }
        {
            long theDstOffset;
            theDstOffset = (true?this.getDstOffset(): 0L);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dstOffset", theDstOffset), currentHashCode, theDstOffset);
        }
        {
            byte[] theDstStartRule;
            theDstStartRule = this.getDstStartRule();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dstStartRule", theDstStartRule), currentHashCode, theDstStartRule);
        }
        {
            long theTzOffset;
            theTzOffset = (true?this.getTzOffset(): 0L);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "tzOffset", theTzOffset), currentHashCode, theTzOffset);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public LocalTimeParameters withDstEndRule(byte[] value) {
        setDstEndRule(value);
        return this;
    }

    public LocalTimeParameters withDstOffset(long value) {
        setDstOffset(value);
        return this;
    }

    public LocalTimeParameters withDstStartRule(byte[] value) {
        setDstStartRule(value);
        return this;
    }

    public LocalTimeParameters withTzOffset(long value) {
        setTzOffset(value);
        return this;
    }

    @Override
    public LocalTimeParameters withBatchItemInfo(BatchItemInfo value) {
        setBatchItemInfo(value);
        return this;
    }

    @Override
    public LocalTimeParameters withExtensions(java.lang.Object... values) {
        if (values!= null) {
            for (java.lang.Object value: values) {
                getExtensions().add(value);
            }
        }
        return this;
    }

    @Override
    public LocalTimeParameters withExtensions(Collection<java.lang.Object> values) {
        if (values!= null) {
            getExtensions().addAll(values);
        }
        return this;
    }

}
