package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * defines DocumentClass in MISMO XML
 * @author sboragala
 *
 */
public class DocumentClass extends MISMODataAccessObject {

	private static final long serialVersionUID = 3162287825746877294L;
	
	public final String documentType;
    public final String documentTypeOtherDescription;
    
	public DocumentClass(Element element) {
		super(element);
		documentType = getValueAddNS("DocumentType");
		documentTypeOtherDescription = getValueAddNS("DocumentTypeOtherDescription");
	}
}
