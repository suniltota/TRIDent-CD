package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents LegalEntityDetail in MISMO XML
 * @author sboragala
 *
 */
public class LegalEntityDetail extends MISMODataAccessObject {
	private static final long serialVersionUID = -1262754475796066376L;
	public final String fullName;
	
	public LegalEntityDetail(Element element) {
		super(element);
		fullName = getValueAddNS("FullName");
	}
}
