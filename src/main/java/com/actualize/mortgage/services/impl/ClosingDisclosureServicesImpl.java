/*
 * @(#)ClosingDisclosureServiceImpl.java 1.0 04/11/2017
 * 
 */
package com.actualize.mortgage.services.impl;

import java.io.InputStream;

import com.actualize.mortgage.convertors.ClosingDisclosureConverter;
import com.actualize.mortgage.datamodels.MISMODocument;
import com.actualize.mortgage.domainmodels.ClosingDisclosure;
import com.actualize.mortgage.services.ClosingDisclosureServices;
import com.actualize.mortgage.utils.JsonToUcd;

/**
 * This is the implementation class for the {@link ClosingDisclosureServices} which is used to write 
 * the business logic to create ClosingDisclosure XML / PDF and generate Page Objects to represents
 *  the all the pages present in Closing Disclosure
 * 
 * @author rsudula
 * @version 1.0
 * 
 */
public class ClosingDisclosureServicesImpl implements ClosingDisclosureServices {
	
	/*
	 * (non-Javadoc)
	 * @see com.actualize.closingdisclosure.services.ClosingDisclosureServices#createClosingDisclosureObjectfromXMLDoc(java.io.InputStream)
	 */
    @Override
    public ClosingDisclosure createClosingDisclosureObjectfromXMLDoc(InputStream inputXmlStream) throws Exception {
        MISMODocument document = new MISMODocument(inputXmlStream); 
        ClosingDisclosureConverter closingDisclosureConverter = new ClosingDisclosureConverter();
        ClosingDisclosure closingDisclosure = closingDisclosureConverter.convertXmltoJSON(document);
        return closingDisclosure;
    }

    /*
     * (non-Javadoc)
     * @see com.actualize.closingdisclosure.services.ClosingDisclosureServices#createClosingDisclosureXMLfromObject(com.actualize.closingdisclosure.pagemodels.ClosingDisclosure)
     */
    @Override
    public String createClosingDisclosureXMLfromObject(ClosingDisclosure closingDisclosure) throws Exception {
    	JsonToUcd jsonToUcd = new JsonToUcd();
        return jsonToUcd.transform(closingDisclosure);
    }

}
