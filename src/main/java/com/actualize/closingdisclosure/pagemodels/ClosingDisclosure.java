/*
 * @(#)ClosingDisclosure.java 1.0 04/10/2017
 * 
 */

package com.actualize.closingdisclosure.pagemodels;

import java.io.Serializable;

/**
 * This class represents the all the pages present in Closing Disclosure
 * 
 * @author rsudula
 * @version 1.0
 * 
 */
public class ClosingDisclosure implements Serializable {

    private static final long serialVersionUID = 3808831045060865597L;
    private ClosingDisclosureDocumentDetails closingDisclosureDocumentDetails;
    private ClosingDisclosurePageOne closingDisclosurePageOne;
    private ClosingDisclosurePageTwo closingDisclosurePageTwo;
    private ClosingDisclosurePageThree closingDisclosurePageThree;
    private ClosingDisclosurePageFour closingDisclosurePageFour;
    private ClosingDisclosurePageFive closingDisclosurePageFive;
    
	/**
	 * @return the closingDisclosureDocumentDetails
	 */
	public ClosingDisclosureDocumentDetails getClosingDisclosureDocumentDetails() {
		return closingDisclosureDocumentDetails;
	}
	/**
	 * @param closingDisclosureDocumentDetails the closingDisclosureDocumentDetails to set
	 */
	public void setClosingDisclosureDocumentDetails(ClosingDisclosureDocumentDetails closingDisclosureDocumentDetails) {
		this.closingDisclosureDocumentDetails = closingDisclosureDocumentDetails;
	}
	/**
	 * @return the closingDisclosurePageOne
	 */
	public ClosingDisclosurePageOne getClosingDisclosurePageOne() {
		return closingDisclosurePageOne;
	}
	/**
	 * @param closingDisclosurePageOne the closingDisclosurePageOne to set
	 */
	public void setClosingDisclosurePageOne(ClosingDisclosurePageOne closingDisclosurePageOne) {
		this.closingDisclosurePageOne = closingDisclosurePageOne;
	}
	/**
	 * @return the closingDisclosurePageTwo
	 */
	public ClosingDisclosurePageTwo getClosingDisclosurePageTwo() {
		return closingDisclosurePageTwo;
	}
	/**
	 * @param closingDisclosurePageTwo the closingDisclosurePageTwo to set
	 */
	public void setClosingDisclosurePageTwo(ClosingDisclosurePageTwo closingDisclosurePageTwo) {
		this.closingDisclosurePageTwo = closingDisclosurePageTwo;
	}
	/**
	 * @return the closingDisclosurePageThree
	 */
	public ClosingDisclosurePageThree getClosingDisclosurePageThree() {
		return closingDisclosurePageThree;
	}
	/**
	 * @param closingDisclosurePageThree the closingDisclosurePageThree to set
	 */
	public void setClosingDisclosurePageThree(ClosingDisclosurePageThree closingDisclosurePageThree) {
		this.closingDisclosurePageThree = closingDisclosurePageThree;
	}
	/**
	 * @return the closingDisclosurePageFour
	 */
	public ClosingDisclosurePageFour getClosingDisclosurePageFour() {
		return closingDisclosurePageFour;
	}
	/**
	 * @param closingDisclosurePageFour the closingDisclosurePageFour to set
	 */
	public void setClosingDisclosurePageFour(ClosingDisclosurePageFour closingDisclosurePageFour) {
		this.closingDisclosurePageFour = closingDisclosurePageFour;
	}
	/**
	 * @return the closingDisclosurePageFive
	 */
	public ClosingDisclosurePageFive getClosingDisclosurePageFive() {
		return closingDisclosurePageFive;
	}
	/**
	 * @param closingDisclosurePageFive the closingDisclosurePageFive to set
	 */
	public void setClosingDisclosurePageFive(ClosingDisclosurePageFive closingDisclosurePageFive) {
		this.closingDisclosurePageFive = closingDisclosurePageFive;
	}

  
}
