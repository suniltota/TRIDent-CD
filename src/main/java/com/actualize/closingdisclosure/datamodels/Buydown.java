package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class Buydown extends MISMODataAccessObject {
	public final BuydownOccurences buydownOccurences;
	public final BuydownRule buydownRule;

	public Buydown(String NS, Element element) {
		super(element);
		buydownOccurences = new BuydownOccurences((Element)getElementAddNS("BUYDOWN_OCCURRENCES"));
		buydownRule = new BuydownRule((Element)getElementAddNS("BUYDOWN_RULE"));
	}
	public Buydown(Element element) {
		super(element);
		buydownOccurences = new BuydownOccurences((Element)getElementAddNS("BUYDOWN_OCCURRENCES"));
		buydownRule = new BuydownRule((Element)getElementAddNS("BUYDOWN_RULE"));
	}
}
