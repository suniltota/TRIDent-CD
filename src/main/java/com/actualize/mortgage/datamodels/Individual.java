package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents Individual in MISMO XML
 * @author sboragala
 *
 */
public class Individual extends MISMODataAccessObject {

	private static final long serialVersionUID = 8281142160975846811L;
	public final ContactPoints contactPoints;
	public final Name name;
	
	public Individual(Element element) {
		super(element);
		contactPoints = new ContactPoints((Element)getElementAddNS("CONTACT_POINTS"));
		name = new Name((Element)getElementAddNS("NAME"));
	}
}
