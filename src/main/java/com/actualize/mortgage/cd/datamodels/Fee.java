package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents Extension in MISMO XML
 * @author sboragala
 *
 */
public class Fee extends MISMODataAccessObject {
	private static final long serialVersionUID = 8906383547642086575L;
	public final FeeDetail feeDetail;
	public final FeePaidTo feePaidTo;
	public final FeePayments feePayments;

	public Fee(Element element) {
		super(element);
		feeDetail = new FeeDetail((Element)getElementAddNS("FEE_DETAIL"));
		feePaidTo = new FeePaidTo((Element)getElementAddNS("FEE_PAID_TO"));
		feePayments = new FeePayments((Element)getElementAddNS("FEE_PAYMENTS"));
	}
}
