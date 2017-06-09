package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents DocumentSet in MISMO XML
 * @author sboragala
 *
 */
public class DocumentSet extends MISMODataAccessObject {

	private static final long serialVersionUID = -5336373567566941758L;
	Documents documents;

	public DocumentSet(String NS, Element element) {
		super(element);
		Element elem;
		elem = getElement(element, NS + "DOCUMENTS");
		documents = elem == null ? null : new Documents(NS, elem);
	}
}
