package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents PayOff in MISMO XML
 * @author sboragala
 *
 */
public class PayOff extends MISMODataAccessObject{
	
	private static final long serialVersionUID = 2653595809218653567L;
	public final String payoffAmount;
	public final String payoffPrepaymentPenaltyAmount;
	public final String payoffPartialIndicator;
	protected PayOff(Element e) {
		super(e);
		payoffAmount = getValueAddNS("PayoffAmount");
		payoffPrepaymentPenaltyAmount = getValueAddNS("PayoffPrepaymentPenaltyAmount");
		payoffPartialIndicator = getValueAddNS("EXTENSION/MISMO/PayoffPartialIndicator");
	}
	
}
