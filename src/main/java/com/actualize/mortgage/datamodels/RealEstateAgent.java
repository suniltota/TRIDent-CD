package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents RealEstateAgent in MISMO XML
 * @author sboragala
 *
 */
public class RealEstateAgent extends MISMODataAccessObject {
	private static final long serialVersionUID = 3571813872954668620L;
	public final String realEstateAgentType;
	
	public RealEstateAgent(Element element) {
		super(element);
		realEstateAgentType = getValueAddNS("RealEstateAgentType");
	}
}
