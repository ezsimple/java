//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.2-hudson-jaxb-ri-2.2-63- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.29 at 05:09:52 \uc624\ud6c4 KST 
//


package net.ion.open.oadr2.model.v20b.ei;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EventStatusEnumeratedType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EventStatusEnumeratedType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="none"/>
 *     &lt;enumeration value="far"/>
 *     &lt;enumeration value="near"/>
 *     &lt;enumeration value="active"/>
 *     &lt;enumeration value="completed"/>
 *     &lt;enumeration value="cancelled"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EventStatusEnumeratedType")
@XmlEnum
public enum EventStatusEnumeratedType {


    /**
     * No event pending
     * 
     */
    @XmlEnumValue("none")
    NONE("none"),

    /**
     * Event pending in the far future. The exact definition of how far in the future this refers is dependent upon the market context, but typically means the next day.
     * 
     */
    @XmlEnumValue("far")
    FAR("far"),

    /**
     * Event pending in the near future. The exact definition of how near in the future the pending event is active is dependent on the market context.
     * 
     */
    @XmlEnumValue("near")
    NEAR("near"),

    /**
     * The event has been initiated and is currently active.
     * 
     */
    @XmlEnumValue("active")
    ACTIVE("active"),

    /**
     * The event has completed.
     * 
     */
    @XmlEnumValue("completed")
    COMPLETED("completed"),

    /**
     * The event has been canceled.
     * 
     */
    @XmlEnumValue("cancelled")
    CANCELLED("cancelled");
    private final String value;

    EventStatusEnumeratedType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EventStatusEnumeratedType fromValue(String v) {
        for (EventStatusEnumeratedType c: EventStatusEnumeratedType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
