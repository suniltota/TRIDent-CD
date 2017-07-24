package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents MIDataDetail in MISMO XML
 * @author pugaz
 *
 */
public class MIDataDetail extends MISMODataAccessObject {
	private static final long serialVersionUID = 1306425929192950469L;
	public final String miCertificateIdentifier;
	public final String miCompanyNameType;
	public final String miCompanyNameTypeOtherDescription;
	public final String miScheduledTerminationDate;
	public final String miInitialPremiumAmount;
	public final String miCoveragePercent; 
	
	public MIDataDetail(Element element) {
		super(element);
		miCertificateIdentifier = getValueAddNS("MICertificateIdentifier");
		miCompanyNameType = getValueAddNS("MICompanyNameType");
		miCompanyNameTypeOtherDescription = getValueAddNS("MICompanyNameTypeOtherDescription");
		miScheduledTerminationDate = getValueAddNS("MIScheduledTerminationDate");
		miInitialPremiumAmount = getValueAddNS("MIInitialPremiumAmount");
		miCoveragePercent = getValueAddNS("MICoveragePercent");
	}
}
