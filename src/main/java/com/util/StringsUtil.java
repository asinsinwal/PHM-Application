package com.util;

/**
 * @author Sumit
 *
 */
public class StringsUtil {

	/** Static Queries **/
	/**
	 * query for alert table
	 */
	public static final String ALERT_QUERY = "SELECT A_ID as alertId, P_ID as personId, DESCRIPTION as description, IS_MANDATORY as isMandatory, IS_VIEWED as isViewed FROM ALERT";
	/**
	 * query for disease table
	 */
	public static final String DISEASE_QUERY = "SELECT D_ID as diseaseId, DNAME as diseaseName FROM DISEASE";
	/**
	 * query for observation table
	 */
	public static final String OBSERVATION_QUERY = "SELECT OB_ID as observationId, P_ID as personId, R_ID as recommendationId, OB_VALUE as observationValue, RECORD_TIME as recordTime, OB_TIME as observationTime FROM OBSERVATION";
	/**
	 * query for person table
	 */
	public static final String PERSON_QUERY = "SELECT P_ID as PersonId, PNAME as personName, USERNAME as username, PASSWORD as password, ADDRESS as address, DOB as dob, GENDER as gender, CONTACT as contactInfo FROM PERSON";
	/**
	 * query for recommendation table
	 */
	public static final String RECOMMENDATION_QUERY = "SELECT R_ID as recommendationId, DESCRIPTION as description, FREQUENCY as frequency, LOWER_BOUND as lowerBound, UPPER_BOUND as upperBound, METRIC as metric, STRING_VALUE as value FROM RECOMMENDATION";
	/**
	 * query for record disease table
	 */
	public static final String RECRD_DISEASE_QUERY = "SELECT P_ID as PersonId, D_ID as diseaseId, RECORD_TIME as recordTime FROM RECORD_DISEASE";
	/**
	 * query for sick person table
	 */
	public static final String SICK_PERSON_QUERY = "SELECT P_ID as PersonId, HS1_ID as HealthSupporter1Id, HS2_ID as HealthSupporter2Id, HS1_AUTH_DATE as hs1AuthDate, HS2_AUTH_DATE as hs2AuthDate FROM SICK_PERSON";
	
	public static final String INSERT_SICK_PERSON = "INSERT INTO SICK_PERSON (P_ID, HS1_ID, HS2_ID, HS1_AUTH_DATE, HS2_AUTH_DATE) VALUES (?,?,?,?,?)";
	/**
	 * 
	 * query for specific recommendation table
	 */
	public static final String SPE_RECOMMENDATION_QUERY = "SELECT P_ID as PersonId, R_ID as recommendationId FROM SPECIFIC_RECOMMENDATION";
	/**
	 * query for standard recommendation table
	 */
	
	public static final String GET_RECOMMENDATION_BY_DID = "SELECT R.R_ID as recommendationId, R.DESCRIPTION as description, R.FREQUENCY as frequency, R.LOWER_BOUND as lowerBound, R.UPPER_BOUND as upperBound, R.METRIC as metric, R.STRING_VALUE as value FROM RECOMMENDATION R, STANDARD_RECOMMENDATION S WHERE R.R_ID = S.R_ID AND S.D_ID = ?";
	
	public static final String GET_RECOMMENDATION_BY_PID = "SELECT R.R_ID as recommendationId, R.DESCRIPTION as description, R.FREQUENCY as frequency, R.LOWER_BOUND as lowerBound, R.UPPER_BOUND as upperBound, R.METRIC as metric, R.STRING_VALUE as value FROM RECOMMENDATION R, SPECIFIC_RECOMMENDATION P WHERE R.R_ID = P.R_ID AND P.P_ID = ?";
	
	public static final String STD_RECOMMENDATION_QUERY = "SELECT D_ID as diseaseId, R_ID as recommendationId FROM STANDARD_RECOMMENDATION";
	
	public static final String GET_SUG_RECOMMENDATIONS = RECOMMENDATION_QUERY.concat(" WHERE R_ID NOT IN (SELECT R_ID FROM SPECIFIC_RECOMMENDATION WHERE P_ID = ?) AND R_ID NOT IN (SELECT R_ID FROM STANDARD_RECOMMENDATION)");
	/**
	 * query for well person table
	 */
	public static final String WELL_PERSON_QUERY = "SELECT P_ID as PersonId, HS1_ID as HealthSupporter1Id, HS2_ID as HealthSupporter2Id, HS1_AUTH_DATE as hs1AuthDate, HS2_AUTH_DATE as hs2AuthDate FROM WELL_PERSON";

	public static final String WELL_PERSON_QUERY_BY_ID = WELL_PERSON_QUERY.concat(" WHERE P_ID = ?");
	
	/** Prepared Statements **/
	public static final String LOGIN_QUERY = PERSON_QUERY.concat(" WHERE USERNAME = ? AND PASSWORD = ?");

	/** Messages **/

	public static final String LOGIN_MESSAGE = "Welcome to Patient Health Management Application!";

	public static final String INSERT_PERSON = "INSERT INTO Person (P_ID, PNAME, USERNAME, PASSWORD, ADDRESS, DOB, GENDER, CONTACT) VALUES (concat('P',phmseq.nextval), ?, ?, ?, ?, ?, ?, ?)";

	public static final String INSERT_OBSERVATION = "INSERT INTO OBSERVATION (OB_ID, P_ID, R_ID, OB_VALUE, RECORD_TIME, OB_TIME) VALUES (obvseq.nextval,?,?,?,SYSTIMESTAMP,?)";
	
	public static final String INSERT_RECOMMENDATION = "INSERT INTO RECOMMENDATION (R_ID, FREQUENCY, DESCRIPTION, METRIC, LOWER_BOUND, UPPER_BOUND, STRING_VALUE) VALUES (recseq.nextval,?,?,?,?,?,?)";
	
	public static final String INSERT_SPECIFIC_RECOMMENDATION = "INSERT INTO SPECIFIC_RECOMMENDATION (P_ID,R_ID) VALUES (?,?)";
	
	public static final String VIEW_EXISTING_HEALTH_SUPPORTERS = "SELECT HS1_ID AND HS2_ID FROM SICK_PERSON WHERE P_ID = ?"
			+ "UNION" + "SELECT HS1_ID AND HS2_ID FROM WELL_PERSON WHERE P_ID = ?";

	public static final String VIEW_DISEASES = RECRD_DISEASE_QUERY.concat(" WHERE P_ID = ? ");

	public static final String VIEW_ALERTS = ALERT_QUERY.concat(" WHERE P_ID = ? AND IS_VIEWED = 'F'");

	public static final String MARK_VIEWED_ALERTS = "UPDATE ALERT SET IS_VIEWED = 'T' WHERE P_ID = ?";

	public static final String VIEW_OBSERVATIONS = OBSERVATION_QUERY.concat(" WHERE P_ID = ? ORDER BY RECORD_TIME");

	public static final String UPDATE_SICK_PERSON_FIRST_HEALTH_SUPPORTER = "UPDATE SICK_PERSON SET HS1_ID = ?,HS1_AUTH_DATE = ? WHERE P_ID = ?";

	public static final String UPDATE_SICK_PERSON_SECOND_HEALTH_SUPPORTER = "UPDATE SICK_PERSON SET HS2_ID = ?,HS2_AUTH_DATE = ? WHERE P_ID = ?";

	public static final String UPDATE_WELL_PERSON_FIRST_HEALTH_SUPPORTER = "UPDATE WELL_PERSON SET HS1_ID = ?,HS1_AUTH_DATE = ? WHERE P_ID = ?";

	public static final String UPDATE_WELL_PERSON_SECOND_HEALTH_SUPPORTER = "UPDATE WELL_PERSON SET HS2_ID = ?,HS2_AUTH_DATE = ? WHERE P_ID = ?";

	public static final String DELETE_SICK_PERSON_FIRST_HEALTH_SUPPORTER = "UPDATE SICK_PERSON SET HS1_ID = NULL, HS1_AUTH_DATE=NULL WHERE P_ID = ?";

	public static final String DELETE_SICK_PERSON_SECOND_HEALTH_SUPPORTER = "UPDATE SICK_PERSON SET HS2_ID = NULL, HS2_AUTH_DATE=NULL WHERE P_ID = ?";

	public static final String DELETE_WELL_PERSON_FIRST_HEALTH_SUPPORTER = "UPDATE WELL_PERSON SET HS1_ID = NULL, HS1_AUTH_DATE=NULL WHERE P_ID = ?";

	public static final String DELETE_WELL_PERSON_SECOND_HEALTH_SUPPORTER = "UPDATE WELL_PERSON SET HS2_ID = NULL, HS2_AUTH_DATE=NULL WHERE P_ID = ?";

	public static final String SHOW_DISEASES = "SELECT * FROM DISEASE";

	public static final String RECORD_DISEASE = "INSERT INTO RECORD_DISEASE (P_ID, D_ID, RECORD_TIME) VALUES (?, ?, SYSTIMESTAMP)";

	public static final String ADD_OBSERVATION = "INSERT INTO OBSERVATION VALUES (PHMSQL.NEXTVAL, P_ID?, R_ID?, OB_VALUE?, SYSTIMESTAMP)";

	public static final String GET_PATIENTS_OF_HEALTH_SUPPORTER = PERSON_QUERY.concat(
			" WHERE P_ID IN ( SELECT P_ID FROM WELL_PERSON WHERE HS1_ID=? OR HS2_ID=? UNION SELECT P_ID FROM SICK_PERSON WHERE HS1_ID=? OR HS2_ID=? )");

	public static final String HEALTH_SUPPORTER_VIEWS_ALERTS_OF_PATIENT = "SELECT DESCRIPTION AS Description FROM ALERT WHERE P_ID = ?";

	public static final String IS_PERSON_SICK_PATIENT = "SELECT COUNT(*) AS VALUE FROM SICK_PERSON S WHERE S.P_ID = ?";
	
	public static final String UPDATE_PERSON_PROFILE = "UPDATE PERSON SET PNAME = ?, PASSWORD = ?, ADDRESS = ?, GENDER = ?, CONTACT = ? WHERE P_ID = ?";
	
	public static final String IS_PERSON_HEALTH_SUPPORTER = "SELECT COUNT(*) AS VALUE FROM PERSON P WHERE P_ID IN ( SELECT P_ID FROM WELL_PERSON WHERE HS1_ID=? OR HS2_ID=? UNION SELECT P_ID FROM SICK_PERSON WHERE HS1_ID=? OR HS2_ID=? )";
	
	public static final String HEALTH_SUPPORTER_VIEWS_SPECIFIC_RECOMMENDATION = "SELECT R.FREQUENCY AS Frequency, R.DESCRIPTION AS Description, R.METRIC AS Metric, R.LOWER_BOUND AS Lower Bound, R.UPPER_BOUND AS Upper Bound, R.STRING_VALUE AS String_Value FROM RECOMMENDATION R, SPECIFIC_RECOMMENDATION SR WHERE SR.R_ID=R.R_ID";
 	
	public static final String RECOMMENDATION_DESCRIPTION_FROM_OBSERVATION_ID = "SELECT R.DESCRIPTION AS Description FROM RECOMMENDATION R, OBSERVATION O WHERE O.R_ID = R.R_ID AND O.OB_ID=?";

	public static final String GET_DISEASE_NAME = "SELECT DNAME as diseaseName FROM DISEASE WHERE D_ID = ?";
	
	public static final String GET_PERSON_NAME = "SELECT PNAME as personName FROM PERSON WHERE P_ID = ?";
	
	public static final String GET_CONTACT_INFO = "SELECT CONTACT as contactInfo FROM PERSON WHERE P_ID = ?";
	
	public static final String GET_PERSON_ID = "SELECT P_ID as personId FROM PERSON WHERE USERNAME = ?";
	
	public static final String GET_PERSON_BY_ID = PERSON_QUERY.concat(" WHERE P_ID = ?");
	
	public static final String GET_RECORD_DISEASE_ID = RECRD_DISEASE_QUERY.concat(" WHERE P_ID = ?");
	
	public static final String DELETE_RECORD_DISEASE = "DELETE FROM RECORD_DISEASE WHERE P_ID = ? AND D_ID = ?";
	
	public static final String PATIENT_STANDARD_RECOMENDATIONS = STD_RECOMMENDATION_QUERY.concat(" WHERE D_ID = ?");
	
	public static final String PATIENT_SPECIFIC_RECOMMENDATIONS = SPE_RECOMMENDATION_QUERY.concat(" WHERE P_ID = ?");
	
	public static final String GET_RECOMMENDATIONS_BY_RID = RECOMMENDATION_QUERY.concat(" WHERE R_ID = ?");
	
	public static final String HP_SICK_PERSON_QUERY = SICK_PERSON_QUERY.concat(" WHERE P_ID = ?");
	
	public static final String HP_WELL_PERSON_QUERY = WELL_PERSON_QUERY.concat(" WHERE P_ID = ?");

	public static final String CLEAR_ALERT_BY_HS = "update ALERT set IS_VIEWED = 'T' where P_ID = ? and A_ID = ?";
	
	public static final String CLEAR_ALERT_BY_PATIENT = "update ALERT set IS_VIEWED = 'T' where P_ID = ? and A_ID = ? and IS_MANDATORY != 'T'";
	
	public static final String SPECIFIC_RECOMMENDATIONS = SPE_RECOMMENDATION_QUERY.concat(" WHERE R_ID = ?");
	
	public static final String UPDATE_SPECIFIC_RECOMMENDATION = "UPDATE SPECIFIC_RECOMMENDATION SET R_ID = ? WHERE R_ID = ? AND P_ID = ?";
}
