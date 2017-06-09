package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents FeePaidTo in MISMO XML
 * @author sboragala
 *
 */
public class FeePaidTo extends MISMODataAccessObject {
	
	private static final long serialVersionUID = -7221845896478666684L;
	public LegalEntity legalEntity;

	public FeePaidTo(Element element) {
		super(element);
		legalEntity = new LegalEntity((Element)getElementAddNS("LEGAL_ENTITY"));
	}
}
