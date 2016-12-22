package com.model;

/**
 * P_ID R_ID
 * 
 * @author Sumit
 *
 */
public class SpecificRecommendationDTO {

	private String personId;
	private int recommendationId;

	/**
	 * Parameterized constructor
	 * 
	 * @param recommendationId
	 *            the recommendation ID
	 * @param recordTime
	 *            the record time
	 */
	public SpecificRecommendationDTO(int recommendationId, String personId) {
		this.recommendationId = recommendationId;
		this.personId = personId;
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

	// /**
	// * @return the diseaseId
	// */
	// public int getDiseaseId() {
	// return diseaseId;
	// }
	//
	// /**
	// * @param diseaseId
	// * the diseaseId to set
	// */
	// public void setDiseaseId(int diseaseId) {
	// this.diseaseId = diseaseId;
	// }

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

}