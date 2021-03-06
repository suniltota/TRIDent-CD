package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;

/**
 * this class defines BuydownOccurence in MISMO XML
 * @author sboragala
 *
 */
public class BuydownOccurence extends MISMODataAccessObject {
	
	private static final long serialVersionUID = -1384468981198064634L;
	public final String buydownInitialEffectiveInterestRatePercent;
	
	public BuydownOccurence(Element element) {
		super(element);
		buydownInitialEffectiveInterestRatePercent = getValueAddNS("BuydownInitialEffectiveInterestRatePercent");
	}
}
