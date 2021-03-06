/**
 * 
 */
package com.actualize.mortgage.cd.datamodels;

import java.io.Serializable;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;

/**
 * represents QualifiedMortgageDetail in MISMO XML
 * @author sboragala
 *
 */
public class QualifiedMortgageDetail extends MISMODataAccessObject implements Serializable {

	private static final long serialVersionUID = 7501359052664764743L;
	
	public final String abilityToRepayMethodType;
	protected QualifiedMortgageDetail(Element e) {
		super(e);
		abilityToRepayMethodType =  getValueAddNS("AbilityToRepayMethodType");
	}

}
