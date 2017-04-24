package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class UrlaDetail extends MISMODataAccessObject {
	public final String BorrowerRequestedLoanAmount;

	public UrlaDetail(Element element) {
		super(element);
		BorrowerRequestedLoanAmount = getValueAddNS("BorrowerRequestedLoanAmount");
	}
}
