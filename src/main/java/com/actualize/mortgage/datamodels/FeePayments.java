package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents Fee Payments in MISMO XML
 * @author sboragala
 *
 */
public class FeePayments extends MISMODataAccessObject {

	private static final long serialVersionUID = -5867590665485863463L;
	public final FeePayment[] feePayments;

	public FeePayments(Element element) {
		super(element);
		NodeList nodes = getElementsAddNS("FEE_PAYMENT");
		feePayments = new FeePayment[nodes==null ? 0 : nodes.getLength()];
		for (int i = 0; i < feePayments.length; i++)
			feePayments[i] = new FeePayment((Element)nodes.item(i));
	}
}
