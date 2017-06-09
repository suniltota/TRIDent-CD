/**
 * 
 */
package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;

/**
 * represents ProrationItems in MISMO XML
 * @author sboragala
 *
 */
public class ProrationItems extends MISMODataAccessObject {
	private static final long serialVersionUID = -97346818218291257L;
	public final ProrationItem prorationItemList[];
	protected ProrationItems(Element e) {
		super(e);
		NodeList nodes = getElementsAddNS("PRORATION_ITEM");
		prorationItemList = new ProrationItem[nodes==null ? 0 : nodes.getLength()];
		for (int i = 0; i < prorationItemList.length; i++)
			prorationItemList[i] = new ProrationItem((Element)nodes.item(i));
	}

}
