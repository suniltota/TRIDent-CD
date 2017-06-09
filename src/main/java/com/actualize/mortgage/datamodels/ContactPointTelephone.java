package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents ContactPointTelephone in MISMO XML
 * @author sboragala
 *
 */
public class ContactPointTelephone extends MISMODataAccessObject {

	private static final long serialVersionUID = 3642925203584486146L;
	public final String contactPointTelephoneValue;
	
	public ContactPointTelephone(Element element) {
		super(element);
		contactPointTelephoneValue = getValueAddNS("ContactPointTelephoneValue");
	}
}
