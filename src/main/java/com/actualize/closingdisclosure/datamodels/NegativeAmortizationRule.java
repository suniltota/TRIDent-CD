package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class NegativeAmortizationRule extends MISMODataAccessObject {
	public final String LoanNegativeAmortizationResolutionType;
	public final String LoanNegativeAmortizationResolutionTypeOtherDescription;
	public final String NegativeAmortizationLimitMonthsCount;
	public final String NegativeAmortizationMaximumLoanBalanceAmount;
	public final String NegativeAmortizationType;
	
	public NegativeAmortizationRule(Element element) {
		super(element);
		LoanNegativeAmortizationResolutionType = getValueAddNS("LoanNegativeAmortizationResolutionType");
		LoanNegativeAmortizationResolutionTypeOtherDescription = getValueAddNS("LoanNegativeAmortizationResolutionTypeOtherDescription");
		NegativeAmortizationLimitMonthsCount = getValueAddNS("NegativeAmortizationLimitMonthsCount");
		NegativeAmortizationMaximumLoanBalanceAmount = getValueAddNS("NegativeAmortizationMaximumLoanBalanceAmount");
		NegativeAmortizationType = getValueAddNS("NegativeAmortizationType");
	}
}
