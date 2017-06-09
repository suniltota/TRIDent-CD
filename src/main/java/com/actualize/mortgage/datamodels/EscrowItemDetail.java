package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents EscrowItemDetail in MISMO XML
 * @author sboragala
 *
 */
public class EscrowItemDetail extends MISMODataAccessObject {

	private static final long serialVersionUID = 1782808148649020882L;
	public  String displayLabelText = "";
	public final String escrowCollectedNumberOfMonthsCount;
	public final String escrowItemCategoryType;
	public final String escrowItemEstimatedTotalAmount;
	public final String escrowItemType;
	public final String escrowItemTypeOtherDescription;
	public final String escrowMonthlyPaymentAmount;
	public final String feePaidToType;
	public final String feePaidToTypeOtherDescription;
	public final String integratedDisclosureSectionType;
	public final String regulationZPointsAndFeesIndicator;
	public final String paymentIncludedInAPRIndicator;
	  
	public EscrowItemDetail(Element element) {
		super(element);
		escrowCollectedNumberOfMonthsCount = getValueAddNS("EscrowCollectedNumberOfMonthsCount");
		escrowItemCategoryType = getValueAddNS("EscrowItemCategoryType");
		escrowItemEstimatedTotalAmount = getValueAddNS("EscrowItemEstimatedTotalAmount");
		escrowItemType = getValueAddNS("EscrowItemType");
		NodeList node = getElementsAddNS("EscrowItemType");
		if(null != node)
		{
			Element ele =(Element)node.item(0);
			if(null != ele)
			{	
				displayLabelText = ele.getAttribute("DisplayLabelText");
				if("".equals(displayLabelText) || displayLabelText.isEmpty())
					displayLabelText = ele.getAttribute("gse:DisplayLabelText");
			}
		}
		if("".equals(displayLabelText) || displayLabelText.isEmpty())
			displayLabelText = getAttributeValue("gse:DisplayLabelText");
		if("".equals(displayLabelText) || displayLabelText.isEmpty())
			displayLabelText = getAttributeValue("DisplayLabelText");
		escrowItemTypeOtherDescription = getValueAddNS("EscrowItemTypeOtherDescription");
		escrowMonthlyPaymentAmount = getValueAddNS("EscrowMonthlyPaymentAmount");
		feePaidToType = getValueAddNS("FeePaidToType");
		feePaidToTypeOtherDescription = getValueAddNS("FeePaidToTypeOtherDescription");
		integratedDisclosureSectionType = getValueAddNS("IntegratedDisclosureSectionType");
		regulationZPointsAndFeesIndicator = getValueAddNS("RegulationZPointsAndFeesIndicator");
		paymentIncludedInAPRIndicator = getValueAddNS("EXTENSION/MISMO/PaymentIncludedInAPRIndicator");
	}
	
	public String displayName() {
		if (!displayLabelText.equals(""))
			return displayLabelText;
		if (escrowItemType.equalsIgnoreCase("Other"))
			return escrowItemTypeOtherDescription;
		return escrowItemType;
	}
}
