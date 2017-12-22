//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.2-hudson-jaxb-ri-2.2-63- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.29 at 05:09:52 \uc624\ud6c4 KST 
//


package net.ion.open.oadr2.model.v20b.xmldsig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
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
import org.w3c.dom.Element;


/**
 * <p>Java class for X509DataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="X509DataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;choice>
 *           &lt;element name="X509IssuerSerial" type="{http://www.w3.org/2000/09/xmldsig#}X509IssuerSerialType"/>
 *           &lt;element name="X509SKI" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *           &lt;element name="X509SubjectName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="X509Certificate" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *           &lt;element name="X509CRL" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *           &lt;any processContents='lax' namespace='##other'/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X509DataType", propOrder = {
    "x509IssuerSerialsAndX509SKISAndX509SubjectNames"
})
@XmlRootElement(name = "X509Data")
public class X509Data implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElementRefs({
        @XmlElementRef(name = "X509SKI", namespace = "http://www.w3.org/2000/09/xmldsig#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "X509IssuerSerial", namespace = "http://www.w3.org/2000/09/xmldsig#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "X509Certificate", namespace = "http://www.w3.org/2000/09/xmldsig#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "X509CRL", namespace = "http://www.w3.org/2000/09/xmldsig#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "X509SubjectName", namespace = "http://www.w3.org/2000/09/xmldsig#", type = JAXBElement.class, required = false)
    })
    @XmlAnyElement
    protected List<java.lang.Object> x509IssuerSerialsAndX509SKISAndX509SubjectNames;

    /**
     * Default no-arg constructor
     * 
     */
    public X509Data() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public X509Data(final List<java.lang.Object> x509IssuerSerialsAndX509SKISAndX509SubjectNames) {
        this.x509IssuerSerialsAndX509SKISAndX509SubjectNames = x509IssuerSerialsAndX509SKISAndX509SubjectNames;
    }

    /**
     * Gets the value of the x509IssuerSerialsAndX509SKISAndX509SubjectNames property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the x509IssuerSerialsAndX509SKISAndX509SubjectNames property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getX509IssuerSerialsAndX509SKISAndX509SubjectNames().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     * {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     * {@link JAXBElement }{@code <}{@link X509IssuerSerialType }{@code >}
     * {@link Element }
     * {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<java.lang.Object> getX509IssuerSerialsAndX509SKISAndX509SubjectNames() {
        if (x509IssuerSerialsAndX509SKISAndX509SubjectNames == null) {
            x509IssuerSerialsAndX509SKISAndX509SubjectNames = new ArrayList<java.lang.Object>();
        }
        return this.x509IssuerSerialsAndX509SKISAndX509SubjectNames;
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
            List<java.lang.Object> theX509IssuerSerialsAndX509SKISAndX509SubjectNames;
            theX509IssuerSerialsAndX509SKISAndX509SubjectNames = (((this.x509IssuerSerialsAndX509SKISAndX509SubjectNames!= null)&&(!this.x509IssuerSerialsAndX509SKISAndX509SubjectNames.isEmpty()))?this.getX509IssuerSerialsAndX509SKISAndX509SubjectNames():null);
            strategy.appendField(locator, this, "x509IssuerSerialsAndX509SKISAndX509SubjectNames", buffer, theX509IssuerSerialsAndX509SKISAndX509SubjectNames);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, java.lang.Object object, EqualsStrategy strategy) {
        if (!(object instanceof X509Data)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final X509Data that = ((X509Data) object);
        {
            List<java.lang.Object> lhsX509IssuerSerialsAndX509SKISAndX509SubjectNames;
            lhsX509IssuerSerialsAndX509SKISAndX509SubjectNames = (((this.x509IssuerSerialsAndX509SKISAndX509SubjectNames!= null)&&(!this.x509IssuerSerialsAndX509SKISAndX509SubjectNames.isEmpty()))?this.getX509IssuerSerialsAndX509SKISAndX509SubjectNames():null);
            List<java.lang.Object> rhsX509IssuerSerialsAndX509SKISAndX509SubjectNames;
            rhsX509IssuerSerialsAndX509SKISAndX509SubjectNames = (((that.x509IssuerSerialsAndX509SKISAndX509SubjectNames!= null)&&(!that.x509IssuerSerialsAndX509SKISAndX509SubjectNames.isEmpty()))?that.getX509IssuerSerialsAndX509SKISAndX509SubjectNames():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "x509IssuerSerialsAndX509SKISAndX509SubjectNames", lhsX509IssuerSerialsAndX509SKISAndX509SubjectNames), LocatorUtils.property(thatLocator, "x509IssuerSerialsAndX509SKISAndX509SubjectNames", rhsX509IssuerSerialsAndX509SKISAndX509SubjectNames), lhsX509IssuerSerialsAndX509SKISAndX509SubjectNames, rhsX509IssuerSerialsAndX509SKISAndX509SubjectNames)) {
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
            List<java.lang.Object> theX509IssuerSerialsAndX509SKISAndX509SubjectNames;
            theX509IssuerSerialsAndX509SKISAndX509SubjectNames = (((this.x509IssuerSerialsAndX509SKISAndX509SubjectNames!= null)&&(!this.x509IssuerSerialsAndX509SKISAndX509SubjectNames.isEmpty()))?this.getX509IssuerSerialsAndX509SKISAndX509SubjectNames():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "x509IssuerSerialsAndX509SKISAndX509SubjectNames", theX509IssuerSerialsAndX509SKISAndX509SubjectNames), currentHashCode, theX509IssuerSerialsAndX509SKISAndX509SubjectNames);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public X509Data withX509IssuerSerialsAndX509SKISAndX509SubjectNames(java.lang.Object... values) {
        if (values!= null) {
            for (java.lang.Object value: values) {
                getX509IssuerSerialsAndX509SKISAndX509SubjectNames().add(value);
            }
        }
        return this;
    }

    public X509Data withX509IssuerSerialsAndX509SKISAndX509SubjectNames(Collection<java.lang.Object> values) {
        if (values!= null) {
            getX509IssuerSerialsAndX509SKISAndX509SubjectNames().addAll(values);
        }
        return this;
    }

}