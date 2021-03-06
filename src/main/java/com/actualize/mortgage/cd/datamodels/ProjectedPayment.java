package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents ProjectedPayment in MISMO XML
 * @author sboragala
 *
 */
public class ProjectedPayment extends MISMODataAccessObject {
	private static final long serialVersionUID = 1965224472442051932L;
	public final String sequenceNumber;
	public final String paymentFrequencyType;
	public final String projectedPaymentCalculationPeriodEndNumber;
	public final String projectedPaymentCalculationPeriodStartNumber;
	public final String projectedPaymentCalculationPeriodTermType;
	public final String projectedPaymentCalculationPeriodTermTypeOtherDescription;
	public final String projectedPaymentEstimatedEscrowPaymentAmount;
	public final String projectedPaymentEstimatedTotalMaximumPaymentAmount;
	public final String projectedPaymentEstimatedTotalMinimumPaymentAmount;
	public final String projectedPaymentMIPaymentAmount;
	public final String projectedPaymentPrincipalAndInterestMaximumPaymentAmount;
	public final String projectedPaymentPrincipalAndInterestMinimumPaymentAmount;
	/**
	 * Parameterized constructor to get values from XML
	 * @param element
	 */
	public ProjectedPayment(Element element) {
		super(element);
		sequenceNumber = getAttributeValue("SequenceNumber");
		paymentFrequencyType = getValueAddNS("PaymentFrequencyType");
		projectedPaymentCalculationPeriodEndNumber = getValueAddNS("ProjectedPaymentCalculationPeriodEndNumber");
		projectedPaymentCalculationPeriodStartNumber = getValueAddNS("ProjectedPaymentCalculationPeriodStartNumber");
		projectedPaymentCalculationPeriodTermType = getValueAddNS("ProjectedPaymentCalculationPeriodTermType");
		projectedPaymentCalculationPeriodTermTypeOtherDescription = getValueAddNS("ProjectedPaymentCalculationPeriodTermTypeOtherDescription");
		projectedPaymentEstimatedEscrowPaymentAmount = getValueAddNS("ProjectedPaymentEstimatedEscrowPaymentAmount");
		projectedPaymentEstimatedTotalMaximumPaymentAmount = getValueAddNS("ProjectedPaymentEstimatedTotalMaximumPaymentAmount");
		projectedPaymentEstimatedTotalMinimumPaymentAmount = getValueAddNS("ProjectedPaymentEstimatedTotalMinimumPaymentAmount");
		projectedPaymentMIPaymentAmount = getValueAddNS("ProjectedPaymentMIPaymentAmount");
		projectedPaymentPrincipalAndInterestMaximumPaymentAmount = getValueAddNS("ProjectedPaymentPrincipalAndInterestMaximumPaymentAmount");
		projectedPaymentPrincipalAndInterestMinimumPaymentAmount = getValueAddNS("ProjectedPaymentPrincipalAndInterestMinimumPaymentAmount");
	}
}
