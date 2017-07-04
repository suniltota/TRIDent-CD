package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents LateChargeRule in MISMO XML
 * @author sboragala
 *
 */
public class LateChargeRule extends MISMODataAccessObject {

	private static final long serialVersionUID = -8894459264425661828L;
	public final String lateChargeAmount;
	public final String lateChargeGracePeriodDaysCount;
	public final String lateChargeMaximumAmount;
	public final String lateChargeMinimumAmount;
	public final String lateChargeRatePercent;
	public final String lateChargeType;
	
	public LateChargeRule(Element element) {
		super(element);
		lateChargeAmount = getValue("gse:LateChargeAmount");
		lateChargeGracePeriodDaysCount = getValue("gse:LateChargeGracePeriodDaysCount");
		lateChargeMaximumAmount = getValue("gse:LateChargeMaximumAmount");
		lateChargeMinimumAmount = getValue("gse:LateChargeMinimumAmount");
		lateChargeRatePercent = getValue("gse:LateChargeRatePercent");
		lateChargeType = getValue("gse:LateChargeType");
	}
}
