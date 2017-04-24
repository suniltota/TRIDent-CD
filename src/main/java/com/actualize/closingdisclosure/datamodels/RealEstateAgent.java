package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class RealEstateAgent extends MISMODataAccessObject {
	public final String RealEstateAgentType;
	
	public RealEstateAgent(Element element) {
		super(element);
		RealEstateAgentType = getValueAddNS("RealEstateAgentType");
	}
}
