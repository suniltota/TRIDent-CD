/**
 * 
 */
package com.actualize.mortgage.cd.domainmodels;

import java.io.Serializable;

/**
 * @author sboragala
 *
 */
public class MIPremiumModel implements Serializable{
	

	private static final long serialVersionUID = 3152227342376780954L;
	
	private String miPremiumPeriodType = "";
	private String miPremiumRateDurationMonthsCount = "";
	private String miPremiumRatePercent = "";
	/**
	 * @return the miPremiumPeriodType
	 */
	public String getMiPremiumPeriodType() {
		return miPremiumPeriodType;
	}
	/**
	 * @param miPremiumPeriodType the miPremiumPeriodType to set
	 */
	public void setMiPremiumPeriodType(String miPremiumPeriodType) {
		this.miPremiumPeriodType = miPremiumPeriodType;
	}
	/**
	 * @return the miPremiumRateDurationMonthsCount
	 */
	public String getMiPremiumRateDurationMonthsCount() {
		return miPremiumRateDurationMonthsCount;
	}
	/**
	 * @param miPremiumRateDurationMonthsCount the miPremiumRateDurationMonthsCount to set
	 */
	public void setMiPremiumRateDurationMonthsCount(String miPremiumRateDurationMonthsCount) {
		this.miPremiumRateDurationMonthsCount = miPremiumRateDurationMonthsCount;
	}
	/**
	 * @return the miPremiumRatePercent
	 */
	public String getMiPremiumRatePercent() {
		return miPremiumRatePercent;
	}
	/**
	 * @param miPremiumRatePercent the miPremiumRatePercent to set
	 */
	public void setMiPremiumRatePercent(String miPremiumRatePercent) {
		this.miPremiumRatePercent = miPremiumRatePercent;
	}
	
	

}
