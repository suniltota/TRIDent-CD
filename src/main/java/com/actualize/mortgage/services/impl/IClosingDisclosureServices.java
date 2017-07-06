package com.actualize.mortgage.services.impl;

import java.io.InputStream;

import com.actualize.mortgage.cd.domainmodels.ClosingDisclosure;

public interface IClosingDisclosureServices {

	public ClosingDisclosure createClosingDisclosureObjectfromXMLDoc(InputStream inputXmlStream) throws Exception;
	public String createClosingDisclosureXMLfromObject(ClosingDisclosure closingDisclosure) throws Exception;
}
