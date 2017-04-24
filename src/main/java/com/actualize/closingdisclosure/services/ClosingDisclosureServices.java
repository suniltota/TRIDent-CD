package com.actualize.closingdisclosure.services;

import java.io.InputStream;

import com.actualize.closingdisclosure.pagemodels.ClosingDisclosure;
/**
 * This is service interface for the Closing Disclosure which is used to define 
 * different methods for example create ClosingDisclosure XML / PDF and generate Page Objects to represents
 * the all the pages present in Closing Disclosure.
 * 
 * @author rsudula
 * @version 1.0
 * 
 */
public interface ClosingDisclosureServices {

    public ClosingDisclosure createClosingDisclosureObjectfromXMLDoc(InputStream inputXmlStream) throws Exception;
    
    public ClosingDisclosure createClosingDisclosureXMLfromObject(ClosingDisclosure closingDisclosure) throws Exception;

}
