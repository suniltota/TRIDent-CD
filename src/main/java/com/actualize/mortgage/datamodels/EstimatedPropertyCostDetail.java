package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents EstimatedPropertyCostDetail in MISMO XML
 * @author sboragala
 *
 */
public class EstimatedPropertyCostDetail extends MISMODataAccessObject {
	private static final long serialVersionUID = 5332830603254422532L;
	public final String ProjectedPaymentEstimatedTaxesInsuranceAssessmentTotalAmount;
	
	public EstimatedPropertyCostDetail(Element element) {
		super(element);
		ProjectedPaymentEstimatedTaxesInsuranceAssessmentTotalAmount = getValueAddNS("ProjectedPaymentEstimatedTaxesInsuranceAssessmentTotalAmount");
	}
}
