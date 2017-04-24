package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class ContactPoint extends MISMODataAccessObject {
	public final ContactPointEmail contactPointEmail;
	public final ContactPointTelephone contactPointTelephone;

	public ContactPoint(Element element) {
		super(element);
		contactPointEmail = new ContactPointEmail((Element)getElementAddNS("CONTACT_POINT_EMAIL"));
		contactPointTelephone = new ContactPointTelephone((Element)getElementAddNS("CONTACT_POINT_TELEPHONE"));
	}
}
