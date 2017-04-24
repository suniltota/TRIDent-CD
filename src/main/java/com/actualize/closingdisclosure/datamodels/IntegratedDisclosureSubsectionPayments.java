package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class IntegratedDisclosureSubsectionPayments extends MISMODataAccessObject {
	public final IntegratedDisclosureSubsectionPayment[] integratedDisclosureSubsectionPayments;

	public IntegratedDisclosureSubsectionPayments(Element element) {
		super(element);
		NodeList nodes = getElementsAddNS((NS == null ? "" : NS)+"INTEGRATED_DISCLOSURE_SUBSECTION_PAYMENT");
		integratedDisclosureSubsectionPayments = new IntegratedDisclosureSubsectionPayment[nodes==null ? 0 : nodes.getLength()];
		for (int i = 0; i < integratedDisclosureSubsectionPayments.length; i++)
			integratedDisclosureSubsectionPayments[i] = new IntegratedDisclosureSubsectionPayment((Element)nodes.item(i));
	}
}
