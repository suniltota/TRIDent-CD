package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents PrepaidItem in MISMO XML
 * @author sboragala
 *
 */
public class PrepaidItem extends MISMODataAccessObject {
	private static final long serialVersionUID = 1154511380339952456L;
	public final PrepaidItemDetail prepaidItemDetail;
	public final PrepaidItemPayments prepaidItemPayments;
	public final PrepaidItemPaidTo prepaidItemPaidTo;
	public PrepaidItem(Element element) {
		super(element);
		prepaidItemDetail = new PrepaidItemDetail((Element)getElementAddNS("PREPAID_ITEM_DETAIL"));
		prepaidItemPayments = new PrepaidItemPayments((Element)getElementAddNS("PREPAID_ITEM_PAYMENTS"));
		prepaidItemPaidTo = new PrepaidItemPaidTo((Element)getElementAddNS("PREPAID_ITEM_PAID_TO"));
	}
}
