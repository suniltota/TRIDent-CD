package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents EscrowItemPayments in MISMO XML
 * @author sboragala
 *
 */
public class EscrowItemPayments extends MISMODataAccessObject{

	private static final long serialVersionUID = -8715564388847020658L;
	public final EscrowItemPayment[] escrowItemPayment;
	public EscrowItemPayments(Element element) {
		super(element);
		// TODO
		NodeList nodes = getElementsAddNS((NS == null ? "" : NS)+"ESCROW_ITEM_PAYMENT");
		escrowItemPayment = new EscrowItemPayment[nodes==null ? 0 : nodes.getLength()];
		for (int i = 0; i < escrowItemPayment.length; i++)
			escrowItemPayment[i] = new EscrowItemPayment((Element)nodes.item(i));	
	}
}
