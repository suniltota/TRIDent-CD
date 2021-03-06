package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents Execution in MISMO XML
 * @author sboragala
 *
 */
public class Execution extends MISMODataAccessObject {
	
	private static final long serialVersionUID = -49702008261042029L;
	public final String actualSignatureType;
	public final String actualSignatureTypeOtherDescription;
	public final String executionDate;

	public Execution(String NS, Element element) {
		super(element);
		actualSignatureType = getValueAddNS("ActualSignatureType");
		actualSignatureTypeOtherDescription = getValueAddNS("ActualSignatureTypeOtherDescription");
		executionDate = getValueAddNS("ExecutionDate"); 
	}
}
