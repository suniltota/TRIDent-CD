/**
 * 
 */
package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;

/**
 * defines MIPremiums in mismo xml
 * @author sboragala
 *
 */
public class MIPremiums extends MISMODataAccessObject {
	
	private static final long serialVersionUID = -1329804767620641002L;
	public final MIPremium[] miPremiumList;
	public MIPremiums(Element e) {
		super(e);
		NodeList nodes = getElementsAddNS("MI_PREMIUM");
		miPremiumList = new MIPremium[nodes==null ? 0 : nodes.getLength()];
		for (int i = 0; i < miPremiumList.length; i++)
			miPremiumList[i] = new MIPremium((Element)nodes.item(i));
	}

}
