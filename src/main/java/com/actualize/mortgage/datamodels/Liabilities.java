package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents Liabilities in MISMO XML
 * @author sboragala
 *
 */
public class Liabilities extends MISMODataAccessObject {

	private static final long serialVersionUID = -2145082494968420099L;
	public final Liability[] liabilityList;

	public Liabilities(String NS, Element element) {
		super(element);
		NodeList nodes = getElementsAddNS("LIABILITY");
		liabilityList = new Liability[nodes==null ? 0 : nodes.getLength()];
		for (int i = 0; i < liabilityList.length; i++)
			liabilityList[i] = new Liability((Element)nodes.item(i));
	}
}
