package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents Refinance in MISMO XML
 * @author sboragala
 *
 */
public class Refinance extends MISMODataAccessObject {
	private static final long serialVersionUID = 421393109165643968L;
	public final String refinanceSameLenderIndicator;
	
	public Refinance(String NS, Element element) {
		super(element);
		refinanceSameLenderIndicator = getValueAddNS("RefinanceSameLenderIndicator");
	}
}
