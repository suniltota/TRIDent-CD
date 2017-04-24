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
 * Specifies the general names (type) of items commonly listed as financial assets of the borrower(s) in a mortgage loan transaction. Assets may be either liquid or fixed and are associated with a corresponding asset amount.
 * 
 * <p>Java class for AssetEnum complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssetEnum">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.mismo.org/residential/2009/schemas>AssetBase">
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssetEnum", propOrder = {
    "value"
})
public class AssetEnum {

    @XmlValue
    protected AssetBase value;

    /**
     * Term: Asset Type Definition: Specifies the general names (type) of items commonly listed as financial assets of the borrower(s) in a mortgage loan transaction. Assets may be either liquid or fixed and are associated with a corresponding asset amount.
     * 
     * @return
     *     possible object is
     *     {@link AssetBase }
     *     
     */
    public AssetBase getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetBase }
     *     
     */
    public void setValue(AssetBase value) {
        this.value = value;
    }

}
