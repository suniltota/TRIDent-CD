package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents ContactPointEmail in MIMSO XML
 * @author sboragala
 *
 */
public class ContactPointEmail extends MISMODataAccessObject {

	private static final long serialVersionUID = -7947575048336497228L;
	public final String contactPointEmailValue;
	
	public ContactPointEmail(Element element) {
		super(element);
		contactPointEmailValue = getValueAddNS("ContactPointEmailValue");
	}
}
