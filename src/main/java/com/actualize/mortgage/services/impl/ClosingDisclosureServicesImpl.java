/*
 * @(#)ClosingDisclosureServiceImpl.java 1.0 04/11/2017
 * 
 */
package com.actualize.mortgage.services.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.springframework.stereotype.Service;

import com.actualize.mortgage.cd.convertors.ClosingDisclosureConverter;
import com.actualize.mortgage.cd.datamodels.MISMODocument;
import com.actualize.mortgage.cd.domainmodels.ClosingDisclosure;
import com.actualize.mortgage.cd.domainmodels.PDFResponse;
import com.actualize.mortgage.cd.utils.JsonToUcd;
import com.actualize.mortgage.datalayer.InputData;
import com.actualize.mortgage.datalayer.PopulateInputData;
import com.actualize.mortgage.domainmodels.UniformDisclosureBuilder;
import com.actualize.mortgage.domainmodels.UniformDisclosureBuilderSeller;


/**
 * This is the implementation class which is used to write 
 * the business logic to create ClosingDisclosure XML / PDF and generate Page Objects to represents
 *  the all the pages present in Closing Disclosure
 * 
 * @author rsudula
 * @version 1.0
 * 
 */
@Service
public class ClosingDisclosureServicesImpl implements IClosingDisclosureServices{
	
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
    
    /**
     * 
     * @param xmlDoc
     * @return
     * @throws Exception
     */
    public List<PDFResponse> createPDF(String xmlDoc) {
		 PopulateInputData reader = new PopulateInputData();
		    List<InputData> inputData = new LinkedList<>();
			try {
				inputData = reader.getData(new ByteArrayInputStream(xmlDoc.getBytes("utf-8")));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    ByteArrayOutputStream pdfOutStream = null;
		    List<PDFResponse> pdfResponseList = new ArrayList<>();
		    for (InputData data : inputData) {
		    PDFResponse outputResponse = new PDFResponse();
		    outputResponse.setFilename("ClosingDisclosure");
		    outputResponse.setOutputType("application/pdf");
		        if (data.isSellerOnly()) {
		            UniformDisclosureBuilderSeller pdfbuilder = new UniformDisclosureBuilderSeller();
		            try {
						pdfOutStream = pdfbuilder.run(data);
					} catch (COSVisitorException | ParserConfigurationException | TransformerFactoryConfigurationError
							| TransformerException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            outputResponse.setResponseData(pdfOutStream.toByteArray());
		        } else {
		            UniformDisclosureBuilder pdfbuilder = new UniformDisclosureBuilder();
		            try {
						pdfOutStream = pdfbuilder.run(data);
					} catch (COSVisitorException | ParserConfigurationException | TransformerFactoryConfigurationError
							| TransformerException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		 
		            outputResponse.setResponseData(pdfOutStream.toByteArray());
		        }
		        pdfResponseList.add(outputResponse);
		    }
		return pdfResponseList;
	}


}
