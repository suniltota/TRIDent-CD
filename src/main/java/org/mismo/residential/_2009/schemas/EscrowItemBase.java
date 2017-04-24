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
 * <p>Java class for EscrowItemBase.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EscrowItemBase">
 *   &lt;restriction base="{http://www.mismo.org/residential/2009/schemas}MISMOEnum_Base">
 *     &lt;enumeration value="AssessmentTax"/>
 *     &lt;enumeration value="BoroughPropertyTax"/>
 *     &lt;enumeration value="CityPropertyTax"/>
 *     &lt;enumeration value="CondominiumAssociationDues"/>
 *     &lt;enumeration value="CondominiumAssociationSpecialAssessment"/>
 *     &lt;enumeration value="CooperativeAssociationDues"/>
 *     &lt;enumeration value="CooperativeAssociationSpecialAssessment"/>
 *     &lt;enumeration value="CountyBondTax"/>
 *     &lt;enumeration value="CountyPropertyTax"/>
 *     &lt;enumeration value="DistrictPropertyTax"/>
 *     &lt;enumeration value="EarthquakeInsurance"/>
 *     &lt;enumeration value="EnergyEfficientImprovementFunds"/>
 *     &lt;enumeration value="FloodInsurance"/>
 *     &lt;enumeration value="HailInsurancePremium"/>
 *     &lt;enumeration value="HazardInsurance"/>
 *     &lt;enumeration value="HomeownersAssociationDues"/>
 *     &lt;enumeration value="HomeownersAssociationSpecialAssessment"/>
 *     &lt;enumeration value="HomeownersInsurance"/>
 *     &lt;enumeration value="MortgageInsurance"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="ParishTax"/>
 *     &lt;enumeration value="PestInsurance"/>
 *     &lt;enumeration value="RehabilitationFunds"/>
 *     &lt;enumeration value="SchoolPropertyTax"/>
 *     &lt;enumeration value="StatePropertyTax"/>
 *     &lt;enumeration value="TownPropertyTax"/>
 *     &lt;enumeration value="TownshipPropertyTax"/>
 *     &lt;enumeration value="VillagePropertyTax"/>
 *     &lt;enumeration value="VolcanoInsurance"/>
 *     &lt;enumeration value="WindstormInsurance"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EscrowItemBase")
@XmlEnum
public enum EscrowItemBase {

    @XmlEnumValue("AssessmentTax")
    ASSESSMENT_TAX("AssessmentTax"),
    @XmlEnumValue("BoroughPropertyTax")
    BOROUGH_PROPERTY_TAX("BoroughPropertyTax"),
    @XmlEnumValue("CityPropertyTax")
    CITY_PROPERTY_TAX("CityPropertyTax"),
    @XmlEnumValue("CondominiumAssociationDues")
    CONDOMINIUM_ASSOCIATION_DUES("CondominiumAssociationDues"),
    @XmlEnumValue("CondominiumAssociationSpecialAssessment")
    CONDOMINIUM_ASSOCIATION_SPECIAL_ASSESSMENT("CondominiumAssociationSpecialAssessment"),
    @XmlEnumValue("CooperativeAssociationDues")
    COOPERATIVE_ASSOCIATION_DUES("CooperativeAssociationDues"),
    @XmlEnumValue("CooperativeAssociationSpecialAssessment")
    COOPERATIVE_ASSOCIATION_SPECIAL_ASSESSMENT("CooperativeAssociationSpecialAssessment"),
    @XmlEnumValue("CountyBondTax")
    COUNTY_BOND_TAX("CountyBondTax"),
    @XmlEnumValue("CountyPropertyTax")
    COUNTY_PROPERTY_TAX("CountyPropertyTax"),
    @XmlEnumValue("DistrictPropertyTax")
    DISTRICT_PROPERTY_TAX("DistrictPropertyTax"),
    @XmlEnumValue("EarthquakeInsurance")
    EARTHQUAKE_INSURANCE("EarthquakeInsurance"),
    @XmlEnumValue("EnergyEfficientImprovementFunds")
    ENERGY_EFFICIENT_IMPROVEMENT_FUNDS("EnergyEfficientImprovementFunds"),
    @XmlEnumValue("FloodInsurance")
    FLOOD_INSURANCE("FloodInsurance"),
    @XmlEnumValue("HailInsurancePremium")
    HAIL_INSURANCE_PREMIUM("HailInsurancePremium"),
    @XmlEnumValue("HazardInsurance")
    HAZARD_INSURANCE("HazardInsurance"),
    @XmlEnumValue("HomeownersAssociationDues")
    HOMEOWNERS_ASSOCIATION_DUES("HomeownersAssociationDues"),
    @XmlEnumValue("HomeownersAssociationSpecialAssessment")
    HOMEOWNERS_ASSOCIATION_SPECIAL_ASSESSMENT("HomeownersAssociationSpecialAssessment"),
    @XmlEnumValue("HomeownersInsurance")
    HOMEOWNERS_INSURANCE("HomeownersInsurance"),
    @XmlEnumValue("MortgageInsurance")
    MORTGAGE_INSURANCE("MortgageInsurance"),
    @XmlEnumValue("Other")
    OTHER("Other"),
    @XmlEnumValue("ParishTax")
    PARISH_TAX("ParishTax"),
    @XmlEnumValue("PestInsurance")
    PEST_INSURANCE("PestInsurance"),

    /**
     * Escrow account for rehabilitation repair or upgrade funds that are set aside as part of a Section 203(k) rehabilitation loan.
     * 
     */
    @XmlEnumValue("RehabilitationFunds")
    REHABILITATION_FUNDS("RehabilitationFunds"),
    @XmlEnumValue("SchoolPropertyTax")
    SCHOOL_PROPERTY_TAX("SchoolPropertyTax"),
    @XmlEnumValue("StatePropertyTax")
    STATE_PROPERTY_TAX("StatePropertyTax"),
    @XmlEnumValue("TownPropertyTax")
    TOWN_PROPERTY_TAX("TownPropertyTax"),
    @XmlEnumValue("TownshipPropertyTax")
    TOWNSHIP_PROPERTY_TAX("TownshipPropertyTax"),
    @XmlEnumValue("VillagePropertyTax")
    VILLAGE_PROPERTY_TAX("VillagePropertyTax"),
    @XmlEnumValue("VolcanoInsurance")
    VOLCANO_INSURANCE("VolcanoInsurance"),
    @XmlEnumValue("WindstormInsurance")
    WINDSTORM_INSURANCE("WindstormInsurance");
    private final String value;

    EscrowItemBase(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EscrowItemBase fromValue(String v) {
        for (EscrowItemBase c: EscrowItemBase.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
