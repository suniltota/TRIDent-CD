package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class PrepaymentPenaltyLifetimeRule extends MISMODataAccessObject {
	public final String PrepaymentPenaltyExpirationDate;
	public final String PrepaymentPenaltyExpirationMonthsCount;
	public final String PrepaymentPenaltyMaximumLifeOfLoanAmount;
	
	public PrepaymentPenaltyLifetimeRule(Element element) {
		super(element);
		PrepaymentPenaltyExpirationDate = getValueAddNS("PrepaymentPenaltyExpirationDate");
		PrepaymentPenaltyExpirationMonthsCount = getValueAddNS("PrepaymentPenaltyExpirationMonthsCount");
		PrepaymentPenaltyMaximumLifeOfLoanAmount = getValueAddNS("PrepaymentPenaltyMaximumLifeOfLoanAmount");
	}
}
