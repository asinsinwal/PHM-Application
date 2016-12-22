package com.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * OB_ID P_ID R_ID OB_VALUE RECORD_TIME OB_TIME
 * 
 * @author Sumit
 *
 */
public class ObservationDTO {

	private int observationId;
	private String personId;
	private int recommendationId;
	private String observationValue;
	private Timestamp recordTime;
	private Timestamp observationTime;

	/**
	 * Parameterized constructor
	 * 
	 * @param observationId
	 *            the observation ID
	 * @param observationType
	 *            the observation type
	 * @param observationValue
	 *            the observation Value
	 */
	public ObservationDTO(int observationId, String personId, int recommendationId, String observationValue,
			Timestamp recordTime, Timestamp observationTime) {
		this.observationId = observationId;
		this.personId = personId;
		this.recommendationId = recommendationId;
		this.observationValue = observationValue;
		this.recordTime = recordTime;
		this.observationTime = observationTime;
	}

	/**
	 * @return the observationId
	 */
	public int getObservationId() {
		return observationId;
	}

	/**
	 * @param observationId
	 *            the observationId to set
	 */
	public void setObservationId(int observationId) {
		this.observationId = observationId;
	}

	// /**
	// * @return the observationType
	// */
	// public String getObservationType() {
	// return observationType;
	// }
	//
	// /**
	// * @param observationType
	// * the observationType to set
	// */
	// public void setObservationType(String observationType) {
	// this.observationType = observationType;
	// }

	/**
	 * @return the observationValue
	 */
	public String getObservationValue() {
		return observationValue;
	}

	/**
	 * @param observationValue
	 *            the observationValue to set
	 */
	public void setObservationValue(String observationValue) {
		this.observationValue = observationValue;
	}

	/**
	 * @return the recommendationId
	 */
	public int getRecommendationId() {
		return recommendationId;
	}

	/**
	 * @param recommendationId
	 *            the recommendationId to set
	 */
	public void setRecommendationId(int recommendationId) {
		this.recommendationId = recommendationId;
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

	/**
	 * @return the observationTime
	 */
	public Timestamp getObservationTime() {
		return observationTime;
	}

	/**
	 * @param observationTime
	 *            the observationTime to set
	 */
	public void setObservationTime(Timestamp observationTime) {
		this.observationTime = observationTime;
	}

}
