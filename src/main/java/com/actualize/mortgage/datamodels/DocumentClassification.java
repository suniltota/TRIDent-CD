package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents DocumentClassification in MISMO XML
 * @author sboragala
 *
 */
public class DocumentClassification extends MISMODataAccessObject {
	
	private static final long serialVersionUID = 6855391629579686499L;
	
	public DocumentClasses documentClasses;
    public DocumentClassificationDetail documentClassificationDetail;
    public DocumentClassification(String NS, Element element) {
		super(element);
		Element elem;
        elem = getElement(element, NS + "DOCUMENT_CLASSES");
        documentClasses = elem == null ? null : new DocumentClasses(elem);
        elem = getElement(element, NS + "DOCUMENT_CLASSIFICATION_DETAIL");
        documentClassificationDetail = elem == null ? null : new DocumentClassificationDetail(elem);
	}
}
