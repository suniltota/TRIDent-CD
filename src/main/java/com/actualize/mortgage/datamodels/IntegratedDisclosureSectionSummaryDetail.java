package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents IntegratedDisclosureSectionSummaryDetail in MISMO XML
 * @author sboragala
 *
 */
public class IntegratedDisclosureSectionSummaryDetail extends MISMODataAccessObject {

	private static final long serialVersionUID = 3239113459319003766L;
	public final String integratedDisclosureSectionTotalAmount;
	public final String integratedDisclosureSectionType;
	public final String integratedDisclosureSubsectionTotalAmount;
	public final String integratedDisclosureSubsectionType;
	public final String integratedDisclosureSubsectionTypeOtherDescription;
	public final String lenderCreditToleranceCureAmount;
	
	public IntegratedDisclosureSectionSummaryDetail(Element element) {
		super(element);
		integratedDisclosureSectionTotalAmount = getValueAddNS("IntegratedDisclosureSectionTotalAmount");
		integratedDisclosureSectionType = getValueAddNS("IntegratedDisclosureSectionType");
		integratedDisclosureSubsectionTotalAmount = getValueAddNS("IntegratedDisclosureSubsectionTotalAmount");
		integratedDisclosureSubsectionType = getValueAddNS("IntegratedDisclosureSubsectionType");
		integratedDisclosureSubsectionTypeOtherDescription = getValueAddNS("IntegratedDisclosureSubsectionTypeOtherDescription");
		lenderCreditToleranceCureAmount = getValueAddNS("LenderCreditToleranceCureAmount");
	}
}
