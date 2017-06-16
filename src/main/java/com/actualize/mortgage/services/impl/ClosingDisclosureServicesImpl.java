/*
 * @(#)ClosingDisclosureServiceImpl.java 1.0 04/11/2017
 * 
 */
package com.actualize.mortgage.services.impl;

import java.io.InputStream;

import com.actualize.mortgage.cd.convertors.ClosingDisclosureConverter;
import com.actualize.mortgage.cd.datamodels.MISMODocument;
import com.actualize.mortgage.cd.domainmodels.ClosingDisclosure;
import com.actualize.mortgage.cd.utils.JsonToUcd;

/**
 * This is the implementation class which is used to write 
 * the business logic to create ClosingDisclosure XML / PDF and generate Page Objects to represents
 *  the all the pages present in Closing Disclosure
 * 
 * @author rsudula
 * @version 1.0
 * 
 */
public class ClosingDisclosureServicesImpl {
	
	/**
	 * create closing disclosure JSON object from MISMO XML
	 * @param inputXmlStream MISMO XML as InputStream
	 * @return ClosingDisclosure JSON Object 
	 * @throws Exception
	 */
    public ClosingDisclosure createClosingDisclosureObjectfromXMLDoc(InputStream inputXmlStream) throws Exception {
        MISMODocument document = new MISMODocument(inputXmlStream); 
        ClosingDisclosureConverter closingDisclosureConverter = new ClosingDisclosureConverter();
        ClosingDisclosure closingDisclosure = closingDisclosureConverter.convertXmltoJSON(document);
        return closingDisclosure;
    }

    /**
     * create MISMO XML  from closing disclosure JSON object
     * @param closingDisclosure
     * @return MISMO XML as String
     * @throws Exception
     */
    public String createClosingDisclosureXMLfromObject(ClosingDisclosure closingDisclosure) throws Exception {
    	JsonToUcd jsonToUcd = new JsonToUcd();
        return jsonToUcd.transform(closingDisclosure);
    }

}
