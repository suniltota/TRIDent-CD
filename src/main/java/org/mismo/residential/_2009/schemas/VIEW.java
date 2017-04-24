//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.01 at 06:02:48 PM IST 
//


package org.mismo.residential._2009.schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A complete, standalone visual representation of the document  at a given point in its lifecycle. Document processors may create new VIEW elements when there is a need to effect a change to the view but the previous version has to be preserved because of legal constraints, technical limitations or other reasons.  The first VIEW element created in the DOCUMENT must have a SequenceNumber  with the value 1. Each subsequent element must have the sequence number of the previous element plus 1. The VIEW element with the highest sequence identifier is the current version of the view.
 * 
 * <p>Java class for VIEW complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VIEW">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VIEW_FILES" type="{http://www.mismo.org/residential/2009/schemas}VIEW_FILES"/>
 *       &lt;/sequence>
 *       &lt;attribute name="SequenceNumber" type="{http://www.mismo.org/residential/2009/schemas}MISMOSequenceNumber_Base" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VIEW", propOrder = {
    "viewfiles"
})
public class VIEW {

    @XmlElement(name = "VIEW_FILES", required = true)
    protected VIEWFILES viewfiles;
    @XmlAttribute(name = "SequenceNumber")
    protected Integer sequenceNumber;

    /**
     * Gets the value of the viewfiles property.
     * 
     * @return
     *     possible object is
     *     {@link VIEWFILES }
     *     
     */
    public VIEWFILES getVIEWFILES() {
        return viewfiles;
    }

    /**
     * Sets the value of the viewfiles property.
     * 
     * @param value
     *     allowed object is
     *     {@link VIEWFILES }
     *     
     */
    public void setVIEWFILES(VIEWFILES value) {
        this.viewfiles = value;
    }

    /**
     * Gets the value of the sequenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the value of the sequenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSequenceNumber(Integer value) {
        this.sequenceNumber = value;
    }

}
