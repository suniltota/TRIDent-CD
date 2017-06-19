package com.actualize.mortgage.cd.domainmodels;

import java.io.Serializable;
import java.util.List;
/**
 * defines loan information in JSON response
 * @author sboragala
 *
 */
public class LoanInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6382855685415511549L;
	
	private String amortizationType;
	private String loanAmortizationPeriodCount;
	private String loanAmortizationPeriodType;
	private List<LoanInformationLoanIdentifier> loanIdentifiers;
	private List<AutomatedUnderwritingsModel> automatedUnderwritings;
	private boolean loanManualUnderwritingIndicator;
	
	/**
	 * @return the amortizationType
	 */
	public String getAmortizationType() {
		return amortizationType;
	}
	/**
	 * @return the loanAmortizationPeriodCount
	 */
	public String getLoanAmortizationPeriodCount() {
		return loanAmortizationPeriodCount;
	}
	/**
	 * @param loanAmortizationPeriodCount the loanAmortizationPeriodCount to set
	 */
	public void setLoanAmortizationPeriodCount(String loanAmortizationPeriodCount) {
		this.loanAmortizationPeriodCount = loanAmortizationPeriodCount;
	}
	/**
	 * @return the loanAmortizationPeriodType
	 */
	public String getLoanAmortizationPeriodType() {
		return loanAmortizationPeriodType;
	}
	/**
	 * @param loanAmortizationPeriodType the loanAmortizationPeriodType to set
	 */
	public void setLoanAmortizationPeriodType(String loanAmortizationPeriodType) {
		this.loanAmortizationPeriodType = loanAmortizationPeriodType;
	}
	/**
	 * @param amortizationType the amortizationType to set
	 */
	public void setAmortizationType(String amortizationType) {
		this.amortizationType = amortizationType;
	}
	/**
	 * @return the loanIdentifiers
	 */
	public List<LoanInformationLoanIdentifier> getLoanIdentifiers() {
		return loanIdentifiers;
	}
	/**
	 * @param loanIdentifiers the loanIdentifiers to set
	 */
	public void setLoanIdentifiers(List<LoanInformationLoanIdentifier> loanIdentifiers) {
		this.loanIdentifiers = loanIdentifiers;
	}
	/**
	 * @return the automatedUnderwritings
	 */
	public List<AutomatedUnderwritingsModel> getAutomatedUnderwritings() {
		return automatedUnderwritings;
	}
	/**
	 * @param automatedUnderwritings the automatedUnderwritings to set
	 */
	public void setAutomatedUnderwritings(List<AutomatedUnderwritingsModel> automatedUnderwritings) {
		this.automatedUnderwritings = automatedUnderwritings;
	}
	/**
	 * @return the loanManualUnderwritingIndicator
	 */
	public boolean isLoanManualUnderwritingIndicator() {
		return loanManualUnderwritingIndicator;
	}
	/**
	 * @param loanManualUnderwritingIndicator the loanManualUnderwritingIndicator to set
	 */
	public void setLoanManualUnderwritingIndicator(boolean loanManualUnderwritingIndicator) {
		this.loanManualUnderwritingIndicator = loanManualUnderwritingIndicator;
	}
}
