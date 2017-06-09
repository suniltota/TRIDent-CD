package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents PrepaidItemPayment in MISMO XML
 * @author sboragala
 *
 */
public class PrepaidItemPayment extends MISMODataAccessObject {
	private static final long serialVersionUID = -4837048901075958146L;
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
