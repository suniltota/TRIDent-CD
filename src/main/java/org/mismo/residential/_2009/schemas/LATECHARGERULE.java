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
 * <p>Java class for LATE_CHARGE_RULE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LATE_CHARGE_RULE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LateChargeAmount" type="{http://www.mismo.org/residential/2009/schemas}MISMOAmount" minOccurs="0"/>
 *         &lt;element name="LateChargeGracePeriodDaysCount" type="{http://www.mismo.org/residential/2009/schemas}MISMOCount" minOccurs="0"/>
 *         &lt;element name="LateChargeRatePercent" type="{http://www.mismo.org/residential/2009/schemas}MISMOPercent" minOccurs="0"/>
 *         &lt;element name="LateChargeType" type="{http://www.mismo.org/residential/2009/schemas}LateChargeEnum" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LATE_CHARGE_RULE", propOrder = {
    "lateChargeAmount",
    "lateChargeGracePeriodDaysCount",
    "lateChargeRatePercent",
    "lateChargeType"
})
public class LATECHARGERULE {

    @XmlElement(name = "LateChargeAmount")
    protected MISMOAmount lateChargeAmount;
    @XmlElement(name = "LateChargeGracePeriodDaysCount")
    protected MISMOCount lateChargeGracePeriodDaysCount;
    @XmlElement(name = "LateChargeRatePercent")
    protected MISMOPercent lateChargeRatePercent;
    @XmlElement(name = "LateChargeType")
    protected LateChargeEnum lateChargeType;

    /**
     * Gets the value of the lateChargeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link MISMOAmount }
     *     
     */
    public MISMOAmount getLateChargeAmount() {
        return lateChargeAmount;
    }

    /**
     * Sets the value of the lateChargeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link MISMOAmount }
     *     
     */
    public void setLateChargeAmount(MISMOAmount value) {
        this.lateChargeAmount = value;
    }

    /**
     * Gets the value of the lateChargeGracePeriodDaysCount property.
     * 
     * @return
     *     possible object is
     *     {@link MISMOCount }
     *     
     */
    public MISMOCount getLateChargeGracePeriodDaysCount() {
        return lateChargeGracePeriodDaysCount;
    }

    /**
     * Sets the value of the lateChargeGracePeriodDaysCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link MISMOCount }
     *     
     */
    public void setLateChargeGracePeriodDaysCount(MISMOCount value) {
        this.lateChargeGracePeriodDaysCount = value;
    }

    /**
     * Gets the value of the lateChargeRatePercent property.
     * 
     * @return
     *     possible object is
     *     {@link MISMOPercent }
     *     
     */
    public MISMOPercent getLateChargeRatePercent() {
        return lateChargeRatePercent;
    }

    /**
     * Sets the value of the lateChargeRatePercent property.
     * 
     * @param value
     *     allowed object is
     *     {@link MISMOPercent }
     *     
     */
    public void setLateChargeRatePercent(MISMOPercent value) {
        this.lateChargeRatePercent = value;
    }

    /**
     * Gets the value of the lateChargeType property.
     * 
     * @return
     *     possible object is
     *     {@link LateChargeEnum }
     *     
     */
    public LateChargeEnum getLateChargeType() {
        return lateChargeType;
    }

    /**
     * Sets the value of the lateChargeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link LateChargeEnum }
     *     
     */
    public void setLateChargeType(LateChargeEnum value) {
        this.lateChargeType = value;
    }

}
