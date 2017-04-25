/*
 * @(#)ClosingDisclosureServiceImpl.java 1.0 04/11/2017
 * 
 */
package com.actualize.closingdisclosure.services.impl;

import java.io.InputStream;

import com.actualize.closingdisclosure.convertors.ClosingDisclosureConverter;
import com.actualize.closingdisclosure.datamodels.MISMODocument;
import com.actualize.closingdisclosure.domainmodels.ClosingDisclosureDocument;
import com.actualize.closingdisclosure.pagemodels.ClosingDisclosure;
import com.actualize.closingdisclosure.services.ClosingDisclosureServices;

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
    public ClosingDisclosureDocument createClosingDisclosureObjectfromXMLDoc(InputStream inputXmlStream) throws Exception {
        MISMODocument document = new MISMODocument(inputXmlStream); 
        ClosingDisclosureConverter closingDisclosureConverter = new ClosingDisclosureConverter();
        ClosingDisclosureDocument closingDisclosure = closingDisclosureConverter.convertXmltoJSON(document);
        return closingDisclosure;
    }

    /*
     * (non-Javadoc)
     * @see com.actualize.closingdisclosure.services.ClosingDisclosureServices#createClosingDisclosureXMLfromObject(com.actualize.closingdisclosure.pagemodels.ClosingDisclosure)
     */
    @Override
    public ClosingDisclosure createClosingDisclosureXMLfromObject(ClosingDisclosure closingDisclosure) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
