//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.01 at 06:02:48 PM IST 
//


package org.mismo.residential._2009.schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SALES_CONTRACTS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SALES_CONTRACTS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SALES_CONTRACT" type="{http://www.mismo.org/residential/2009/schemas}SALES_CONTRACT" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SALES_CONTRACTS", propOrder = {
    "salescontract"
})
public class SALESCONTRACTS {

    @XmlElement(name = "SALES_CONTRACT")
    protected SALESCONTRACT salescontract;

    /**
     * Gets the value of the salescontract property.
     * 
     * @return
     *     possible object is
     *     {@link SALESCONTRACT }
     *     
     */
    public SALESCONTRACT getSALESCONTRACT() {
        return salescontract;
    }

    /**
     * Sets the value of the salescontract property.
     * 
     * @param value
     *     allowed object is
     *     {@link SALESCONTRACT }
     *     
     */
    public void setSALESCONTRACT(SALESCONTRACT value) {
        this.salescontract = value;
    }

}
