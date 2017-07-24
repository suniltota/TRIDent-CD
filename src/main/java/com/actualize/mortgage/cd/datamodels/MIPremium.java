/**
 * 
 */
package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;

/**
 * defines mipremium in mismo xml
 * @author sboragala
 *
 */
public class MIPremium extends MISMODataAccessObject {
	
	private static final long serialVersionUID = 9148811878736446133L;
	public MIPremiumDetail miPremiumDetail;

	public MIPremium(Element e) {
		super(e);
		miPremiumDetail = new MIPremiumDetail((Element)getElementAddNS("MI_PREMIUM_DETAIL"));
	}
	

}
