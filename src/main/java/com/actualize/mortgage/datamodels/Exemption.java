/**
 * 
 */
package com.actualize.mortgage.datamodels;

import java.io.Serializable;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;

/**
 * represents Exemption in MISMO XML
 * @author sboragala
 *
 */
public class Exemption extends MISMODataAccessObject implements Serializable {

	private static final long serialVersionUID = 3084196305020133649L;

	public final String abilityToRepayExemptionReasonType;
	protected Exemption(Element e) {
		super(e);
		abilityToRepayExemptionReasonType = getValueAddNS("AbilityToRepayExemptionReasonType");
	}

}
