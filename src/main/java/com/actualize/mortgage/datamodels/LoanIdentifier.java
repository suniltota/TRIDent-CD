package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents LoanIdentifier in MISMO XML
 * @author sboragala
 *
 */
public class LoanIdentifier extends MISMODataAccessObject {

	private static final long serialVersionUID = 4127997778226169527L;
	public final String LoanIdentifier;
	public final String LoanIdentifierType;
	
	public LoanIdentifier(Element element) {
		super(element);
		LoanIdentifier = getValueAddNS("LoanIdentifier");
		LoanIdentifierType = getValueAddNS("LoanIdentifierType");
	}
}
