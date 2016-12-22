package com.model;

/**
 * A_ID P_ID DESCRIPTION IS_MANDATORY IS_VIEWED
 * 
 * @author Sumit
 *
 */
public class AlertDTO {

	private int alertId;
	private String personId;
	private String description;
	private String isMandatory;
	private String isViewed;

	/**
	 * Parameterized constructor
	 * 
	 * @param alertId
	 *            the alert ID
	 * @param personId
	 *            the person ID
	 * @param description
	 *            the description
	 */
	public AlertDTO(int alertId, String personId, String description, String isMandatory, String isViewed) {
		this.alertId = alertId;
		this.personId = personId;
		this.description = description;
		this.isMandatory = isMandatory;
		this.isViewed = isViewed;
	}

	/**
	 * @return the alertId
	 */
	public int getAlertId() {
		return alertId;
	}

	/**
	 * @param alertId
	 *            the alertId to set
	 */
	public void setAlertId(int alertId) {
		this.alertId = alertId;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the isMandatory
	 */
	public String getIsMandatory() {
		return isMandatory;
	}

	/**
	 * @param isMandatory
	 *            the isMandatory to set
	 */
	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
	}

	/**
	 * @return the isViewed
	 */
	public String getIsViewed() {
		return isViewed;
	}

	/**
	 * @param isViewed
	 *            the isViewed to set
	 */
	public void setIsViewed(String isViewed) {
		this.isViewed = isViewed;
	}
}
