//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.01 at 06:02:48 PM IST 
//


package org.mismo.residential._2009.schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * Level of authority of the license issuer.
 * 
 * <p>Java class for LicenseAuthorityLevelEnum complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LicenseAuthorityLevelEnum">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.mismo.org/residential/2009/schemas>LicenseAuthorityLevelBase">
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LicenseAuthorityLevelEnum", propOrder = {
    "value"
})
public class LicenseAuthorityLevelEnum {

    @XmlValue
    protected LicenseAuthorityLevelBase value;

    /**
     * Term: License Authority Level Type Definition: Level of authority of the license issuer.
     * 
     * @return
     *     possible object is
     *     {@link LicenseAuthorityLevelBase }
     *     
     */
    public LicenseAuthorityLevelBase getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link LicenseAuthorityLevelBase }
     *     
     */
    public void setValue(LicenseAuthorityLevelBase value) {
        this.value = value;
    }

}
