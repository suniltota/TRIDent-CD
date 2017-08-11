package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents property detail in MISMO XML
 * @author sboragala
 *
 */
public class Signatories extends MISMODataAccessObject {
	
	private static final long serialVersionUID = -1202958440028776327L;

	public final Signatory[] signatoryList;
	
	public Signatories(String NS, Element element) {
		super(element);
		
		NodeList nodes = getElementsAddNS("SIGNATORY");
		signatoryList = new Signatory[nodes==null ? 0 : nodes.getLength()];
		for (int i = 0; i < signatoryList.length; i++)
			signatoryList[i] = new Signatory((Element)nodes.item(i));
	}
}
