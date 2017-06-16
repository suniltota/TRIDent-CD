package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
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
