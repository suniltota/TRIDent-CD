package com.actualize.mortgage.cd.utils;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.actualize.mortgage.cd.domainmodels.AddressModel;
import com.actualize.mortgage.cd.domainmodels.AutomatedUnderwritingsModel;
import com.actualize.mortgage.cd.domainmodels.Borrower;
import com.actualize.mortgage.cd.domainmodels.CashToClose;
import com.actualize.mortgage.cd.domainmodels.CashToCloseModel;
import com.actualize.mortgage.cd.domainmodels.ClosingAdjustmentItemModel;
import com.actualize.mortgage.cd.domainmodels.ClosingCostFundModel;
import com.actualize.mortgage.cd.domainmodels.ClosingCostProperties;
import com.actualize.mortgage.cd.domainmodels.ClosingDisclosure;
import com.actualize.mortgage.cd.domainmodels.ClosingDisclosureDocumentDetails;
import com.actualize.mortgage.cd.domainmodels.ClosingInformationDetailModel;
import com.actualize.mortgage.cd.domainmodels.ConstructionModel;
import com.actualize.mortgage.cd.domainmodels.ContactInformationDetailModel;
import com.actualize.mortgage.cd.domainmodels.ETIA;
import com.actualize.mortgage.cd.domainmodels.ETIASection;
import com.actualize.mortgage.cd.domainmodels.EscrowItemModel;
import com.actualize.mortgage.cd.domainmodels.IntegratedDisclosureDetailModel;
import com.actualize.mortgage.cd.domainmodels.IntegratedDisclosureSectionSummaryDetailModel;
import com.actualize.mortgage.cd.domainmodels.IntegratedDisclosureSectionSummaryModel;
import com.actualize.mortgage.cd.domainmodels.IntegratedDisclosureSubsectionPaymentModel;
import com.actualize.mortgage.cd.domainmodels.InterestOnlyModel;
import com.actualize.mortgage.cd.domainmodels.InterestRateAdjustmentModel;
import com.actualize.mortgage.cd.domainmodels.LateChargeRuleModel;
import com.actualize.mortgage.cd.domainmodels.LiabilityModel;
import com.actualize.mortgage.cd.domainmodels.LoanCalculationModel;
import com.actualize.mortgage.cd.domainmodels.LoanDetailModel;
import com.actualize.mortgage.cd.domainmodels.LoanInformation;
import com.actualize.mortgage.cd.domainmodels.LoanInformationLoanIdentifier;
import com.actualize.mortgage.cd.domainmodels.LoanProductModel;
import com.actualize.mortgage.cd.domainmodels.LoanTermsPrepaymentPenalty;
import com.actualize.mortgage.cd.domainmodels.LoanTermsTemporaryBuydown;
import com.actualize.mortgage.cd.domainmodels.MIDataDetailModel;
import com.actualize.mortgage.cd.domainmodels.MIPremiumModel;
import com.actualize.mortgage.cd.domainmodels.MaturityRuleModel;
import com.actualize.mortgage.cd.domainmodels.MismoContactPointsModel;
import com.actualize.mortgage.cd.domainmodels.MismoIndividualModel;
import com.actualize.mortgage.cd.domainmodels.MismoPaymentsModel;
import com.actualize.mortgage.cd.domainmodels.MismoProjectedPaymentsModel;
import com.actualize.mortgage.cd.domainmodels.NameModel;
import com.actualize.mortgage.cd.domainmodels.NegativeAmortizationModel;
import com.actualize.mortgage.cd.domainmodels.OtherModel;
import com.actualize.mortgage.cd.domainmodels.PDFResponse;
import com.actualize.mortgage.cd.domainmodels.PartialPaymentModel;
import com.actualize.mortgage.cd.domainmodels.PartialPaymentsModel;
import com.actualize.mortgage.cd.domainmodels.PaymentModel;
import com.actualize.mortgage.cd.domainmodels.PaymentRuleModel;
import com.actualize.mortgage.cd.domainmodels.PaymentsModel;
import com.actualize.mortgage.cd.domainmodels.Prepaids;
import com.actualize.mortgage.cd.domainmodels.PrincipalAndInterestPaymentAdjustmentModel;
import com.actualize.mortgage.cd.domainmodels.ProjectedPaymentsDetails;
import com.actualize.mortgage.cd.domainmodels.PropertyValuationDetailModel;
import com.actualize.mortgage.cd.domainmodels.ProrationModel;
import com.actualize.mortgage.cd.domainmodels.QualifiedMortgageModel;
import com.actualize.mortgage.cd.domainmodels.RelationshipsModel;
import com.actualize.mortgage.cd.domainmodels.SalesContractDetailModel;
import com.actualize.mortgage.cd.domainmodels.SignatoriesModel;
import com.actualize.mortgage.cd.domainmodels.TermsOfLoanModel;
import com.actualize.mortgage.services.impl.ClosingDisclosureServicesImpl;



public class JsonToUcd {
	
	private static final String GSE_ALIAS = "gse";
	private static final String MISMO_ALIAS = "mismo";
	private static final String XLINK_ALIAS = "xlink";
	private static final String XMLNS_ALIAS = "xmlns";
	private static final String GSE_URI = "http://www.datamodelextension.org";
	private static final String MISMO_URI = "http://www.mismo.org/residential/2009/schemas";
	private static final String XLINK_URI = "http://www.w3.org/1999/xlink";
	private static final String XSI_URI = "http://www.w3.org/2001/XMLSchema-instance";

	private List<RelationshipsModel> relationships = new LinkedList<>();
	private List<SignatoriesModel> signatories = new LinkedList<>();
	private int borrowerSNumber = 11;
	private int sellerSNumber = 13;
	private int signatoryBorrowerSNumber = 1;
	private int signatorySellerSNumber = 3;
	private int relationshipSellerSNumber = 13;
	
	private static final Logger LOG = LogManager.getLogger(JsonToUcd.class.getName());

	private static final DocumentBuilderFactory dbf = initializeDocumentBuilderFactory();
//	private static final XPath xPath = XPathFactory.newInstance().newXPath();
	
    private static DocumentBuilderFactory initializeDocumentBuilderFactory() {
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
    	return dbf;
    }
	/**
	 * Method is used to transform one file into another form
	 * @param jsonDocument is Input JSON Object
	 * @return object of Document
	 * @throws TransformerFactoryConfigurationError 
	 * @throws TransformerConfigurationException 
	 */
    public String transform(ClosingDisclosure jsonDocument) throws Exception {
		Document document = null;
		try {
			document = dbf.newDocumentBuilder().newDocument();
			Element message = (Element) document.appendChild(document.createElement(addNamespace("MESSAGE")));
			insertMessage(document, message, jsonDocument);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Transformer tr = TransformerFactory.newInstance().newTransformer();
        tr.setOutputProperty(OutputKeys.INDENT, "yes");
        tr.setOutputProperty(OutputKeys.METHOD, "xml");
        tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        StreamResult result = new StreamResult(new StringWriter());
      //  ByteArrayOutputStream out = new ByteArrayOutputStream();
        removeEmptyNodes(document);
        tr.transform(new DOMSource(document), result);
        String xmlString = result.getWriter().toString();
		return xmlString;
	}
	
	private String addNamespace(String tag) {
		return (tag.indexOf(':') == -1 ? MISMO_ALIAS + ":" : "") + tag;
	}
	/**
	 * Inserts Data to XML Object from JSON Object
	 * @param document Output XML file
	 * @param element parent node of XML
	 * @param element parent node of XMLName
	 * @param element parent node of XMLValue
	 * @return
	 */
	private Element insertData(Document document, Element element, String elementName, String elementValue) {
		Element e = null;
		if (elementValue != null && !elementValue.isEmpty()) {
			e = (Element)element.appendChild(document.createElement(addNamespace(elementName)));
			e.appendChild(document.createTextNode(elementValue));
		}
		return element;
	}
	/**
	 * Inserts Data to XML Object from JSON Object
	 * @param document Output XML file
	 * @param element parent node of XML
	 * @param element parent node of XMLName
	 * @param element parent node of XMLValue
	 * @return
	 */
	private Element returnElement(Document document, Element element, String elementName, String elementValue) {
		Element e = null;
		if (elementValue != null && !elementValue.isEmpty()) {
			e = (Element)element.appendChild(document.createElement(addNamespace(elementName)));
			e.appendChild(document.createTextNode(elementValue));
		}
		return e;
	}
	/**
	 * Inserts Levels from JSON Object
	 * @param document Output XML file
	 * @param element parent node of XML
	 * @param element parent node of XMLName
	 * @param element parent node of XMLValue
	 * @return
	 */
	private Element insertLevels(Document xmlout, Element parentElement, String path) {
		Element parent = parentElement;
		for (String container : path.split("/"))
			parent = (Element) parent.appendChild(xmlout.createElement(addNamespace(container)));
		return parent;
	}
	/**
     * Inserts About Version from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertAboutVersion(Document document, Element element, ClosingDisclosure jsonDocument) {
		insertData(document, element, "AboutVersionIdentifier", "TRIDent Web Toolkit, v0.1"); //TODO: This datapoint is not found in UCD Spec. 
		insertData(document, element, "CreatedDatetime", Convertor.getUTCDate());
	}
	/**
     * Inserts Closing Information Detail from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertClosingInformationDetail(Document document, Element element, ClosingInformationDetailModel closingInformationDetailModel) {
		insertData(document, element, "CashFromBorrowerAtClosingAmount", Convertor.checkAmountFormat(closingInformationDetailModel.getCashFromBorrowerAtClosingAmount()));
		insertData(document, element, "CashFromSellerAtClosingAmount", Convertor.checkAmountFormat(closingInformationDetailModel.getCashFromSellerAtClosingAmount()));
		insertData(document, element, "CashToBorrowerAtClosingAmount", Convertor.checkAmountFormat(closingInformationDetailModel.getCashToBorrowerAtClosingAmount()));
		insertData(document, element, "CashToSellerAtClosingAmount", Convertor.checkAmountFormat(closingInformationDetailModel.getCashToSellerAtClosingAmount()));
		insertData(document, element, "ClosingAgentOrderNumberIdentifier", closingInformationDetailModel.getClosingAgentOrderNumberIdentifier());
		insertData(document, element, "ClosingDate", closingInformationDetailModel.getClosingDate());
		insertData(document, element, "CurrentRateSetDate", closingInformationDetailModel.getCurrentRateSetDate());
		insertData(document, element, "DisbursementDate", closingInformationDetailModel.getDisbursementDate());
		insertData(document, element, "DocumentOrderClassificationType", closingInformationDetailModel.getDocumentOrderClassificationType()); //TODO: This datapoint is not found in UCD Spec.
	}
    /**
     * Inserts Deal to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertDeal(Document document, Element element, ClosingDisclosure jsonDocument) {
		insertSubjectProperty(document, insertLevels(document, element, "COLLATERALS/COLLATERAL/SUBJECT_PROPERTY"), jsonDocument);
		if(jsonDocument.getLiabilityList().size() > 0)
			insertLiabilities(document, insertLevels(document, element, "LIABILITIES"), jsonDocument.getLiabilityList());
		insertLoan(document, insertLevels(document, element, "LOANS/LOAN"), jsonDocument);
		   insertParties(document, insertLevels(document, element, "PARTIES"), jsonDocument);
		
	}
	
	/**
	 * inserts Liabilities to MISMO XML 
	 * @param document
	 * @param element
	 * @param liabilityList
	 */
	private void insertLiabilities(Document document, Element element, List<LiabilityModel> liabilityList) {
		for (LiabilityModel liabilityModel : liabilityList)
			insertLiability(document, insertLevels(document, element, "LIABILITY"), liabilityModel);
	}
	
	/**
	 * inserts Liability from JSON Object to XML
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertLiability(Document document, Element element, LiabilityModel liabilityModel) {
		 insertLiabilityDetail(document, insertLevels(document, element, "LIABILITY_DETAIL"), liabilityModel);
		 insertLiabilityHolder(document, insertLevels(document, element, "LIABILITY_HOLDER"), liabilityModel);
		 insertPayoff(document, insertLevels(document, element, "PAYOFF"), liabilityModel);
	}
	
	/**
	 * inserts Payoff to MISMO XML
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertPayoff(Document document, Element element, LiabilityModel liabilityModel) {
		OtherModel other = new OtherModel();
		insertData(document, element, "PayoffAmount", liabilityModel.getPayoffAmount());
		insertData(document, element, "PayoffPrepaymentPenaltyAmount",Convertor.checkAmountFormat(liabilityModel.getPayoffPrepaymentPenaltyAmount()));
		insertExtension(document, insertLevels(document, element, "EXTENSION"), other);
	}
	
	/** 
	 * inserts Liability Holder to MISMO XML
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	 private void insertLiabilityHolder(Document document, Element element,
			 LiabilityModel liabilityModel) {
		NameModel name = new NameModel();
			name.setFullName(liabilityModel.getLiabilityHolderFullName());
		insertName(document, insertLevels(document, element, "NAME"), name);
	 }
	 
	/**
	 * inserts LiabilityDetail to MISMO XML
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertLiabilityDetail(Document document, Element element,
			LiabilityModel liabilityModel) {
		OtherModel other = new OtherModel();
			other.setIntegratedDisclosureSectionType(liabilityModel.getIntegratedDisclosureSectionType());
			other.setLiabilitySecuredBySubjectPropertyIndicator(Boolean.toString(liabilityModel.isLiabilitySecuredBySubjectPropertyIndicator()));
			
		insertData(document, element, "LiabilityDescription", liabilityModel.getLiabilityDescription());
		Element liabilityTypeElement = returnElement(document, element, "LiabilityType", liabilityModel.getLiabilityType());
			if(null != liabilityTypeElement && null != liabilityModel.getDisplayLabel() && !liabilityModel.getDisplayLabel().isEmpty())
				liabilityTypeElement.setAttribute("gse:DisplayLabelText", liabilityModel.getDisplayLabel());
		insertData(document, element, "LiabilityTypeOtherDescription", liabilityModel.getLiabilityTypeOtherDescription());
		insertExtension(document, insertLevels(document, element, "EXTENSION"),other);
	}
	
	/**
	 * inserts Deal Set to MISMO XML
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertDealSet(Document document, Element element, ClosingDisclosure jsonDocument) {
		insertDeal(document, insertLevels(document, element, "DEALS/DEAL"), jsonDocument);
	}
	
	/**
     * inserts Deal Sets to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertDealSets(Document document, Element element, ClosingDisclosure jsonDocument) {
		insertDealSet(document, insertLevels(document, element, "DEAL_SET"), jsonDocument);
		Element parties =  insertLevels(document, element, "PARTIES");
		insertPartyRoleIdentifier(document, parties, "Actualize", "www.fanniemae.com");
		insertPartyRoleIdentifier(document, parties, "000100", "www.freddiemac.com");
			
	}
	
	private void insertPartyRoleIdentifier(Document document, Element element, String partyRoleIdentifier, String identifierOwnerURI)
	{
		Element party =  insertLevels(document, element, "PARTY");
		Element role =  insertLevels(document, party, "ROLES/ROLE");
		Element roleIdentifier = insertLevels(document, role, "PARTY_ROLE_IDENTIFIERS/PARTY_ROLE_IDENTIFIER");
		Element actualize = returnElement(document, roleIdentifier, "PartyRoleIdentifier", partyRoleIdentifier);
			actualize.setAttribute("IdentifierOwnerURI", identifierOwnerURI);
		Element roleDetail =  insertLevels(document, role, "ROLE_DETAIL");
			insertData(document, roleDetail, "PartyRoleType", "LoanDeliveryFilePreparer");
	}
	/**
     * Inserts Document Set to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertDocumentSet(Document document, Element element, ClosingDisclosure jsonDocument) {
		insertDocuments(document, insertLevels(document, element, "DOCUMENTS"), jsonDocument);
	}
	/**
     * Inserts Documents to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertDocuments(Document document, Element element, ClosingDisclosure jsonDocument) {
		insertDocument(document, insertLevels(document, element, "DOCUMENT"), jsonDocument);
	}
	/**
     * Inserts Document to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */	
	private void insertDocument(Document document, Element element, ClosingDisclosure jsonDocument) {
		element.setAttribute("MISMOReferenceModelIdentifier", "3.3.0299");
		insertDealSets(document, insertLevels(document, element, "DEAL_SETS"), jsonDocument);
	//	insertAuditTrail(document, insertLevels(document, element, "AUDIT_TRAIL"), jsonDocument);
		insertRelationships(document, insertLevels(document, element, "RELATIONSHIPS"), relationships);
		insertSignatories(document, insertLevels(document, element, "SIGNATORIES"), signatories, jsonDocument.getClosingDisclosureDocDetails());
		//insertSystemSignatures(document, insertLevels(document, element, "SYSTEM_SIGNATORIES"), jsonDocument);
		Element view = null;
		if(jsonDocument.isEmbeddedPDF())
			 view = insertLevels(document, element, "VIEWS");
		insertAboutVersions(document, insertLevels(document, element, "ABOUT_VERSIONS"), null);
		insertDocumentClassification(document, insertLevels(document, element, "DOCUMENT_CLASSIFICATION"), jsonDocument.getClosingDisclosureDocDetails());
		if(jsonDocument.isEmbeddedPDF())
			insertViews(document, view);
	}
	
	/**
	 * inserts DocumentClassification in MISMO XML
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertDocumentClassification(Document document, Element element,
			ClosingDisclosureDocumentDetails closingDisclosureDocDetails) {
		insertDocumentClasses(document,	insertLevels(document, element, "DOCUMENT_CLASSES"), closingDisclosureDocDetails);
		insertDocumentClassificationDetail(document, insertLevels(document, element, "DOCUMENT_CLASSIFICATION_DETAIL"), closingDisclosureDocDetails);
	}
	
	private void insertDocumentClassificationDetail(Document document, Element element,
			ClosingDisclosureDocumentDetails closingDisclosureDocDetails) {
		OtherModel other = new OtherModel();
			other.setDocumentSignatureRequiredIndicator(Convertor.booleanToString(closingDisclosureDocDetails.isDocumentSignatureRequiredIndicator()));
		insertData(document, element, "DocumentFormIssuingEntityNameType","CFPB");
		insertData(document, element, "DocumentFormIssuingEntityVersionIdentifier", "11-20-2013");
		insertExtension(document, insertLevels(document, element, "EXTENSION"), other);
	}
	
	private void insertDocumentClasses(Document document, Element element,
			ClosingDisclosureDocumentDetails closingDisclosureDocDetails) {
		//for (String group : groupings)
			insertDocumentClass(document, insertLevels(document, element, "DOCUMENT_CLASS"), closingDisclosureDocDetails);
	}
	
	private void insertDocumentClass(Document document, Element element, ClosingDisclosureDocumentDetails closingDisclosureDocDetails) {

		insertData(document, element, "DocumentType", "Other");
		insertData(document, element, "DocumentTypeOtherDescription", closingDisclosureDocDetails.getDocumentType()+":"+closingDisclosureDocDetails.getFormType());
	}
	/**
     * Inserts Views to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertViews(Document document, Element element) {
		//for (String group : groupings)
		Element view = insertLevels(document, element, "VIEW");
			view.setAttribute("SequenceNumber", "1");
			insertView(document, view);
	}
	/**
     * Inserts View to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertView(Document document, Element element) {
		insertViewFile(document, insertLevels(document, element, "VIEW_FILES/VIEW_FILE"));
	}
	/**
     * Inserts View File to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertViewFile(Document document, Element element) {
		insertForeignObject(document, insertLevels(document, element, "FOREIGN_OBJECT"));
	}
	/**
     * Inserts Foreign Object to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertForeignObject(Document document, Element element){
		//insertLevels(document, element, ""); 
		ClosingDisclosureServicesImpl closingDisclosureServicesImpl = new ClosingDisclosureServicesImpl();
		Transformer tr = null;
		try {
			tr = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        tr.setOutputProperty(OutputKeys.INDENT, "yes");
        tr.setOutputProperty(OutputKeys.METHOD, "xml");
        tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        StreamResult result = new StreamResult(new StringWriter());
      //  ByteArrayOutputStream out = new ByteArrayOutputStream();
        //removeEmptyNodes(document);
        try {
			tr.transform(new DOMSource(document), result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String xmlString = result.getWriter().toString();
        
        List<PDFResponse> pdf = closingDisclosureServicesImpl.createPDF(xmlString);
		try {
			element.appendChild(document.createElement(addNamespace("EmbeddedContentXML")))
					.appendChild(document.createTextNode(Base64.getEncoder().encodeToString( new String(pdf.get(0).getResponseData()).getBytes( "utf-8" ) )));
		} catch (DOMException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        element.appendChild(document.createElement(addNamespace("MIMETypeIdentifier")))
               .appendChild(document.createTextNode("application/pdf"));
        element.appendChild(document.createElement(addNamespace("ObjectEncodingType")))
               .appendChild(document.createTextNode("Base64"));
        element.appendChild(document.createElement(addNamespace("ObjectName")))
               .appendChild(document.createTextNode("ClosingDisclosure.pdf"));
	}
	/**
     * Inserts System Signatories from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*
	private void insertSystemSignatures(Document document, Element element,
			ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		//for (String group : groupings)
			insertSignature(document, insertLevels(document, element, "SYSTEM_SIGNATURE"), jsonDocument);
	}
	*//**
     * Inserts Signature from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*
	private void insertSignature(Document document, Element element, ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		insertData(document, element, "XMLDigitalSignatureElement", "");
	}
	*//**
     * Inserts Signatories to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param signatories list of signatories
     */	
	private void insertSignatories(Document document, Element element, List<SignatoriesModel> signatories, ClosingDisclosureDocumentDetails closingDisclosureDocumentDetails) {
		signatories.sort(new Comparator<SignatoriesModel>(){
			@Override
			public int compare(SignatoriesModel o1, SignatoriesModel o2) {
				return Integer.parseInt(o1.getSequenceNumber()) - Integer.parseInt(o2.getSequenceNumber());
			}
		});
		for (SignatoriesModel signatory : signatories)
			insertSignatory(document, insertLevels(document, element, "SIGNATORY"), signatory, closingDisclosureDocumentDetails);
	}
	/**
     * Inserts Signatory to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param signatory Input SignatoriesModel Object
     */	
	private void insertSignatory(Document document, Element element, SignatoriesModel signatory, ClosingDisclosureDocumentDetails closingDisclosureDocumentDetails) {
		element.setAttribute("SequenceNumber", signatory.getSequenceNumber());
		element.setAttribute(XLINK_ALIAS + ":label", signatory.getXlabel());
		insertExecution(document, insertLevels(document, element, "EXECUTION"), closingDisclosureDocumentDetails);
	}
	/**
     * Inserts Relationships to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param list of RelationshipsModel 
     */		
	private void insertRelationships(Document document, Element element, List<RelationshipsModel> relationships) {
		
		relationships.sort(new Comparator<RelationshipsModel>(){
			@Override
			public int compare(RelationshipsModel o1, RelationshipsModel o2) {
				return Integer.parseInt(o1.getSequenceNumber()) - Integer.parseInt(o2.getSequenceNumber());
			}
		});
		
		for (RelationshipsModel relationship : relationships)
			insertRelationship(document, insertLevels(document, element, "RELATIONSHIP"), relationship);
	}
	/**
     * Inserts Relationship to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param RelationshipsModel 
     */	
	private void insertRelationship(Document document, Element element, RelationshipsModel relationship) {
		element.setAttribute("SequenceNumber", relationship.getSequenceNumber());
		element.setAttribute(XLINK_ALIAS + ":from", relationship.getXlinkFrom());
		element.setAttribute(XLINK_ALIAS + ":to", relationship.getXlinkTo());
		element.setAttribute(XLINK_ALIAS + ":arcrole", relationship.getXlinkArcrole());
	}
	/**
     * Inserts Audit Trail from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*	
	private void insertAuditTrail(Document document, Element element, ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		insertAuditTrailEntries(document, insertLevels(document, element, "AUDIT_TRAIL_ENTRIES"), jsonDocument);
	}
	*//**
     * Inserts Audit Trail Entries from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*	
	private void insertAuditTrailEntries(Document document, Element element,
			ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		insertAuditTrailEntry(document, insertLevels(document, element, "AUDIT_TRAIL_ENTRY"), jsonDocument);
	}
	*//**
     * Inserts Audit Trail Entry from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*		
	private void insertAuditTrailEntry(Document document, Element element,
			ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		insertAuditTrailEntryDetail(document, insertLevels(document, element, "AUDIT_TRAIL_ENTRY_DETAIL"), jsonDocument);
	}
	*//**
     * Inserts Audit Trail Entry Detail from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*	
	private void insertAuditTrailEntryDetail(Document document, Element element,
			ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		insertData(document, element, "EntryDatetime", "");
		insertData(document, element, "EventType", "");
		insertData(document, element, "EventTypeOtherDescription", "");
	}
	*//**
     * Inserts About Versions to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */	
	private void insertAboutVersions(Document document, Element element, ClosingDisclosure jsonDocument) {
		//for (String group : groupings)
		insertAboutVersionsInDocument(document, insertLevels(document, element, "ABOUT_VERSION"), jsonDocument);
			
	}
	/**
	 * Inserts About Versions under documents to MISMO XML
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertAboutVersionsInDocument(Document document, Element element, ClosingDisclosure jsonDocument) {
		// TODO Auto-generated method stub
		//for (String group : groupings)
		Element aboutVersionIdentifier = returnElement(document, element, "AboutVersionIdentifier", "Retrievable"); //TODO: This datapoint is not found in UCD Spec.
			aboutVersionIdentifier.setAttribute("IdentifierOwnerURI","http://www.mismo.org/residential/2009/SMARTDocProfile");
		insertData(document, element, "DataVersionIdentifier", "UCD Delivery Specification 1.4");
	}
	/**
     * inserts Integrated Disclosure Detail to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertIntegratedDisclosureDetail(Document document, Element element, IntegratedDisclosureDetailModel integratedDisclosureDetail) {
		insertData(document, element, "FirstYearTotalEscrowPaymentAmount", Convertor.checkAmountFormat(integratedDisclosureDetail.getFirstYearTotalEscrowPaymentAmount()));
		insertData(document, element, "FirstYearTotalEscrowPaymentDescription", integratedDisclosureDetail.getFirstYearTotalEscrowPaymentDescription());
		insertData(document, element, "FirstYearTotalNonEscrowPaymentAmount", Convertor.checkAmountFormat(integratedDisclosureDetail.getFirstYearTotalNonEscrowPaymentAmount()));
		insertData(document, element, "FirstYearTotalNonEscrowPaymentDescription", integratedDisclosureDetail.getFirstYearTotalNonEscrowPaymentDescription());
		insertData(document, element, "IntegratedDisclosureHomeEquityLoanIndicator", Boolean.toString(integratedDisclosureDetail.isIntegratedDisclosureHomeEquityLoanIndicator()));
		insertData(document, element, "IntegratedDisclosureIssuedDate", integratedDisclosureDetail.getIntegratedDisclosureIssuedDate());
		insertData(document, element, "IntegratedDisclosureLoanProductDescription", integratedDisclosureDetail.getIntegratedDisclosureLoanProductDescription());
		
	}
	/**
	 * inserts Loan to MISMO XML
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertLoan(Document document, Element element, ClosingDisclosure jsonDocument) {
		
		insertAdjustment(document, insertLevels(document, element, "ADJUSTMENT"), jsonDocument);
		insertAmortizationRule(document, insertLevels(document, element, "AMORTIZATION/AMORTIZATION_RULE"), jsonDocument.getLoanInformation());
		insertBuydown(document, insertLevels(document, element, "BUYDOWN"), jsonDocument.getLoanTerms().getLoanTermsTemporaryBuydown());
		insertClosingInformation(document, insertLevels(document, element, "CLOSING_INFORMATION"), jsonDocument);
		insertConstruction(document, insertLevels(document, element, "CONSTRUCTION"), jsonDocument.getConstruction());
		insertDocumentSpecificDataSet(document, insertLevels(document, element, "DOCUMENT_SPECIFIC_DATA_SETS/DOCUMENT_SPECIFIC_DATA_SET"), jsonDocument);
		insertEscrow(document, insertLevels(document, element, "ESCROW"), jsonDocument);
		insertFeeInformation(document, insertLevels(document, element, "FEE_INFORMATION"), jsonDocument);
		insertForeclosures(document, insertLevels(document, element, "FORECLOSURES"), Boolean.toString(jsonDocument.getLoanCalculationsQualifiedMortgage().getLoanCalculationModel().isDeficiencyRightsPreservedIndicator()).toLowerCase()); // Not needed for LE
		/*insertHeloc(document, insertLevels(document, element, "HELOC"), jsonDocument); // Not needed for LE*/
		insertHighCostMortgages(document, insertLevels(document, element, "HIGH_COST_MORTGAGES"), jsonDocument.getLoanCalculationsQualifiedMortgage().getQualifiedMortgage()); //  Not needed for LE
		//insertHmdaLoan(document, insertLevels(document, element, "HMDA_LOAN"), jsonDocument); //  Not needed for LE*/
		insertInterestOnly(document, insertLevels(document, element, "INTEREST_ONLY"), jsonDocument.getInterestOnly());
		insertLateChargeRule(document, insertLevels(document, element, "LATE_CHARGE/EXTENSION/OTHER/gse:LATE_CHARGE_RULES/gse:LATE_CHARGE_RULE"), jsonDocument.getLateChargeRule());
		insertLoanDetail(document, insertLevels(document, element, "LOAN_DETAIL"), jsonDocument.getLoanDetail());
		insertLoanIdentifiers(document, insertLevels(document, element, "LOAN_IDENTIFIERS"), jsonDocument.getLoanInformation().getLoanIdentifiers());
		//insertLoanLevelCredit(document, insertLevels(document, element, "LOAN_LEVEL_CREDIT"), jsonDocument);
		if(null != jsonDocument.getLoanProduct().getLoanPriceQuoteInterestRatePercent() && ! jsonDocument.getLoanProduct().getLoanPriceQuoteInterestRatePercent().isEmpty())
			insertLoanProduct(document, insertLevels(document, element, "LOAN_PRODUCT"), jsonDocument.getLoanProduct());
		insertMaturityRule(document, insertLevels(document, element, "MATURITY/MATURITY_RULE"), jsonDocument.getMaturityRule());
		insertMIData(document, insertLevels(document, element, "MI_DATA"), jsonDocument);
		insertNegativeAmortization(document, insertLevels(document, element, "NEGATIVE_AMORTIZATION"), jsonDocument.getNegativeAmortization());
		insertPayment(document, insertLevels(document, element, "PAYMENT"), jsonDocument.getPayment());
		insertPrepaymentPenalty(document, insertLevels(document, element, "PREPAYMENT_PENALTY"), jsonDocument.getLoanTerms().getLoanTermsPrepaymentPenalty());
		//insertQualification(document, insertLevels(document, element, "QUALIFICATION"), jsonDocument); //Not needed for LE
		insertQualifiedMortgage(document, insertLevels(document, element, "QUALIFIED_MORTGAGE"), jsonDocument.getLoanCalculationsQualifiedMortgage().getQualifiedMortgage()); // Not needed for LE
		insertRefinance(document, insertLevels(document, element, "REFINANCE"),Convertor.booleanToString(jsonDocument.getTransactionInformation().getRefinanceSameLenderIndicator()).toLowerCase()); //Not needed for LE
		/*insertReverseMortgage(document, insertLevels(document, element, "REVERSE_MORTGAGE"), jsonDocument); //Not needed for LE
		insertServicing(document, insertLevels(document, element, "SERVICING"), jsonDocument); // Not needed for LE
*/		insertTermsOfLoan(document, insertLevels(document, element, "TERMS_OF_LOAN"), jsonDocument.getTermsOfLoan());
		insertUnderwriting(document, insertLevels(document, element, "UNDERWRITING"), jsonDocument); // Not needed for LE
	
	}
	
    /**
     * Inserts Underwriting to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertUnderwriting(Document document, Element element, ClosingDisclosure jsonDocument) {
		insertAutomatedUnderwritings(document, insertLevels(document, element, "AUTOMATED_UNDERWRITINGS"), jsonDocument.getLoanInformation().getAutomatedUnderwritings());
		insertUnderwritingDetail(document, insertLevels(document, element, "UNDERWRITING_DETAIL"), Boolean.toString(jsonDocument.getLoanInformation().isLoanManualUnderwritingIndicator()));
	}
	/**
     * Inserts Underwriting Detail to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertUnderwritingDetail(Document document, Element element,
			String loanManualUnderwritingIndicator) {
		insertData(document, element, "LoanManualUnderwritingIndicator", loanManualUnderwritingIndicator);
	}

	/**
    * Inserts Automated Underwritings to MISMO XML
    * @param document Output XML file
    * @param element parent node of XML
    * @param jsonDocument Input JSON Object
    */
    private void insertAutomatedUnderwritings(Document document, Element element,
    		List<AutomatedUnderwritingsModel> automatedUnderwritingsModels) {
    	for (AutomatedUnderwritingsModel automatedUnderwritings : automatedUnderwritingsModels)
			insertAutomatedUnderwriting(document, insertLevels(document, element, "AUTOMATED_UNDERWRITING"), automatedUnderwritings);
	}
    /**
     * Inserts Automated Underwriting to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertAutomatedUnderwriting(Document document, Element element,
			AutomatedUnderwritingsModel automatedUnderwritings) {
		insertData(document, element, "AutomatedUnderwritingCaseIdentifier", automatedUnderwritings.getAutomatedUnderwritingCaseIdentifier());
		//insertData(document, element, "AutomatedUnderwritingRecommendationDescription", automatedUnderwritings.get);
		insertData(document, element, "AutomatedUnderwritingSystemType", automatedUnderwritings.getAutomatedUnderwritingSystemType());
		insertData(document, element, "AutomatedUnderwritingSystemTypeOtherDescription", automatedUnderwritings.getAutomatedUnderwritingSystemTypeOtherDescription());
	}

	/**
     * Inserts Servicing from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*
	private void insertServicing(Document document, Element element, ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		insertServicingDetail(document, insertLevels(document, element, "SERVICING_DETAIL"), jsonDocument);
	}
	*//**
     * Inserts Servicing Detail from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*
    private void insertServicingDetail(Document document, Element element,
			ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
    	insertData(document, element, "LoanAcquisitionActualUPBAmount", "");
	}

	*//**
     * Inserts Reverse Mortgage from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*
	private void insertReverseMortgage(Document document, Element element, ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		insertData(document, element, "ReverseInitialPrincipalLimitAmount", "");
	}
    *//**
     * Inserts Refinace to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertRefinance(Document document, Element element, String refinanceSameLenderIndicator) {
		//insertData(document, element, "RefinanceCashOutDeterminationType", "");
		insertData(document, element, "RefinanceSameLenderIndicator", refinanceSameLenderIndicator);
	}
	/**
	 * Inserts Qualified Mortgage to MISMO XML
	 * @param document Output XML file
	 * @param element parent node of XML
	 * @param jsonDocument Input JSON Object
	 */
	private void insertQualifiedMortgage(Document document, Element element, QualifiedMortgageModel qualifiedMortgage) {
		if(!"".equals(qualifiedMortgage.getAbilityToRepayExemptionReasonType()))
			insertExemption(document, insertLevels(document, element, "EXEMPTIONS/EXEMPTION"), qualifiedMortgage.getAbilityToRepayExemptionReasonType());
		if(!"".equals(qualifiedMortgage.getAbilityToRepayMethodType()))
			insertQualifiedMortgageDetail(document, insertLevels(document, element, "QUALIFIED_MORTGAGE_DETAIL"), qualifiedMortgage.getAbilityToRepayMethodType());
	}
	/**
	 * Inserts Qualified Mortgage Detail to MISMO XML
	 * @param document Output XML file
	 * @param insertLevels
	 * @param jsonDocument Input JSON Object
	 */
	private void insertQualifiedMortgageDetail(Document document, Element element,
			String abilityToRepayMethodType) {
		insertData(document, element, "AbilityToRepayMethodType", abilityToRepayMethodType);
	}
	/**
	 * Inserts Exemption to MISMO XML
	 * @param document Output XML file
	 * @param insertLevels
	 * @param jsonDocument Input JSON Object
	 */
	private void insertExemption(Document document, Element element, String abilityToRepayMethodType) {
		insertData(document, element, "AbilityToRepayExemptionReasonType", abilityToRepayMethodType);
	}
	/**
	 * inserts Qualification from JSON Object
	 * @param document Output XML file
	 * @param element parent node of XML
	 * @param jsonDocument Input JSON Object
	 *//*
	private void insertQualification(Document document, Element element, ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		insertData(document, element, "TotalMonthlyIncomeAmount", "");
		insertData(document, element, "IncomeConsideredInDecisionIndicator", "");
		insertData(document, element, "CreditScoreConsideredInDecisionIndicator", "");
		insertData(document, element, "TotalDebtExpenseRatioPercent", "");
		insertData(document, element, "TotalDebtExpenseRatioConsideredInDecisionIndicator", "");
		insertData(document, element, "CombinedLTVRatioConsideredInDecisionIndicator", "");
	}
	*//**
     * Inserts Prepayment Penalty to MISMO Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPrepaymentPenalty(Document document, Element element, LoanTermsPrepaymentPenalty prepaymentPenalty ) {
		insertPrepaymentPenaltyLifetimeRule(document, insertLevels(document, element, "PREPAYMENT_PENALTY_LIFETIME_RULE"), prepaymentPenalty);
	}
	/**
     * Inserts Prepayment Penalty Lifetime Rule to MISMO Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPrepaymentPenaltyLifetimeRule(Document document, Element element,
			LoanTermsPrepaymentPenalty prepaymentPenalty) {
		//insertData(document, element, "PrepaymentPenaltyExpirationDate", prepaymentPenalty.get);
		insertData(document, element, "PrepaymentPenaltyExpirationMonthsCount", prepaymentPenalty.getPrepaymentPenaltyExpirationMonthsCount());
		insertData(document, element, "PrepaymentPenaltyMaximumLifeOfLoanAmount", Convertor.checkAmountFormat(prepaymentPenalty.getPrepaymentPenaltyMaximumLifeOfLoanAmount()));
	}
	/**
     * Inserts Payment to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPayment(Document document, Element element, PaymentModel payment) {
		insertPartialPayments(document, insertLevels(document, element, "PARTIAL_PAYMENTS"), payment.getPartialPayments()); 
		insertPaymentRule(document, insertLevels(document, element, "PAYMENT_RULE"), payment.getPaymentRule());
	}
	/**
     * Inserts Payment Rule to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPaymentRule(Document document, Element element, PaymentRuleModel paymentRule) {
		insertData(document, element, "FullyIndexedInitialPrincipalAndInterestPaymentAmount", Convertor.checkAmountFormat(paymentRule.getFullyIndexedInitialPrincipalAndInterestPaymentAmount()));
		insertData(document, element, "InitialPrincipalAndInterestPaymentAmount", Convertor.checkAmountFormat(paymentRule.getInitialPrincipalAndInterestPaymentAmount()));
		insertData(document, element, "PartialPaymentAllowedIndicator", Boolean.toString(paymentRule.isPartialPaymentAllowedIndicator()));
		insertData(document, element, "PaymentFrequencyType", paymentRule.getPaymentFrequencyType());
		insertData(document, element, "PaymentOptionIndicator", Boolean.toString(paymentRule.isPaymentOptionIndicator()));
		insertData(document, element, "SeasonalPaymentPeriodEndMonth", paymentRule.getSeasonalPaymentPeriodEndMonth());
		insertData(document, element, "SeasonalPaymentPeriodStartMonth", paymentRule.getSeasonalPaymentPeriodStartMonth());
		OtherModel other = new OtherModel();
			other.setTotalOptionalPaymentCount(paymentRule.getTotalOptionalPaymentCount());
			other.setTotalStepPaymentCount(paymentRule.getTotalStepPaymentCount());
		insertExtension(document, insertLevels(document, element, "EXTENSION"), other);
	}
	/**
     * Inserts Partial Payments to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPartialPayments(Document document, Element element,
			PartialPaymentsModel partialPayments) {
		for (PartialPaymentModel partialPayment : partialPayments.getPartialPaymentModels())
			insertPartialPayment(document, insertLevels(document, element, "PARTIAL_PAYMENT"), partialPayment);
	}
	/**
     * Inserts Partial Payments to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPartialPayment(Document document, Element element, PartialPaymentModel partialPayment) {
		insertData(document, element, "PartialPaymentApplicationMethodType", partialPayment.getPartialPaymentApplicationMethodType());
		insertData(document, element, "PartialPaymentApplicationMethodTypeOtherDescription", partialPayment.getPartialPaymentApplicationMethodTypeOtherDescription());
	}
	/**
     * Inserts Negative Amortization to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertNegativeAmortization(Document document, Element element, NegativeAmortizationModel negativeAmortization) {
		insertNegativeAmortizationRule(document, insertLevels(document, element, "NEGATIVE_AMORTIZATION_RULE"), negativeAmortization);
	}
	/**
     * Inserts Negative Amortization Rule to MISMO XML 
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertNegativeAmortizationRule(Document document, Element element,
			NegativeAmortizationModel negativeAmortization) {
		//insertData(document, element, "LoanNegativeAmortizationResolutionType", "");
		//insertData(document, element, "LoanNegativeAmortizationResolutionTypeOtherDescription", "");
		insertData(document, element, "NegativeAmortizationLimitMonthsCount", negativeAmortization.getNegativeAmortizationLimitMonthsCount());
		insertData(document, element, "NegativeAmortizationMaximumLoanBalanceAmount", Convertor.checkAmountFormat(negativeAmortization.getNegativeAmortizationMaximumLoanBalanceAmount()));
		insertData(document, element, "NegativeAmortizationType", negativeAmortization.getNegativeAmortizationType());
	}
	/**
     * Inserts MI Data Detail to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param miDataDetail Input MIDataDetailModel Object
     */
	private void insertMIDataDetail(Document document, Element element, MIDataDetailModel miDataDetail) {
		insertData(document, element, "MICertificateIdentifier", miDataDetail.getMiCertificateIdentifier());
		insertData(document, element, "MICompanyNameType", miDataDetail.getMiCompanyNameType());
		insertData(document, element, "MICompanyNameTypeOtherDescription",  miDataDetail.getMiCompanyNameTypeOtherDescription());
		insertData(document, element, "MIScheduledTerminationDate",  miDataDetail.getMiScheduledTerminationDate());
		
	}
	/**
     * Inserts MI Premiums to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param MIPremiumList Input List of MIPremium Object
     */
	private void insertMIPremium(Document document, Element element, List<MIPremiumModel> miPremiumList) {
		for(MIPremiumModel miPremiumModel: miPremiumList)
			if(Convertor.checkAmountNotNull(miPremiumModel.getMiPremiumRateDurationMonthsCount()) && Convertor.checkAmountNotNull(miPremiumModel.getMiPremiumRatePercent()))
				insertMIPremiumDetail(document, insertLevels(document, element, "MI_PREMIUM/MI_PREMIUM_DETAIL"), miPremiumModel);
	}
	
	/**
     * Inserts MI Premium Detail to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param miPremiumModel Input MIPremiumModel Object
     */
	private void insertMIPremiumDetail(Document document, Element element, MIPremiumModel miPremiumModel) {
		insertData(document, element, "MIPremiumPeriodType", miPremiumModel.getMiPremiumPeriodType());
		insertData(document, element, "MIPremiumRateDurationMonthsCount", miPremiumModel.getMiPremiumRateDurationMonthsCount());
		insertData(document, element, "MIPremiumRatePercent", miPremiumModel.getMiPremiumRatePercent());
	}
	/**
     * Inserts Loan Product to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertLoanProduct(Document document, Element element, LoanProductModel loanProduct) {
		insertLoanPriceQuotes(document, insertLevels(document, element, "LOAN_PRICE_QUOTES"), loanProduct);
		//insertLocks(document, insertLevels(document, element, "LOCKS"), jsonDocument);
	}
	/**
     * Inserts Locks from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*
	private void insertLocks(Document document, Element element, ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		//for (String group : groupings)
			insertLock(document, insertLevels(document, element, "LOCK"), jsonDocument);
	}
	*//**
     * Inserts Lock from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*
	private void insertLock(Document document, Element element, ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		insertData(document, element, "LockExpirationDatetime", "");
		insertData(document, element, "LockStatusType", "");
		insertExtension(document, insertLevels(document, element, "EXTENSION"), jsonDocument);
	}
	*//**
     * Inserts Loan Price Quotes to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertLoanPriceQuotes(Document document, Element element,
			LoanProductModel loanProduct) {
		//for (String group : groupings)
			insertLoanPriceQuote(document, insertLevels(document, element, "LOAN_PRICE_QUOTE"), loanProduct);
	}
	/**
     * Inserts Loan Price Quote to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertLoanPriceQuote(Document document, Element element, LoanProductModel loanProduct) {
		insertLoanPriceQuoteDetail(document, insertLevels(document, element, "LOAN_PRICE_QUOTE_DETAIL"), loanProduct);
	}
	/**
     * Inserts Loan Price Quote Detail to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertLoanPriceQuoteDetail(Document document, Element element,
			LoanProductModel loanProduct) {
		insertData(document, element, "LoanPriceQuoteInterestRatePercent", loanProduct.getLoanPriceQuoteInterestRatePercent());
	}
	/**
     * Inserts Loan Level Credit from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*
	private void insertLoanLevelCredit(Document document, Element element, ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		insertLoanLevelCreditDetail(document, insertLevels(document, element, "LOAN_LEVEL_CREDIT_DETAIL"), jsonDocument);
	}
	*//**
     * Inserts Loan Level Credit Detail from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*
	private void insertLoanLevelCreditDetail(Document document, Element element,
			ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		insertData(document, element, "LoanLevelCreditScoreValue", "");
		insertData(document, element, "CreditScoreModelNameType", "");
		insertData(document, element, "CreditScoreModelNameTypeOtherDescription", "");
		insertData(document, element, "CreditScoreCategoryVersionType", "");
	}
	*//**
     * Inserts Loan Detail to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertLoanDetail(Document document, Element element, LoanDetailModel loanDetail) {
			
		//insertData(document, element, "AssumedIndicator",);
		insertData(document, element, "AssumabilityIndicator", Boolean.toString(loanDetail.isAssumabilityIndicator()));
		insertData(document, element, "BalloonIndicator", Boolean.toString(loanDetail.isBalloonIndicator()));
		insertData(document, element, "BalloonPaymentAmount", Convertor.checkAmountFormat(loanDetail.getBalloonPaymentAmount()));
		insertData(document, element, "BuydownTemporarySubsidyFundingIndicator",Boolean.toString(loanDetail.isBuydownTemporarySubsidyFundingIndicator()));
		insertData(document, element, "ConstructionLoanIndicator",Boolean.toString(loanDetail.isConstructionLoanIndicator()));
		insertData(document, element, "CreditorServicingOfLoanStatementType", loanDetail.getCreditorServicingOfLoanStatementType());
		insertData(document, element, "DemandFeatureIndicator",Boolean.toString(loanDetail.isDemandFeatureIndicator()));
		insertData(document, element, "EscrowAbsenceReasonType",  loanDetail.getEscrowAbsenceReasonType()); 
		insertData(document, element, "EscrowIndicator", Boolean.toString(loanDetail.isEscrowIndicator()));
		insertData(document, element, "InterestOnlyIndicator", Boolean.toString(loanDetail.isInterestOnlyIndicator()));
		insertData(document, element, "InterestRateIncreaseIndicator", Boolean.toString(loanDetail.isInterestRateIncreaseIndicator()));
		insertData(document, element, "LoanAmountIncreaseIndicator", Boolean.toString(loanDetail.isLoanAmountIncreaseIndicator()));
		//insertData(document, element, "LoanLevelCreditScoreValue", ); 
		insertData(document, element, "MIRequiredIndicator", Boolean.toString(loanDetail.isMiRequiredIndicator()));
		insertData(document, element, "NegativeAmortizationIndicator", Boolean.toString(loanDetail.isNegativeAmortizationIndicator()));
		insertData(document, element, "PaymentIncreaseIndicator",Boolean.toString(loanDetail.isPaymentIncreaseIndicator()));  
		insertData(document, element, "PrepaymentPenaltyIndicator",Boolean.toString(loanDetail.isPrepaymentPenaltyIndicator()));
		insertData(document, element, "SeasonalPaymentFeatureIndicator", Boolean.toString(loanDetail.isSeasonalPaymentFeatureIndicator()));
		insertData(document, element, "StepPaymentsFeatureDescription", loanDetail.getStepPaymentsFeatureDescription());
		insertData(document, element, "TotalSubordinateFinancingAmount", Convertor.checkAmountFormat(loanDetail.getTotalSubordinateFinancingAmount()));
		OtherModel other = new OtherModel();
			other.setSubordinateFinancingIsNewIndicator(Boolean.toString(loanDetail.isSubordinateFinancingIsNewIndicator()).toLowerCase());
		insertExtension(document, insertLevels(document, element, "EXTENSION"), other);
	}
	/**
     * inserts Late Charge Rule to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertLateChargeRule(Document document, Element element, LateChargeRuleModel lateChargeRule) {
		insertData(document, element, GSE_ALIAS + ":LateChargeAmount", Convertor.checkAmountFormat(lateChargeRule.getLateChargeAmount()));
		insertData(document, element, GSE_ALIAS + ":LateChargeGracePeriodDaysCount", lateChargeRule.getLateChargeGracePeriodDaysCount().contains(".") ? lateChargeRule.getLateChargeGracePeriodDaysCount().split("\\.")[0]:lateChargeRule.getLateChargeGracePeriodDaysCount());
		insertData(document, element, GSE_ALIAS + ":LateChargeMaximumAmount", lateChargeRule.getLateChargeMaximumAmount());
		insertData(document, element, GSE_ALIAS + ":LateChargeMinimumAmount", lateChargeRule.getLateChargeMinimumAmount());
		insertData(document, element, GSE_ALIAS + ":LateChargeRatePercent", lateChargeRule.getLateChargeRatePercent());
		insertData(document, element, GSE_ALIAS + ":LateChargeType",  lateChargeRule.getLateChargeType());
	}
	/**
	 * insert insterestOnly in MISMO XML
	 * @param document
	 * @param element
	 * @param interestOnly
	 */
	private void insertInterestOnly(Document document, Element element, InterestOnlyModel interestOnly) {
		insertData(document, element, "InterestOnlyTermMonthsCount", interestOnly.getInterestOnlyTermMonthsCount());
	}
	/**
     * Inserts Hmda Loan from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*
	private void insertHmdaLoan(Document document, Element element, ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		insertHmdaLoanDenial(document, insertLevels(document, element, "HMDA_LOAN_DENIALS"), jsonDocument);
		insertHmdaLoanDetail(document, insertLevels(document, element, "HMDA_LOAN_DETAIL"), jsonDocument);
	}
	*//**
     * Inserts Hmda Loan Detail from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*
	private void insertHmdaLoanDetail(Document document, Element element, ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		insertData(document, element, "HMDAPurposeOfLoanType", "");
		insertData(document, element, "HMDAPreapprovalType", "");
		insertData(document, element, "HMDADispositionType", "");
		insertData(document, element, "HMDADispositionDate", "");
		insertData(document, element, "HMDAReportingCRAExemptionIndicator", "");
		insertData(document, element, "HMDAReportingSmallPopulationIndicator", "");
		insertData(document, element, "HMDAPurchaserType", "");
		insertData(document, element, "HMDAPurchaserTypeOtherDescription", "");
		insertData(document, element, "HMDARateSpreadPercent", "");
		insertData(document, element, "HMDAHOEPALoanStatusIndicator", "");
		insertData(document, element, "HMDAMultipleCreditScoresUsedIndicator", "");
		insertData(document, element, "HMDAOtherNonAmortizingFeaturesIndicator", "");
		insertData(document, element, "HMDAManufacturedHomeLegalClassificationType", "");
		insertData(document, element, "HMDAApplicationSubmissionType", "");
		insertData(document, element, "HMDACoveredLoanInitiallyPayableToReportingInstitutionStatusType", "");
		insertData(document, element, "HMDABusinessPurposeIndicator", "");
	}
	*//**
     * Inserts Hmda Loan Denial from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*
	private void insertHmdaLoanDenial(Document document, Element element, ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		insertData(document, element, "HMDAReasonForDenialType", "");
		insertData(document, element, "HMDAReasonForDenialTypeOtherDescription", "");
	}
	*/
	/**
	 * Inserts HighCostMortgages to MISMO XML
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertHighCostMortgages(Document document, Element element, QualifiedMortgageModel qualifiedMortgage) {
		//for (String group : groupings)
			insertHighCostMortgage(document, insertLevels(document, element, "HIGH_COST_MORTGAGE"), qualifiedMortgage);
	}
	/**
     * Inserts High Cost Mortgage to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertHighCostMortgage(Document document, Element element,
			 QualifiedMortgageModel qualifiedMortgage) {
		insertData(document, element, "AveragePrimeOfferRatePercent", qualifiedMortgage.getAveragePrimeOfferRatePercent());
		insertData(document, element, "RegulationZExcludedBonaFideDiscountPointsIndicator",Boolean.toString(qualifiedMortgage.isRegulationZExcludedBonaFideDiscountPointsIndicator()));
		insertData(document, element, "RegulationZExcludedBonaFideDiscountPointsPercent", qualifiedMortgage.getRegulationZExcludedBonaFideDiscountPointsPercent() );
		insertData(document, element, "RegulationZTotalAffiliateFeesAmount", Convertor.checkAmountFormat(qualifiedMortgage.getRegulationZTotalAffiliateFeesAmount()));
		insertData(document, element, "RegulationZTotalLoanAmount", Convertor.checkAmountFormat(qualifiedMortgage.getRegulationZTotalLoanAmount()));
		insertData(document, element, "RegulationZTotalPointsAndFeesAmount", Convertor.checkAmountFormat(qualifiedMortgage.getRegulationZTotalPointsAndFeesAmount()));
	}
	/**
     * Inserts Heloc from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*
	private void insertHeloc(Document document, Element element, ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		insertHelocRule(document, insertLevels(document, element, "HELOC_RULE"), jsonDocument);
	}
	*//**
     * Inserts Heloc Rule from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*
	private void insertHelocRule(Document document, Element element, ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		insertData(document, element, "HELOCMaximumBalanceAmount", "");
	}
	*//**
     * Inserts Foreclosures to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertForeclosures(Document document, Element element, String deficiencyRightsPreservedIndicator) {
		//for (String group : groupings)
			insertForeclosure(document, insertLevels(document, element, "FORECLOSURE"), deficiencyRightsPreservedIndicator);
	}
	/**
     * inserts Foreclosure to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertForeclosure(Document document, Element element, String deficiencyRightsPreservedIndicator) {
		insertForeclosureDetail(document, insertLevels(document, element, "FORECLOSURE_DETAIL"), deficiencyRightsPreservedIndicator);
	}
	/**
     * inserts Foreclosure Detail from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertForeclosureDetail(Document document, Element element,
			String deficiencyRightsPreservedIndicator) {
		insertData(document, element, "DeficiencyRightsPreservedIndicator", deficiencyRightsPreservedIndicator);
	}
	/**
	 * inserts fee information in MISMO XML
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertFeeInformation(Document document, Element element, ClosingDisclosure jsonDocument) {
		insertFees(document, insertLevels(document, element, "FEES"), jsonDocument);
		insertFeeSummaryDetail(document, insertLevels(document, element, "FEES_SUMMARY/FEE_SUMMARY_DETAIL"), jsonDocument.getLoanCalculationsQualifiedMortgage().getLoanCalculationModel());
	}
	
	/**
	 * inserts FeeSummaryDetail in MISMO XML
	 * @param document
	 * @param element
	 * @param loanCalculation
	 */
	private void insertFeeSummaryDetail(Document document, Element element,
			LoanCalculationModel loanCalculation) {
		insertData(document, element, "APRPercent", loanCalculation.getAprPercent());
		insertData(document, element, "FeeSummaryTotalAmountFinancedAmount", Convertor.checkAmountFormat(loanCalculation.getFeeSummaryTotalAmountFinancedAmount()));
		insertData(document, element, "FeeSummaryTotalFinanceChargeAmount", Convertor.checkAmountFormat(loanCalculation.getFeeSummaryTotalFinanceChargeAmount()));
		insertData(document, element, "FeeSummaryTotalInterestPercent", loanCalculation.getFeeSummaryTotalInterestPercent());
		insertData(document, element, "FeeSummaryTotalOfAllPaymentsAmount", Convertor.checkAmountFormat(loanCalculation.getFeeSummaryTotalOfAllPaymentsAmount()));
	}
	
	/**
	 * inserts Fees in MISMO XML 
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertFees(Document document, Element element, ClosingDisclosure jsonDocument) {
		 
		if(null != jsonDocument.getClosingCostDetailsLoanCosts().getOriginationCharges())
			for (ClosingCostProperties closingCostProperties : jsonDocument.getClosingCostDetailsLoanCosts().getOriginationCharges())
				if(Convertor.isInsertFee(closingCostProperties))
					insertFee(document, insertLevels(document, element, "FEE"), closingCostProperties);
		
		if(null != jsonDocument.getClosingCostDetailsLoanCosts().getSbDidNotShopFors())
			for (ClosingCostProperties closingCostProperties : jsonDocument.getClosingCostDetailsLoanCosts().getSbDidNotShopFors())
				if(Convertor.isInsertFee(closingCostProperties))
					insertFee(document, insertLevels(document, element, "FEE"), closingCostProperties);
		
		if(null != jsonDocument.getClosingCostDetailsLoanCosts().getSbDidShopFors())
			for (ClosingCostProperties closingCostProperties : jsonDocument.getClosingCostDetailsLoanCosts().getSbDidShopFors())
				if(Convertor.isInsertFee(closingCostProperties))
					insertFee(document, insertLevels(document, element, "FEE"), closingCostProperties);
		
		if(null != jsonDocument.getClosingCostDetailsOtherCosts().gettOGovtFeesList())
			for (ClosingCostProperties closingCostProperties : jsonDocument.getClosingCostDetailsOtherCosts().gettOGovtFeesList())
				if(Convertor.checkFeeActualTotalAmount(closingCostProperties))
					insertFee(document, insertLevels(document, element, "FEE"), closingCostProperties);
		
		if(null != jsonDocument.getClosingCostDetailsOtherCosts().getOtherCostsList())
			for (ClosingCostProperties closingCostProperties : jsonDocument.getClosingCostDetailsOtherCosts().getOtherCostsList())
				if(Convertor.isInsertFee(closingCostProperties))
					insertFee(document, insertLevels(document, element, "FEE"), closingCostProperties);		
	}
	
	/**
	 * inserts Fee in MISMO XML
	 * @param document
	 * @param element
	 * @param closingCostProperties
	 */
	private void insertFee(Document document, Element element, ClosingCostProperties closingCostProperties) {
		insertFeeDetail(document,  insertLevels(document, element, "FEE_DETAIL"), closingCostProperties);
		if(!closingCostProperties.getFeePaidToFullName().isEmpty() && null != closingCostProperties.getFeePaidToFullName())
			insertFeePaidTo(document, 	insertLevels(document, element, "FEE_PAID_TO"), closingCostProperties.getFeePaidToFullName());
		PaymentsModel paymentsModel = new PaymentsModel();
			paymentsModel.setBpAtClosing(closingCostProperties.getBpAtClosing());
			paymentsModel.setBpB4Closing(closingCostProperties.getBpB4Closing());
			paymentsModel.setLenderStatus(closingCostProperties.isLenderStatus());
			paymentsModel.setPaidByOthers(closingCostProperties.getPaidByOthers());
			paymentsModel.setSpAtClosing(closingCostProperties.getSpAtClosing());
			paymentsModel.setSpB4Closing(closingCostProperties.getSpB4Closing());
		List<MismoPaymentsModel> mismoPaymentsModelList = Convertor.toMismoFeePayments(paymentsModel, "FEE");
		if(mismoPaymentsModelList.size() > 0)
			insertFeePayments(document, insertLevels(document, element, "FEE_PAYMENTS"), mismoPaymentsModelList);
	}
	
	/**
	 * inserts FeePayments in MISMO XML
	 * @param document
	 * @param element
	 * @param closingCostProperties
	 */
	private void insertFeePayments(Document document, Element element, List<MismoPaymentsModel> mismoPaymentsModelList) {
		for (MismoPaymentsModel mismoPaymentsModel : mismoPaymentsModelList)
			insertFeePayment(document,	insertLevels(document, element, "FEE_PAYMENT"), mismoPaymentsModel);
	}
	
	/**
	 * inserts FeePayment in MISMO XML
	 * @param document
	 * @param element
	 * @param closingCostProperties
	 */
	private void insertFeePayment(Document document, Element element, MismoPaymentsModel mismoPayment) {
		insertData(document, element, "FeeActualPaymentAmount", mismoPayment.getAmount());
		insertData(document, element, "FeePaymentPaidByType", mismoPayment.getPaidByType());
		insertData(document, element, "FeePaymentPaidOutsideOfClosingIndicator", mismoPayment.getClosingIndicator());
	}
	
	/**
	 * inserts FeePaidTo in MISMO XML
	 * @param document
	 * @param element
	 * @param closingCostProperties
	 */
	private void insertFeePaidTo(Document document, Element element, String fullName ) {
		insertLegalEntity(document,	insertLevels(document, element, "LEGAL_ENTITY"), fullName);
	}
	
	/**
	 * inserts FeeDetail in MISMO XML
	 * @param document
	 * @param element
	 * @param closingCostProperties
	 */
	private void insertFeeDetail(Document document, Element element,  ClosingCostProperties closingCostProperties) {
		
		insertData(document, element, "BorrowerChosenProviderIndicator", "");
		insertData(document, element, "FeeActualTotalAmount", Convertor.checkAmountFormat(closingCostProperties.getFeeActualTotalAmount()));
		insertData(document, element, "FeeEstimatedTotalAmount", "" );
		insertData(document, element, "FeePaidToType", closingCostProperties.getFeePaidToType());
		insertData(document, element, "FeePaidToTypeOtherDescription", closingCostProperties.getFeePaidToTypeOtherDescription());
		insertData(document, element, "FeePercentBasisType", closingCostProperties.getFeePercentBasisType());
		insertData(document, element, "FeeTotalPercent", closingCostProperties.getFeeTotalPercent());
		Element feeTypeElement = returnElement(document, element, "FeeType", closingCostProperties.getFeeType());
			if(null != feeTypeElement && null != closingCostProperties.getDisplayLabel() && !closingCostProperties.getDisplayLabel().isEmpty())
				feeTypeElement.setAttribute("gse:DisplayLabelText", closingCostProperties.getDisplayLabel());
		insertData(document, element, "FeeTypeOtherDescription", closingCostProperties.getFeeTypeOtherDescription());
		insertData(document, element, "IntegratedDisclosureSectionType", closingCostProperties.getIntegratedDisclosureSectionType());
		insertData(document, element, "OptionalCostIndicator", Convertor.booleanToString(closingCostProperties.getOptionalCostIndicator()));
		if(null != closingCostProperties.getFeeType() && !Convertor.isPropertyTax(closingCostProperties.getFeeType()))
			insertData(document, element, "RegulationZPointsAndFeesIndicator", Convertor.booleanToString(closingCostProperties.getRegulationZPointsAndFeesIndicator()).toLowerCase());
		insertData(document, element, "RequiredProviderOfServiceIndicator", "");
		//if(!Convertor.isPropertyTax(closingCostProperties.getFeeType()));
			OtherModel other = new OtherModel();
				other.setPaymentIncludedInAPRIndicator(Convertor.booleanToString(closingCostProperties.getPaymentIncludedInAPRIndicator()));
			insertExtension(document, insertLevels(document, element, "EXTENSION"), other);
	}
	
	/**
	 * inserts Escrow in MISMO XML
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertEscrow(Document document, Element element, ClosingDisclosure jsonDocument) {
			insertEscrowDetail(document, insertLevels(document, element, "ESCROW_DETAIL"), jsonDocument.getClosingDisclosureDocDetails());
		if(jsonDocument.getClosingCostDetailsOtherCosts().getEscrowItemsList().size() > 0)
			insertEscrowItems(document, insertLevels(document, element, "ESCROW_ITEMS"), jsonDocument.getClosingCostDetailsOtherCosts().getEscrowItemsList());
	}
	
	/**
	 * inserts EscrowItems in MISMO XML
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertEscrowItems(Document document, Element element, List<EscrowItemModel> escrowItemList) {
		for (EscrowItemModel escrowItem : escrowItemList)
		{
		  if(Convertor.isInsertFee(escrowItem))
				insertEscrowItem(document, insertLevels(document, element, "ESCROW_ITEM"), escrowItem);
		}
	}
	/**
     * Inserts Escrow Item to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertEscrowItem(Document document, Element element, EscrowItemModel escrowItem) {
		insertEscrowItemDetail(document, insertLevels(document, element, "ESCROW_ITEM_DETAIL"), escrowItem);
		/*PaymentsModel paymentsModel = new PaymentsModel();
			paymentsModel.setBpAtClosing(escrowItem.getBpAtClosing());
			paymentsModel.setBpB4Closing(escrowItem.getBpB4Closing());
			paymentsModel.setSpAtClosing(escrowItem.getSpAtClosing());
			paymentsModel.setSpB4Closing(escrowItem.getSpB4Closing());
			paymentsModel.setLenderStatus(escrowItem.isLenderStatus());
			paymentsModel.setPaidByOthers(escrowItem.getPaidByOthers());*/
		List<MismoPaymentsModel> mismoPaymentsList = Convertor.toMismoFeePayments(escrowItem, "ESCROW");
		if(mismoPaymentsList.size() > 0)
			insertEscrowItemPayments(document,  insertLevels(document, element, "ESCROW_ITEM_PAYMENTS"), mismoPaymentsList);
	}
	/**
     * Inserts Escrow Item Payments to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertEscrowItemPayments(Document document, Element element,
			List<MismoPaymentsModel> mismoPaymentsList) {
		for (MismoPaymentsModel mismoPayment : mismoPaymentsList)
			insertEscrowItemPayment(document, insertLevels(document, element, "ESCROW_ITEM_PAYMENT"), mismoPayment);
	}
	/**
     * Inserts Escrow Item Payment to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertEscrowItemPayment(Document document, Element element,
			MismoPaymentsModel mismoPayment) {
		insertData(document, element, "EscrowItemActualPaymentAmount",mismoPayment.getAmount());
		insertData(document, element, "EscrowItemPaymentPaidByType", mismoPayment.getPaidByType());
		insertData(document, element, "EscrowItemPaymentTimingType", mismoPayment.getClosingIndicator());
	}
	/**
     * Inserts Escrow Item Detail from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertEscrowItemDetail(Document document, Element element,
			EscrowItemModel escrowItem) {
		insertData(document, element, "EscrowCollectedNumberOfMonthsCount", escrowItem.getEscrowCollectedNumberOfMonthsCount());
		insertData(document, element, "EscrowItemCategoryType","");
		insertData(document, element, "EscrowItemEstimatedTotalAmount","");
		Element itemType = returnElement(document, element, "EscrowItemType", escrowItem.getEscrowItemType());
			if(null != itemType && null != escrowItem.getDisplayLabel() && !escrowItem.getDisplayLabel().isEmpty())
				itemType.setAttribute("gse:DisplayLabelText",  escrowItem.getDisplayLabel());
		insertData(document, element, "EscrowItemTypeOtherDescription", escrowItem.getEscrowItemTypeOtherDescription());
		insertData(document, element, "EscrowMonthlyPaymentAmount", escrowItem.getEscrowMonthlyPaymentAmount());
		insertData(document, element, "FeePaidToType", escrowItem.getFeePaidToType());
		insertData(document, element, "FeePaidToTypeOtherDescription", escrowItem.getFeePaidToTypeOtherDescription());
		insertData(document, element, "IntegratedDisclosureSectionType", escrowItem.getIntegratedDisclosureSectionType());
		if(null != escrowItem.getEscrowItemType() && !Convertor.isPropertyTax(escrowItem.getEscrowItemType()))
			insertData(document, element, "RegulationZPointsAndFeesIndicator", Boolean.toString(escrowItem.isRegulationZPointsAndFeesIndicator()));
	//	if(!Convertor.isPropertyTax(escrowItem.getEscrowItemType()));
			OtherModel other = new OtherModel();
			other.setPaymentIncludedInAPRIndicator(Boolean.toString(escrowItem.isPaymentIncludedInAPRIndicator()));
				insertExtension(document, insertLevels(document, element, "EXTENSION"), other);
	}
	
	/**
	 * inserts EscrowDetail in MISMO XML
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertEscrowDetail(Document document, Element element, ClosingDisclosureDocumentDetails  closingDisclosureDocumentDetails) {
		
		if(!"".equals(closingDisclosureDocumentDetails.getEscrowAggregateAccountingAdjustmentAmountSellerPaid()) && null != closingDisclosureDocumentDetails.getEscrowAggregateAccountingAdjustmentAmountSellerPaid())
		{
			OtherModel other = new OtherModel();
				other.setEscrowAggregateAccountingAdjustmentPaidByType("Seller");
				other.setEscrowAggregateAccountingAdjustmentPaymentTimingType("AtClosing");
			insertData(document, element, "EscrowAggregateAccountingAdjustmentAmount", Convertor.checkAmountFormat(closingDisclosureDocumentDetails.getEscrowAggregateAccountingAdjustmentAmountSellerPaid()));
			insertExtension(document, insertLevels(document, element, "EXTENSION"), other);	
		}
		else if(!"".equals(closingDisclosureDocumentDetails.getEscrowAggregateAccountingAdjustmentAmountOthersPaid()) && null != closingDisclosureDocumentDetails.getEscrowAggregateAccountingAdjustmentAmountOthersPaid())
		{
			OtherModel other = new OtherModel();
				other.setEscrowAggregateAccountingAdjustmentPaidByType("ThirdParty");
				other.setEscrowAggregateAccountingAdjustmentPaymentTimingType("AtClosing");
				insertData(document, element, "EscrowAggregateAccountingAdjustmentAmount", Convertor.checkAmountFormat(closingDisclosureDocumentDetails.getEscrowAggregateAccountingAdjustmentAmountOthersPaid()));
			insertExtension(document, insertLevels(document, element, "EXTENSION"), other);
		}	
		else if(!"".equals(closingDisclosureDocumentDetails.getEscrowAggregateAccountingAdjustmentAmount()) && null != closingDisclosureDocumentDetails.getEscrowAggregateAccountingAdjustmentAmount())	
			insertData(document, element, "EscrowAggregateAccountingAdjustmentAmount", Convertor.checkAmountFormat(closingDisclosureDocumentDetails.getEscrowAggregateAccountingAdjustmentAmount()));
	}
	/**
     * Inserts Document Specific DataSet to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertDocumentSpecificDataSet(Document document, Element element, ClosingDisclosure jsonDocument) {
		// TODO Auto-generated method stub
		//insertExecution(document, insertLevels(document, element, "EXECUTION"), jsonDocument);
		insertIntegratedDisclosure(document, insertLevels(document, element, "INTEGRATED_DISCLOSURE"), jsonDocument);
		//insertURLA(document, insertLevels(document, element, "URLA"), jsonDocument);
	}
	/**
     * Inserts URLA from JSON Object (URLA : Uniform Residential Loan Application)
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*
	private void insertURLA(Document document, Element element, ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		insertURLADetail(document, insertLevels(document, element, "URLA_DETAIL"), jsonDocument);
	}
	*//**
     * Inserts URLA Detail from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     *//*
	private void insertURLADetail(Document document, Element element, ClosingDisclosureDocument jsonDocument) {
		// TODO Auto-generated method stub
		insertData(document, element, "BorrowerRequestedLoanAmount", ""); //TODO Need to add the Object
	}
	*//**
     * Inserts Integrated Disclosure from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertIntegratedDisclosure(Document document, Element element,
			ClosingDisclosure jsonDocument) {
		insertCashToCloseItems(document, insertLevels(document, element, "CASH_TO_CLOSE_ITEMS"), jsonDocument.getCashToCloses());
		insertEstimatedPropertyCost(document, insertLevels(document, element, "ESTIMATED_PROPERTY_COST"), jsonDocument.getEtiaSection());
		insertIntegratedDisclosureDetail(document, insertLevels(document, element, "INTEGRATED_DISCLOSURE_DETAIL"), jsonDocument.getIntegratedDisclosureDetail());
		insertIntegratedDisclosureSectionSummaries(document, insertLevels(document, element, "INTEGRATED_DISCLOSURE_SECTION_SUMMARIES"), jsonDocument);
		insertProjectedPayments(document, insertLevels(document, element, "PROJECTED_PAYMENTS"), jsonDocument.getProjectedPayments());
	}
	/**
     * Inserts Projected Payments to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertProjectedPayments(Document document, Element element,
			ProjectedPaymentsDetails projectedPaymentsDetails) {
		List<MismoProjectedPaymentsModel> mismoProjectedPaymentsModels = Convertor.createMismoProjectedPayments(projectedPaymentsDetails);
		for (MismoProjectedPaymentsModel projectedPayment : mismoProjectedPaymentsModels)
			insertProjectedPayment(document, insertLevels(document, element, "PROJECTED_PAYMENT"), projectedPayment);
	}
	/**
     * Inserts Projected Payment to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertProjectedPayment(Document document, Element element,
			MismoProjectedPaymentsModel projectedPayment) {
		//insertAttributeValue(xmlout, parentElement, "SequenceNumber", "");
		
		element.setAttribute("SequenceNumber", projectedPayment.getSequenceNumber() );
		insertData(document, element, "PaymentFrequencyType", projectedPayment.getPaymentFrequencyType());
		insertData(document, element, "ProjectedPaymentCalculationPeriodEndNumber", projectedPayment.getProjectedPaymentCalculationPeriodEndNumber());
		insertData(document, element, "ProjectedPaymentCalculationPeriodStartNumber", projectedPayment.getProjectedPaymentCalculationPeriodStartNumber());
		insertData(document, element, "ProjectedPaymentCalculationPeriodTermType",  projectedPayment.getProjectedPaymentCalculationPeriodTermType());
		insertData(document, element, "ProjectedPaymentCalculationPeriodTermTypeOtherDescription", projectedPayment.getProjectedPaymentCalculationPeriodTermTypeOtherDescription());
		insertData(document, element, "ProjectedPaymentEstimatedEscrowPaymentAmount", projectedPayment.getProjectedPaymentEstimatedEscrowPaymentAmount());
		insertData(document, element, "ProjectedPaymentEstimatedTotalMaximumPaymentAmount", projectedPayment.getProjectedPaymentEstimatedTotalMaximumPaymentAmount());
		insertData(document, element, "ProjectedPaymentEstimatedTotalMinimumPaymentAmount",  projectedPayment.getProjectedPaymentEstimatedTotalMinimumPaymentAmount());
		insertData(document, element, "ProjectedPaymentMIPaymentAmount", projectedPayment.getProjectedPaymentMIPaymentAmount());
		insertData(document, element, "ProjectedPaymentPrincipalAndInterestMaximumPaymentAmount", projectedPayment.getProjectedPaymentPrincipalAndInterestMaximumPaymentAmount());
		insertData(document, element, "ProjectedPaymentPrincipalAndInterestMinimumPaymentAmount", projectedPayment.getProjectedPaymentPrincipalAndInterestMinimumPaymentAmount());
	}
	/**
     * Inserts Integrated Disclosure Section Summaries to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertIntegratedDisclosureSectionSummaries(Document document, Element element,
			ClosingDisclosure jsonDocument) {
		for (IntegratedDisclosureSectionSummaryModel integratedDisclosureSectionSummary : Convertor.createIntegratedDisclosureSectionSummaryModels(jsonDocument)) //TODO Not Implemented
			insertIntegratedDisclosureSectionSummary(document, insertLevels(document, element, "INTEGRATED_DISCLOSURE_SECTION_SUMMARY"), integratedDisclosureSectionSummary);
	}
	/**
     * Inserts Integrated Disclosure Section Summary to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertIntegratedDisclosureSectionSummary(Document document, Element element,
			IntegratedDisclosureSectionSummaryModel integratedDisclosureSectionSummary) {
		insertIntegratedDisclosureSectionSummaryDetail(document, insertLevels(document, element, "INTEGRATED_DISCLOSURE_SECTION_SUMMARY_DETAIL"), integratedDisclosureSectionSummary.getIntegratedDisclosureSectionSummaryDetailModel());
		if(null != integratedDisclosureSectionSummary.getIntegratedDisclosureSubsectionPayments())
			insertIntegratedDisclosureSubsectionPayments(document, insertLevels(document, element, "INTEGRATED_DISCLOSURE_SUBSECTION_PAYMENTS"), integratedDisclosureSectionSummary.getIntegratedDisclosureSubsectionPayments());
	}
	/**
     * Inserts Integrated Disclosure Subsection Payments to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertIntegratedDisclosureSubsectionPayments(Document document, Element element,
			List<IntegratedDisclosureSubsectionPaymentModel> integratedDisclosureSubsectionPaymentModels ) {
		// TODO Auto-generated method stub
		for (IntegratedDisclosureSubsectionPaymentModel integratedDisclosureSubsectionPayment : integratedDisclosureSubsectionPaymentModels)
			insertIntegratedDisclosureSubsectionPayment(document, insertLevels(document, element, "INTEGRATED_DISCLOSURE_SUBSECTION_PAYMENT"), integratedDisclosureSubsectionPayment);
	}
	/**
     * Inserts Integrated Disclosure Subsection Payment to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertIntegratedDisclosureSubsectionPayment(Document document, Element element,
			IntegratedDisclosureSubsectionPaymentModel integratedDisclosureSubsectionPayment) {
		insertData(document, element, "IntegratedDisclosureSubsectionPaidByType", integratedDisclosureSubsectionPayment.getIntegratedDisclosureSubsectionPaidByType());
		insertData(document, element, "IntegratedDisclosureSubsectionPaymentAmount", integratedDisclosureSubsectionPayment.getIntegratedDisclosureSubsectionPaymentAmount());
		insertData(document, element, "IntegratedDisclosureSubsectionPaymentTimingType", integratedDisclosureSubsectionPayment.getIntegratedDisclosureSubsectionPaymentTimingType());
	}
	/**
     * Inserts Integrated Disclosure Section Summary Detail to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertIntegratedDisclosureSectionSummaryDetail(Document document, Element element,
			IntegratedDisclosureSectionSummaryDetailModel integratedDisclosureSectionSummaryDetail) {
		insertData(document, element, "IntegratedDisclosureSectionTotalAmount", integratedDisclosureSectionSummaryDetail.getIntegratedDisclosureSectionTotalAmount());
		insertData(document, element, "IntegratedDisclosureSectionType", integratedDisclosureSectionSummaryDetail.getIntegratedDisclosureSectionType());
		insertData(document, element, "IntegratedDisclosureSubsectionTotalAmount", integratedDisclosureSectionSummaryDetail.getIntegratedDisclosureSubsectionTotalAmount());
		insertData(document, element, "IntegratedDisclosureSubsectionType", integratedDisclosureSectionSummaryDetail.getIntegratedDisclosureSubsectionType());
		insertData(document, element, "IntegratedDisclosureSubsectionTypeOtherDescription", integratedDisclosureSectionSummaryDetail.getIntegratedDisclosureSubsectionTypeOtherDescription());
		insertData(document, element, "LenderCreditToleranceCureAmount", integratedDisclosureSectionSummaryDetail.getLenderCreditToleranceCureAmount());
	}
	/**
     * Inserts Estimated Property Cost to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertEstimatedPropertyCost(Document document, Element element,
			ETIASection propertyCost) {
		insertEstimatedPropertyCostComponents(document, insertLevels(document, element, "ESTIMATED_PROPERTY_COST_COMPONENTS"), propertyCost);
		if(!"".equals(propertyCost.getProjectedPaymentEstimatedTaxesInsuranceAssessmentTotalAmount()) && null != propertyCost.getProjectedPaymentEstimatedTaxesInsuranceAssessmentTotalAmount())
			insertEstimatedPropertyCostDetail(document, insertLevels(document, element, "ESTIMATED_PROPERTY_COST_DETAIL"), Convertor.checkAmountFormat(propertyCost.getProjectedPaymentEstimatedTaxesInsuranceAssessmentTotalAmount()));
	}
	/**
     * Inserts Estimated Property Cost Detail to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertEstimatedPropertyCostDetail(Document document, Element element,
			String projectedPaymentEstimatedTaxesInsuranceAssessmentTotalAmount) {
		insertData(document, element, "ProjectedPaymentEstimatedTaxesInsuranceAssessmentTotalAmount", projectedPaymentEstimatedTaxesInsuranceAssessmentTotalAmount);
	}
	/**
     * Inserts Estimated Property Cost Components to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertEstimatedPropertyCostComponents(Document document, Element element,
			ETIASection propertyCost) {
		if(null != element && null != propertyCost.getDisplayLabel() && !propertyCost.getDisplayLabel().isEmpty())
			element.setAttribute("gse:DisplayLabelText", propertyCost.getDisplayLabel());
		for (ETIA ETIA : propertyCost.getEtiaValues())
			insertEstimatedPropertyCostComponent(document, insertLevels(document, element, "ESTIMATED_PROPERTY_COST_COMPONENT"), ETIA);
	}
	/**
     * Inserts Estimated Property Cost Component to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertEstimatedPropertyCostComponent(Document document, Element element,
			ETIA eTIA) {
		insertData(document, element, "ProjectedPaymentEscrowedType", eTIA.getProjectedPaymentEscrowedType());
		insertData(document, element, 
				"ProjectedPaymentEstimatedTaxesInsuranceAssessmentComponentType", eTIA.getProjectedPaymentEstimatedTaxesInsuranceAssessmentComponentType());
		insertData(document, element,
				"ProjectedPaymentEstimatedTaxesInsuranceAssessmentComponentTypeOtherDescription", eTIA.getProjectedPaymentEstimatedTaxesInsuranceAssessmentComponentTypeOtherDescription());
	
	}
	/**
     * Inserts Cash To Close Items to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertCashToCloseItems(Document document, Element element,
			CashToClose cashToClose) {
		if(Convertor.checkNotNull(cashToClose.getAdjustmentsAndOtherCredits().getIntegratedDisclosureCashToCloseItemType()))
			insertCashToCloseItem(document,	insertLevels(document, element, "CASH_TO_CLOSE_ITEM"), cashToClose.getAdjustmentsAndOtherCredits());
		if(Convertor.checkNotNull(cashToClose.getClosingCostsFinanced().getIntegratedDisclosureCashToCloseItemType()))
			insertCashToCloseItem(document,	insertLevels(document, element, "CASH_TO_CLOSE_ITEM"), cashToClose.getClosingCostsFinanced());
		if(Convertor.checkNotNull(cashToClose.getClosingCostsPaidBeforeClosing().getIntegratedDisclosureCashToCloseItemType()))
			insertCashToCloseItem(document,	insertLevels(document, element, "CASH_TO_CLOSE_ITEM"), cashToClose.getClosingCostsPaidBeforeClosing());
		if(Convertor.checkNotNull(cashToClose.getDeposit().getIntegratedDisclosureCashToCloseItemType()))
			insertCashToCloseItem(document,	insertLevels(document, element, "CASH_TO_CLOSE_ITEM"), cashToClose.getDeposit());
		if(Convertor.checkNotNull(cashToClose.getDownPayment().getIntegratedDisclosureCashToCloseItemType()))
			insertCashToCloseItem(document,	insertLevels(document, element, "CASH_TO_CLOSE_ITEM"), cashToClose.getDownPayment());
		if(Convertor.checkNotNull(cashToClose.getFundsForBorrower().getIntegratedDisclosureCashToCloseItemType()))
			insertCashToCloseItem(document,	insertLevels(document, element, "CASH_TO_CLOSE_ITEM"), cashToClose.getFundsForBorrower());
		if(Convertor.checkNotNull(cashToClose.getLoanAmount().getIntegratedDisclosureCashToCloseItemType()))
			insertCashToCloseItem(document,	insertLevels(document, element, "CASH_TO_CLOSE_ITEM"), cashToClose.getLoanAmount());
		if(Convertor.checkNotNull(cashToClose.getSellerCredits().getIntegratedDisclosureCashToCloseItemType()))	
			insertCashToCloseItem(document,	insertLevels(document, element, "CASH_TO_CLOSE_ITEM"), cashToClose.getSellerCredits());
		if(Convertor.checkNotNull(cashToClose.getTotalClosingCosts().getIntegratedDisclosureCashToCloseItemType()))
			insertCashToCloseItem(document,	insertLevels(document, element, "CASH_TO_CLOSE_ITEM"), cashToClose.getTotalClosingCosts());
		if(Convertor.checkNotNull(cashToClose.getTotalPayoffsAndPayments().getIntegratedDisclosureCashToCloseItemType()))
			insertCashToCloseItem(document,	insertLevels(document, element, "CASH_TO_CLOSE_ITEM"), cashToClose.getTotalPayoffsAndPayments());
		
		if(cashToClose.getCashToCloseTotal().size() > 0)
			for(CashToCloseModel cashToCloseModel : cashToClose.getCashToCloseTotal())
				if(Convertor.checkNotNull(cashToCloseModel.getIntegratedDisclosureCashToCloseItemType()))
				{
					cashToCloseModel.setIntegratedDisclosureCashToCloseItemAmountChangedIndicator(null);
					insertCashToCloseItem(document,	insertLevels(document, element, "CASH_TO_CLOSE_ITEM"), cashToCloseModel);
				}
	}
	/**
     * Inserts Cash To Close Item to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertCashToCloseItem(Document document, Element element,
			CashToCloseModel cashToClose) {
			insertData(document, element, "IntegratedDisclosureCashToCloseItemAmountChangedIndicator",Convertor.booleanToString(cashToClose.getIntegratedDisclosureCashToCloseItemAmountChangedIndicator()));
			insertData(document, element, "IntegratedDisclosureCashToCloseItemChangeDescription", cashToClose.getIntegratedDisclosureCashToCloseItemChangeDescription());
			insertData(document, element, "IntegratedDisclosureCashToCloseItemEstimatedAmount", Convertor.checkAmountFormat(cashToClose.getIntegratedDisclosureCashToCloseItemEstimatedAmount()));
			insertData(document, element, "IntegratedDisclosureCashToCloseItemFinalAmount", Convertor.checkAmountFormat(cashToClose.getIntegratedDisclosureCashToCloseItemFinalAmount()));
			insertData(document, element, "IntegratedDisclosureCashToCloseItemPaymentType", cashToClose.getIntegratedDisclosureCashToCloseItemPaymentType());
			insertData(document, element, "IntegratedDisclosureCashToCloseItemType",  cashToClose.getIntegratedDisclosureCashToCloseItemType());
	}
	/**
     * Inserts Execution to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param signatory Input SignatoriesModel Object
     */
	private void insertExecution(Document document, Element element, ClosingDisclosureDocumentDetails closingDisclosureDocumentDetails) {
		insertExecutionDetail(document,	insertLevels(document, element, "EXECUTION_DETAIL"), closingDisclosureDocumentDetails);
	}
	/**
     * Inserts Execution Detail to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param signatory Input SignatoriesModel Object
     */
	private void insertExecutionDetail(Document document, Element element,
			ClosingDisclosureDocumentDetails closingDisclosureDocumentDetails) {
		insertData(document, element, "ActualSignatureType", closingDisclosureDocumentDetails.getActualSignatureType());
		insertData(document, element, "ActualSignatureTypeOtherDescription", closingDisclosureDocumentDetails.getActualSignatureTypeOtherDescription());
		insertData(document, element, "ExecutionDate", closingDisclosureDocumentDetails.getExecutionDate());
		insertData(document, element, "ExecutionDatetime", "");
	}
	
	/**
	 * inserts construction to MISMO XML 
	 * @param document
	 * @param element
	 * @param construction
	 */
	private void insertConstruction(Document document, Element element, ConstructionModel construction) {
		insertData(document, element, "ConstructionLoanTotalTermMonthsCount", construction.getConstructionLoanTotalTermMonthsCount());
		insertData(document, element, "ConstructionLoanType", construction.getConstructionLoanType());
		insertData(document, element, "ConstructionPeriodNumberOfMonthsCount", construction.getConstructionPeriodNumberOfMonthsCount());
	}
	
	/**
	 * inserts Buydown to MISMO XML
	 * @param document
	 * @param element
	 * @param temporaryBuydown
	 */
	private void insertBuydown(Document document, Element element, LoanTermsTemporaryBuydown temporaryBuydown) {
		insertBuydownOccurences(document ,insertLevels(document, element, "BUYDOWN_OCCURRENCES"), temporaryBuydown);
		insertBuydownRule(document ,insertLevels(document, element, "BUYDOWN_RULE"), temporaryBuydown);
	}
	
	/**
	 * inserts BuydownRule to MISMO XML
	 * @param document
	 * @param element
	 * @param temporaryBuydown
	 */
	private void insertBuydownRule(Document document, Element element, LoanTermsTemporaryBuydown temporaryBuydown) {
		OtherModel other = new OtherModel();
			other.setBuydownReflectedInNoteIndicator(Convertor.booleanToString(temporaryBuydown.getGseBuydownReflectedInNoteIndicator()));
		insertData(document, element, "BuydownChangeFrequencyMonthsCount", temporaryBuydown.getBuydownChangeFrequencyMonthsCount());
		insertData(document, element, "BuydownDurationMonthsCount", temporaryBuydown.getBuydownDurationMonthsCount());
		insertData(document, element, "BuydownIncreaseRatePercent", temporaryBuydown.getBuydownIncreaseRatePercent());
		insertExtension(document, insertLevels(document, element, "EXTENSION"), other);
	}
	
	/**
	 * inserts BuydownOccurences to MISMO XML
	 * @param document
	 * @param element
	 * @param temporaryBuydown
	 */
	private void insertBuydownOccurences(Document document, Element element, LoanTermsTemporaryBuydown temporaryBuydown) {
		//for (BuyDownOccurance buyDownOccurance : buyDownOccurances)
			insertBuydownOccurence(document ,insertLevels(document, element, "BUYDOWN_OCCURRENCE"), temporaryBuydown);
	}
	
	/**
	 * inserts BuydownOccurence to MISMO XML
	 * @param document
	 * @param element
	 * @param temporaryBuydown
	 */
	private void insertBuydownOccurence(Document document, Element element, LoanTermsTemporaryBuydown temporaryBuydown) {
		insertData(document, element, "BuydownInitialEffectiveInterestRatePercent", temporaryBuydown.getBuydownInitialEffectiveInterestRatePercent());
	}
	
    /**
     * inserts closingInformation to MISMO XML 
     * @param document
     * @param element
     * @param jsonDocument
     */
	private void insertClosingInformation(Document document, Element element, ClosingDisclosure jsonDocument) {
		insertClosingAdjustmentItems(document ,insertLevels(document, element, "CLOSING_ADJUSTMENT_ITEMS"), jsonDocument.getClosingAdjustmentItemList());
		if(jsonDocument.getClosingCostFundList().size() > 0)
			insertClosingCostFunds(document ,insertLevels(document, element, "CLOSING_COST_FUNDS"), jsonDocument.getClosingCostFundList());
		insertClosingInformationDetail(document,insertLevels(document, element, "CLOSING_INFORMATION_DETAIL"), jsonDocument.getClosingInformationDetail());
		insertPrepaidItems(document ,insertLevels(document, element, "PREPAID_ITEMS"), jsonDocument.getClosingCostDetailsOtherCosts().getPrepaidsList());
		if(jsonDocument.getProrationsList().size() > 0)
			insertProrationItems(document ,insertLevels(document, element, "PRORATION_ITEMS"), jsonDocument.getProrationsList());
	}
	
	/**
	 * inserts prorationItems to MISMO XML
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertProrationItems(Document document, Element element, List<ProrationModel> prorationItems) {
		for (ProrationModel prorationItem : prorationItems)
			insertProrationItem(document, insertLevels(document, element, "PRORATION_ITEM"), prorationItem);
	}
	
	/**
	 * inserts prorationItem to MISMO XML
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertProrationItem(Document document, Element element, ProrationModel prorationItem) {
		insertData(document, element, "IntegratedDisclosureSectionType", prorationItem.getIntegratedDisclosureSectionType());
		insertData(document, element, "IntegratedDisclosureSubsectionType", prorationItem.getIntegratedDisclosureSubsectionType());
		insertData(document, element, "ProrationItemAmount", Convertor.checkAmountFormat(prorationItem.getProrationItemAmount()));
		insertData(document, element, "ProrationItemPaidFromDate",  prorationItem.getProrationItemPaidFromDate());
		insertData(document, element, "ProrationItemPaidThroughDate",  prorationItem.getProrationItemPaidThroughDate());
		Element prorationItemTypeElement = returnElement(document, element, "ProrationItemType",  prorationItem.getProrationItemType());
			if(null != prorationItemTypeElement && null != prorationItem.getDisplayLabel() && !prorationItem.getDisplayLabel().isEmpty())
				prorationItemTypeElement.setAttribute("gse:DisplayLabelText",  prorationItem.getDisplayLabel());
		insertData(document, element, "ProrationItemTypeOtherDescription", prorationItem.getProrationItemTypeOtherDescription());
	}
		
	/**
     * Inserts Prepaid Items to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPrepaidItems(Document document, Element element, List<Prepaids> prepaidItems) {
		for (Prepaids prepaidItem : prepaidItems)
			if(Convertor.isInsertFee(prepaidItem))
				insertPrepaidItem(document, insertLevels(document, element, "PREPAID_ITEM"), prepaidItem);
	}
	/**
     * Inserts Prepaid Item to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPrepaidItem(Document document, Element element,
			Prepaids prepaidItem) {
		insertPrepaidItemDetail(document, insertLevels(document, element, "PREPAID_ITEM_DETAIL"), prepaidItem);
		if(!prepaidItem.getPrepaidPaidToFullName().isEmpty())
			insertPrepaidItemPaidTo(document, insertLevels(document, element, "PREPAID_ITEM_PAID_TO"), prepaidItem.getPrepaidPaidToFullName());
		insertPrepaidItemPayments(document, insertLevels(document, element, "PREPAID_ITEM_PAYMENTS"), prepaidItem);
	}
	/**
     * Inserts Prepaid Item Payments to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPrepaidItemPayments(Document document, Element element,
			Prepaids prepaidItem) {
		PaymentsModel paymentsModel = new PaymentsModel();
			paymentsModel.setBpAtClosing(prepaidItem.getBpAtClosing());
			paymentsModel.setBpB4Closing(prepaidItem.getBpB4Closing());
			paymentsModel.setLenderStatus(prepaidItem.isLenderStatus());
			paymentsModel.setPaidByOthers(prepaidItem.getPaidByOthers());
			paymentsModel.setSpAtClosing(prepaidItem.getSpAtClosing());
			paymentsModel.setSpB4Closing(prepaidItem.getSpB4Closing());
		
		List<MismoPaymentsModel> mismoFeePaymentsModels = Convertor.toMismoFeePayments(paymentsModel, "PREPAID");
		
		for (MismoPaymentsModel prepaidItemPayment : mismoFeePaymentsModels)
			insertPrepaidItemPayment(document, insertLevels(document, element, "PREPAID_ITEM_PAYMENT"), prepaidItemPayment);
	}
	/**
     * Inserts Prepaid Item Payment to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPrepaidItemPayment(Document document, Element element,
			MismoPaymentsModel prepaidItemPayment) {
		insertData(document, element, "PrepaidItemActualPaymentAmount", prepaidItemPayment.getAmount());
		insertData(document, element, "PrepaidItemPaymentPaidByType", prepaidItemPayment.getPaidByType());
		insertData(document, element, "PrepaidItemPaymentTimingType", prepaidItemPayment.getClosingIndicator());
		//insertData(document, element, "RegulationZPointsAndFeesIndicator", "");
	}
	/**
     * Inserts Prepaid Item Paid To to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPrepaidItemPaidTo(Document document, Element element,
			String fullName) {
		insertLegalEntity(document, insertLevels(document, element, "LEGAL_ENTITY"), fullName);
	}
	/**
     * Inserts Prepaid Item Detail to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPrepaidItemDetail(Document document, Element element,
			Prepaids prepaidItem) {
		insertData(document, element, "FeePaidToType", prepaidItem.getFeePaidToType());
		insertData(document, element, "FeePaidToTypeOtherDescription", prepaidItem.getFeePaidToTypeOtherDescription());
		insertData(document, element, "IntegratedDisclosureSectionType", prepaidItem.getIntegratedDisclosureSectionType());
		//insertData(document, element, "PrepaidItemEstimatedTotalAmount", prepaidItem.getPre); 
		insertData(document, element, "PrepaidItemMonthsPaidCount", prepaidItem.getPrepaidItemMonthsPaidCount());
		//insertData(document, element, "PrepaidItemNumberOfDaysCount", prepaidItem.getprep);// TODO Value Not binded with object 
		insertData(document, element, "PrepaidItemPaidFromDate", prepaidItem.getPrepaidItemPaidFromDate());
		insertData(document, element, "PrepaidItemPaidThroughDate", prepaidItem.getPrepaidItemPaidThroughDate());
		insertData(document, element, "PrepaidItemPerDiemAmount", Convertor.checkAmountFormat(prepaidItem.getPrepaidItemPerDiemAmount()));
		insertData(document, element, "PrepaidItemPerDiemCalculationMethodType", prepaidItem.getPrepaidItemPerDiemCalculationMethodType());
		Element prepaidItemTypeElement = returnElement(document, element, "PrepaidItemType", prepaidItem.getPrepaidItemType());
			if(null != prepaidItemTypeElement && null != prepaidItem.getDisplayLabel() && !prepaidItem.getDisplayLabel().isEmpty())
				prepaidItemTypeElement.setAttribute("gse:DisplayLabelText", prepaidItem.getDisplayLabel());
		insertData(document, element, "PrepaidItemTypeOtherDescription", prepaidItem.getPrepaidItemTypeOtherDescription());
		if(null != prepaidItem.getPrepaidItemType() &&  !Convertor.isPropertyTax(prepaidItem.getPrepaidItemType()))
			insertData(document, element, "RegulationZPointsAndFeesIndicator", Boolean.toString(prepaidItem.isRegulationZPointsAndFeesIndicator()));
		//if(!Convertor.isPropertyTax(prepaidItem.getFeePaidToType())){
			OtherModel other = new OtherModel();
			other.setPaymentIncludedInAPRIndicator(Boolean.toString(prepaidItem.isPaymentIncludedInAPRIndicator()));
			insertExtension(document,insertLevels(document, element, "EXTENSION"), other);
	//	}
	}
	/**
	 * inserts ClosingCostFunds in MISMO XML 
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertClosingCostFunds(Document document, Element element,
			List<ClosingCostFundModel> closingCostFundList) {
		for (ClosingCostFundModel closingCostFund : closingCostFundList)
			insertClosingCostFund(document ,insertLevels(document, element, "CLOSING_COST_FUND"), closingCostFund);
	}
	
	/**
	 * inserts ClosingCostFund in MISMO XML
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertClosingCostFund(Document document, Element element,
			ClosingCostFundModel closingCostFund) {
		insertData(document, element, "ClosingCostFundAmount", Convertor.checkAmountFormat(closingCostFund.getClosingCostFundAmount()));
		insertData(document, element, "FundsType", closingCostFund.getFundsType());
		insertData(document, element, "IntegratedDisclosureSectionType", closingCostFund.getIntegratedDisclosureSectionType());
	}
	/**
     * Inserts Closing Adjustment Items to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertClosingAdjustmentItems(Document document, Element element, List<ClosingAdjustmentItemModel> closingAdjustmentItemList) {
		for (ClosingAdjustmentItemModel closingAdjustmentItem : closingAdjustmentItemList)
			insertClosingAdjustmentItem(document, insertLevels(document, element, "CLOSING_ADJUSTMENT_ITEM"), closingAdjustmentItem);
	}
	/**
     * Inserts Closing Adjustment Item to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertClosingAdjustmentItem(Document document, Element element, ClosingAdjustmentItemModel closingAdjustmentItem) {
		insertClosingAdjustmentItemDetail(document,insertLevels(document, element, "CLOSING_ADJUSTMENT_ITEM_DETAIL"), closingAdjustmentItem);
		insertClosingAdjustmentItemPaidBy(document,insertLevels(document, element, "CLOSING_ADJUSTMENT_ITEM_PAID_BY"), closingAdjustmentItem);
		if(!"".equalsIgnoreCase(closingAdjustmentItem.getPaidToEntityFullName()))
			insertLegalEntityDetail(document,insertLevels(document, element, "EXTENSION/OTHER/gse:CLOSING_ADJUSTMENT_ITEM_PAID_TO/LEGAL_ENTITY/LEGAL_ENTITY_DETAIL/"), closingAdjustmentItem.getPaidToEntityFullName());
	
	}
	/**
     * Inserts Closing Adjustment Item Paid By to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertClosingAdjustmentItemPaidBy(Document document, Element element,
			 ClosingAdjustmentItemModel closingAdjustmentItem) {
		if(!"".equals(closingAdjustmentItem.getPaidByEntityFullName()))
		  insertLegalEntity(document,insertLevels(document, element, "LEGAL_ENTITY"), closingAdjustmentItem.getPaidByEntityFullName());
		if(!"".equals(closingAdjustmentItem.getPaidByIndividualFullName()))
		{
			MismoIndividualModel individual = new MismoIndividualModel();
			NameModel name = new NameModel();
				name.setFullName(closingAdjustmentItem.getPaidByIndividualFullName());
				individual.setName(name);
			insertIndividual(document,insertLevels(document, element, "INDIVIDUAL"), individual);
		}
	}
	/**
     * Inserts Individual from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertIndividual(Document document, Element element, MismoIndividualModel individual) {
		if(!individual.getContactPoints().isEmpty())
			insertContactPoints(document,insertLevels(document, element, "CONTACT_POINTS"), individual.getContactPoints());
		insertName(document,insertLevels(document, element, "NAME"), individual.getName());
	}
	/**
     * Inserts Name from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertName(Document document, Element element, NameModel name) {
		insertData(document, element, "FirstName", name.getFirstName());
		insertData(document, element, "FullName", name.getFullName());
		insertData(document, element, "LastName", name.getLastName());
		insertData(document, element, "MiddleName",name.getLastName());
		insertData(document, element, "SuffixName", name.getSuffixName());
	}
	/**
     * Inserts Contact Points to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertContactPoints(Document document, Element element, List<MismoContactPointsModel> contactPoints) {
		for (MismoContactPointsModel contactPoint : contactPoints)
			insertContactPoint(document, insertLevels(document, element, "CONTACT_POINT"), contactPoint);
	
	}
	/**
     * Inserts Contact Point to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertContactPoint(Document document, Element element, MismoContactPointsModel contactPoint) {
		if(!contactPoint.getEmail().isEmpty())
			insertContactPointEmail(document, insertLevels(document, element, "CONTACT_POINT_EMAIL"), contactPoint.getEmail());
		if(!contactPoint.getPhone().isEmpty())
			insertContactPointTelephone(document, insertLevels(document, element, "CONTACT_POINT_TELEPHONE"), contactPoint.getPhone());
	}
	/**
     * Inserts Contact Point Telephone to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertContactPointTelephone(Document document, Element element,
			String phone) {
		insertData(document, element, "ContactPointTelephoneValue", phone);
	}
	/**
     * Inserts Contact Point Email to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertContactPointEmail(Document document, Element element,
			String email) {
		insertData(document, element, "ContactPointEmailValue", email);
	}
	/**
     * Inserts Legal Entity to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertLegalEntity(Document document, Element element, String fullName) {
		insertLegalEntityDetail(document,insertLevels(document, element, "LEGAL_ENTITY_DETAIL"), fullName);
	}
	/**
     * Inserts Legal Entity Detail to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertLegalEntityDetail(Document document, Element element,
			String fullName) {
		insertData(document, element, "FullName", fullName);
		//insertData(document, element, "GlobalLegalEntityIdentifier", "");
	}
	/**
     * Inserts Closing Adjustment Item Detail to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertClosingAdjustmentItemDetail(Document document, Element element,
			ClosingAdjustmentItemModel closingAdjustmentItem) {
		insertData(document, element, "ClosingAdjustmentItemAmount", Convertor.checkAmountFormat(closingAdjustmentItem.getClosingAdjustmentItemAmount()));
		//insertData(document, element, "ClosingAdjustmentItemPaidOutsideOfClosingIndicator",);
		Element closingAdjustmentItemTypeElement = returnElement(document, element, "ClosingAdjustmentItemType", closingAdjustmentItem.getClosingAdjustmentItemType());
			if(null != closingAdjustmentItem.getDisplayLabel() && !closingAdjustmentItem.getDisplayLabel().isEmpty())
				closingAdjustmentItemTypeElement.setAttribute("gse:DisplayLabelText",closingAdjustmentItem.getDisplayLabel());
		insertData(document, element, "ClosingAdjustmentItemTypeOtherDescription", closingAdjustmentItem.getClosingAdjustmentItemTypeOtherDescription());
		insertData(document, element, "IntegratedDisclosureSectionType", closingAdjustmentItem.getIntegratedDisclosureSectionType());
		insertData(document, element, "IntegratedDisclosureSubsectionType", closingAdjustmentItem.getIntegratedDisclosureSubsectionType());
	
	}
	/**
	 * inserts AmortizationRule in MISMO XML
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertAmortizationRule(Document document, Element element, LoanInformation loanInformation) {
		insertData(document ,element ,"AmortizationType" , loanInformation.getAmortizationType());
		insertData(document ,element ,"LoanAmortizationPeriodCount" , loanInformation.getLoanAmortizationPeriodCount());
		insertData(document ,element ,"LoanAmortizationPeriodType" , loanInformation.getLoanAmortizationPeriodType());
	}
	/**
     * Inserts Adjustment to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertAdjustment(Document document, Element element, ClosingDisclosure jsonDocument) {
		insertInterestRateAdjustment(document ,insertLevels(document, element, "INTEREST_RATE_ADJUSTMENT"), jsonDocument.getInterestRateAdjustment());
		insertPrincipalAndInterestPaymentAdjustment(document ,insertLevels(document, element, "PRINCIPAL_AND_INTEREST_PAYMENT_ADJUSTMENT"), jsonDocument.getPrincipalAndInterestPaymentAdjustment());
	}
	/**
     * Inserts Principal And Interest Payment Adjustment to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param principalAndInterestPaymentAdjustment Input JSON Object
     */
	private void insertPrincipalAndInterestPaymentAdjustment(Document document, Element element,
			PrincipalAndInterestPaymentAdjustmentModel principalAndInterestPaymentAdjustment) {
		insertPrincipalAndInterestPaymentLifetimeAdjustmentRule(document ,insertLevels(document, element, "PRINCIPAL_AND_INTEREST_PAYMENT_LIFETIME_ADJUSTMENT_RULE"), principalAndInterestPaymentAdjustment);
		insertPrincipalAndInterestPaymentPerChangeAdjustmentRules(document ,insertLevels(document, element, "PRINCIPAL_AND_INTEREST_PAYMENT_PER_CHANGE_ADJUSTMENT_RULES"), principalAndInterestPaymentAdjustment);
	}
	/**
     * Inserts Principal And Interest Payment Per Change Adjustment Rules to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPrincipalAndInterestPaymentPerChangeAdjustmentRules(Document document, Element element,
			PrincipalAndInterestPaymentAdjustmentModel principalAndInterestPaymentAdjustment) {
		if("First".equalsIgnoreCase(principalAndInterestPaymentAdjustment.getFirstAdjustmentRuleType()) || principalAndInterestPaymentAdjustment.getFirstAdjustmentRuleType().isEmpty())
			insertPrincipalAndInterestPaymentPerChangeAdjustmentRule(document,insertLevels(document, element, "PRINCIPAL_AND_INTEREST_PAYMENT_PER_CHANGE_ADJUSTMENT_RULE"),principalAndInterestPaymentAdjustment, "First");
		if("Subsequent".equalsIgnoreCase(principalAndInterestPaymentAdjustment.getSubsequentAdjustmentRuleType()))
			insertPrincipalAndInterestPaymentPerChangeAdjustmentRule(document,insertLevels(document, element, "PRINCIPAL_AND_INTEREST_PAYMENT_PER_CHANGE_ADJUSTMENT_RULE"),principalAndInterestPaymentAdjustment, "Subsequent");
	}
	/**
     * Inserts Principal And Interest Payment Per Change Adjustment Rule to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPrincipalAndInterestPaymentPerChangeAdjustmentRule(Document document, Element element,
			PrincipalAndInterestPaymentAdjustmentModel principalAndInterestPaymentAdjustment, String type) {
		if("First".equalsIgnoreCase(type))
		{
			insertData(document,element, "AdjustmentRuleType", principalAndInterestPaymentAdjustment.getFirstAdjustmentRuleType());
			insertData(document,element, "PerChangeMaximumPrincipalAndInterestPaymentAmount", Convertor.checkAmountFormat(principalAndInterestPaymentAdjustment.getFirstPerChangeMaximumPrincipalAndInterestPaymentAmount())); 
			insertData(document,element, "PerChangeMinimumPrincipalAndInterestPaymentAmount", Convertor.checkAmountFormat(principalAndInterestPaymentAdjustment.getFirstPerChangeMinimumPrincipalAndInterestPaymentAmount()));
			insertData(document,element, "PerChangePrincipalAndInterestPaymentAdjustmentFrequencyMonthsCount", principalAndInterestPaymentAdjustment.getFirstPerChangePrincipalAndInterestPaymentAdjustmentFrequencyMonthsCount());
		}
		if("Subsequent".equalsIgnoreCase(type))
		{
			insertData(document,element, "AdjustmentRuleType", principalAndInterestPaymentAdjustment.getSubsequentAdjustmentRuleType());
			insertData(document,element, "PerChangeMaximumPrincipalAndInterestPaymentAmount", Convertor.checkAmountFormat(principalAndInterestPaymentAdjustment.getSubsequentPerChangeMaximumPrincipalAndInterestPaymentAmount())); 
			insertData(document,element, "PerChangeMinimumPrincipalAndInterestPaymentAmount", Convertor.checkAmountFormat(principalAndInterestPaymentAdjustment.getSubsequentPerChangeMinimumPrincipalAndInterestPaymentAmount()));
			insertData(document,element, "PerChangePrincipalAndInterestPaymentAdjustmentFrequencyMonthsCount", principalAndInterestPaymentAdjustment.getSubsequentPerChangePrincipalAndInterestPaymentAdjustmentFrequencyMonthsCount());
		}
	}
	/**
     * Inserts Principal And Interest Payment Lifetime Adjustment Rule to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPrincipalAndInterestPaymentLifetimeAdjustmentRule(Document document, Element element,
			PrincipalAndInterestPaymentAdjustmentModel principalAndInterestPaymentAdjustment) {
		insertData(document, element, "FirstPrincipalAndInterestPaymentChangeMonthsCount",principalAndInterestPaymentAdjustment.getFirstPrincipalAndInterestPaymentChangeMonthsCount() );
		insertData(document, element, "PrincipalAndInterestPaymentMaximumAmount", Convertor.checkAmountFormat(principalAndInterestPaymentAdjustment.getPrincipalAndInterestPaymentMaximumAmount()));
		insertData(document, element, "PrincipalAndInterestPaymentMaximumAmountEarliestEffectiveMonthsCount", principalAndInterestPaymentAdjustment.getPrincipalAndInterestPaymentMaximumAmountEarliestEffectiveMonthsCount());
	}
	/**
     * inserts Interest Rate Adjustment to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertInterestRateAdjustment(Document document, Element element, InterestRateAdjustmentModel interestRateAdjustment) {
		if(!interestRateAdjustment.getIndexType().isEmpty() && null != interestRateAdjustment.getIndexType())
			insertIndexRules(document ,insertLevels(document, element, "INDEX_RULES/INDEX_RULE"), interestRateAdjustment.getIndexType());
		insertInterestRateLifetimeAdjustmentRule(document ,insertLevels(document, element, "INTEREST_RATE_LIFETIME_ADJUSTMENT_RULE"), interestRateAdjustment);
		insertInterestRatePerChangeAdjustmentRules(document ,insertLevels(document, element, "INTEREST_RATE_PER_CHANGE_ADJUSTMENT_RULES"), interestRateAdjustment);
	}
	/**
     * inserts Interest Rate Per Change Adjustment Rules to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertInterestRatePerChangeAdjustmentRules(Document document, Element element,
			InterestRateAdjustmentModel interestRateAdjustment) {
		if("First".equalsIgnoreCase(interestRateAdjustment.getFirstAdjustmentRule()))
			insertInterestRatePerChangeAdjustmentRule(document ,insertLevels(document, element, "INTEREST_RATE_PER_CHANGE_ADJUSTMENT_RULE"), interestRateAdjustment, "First");
		if("Subsequent".equalsIgnoreCase(interestRateAdjustment.getSubsequentAdjustmentRule()))
			insertInterestRatePerChangeAdjustmentRule(document ,insertLevels(document, element, "INTEREST_RATE_PER_CHANGE_ADJUSTMENT_RULE"), interestRateAdjustment, "Subsequent");
		
	}
	/**
     * Inserts Interest Rate Per Change Adjustment Rule to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertInterestRatePerChangeAdjustmentRule(Document document, Element element,
			InterestRateAdjustmentModel interestRateAdjustment, String type) {
		if("First".equalsIgnoreCase(type))
		{
			insertData(document ,element , "AdjustmentRuleType", interestRateAdjustment.getFirstAdjustmentRule());
			insertData(document ,element , "PerChangeMaximumIncreaseRatePercent", interestRateAdjustment.getFirstPerChangeMaximumIncreaseRatePercent());
			insertData(document ,element , "PerChangeRateAdjustmentFrequencyMonthsCount", interestRateAdjustment.getFirstPerChangeRateAdjustmentFrequencyMonthsCount());
		}
		if("Subsequent".equalsIgnoreCase(type))
		{
			insertData(document ,element , "AdjustmentRuleType", interestRateAdjustment.getSubsequentAdjustmentRule());
			insertData(document ,element , "PerChangeMaximumIncreaseRatePercent", interestRateAdjustment.getSubsequentPerChangeMaximumIncreaseRatePercent());
			insertData(document ,element , "PerChangeRateAdjustmentFrequencyMonthsCount", interestRateAdjustment.getSubsequentPerChangeRateAdjustmentFrequencyMonthsCount());
		}
		
	}                                
	/**
     * Inserts Interest Rate Lifetime Adjustment Rule to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertInterestRateLifetimeAdjustmentRule(Document document, Element element,
			InterestRateAdjustmentModel interestRateLifetimeAdjustmentRule) {
		insertData(document, element, "CeilingRatePercent", interestRateLifetimeAdjustmentRule.getCeilingRatePercent());
		insertData(document, element, "CeilingRatePercentEarliestEffectiveMonthsCount", interestRateLifetimeAdjustmentRule.getCeilingRatePercentEarliestEffectiveMonthsCount());
		insertData(document, element, "FirstRateChangeMonthsCount", interestRateLifetimeAdjustmentRule.getFirstRateChangeMonthsCount());
		insertData(document, element, "FloorRatePercent", interestRateLifetimeAdjustmentRule.getFloorRatePercent());
		insertData(document, element, "MarginRatePercent", interestRateLifetimeAdjustmentRule.getMarginRatePercent());
		OtherModel other = new OtherModel();
			other.setTotalStepCount(interestRateLifetimeAdjustmentRule.getTotalStepCount());
		insertExtension(document ,insertLevels(document, element, "EXTENSION"), other);
		
	}
	/**
	 * inserts Extension in MISMO XML
	 * @param document
	 * @param element
	 * @param jsonDocument
	 */
	private void insertExtension(Document document, Element element, OtherModel other) {
		if(!"".equals(other.getPaymentIncludedInAPRIndicator()) || !"".equals(other.getPayoffPartialIndicator()))
			insertMismo(document ,insertLevels(document, element, "MISMO") ,other);
		insertOther(document ,insertLevels(document, element, "OTHER") ,other);
	}
	
	/**
	 * inserts gse datapoints under Other in MISMO XML
	 * @param document
	 * @param element
	 * @param other
	 */
	private void insertOther(Document document, Element element, OtherModel other) {
	 	insertData(document, element, GSE_ALIAS + ":BuydownReflectedInNoteIndicator", other.getBuydownReflectedInNoteIndicator());
		insertData(document, element, GSE_ALIAS + ":DocumentSignatureRequiredIndicator", other.getDocumentSignatureRequiredIndicator());
		insertData(document, element, GSE_ALIAS + ":EscrowAccountRolloverAmount", Convertor.checkAmountFormat(other.getEscrowAccountRolloverAmount()));
		insertData(document, element,
				GSE_ALIAS + ":IntegratedDisclosureEstimatedClosingCostsExpirationTimezoneType", other.getIntegratedDisclosureEstimatedClosingCostsExpirationTimezoneType());
		insertData(document, element, GSE_ALIAS + ":IntegratedDisclosureSectionType", other.getIntegratedDisclosureSectionType());
		insertData(document, element, GSE_ALIAS + ":LiabilitySecuredBySubjectPropertyIndicator", other.getLiabilitySecuredBySubjectPropertyIndicator());
		insertData(document, element, GSE_ALIAS + ":LockExpirationTimezoneType", other.getLockExpirationTimezoneType());
		insertData(document, element, GSE_ALIAS + ":TotalOptionalPaymentCount", other.getTotalOptionalPaymentCount());
		insertData(document, element, GSE_ALIAS + ":TotalStepCount", other.getTotalStepCount());
		insertData(document, element, GSE_ALIAS + ":TotalStepPaymentCount", other.getTotalStepPaymentCount());
		insertData(document, element, GSE_ALIAS + ":SubordinateFinancingIsNewIndicator", other.getSubordinateFinancingIsNewIndicator());
		insertData(document, element, GSE_ALIAS + ":EscrowAggregateAccountingAdjustmentPaidByType", other.getEscrowAggregateAccountingAdjustmentPaidByType());
		insertData(document, element, GSE_ALIAS + ":EscrowAggregateAccountingAdjustmentPaymentTimingType", other.getEscrowAggregateAccountingAdjustmentPaymentTimingType());
	}
	
	/**
	 * inserts mismo datapoints under Other in MISMO XML
	 * @param document
	 * @param element
	 * @param other
	 */
	private void insertMismo(Document document, Element element, OtherModel other) {
		insertData(document, element, "PaymentIncludedInAPRIndicator", other.getPaymentIncludedInAPRIndicator());
		insertData(document, element, "PayoffPartialIndicator", other.getPayoffPartialIndicator());
	}
	/**
     * Inserts Index Rules from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertIndexRules(Document document, Element element, String indexType) {
		//for (IndexRule indexRule : indexRules)
		insertIndexRule(document, element, "INDEX_RULE", indexType);
	}
	/**
     * Inserts Index Rule from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertIndexRule(Document document, Element element, String string, String indexType) {
		insertData(document, element, "IndexType", indexType);
		//insertData(document, element, "IndexTypeOtherDescription", "jsonDocument.IndexTypeOtherDescription");
	}
	/**
     * Inserts Loan Identifiers to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertLoanIdentifiers(Document document, Element element, List<LoanInformationLoanIdentifier> loanIdentifiers) {
	
		for(LoanInformationLoanIdentifier loanIdentifierModel: loanIdentifiers)
		{
			Element loanIdentifier = insertLevels(document, element, "LOAN_IDENTIFIER");
			    insertData(document, loanIdentifier, "LoanIdentifier", loanIdentifierModel.getLoanIdentifier());
			    insertData(document, loanIdentifier, "LoanIdentifierType", loanIdentifierModel.getLoanIdentifierType());
		}
	}
	/**
     * Inserts Maturity Rule to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertMaturityRule(Document document, Element element, MaturityRuleModel maturityRule) {
			insertData(document, element, "LoanMaturityPeriodCount", maturityRule.getLoanMaturityPeriodCount());
			insertData(document, element, "LoanMaturityPeriodType", maturityRule.getLoanMaturityPeriodType());
			insertData(document, element, "LoanTermMaximumMonthsCount", maturityRule.getLoanTermMaximumMonthsCount());
	}
	/**
     * Inserts MIData to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertMIData(Document document, Element element, ClosingDisclosure jsonDocument) {
		insertMIDataDetail(document, insertLevels(document, element, "MI_DATA_DETAIL"), jsonDocument.getMiDataDetail()); 
		insertMIPremium(document, insertLevels(document, element, "MI_PREMIUMS"), jsonDocument.getMiPremium());
	}
    /**
     * Inserts Message to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertMessage(Document document, Element element, ClosingDisclosure jsonDocument) {
		element.setAttribute(XMLNS_ALIAS + ":xsi", XSI_URI);
		element.setAttribute("xsi:schemaLocation", "http://www.mismo.org/residential/2009/schemas ../../../MISMO/V3.3.0_CR_2014-02/ReferenceModel_v3.3.0_B299/MISMO_3.3.0_B299.xsd");
		element.setAttribute(XMLNS_ALIAS + ":" + MISMO_ALIAS, MISMO_URI);
		element.setAttribute(XMLNS_ALIAS + ":" + GSE_ALIAS, GSE_URI);
		element.setAttribute(XMLNS_ALIAS + ":" + XLINK_ALIAS, XLINK_URI);
		element.setAttribute("MISMOReferenceModelIdentifier", "3.3.0299");
		insertAboutVersion(document, insertLevels(document, element, "ABOUT_VERSIONS/ABOUT_VERSION"), jsonDocument);
		insertDocumentSet(document, insertLevels(document, element, "DOCUMENT_SETS/DOCUMENT_SET"), jsonDocument);
	}
	/**
     * Inserts Parties to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertParties(Document document, Element element, ClosingDisclosure jsonDocument) {
		List<Borrower> borrowers = jsonDocument.getTransactionInformation().getBorrower();
			insertPartys(document, element, borrowers);
		List<Borrower> sellers = jsonDocument.getTransactionInformation().getSeller();
			insertPartys(document, element, sellers);
	/*	List<Borrower> lenders = jsonDocument.getTransactionInformation().getLender();
			insertPartys(document, element, lenders);*/
				
		ContactInformationDetailModel mortagageBroker =	jsonDocument.getContactInformation().getMortagageBroker();
		if(null != mortagageBroker)
			insertParty(document, element, mortagageBroker,"mortagageBroker");
		
		ContactInformationDetailModel lender =	jsonDocument.getContactInformation().getLender();
		if(null != lender)
			insertParty(document, element, lender, "lender");
		
		ContactInformationDetailModel realEstateBrokerB =	jsonDocument.getContactInformation().getRealEstateBrokerB();
		if(null != realEstateBrokerB)
			insertParty(document, element, realEstateBrokerB, "realEstateBrokerB");
		
		ContactInformationDetailModel realEstateBrokerS =	jsonDocument.getContactInformation().getRealEstateBrokerS();
		if(null != realEstateBrokerS)
			insertParty(document, element, realEstateBrokerS, "realEstateBrokerS");
		
		ContactInformationDetailModel settlementAgent =	jsonDocument.getContactInformation().getSettlementAgent();
		if(null != settlementAgent)
			insertParty(document, element, settlementAgent, "settlementAgent");
	}
	
	/**
     * Inserts Party to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPartys(Document document, Element element, List<Borrower> borrowers)
	{
		for (Borrower borrower : borrowers) {
			RelationshipsModel relationship = new RelationshipsModel();
			SignatoriesModel signatory = new SignatoriesModel();
			
			Element party = insertLevels(document, element, "PARTY");
			
			if("O".equalsIgnoreCase(borrower.getType()))
			{
				Element legalEntity = insertLevels(document, party, "LEGAL_ENTITY/LEGAL_ENTITY_DETAIL");
					insertData(document, legalEntity, "FullName", borrower.getNameModel().getFullName());
			}
			else
			{
				Element name = insertLevels(document, party, "INDIVIDUAL/NAME");
					insertData(document, name, "FirstName", borrower.getNameModel().getFirstName());
					insertData(document, name, "LastName", borrower.getNameModel().getLastName());
					insertData(document, name, "MiddleName", borrower.getNameModel().getMiddleName());
					insertData(document, name, "SuffixName", borrower.getNameModel().getSuffixName());
			}
			
			Element address = insertLevels(document, party, "ADDRESSES/ADDRESS");
				insertData(document, address, "AddressLineText", borrower.getAddress().getAddressLineText());
				insertData(document, address, "AddressUnitDesignatorType", borrower.getAddress().getAddressUnitDesignatorType());
				insertData(document, address, "AddressUnitIdentifier", borrower.getAddress().getAddressUnitIdentifier());
				insertData(document, address, "AddressType", borrower.getAddress().getAddressType());
				insertData(document, address, "CityName", borrower.getAddress().getCityName());
				insertData(document, address, "CountryCode", borrower.getAddress().getCountryCode());
				insertData(document, address, "PostalCode", borrower.getAddress().getPostalCode());
				insertData(document, address, "StateCode", borrower.getAddress().getStateCode());
			
			Element role = insertLevels(document, party, "ROLES/ROLE");
			if(!"PropertySeller".equalsIgnoreCase(borrower.getPartyRoleType()))
			{
				role.setAttribute("SequenceNumber", Integer.toString(borrowerSNumber));
				role.setAttribute(XLINK_ALIAS+":label", "PARTY" + Integer.toString(borrowerSNumber) + "_ROLE1");
				relationship.setXlinkFrom("PARTY" + Integer.toString(borrowerSNumber) + "_ROLE1");
				relationship.setSequenceNumber(Integer.toString(borrowerSNumber));
				borrowerSNumber ++;
				if(13 == borrowerSNumber)
					borrowerSNumber = borrowerSNumber + 2;
				
				signatory.setSequenceNumber(Integer.toString(signatoryBorrowerSNumber));
				signatory.setXlabel("SIGNATORY_"+ Integer.toString(signatoryBorrowerSNumber));
				
				
				relationship.setXlinkTo("SIGNATORY_"+ Integer.toString(signatoryBorrowerSNumber));
				
				signatoryBorrowerSNumber++;
				
				if(3 == signatoryBorrowerSNumber)
					signatoryBorrowerSNumber = signatoryBorrowerSNumber + 2;
				
			}
			else if("PropertySeller".equalsIgnoreCase(borrower.getPartyRoleType()))
			{
				if(14 > sellerSNumber)
				{
					role.setAttribute("SequenceNumber", Integer.toString(sellerSNumber));
					role.setAttribute(XLINK_ALIAS+":label", "PARTY" + Integer.toString(sellerSNumber) + "_ROLE1");
					relationship.setXlinkFrom("PARTY" + Integer.toString(sellerSNumber) + "_ROLE1");
					sellerSNumber ++;
				}
				else
				{
					role.setAttribute("SequenceNumber", Integer.toString(borrowerSNumber));
					role.setAttribute(XLINK_ALIAS+":label", "PARTY" + Integer.toString(borrowerSNumber) + "_ROLE1");
					relationship.setXlinkFrom("PARTY" + Integer.toString(borrowerSNumber) + "_ROLE1");
					borrowerSNumber ++;
				}
				
		
				if(14 > relationshipSellerSNumber && 4 > signatorySellerSNumber)
				{
					signatory.setSequenceNumber(Integer.toString(signatorySellerSNumber));
					signatory.setXlabel("SIGNATORY_"+ Integer.toString(signatorySellerSNumber));
					
					relationship.setSequenceNumber(Integer.toString(relationshipSellerSNumber));
					relationship.setXlinkTo("SIGNATORY_"+ Integer.toString(signatorySellerSNumber));
					
					relationshipSellerSNumber++;
					signatorySellerSNumber++;
				}
				else
				{
					signatory.setSequenceNumber(Integer.toString(signatoryBorrowerSNumber));
					signatory.setXlabel("SIGNATORY_"+ Integer.toString(signatoryBorrowerSNumber));
					
					relationship.setSequenceNumber(Integer.toString(borrowerSNumber));
					relationship.setXlinkTo("SIGNATORY_"+ Integer.toString(signatoryBorrowerSNumber));
					
					signatoryBorrowerSNumber++;
					borrowerSNumber++;
				}
			}
			
				signatory.setActualSignatureType("wet");
				signatory.setExecutionDate("2013-04-15");
				
			signatories.add(signatory);
				
				relationship.setXlinkArcrole("urn:fdc:mismo.org:2009:residential/ROLE_IsAssociatedWith_SIGNATORY");
				if(null != relationship.getSequenceNumber() && !relationship.getSequenceNumber().isEmpty())
					relationships.add(relationship);
				
			Element roleDetail = insertLevels(document, role, "ROLE_DETAIL");
				insertData(document, roleDetail, "PartyRoleType", borrower.getPartyRoleType());
				if ("Other".equals(borrower.getPartyRoleType()))
					insertData(document, roleDetail, "PartyRoleTypeOtherDescription", borrower.getPartyRoleOtherDescription());
		}
	}
	
	/**
	 * inserts party to MISMO XML	
	 * @param document
	 * @param element
	 * @param partyDetail
	 * @param type
	 */
	private void insertParty(Document document, Element element, ContactInformationDetailModel partyDetail, String type)
	{
		RelationshipsModel relationship = new RelationshipsModel();
		
		String reType = "";
		if("realEstateBrokerB".equalsIgnoreCase(type))
			reType = "Selling";
		else if("realEstateBrokerS".equalsIgnoreCase(type))
			reType = "Listing";
		
		if(null != partyDetail.getOrganizationName() && !partyDetail.getOrganizationName().isEmpty())
		{
			Element party = insertLevels(document, element, "PARTY");
			
			Element legalEntity = insertLevels(document, party, "LEGAL_ENTITY/LEGAL_ENTITY_DETAIL");
				insertData(document, legalEntity, "FullName",partyDetail.getOrganizationName());
				
			Element address = insertLevels(document, party, "ADDRESSES/ADDRESS");
				insertData(document, address, "AddressLineText", partyDetail.getAddress().getAddressLineText());
				insertData(document, address, "AddressUnitDesignatorType", partyDetail.getAddress().getAddressUnitDesignatorType());
				insertData(document, address, "AddressUnitIdentifier", partyDetail.getAddress().getAddressUnitIdentifier());
				insertData(document, address, "AddressType", partyDetail.getAddress().getAddressType());
				insertData(document, address, "CityName", partyDetail.getAddress().getCityName());
				insertData(document, address, "CountryCode", partyDetail.getAddress().getCountryCode());
				insertData(document, address, "PostalCode", partyDetail.getAddress().getPostalCode());
				insertData(document, address, "StateCode", partyDetail.getAddress().getStateCode());
								
				Element role = insertLevels(document, party, "ROLES/ROLE");
				Element licenseDetail = insertLevels(document, role, "LICENSES/LICENSE/LICENSE_DETAIL");
				
				String label = Convertor.getPartySNumber(partyDetail.getPartyRoleType(), "O", reType);
				String xlink = Convertor.getXLink(partyDetail.getPartyRoleType(), "O", reType);
			
				if(null != label && !label.isEmpty())
					role.setAttribute("SequenceNumber", label);
				if(null != xlink && !xlink.isEmpty())
					role.setAttribute(XLINK_ALIAS+":label", xlink);
				
				relationship.setXlinkTo(xlink);
				
				if("realEstateBrokerB".equalsIgnoreCase(type))
				{
					Element reAgent = insertLevels(document, role, "REAL_ESTATE_AGENT");
						insertData(document, reAgent, "RealEstateAgentType", "Selling");
				}
				else if("realEstateBrokerS".equalsIgnoreCase(type))
				{
					Element reAgent = insertLevels(document, role, "REAL_ESTATE_AGENT");
						insertData(document, reAgent, "RealEstateAgentType", "Listing");
				}	
				
				Element roleDetail = insertLevels(document, role, "ROLE_DETAIL");
				
				insertData(document, licenseDetail, "LicenseAuthorityLevelType", partyDetail.getOrganizationLicenseDetail().getLicenseAuthorityLevelType());
				Element identifier =  returnElement(document, licenseDetail, "LicenseIdentifier", partyDetail.getOrganizationLicenseDetail().getLicenseIdentifier());
				if(null != identifier &&  null != partyDetail.getOrganizationLicenseDetail().getIdentifierOwnerURI() && !partyDetail.getOrganizationLicenseDetail().getIdentifierOwnerURI().isEmpty())
					identifier.setAttribute("IdentifierOwnerURI", partyDetail.getOrganizationLicenseDetail().getIdentifierOwnerURI());
				insertData(document, licenseDetail, "LicenseIssueDate", partyDetail.getOrganizationLicenseDetail().getLicenseIssueDate());
				insertData(document, licenseDetail, "LicenseIssuingAuthorityName", partyDetail.getOrganizationLicenseDetail().getLicenseIssuingAuthorityName());
				insertData(document, licenseDetail, "LicenseIssuingAuthorityStateCode", partyDetail.getOrganizationLicenseDetail().getLicenseIssuingAuthorityStateCode());

				insertData(document, roleDetail, "PartyRoleType", partyDetail.getPartyRoleType());
		}
		
		if(!partyDetail.getName().getFirstName().isEmpty() || !partyDetail.getName().getLastName().isEmpty() || !partyDetail.getName().getMiddleName().isEmpty() || !partyDetail.getName().getSuffixName().isEmpty())
		{
			Element party = insertLevels(document, element, "PARTY");
			Element individual = insertLevels(document, party, "INDIVIDUAL");			
			if((!partyDetail.getIndividualEmail().isEmpty() && null != partyDetail.getIndividualEmail()) || (!partyDetail.getIndividualPhone().isEmpty() && null != partyDetail.getIndividualPhone()))	
			{	
				Element contact = insertLevels(document, individual, "CONTACT_POINTS");
				
				if((!partyDetail.getIndividualEmail().isEmpty() && null != partyDetail.getIndividualEmail()))
				{
					Element email = insertLevels(document, contact, "CONTACT_POINT/CONTACT_POINT_EMAIL");
						insertData(document, email, "ContactPointEmailValue", partyDetail.getIndividualEmail());
				}
				
				if(!partyDetail.getIndividualPhone().isEmpty() && null != partyDetail.getIndividualPhone())
				{
					Element phone = insertLevels(document, contact, "CONTACT_POINT/CONTACT_POINT_TELEPHONE");
						insertData(document, phone, "ContactPointTelephoneValue", partyDetail.getIndividualPhone());
				}
			}
			
			Element name = insertLevels(document, individual, "NAME");
				insertData(document, name, "FirstName", partyDetail.getName().getFirstName());
				insertData(document, name, "LastName", partyDetail.getName().getLastName());
				insertData(document, name, "MiddleName", partyDetail.getName().getMiddleName());
				insertData(document, name, "SuffixName", partyDetail.getName().getSuffixName());
					
			Element role = insertLevels(document, party, "ROLES/ROLE");
				
			String label = Convertor.getPartySNumber(partyDetail.getPartyRoleType(), "I", reType);
			String xlink = Convertor.getXLink(partyDetail.getPartyRoleType(), "I", reType);
			
				if(null != label && !label.isEmpty())
					role.setAttribute("SequenceNumber", label);
				if(null != xlink && !xlink.isEmpty())
					role.setAttribute(XLINK_ALIAS+":label", xlink);
			
				relationship.setXlinkFrom(xlink);
				
			if("realEstateBrokerB".equalsIgnoreCase(type))
			{
				Element reAgent = insertLevels(document, role, "REAL_ESTATE_AGENT");
					insertData(document, reAgent, "RealEstateAgentType", "Selling");
			}
			else if("realEstateBrokerS".equalsIgnoreCase(type))
			{
				Element reAgent = insertLevels(document, role, "REAL_ESTATE_AGENT");
					insertData(document, reAgent, "RealEstateAgentType", "Listing");
			}	
			
			Element licenseDetail = insertLevels(document, role, "LICENSES/LICENSE/LICENSE_DETAIL");
				insertData(document, licenseDetail, "LicenseAuthorityLevelType", partyDetail.getIndividualLicenseDetail().getLicenseAuthorityLevelType());
				
			Element identifier =  returnElement(document, licenseDetail, "LicenseIdentifier", partyDetail.getIndividualLicenseDetail().getLicenseIdentifier());
					if(null != identifier &&  null != partyDetail.getIndividualLicenseDetail().getIdentifierOwnerURI() && !partyDetail.getIndividualLicenseDetail().getIdentifierOwnerURI().isEmpty())
						identifier.setAttribute("IdentifierOwnerURI", partyDetail.getIndividualLicenseDetail().getIdentifierOwnerURI());
				insertData(document, licenseDetail, "LicenseIssueDate", partyDetail.getIndividualLicenseDetail().getLicenseIssueDate());
				insertData(document, licenseDetail, "LicenseIssuingAuthorityName", partyDetail.getIndividualLicenseDetail().getLicenseIssuingAuthorityName());
				insertData(document, licenseDetail, "LicenseIssuingAuthorityStateCode", partyDetail.getIndividualLicenseDetail().getLicenseIssuingAuthorityStateCode());			
				
			Element roleDetail = insertLevels(document, role, "ROLE_DETAIL");
			
			insertData(document, roleDetail, "PartyRoleType", partyDetail.getPartyRoleType());
		}
		
			relationship.setXlinkArcrole("urn:fdc:mismo.org:2009:residential/ROLE_IsEmployedBy_ROLE");
			relationship.setSequenceNumber(Convertor.getRelationshipSNumber(partyDetail.getPartyRoleType(), reType));
			
		if(Convertor.checkNotNull(relationship.getXlinkFrom()) && Convertor.checkNotNull(relationship.getXlinkTo()))
			relationships.add(relationship);
		
	}
	
	
	/**
     * Inserts Subject Property from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertSubjectProperty(Document document, Element element, ClosingDisclosure jsonDocument) {

		insertAddress(document, insertLevels(document, element, "ADDRESS"), jsonDocument.getClosingInformation().getProperty());
		insertUnparsedLegalDescription(document, 
				insertLevels(document, element,"LEGAL_DESCRIPTIONS/LEGAL_DESCRIPTION/UNPARSED_LEGAL_DESCRIPTIONS/UNPARSED_LEGAL_DESCRIPTION"), jsonDocument.getClosingInformation().getProperty().getUnparsedLegalDescription());
		//insertLocationIdentifier(document, insertLevels(document, element, "LOCATION_IDENTIFIER"), jsonDocument);
		insertPropertyDetail(document, insertLevels(document, element, "PROPERTY_DETAIL"), Convertor.checkAmountFormat(jsonDocument.getClosingInformation().getPropertyValuationDetail().getPropertyEstimatedValueAmount()));
		insertPropertyValuations(document, insertLevels(document, element, "PROPERTY_VALUATIONS"), jsonDocument.getClosingInformation().getPropertyValuationDetail());
		insertSalesContractDetail(document, insertLevels(document, element, "SALES_CONTRACTS/SALES_CONTRACT/SALES_CONTRACT_DETAIL"), jsonDocument.getSalesContractDetail());
	
	}
	/**
     * Inserts Sales Contract Detail from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertSalesContractDetail(Document document, Element element,
			SalesContractDetailModel salesContractDetail) {
		insertData(document, element, "PersonalPropertyAmount", Convertor.checkAmountFormat(salesContractDetail.getPersonalPropertyAmount()));
		insertData(document, element, "PersonalPropertyIncludedIndicator",Convertor.booleanToString(salesContractDetail.getPersonalPropertyIndicator()));
		insertData(document, element, "RealPropertyAmount", Convertor.checkAmountFormat(salesContractDetail.getRealPropertyAmount()));
		insertData(document, element, "SalesContractAmount", Convertor.checkAmountFormat(salesContractDetail.getSaleContractAmount()));
	}
	/**
     * Inserts Property Valuations from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPropertyValuations(Document document, Element element,
			PropertyValuationDetailModel propertyValuationDetailModel) {
		//for (String group : groupings)
			insertPropertyValuation(document, insertLevels(document, element, "PROPERTY_VALUATION"), propertyValuationDetailModel);
	}
	/**
     * Inserts Property Valuation from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPropertyValuation(Document document, Element element,
			PropertyValuationDetailModel propertyValuationDetailModel) {
		insertPropertyValuationDetail(document, insertLevels(document, element, "PROPERTY_VALUATION_DETAIL"), propertyValuationDetailModel);
	}
	/**
     * Inserts Property Valuation Detail from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */	
	private void insertPropertyValuationDetail(Document document, Element element,
			PropertyValuationDetailModel propertyValuationDetail) {
		Element appraisalIdentifierelement = returnElement(document, element, "AppraisalIdentifier", propertyValuationDetail.getAppraisalIdentifier());
		if(null != appraisalIdentifierelement && null != propertyValuationDetail.getIdentifierOwnerURI() && ! propertyValuationDetail.getIdentifierOwnerURI().isEmpty())
			appraisalIdentifierelement.setAttribute("IdentifierOwnerURI", propertyValuationDetail.getIdentifierOwnerURI());
		insertData(document, element, "PropertyValuationAmount", Convertor.checkAmountFormat(propertyValuationDetail.getPropertyValuationAmount()));
		insertData(document, element, "PropertyValuationMethodType", propertyValuationDetail.getPropertyValuationMethodType());
		insertData(document, element, "PropertyValuationMethodTypeOtherDescription", propertyValuationDetail.getPropertyValuationMethodTypeOtherDescription());
		
	}
	/**
     * Inserts Property Detail from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertPropertyDetail(Document document, Element element, String propertyEstimatedValueAmount) {
		insertData(document, element, "AffordableUnitsCount", "");
		insertData(document, element, "ConstructionMethodType", "");
		insertData(document, element, "FinancedUnitCount", "");
		insertData(document, element, "MetropolitanDivisionIndicator", "");
		insertData(document, element, "MSAIndicator", "");
		insertData(document, element, "PropertyEstateType", "");
		insertData(document, element, "PropertyEstateTypeOtherDescription", "");
		insertData(document, element, "PropertyEstimatedValueAmount", propertyEstimatedValueAmount);
		insertData(document, element, "PropertyUsageType", "");
	}
	/**
     * Inserts Location Identifier to MISMO XML
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	/*private void insertLocationIdentifier(Document document, Element element,
			ClosingDisclosure jsonDocument) {
		insertCensusInformation(document, insertLevels(document, element, "CENSUS_INFORMATION"), "TODO");
		insertFipsInformation(document, insertLevels(document, element, "FIPS_INFORMATION"), "TODO");
	}*/
	/**
     * Inserts Fips Information from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */	
	/*private void insertFipsInformation(Document document, Element element,
			String FIPSCountyCode) {
		insertData(document, element, "FIPSCountyCode", FIPSCountyCode);
	}*/
	/**
     * Inserts Census Information from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */	
	/*private void insertCensusInformation(Document document, Element element,
			String censusTractIdentifier) {
		insertData(document, element, "CensusTractIdentifier", censusTractIdentifier);
	}*/
	/**
     * Inserts Address from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertAddress(Document document, Element element, AddressModel addressModel) {
		
		insertData(document, element, "AddressLineText", addressModel.getAddressLineText());
		insertData(document, element, "AddressUnitDesignatorType", addressModel.getAddressUnitDesignatorType());
		insertData(document, element, "AddressUnitIdentifier", addressModel.getAddressUnitIdentifier());
		insertData(document, element, "CityName", addressModel.getCityName());
		insertData(document, element, "PostalCode", addressModel.getPostalCode());
		insertData(document, element, "StateCode", addressModel.getStateCode());
	}
	/**
     * Inserts Unparsed Legal Description from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertUnparsedLegalDescription(Document document, Element element,
			String unparsedLegalDescription) {
		insertData(document, element, "UnparsedLegalDescription", unparsedLegalDescription);
	}
	/**
     * Inserts Terms Of Loan from JSON Object
     * @param document Output XML file
     * @param element parent node of XML
     * @param jsonDocument Input JSON Object
     */
	private void insertTermsOfLoan(Document document, Element element, TermsOfLoanModel termsOfLoan) {
		insertData(document, element, "AssumedLoanAmount", Convertor.checkAmountFormat(termsOfLoan.getAssumedLoanAmount()));
		insertData(document, element, "DisclosedFullyIndexedRatePercent", termsOfLoan.getDisclosedFullyIndexedRatePercent());
		insertData(document, element, "LienPriorityType", termsOfLoan.getLienPriorityType());
		insertData(document, element, "LoanPurposeType", termsOfLoan.getLoanPurposeType());
		insertData(document, element, "MortgageType", termsOfLoan.getMortgageType());
		insertData(document, element, "MortgageTypeOtherDescription", termsOfLoan.getMortgageTypeOtherDescription());
		insertData(document, element, "NoteAmount", Convertor.checkAmountFormat(termsOfLoan.getNoteAmount()));
		insertData(document, element, "NoteRatePercent", termsOfLoan.getNoteRatePercent());
		insertData(document, element, "WeightedAverageInterestRatePercent", termsOfLoan.getWeightedAverageInterestRatePercent());
	}
	
	/**
	 * deletes the empty nodes in the generated MISMO XML 
	 * @param node
	 */
	public static void removeEmptyNodes(Node node) {

        // Grab all children of node
        NodeList childnodes = node.getChildNodes();

        // Save all children nodes into list that doesn't change
        List<Node> immutable = new LinkedList<Node>();
        for (int i = 0; i < childnodes.getLength(); i++)
            immutable.add(childnodes.item(i));

        // Recursive through list that doesn't change
        for (Node child : immutable)
            removeEmptyNodes(child);

        boolean emptyElementNode = node.getNodeType() == Node.ELEMENT_NODE && node.getChildNodes().getLength() == 0
                && node.getAttributes().getLength() == 0;
        boolean emptyTextNode = node.getNodeType() == Node.TEXT_NODE && node.getNodeValue().trim().isEmpty();

        // Remove node if empty
        if (emptyElementNode || emptyTextNode)
            node.getParentNode().removeChild(node);
    }
}
