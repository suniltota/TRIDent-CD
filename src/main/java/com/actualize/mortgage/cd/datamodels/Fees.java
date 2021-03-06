package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents Fees in MISMO XML
 * @author sboragala
 *
 */
public class Fees extends MISMODataAccessObject {
	
	private static final long serialVersionUID = -6457846149654564046L;
	public final Fee[] fees;

	public Fees(Element element) {
		this(element, "");
	}

	public Fees(Element element, String qualifier) {
		super(element);
		NodeList nodes = getElementsAddNS("FEE" + qualifier);
		fees = new Fee[nodes==null ? 0 : nodes.getLength()];
		for (int i = 0; i < fees.length; i++)
			fees[i] = new Fee((Element)nodes.item(i));
		
		// Sort fees
		for (int i = 0; i < fees.length; i++)
			for (int j = i+1; j < fees.length; j++)
				if (fees[i].feeDetail != null && fees[j].feeDetail != null && !nameSortsBefore(fees[i].feeDetail.displayName(), fees[j].feeDetail.displayName())) {
					Fee tmp = fees[i];
					fees[i] = fees[j];
					fees[j] = tmp;
				}
	}
}
