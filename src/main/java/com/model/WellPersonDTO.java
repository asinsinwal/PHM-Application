package com.model;

import java.sql.Date;

/**
 * P_ID HS1_ID HS2_ID HS1_AUTH_DATE HS2_AUTH_DATE
 * 
 * @author Sumit
 *
 */
public class WellPersonDTO extends PersonDTO {

	private String personId;
	private String healthSupporter1Id;
	private String healthSupporter2Id;
	private Date hs1AuthDate; // modified as per changes from dev_Nikhil branch
	private Date hs2AuthDate; // modified as per changes from dev_Nikhil branch

	public WellPersonDTO() {
		super();
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param personId
	 *            the person ID
	 * @param healthSupporter1Id
	 *            the health supporter 1 ID
	 * @param healthSupporter2Id
	 *            the health supporter 2 ID
	 */
	public WellPersonDTO(String personId, String healthSupporter1Id, String healthSupporter2Id, Date hs1AuthDate,
			Date hs2AuthDate) {
		this.personId = personId;
		this.healthSupporter1Id = healthSupporter1Id;
		this.healthSupporter2Id = healthSupporter2Id;
		this.hs1AuthDate = hs1AuthDate;
		this.hs2AuthDate = hs2AuthDate;
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
	 * @return the healthSupporter1Id
	 */
	public String getHealthSupporter1Id() {
		return healthSupporter1Id;
	}

	/**
	 * @param healthSupporter1Id
	 *            the healthSupporter1Id to set
	 */
	public void setHealthSupporter1Id(String healthSupporter1Id) {
		this.healthSupporter1Id = healthSupporter1Id;
	}

	/**
	 * @return the healthSupporter2Id
	 */
	public String getHealthSupporter2Id() {
		return healthSupporter2Id;
	}

	/**
	 * @param healthSupporter2Id
	 *            the healthSupporter2Id to set
	 */
	public void setHealthSupporter2Id(String healthSupporter2Id) {
		this.healthSupporter2Id = healthSupporter2Id;
	}

	/**
	 * @return the hs1AuthDate
	 */
	public Date getHs1AuthDate() {
		return hs1AuthDate;
	}

	/**
	 * @param hs1AuthDate
	 *            the hs1AuthDate to set
	 */
	public void setHs1AuthDate(Date hs1AuthDate) {
		this.hs1AuthDate = hs1AuthDate;
	}

	/**
	 * @return the hs2AuthDate
	 */
	public Date getHs2AuthDate() {
		return hs2AuthDate;
	}

	/**
	 * @param hs2AuthDate
	 *            the hs2AuthDate to set
	 */
	public void setHs2AuthDate(Date hs2AuthDate) {
		this.hs2AuthDate = hs2AuthDate;
	}

}