package com.actualize.mortgage.cd.datamodels;

import org.w3c.dom.Element;

import com.actualize.mortgage.cd.domainmodels.MISMODataAccessObject;
/**
 * represents Loan in MISMO XML
 * @author sboragala
 *
 */
public class Loan extends MISMODataAccessObject {
	private static final long serialVersionUID = 1359093742138850408L;
	public final Adjustment adjustment;
	public final Amortization amortization;
	public final Buydown buydown;
	public final ClosingInformation closingInformation;
	public final Construction construction;
	public final DocumentSpecificDataSets documentSpecificDataSets;
	public final Escrow escrow;
	public final FeeInformation feeInformation;
	public final Foreclosures foreclosures;
	public final HighCostMortgages highCostMortgages;
	public final InterestOnly interestOnly;
	public final LateChargeRule lateChargeRule;
	public final LoanDetail loanDetail;
	public final LoanIdentifiers loanIdentifiers;
	public final LoanProduct loanProduct;
	public final Locks Locks;
	public final MaturityRule maturityRule;
	public final MIDataDetail miDataDetail;
	public final NegativeAmortization negativeAmortization;
	public final Payment payment;
	public final PrepaymentPenalty prepaymentPenalty;
	public final QualifiedMortgage qualifiedMortgage;
	public final Refinance refinance;
	public final TermsOfLoan termsOfLoan;
	public final Underwriting underwriting;

	public Loan(Element element) {
		super(element);
		adjustment = new Adjustment(NS, element);
		amortization = new Amortization(element);
		buydown = new Buydown(NS, element);
		closingInformation = new ClosingInformation(NS, element);
		construction = new Construction(element);
		documentSpecificDataSets = new DocumentSpecificDataSets(NS, element);
		escrow = new Escrow(NS, element);
		feeInformation = new FeeInformation(NS, element);
		foreclosures = new Foreclosures(NS, element);
		highCostMortgages = new HighCostMortgages(NS, element);
		interestOnly = new InterestOnly(element);
		lateChargeRule = new LateChargeRule(element);
		loanDetail = new LoanDetail(element);
		loanIdentifiers = new LoanIdentifiers(NS, element);
		loanProduct = new LoanProduct(NS, element);
		Locks = new Locks(element);
		maturityRule = new MaturityRule(element);
		miDataDetail = new MIDataDetail(element);
		negativeAmortization = new NegativeAmortization(NS, element);
		payment = new Payment(NS, element);
		prepaymentPenalty = new PrepaymentPenalty(NS, element);
		qualifiedMortgage = new QualifiedMortgage(NS, element);
		refinance = new Refinance(NS, element);
		termsOfLoan = new TermsOfLoan(element);
		underwriting = new Underwriting(NS, element);
	}
}
