package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents QualifiedMortgage in MISMO XML
 * @author sboragala
 *
 */
public class QualifiedMortgage extends MISMODataAccessObject {
	
	private static final long serialVersionUID = 9080627343901954730L;
	
	public final QualifiedMortgageDetail qualifiedMortgageDetail;
	public final Exemption exemption;
	public QualifiedMortgage(String NS, Element element) {
		super(element);
		qualifiedMortgageDetail = new QualifiedMortgageDetail((Element)getElementAddNS("QUALIFIED_MORTGAGE_DETAIL"));
		exemption = new Exemption((Element)getElementAddNS("EXEMPTIONS/EXEMPTION"));
	}
}
