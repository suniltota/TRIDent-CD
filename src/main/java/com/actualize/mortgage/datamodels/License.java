package com.actualize.mortgage.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.domainmodels.MISMODataAccessObject;
/**
 * represents License in MISMO XML
 * @author sboragala
 *
 */
public class License extends MISMODataAccessObject {

	private static final long serialVersionUID = -5780416607846360070L;
	public final LicenseDetail licenseDetail;

	public License(Element element) {
		super(element);
		licenseDetail = new LicenseDetail((Element)getElementAddNS("LICENSE_DETAIL"));
	}
}
