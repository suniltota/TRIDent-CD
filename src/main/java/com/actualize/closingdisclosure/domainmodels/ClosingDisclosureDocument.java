package com.actualize.closingdisclosure.domainmodels;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 *  This class defines the structure of closing disclosure document
 * @author sboragala
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class ClosingDisclosureDocument implements Serializable {
	
	private static final long serialVersionUID = 4671101480733733876L;
	
	private DocumentDetails documentDetails;
	private PageOne pageOne;
	private PageTwo pageTwo;
	private PageThree pageThree;
	
	/**
	 * @return the documentDetails
	 */
	public DocumentDetails getDocumentDetails() {
		return documentDetails;
	}
	/**
	 * @param documentDetails the documentDetails to set
	 */
	public void setDocumentDetails(DocumentDetails documentDetails) {
		this.documentDetails = documentDetails;
	}
	/**
	 * @return the pageOne
	 */
	public PageOne getPageOne() {
		return pageOne;
	}
	/**
	 * @param pageOne the pageOne to set
	 */
	public void setPageOne(PageOne pageOne) {
		this.pageOne = pageOne;
	}
	/**
	 * @return the pageTwo
	 */
	public PageTwo getPageTwo() {
		return pageTwo;
	}
	/**
	 * @param pageTwo the pageTwo to set
	 */
	public void setPageTwo(PageTwo pageTwo) {
		this.pageTwo = pageTwo;
	}
	/**
	 * @return the pageThree
	 */
	public PageThree getPageThree() {
		return pageThree;
	}
	/**
	 * @param pageThree the pageThree to set
	 */
	public void setPageThree(PageThree pageThree) {
		this.pageThree = pageThree;
	}

	
}
