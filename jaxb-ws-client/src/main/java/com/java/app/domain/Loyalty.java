
package com.java.app.domain;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for loyalty.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="loyalty"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="BRONZE"/&gt;
 *     &lt;enumeration value="SILVER"/&gt;
 *     &lt;enumeration value="GOLD"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "loyalty")
@XmlEnum
public enum Loyalty {

    BRONZE,
    SILVER,
    GOLD;

    public String value() {
        return name();
    }

    public static Loyalty fromValue(String v) {
        return valueOf(v);
    }

}
