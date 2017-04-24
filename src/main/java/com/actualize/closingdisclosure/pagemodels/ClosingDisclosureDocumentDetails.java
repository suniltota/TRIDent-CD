package com.actualize.closingdisclosure.pagemodels;
/**
 * This class is used to represent document level variables to quickly verify across all the pages.
 * @author sboragala
 *
 */
public class ClosingDisclosureDocumentDetails {
	
	private static String loanType;
	private static boolean standardView;
	private static boolean alternateView;
	private static boolean payoffsAndPayments = false;
	private static boolean refinanceTypeLoan;
	private static boolean homeEquityLoanIndicator;
	private static boolean sellerOnly;
	private static String aboutVersionIdentifier;
	private static String loanId;
	
	/**
	 * @return the loanType
	 */
	public static String getLoanType() {
		return loanType;
	}
	/**
	 * @param loanType the loanType to set
	 */
	public static void setLoanType(String loanType) {
		ClosingDisclosureDocumentDetails.loanType = loanType;
	}
	/**
	 * @return the standardView
	 */
	public static boolean isStandardView() {
		return standardView;
	}
	/**
	 * @param standardView the standardView to set
	 */
	public static void setStandardView(boolean standardView) {
		ClosingDisclosureDocumentDetails.standardView = standardView;
	}
	/**
	 * @return the alternateView
	 */
	public static boolean isAlternateView() {
		return alternateView;
	}
	/**
	 * @param alternateView the alternateView to set
	 */
	public static void setAlternateView(boolean alternateView) {
		ClosingDisclosureDocumentDetails.alternateView = alternateView;
	}
	/**
	 * @return the payoffsAndPayments
	 */
	public static boolean isPayoffsAndPayments() {
		return payoffsAndPayments;
	}
	/**
	 * @param payoffsAndPayments the payoffsAndPayments to set
	 */
	public static void setPayoffsAndPayments(boolean payoffsAndPayments) {
		ClosingDisclosureDocumentDetails.payoffsAndPayments = payoffsAndPayments;
	}
	/**
	 * @return the refinanceTypeLoan
	 */
	public static boolean isRefinanceTypeLoan() {
		return refinanceTypeLoan;
	}
	/**
	 * @param refinanceTypeLoan the refinanceTypeLoan to set
	 */
	public static void setRefinanceTypeLoan(boolean refinanceTypeLoan) {
		ClosingDisclosureDocumentDetails.refinanceTypeLoan = refinanceTypeLoan;
	}
	/**
	 * @return the homeEquityLoanIndicator
	 */
	public static boolean isHomeEquityLoanIndicator() {
		return homeEquityLoanIndicator;
	}
	/**
	 * @param homeEquityLoanIndicator the homeEquityLoanIndicator to set
	 */
	public static void setHomeEquityLoanIndicator(boolean homeEquityLoanIndicator) {
		ClosingDisclosureDocumentDetails.homeEquityLoanIndicator = homeEquityLoanIndicator;
	}
	/**
	 * @return the sellerOnly
	 */
	public static boolean isSellerOnly() {
		return sellerOnly;
	}
	/**
	 * @param sellerOnly the sellerOnly to set
	 */
	public static void setSellerOnly(boolean sellerOnly) {
		ClosingDisclosureDocumentDetails.sellerOnly = sellerOnly;
	}
	/**
	 * @return the aboutVersionIdentifier
	 */
	public static String getAboutVersionIdentifier() {
		return aboutVersionIdentifier;
	}
	/**
	 * @param aboutVersionIdentifier the aboutVersionIdentifier to set
	 */
	public static void setAboutVersionIdentifier(String aboutVersionIdentifier) {
		ClosingDisclosureDocumentDetails.aboutVersionIdentifier = aboutVersionIdentifier;
	}
	/**
	 * @return the loanId
	 */
	public static String getLoanId() {
		return loanId;
	}
	/**
	 * @param loanId the loanId to set
	 */
	public static void setLoanId(String loanId) {
		ClosingDisclosureDocumentDetails.loanId = loanId;
	}
	
	

}