package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents EscrowItemPayments in MISMO XML
 * @author sboragala
 *
 */
public class EscrowItemPayment extends MISMODataAccessObject{

	private static final long serialVersionUID = -282067693601085765L;
	public final String escrowItemActualPaymentAmount;
	public final String escrowItemPaymentPaidByType; 
	public final String escrowItemPaymentTimingType;
	
	protected EscrowItemPayment(Element element) {
		super(element);
		escrowItemActualPaymentAmount = getValueAddNS("EscrowItemActualPaymentAmount");
		escrowItemPaymentPaidByType   = getValueAddNS("EscrowItemPaymentPaidByType"); 
		escrowItemPaymentTimingType  = getValueAddNS("EscrowItemPaymentTimingType");
	}
}
