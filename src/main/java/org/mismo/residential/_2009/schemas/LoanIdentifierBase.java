//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.01 at 06:02:48 PM IST 
//


package org.mismo.residential._2009.schemas;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LoanIdentifierBase.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LoanIdentifierBase">
 *   &lt;restriction base="{http://www.mismo.org/residential/2009/schemas}MISMOEnum_Base">
 *     &lt;enumeration value="AgencyCase"/>
 *     &lt;enumeration value="LenderLoan"/>
 *     &lt;enumeration value="MERS_MIN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LoanIdentifierBase")
@XmlEnum
public enum LoanIdentifierBase {


    /**
     * An identifier assigned by a government agency (for example FHA Case Number, VA Case Number). The identifier is used by the agency to identify a loan.
     * 
     */
    @XmlEnumValue("AgencyCase")
    AGENCY_CASE("AgencyCase"),

    /**
     * The identifier assigned by the originating Lender to be referenced as the Loan ID/Number on all settlement documents, notes, riders, etc.
     * 
     */
    @XmlEnumValue("LenderLoan")
    LENDER_LOAN("LenderLoan"),

    /**
     * Number used by MERS to identify loans. Referred to as the MIN, Mortgage Identification Number.
     * 
     */
    MERS_MIN("MERS_MIN");
    private final String value;

    LoanIdentifierBase(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LoanIdentifierBase fromValue(String v) {
        for (LoanIdentifierBase c: LoanIdentifierBase.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
