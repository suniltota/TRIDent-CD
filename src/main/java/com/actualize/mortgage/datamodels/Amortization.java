package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * Defines Amortization element from XML
 * @author sboragala
 *
 */
public class Amortization extends MISMODataAccessObject {

	private static final long serialVersionUID = -8669018601747202023L;
	public final AmortizationRule amortizationRule;

	public Amortization(Element element) {
		super(element);
		amortizationRule = new AmortizationRule((Element)getElementAddNS("AMORTIZATION_RULE"));
	}
}
