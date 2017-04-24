package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class DocumentSet extends MISMODataAccessObject {
    Documents documents;

	public DocumentSet(String NS, Element element) {
		super(element);
		Element elem;
		elem = getElement(element, NS + "DOCUMENTS");
		documents = elem == null ? null : new Documents(NS, elem);
	}
}
