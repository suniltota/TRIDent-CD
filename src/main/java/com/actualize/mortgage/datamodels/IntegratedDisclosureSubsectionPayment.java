package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents IntegratedDisclosureSubsectionPayment from XML
 * @author sboragala
 *
 */
public class IntegratedDisclosureSubsectionPayment extends MISMODataAccessObject {

	private static final long serialVersionUID = 1592431424100790650L;
	
	public final String integratedDisclosureSubsectionPaidByType;
	public final String integratedDisclosureSubsectionPaymentAmount;
	public final String integratedDisclosureSubsectionPaymentTimingType;
	
	public IntegratedDisclosureSubsectionPayment(Element element) {
		super(element);
		integratedDisclosureSubsectionPaidByType = getValueAddNS("IntegratedDisclosureSubsectionPaidByType");
		integratedDisclosureSubsectionPaymentAmount = getValueAddNS("IntegratedDisclosureSubsectionPaymentAmount");
		integratedDisclosureSubsectionPaymentTimingType = getValueAddNS("IntegratedDisclosureSubsectionPaymentTimingType");
	}
}
