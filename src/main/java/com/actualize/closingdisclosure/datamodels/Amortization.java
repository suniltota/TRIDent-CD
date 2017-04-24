package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;
/**
 * Defines Amortization element from XML
 * @author sboragala
 *
 */
public class Amortization extends MISMODataAccessObject {
	public final AmortizationRule amortizationRule;

	public Amortization(Element element) {
		super(element);
		amortizationRule = new AmortizationRule((Element)getElementAddNS("AMORTIZATION_RULE"));
	}
}
