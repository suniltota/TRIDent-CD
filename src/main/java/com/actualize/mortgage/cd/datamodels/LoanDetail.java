package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents LoanDetail in MISMO XML
 * @author sboragala
 *
 */
public class LoanDetail extends MISMODataAccessObject {
	
	private static final long serialVersionUID = -8845666261771481514L;
	
	public final String assumabilityIndicator;
	public final String balloonIndicator;
	public final String balloonPaymentAmount;
	public final String buydownTemporarySubsidyFundingIndicator;
	public final String constructionLoanIndicator;
	public final String creditorServicingOfLoanStatementType;
	public final String demandFeatureIndicator;
	public final String escrowAbsenceReasonType;
	public final String escrowIndicator;
	public final String interestOnlyIndicator;
	public final String interestRateIncreaseIndicator;
	public final String loanAmountIncreaseIndicator;
	public final String miRequiredIndicator;
	public final String negativeAmortizationIndicator;
	public final String paymentIncreaseIndicator;
	public final String prepaymentPenaltyIndicator;
	public final String seasonalPaymentFeatureIndicator;
	public final String stepPaymentsFeatureDescription;
	public final String totalSubordinateFinancingAmount;
	public final String subordinateFinancingIsNewIndicator;

	public LoanDetail(Element element) {
		super(element);
		assumabilityIndicator = getValueAddNS("AssumabilityIndicator");
		balloonIndicator = getValueAddNS("BalloonIndicator");
		balloonPaymentAmount = getValueAddNS("BalloonPaymentAmount");
		buydownTemporarySubsidyFundingIndicator = getValueAddNS("BuydownTemporarySubsidyFundingIndicator");
		constructionLoanIndicator = getValueAddNS("ConstructionLoanIndicator");
		creditorServicingOfLoanStatementType = getValueAddNS("CreditorServicingOfLoanStatementType");
		demandFeatureIndicator = getValueAddNS("DemandFeatureIndicator");
		escrowAbsenceReasonType = getValueAddNS("EscrowAbsenceReasonType");
		escrowIndicator = getValueAddNS("EscrowIndicator");
		interestOnlyIndicator = getValueAddNS("InterestOnlyIndicator");
		interestRateIncreaseIndicator = getValueAddNS("InterestRateIncreaseIndicator");
		loanAmountIncreaseIndicator = getValueAddNS("LoanAmountIncreaseIndicator");
		miRequiredIndicator = getValueAddNS("MIRequiredIndicator");
		negativeAmortizationIndicator = getValueAddNS("NegativeAmortizationIndicator");
		paymentIncreaseIndicator = getValueAddNS("PaymentIncreaseIndicator");
		prepaymentPenaltyIndicator = getValueAddNS("PrepaymentPenaltyIndicator");
		seasonalPaymentFeatureIndicator = getValueAddNS("SeasonalPaymentFeatureIndicator");
		stepPaymentsFeatureDescription = getValueAddNS("StepPaymentsFeatureDescription");
		totalSubordinateFinancingAmount = getValueAddNS("TotalSubordinateFinancingAmount");
		subordinateFinancingIsNewIndicator = getValue("mismo:EXTENSION/mismo:OTHER/gse:SubordinateFinancingIsNewIndicator");
	}
}
