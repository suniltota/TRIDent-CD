package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents DocumentClasses in MISMO XML
 * @author sboragala
 *
 */
public class DocumentClasses extends MISMODataAccessObject {
  
	private static final long serialVersionUID = -1516597567986955458L;
	public DocumentClass documentClass;
	
    public DocumentClasses(Element element) {
		super(element);
        documentClass = new DocumentClass((Element)getElementAddNS("DOCUMENT_CLASS"));
	}
}
