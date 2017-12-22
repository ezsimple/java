//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.2-hudson-jaxb-ri-2.2-63- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.29 at 05:09:52 \uc624\ud6c4 KST 
//


package net.ion.open.oadr2.model.v20b;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for temperatureUnitType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="temperatureUnitType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="celsius"/>
 *     &lt;enumeration value="fahrenheit"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "temperatureUnitType")
@XmlEnum
public enum TemperatureUnitType {

    @XmlEnumValue("celsius")
    CELSIUS("celsius"),
    @XmlEnumValue("fahrenheit")
    FAHRENHEIT("fahrenheit");
    private final String value;

    TemperatureUnitType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TemperatureUnitType fromValue(String v) {
        for (TemperatureUnitType c: TemperatureUnitType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
