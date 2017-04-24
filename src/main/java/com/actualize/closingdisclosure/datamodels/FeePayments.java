package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class FeePayments extends MISMODataAccessObject {
	public final FeePayment[] feePayments;

	public FeePayments(Element element) {
		super(element);
		NodeList nodes = getElementsAddNS("FEE_PAYMENT");
		feePayments = new FeePayment[nodes==null ? 0 : nodes.getLength()];
		for (int i = 0; i < feePayments.length; i++)
			feePayments[i] = new FeePayment((Element)nodes.item(i));
	}
}
