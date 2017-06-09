package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents Lock in MISMO XML
 * @author sboragala
 *
 */
public class Lock extends MISMODataAccessObject {
	private static final long serialVersionUID = -7772437665729206243L;
	public final String LockExpirationDatetime;
	public final String LockStatusType;
	public final Extension extension;
	
	public Lock(Element element) {
		super(element);
		LockExpirationDatetime = getValueAddNS("LockExpirationDatetime");
		LockStatusType = getValueAddNS("LockStatusType");
		extension = new Extension((Element)getElementAddNS("EXTENSION"));
	}
}
