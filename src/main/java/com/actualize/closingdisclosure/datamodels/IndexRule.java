package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class IndexRule extends MISMODataAccessObject {
	public final String IndexType;
	public final String IndexTypeOtherDescription;
	
	public IndexRule(Element element) {
		super(element);
		IndexType = getValueAddNS("IndexType");
		IndexTypeOtherDescription = getValueAddNS("IndexTypeOtherDescription");
	}
}
