package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents Licenses in MISMO XML
 * @author sboragala
 *
 */
public class Licenses extends MISMODataAccessObject {
	public License[] licenses;

	public Licenses(Element element) {
		super(element);
		NodeList nodes = getElementsAddNS("LICENSE");
		licenses = new License[nodes==null ? 0 : nodes.getLength()];
		for (int i = 0; i < licenses.length; i++)
			licenses[i] = new License((Element)nodes.item(i));
	}
}
