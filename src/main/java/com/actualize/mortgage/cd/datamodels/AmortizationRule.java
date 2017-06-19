package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * defines AmortizationRule in MISMO XML
 * @author sboragala
 *
 */
public class AmortizationRule extends MISMODataAccessObject {

	private static final long serialVersionUID = -1233053842692113481L;

	public final String amortizationType;
	public final String loanAmortizationPeriodCount;
	public final String loanAmortizationPeriodType;
	
	public AmortizationRule(Element element) {
		super(element);
		amortizationType = getValueAddNS("AmortizationType");
		loanAmortizationPeriodCount = getValueAddNS("LoanAmortizationPeriodCount");
		loanAmortizationPeriodType = getValueAddNS("LoanAmortizationPeriodType");
	}
}
