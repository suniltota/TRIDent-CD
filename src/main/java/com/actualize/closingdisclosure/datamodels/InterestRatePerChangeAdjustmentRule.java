package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class InterestRatePerChangeAdjustmentRule extends MISMODataAccessObject {
	public final String AdjustmentRuleType;
	public final String PerChangeMaximumIncreaseRatePercent;
	public final String PerChangeRateAdjustmentFrequencyMonthsCount;
	
	public InterestRatePerChangeAdjustmentRule(Element element) {
		super(element);
		AdjustmentRuleType = getValueAddNS("AdjustmentRuleType");
		PerChangeMaximumIncreaseRatePercent = getValueAddNS("PerChangeMaximumIncreaseRatePercent");
		PerChangeRateAdjustmentFrequencyMonthsCount = getValueAddNS("PerChangeRateAdjustmentFrequencyMonthsCount");
	}
}
