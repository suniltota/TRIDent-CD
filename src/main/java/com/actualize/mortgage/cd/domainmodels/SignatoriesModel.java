/**
 * 
 */
package com.actualize.mortgage.cd.domainmodels;

import java.io.Serializable;

/**
 * defines the Signatories while generating XML  
 * @author sboragala
 *
 */
public class SignatoriesModel implements Serializable {
	
	private static final long serialVersionUID = 5633583428478208479L;
	
	private String sequenceNumber;
	private String xlabel;
	private String actualSignatureType;
	private String executionDate;
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
	 * @return the xlabel
	 */
	public String getXlabel() {
		return xlabel;
	}
	/**
	 * @param xlabel the xlabel to set
	 */
	public void setXlabel(String xlabel) {
		this.xlabel = xlabel;
	}
	/**
	 * @return the actualSignatureType
	 */
	public String getActualSignatureType() {
		return actualSignatureType;
	}
	/**
	 * @param actualSignatureType the actualSignatureType to set
	 */
	public void setActualSignatureType(String actualSignatureType) {
		this.actualSignatureType = actualSignatureType;
	}
	/**
	 * @return the executionDate
	 */
	public String getExecutionDate() {
		return executionDate;
	}
	/**
	 * @param executionDate the executionDate to set
	 */
	public void setExecutionDate(String executionDate) {
		this.executionDate = executionDate;
	}
	
	

}
