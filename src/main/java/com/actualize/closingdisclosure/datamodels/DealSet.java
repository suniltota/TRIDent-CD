package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class DealSet extends MISMODataAccessObject {
	public final Deals deal;

	public DealSet(String NS, Element element) {
		super(element);
		Element elem = getElement(element, NS + "DEALS");
		deal = elem == null ? null : new Deals(NS, elem);
	}
}
