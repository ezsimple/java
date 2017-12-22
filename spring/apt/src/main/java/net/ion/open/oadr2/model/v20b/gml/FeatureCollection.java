//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.2-hudson-jaxb-ri-2.2-63- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.29 at 05:09:52 \uc624\ud6c4 KST 
//


package net.ion.open.oadr2.model.v20b.gml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
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
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="location">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Polygon">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="exterior">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="LinearRing">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element ref="{http://www.opengis.net/gml/3.2}posList"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute ref="{http://www.opengis.net/gml/3.2}id"/>
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
 *       &lt;attribute ref="{http://www.opengis.net/gml/3.2}id"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "location"
})
@XmlRootElement(name = "FeatureCollection")
public class FeatureCollection implements Serializable, Equals, HashCode, ToString
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected Location location;
    @XmlAttribute(name = "id", namespace = "http://www.opengis.net/gml/3.2")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Default no-arg constructor
     * 
     */
    public FeatureCollection() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public FeatureCollection(final Location location, final String id) {
        this.location = location;
        this.id = id;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setLocation(Location value) {
        this.location = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
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
            Location theLocation;
            theLocation = this.getLocation();
            strategy.appendField(locator, this, "location", buffer, theLocation);
        }
        {
            String theId;
            theId = this.getId();
            strategy.appendField(locator, this, "id", buffer, theId);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof FeatureCollection)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final FeatureCollection that = ((FeatureCollection) object);
        {
            Location lhsLocation;
            lhsLocation = this.getLocation();
            Location rhsLocation;
            rhsLocation = that.getLocation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "location", lhsLocation), LocatorUtils.property(thatLocator, "location", rhsLocation), lhsLocation, rhsLocation)) {
                return false;
            }
        }
        {
            String lhsId;
            lhsId = this.getId();
            String rhsId;
            rhsId = that.getId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "id", lhsId), LocatorUtils.property(thatLocator, "id", rhsId), lhsId, rhsId)) {
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
            Location theLocation;
            theLocation = this.getLocation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "location", theLocation), currentHashCode, theLocation);
        }
        {
            String theId;
            theId = this.getId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "id", theId), currentHashCode, theId);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public FeatureCollection withLocation(Location value) {
        setLocation(value);
        return this;
    }

    public FeatureCollection withId(String value) {
        setId(value);
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
     *         &lt;element name="Polygon">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="exterior">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="LinearRing">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element ref="{http://www.opengis.net/gml/3.2}posList"/>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute ref="{http://www.opengis.net/gml/3.2}id"/>
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
        "polygon"
    })
    public static class Location implements Serializable, Equals, HashCode, ToString
    {

        private final static long serialVersionUID = 1L;
        @XmlElement(name = "Polygon", required = true)
        protected Polygon polygon;

        /**
         * Default no-arg constructor
         * 
         */
        public Location() {
            super();
        }

        /**
         * Fully-initialising value constructor
         * 
         */
        public Location(final Polygon polygon) {
            this.polygon = polygon;
        }

        /**
         * Gets the value of the polygon property.
         * 
         * @return
         *     possible object is
         *     {@link Polygon }
         *     
         */
        public Polygon getPolygon() {
            return polygon;
        }

        /**
         * Sets the value of the polygon property.
         * 
         * @param value
         *     allowed object is
         *     {@link Polygon }
         *     
         */
        public void setPolygon(Polygon value) {
            this.polygon = value;
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
                Polygon thePolygon;
                thePolygon = this.getPolygon();
                strategy.appendField(locator, this, "polygon", buffer, thePolygon);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Location)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Location that = ((Location) object);
            {
                Polygon lhsPolygon;
                lhsPolygon = this.getPolygon();
                Polygon rhsPolygon;
                rhsPolygon = that.getPolygon();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "polygon", lhsPolygon), LocatorUtils.property(thatLocator, "polygon", rhsPolygon), lhsPolygon, rhsPolygon)) {
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
                Polygon thePolygon;
                thePolygon = this.getPolygon();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "polygon", thePolygon), currentHashCode, thePolygon);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public Location withPolygon(Polygon value) {
            setPolygon(value);
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
         *         &lt;element name="exterior">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="LinearRing">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element ref="{http://www.opengis.net/gml/3.2}posList"/>
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
         *       &lt;attribute ref="{http://www.opengis.net/gml/3.2}id"/>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "exterior"
        })
        public static class Polygon implements Serializable, Equals, HashCode, ToString
        {

            private final static long serialVersionUID = 1L;
            @XmlElement(required = true)
            protected Exterior exterior;
            @XmlAttribute(name = "id", namespace = "http://www.opengis.net/gml/3.2")
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            @XmlID
            @XmlSchemaType(name = "ID")
            protected String id;

            /**
             * Default no-arg constructor
             * 
             */
            public Polygon() {
                super();
            }

            /**
             * Fully-initialising value constructor
             * 
             */
            public Polygon(final Exterior exterior, final String id) {
                this.exterior = exterior;
                this.id = id;
            }

            /**
             * Gets the value of the exterior property.
             * 
             * @return
             *     possible object is
             *     {@link Exterior }
             *     
             */
            public Exterior getExterior() {
                return exterior;
            }

            /**
             * Sets the value of the exterior property.
             * 
             * @param value
             *     allowed object is
             *     {@link Exterior }
             *     
             */
            public void setExterior(Exterior value) {
                this.exterior = value;
            }

            /**
             * Gets the value of the id property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getId() {
                return id;
            }

            /**
             * Sets the value of the id property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setId(String value) {
                this.id = value;
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
                    Exterior theExterior;
                    theExterior = this.getExterior();
                    strategy.appendField(locator, this, "exterior", buffer, theExterior);
                }
                {
                    String theId;
                    theId = this.getId();
                    strategy.appendField(locator, this, "id", buffer, theId);
                }
                return buffer;
            }

            public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                if (!(object instanceof Polygon)) {
                    return false;
                }
                if (this == object) {
                    return true;
                }
                final Polygon that = ((Polygon) object);
                {
                    Exterior lhsExterior;
                    lhsExterior = this.getExterior();
                    Exterior rhsExterior;
                    rhsExterior = that.getExterior();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "exterior", lhsExterior), LocatorUtils.property(thatLocator, "exterior", rhsExterior), lhsExterior, rhsExterior)) {
                        return false;
                    }
                }
                {
                    String lhsId;
                    lhsId = this.getId();
                    String rhsId;
                    rhsId = that.getId();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "id", lhsId), LocatorUtils.property(thatLocator, "id", rhsId), lhsId, rhsId)) {
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
                    Exterior theExterior;
                    theExterior = this.getExterior();
                    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "exterior", theExterior), currentHashCode, theExterior);
                }
                {
                    String theId;
                    theId = this.getId();
                    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "id", theId), currentHashCode, theId);
                }
                return currentHashCode;
            }

            public int hashCode() {
                final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
                return this.hashCode(null, strategy);
            }

            public Polygon withExterior(Exterior value) {
                setExterior(value);
                return this;
            }

            public Polygon withId(String value) {
                setId(value);
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
             *         &lt;element name="LinearRing">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element ref="{http://www.opengis.net/gml/3.2}posList"/>
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
                "linearRing"
            })
            public static class Exterior implements Serializable, Equals, HashCode, ToString
            {

                private final static long serialVersionUID = 1L;
                @XmlElement(name = "LinearRing", required = true)
                protected LinearRing linearRing;

                /**
                 * Default no-arg constructor
                 * 
                 */
                public Exterior() {
                    super();
                }

                /**
                 * Fully-initialising value constructor
                 * 
                 */
                public Exterior(final LinearRing linearRing) {
                    this.linearRing = linearRing;
                }

                /**
                 * Gets the value of the linearRing property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link LinearRing }
                 *     
                 */
                public LinearRing getLinearRing() {
                    return linearRing;
                }

                /**
                 * Sets the value of the linearRing property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link LinearRing }
                 *     
                 */
                public void setLinearRing(LinearRing value) {
                    this.linearRing = value;
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
                        LinearRing theLinearRing;
                        theLinearRing = this.getLinearRing();
                        strategy.appendField(locator, this, "linearRing", buffer, theLinearRing);
                    }
                    return buffer;
                }

                public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                    if (!(object instanceof Exterior)) {
                        return false;
                    }
                    if (this == object) {
                        return true;
                    }
                    final Exterior that = ((Exterior) object);
                    {
                        LinearRing lhsLinearRing;
                        lhsLinearRing = this.getLinearRing();
                        LinearRing rhsLinearRing;
                        rhsLinearRing = that.getLinearRing();
                        if (!strategy.equals(LocatorUtils.property(thisLocator, "linearRing", lhsLinearRing), LocatorUtils.property(thatLocator, "linearRing", rhsLinearRing), lhsLinearRing, rhsLinearRing)) {
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
                        LinearRing theLinearRing;
                        theLinearRing = this.getLinearRing();
                        currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "linearRing", theLinearRing), currentHashCode, theLinearRing);
                    }
                    return currentHashCode;
                }

                public int hashCode() {
                    final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
                    return this.hashCode(null, strategy);
                }

                public Exterior withLinearRing(LinearRing value) {
                    setLinearRing(value);
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
                 *         &lt;element ref="{http://www.opengis.net/gml/3.2}posList"/>
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
                    "posList"
                })
                public static class LinearRing implements Serializable, Equals, HashCode, ToString
                {

                    private final static long serialVersionUID = 1L;
                    @XmlList
                    @XmlElement(type = Double.class)
                    protected List<Double> posList;

                    /**
                     * Default no-arg constructor
                     * 
                     */
                    public LinearRing() {
                        super();
                    }

                    /**
                     * Fully-initialising value constructor
                     * 
                     */
                    public LinearRing(final List<Double> posList) {
                        this.posList = posList;
                    }

                    /**
                     * Gets the value of the posList property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the posList property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getPosList().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link Double }
                     * 
                     * 
                     */
                    public List<Double> getPosList() {
                        if (posList == null) {
                            posList = new ArrayList<Double>();
                        }
                        return this.posList;
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
                            List<Double> thePosList;
                            thePosList = (((this.posList!= null)&&(!this.posList.isEmpty()))?this.getPosList():null);
                            strategy.appendField(locator, this, "posList", buffer, thePosList);
                        }
                        return buffer;
                    }

                    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                        if (!(object instanceof LinearRing)) {
                            return false;
                        }
                        if (this == object) {
                            return true;
                        }
                        final LinearRing that = ((LinearRing) object);
                        {
                            List<Double> lhsPosList;
                            lhsPosList = (((this.posList!= null)&&(!this.posList.isEmpty()))?this.getPosList():null);
                            List<Double> rhsPosList;
                            rhsPosList = (((that.posList!= null)&&(!that.posList.isEmpty()))?that.getPosList():null);
                            if (!strategy.equals(LocatorUtils.property(thisLocator, "posList", lhsPosList), LocatorUtils.property(thatLocator, "posList", rhsPosList), lhsPosList, rhsPosList)) {
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
                            List<Double> thePosList;
                            thePosList = (((this.posList!= null)&&(!this.posList.isEmpty()))?this.getPosList():null);
                            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "posList", thePosList), currentHashCode, thePosList);
                        }
                        return currentHashCode;
                    }

                    public int hashCode() {
                        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
                        return this.hashCode(null, strategy);
                    }

                    public LinearRing withPosList(Double... values) {
                        if (values!= null) {
                            for (Double value: values) {
                                getPosList().add(value);
                            }
                        }
                        return this;
                    }

                    public LinearRing withPosList(Collection<Double> values) {
                        if (values!= null) {
                            getPosList().addAll(values);
                        }
                        return this;
                    }

                }

            }

        }

    }

}