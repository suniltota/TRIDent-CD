package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class MaturityRule extends MISMODataAccessObject {
	public final String LoanMaturityPeriodCount;
	public final String LoanMaturityPeriodType;
	public final String LoanTermMaximumMonthsCount;

	public MaturityRule(Element element) {
		super(element);
		LoanMaturityPeriodCount = getValueAddNS("LoanMaturityPeriodCount");
		LoanMaturityPeriodType = getValueAddNS("LoanMaturityPeriodType");
		LoanTermMaximumMonthsCount = getValueAddNS("LoanTermMaximumMonthsCount");
	}
}
