package com.actualize.mortgage.cd.datamodels;

import java.io.Serializable;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * this class defines Foreclosures in MISMO XML
 * @author sboragala
 *
 */
public class Foreclosures extends MISMODataAccessObject implements Serializable{
	
	private static final long serialVersionUID = 56738486919283388L;
	public final String deficiencyRightsPreservedIndicator; 
	public Foreclosures(String NS, Element element) {
		super(element);
		deficiencyRightsPreservedIndicator = getValueAddNS("DeficiencyRightsPreservedIndicator");
	}
}
