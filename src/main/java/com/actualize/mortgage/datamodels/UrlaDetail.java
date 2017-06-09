package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents UrlaDetail in MISMO XML
 * @author sboragala
 *
 */
public class UrlaDetail extends MISMODataAccessObject {
	public final String BorrowerRequestedLoanAmount;

	public UrlaDetail(Element element) {
		super(element);
		BorrowerRequestedLoanAmount = getValueAddNS("BorrowerRequestedLoanAmount");
	}
}
