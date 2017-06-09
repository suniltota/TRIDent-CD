package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents Fee Payment in MISMO XML
 * @author sboragala
 *
 */
public class FeePayment extends MISMODataAccessObject {

	private static final long serialVersionUID = -9013521457029759073L;
	public final String feeActualPaymentAmount;
	public final String feePaymentPaidByType;
	public final String feePaymentPaidOutsideOfClosingIndicator;
		
	public FeePayment(Element element) {
		super(element);
		feeActualPaymentAmount = getValueAddNS("FeeActualPaymentAmount"); 
		feePaymentPaidByType = getValueAddNS("FeePaymentPaidByType"); 
		feePaymentPaidOutsideOfClosingIndicator = getValueAddNS("FeePaymentPaidOutsideOfClosingIndicator"); 
	}

}
