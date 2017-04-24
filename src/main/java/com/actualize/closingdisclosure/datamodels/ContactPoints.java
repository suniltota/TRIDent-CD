package com.actualize.closingdisclosure.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.closingdisclosure.domainmodels.MISMODataAccessObject;

public class ContactPoints extends MISMODataAccessObject {
	public final ContactPoint[] contactPoints;
	
	public ContactPoints(Element element) {
		super(element);
		NodeList nodes = getElementsAddNS("CONTACT_POINT");
		contactPoints = new ContactPoint[nodes==null ? 0 : nodes.getLength()];
		for (int i = 0; i < contactPoints.length; i++)
			contactPoints[i] = new ContactPoint((Element)nodes.item(i));
	}
}
