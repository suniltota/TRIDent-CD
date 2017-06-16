package com.actualize.mortgage.cd.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import com.actualize.mortgage.cd.domainmodels.ClosingDisclosure;
import com.actualize.mortgage.cd.domainmodels.IntegratedDisclosureSectionSummaryDetailModel;
import com.actualize.mortgage.cd.domainmodels.IntegratedDisclosureSectionSummaryModel;
import com.actualize.mortgage.cd.domainmodels.IntegratedDisclosureSubsectionPaymentModel;
import com.actualize.mortgage.cd.domainmodels.MismoPaymentsModel;
import com.actualize.mortgage.cd.domainmodels.MismoProjectedPaymentsModel;
import com.actualize.mortgage.cd.domainmodels.PaymentsModel;
import com.actualize.mortgage.cd.domainmodels.ProjectedPaymentsDetails;
import com.actualize.mortgage.cd.domainmodels.ProjectedPaymentsPC;

/**
 * This class is perform various operations such as conversions of JSON objects to MISMO Objects
 * @author sboragala
 *
 */
public class Convertor {
	
	/**
	 * converts payment model to Fee payments for inserting into MISMO XML 
	 * @param paymentsModel
	 * @return List of MismoFeePaymentsModels
	 */
	public static List<MismoPaymentsModel> toMismoFeePayments(PaymentsModel paymentsModel, String type)
	{
		List<MismoPaymentsModel> mismoFeePaymentList = new LinkedList<>();
		
		if("FEE".equalsIgnoreCase(type))
		{
			if(checkNotNull(paymentsModel.getBpAtClosing()))
			{
				MismoPaymentsModel mismoPaymentsModel = new MismoPaymentsModel();
					mismoPaymentsModel.setAmount(paymentsModel.getBpAtClosing());
					mismoPaymentsModel.setPaidByType("Buyer");
					mismoPaymentsModel.setClosingIndicator("false");
				mismoFeePaymentList.add(mismoPaymentsModel);
			}
			if(checkNotNull(paymentsModel.getBpB4Closing()))
			{
				MismoPaymentsModel mismoPaymentsModel = new MismoPaymentsModel();
					mismoPaymentsModel.setAmount(paymentsModel.getBpB4Closing());
					mismoPaymentsModel.setPaidByType("Buyer");
					mismoPaymentsModel.setClosingIndicator("true");
				mismoFeePaymentList.add(mismoPaymentsModel);
			}
			if(checkNotNull(paymentsModel.getSpAtClosing()))
			{
				MismoPaymentsModel mismoPaymentsModel = new MismoPaymentsModel();
					mismoPaymentsModel.setAmount(paymentsModel.getSpAtClosing());
					mismoPaymentsModel.setPaidByType("Seller");
					mismoPaymentsModel.setClosingIndicator("false");
				mismoFeePaymentList.add(mismoPaymentsModel);
			}
			if(checkNotNull(paymentsModel.getSpB4Closing()))
			{
				MismoPaymentsModel mismoPaymentsModel = new MismoPaymentsModel();
					mismoPaymentsModel.setAmount(paymentsModel.getSpB4Closing());
					mismoPaymentsModel.setPaidByType("Seller");
					mismoPaymentsModel.setClosingIndicator("true");
				mismoFeePaymentList.add(mismoPaymentsModel);
			}
			if(checkNotNull(paymentsModel.getPaidByOthers()))
			{
				MismoPaymentsModel mismoPaymentsModel = new MismoPaymentsModel();
					mismoPaymentsModel.setAmount(paymentsModel.getPaidByOthers());
					if(paymentsModel.isLenderStatus())
						mismoPaymentsModel.setPaidByType("Lender");
					else
						mismoPaymentsModel.setPaidByType("ThirdParty");
					mismoPaymentsModel.setClosingIndicator("");
				mismoFeePaymentList.add(mismoPaymentsModel);
			}
		}
		else if("ESCROW".equalsIgnoreCase(type))
		{
			if(checkNotNull(paymentsModel.getBpAtClosing()))
			{
				MismoPaymentsModel mismoPaymentsModel = new MismoPaymentsModel();
					mismoPaymentsModel.setAmount(paymentsModel.getBpAtClosing());
					mismoPaymentsModel.setPaidByType("Buyer");
					mismoPaymentsModel.setClosingIndicator("AtClosing");
				mismoFeePaymentList.add(mismoPaymentsModel);
			}
			if(checkNotNull(paymentsModel.getBpB4Closing()))
			{
				MismoPaymentsModel mismoPaymentsModel = new MismoPaymentsModel();
					mismoPaymentsModel.setAmount(paymentsModel.getBpB4Closing());
					mismoPaymentsModel.setPaidByType("Buyer");
					mismoPaymentsModel.setClosingIndicator("false");
				mismoFeePaymentList.add(mismoPaymentsModel);
			}
			if(checkNotNull(paymentsModel.getSpAtClosing()))
			{
				MismoPaymentsModel mismoPaymentsModel = new MismoPaymentsModel();
					mismoPaymentsModel.setAmount(paymentsModel.getSpAtClosing());
					mismoPaymentsModel.setPaidByType("Seller");
					mismoPaymentsModel.setClosingIndicator("AtClosing");
				mismoFeePaymentList.add(mismoPaymentsModel);
			}
			if(checkNotNull(paymentsModel.getSpB4Closing()))
			{
				MismoPaymentsModel mismoPaymentsModel = new MismoPaymentsModel();
					mismoPaymentsModel.setAmount(paymentsModel.getSpB4Closing());
					mismoPaymentsModel.setPaidByType("Seller");
					mismoPaymentsModel.setClosingIndicator("false");
				mismoFeePaymentList.add(mismoPaymentsModel);
			}
			if(checkNotNull(paymentsModel.getPaidByOthers()))
			{
				MismoPaymentsModel mismoPaymentsModel = new MismoPaymentsModel();
					mismoPaymentsModel.setAmount(paymentsModel.getPaidByOthers());
					if(paymentsModel.isLenderStatus())
						mismoPaymentsModel.setPaidByType("Lender");
					else
						mismoPaymentsModel.setPaidByType("ThirdParty");
					mismoPaymentsModel.setClosingIndicator("");
				mismoFeePaymentList.add(mismoPaymentsModel);
			}
		}
		else if("PREPAID".equalsIgnoreCase(type))
		{
			if(checkNotNull(paymentsModel.getBpAtClosing()))
			{
				MismoPaymentsModel mismoPaymentsModel = new MismoPaymentsModel();
					mismoPaymentsModel.setAmount(paymentsModel.getBpAtClosing());
					mismoPaymentsModel.setPaidByType("Buyer");
					mismoPaymentsModel.setClosingIndicator("AtClosing");
				mismoFeePaymentList.add(mismoPaymentsModel);
			}
			if(checkNotNull(paymentsModel.getBpB4Closing()))
			{
				MismoPaymentsModel mismoPaymentsModel = new MismoPaymentsModel();
					mismoPaymentsModel.setAmount(paymentsModel.getBpB4Closing());
					mismoPaymentsModel.setPaidByType("Buyer");
					mismoPaymentsModel.setClosingIndicator("BeforeClosing");
				mismoFeePaymentList.add(mismoPaymentsModel);
			}
			if(checkNotNull(paymentsModel.getSpAtClosing()))
			{
				MismoPaymentsModel mismoPaymentsModel = new MismoPaymentsModel();
					mismoPaymentsModel.setAmount(paymentsModel.getSpAtClosing());
					mismoPaymentsModel.setPaidByType("Seller");
					mismoPaymentsModel.setClosingIndicator("AtClosing");
				mismoFeePaymentList.add(mismoPaymentsModel);
			}
			if(checkNotNull(paymentsModel.getSpB4Closing()))
			{
				MismoPaymentsModel mismoPaymentsModel = new MismoPaymentsModel();
					mismoPaymentsModel.setAmount(paymentsModel.getSpB4Closing());
					mismoPaymentsModel.setPaidByType("Seller");
					mismoPaymentsModel.setClosingIndicator("BeforeClosing");
				mismoFeePaymentList.add(mismoPaymentsModel);
			}
			if(checkNotNull(paymentsModel.getPaidByOthers()))
			{
				MismoPaymentsModel mismoPaymentsModel = new MismoPaymentsModel();
					mismoPaymentsModel.setAmount(paymentsModel.getPaidByOthers());
					if(paymentsModel.isLenderStatus())
						mismoPaymentsModel.setPaidByType("Lender");
					else
						mismoPaymentsModel.setPaidByType("ThirdParty");
					mismoPaymentsModel.setClosingIndicator("");
				mismoFeePaymentList.add(mismoPaymentsModel);
			}
		}
		return mismoFeePaymentList;
	}
	
	/**
	 * converts ProjectedPaymentsDetails to MismoProjectedPaymentsModel
	 * @param projectedPayments
	 * @return list of MismoProjectedPaymentsModel 
	 */
	public static List<MismoProjectedPaymentsModel> createMismoProjectedPayments(ProjectedPaymentsDetails projectedPayments)
	{
		List<MismoProjectedPaymentsModel> mismoProjectedPaymentsModels = new LinkedList<>();
		
		for(ProjectedPaymentsPC projectedPaymentsPC : projectedPayments.getPaymentCalculation())
		{
			MismoProjectedPaymentsModel mismoProjectedPaymentsModel = new MismoProjectedPaymentsModel();
				mismoProjectedPaymentsModel.setPaymentFrequencyType(projectedPayments.getPaymentFrequencyType());
				mismoProjectedPaymentsModel.setProjectedPaymentCalculationPeriodEndNumber(projectedPaymentsPC.getProjectedPaymentCalculationPeriodEndNumber());
				mismoProjectedPaymentsModel.setProjectedPaymentCalculationPeriodStartNumber(projectedPaymentsPC.getProjectedPaymentCalculationPeriodStartNumber());
				mismoProjectedPaymentsModel.setSequenceNumber(projectedPaymentsPC.getSequenceNumber());
				mismoProjectedPaymentsModel.setProjectedPaymentCalculationPeriodTermType(projectedPaymentsPC.getProjectedPaymentCalculationPeriodTermType());
				mismoProjectedPaymentsModel.setProjectedPaymentCalculationPeriodTermTypeOtherDescription(projectedPaymentsPC.getProjectedPaymentCalculationPeriodTermTypeOtherDescription());				
			mismoProjectedPaymentsModels.add(mismoProjectedPaymentsModel);
		}
		
		for(int i=0; i<projectedPayments.getPrincipalInterest().size(); i++)
		{
			mismoProjectedPaymentsModels.get(i).setProjectedPaymentPrincipalAndInterestMaximumPaymentAmount(projectedPayments.getPrincipalInterest().get(i).getProjectedPaymentPrincipalAndInterestMaximumPaymentAmount());
			mismoProjectedPaymentsModels.get(i).setProjectedPaymentPrincipalAndInterestMinimumPaymentAmount(projectedPayments.getPrincipalInterest().get(i).getProjectedPaymentPrincipalAndInterestMinimumPaymentAmount());
		}
		
		for(int i=0; i<projectedPayments.getMortgageInsurance().size(); i++)
			mismoProjectedPaymentsModels.get(i).setProjectedPaymentMIPaymentAmount(projectedPayments.getMortgageInsurance().get(i).getProjectedPaymentMIPaymentAmount());
		
		for(int i=0; i<projectedPayments.getEstimatedEscrow().size(); i++)
			mismoProjectedPaymentsModels.get(i).setProjectedPaymentEstimatedEscrowPaymentAmount(projectedPayments.getEstimatedEscrow().get(i).getProjectedPaymentEstimatedEscrowPaymentAmount());
		
		for(int i=0; i<projectedPayments.getEstimatedTotal().size(); i++)
		{
			mismoProjectedPaymentsModels.get(i).setProjectedPaymentEstimatedTotalMaximumPaymentAmount(projectedPayments.getEstimatedTotal().get(i).getProjectedPaymentEstimatedTotalMaximumPaymentAmount());
			mismoProjectedPaymentsModels.get(i).setProjectedPaymentEstimatedTotalMinimumPaymentAmount(projectedPayments.getEstimatedTotal().get(i).getProjectedPaymentEstimatedTotalMinimumPaymentAmount());
		}
		
		return mismoProjectedPaymentsModels;
	}
	
	/**
	 * calculates IntegratedDisclosureSectionSummaryModels to insert into MISMO XML
	 * @param json
	 * @return list of IntegratedDisclosureSectionSummaryModel
	 */
	public static List<IntegratedDisclosureSectionSummaryModel> createIntegratedDisclosureSectionSummaryModels(ClosingDisclosure json)
	{
		List<IntegratedDisclosureSectionSummaryModel> integratedDisclosureSectionSummaryModels = new LinkedList<>();
		
		if(checkNotNull(json.getClosingCostDetailsLoanCosts().getOcTotalAmount()))
		{
			IntegratedDisclosureSectionSummaryModel integratedDisclosureSectionSummaryModel = new IntegratedDisclosureSectionSummaryModel();
			
			integratedDisclosureSectionSummaryModel = createIntegratedDisclosureSectionSummaryModel(json.getClosingCostDetailsLoanCosts().getOcTotalAmount(), "OriginationCharges", "", 
					"", false, null);			
			
			integratedDisclosureSectionSummaryModels.add(integratedDisclosureSectionSummaryModel);
		}
		
		if(checkNotNull(json.getClosingCostDetailsLoanCosts().getSbDidShopTotalAmount()))
		{
			IntegratedDisclosureSectionSummaryModel integratedDisclosureSectionSummaryModel = new IntegratedDisclosureSectionSummaryModel();
			
			integratedDisclosureSectionSummaryModel = createIntegratedDisclosureSectionSummaryModel(json.getClosingCostDetailsLoanCosts().getSbDidShopTotalAmount(),
			"ServicesBorrowerDidShopFor", "","", false, null);			
			
			integratedDisclosureSectionSummaryModels.add(integratedDisclosureSectionSummaryModel);
		}
		
		if(checkNotNull(json.getClosingCostDetailsLoanCosts().getSbDidNotShopTotalAmount()))
		{
			IntegratedDisclosureSectionSummaryModel integratedDisclosureSectionSummaryModel = new IntegratedDisclosureSectionSummaryModel();
			
			integratedDisclosureSectionSummaryModel = createIntegratedDisclosureSectionSummaryModel(json.getClosingCostDetailsLoanCosts().getSbDidNotShopTotalAmount(),
			"ServicesBorrowerDidNotShopFor", "","", false, null);			
			
			integratedDisclosureSectionSummaryModels.add(integratedDisclosureSectionSummaryModel);
		}
		
		if(checkNotNull(json.getClosingCostDetailsLoanCosts().getTlCostsTotalAmount()))
		{
			IntegratedDisclosureSectionSummaryModel integratedDisclosureSectionSummaryModel = new IntegratedDisclosureSectionSummaryModel();
			
			
			integratedDisclosureSectionSummaryModel = createIntegratedDisclosureSectionSummaryModel(json.getClosingCostDetailsLoanCosts().getTlCostsTotalAmount(),
			"TotalLoanCosts", "","LoanCostsSubtotal", true, json.getClosingCostDetailsLoanCosts().getTlCosts());			
			
			integratedDisclosureSectionSummaryModels.add(integratedDisclosureSectionSummaryModel);
		}
		
		if(checkNotNull(json.getClosingCostDetailsOtherCosts().gettOGovtFeesTotalAmount()))
		{
			IntegratedDisclosureSectionSummaryModel integratedDisclosureSectionSummaryModel = new IntegratedDisclosureSectionSummaryModel();
						
			integratedDisclosureSectionSummaryModel = createIntegratedDisclosureSectionSummaryModel(json.getClosingCostDetailsOtherCosts().gettOGovtFeesTotalAmount(),
			"TaxesAndOtherGovernmentFees", "","", false, null);		
			
			integratedDisclosureSectionSummaryModels.add(integratedDisclosureSectionSummaryModel);
		}
		
		if(checkNotNull(json.getClosingCostDetailsOtherCosts().getPrepaidsTotalAmount()))
		{
			IntegratedDisclosureSectionSummaryModel integratedDisclosureSectionSummaryModel = new IntegratedDisclosureSectionSummaryModel();
						
			integratedDisclosureSectionSummaryModel = createIntegratedDisclosureSectionSummaryModel(json.getClosingCostDetailsOtherCosts().getPrepaidsTotalAmount(),
			"Prepaids", "","", false, null);		
			
			integratedDisclosureSectionSummaryModels.add(integratedDisclosureSectionSummaryModel);
		}
		
		if(checkNotNull(json.getClosingCostDetailsOtherCosts().getEscrowItemsTotalAmount()))
		{
			IntegratedDisclosureSectionSummaryModel integratedDisclosureSectionSummaryModel = new IntegratedDisclosureSectionSummaryModel();
						
			integratedDisclosureSectionSummaryModel = createIntegratedDisclosureSectionSummaryModel(json.getClosingCostDetailsOtherCosts().getEscrowItemsTotalAmount(),
			"InitialEscrowPaymentAtClosing", "","", false, null);		
			
			integratedDisclosureSectionSummaryModels.add(integratedDisclosureSectionSummaryModel);
		}
		
		if(checkNotNull(json.getClosingCostDetailsOtherCosts().getOtherTotalAmount()))
		{
			IntegratedDisclosureSectionSummaryModel integratedDisclosureSectionSummaryModel = new IntegratedDisclosureSectionSummaryModel();
			
			integratedDisclosureSectionSummaryModel = createIntegratedDisclosureSectionSummaryModel(json.getClosingCostDetailsOtherCosts().getOtherTotalAmount(),
			"OtherCosts", "","", false, null);		
			
			integratedDisclosureSectionSummaryModels.add(integratedDisclosureSectionSummaryModel);
		}
		
		if(checkNotNull(json.getClosingCostDetailsOtherCosts().getTotalOtherCostsTotalAmount()))
		{
			IntegratedDisclosureSectionSummaryModel integratedDisclosureSectionSummaryModel = new IntegratedDisclosureSectionSummaryModel();
			
			integratedDisclosureSectionSummaryModel = createIntegratedDisclosureSectionSummaryModel(json.getClosingCostDetailsOtherCosts().getTotalOtherCostsTotalAmount(),
			"TotalOtherCosts", "","OtherCostsSubtotal", true, json.getClosingCostDetailsOtherCosts().getTotalOtherCosts());
			
			integratedDisclosureSectionSummaryModels.add(integratedDisclosureSectionSummaryModel);
		}
		
		//ClosingCostsTotal
		if(checkNotNull(json.getClosingCostsTotal().getTotalClosingCosts()))
		{
			IntegratedDisclosureSectionSummaryModel integratedDisclosureSectionSummaryModel = new IntegratedDisclosureSectionSummaryModel();
			
			integratedDisclosureSectionSummaryModel = createIntegratedDisclosureSectionSummaryModel(json.getClosingCostsTotal().getTotalClosingCosts(),
			"TotalClosingCosts", "","ClosingCostsSubtotal", true, json.getClosingCostsTotal().getClosingCostsSubtotal());
			
			integratedDisclosureSectionSummaryModels.add(integratedDisclosureSectionSummaryModel);
		}
		
		if(checkNotNull(json.getClosingCostsTotal().getLenderCredits()))
		{
			IntegratedDisclosureSectionSummaryModel integratedDisclosureSectionSummaryModel = new IntegratedDisclosureSectionSummaryModel();
			PaymentsModel lenderCredits = new PaymentsModel();
				lenderCredits.setBpAtClosing(json.getClosingCostsTotal().getLenderCredits());
			
			integratedDisclosureSectionSummaryModel = createIntegratedDisclosureSectionSummaryModel("",
			"TotalClosingCosts", "","LenderCredits", true, lenderCredits);
			
			integratedDisclosureSectionSummaryModel.getIntegratedDisclosureSectionSummaryDetailModel()
				.setLenderCreditToleranceCureAmount(json.getClosingCostsTotal().getLenderCreditToleranceCureAmount());
			
			integratedDisclosureSectionSummaryModels.add(integratedDisclosureSectionSummaryModel);
		}
		
		if(checkNotNull(json.getSummariesofTransactions().getBorrowerTransaction().getPaidAlreadyByOrOnBehalfOfBorrowerAtClosing().
			getIntegratedDisclosureSectionSummaryDetailModel().getIntegratedDisclosureSectionTotalAmount()) &&
				checkNotNull(json.getSummariesofTransactions().getBorrowerTransaction().getPaidAlreadyByOrOnBehalfOfBorrowerAtClosing().
						getIntegratedDisclosureSectionSummaryDetailModel().getIntegratedDisclosureSectionType()))
				integratedDisclosureSectionSummaryModels.add(json.getSummariesofTransactions().getBorrowerTransaction().getPaidAlreadyByOrOnBehalfOfBorrowerAtClosing());

		if(checkNotNull(json.getSummariesofTransactions().getBorrowerTransaction().getDueFromBorrowerAtClosing()
			.getIntegratedDisclosureSectionSummaryDetailModel().getIntegratedDisclosureSectionTotalAmount()) && 
				checkNotNull(json.getSummariesofTransactions().getBorrowerTransaction().getDueFromBorrowerAtClosing()
						.getIntegratedDisclosureSectionSummaryDetailModel().getIntegratedDisclosureSectionType()))
				integratedDisclosureSectionSummaryModels.add(json.getSummariesofTransactions().getBorrowerTransaction().getDueFromBorrowerAtClosing());	
		
		if(checkNotNull(json.getSummariesofTransactions().getSellerTransaction().getFromSellerAtClosing()
				.getIntegratedDisclosureSectionSummaryDetailModel().getIntegratedDisclosureSectionTotalAmount()) && 
				checkNotNull(json.getSummariesofTransactions().getSellerTransaction().getFromSellerAtClosing()
						.getIntegratedDisclosureSectionSummaryDetailModel().getIntegratedDisclosureSectionType()))
					integratedDisclosureSectionSummaryModels.add(json.getSummariesofTransactions().getSellerTransaction().getFromSellerAtClosing());

		if(checkNotNull(json.getSummariesofTransactions().getSellerTransaction().getToSellerAtClosing()
			.getIntegratedDisclosureSectionSummaryDetailModel().getIntegratedDisclosureSectionTotalAmount()) &&
				checkNotNull(json.getSummariesofTransactions().getSellerTransaction().getToSellerAtClosing()
						.getIntegratedDisclosureSectionSummaryDetailModel().getIntegratedDisclosureSectionType())) 
				integratedDisclosureSectionSummaryModels.add(json.getSummariesofTransactions().getSellerTransaction().getToSellerAtClosing());	
		
		if(null != json.getPayoffsAndPayments() && checkNotNull(json.getPayoffsAndPayments().getIntegratedDisclosureSectionSummary().getIntegratedDisclosureSectionSummaryDetailModel().getIntegratedDisclosureSectionTotalAmount()))
			integratedDisclosureSectionSummaryModels.add(json.getPayoffsAndPayments().getIntegratedDisclosureSectionSummary());
		
		return integratedDisclosureSectionSummaryModels;
	}
	
	/**
	 * creates IntegratedDisclosureSectionSummaryModel container to insert into XML
	 * @param secAmount section Total Amount
	 * @param sectionType section Type
	 * @param subAmount subSection TotalAmount
	 * @param subSectionType subSection Type
	 * @param hasPayments whether payments container present or not
	 * @param paymentsModel payments 
	 * @return IntegratedDisclosureSectionSummaryModel
	 */
	
	private static IntegratedDisclosureSectionSummaryModel createIntegratedDisclosureSectionSummaryModel(String secAmount, String sectionType, String subAmount, String subSectionType, boolean hasPayments, PaymentsModel paymentsModel)
	{
		IntegratedDisclosureSectionSummaryModel integratedDisclosureSectionSummaryModel = new IntegratedDisclosureSectionSummaryModel();
		IntegratedDisclosureSectionSummaryDetailModel integratedDisclosureSectionSummaryDetail = new IntegratedDisclosureSectionSummaryDetailModel();
		List<IntegratedDisclosureSubsectionPaymentModel> integratedDisclosureSubsectionPaymentModels = new LinkedList<>();
		
		integratedDisclosureSectionSummaryDetail.setIntegratedDisclosureSectionTotalAmount(secAmount);
		integratedDisclosureSectionSummaryDetail.setIntegratedDisclosureSectionType(sectionType);
		integratedDisclosureSectionSummaryDetail.setIntegratedDisclosureSubsectionTotalAmount(subAmount);
		integratedDisclosureSectionSummaryDetail.setIntegratedDisclosureSubsectionType(subSectionType);
		
		if(hasPayments)
		{
			List<MismoPaymentsModel> mismoPaymentsModels = toMismoFeePayments(paymentsModel, "PREPAID");
			for(MismoPaymentsModel payment : mismoPaymentsModels)
			{
				IntegratedDisclosureSubsectionPaymentModel integratedDisclosureSubsectionPayment = new IntegratedDisclosureSubsectionPaymentModel();
					integratedDisclosureSubsectionPayment.setIntegratedDisclosureSubsectionPaidByType(payment.getPaidByType());
					integratedDisclosureSubsectionPayment.setIntegratedDisclosureSubsectionPaymentAmount(payment.getAmount());
					integratedDisclosureSubsectionPayment.setIntegratedDisclosureSubsectionPaymentTimingType(payment.getClosingIndicator());
				
			   integratedDisclosureSubsectionPaymentModels.add(integratedDisclosureSubsectionPayment);
			}
			integratedDisclosureSectionSummaryModel.setIntegratedDisclosureSubsectionPayments(integratedDisclosureSubsectionPaymentModels);
		}
		integratedDisclosureSectionSummaryModel.setIntegratedDisclosureSectionSummaryDetailModel(integratedDisclosureSectionSummaryDetail);
		
		return integratedDisclosureSectionSummaryModel;
	}
	
	/**
	 * converts Boolean to string
	 * @param status
	 * @return
	 */
	public static String booleanToString(Boolean status)
	{
		if(status)
			return "true";
		return "false";
	}
	
	/**
	 * converts string to Boolean
	 * @param status
	 * @return
	 */
	public static boolean stringToBoolean(String status)
	{
		if(status.equalsIgnoreCase("true"))
			return true;
		return false;
	}
	
	public int convertYearsToMonthsFormat(String yearsdata){
		if(null != yearsdata || "".equals(yearsdata.trim()))
		{
			String[] years = yearsdata.split(" ");
			int month = Math.round(Integer.parseInt(years[0])*12);
	        return month;
		}
		
			return 0;        
	}
	
	/**
	 * get the current date and time with UTC time zone formatted as yyyy-MM-dd'T'HH:mm:ss.SSS'Z
	 * @return date as String 
	 */
	public static String getUTCDate()
	{
		Date date = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);  
		
	}
	
	/**
	 * checks whether to insert payments section to MISMO XML
	 * @param payment
	 * @return boolean
	 */
	public static boolean isInsertFee(PaymentsModel payment)
	{
		if(checkNotNull(payment.getBpAtClosing()))
			return true;
		else if(checkNotNull(payment.getBpB4Closing()))
			return true;
		else if(checkNotNull(payment.getSpAtClosing()))
			return true;
		else if(checkNotNull(payment.getSpB4Closing()))
			return true;
		else if(checkNotNull(payment.getPaidByOthers()))
			return true;
		return false;
	}
	
	/**
	 * checks for null and empty
	 * @param amount
	 * @return boolean
	 */
	public static boolean checkNotNull(String amount)
	{
		if(null != amount && !amount.isEmpty() && !"0".equalsIgnoreCase(amount) && !"0.00".equalsIgnoreCase(amount))
			return true;
		return false;
	}
}
