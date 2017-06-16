package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents LegalEntity in MISMO XML
 * @author sboragala
 *
 */
public class LegalEntity extends MISMODataAccessObject {
	private static final long serialVersionUID = 7221939983539931365L;
	public final LegalEntityDetail legalEntityDetail;

	public LegalEntity(Element element) {
		super(element);
		legalEntityDetail = new LegalEntityDetail((Element)getElementAddNS("LEGAL_ENTITY_DETAIL"));
	}
}
