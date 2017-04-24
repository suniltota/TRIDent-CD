/**
 * @(#)ClosingDisclosurePageTwo.java 1.0 04/10/2017
 */
package com.actualize.closingdisclosure.pagemodels;

import java.io.Serializable;

import com.actualize.closingdisclosure.domainmodels.ClosingCostDetailsLoanCosts;
import com.actualize.closingdisclosure.domainmodels.ClosingCostDetailsOtherCosts;

/**
 * This class represents all the sections present in Closing Disclosure Form Page-2
 * @author rsudula
 * @version 1.0
 */
public class ClosingDisclosurePageTwo implements Serializable {

    private static final long serialVersionUID = 1093530156541736214L;

    private ClosingCostDetailsLoanCosts closingCostDetailsLoanCosts;
    private ClosingCostDetailsOtherCosts closingCostDetailsOtherCosts;
    /**
     * @return the closingCostDetailsLoanCosts
     */
    public ClosingCostDetailsLoanCosts getClosingCostDetailsLoanCosts() {
        return closingCostDetailsLoanCosts;
    }
    /**
     * @param closingCostDetailsLoanCosts the closingCostDetailsLoanCosts to set
     */
    public void setClosingCostDetailsLoanCosts(ClosingCostDetailsLoanCosts closingCostDetailsLoanCosts) {
        this.closingCostDetailsLoanCosts = closingCostDetailsLoanCosts;
    }
    /**
     * @return the closingCostDetailsOtherCosts
     */
    public ClosingCostDetailsOtherCosts getClosingCostDetailsOtherCosts() {
        return closingCostDetailsOtherCosts;
    }
    /**
     * @param closingCostDetailsOtherCosts the closingCostDetailsOtherCosts to set
     */
    public void setClosingCostDetailsOtherCosts(ClosingCostDetailsOtherCosts closingCostDetailsOtherCosts) {
        this.closingCostDetailsOtherCosts = closingCostDetailsOtherCosts;
    }
}
