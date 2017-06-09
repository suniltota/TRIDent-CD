package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents EscrowItem in MISMO XML
 * @author sboragala
 *
 */
public class EscrowItem extends MISMODataAccessObject {

	private static final long serialVersionUID = 1346112511605771617L;
	public final EscrowItemDetail escrowItemDetail;
	public final EscrowItemPayments escrowItemPayments;

	public EscrowItem(Element element) {
		super(element);
		escrowItemDetail = new EscrowItemDetail((Element)getElementAddNS("ESCROW_ITEM_DETAIL"));
		escrowItemPayments = new EscrowItemPayments((Element)getElementAddNS("ESCROW_ITEM_PAYMENTS"));
	}
}
