//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.01 at 06:02:48 PM IST 
//


package org.mismo.residential._2009.schemas;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Information regarding one or more prepaid item payment breakouts. Holds all occurrences of PREPAID_ITEM_PAYMENT.
 * 
 * <p>Java class for PREPAID_ITEM_PAYMENTS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PREPAID_ITEM_PAYMENTS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PREPAID_ITEM_PAYMENT" type="{http://www.mismo.org/residential/2009/schemas}PREPAID_ITEM_PAYMENT" maxOccurs="5" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PREPAID_ITEM_PAYMENTS", propOrder = {
    "prepaiditempayment"
})
public class PREPAIDITEMPAYMENTS {

    @XmlElement(name = "PREPAID_ITEM_PAYMENT")
    protected List<PREPAIDITEMPAYMENT> prepaiditempayment;

    /**
     * Gets the value of the prepaiditempayment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prepaiditempayment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPREPAIDITEMPAYMENT().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PREPAIDITEMPAYMENT }
     * 
     * 
     */
    public List<PREPAIDITEMPAYMENT> getPREPAIDITEMPAYMENT() {
        if (prepaiditempayment == null) {
            prepaiditempayment = new ArrayList<PREPAIDITEMPAYMENT>();
        }
        return this.prepaiditempayment;
    }

}
