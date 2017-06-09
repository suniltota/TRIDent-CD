package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents Escrow in MISMO XML
 * @author sboragala
 *
 */
public class Escrow extends MISMODataAccessObject {

	private static final long serialVersionUID = -5134841887854347079L;

	public Escrow(String NS, Element element) {
		super(element);
		// TODO
	}
}
