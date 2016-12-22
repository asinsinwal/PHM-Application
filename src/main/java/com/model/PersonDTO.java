package com.model;

/**
 * TABLE PERSON ( P_ID VARCHAR2(5) PRIMARY KEY, PNAME VARCHAR2(100), USERNAME
 * VARCHAR2(20) UNIQUE, PASSWORD VARCHAR2(20), ADDRESS VARCHAR2(250), DOB DATE,
 * GENDER CHAR(1) );
 * 
 * @author Sumit
 *
 */
public class PersonDTO {

	private String personId;
	private String personName;
	private String username;
	private String password;
	private String address;
	private String dob;
	private String gender;
	private String contactInfo;

	/**
	 * default constructor
	 */
	public PersonDTO() {

	}

	/**
	 * Parameterized constructor
	 * 
	 * @param personId
	 *            the person ID
	 * @param personName
	 *            the person name
	 * @param username
	 *            the user name
	 * @param password
	 *            the password
	 * @param address
	 *            the address
	 * @param dob
	 *            the date of birth
	 * @param gender
	 *            the gender
	 */
	public PersonDTO(String personId, String personName, String username, String password, String address, String dob,
			String gender, String contactInfo) {
		this.personId = personId;
		this.personName = personName;
		this.username = username;
		this.password = password;
		this.address = address;
		this.dob = dob;
		this.gender = gender;
		this.contactInfo = contactInfo;
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
	 * @return the personName
	 */
	public String getPersonName() {
		return personName;
	}

	/**
	 * @param personName
	 *            the personName to set
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the contactInfo
	 */
	public String getContactInfo() {
		return contactInfo;
	}

	/**
	 * @param contactInfo
	 *            the contactInfo to set
	 */
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
}
