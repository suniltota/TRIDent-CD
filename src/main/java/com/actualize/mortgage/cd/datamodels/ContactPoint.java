package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * defines ContactPoint in MISMO XML
 * @author sboragala
 *
 */
public class ContactPoint extends MISMODataAccessObject {

	private static final long serialVersionUID = -5078550419288028441L;
	public final ContactPointEmail contactPointEmail;
	public final ContactPointTelephone contactPointTelephone;

	public ContactPoint(Element element) {
		super(element);
		contactPointEmail = new ContactPointEmail((Element)getElementAddNS("CONTACT_POINT_EMAIL"));
		contactPointTelephone = new ContactPointTelephone((Element)getElementAddNS("CONTACT_POINT_TELEPHONE"));
	}
}
