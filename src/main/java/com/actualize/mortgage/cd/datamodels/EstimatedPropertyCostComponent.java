package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents EstimatedPropertyCostComponent in MISMO XML
 * @author sboragala
 *
 */
public class EstimatedPropertyCostComponent extends MISMODataAccessObject {
	private static final long serialVersionUID = -6284756132550201190L;
	public final String projectedPaymentEscrowedType;
	public final String projectedPaymentEstimatedTaxesInsuranceAssessmentComponentType;
	public final String projectedPaymentEstimatedTaxesInsuranceAssessmentComponentTypeOtherDescription;
	
	public EstimatedPropertyCostComponent(Element element) {
		super(element);
		projectedPaymentEscrowedType = getValueAddNS("ProjectedPaymentEscrowedType");
		projectedPaymentEstimatedTaxesInsuranceAssessmentComponentType = getValueAddNS("ProjectedPaymentEstimatedTaxesInsuranceAssessmentComponentType");
		projectedPaymentEstimatedTaxesInsuranceAssessmentComponentTypeOtherDescription = getValueAddNS("ProjectedPaymentEstimatedTaxesInsuranceAssessmentComponentTypeOtherDescription");
	}
}
