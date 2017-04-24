package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class PrepaidItemPayment extends MISMODataAccessObject {
	public final String prepaidItemActualPaymentAmount;
	public final String prepaidItemPaymentPaidByType;
	public final String prepaidItemPaymentTimingType;
	public final String regulationZPointsAndFeesIndicator;
	
	public PrepaidItemPayment(Element e) {
		super(e);
		prepaidItemActualPaymentAmount = getValueAddNS("PrepaidItemActualPaymentAmount");
		prepaidItemPaymentPaidByType = getValueAddNS("PrepaidItemPaymentPaidByType");
		prepaidItemPaymentTimingType = getValueAddNS("PrepaidItemPaymentTimingType");
		regulationZPointsAndFeesIndicator = getValueAddNS("regulationZPointsAndFeesIndicator");
	}
	
}
