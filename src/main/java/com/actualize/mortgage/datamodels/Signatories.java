package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents property detail in MISMO XML
 * @author sboragala
 *
 */
public class Signatories extends MISMODataAccessObject {
	private static final long serialVersionUID = -1202958440028776327L;

	public Signatories(String NS, Element element) {
		super(element);
		// TODO
	}
}
