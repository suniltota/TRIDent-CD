package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;

/**
 * defines CashToCloseItem in MISMO XML
 * @author sboragala
 *
 */
public class CashToCloseItem extends MISMODataAccessObject {
	private static final long serialVersionUID = 3216303395699304276L;
	public final String integratedDisclosureCashToCloseItemAmountChangedIndicator;
	public final String integratedDisclosureCashToCloseItemChangeDescription;
	public final String integratedDisclosureCashToCloseItemEstimatedAmount;
	public final String integratedDisclosureCashToCloseItemFinalAmount;
	public final String integratedDisclosureCashToCloseItemPaymentType;
	public final String integratedDisclosureCashToCloseItemType;

	public CashToCloseItem(Element element) {
		super(element);
		integratedDisclosureCashToCloseItemAmountChangedIndicator = getValueAddNS("IntegratedDisclosureCashToCloseItemAmountChangedIndicator");
		integratedDisclosureCashToCloseItemChangeDescription = getValueAddNS("IntegratedDisclosureCashToCloseItemChangeDescription");
		integratedDisclosureCashToCloseItemEstimatedAmount = getValueAddNS("IntegratedDisclosureCashToCloseItemEstimatedAmount");
		integratedDisclosureCashToCloseItemFinalAmount = getValueAddNS("IntegratedDisclosureCashToCloseItemFinalAmount");
		integratedDisclosureCashToCloseItemPaymentType = getValueAddNS("IntegratedDisclosureCashToCloseItemPaymentType");
		integratedDisclosureCashToCloseItemType = getValueAddNS("IntegratedDisclosureCashToCloseItemType");
	}
}
