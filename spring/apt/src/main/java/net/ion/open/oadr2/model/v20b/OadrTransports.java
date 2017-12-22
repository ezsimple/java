//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.2-hudson-jaxb-ri-2.2-63- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.29 at 05:09:52 \uc624\ud6c4 KST 
//


package net.ion.open.oadr2.model.v20b;

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
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="oadrTransport" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://openadr.org/oadr-2.0b/2012/07}oadrTransportName"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "oadrTransports"
})
@XmlRootElement(name = "oadrTransports")
public class OadrTransports implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "oadrTransport", required = true)
    protected List<OadrTransport> oadrTransports;

    /**
     * Default no-arg constructor
     * 
     */
    public OadrTransports() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public OadrTransports(final List<OadrTransport> oadrTransports) {
        this.oadrTransports = oadrTransports;
    }

    /**
     * Gets the value of the oadrTransports property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the oadrTransports property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOadrTransports().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OadrTransport }
     * 
     * 
     */
    public List<OadrTransport> getOadrTransports() {
        if (oadrTransports == null) {
            oadrTransports = new ArrayList<OadrTransport>();
        }
        return this.oadrTransports;
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
            List<OadrTransport> theOadrTransports;
            theOadrTransports = (((this.oadrTransports!= null)&&(!this.oadrTransports.isEmpty()))?this.getOadrTransports():null);
            strategy.appendField(locator, this, "oadrTransports", buffer, theOadrTransports);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OadrTransports)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final OadrTransports that = ((OadrTransports) object);
        {
            List<OadrTransport> lhsOadrTransports;
            lhsOadrTransports = (((this.oadrTransports!= null)&&(!this.oadrTransports.isEmpty()))?this.getOadrTransports():null);
            List<OadrTransport> rhsOadrTransports;
            rhsOadrTransports = (((that.oadrTransports!= null)&&(!that.oadrTransports.isEmpty()))?that.getOadrTransports():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "oadrTransports", lhsOadrTransports), LocatorUtils.property(thatLocator, "oadrTransports", rhsOadrTransports), lhsOadrTransports, rhsOadrTransports)) {
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
            List<OadrTransport> theOadrTransports;
            theOadrTransports = (((this.oadrTransports!= null)&&(!this.oadrTransports.isEmpty()))?this.getOadrTransports():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "oadrTransports", theOadrTransports), currentHashCode, theOadrTransports);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public OadrTransports withOadrTransports(OadrTransport... values) {
        if (values!= null) {
            for (OadrTransport value: values) {
                getOadrTransports().add(value);
            }
        }
        return this;
    }

    public OadrTransports withOadrTransports(Collection<OadrTransport> values) {
        if (values!= null) {
            getOadrTransports().addAll(values);
        }
        return this;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element ref="{http://openadr.org/oadr-2.0b/2012/07}oadrTransportName"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "oadrTransportName"
    })
    public static class OadrTransport implements Serializable, Equals, HashCode, ToString
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(required = true)
        protected OadrTransportType oadrTransportName;

        /**
         * Default no-arg constructor
         * 
         */
        public OadrTransport() {
            super();
        }

        /**
         * Fully-initialising value constructor
         * 
         */
        public OadrTransport(final OadrTransportType oadrTransportName) {
            this.oadrTransportName = oadrTransportName;
        }

        /**
         * Gets the value of the oadrTransportName property.
         * 
         * @return
         *     possible object is
         *     {@link OadrTransportType }
         *     
         */
        public OadrTransportType getOadrTransportName() {
            return oadrTransportName;
        }

        /**
         * Sets the value of the oadrTransportName property.
         * 
         * @param value
         *     allowed object is
         *     {@link OadrTransportType }
         *     
         */
        public void setOadrTransportName(OadrTransportType value) {
            this.oadrTransportName = value;
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
                OadrTransportType theOadrTransportName;
                theOadrTransportName = this.getOadrTransportName();
                strategy.appendField(locator, this, "oadrTransportName", buffer, theOadrTransportName);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof OadrTransport)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final OadrTransport that = ((OadrTransport) object);
            {
                OadrTransportType lhsOadrTransportName;
                lhsOadrTransportName = this.getOadrTransportName();
                OadrTransportType rhsOadrTransportName;
                rhsOadrTransportName = that.getOadrTransportName();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "oadrTransportName", lhsOadrTransportName), LocatorUtils.property(thatLocator, "oadrTransportName", rhsOadrTransportName), lhsOadrTransportName, rhsOadrTransportName)) {
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
                OadrTransportType theOadrTransportName;
                theOadrTransportName = this.getOadrTransportName();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "oadrTransportName", theOadrTransportName), currentHashCode, theOadrTransportName);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public OadrTransport withOadrTransportName(OadrTransportType value) {
            setOadrTransportName(value);
            return this;
        }

    }

}