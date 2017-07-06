/**
 * @(#)ClosingDisclosureController.java 1.0 04/11/2017
 */

package com.actualize.mortgage.api;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.actualize.mortgage.cd.domainmodels.ClosingDisclosure;
import com.actualize.mortgage.services.impl.IClosingDisclosureServices;

/**
 * This controller is used to define all the endpoints (APIs) for Closing Disclosure
 * @author rsudula
 * @version 1.0
 */

@RestController
@RequestMapping("/actualize/transformx/trident/cd")
public class ClosingDisclosureApiImpl {

	private static final Logger LOG = LogManager.getLogger(ClosingDisclosureApiImpl.class);
	
	@Autowired
	private IClosingDisclosureServices closingDisclosureServices;

    /**
     * Generates JSON response for closing disclosure on giving xml as input in
     * String format
     * 
     * @param xmldoc
     * @return JSON response for closing disclosure
     * @throws Exception
     */
	@ResponseBody
    @RequestMapping(value = "/{version}/ucdtojson", method = { RequestMethod.POST })
    public ClosingDisclosure convertXMLtoObject(@PathVariable String version, @RequestBody String xmldoc) throws Exception {
		LOG.info("Service: ucdtojson called");
        InputStream in = new ByteArrayInputStream(xmldoc.getBytes(StandardCharsets.UTF_8));
        return closingDisclosureServices.createClosingDisclosureObjectfromXMLDoc(in);
    }
    
    /**
     * Generates JSON response for closing disclosure on giving xml as input in
     * String format
     * @param xmldoc
     * @return JSON response for closing disclosure
     * @throws Exception
     */
    @RequestMapping(value = "/{version}/jsontoucd", method = { RequestMethod.POST })
    public String convertObjecttoXML(@PathVariable String version, @RequestBody ClosingDisclosure closingDisclosure) throws Exception {
    	LOG.info("Service: jsontoucd called"); 
    	return closingDisclosureServices.createClosingDisclosureXMLfromObject(closingDisclosure);
    }
    
    /**
     * to check whether the service is running or not
     * @param version
     * @param closingDisclosure
     * @return 
     * @throws Exception
     */
    @RequestMapping(value = "/{version}/ping", method = { RequestMethod.GET })
    public String status(@PathVariable String version) throws Exception {
    	LOG.info("Service: ping for TRIDENT-CD called"); 
        return "The service for generating JSON from UCD XML and vice versa is running and ready to accept your request";
    }
    
   }