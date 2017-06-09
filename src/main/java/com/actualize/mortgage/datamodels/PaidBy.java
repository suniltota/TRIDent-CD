package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;

/**
 * represents PaidBy in MISMO XML
 * @author sboragala
 *
 */
public class PaidBy extends MISMODataAccessObject{
	
	private static final long serialVersionUID = -2566144068392700325L;
	public final Individual individual;
	public final LegalEntity legalEntity;

	protected PaidBy(Element e) {
		super(e);
		individual = new Individual((Element)getElementAddNS("INDIVIDUAL"));
		legalEntity = new LegalEntity((Element)getElementAddNS("LEGAL_ENTITY"));
	}

}
