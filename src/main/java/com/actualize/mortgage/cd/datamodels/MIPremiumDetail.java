/**
 * 
 */
package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;

/**
 * @author sboragala
 *
 */
public class MIPremiumDetail extends MISMODataAccessObject{
	
	private static final long serialVersionUID = 6644425675497532224L;
	public final String miPremiumPeriodType;
	public final String miPremiumRateDurationMonthsCount;
	public final String miPremiumRatePercent;

	public MIPremiumDetail(Element e) {
		super(e);
		miPremiumPeriodType = getValueAddNS("MIPremiumPeriodType");
		miPremiumRateDurationMonthsCount = getValueAddNS("MIPremiumRateDurationMonthsCount");
		miPremiumRatePercent = getValueAddNS("MIPremiumRatePercent");
	}

}
