package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents Interest Only in MISMO XML
 * @author sboragala
 *
 */
public class InterestOnly extends MISMODataAccessObject {

	private static final long serialVersionUID = -1141535787390039253L;
	public final String interestOnlyTermMonthsCount;
	
	public InterestOnly(Element element) {
		super(element);
		interestOnlyTermMonthsCount = getValueAddNS("InterestOnlyTermMonthsCount");
	}
}
