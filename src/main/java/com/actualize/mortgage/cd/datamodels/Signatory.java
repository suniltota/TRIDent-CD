/**
 * 
 */
package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;

/**
 * @author sboragala
 *
 */
public class Signatory extends MISMODataAccessObject{
	
	private static final long serialVersionUID = 4135802190565802265L;
	public final String sequenceNumber;
	public final String xLinkLabel;
	public final Execution execution;

	protected Signatory(Element e) {
		super(e);
		sequenceNumber = getAttributeValue("SequenceNumber");
		xLinkLabel = getAttributeValue("xlink:label");
		execution = new Execution("", (Element)getElementAddNS("EXECUTION/EXECUTION_DETAIL"));
	}

}
