package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents URLA in MISMO XML
 * @author sboragala
 *
 */
public class URLA extends MISMODataAccessObject {
	public final UrlaDetail urlaDetail;

	public URLA(String NS, Element element) {
		super(element);
		urlaDetail = new UrlaDetail(element);
	}
}
