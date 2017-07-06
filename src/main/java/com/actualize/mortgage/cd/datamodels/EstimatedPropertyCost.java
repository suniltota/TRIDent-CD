package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents EscrowItemPayments in MISMO XML
 * @author sboragala
 *
 */
public class EstimatedPropertyCost extends MISMODataAccessObject {

	private static final long serialVersionUID = 9146288208554322644L;
	public String displayLabelText = "";
	public final EstimatedPropertyCostComponents estimatedPropertyCostComponents;
	public final EstimatedPropertyCostDetail estimatedPropertyCostDetail;

	public EstimatedPropertyCost(Element element) {
		super(element);
		estimatedPropertyCostComponents = new EstimatedPropertyCostComponents((Element)getElementAddNS("ESTIMATED_PROPERTY_COST_COMPONENTS"));
		estimatedPropertyCostDetail = new EstimatedPropertyCostDetail((Element)getElementAddNS("ESTIMATED_PROPERTY_COST_DETAIL"));
	}
}
