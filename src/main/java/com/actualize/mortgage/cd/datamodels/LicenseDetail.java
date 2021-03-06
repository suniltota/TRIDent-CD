package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents LicenseDetail in MISMO XML
 * @author sboragala
 *
 */
public class LicenseDetail extends MISMODataAccessObject {
	private static final long serialVersionUID = 8814441650293375683L;
	public final String licenseAuthorityLevelType;
	public final String licenseIdentifier;
	public String identifierOwnerURI = "";
	public final String licenseIssueDate;
	public final String licenseIssuingAuthorityName;
	public final String licenseIssuingAuthorityStateCode;
	
	public LicenseDetail(Element element) {
		super(element);
		licenseAuthorityLevelType = getValueAddNS("LicenseAuthorityLevelType");
		licenseIdentifier = getValueAddNS("LicenseIdentifier");
		NodeList node = getElementsAddNS("LicenseIdentifier");
		if(null != node)
		{	
			Element ele =(Element)node.item(0);
			if(null != ele)
			{
				identifierOwnerURI = ele.getAttribute("IdentifierOwnerURI");
			}
		}
		licenseIssueDate = getValueAddNS("LicenseIssueDate");
		licenseIssuingAuthorityName = getValueAddNS("LicenseIssuingAuthorityName");
		licenseIssuingAuthorityStateCode = getValueAddNS("LicenseIssuingAuthorityStateCode");
	}
}
