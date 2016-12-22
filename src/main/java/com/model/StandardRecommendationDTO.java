package com.model;

/**
 * D_ID R_ID
 * 
 * @author Sumit
 *
 */
public class StandardRecommendationDTO {

	private int diseaseId;
	private int recommendationId;

	/**
	 * Parameterized constructor
	 * 
	 * @param diseaseId
	 *            the disease ID
	 * @param recommendationId
	 *            the recommendation ID
	 * @param recordTime
	 *            the record time
	 */
	public StandardRecommendationDTO(int diseaseId, int recommendationId) {
		this.diseaseId = diseaseId;
		this.recommendationId = recommendationId;
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

	// /**
	// * @return the recordTime
	// */
	// public Timestamp getRecordTime() {
	// return recordTime;
	// }
	//
	// /**
	// * @param recordTime
	// * the recordTime to set
	// */
	// public void setRecordTime(Timestamp recordTime) {
	// this.recordTime = recordTime;
	// }
}
