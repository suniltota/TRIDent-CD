package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class Individual extends MISMODataAccessObject {
	public final ContactPoints contactPoints;
	public final Name name;
	
	public Individual(Element element) {
		super(element);
		contactPoints = new ContactPoints((Element)getElementAddNS("CONTACT_POINTS"));
		name = new Name((Element)getElementAddNS("NAME"));
	}
}
