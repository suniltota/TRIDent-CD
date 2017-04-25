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
	
	private ClosingInformation closingInformation;
    private TransactionInformation transactionInformation;
    private LoanInformation loanInformation;
    private LoanTerms loanTerms;
    private ProjectedPaymentsModel projectedPayments;
    private CostsAtClosing costsAtClosing;
	
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
	 * @return the closingInformation
	 */
	public ClosingInformation getClosingInformation() {
		return closingInformation;
	}
	/**
	 * @param closingInformation the closingInformation to set
	 */
	public void setClosingInformation(ClosingInformation closingInformation) {
		this.closingInformation = closingInformation;
	}
	/**
	 * @return the transactionInformation
	 */
	public TransactionInformation getTransactionInformation() {
		return transactionInformation;
	}
	/**
	 * @param transactionInformation the transactionInformation to set
	 */
	public void setTransactionInformation(TransactionInformation transactionInformation) {
		this.transactionInformation = transactionInformation;
	}
	/**
	 * @return the loanInformation
	 */
	public LoanInformation getLoanInformation() {
		return loanInformation;
	}
	/**
	 * @param loanInformation the loanInformation to set
	 */
	public void setLoanInformation(LoanInformation loanInformation) {
		this.loanInformation = loanInformation;
	}
	/**
	 * @return the loanTerms
	 */
	public LoanTerms getLoanTerms() {
		return loanTerms;
	}
	/**
	 * @param loanTerms the loanTerms to set
	 */
	public void setLoanTerms(LoanTerms loanTerms) {
		this.loanTerms = loanTerms;
	}
	/**
	 * @return the projectedPayments
	 */
	public ProjectedPaymentsModel getProjectedPayments() {
		return projectedPayments;
	}
	/**
	 * @param projectedPayments the projectedPayments to set
	 */
	public void setProjectedPayments(ProjectedPaymentsModel projectedPayments) {
		this.projectedPayments = projectedPayments;
	}
	/**
	 * @return the costsAtClosing
	 */
	public CostsAtClosing getCostsAtClosing() {
		return costsAtClosing;
	}
	/**
	 * @param costsAtClosing the costsAtClosing to set
	 */
	public void setCostsAtClosing(CostsAtClosing costsAtClosing) {
		this.costsAtClosing = costsAtClosing;
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
