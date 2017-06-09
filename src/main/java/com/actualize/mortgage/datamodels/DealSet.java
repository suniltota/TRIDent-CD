package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents DealSet in MISMO XML
 * @author sboragala
 *
 */
public class DealSet extends MISMODataAccessObject {
	private static final long serialVersionUID = -5274686397026093149L;
	public final Deals deal;

	public DealSet(String NS, Element element) {
		super(element);
		Element elem = getElement(element, NS + "DEALS");
		deal = elem == null ? null : new Deals(NS, elem);
	}
}
