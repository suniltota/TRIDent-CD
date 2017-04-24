package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class PrepaidItemPaidTo extends MISMODataAccessObject{
	public final LegalEntity legalEntity;
	public PrepaidItemPaidTo(Element e) {
		super(e);
		legalEntity = new LegalEntity((Element)getElementAddNS("LEGAL_ENTITY"));
	}

}
