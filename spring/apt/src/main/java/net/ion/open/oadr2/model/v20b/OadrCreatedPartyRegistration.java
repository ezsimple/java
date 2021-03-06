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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.ion.open.oadr2.model.v20b.ei.EiResponse;
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
 * <p>Java class for oadrCreatedPartyRegistrationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="oadrCreatedPartyRegistrationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://docs.oasis-open.org/ns/energyinterop/201110}eiResponse"/>
 *         &lt;element ref="{http://docs.oasis-open.org/ns/energyinterop/201110}registrationID" minOccurs="0"/>
 *         &lt;element ref="{http://docs.oasis-open.org/ns/energyinterop/201110}venID" minOccurs="0"/>
 *         &lt;element ref="{http://docs.oasis-open.org/ns/energyinterop/201110}vtnID"/>
 *         &lt;element ref="{http://openadr.org/oadr-2.0b/2012/07}oadrProfiles"/>
 *         &lt;element ref="{http://openadr.org/oadr-2.0b/2012/07}oadrRequestedOadrPollFreq" minOccurs="0"/>
 *         &lt;element ref="{http://openadr.org/oadr-2.0b/2012/07}oadrServiceSpecificInfo" minOccurs="0"/>
 *         &lt;element name="oadrExtensions" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="oadrExtension" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="oadrExtensionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element ref="{http://openadr.org/oadr-2.0b/2012/07}oadrInfo" maxOccurs="unbounded" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://docs.oasis-open.org/ns/energyinterop/201110}schemaVersion"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "oadrCreatedPartyRegistrationType", propOrder = {
    "eiResponse",
    "registrationID",
    "venID",
    "vtnID",
    "oadrProfiles",
    "oadrRequestedOadrPollFreq",
    "oadrServiceSpecificInfo",
    "oadrExtensions"
})
@XmlRootElement(name = "oadrCreatedPartyRegistration")
public class OadrCreatedPartyRegistration implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(namespace = "http://docs.oasis-open.org/ns/energyinterop/201110", required = true)
    protected EiResponse eiResponse;
    @XmlElement(namespace = "http://docs.oasis-open.org/ns/energyinterop/201110")
    protected String registrationID;
    @XmlElement(namespace = "http://docs.oasis-open.org/ns/energyinterop/201110")
    protected String venID;
    @XmlElement(namespace = "http://docs.oasis-open.org/ns/energyinterop/201110", required = true)
    protected String vtnID;
    @XmlElement(required = true)
    protected OadrProfiles oadrProfiles;
    protected DurationPropType oadrRequestedOadrPollFreq;
    protected OadrServiceSpecificInfo oadrServiceSpecificInfo;
    protected OadrExtensions oadrExtensions;
    @XmlAttribute(name = "schemaVersion", namespace = "http://docs.oasis-open.org/ns/energyinterop/201110")
    protected String schemaVersion;

    /**
     * Default no-arg constructor
     * 
     */
    public OadrCreatedPartyRegistration() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public OadrCreatedPartyRegistration(final EiResponse eiResponse, final String registrationID, final String venID, final String vtnID, final OadrProfiles oadrProfiles, final DurationPropType oadrRequestedOadrPollFreq, final OadrServiceSpecificInfo oadrServiceSpecificInfo, final OadrExtensions oadrExtensions, final String schemaVersion) {
        this.eiResponse = eiResponse;
        this.registrationID = registrationID;
        this.venID = venID;
        this.vtnID = vtnID;
        this.oadrProfiles = oadrProfiles;
        this.oadrRequestedOadrPollFreq = oadrRequestedOadrPollFreq;
        this.oadrServiceSpecificInfo = oadrServiceSpecificInfo;
        this.oadrExtensions = oadrExtensions;
        this.schemaVersion = schemaVersion;
    }

    /**
     * Gets the value of the eiResponse property.
     * 
     * @return
     *     possible object is
     *     {@link EiResponse }
     *     
     */
    public EiResponse getEiResponse() {
        return eiResponse;
    }

    /**
     * Sets the value of the eiResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link EiResponse }
     *     
     */
    public void setEiResponse(EiResponse value) {
        this.eiResponse = value;
    }

    /**
     * Gets the value of the registrationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationID() {
        return registrationID;
    }

    /**
     * Sets the value of the registrationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationID(String value) {
        this.registrationID = value;
    }

    /**
     * venID not included in query unless already registered
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVenID() {
        return venID;
    }

    /**
     * Sets the value of the venID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVenID(String value) {
        this.venID = value;
    }

    /**
     * Gets the value of the vtnID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVtnID() {
        return vtnID;
    }

    /**
     * Sets the value of the vtnID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVtnID(String value) {
        this.vtnID = value;
    }

    /**
     * VTN response to query registration returns all supported. This element is not required for a registration  response
     * 
     * @return
     *     possible object is
     *     {@link OadrProfiles }
     *     
     */
    public OadrProfiles getOadrProfiles() {
        return oadrProfiles;
    }

    /**
     * Sets the value of the oadrProfiles property.
     * 
     * @param value
     *     allowed object is
     *     {@link OadrProfiles }
     *     
     */
    public void setOadrProfiles(OadrProfiles value) {
        this.oadrProfiles = value;
    }

    /**
     * HTTP Pull Only - The VEN shall send an oadrPoll payload to the VTN at most once for each duration specified by this element
     * 
     * @return
     *     possible object is
     *     {@link DurationPropType }
     *     
     */
    public DurationPropType getOadrRequestedOadrPollFreq() {
        return oadrRequestedOadrPollFreq;
    }

    /**
     * Sets the value of the oadrRequestedOadrPollFreq property.
     * 
     * @param value
     *     allowed object is
     *     {@link DurationPropType }
     *     
     */
    public void setOadrRequestedOadrPollFreq(DurationPropType value) {
        this.oadrRequestedOadrPollFreq = value;
    }

    /**
     * Gets the value of the oadrServiceSpecificInfo property.
     * 
     * @return
     *     possible object is
     *     {@link OadrServiceSpecificInfo }
     *     
     */
    public OadrServiceSpecificInfo getOadrServiceSpecificInfo() {
        return oadrServiceSpecificInfo;
    }

    /**
     * Sets the value of the oadrServiceSpecificInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link OadrServiceSpecificInfo }
     *     
     */
    public void setOadrServiceSpecificInfo(OadrServiceSpecificInfo value) {
        this.oadrServiceSpecificInfo = value;
    }

    /**
     * Gets the value of the oadrExtensions property.
     * 
     * @return
     *     possible object is
     *     {@link OadrExtensions }
     *     
     */
    public OadrExtensions getOadrExtensions() {
        return oadrExtensions;
    }

    /**
     * Sets the value of the oadrExtensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link OadrExtensions }
     *     
     */
    public void setOadrExtensions(OadrExtensions value) {
        this.oadrExtensions = value;
    }

    /**
     * Gets the value of the schemaVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchemaVersion() {
        return schemaVersion;
    }

    /**
     * Sets the value of the schemaVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchemaVersion(String value) {
        this.schemaVersion = value;
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
            EiResponse theEiResponse;
            theEiResponse = this.getEiResponse();
            strategy.appendField(locator, this, "eiResponse", buffer, theEiResponse);
        }
        {
            String theRegistrationID;
            theRegistrationID = this.getRegistrationID();
            strategy.appendField(locator, this, "registrationID", buffer, theRegistrationID);
        }
        {
            String theVenID;
            theVenID = this.getVenID();
            strategy.appendField(locator, this, "venID", buffer, theVenID);
        }
        {
            String theVtnID;
            theVtnID = this.getVtnID();
            strategy.appendField(locator, this, "vtnID", buffer, theVtnID);
        }
        {
            OadrProfiles theOadrProfiles;
            theOadrProfiles = this.getOadrProfiles();
            strategy.appendField(locator, this, "oadrProfiles", buffer, theOadrProfiles);
        }
        {
            DurationPropType theOadrRequestedOadrPollFreq;
            theOadrRequestedOadrPollFreq = this.getOadrRequestedOadrPollFreq();
            strategy.appendField(locator, this, "oadrRequestedOadrPollFreq", buffer, theOadrRequestedOadrPollFreq);
        }
        {
            OadrServiceSpecificInfo theOadrServiceSpecificInfo;
            theOadrServiceSpecificInfo = this.getOadrServiceSpecificInfo();
            strategy.appendField(locator, this, "oadrServiceSpecificInfo", buffer, theOadrServiceSpecificInfo);
        }
        {
            OadrExtensions theOadrExtensions;
            theOadrExtensions = this.getOadrExtensions();
            strategy.appendField(locator, this, "oadrExtensions", buffer, theOadrExtensions);
        }
        {
            String theSchemaVersion;
            theSchemaVersion = this.getSchemaVersion();
            strategy.appendField(locator, this, "schemaVersion", buffer, theSchemaVersion);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OadrCreatedPartyRegistration)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final OadrCreatedPartyRegistration that = ((OadrCreatedPartyRegistration) object);
        {
            EiResponse lhsEiResponse;
            lhsEiResponse = this.getEiResponse();
            EiResponse rhsEiResponse;
            rhsEiResponse = that.getEiResponse();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eiResponse", lhsEiResponse), LocatorUtils.property(thatLocator, "eiResponse", rhsEiResponse), lhsEiResponse, rhsEiResponse)) {
                return false;
            }
        }
        {
            String lhsRegistrationID;
            lhsRegistrationID = this.getRegistrationID();
            String rhsRegistrationID;
            rhsRegistrationID = that.getRegistrationID();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "registrationID", lhsRegistrationID), LocatorUtils.property(thatLocator, "registrationID", rhsRegistrationID), lhsRegistrationID, rhsRegistrationID)) {
                return false;
            }
        }
        {
            String lhsVenID;
            lhsVenID = this.getVenID();
            String rhsVenID;
            rhsVenID = that.getVenID();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "venID", lhsVenID), LocatorUtils.property(thatLocator, "venID", rhsVenID), lhsVenID, rhsVenID)) {
                return false;
            }
        }
        {
            String lhsVtnID;
            lhsVtnID = this.getVtnID();
            String rhsVtnID;
            rhsVtnID = that.getVtnID();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "vtnID", lhsVtnID), LocatorUtils.property(thatLocator, "vtnID", rhsVtnID), lhsVtnID, rhsVtnID)) {
                return false;
            }
        }
        {
            OadrProfiles lhsOadrProfiles;
            lhsOadrProfiles = this.getOadrProfiles();
            OadrProfiles rhsOadrProfiles;
            rhsOadrProfiles = that.getOadrProfiles();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "oadrProfiles", lhsOadrProfiles), LocatorUtils.property(thatLocator, "oadrProfiles", rhsOadrProfiles), lhsOadrProfiles, rhsOadrProfiles)) {
                return false;
            }
        }
        {
            DurationPropType lhsOadrRequestedOadrPollFreq;
            lhsOadrRequestedOadrPollFreq = this.getOadrRequestedOadrPollFreq();
            DurationPropType rhsOadrRequestedOadrPollFreq;
            rhsOadrRequestedOadrPollFreq = that.getOadrRequestedOadrPollFreq();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "oadrRequestedOadrPollFreq", lhsOadrRequestedOadrPollFreq), LocatorUtils.property(thatLocator, "oadrRequestedOadrPollFreq", rhsOadrRequestedOadrPollFreq), lhsOadrRequestedOadrPollFreq, rhsOadrRequestedOadrPollFreq)) {
                return false;
            }
        }
        {
            OadrServiceSpecificInfo lhsOadrServiceSpecificInfo;
            lhsOadrServiceSpecificInfo = this.getOadrServiceSpecificInfo();
            OadrServiceSpecificInfo rhsOadrServiceSpecificInfo;
            rhsOadrServiceSpecificInfo = that.getOadrServiceSpecificInfo();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "oadrServiceSpecificInfo", lhsOadrServiceSpecificInfo), LocatorUtils.property(thatLocator, "oadrServiceSpecificInfo", rhsOadrServiceSpecificInfo), lhsOadrServiceSpecificInfo, rhsOadrServiceSpecificInfo)) {
                return false;
            }
        }
        {
            OadrExtensions lhsOadrExtensions;
            lhsOadrExtensions = this.getOadrExtensions();
            OadrExtensions rhsOadrExtensions;
            rhsOadrExtensions = that.getOadrExtensions();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "oadrExtensions", lhsOadrExtensions), LocatorUtils.property(thatLocator, "oadrExtensions", rhsOadrExtensions), lhsOadrExtensions, rhsOadrExtensions)) {
                return false;
            }
        }
        {
            String lhsSchemaVersion;
            lhsSchemaVersion = this.getSchemaVersion();
            String rhsSchemaVersion;
            rhsSchemaVersion = that.getSchemaVersion();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "schemaVersion", lhsSchemaVersion), LocatorUtils.property(thatLocator, "schemaVersion", rhsSchemaVersion), lhsSchemaVersion, rhsSchemaVersion)) {
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
            EiResponse theEiResponse;
            theEiResponse = this.getEiResponse();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "eiResponse", theEiResponse), currentHashCode, theEiResponse);
        }
        {
            String theRegistrationID;
            theRegistrationID = this.getRegistrationID();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "registrationID", theRegistrationID), currentHashCode, theRegistrationID);
        }
        {
            String theVenID;
            theVenID = this.getVenID();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "venID", theVenID), currentHashCode, theVenID);
        }
        {
            String theVtnID;
            theVtnID = this.getVtnID();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "vtnID", theVtnID), currentHashCode, theVtnID);
        }
        {
            OadrProfiles theOadrProfiles;
            theOadrProfiles = this.getOadrProfiles();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "oadrProfiles", theOadrProfiles), currentHashCode, theOadrProfiles);
        }
        {
            DurationPropType theOadrRequestedOadrPollFreq;
            theOadrRequestedOadrPollFreq = this.getOadrRequestedOadrPollFreq();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "oadrRequestedOadrPollFreq", theOadrRequestedOadrPollFreq), currentHashCode, theOadrRequestedOadrPollFreq);
        }
        {
            OadrServiceSpecificInfo theOadrServiceSpecificInfo;
            theOadrServiceSpecificInfo = this.getOadrServiceSpecificInfo();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "oadrServiceSpecificInfo", theOadrServiceSpecificInfo), currentHashCode, theOadrServiceSpecificInfo);
        }
        {
            OadrExtensions theOadrExtensions;
            theOadrExtensions = this.getOadrExtensions();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "oadrExtensions", theOadrExtensions), currentHashCode, theOadrExtensions);
        }
        {
            String theSchemaVersion;
            theSchemaVersion = this.getSchemaVersion();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "schemaVersion", theSchemaVersion), currentHashCode, theSchemaVersion);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public OadrCreatedPartyRegistration withEiResponse(EiResponse value) {
        setEiResponse(value);
        return this;
    }

    public OadrCreatedPartyRegistration withRegistrationID(String value) {
        setRegistrationID(value);
        return this;
    }

    public OadrCreatedPartyRegistration withVenID(String value) {
        setVenID(value);
        return this;
    }

    public OadrCreatedPartyRegistration withVtnID(String value) {
        setVtnID(value);
        return this;
    }

    public OadrCreatedPartyRegistration withOadrProfiles(OadrProfiles value) {
        setOadrProfiles(value);
        return this;
    }

    public OadrCreatedPartyRegistration withOadrRequestedOadrPollFreq(DurationPropType value) {
        setOadrRequestedOadrPollFreq(value);
        return this;
    }

    public OadrCreatedPartyRegistration withOadrServiceSpecificInfo(OadrServiceSpecificInfo value) {
        setOadrServiceSpecificInfo(value);
        return this;
    }

    public OadrCreatedPartyRegistration withOadrExtensions(OadrExtensions value) {
        setOadrExtensions(value);
        return this;
    }

    public OadrCreatedPartyRegistration withSchemaVersion(String value) {
        setSchemaVersion(value);
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
     *         &lt;element name="oadrExtension" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="oadrExtensionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element ref="{http://openadr.org/oadr-2.0b/2012/07}oadrInfo" maxOccurs="unbounded" minOccurs="0"/>
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
        "oadrExtensions"
    })
    public static class OadrExtensions implements Serializable, Equals, HashCode, ToString
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "oadrExtension")
        protected List<OadrExtension> oadrExtensions;

        /**
         * Default no-arg constructor
         * 
         */
        public OadrExtensions() {
            super();
        }

        /**
         * Fully-initialising value constructor
         * 
         */
        public OadrExtensions(final List<OadrExtension> oadrExtensions) {
            this.oadrExtensions = oadrExtensions;
        }

        /**
         * Gets the value of the oadrExtensions property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the oadrExtensions property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOadrExtensions().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link OadrExtension }
         * 
         * 
         */
        public List<OadrExtension> getOadrExtensions() {
            if (oadrExtensions == null) {
                oadrExtensions = new ArrayList<OadrExtension>();
            }
            return this.oadrExtensions;
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
                List<OadrExtension> theOadrExtensions;
                theOadrExtensions = (((this.oadrExtensions!= null)&&(!this.oadrExtensions.isEmpty()))?this.getOadrExtensions():null);
                strategy.appendField(locator, this, "oadrExtensions", buffer, theOadrExtensions);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof OadrExtensions)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final OadrExtensions that = ((OadrExtensions) object);
            {
                List<OadrExtension> lhsOadrExtensions;
                lhsOadrExtensions = (((this.oadrExtensions!= null)&&(!this.oadrExtensions.isEmpty()))?this.getOadrExtensions():null);
                List<OadrExtension> rhsOadrExtensions;
                rhsOadrExtensions = (((that.oadrExtensions!= null)&&(!that.oadrExtensions.isEmpty()))?that.getOadrExtensions():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "oadrExtensions", lhsOadrExtensions), LocatorUtils.property(thatLocator, "oadrExtensions", rhsOadrExtensions), lhsOadrExtensions, rhsOadrExtensions)) {
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
                List<OadrExtension> theOadrExtensions;
                theOadrExtensions = (((this.oadrExtensions!= null)&&(!this.oadrExtensions.isEmpty()))?this.getOadrExtensions():null);
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "oadrExtensions", theOadrExtensions), currentHashCode, theOadrExtensions);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public OadrExtensions withOadrExtensions(OadrExtension... values) {
            if (values!= null) {
                for (OadrExtension value: values) {
                    getOadrExtensions().add(value);
                }
            }
            return this;
        }

        public OadrExtensions withOadrExtensions(Collection<OadrExtension> values) {
            if (values!= null) {
                getOadrExtensions().addAll(values);
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
         *         &lt;element name="oadrExtensionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element ref="{http://openadr.org/oadr-2.0b/2012/07}oadrInfo" maxOccurs="unbounded" minOccurs="0"/>
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
            "oadrExtensionName",
            "oadrInfos"
        })
        public static class OadrExtension implements Serializable, Equals, HashCode, ToString
        {

            private final static long serialVersionUID = 1L;
            @XmlElement(required = true)
            protected String oadrExtensionName;
            @XmlElement(name = "oadrInfo")
            protected List<OadrInfo> oadrInfos;

            /**
             * Default no-arg constructor
             * 
             */
            public OadrExtension() {
                super();
            }

            /**
             * Fully-initialising value constructor
             * 
             */
            public OadrExtension(final String oadrExtensionName, final List<OadrInfo> oadrInfos) {
                this.oadrExtensionName = oadrExtensionName;
                this.oadrInfos = oadrInfos;
            }

            /**
             * Gets the value of the oadrExtensionName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOadrExtensionName() {
                return oadrExtensionName;
            }

            /**
             * Sets the value of the oadrExtensionName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOadrExtensionName(String value) {
                this.oadrExtensionName = value;
            }

            /**
             * Gets the value of the oadrInfos property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the oadrInfos property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getOadrInfos().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link OadrInfo }
             * 
             * 
             */
            public List<OadrInfo> getOadrInfos() {
                if (oadrInfos == null) {
                    oadrInfos = new ArrayList<OadrInfo>();
                }
                return this.oadrInfos;
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
                    String theOadrExtensionName;
                    theOadrExtensionName = this.getOadrExtensionName();
                    strategy.appendField(locator, this, "oadrExtensionName", buffer, theOadrExtensionName);
                }
                {
                    List<OadrInfo> theOadrInfos;
                    theOadrInfos = (((this.oadrInfos!= null)&&(!this.oadrInfos.isEmpty()))?this.getOadrInfos():null);
                    strategy.appendField(locator, this, "oadrInfos", buffer, theOadrInfos);
                }
                return buffer;
            }

            public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                if (!(object instanceof OadrExtension)) {
                    return false;
                }
                if (this == object) {
                    return true;
                }
                final OadrExtension that = ((OadrExtension) object);
                {
                    String lhsOadrExtensionName;
                    lhsOadrExtensionName = this.getOadrExtensionName();
                    String rhsOadrExtensionName;
                    rhsOadrExtensionName = that.getOadrExtensionName();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "oadrExtensionName", lhsOadrExtensionName), LocatorUtils.property(thatLocator, "oadrExtensionName", rhsOadrExtensionName), lhsOadrExtensionName, rhsOadrExtensionName)) {
                        return false;
                    }
                }
                {
                    List<OadrInfo> lhsOadrInfos;
                    lhsOadrInfos = (((this.oadrInfos!= null)&&(!this.oadrInfos.isEmpty()))?this.getOadrInfos():null);
                    List<OadrInfo> rhsOadrInfos;
                    rhsOadrInfos = (((that.oadrInfos!= null)&&(!that.oadrInfos.isEmpty()))?that.getOadrInfos():null);
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "oadrInfos", lhsOadrInfos), LocatorUtils.property(thatLocator, "oadrInfos", rhsOadrInfos), lhsOadrInfos, rhsOadrInfos)) {
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
                    String theOadrExtensionName;
                    theOadrExtensionName = this.getOadrExtensionName();
                    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "oadrExtensionName", theOadrExtensionName), currentHashCode, theOadrExtensionName);
                }
                {
                    List<OadrInfo> theOadrInfos;
                    theOadrInfos = (((this.oadrInfos!= null)&&(!this.oadrInfos.isEmpty()))?this.getOadrInfos():null);
                    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "oadrInfos", theOadrInfos), currentHashCode, theOadrInfos);
                }
                return currentHashCode;
            }

            public int hashCode() {
                final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
                return this.hashCode(null, strategy);
            }

            public OadrExtension withOadrExtensionName(String value) {
                setOadrExtensionName(value);
                return this;
            }

            public OadrExtension withOadrInfos(OadrInfo... values) {
                if (values!= null) {
                    for (OadrInfo value: values) {
                        getOadrInfos().add(value);
                    }
                }
                return this;
            }

            public OadrExtension withOadrInfos(Collection<OadrInfo> values) {
                if (values!= null) {
                    getOadrInfos().addAll(values);
                }
                return this;
            }

        }

    }

}
