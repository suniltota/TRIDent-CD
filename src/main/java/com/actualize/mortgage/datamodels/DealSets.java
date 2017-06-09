package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents DealSets in MISMO XML
 * @author sboragala
 *
 */
public class DealSets extends MISMODataAccessObject {

	private static final long serialVersionUID = -99177463733633677L;
	public final DealSet[] dealSet;

	public DealSets(String NS, Element element) {
		super(element);
		NodeList nodes = getElements(element, NS + "DEAL_SET");
		if (nodes.getLength() > 0) {
			dealSet = new DealSet[nodes.getLength()];
			for (int i = 0; i < nodes.getLength(); i++)
				dealSet[i] = new DealSet(NS, (Element)nodes.item(i));
		}
		else
			dealSet = null;
	}
}
