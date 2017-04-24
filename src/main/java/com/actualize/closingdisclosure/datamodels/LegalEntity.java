package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class LegalEntity extends MISMODataAccessObject {
	public final LegalEntityDetail legalEntityDetail;

	public LegalEntity(Element element) {
		super(element);
		legalEntityDetail = new LegalEntityDetail((Element)getElementAddNS("LEGAL_ENTITY_DETAIL"));
	}
}
