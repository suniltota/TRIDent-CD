/**
 * 
 */
package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;

/**
 * represents Liability in MISMO XML
 * @author sboragala
 *
 */
public class Liability extends MISMODataAccessObject {
	private static final long serialVersionUID = 6595013823160030483L;
	public final LiabilityDetail liabilityDetail;
	public final Name liabilityholderName;
	public final PayOff payOff;
	protected Liability(Element e) {
		super(e);
		liabilityDetail = new LiabilityDetail((Element)getElementAddNS("LIABILITY_DETAIL"));
		liabilityholderName = new Name((Element)getElementAddNS("LIABILITY_HOLDER/NAME"));
		payOff = new PayOff((Element)getElementAddNS("PAYOFF"));
	}

}
