package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * defines Addresses in MISMO XML
 * @author sboragala
 *
 */
public class Addresses extends MISMODataAccessObject {

	private static final long serialVersionUID = -3825726982440999102L;
	public final Address[] addresses;

	public Addresses(Element element) {
		super(element);
		NodeList nodes = getElementsAddNS("ADDRESS");
		addresses = new Address[nodes==null ? 0 : nodes.getLength()];
		for (int i = 0; i < addresses.length; i++)
			addresses[i] = new Address((Element)nodes.item(i));
	}
}
