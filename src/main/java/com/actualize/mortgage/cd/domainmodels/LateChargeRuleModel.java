/**
 * 
 */
package com.actualize.mortgage.cd.domainmodels;

import java.io.Serializable;

/**
 * defines LateChargeRuleModel in JSON Response
 * @author sboragala
 *
 */
public class LateChargeRuleModel implements Serializable {

	private static final long serialVersionUID = -1672022915224742394L;
	
	private String lateChargeAmount;
    private String lateChargeGracePeriodDaysCount;
    private String lateChargeRatePercent;
    private String lateChargeType;
    private String lateChargeMaximumAmount;
	private String lateChargeMinimumAmount;
    
	/**
	 * @return the lateChargeAmount
	 */
	public String getLateChargeAmount() {
		return lateChargeAmount;
	}
	/**
	 * @param lateChargeAmount the lateChargeAmount to set
	 */
	public void setLateChargeAmount(String lateChargeAmount) {
		this.lateChargeAmount = lateChargeAmount;
	}
	/**
	 * @return the lateChargeGracePeriodDaysCount
	 */
	public String getLateChargeGracePeriodDaysCount() {
		return lateChargeGracePeriodDaysCount;
	}
	/**
	 * @param lateChargeGracePeriodDaysCount the lateChargeGracePeriodDaysCount to set
	 */
	public void setLateChargeGracePeriodDaysCount(String lateChargeGracePeriodDaysCount) {
		this.lateChargeGracePeriodDaysCount = lateChargeGracePeriodDaysCount;
	}
	/**
	 * @return the lateChargeRatePercent
	 */
	public String getLateChargeRatePercent() {
		return lateChargeRatePercent;
	}
	/**
	 * @param lateChargeRatePercent the lateChargeRatePercent to set
	 */
	public void setLateChargeRatePercent(String lateChargeRatePercent) {
		this.lateChargeRatePercent = lateChargeRatePercent;
	}
	/**
	 * @return the lateChargeType
	 */
	public String getLateChargeType() {
		return lateChargeType;
	}
	/**
	 * @param lateChargeType the lateChargeType to set
	 */
	public void setLateChargeType(String lateChargeType) {
		this.lateChargeType = lateChargeType;
	}
	/**
	 * @return the lateChargeMaximumAmount
	 */
	public String getLateChargeMaximumAmount() {
		return lateChargeMaximumAmount;
	}
	/**
	 * @param lateChargeMaximumAmount the lateChargeMaximumAmount to set
	 */
	public void setLateChargeMaximumAmount(String lateChargeMaximumAmount) {
		this.lateChargeMaximumAmount = lateChargeMaximumAmount;
	}
	/**
	 * @return the lateChargeMinimumAmount
	 */
	public String getLateChargeMinimumAmount() {
		return lateChargeMinimumAmount;
	}
	/**
	 * @param lateChargeMinimumAmount the lateChargeMinimumAmount to set
	 */
	public void setLateChargeMinimumAmount(String lateChargeMinimumAmount) {
		this.lateChargeMinimumAmount = lateChargeMinimumAmount;
	}
    
    
	
}
