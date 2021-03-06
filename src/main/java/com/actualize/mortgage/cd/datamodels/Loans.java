package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;

public class Loans extends MISMODataAccessObject {
	private static final long serialVersionUID = 4490808321727399662L;
	public final Loan[] loans;

	public Loans(String NS, Element element) {
		super(element);
		NodeList nodes = getElementsAddNS("LOAN");
		loans = new Loan[nodes==null ? 0 : nodes.getLength()];
		for (int i = 0; i < loans.length; i++)
			loans[i] = new Loan((Element)nodes.item(i));
	}
}
