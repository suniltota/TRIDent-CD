package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents InterestRatePerChangeAdjustmentRules in MISMO XML
 * @author sboragala
 *
 */
public class InterestRatePerChangeAdjustmentRules extends MISMODataAccessObject {

	private static final long serialVersionUID = 7996361564681952053L;
	public final InterestRatePerChangeAdjustmentRule[] interestRatePerChangeAdjustmentRules;

	public InterestRatePerChangeAdjustmentRules(Element element) {
		super(element);
		NodeList nodes = getElementsAddNS("INTEREST_RATE_PER_CHANGE_ADJUSTMENT_RULE");
		interestRatePerChangeAdjustmentRules = new InterestRatePerChangeAdjustmentRule[nodes==null ? 0 : nodes.getLength()];
		for (int i = 0; i < interestRatePerChangeAdjustmentRules.length; i++)
			interestRatePerChangeAdjustmentRules[i] = new InterestRatePerChangeAdjustmentRule((Element)nodes.item(i));
	}
}
