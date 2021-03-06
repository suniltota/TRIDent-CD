package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents InterestRatePerChangeAdjustmentRule in MISMO XML
 * @author sboragala
 *
 */
public class InterestRatePerChangeAdjustmentRule extends MISMODataAccessObject {

	private static final long serialVersionUID = 2778534890325706867L;
	public final String adjustmentRuleType;
	public final String perChangeMaximumIncreaseRatePercent;
	public final String perChangeRateAdjustmentFrequencyMonthsCount;
	
	public InterestRatePerChangeAdjustmentRule(Element element) {
		super(element);
		adjustmentRuleType = getValueAddNS("AdjustmentRuleType");
		perChangeMaximumIncreaseRatePercent = getValueAddNS("PerChangeMaximumIncreaseRatePercent");
		perChangeRateAdjustmentFrequencyMonthsCount = getValueAddNS("PerChangeRateAdjustmentFrequencyMonthsCount");
	}
}
