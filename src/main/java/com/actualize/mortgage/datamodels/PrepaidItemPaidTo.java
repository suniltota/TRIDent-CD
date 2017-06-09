package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents PrepaidItemPaidTo in MISMO XML
 * @author sboragala
 *
 */
public class PrepaidItemPaidTo extends MISMODataAccessObject{
	private static final long serialVersionUID = 2420552855903172361L;
	public final LegalEntity legalEntity;
	public PrepaidItemPaidTo(Element e) {
		super(e);
		legalEntity = new LegalEntity((Element)getElementAddNS("LEGAL_ENTITY"));
	}

}
