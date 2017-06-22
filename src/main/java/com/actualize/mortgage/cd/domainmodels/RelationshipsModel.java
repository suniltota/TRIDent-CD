/**
 * 
 */
package com.actualize.mortgage.cd.domainmodels;

import java.io.Serializable;

/**
 * @author sboragala
 *
 */
public class RelationshipsModel implements Serializable {
	

	private static final long serialVersionUID = 3848060292963361762L;

	private String sequenceNumber;
	private String xlinkFrom;
	private String xlinkTo;
	private String xlinkArcrole;
	
	/**
	 * @return the sequenceNumber
	 */
	public String getSequenceNumber() {
		return sequenceNumber;
	}
	/**
	 * @param sequenceNumber the sequenceNumber to set
	 */
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	/**
	 * @return the xlinkFrom
	 */
	public String getXlinkFrom() {
		return xlinkFrom;
	}
	/**
	 * @param xlinkFrom the xlinkFrom to set
	 */
	public void setXlinkFrom(String xlinkFrom) {
		this.xlinkFrom = xlinkFrom;
	}
	/**
	 * @return the xlinkTo
	 */
	public String getXlinkTo() {
		return xlinkTo;
	}
	/**
	 * @param xlinkTo the xlinkTo to set
	 */
	public void setXlinkTo(String xlinkTo) {
		this.xlinkTo = xlinkTo;
	}
	/**
	 * @return the xlinkArcrole
	 */
	public String getXlinkArcrole() {
		return xlinkArcrole;
	}
	/**
	 * @param xlinkArcrole the xlinkArcrole to set
	 */
	public void setXlinkArcrole(String xlinkArcrole) {
		this.xlinkArcrole = xlinkArcrole;
	}
	
	
	

}
