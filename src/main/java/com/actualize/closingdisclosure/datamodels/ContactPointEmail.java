package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class ContactPointEmail extends MISMODataAccessObject {
	public final String ContactPointEmailValue;
	
	public ContactPointEmail(Element element) {
		super(element);
		ContactPointEmailValue = getValueAddNS("ContactPointEmailValue");
	}
}
