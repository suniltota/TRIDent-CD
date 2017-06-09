package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents NegativeAmortizationRule in MISMO XML
 * @author sboragala
 *
 */
public class NegativeAmortizationRule extends MISMODataAccessObject {

	private static final long serialVersionUID = -3254683544425468201L;
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
