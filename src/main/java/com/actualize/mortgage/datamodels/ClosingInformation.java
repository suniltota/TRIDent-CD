package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * defines ClosingInformation in MIMSO XML
 * @author sboragala
 *
 */
public class ClosingInformation extends MISMODataAccessObject {

	private static final long serialVersionUID = 4894606193211029598L;
	public final ClosingAdjustmentItems closingAdjustmentItems;
	public final ClosingCostFunds closingCostFunds;
	public final ClosingInformationDetail closingInformationDetail;
	public final ProrationItems prorationItems;
	public ClosingInformation(String NS, Element element) {
		super(element);
		closingAdjustmentItems = new ClosingAdjustmentItems((Element)getElementAddNS("CLOSING_ADJUSTMENT_ITEMS"));
		closingCostFunds = new ClosingCostFunds((Element)getElementAddNS("CLOSING_COST_FUNDS"));
		closingInformationDetail = new ClosingInformationDetail((Element)getElementAddNS("CLOSING_INFORMATION_DETAIL"));
		prorationItems = new ProrationItems((Element)getElementAddNS("PRORATION_ITEMS"));
	}
}
