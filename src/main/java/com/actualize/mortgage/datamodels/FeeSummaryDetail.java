package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents FeeSummaryDetail in MISMO XML
 * @author sboragala
 *
 */
public class FeeSummaryDetail extends MISMODataAccessObject {

	private static final long serialVersionUID = -5010176223307363861L;
	public final String aprPercent;
	public final String feeSummaryTotalAmountFinancedAmount;
	public final String feeSummaryTotalFinanceChargeAmount;
	public final String feeSummaryTotalInterestPercent;
	public final String feeSummaryTotalOfAllPaymentsAmount;
	
	public FeeSummaryDetail(Element element) {
		super(element);
		aprPercent = getValueAddNS("APRPercent");
		feeSummaryTotalAmountFinancedAmount = getValueAddNS("FeeSummaryTotalAmountFinancedAmount");
		feeSummaryTotalFinanceChargeAmount = getValueAddNS("FeeSummaryTotalFinanceChargeAmount");
		feeSummaryTotalInterestPercent = getValueAddNS("FeeSummaryTotalInterestPercent");
		feeSummaryTotalOfAllPaymentsAmount = getValueAddNS("FeeSummaryTotalOfAllPaymentsAmount");
	}
}
