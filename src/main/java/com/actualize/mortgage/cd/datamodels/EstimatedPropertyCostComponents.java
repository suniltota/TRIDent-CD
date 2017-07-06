package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents EstimatedPropertyCostComponents in MISMO XML
 * @author sboragala
 *
 */
public class EstimatedPropertyCostComponents extends MISMODataAccessObject {
	private static final long serialVersionUID = -5781659804787000744L;
	
	public String displayLabel = "";
	public EstimatedPropertyCostComponent[] estimatedPropertyCostComponent;

	public EstimatedPropertyCostComponents(Element element) {
		super(element);
			
		displayLabel = element.getAttribute("gse:DisplayLabelText");
		
		NodeList nodes = getElementsAddNS((NS == null ? "" : NS)+"ESTIMATED_PROPERTY_COST_COMPONENT");
		estimatedPropertyCostComponent = new EstimatedPropertyCostComponent[nodes==null ? 0 : nodes.getLength()];
		for (int i = 0; i < estimatedPropertyCostComponent.length; i++)
			estimatedPropertyCostComponent[i] = new EstimatedPropertyCostComponent((Element)nodes.item(i));
	}
}
