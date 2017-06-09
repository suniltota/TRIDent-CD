package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents PrepaymentPenalty in MISMO XML
 * @author sboragala
 *
 */
public class PrepaymentPenalty extends MISMODataAccessObject {
	private static final long serialVersionUID = 4363333944022406419L;

	public PrepaymentPenalty(String NS, Element element) {
		super(element);
		// TODO
	}
}
