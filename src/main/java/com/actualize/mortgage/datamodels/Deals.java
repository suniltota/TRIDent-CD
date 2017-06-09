package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents Deals in MISMO XML
 * @author sboragala
 *
 */
public class Deals extends MISMODataAccessObject {

	private static final long serialVersionUID = -5165345382646207835L;
	public final Deal[] deal;

	public Deals(String NS, Element element) {
		super(element);
		NodeList nodes = getElements(element, NS + "DEAL");
		if (nodes.getLength() > 0) {
			deal = new Deal[nodes.getLength()];
			for (int i = 0; i < nodes.getLength(); i++)
				deal[i] = new Deal(NS, (Element)nodes.item(i));
		}
		else
			deal = null;
	}
}
