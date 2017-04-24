/**
 * @(#)ClosingDisclosureController.java 1.0 04/11/2017
 */

package com.actualize.closingdisclosure.api;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.actualize.closingdisclosure.TriDentCdApplication;
import com.actualize.closingdisclosure.pagemodels.ClosingDisclosure;
import com.actualize.closingdisclosure.services.ClosingDisclosureServices;

/**
 * This controller is used to define all the endpoints (APIs) for Closing Disclosure
 * @author rsudula
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/actualize/trident/closingdisclosure")
public class ClosingDisclosureApiImpl {

	private static final Logger LOG = LogManager.getLogger(ClosingDisclosureApiImpl.class);
	
    @Autowired
    private ClosingDisclosureServices closingDisclosureServices;

    /**
     * Generates JSON response for closing disclosure on giving xml as input in
     * String format
     * 
     * @param xmldoc
     * @return JSON response for closing disclosure
     * @throws Exception
     */
    @RequestMapping(value = "/convertXmlToJson", method = { RequestMethod.POST })
    public ClosingDisclosure convertXMLtoObject(@RequestBody String xmldoc) throws Exception {
        InputStream in = new ByteArrayInputStream(xmldoc.getBytes(StandardCharsets.UTF_8));
        return closingDisclosureServices.createClosingDisclosureObjectfromXMLDoc(in);
    }

}
