package com.model;

import java.sql.Timestamp;

/**
 * P_ID D_ID RECORD_TIME
 * 
 * @author Sumit
 *
 */
public class RecordDiseaseDTO {

	private String personId;
	private int diseaseId;
	private Timestamp recordTime;

	/**
	 * Parameterized constructor
	 * 
	 * @param personId
	 *            the person ID
	 * @param diseaseId
	 *            the disease ID
	 * @param recordTime
	 *            the record Time
	 */
	public RecordDiseaseDTO(String personId, int diseaseId, Timestamp recordTime) {
		this.personId = personId;
		this.diseaseId = diseaseId;
		this.recordTime = recordTime;
	}

	/**
	 * @return the personId
	 */
	public String getPersonId() {
		return personId;
	}

	/**
	 * @param personId
	 *            the personId to set
	 */
	public void setPersonId(String personId) {
		this.personId = personId;
	}

	/**
	 * @return the diseaseId
	 */
	public int getDiseaseId() {
		return diseaseId;
	}

	/**
	 * @param diseaseId
	 *            the diseaseId to set
	 */
	public void setDiseaseId(int diseaseId) {
		this.diseaseId = diseaseId;
	}

	/**
	 * @return the recordTime
	 */
	public Timestamp getRecordTime() {
		return recordTime;
	}

	/**
	 * @param recordTime
	 *            the recordTime to set
	 */
	public void setRecordTime(Timestamp recordTime) {
		this.recordTime = recordTime;
	}

}
