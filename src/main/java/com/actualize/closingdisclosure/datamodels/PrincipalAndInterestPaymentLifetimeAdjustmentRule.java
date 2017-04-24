package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class PrincipalAndInterestPaymentLifetimeAdjustmentRule extends MISMODataAccessObject {
	public final String FirstPrincipalAndInterestPaymentChangeMonthsCount;
	public final String PrincipalAndInterestPaymentMaximumAmount;
	public final String PrincipalAndInterestPaymentMaximumAmountEarliestEffectiveMonthsCount;
	
	public PrincipalAndInterestPaymentLifetimeAdjustmentRule(Element element) {
		super(element);
		FirstPrincipalAndInterestPaymentChangeMonthsCount = getValueAddNS("FirstPrincipalAndInterestPaymentChangeMonthsCount");
		PrincipalAndInterestPaymentMaximumAmount = getValueAddNS("PrincipalAndInterestPaymentMaximumAmount");
		PrincipalAndInterestPaymentMaximumAmountEarliestEffectiveMonthsCount = getValueAddNS("PrincipalAndInterestPaymentMaximumAmountEarliestEffectiveMonthsCount");
	}
}
