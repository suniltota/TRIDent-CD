/**
 * @(#)ClosingDisclosureDocumentType.java 1.0 04/11/2017
 */
package com.actualize.mortgage.cd.domainmodels;

import java.io.Serializable;

/**
 * This class is used to represent document level variables to quickly verify across all the pages.
 * @author rsudula
 * version : 1.0
 *
 */
public class ClosingDisclosureDocumentDetails implements Serializable {

    private static final long serialVersionUID = 1957140105029843535L;

    private String transactionType = "";
    private String documentType = "";
    private String formType = "";
    private String actualSignatureType = "";
    private String actualSignatureTypeOtherDescription = "";
    private String executionDate = "";
    private boolean documentSignatureRequiredIndicator = false;
    private String escrowAggregateAccountingAdjustmentAmount = "";
    private String escrowAggregateAccountingAdjustmentAmountSellerPaid = "";
    private String escrowAggregateAccountingAdjustmentAmountOthersPaid = "";
    
	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}
	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	/**
	 * @return the documentType
	 */
	public String getDocumentType() {
		return documentType;
	}
	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	/**
	 * @return the formType
	 */
	public String getFormType() {
		return formType;
	}
	/**
	 * @param formType the formType to set
	 */
	public void setFormType(String formType) {
		this.formType = formType;
	}
	/**
	 * @return the actualSignatureType
	 */
	public String getActualSignatureType() {
		return actualSignatureType;
	}
	/**
	 * @param actualSignatureType the actualSignatureType to set
	 */
	public void setActualSignatureType(String actualSignatureType) {
		this.actualSignatureType = actualSignatureType;
	}
	/**
	 * @return the actualSignatureTypeOtherDescription
	 */
	public String getActualSignatureTypeOtherDescription() {
		return actualSignatureTypeOtherDescription;
	}
	/**
	 * @param actualSignatureTypeOtherDescription the actualSignatureTypeOtherDescription to set
	 */
	public void setActualSignatureTypeOtherDescription(String actualSignatureTypeOtherDescription) {
		this.actualSignatureTypeOtherDescription = actualSignatureTypeOtherDescription;
	}
	/**
	 * @return the executionDate
	 */
	public String getExecutionDate() {
		return executionDate;
	}
	/**
	 * @param executionDate the executionDate to set
	 */
	public void setExecutionDate(String executionDate) {
		this.executionDate = executionDate;
	}
	/**
	 * @return the documentSignatureRequiredIndicator
	 */
	public boolean isDocumentSignatureRequiredIndicator() {
		return documentSignatureRequiredIndicator;
	}
	/**
	 * @param documentSignatureRequiredIndicator the documentSignatureRequiredIndicator to set
	 */
	public void setDocumentSignatureRequiredIndicator(boolean documentSignatureRequiredIndicator) {
		this.documentSignatureRequiredIndicator = documentSignatureRequiredIndicator;
	}
	/**
	 * @return the escrowAggregateAccountingAdjustmentAmount
	 */
	public String getEscrowAggregateAccountingAdjustmentAmount() {
		return escrowAggregateAccountingAdjustmentAmount;
	}
	/**
	 * @param escrowAggregateAccountingAdjustmentAmount the escrowAggregateAccountingAdjustmentAmount to set
	 */
	public void setEscrowAggregateAccountingAdjustmentAmount(String escrowAggregateAccountingAdjustmentAmount) {
		this.escrowAggregateAccountingAdjustmentAmount = escrowAggregateAccountingAdjustmentAmount;
	}
	/**
	 * @return the escrowAggregateAccountingAdjustmentAmountSellerPaid
	 */
	public String getEscrowAggregateAccountingAdjustmentAmountSellerPaid() {
		return escrowAggregateAccountingAdjustmentAmountSellerPaid;
	}
	/**
	 * @param escrowAggregateAccountingAdjustmentAmountSellerPaid the escrowAggregateAccountingAdjustmentAmountSellerPaid to set
	 */
	public void setEscrowAggregateAccountingAdjustmentAmountSellerPaid(
			String escrowAggregateAccountingAdjustmentAmountSellerPaid) {
		this.escrowAggregateAccountingAdjustmentAmountSellerPaid = escrowAggregateAccountingAdjustmentAmountSellerPaid;
	}
	/**
	 * @return the escrowAggregateAccountingAdjustmentAmountOthersPaid
	 */
	public String getEscrowAggregateAccountingAdjustmentAmountOthersPaid() {
		return escrowAggregateAccountingAdjustmentAmountOthersPaid;
	}
	/**
	 * @param escrowAggregateAccountingAdjustmentAmountOthersPaid the escrowAggregateAccountingAdjustmentAmountOthersPaid to set
	 */
	public void setEscrowAggregateAccountingAdjustmentAmountOthersPaid(
			String escrowAggregateAccountingAdjustmentAmountOthersPaid) {
		this.escrowAggregateAccountingAdjustmentAmountOthersPaid = escrowAggregateAccountingAdjustmentAmountOthersPaid;
	}
    
	
}
