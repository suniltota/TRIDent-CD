package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents Licenses in MISMO XML
 * @author sboragala
 *
 */
public class Name extends MISMODataAccessObject {

	private static final long serialVersionUID = -6462328350771528479L;
	public final String firstName;
	public final String fullName;
	public final String lastName;
	public final String middleName;
	public final String suffixName;
	
	public Name(Element element) {
		super(element);
		firstName = getValueAddNS("FirstName");
		fullName = getValueAddNS("FullName");
		lastName = getValueAddNS("LastName");
		middleName = getValueAddNS("MiddleName");
		suffixName = getValueAddNS("SuffixName");
	}
}
