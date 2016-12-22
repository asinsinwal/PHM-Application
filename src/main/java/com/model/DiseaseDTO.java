package com.model;

/**
 * D_ID DNAME
 * 
 * @author Sumit
 *
 */
public class DiseaseDTO {

	private int diseaseId;
	private String diseaseName;

	/**
	 * Parameterized constructor
	 * 
	 * @param diseaseId
	 *            the disease ID
	 * @param diseaseName
	 *            the disease Name
	 */
	public DiseaseDTO(int diseaseId, String diseaseName) {
		this.diseaseId = diseaseId;
		this.diseaseName = diseaseName;
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
	 * @return the diseaseName
	 */
	public String getDiseaseName() {
		return diseaseName;
	}

	/**
	 * @param diseaseName
	 *            the diseaseName to set
	 */
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
}
