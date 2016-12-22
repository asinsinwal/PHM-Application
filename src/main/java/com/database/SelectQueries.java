package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exception.PhmException;
import com.model.AlertDTO;
import com.model.DiseaseDTO;
import com.model.ObservationDTO;
import com.model.PersonDTO;
import com.model.RecommendationDTO;
import com.model.RecordDiseaseDTO;
import com.model.SickPersonDTO;
import com.model.SpecificRecommendationDTO;
import com.model.StandardRecommendationDTO;
import com.model.WellPersonDTO;
import com.util.StringsUtil;

/**
 * @author Sumit
 *
 */
public class SelectQueries {

	/**
	 * Method to fetch the record of Login User from database.
	 * 
	 * @param username
	 *            the user name
	 * @param password
	 *            the password
	 * @param connection
	 *            the database connection
	 * @return the object of Person DTO if login successful or null if user does
	 *         not exist.
	 */
	public static PersonDTO getLoginPerson(String username, String password, Connection connection)
			throws PhmException {
		PersonDTO person = null;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.LOGIN_QUERY);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				person = new PersonDTO(resultSet.getString("personId"), resultSet.getString("personName"),
						resultSet.getString("username"), resultSet.getString("password"),
						resultSet.getString("address"), resultSet.getString("dob"), resultSet.getString("gender"),
						resultSet.getString("contactInfo"));
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch Person While Login. " + e.getMessage());
			throw new PhmException("Failed to fetch Person While Login. " + e.getMessage());
		}
		return person;
	}

	/**
	 * Method to fetch all rows for a given personId from ALERT table in
	 * database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @param personId
	 *            the personId
	 * @return List of AlertDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static List<AlertDTO> getPatientAlerts(Connection connection, String personId) throws PhmException {
		List<AlertDTO> alertDTOs = new ArrayList<AlertDTO>();
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.VIEW_ALERTS);
			ps.setString(1, personId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				AlertDTO alertDTO = new AlertDTO(resultSet.getInt("alertId"), resultSet.getString("personId"),
						resultSet.getString("description"), resultSet.getString("isMandatory"),
						resultSet.getString("isViewed"));
				alertDTOs.add(alertDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch patient Alerts." + e.getMessage());
			throw new PhmException("Failed to fetch patient Alerts." + e.getMessage());
		}
		return alertDTOs;
	}

	/**
	 * Method to fetch all rows from ALERT table in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of AlertDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static List<AlertDTO> getAllAlerts(Connection connection) throws PhmException {
		List<AlertDTO> alertDTOs = new ArrayList<AlertDTO>();
		try {
			ResultSet resultSet = connection.createStatement().executeQuery(StringsUtil.ALERT_QUERY);
			while (resultSet.next()) {
				AlertDTO alertDTO = new AlertDTO(resultSet.getInt("alertId"), resultSet.getString("personId"),
						resultSet.getString("description"), resultSet.getString("isMandatory"),
						resultSet.getString("isViewed"));
				alertDTOs.add(alertDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all Alerts." + e.getMessage());
			throw new PhmException("Failed to fetch all Alerts." + e.getMessage());
		}
		return alertDTOs;
	}

	/**
	 * Method to fetch all rows from DISEASE table in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of DiseaseDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static List<DiseaseDTO> getAllDiseases(Connection connection) throws PhmException {
		List<DiseaseDTO> diseaseDTOs = new ArrayList<DiseaseDTO>();
		try {
			ResultSet resultSet = connection.createStatement().executeQuery(StringsUtil.DISEASE_QUERY);
			while (resultSet.next()) {
				DiseaseDTO diseaseDTO = new DiseaseDTO(resultSet.getInt("diseaseId"),
						resultSet.getString("diseaseName"));
				diseaseDTOs.add(diseaseDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all Diseases." + e.getMessage());
			throw new PhmException("Failed to fetch all Diseases." + e.getMessage());
		}
		return diseaseDTOs;
	}

	/**
	 * Method to fetch the disease name from DISEASE table in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of DiseaseDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static String getDiseaseName(Connection connection, int diseaseId) throws PhmException {
		String disease_name = null;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.GET_DISEASE_NAME);
			ps.setInt(1, diseaseId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				disease_name = resultSet.getString("diseaseName");
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch disease name." + e.getMessage());
			throw new PhmException("Failed to fetch disease name." + e.getMessage());
		}
		return disease_name;
	}

	/**
	 * Method to fetch the person name from Person table in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of DiseaseDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static String getPersonName(Connection connection, String personId) throws PhmException {
		String person_name = null;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.GET_PERSON_NAME);
			ps.setString(1, personId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				person_name = resultSet.getString("personName");
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch person name." + e.getMessage());
			throw new PhmException("Failed to fetch person name." + e.getMessage());
		}
		return person_name;
	}
	
	/**
	 * Method to fetch the person name from Person table in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of DiseaseDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static String getContactInfo(Connection connection, String personId) throws PhmException {
		String person_name = null;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.GET_CONTACT_INFO);
			ps.setString(1, personId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				person_name = resultSet.getString("contactInfo");
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch person's contact information." + e.getMessage());
			throw new PhmException("Failed to fetch person's contact information." + e.getMessage());
		}
		return person_name;
	}

	/**
	 * Method to fetch all rows from PERSON table in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of PersonDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static PersonDTO getPatientDetails(Connection connection, String patientId) throws PhmException {
		PersonDTO personDTO = null;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.GET_PERSON_BY_ID);
			ps.setString(1, patientId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				personDTO = new PersonDTO(resultSet.getString("personId"), resultSet.getString("personName"),
						resultSet.getString("username"), resultSet.getString("password"),
						resultSet.getString("address"), resultSet.getString("dob"), resultSet.getString("gender"),
						resultSet.getString("contactInfo"));
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch patient details." + e.getMessage());
			throw new PhmException("Failed to fetch patient details." + e.getMessage());
		}
		return personDTO;
	}

	/*
	 * @param connection the database connection to use
	 * 
	 * @return List of DiseaseDTO objects
	 * 
	 * @throws PhmException if some error occurs
	 */
	public static String getPersonId(Connection connection, String username) throws PhmException {
		String person_id = null;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.GET_PERSON_ID);
			ps.setString(1, username);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				person_id = resultSet.getString("personId");
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch person ID." + e.getMessage());
			throw new PhmException("Failed to fetch person ID." + e.getMessage());
		}
		return person_id;
	}

	/**
	 * Method to fetch the disease id from Record table and delete from Record
	 * Disease in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of DiseaseDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static List<RecordDiseaseDTO> getPatientDiseases(Connection connection, String personId)
			throws PhmException {
		List<RecordDiseaseDTO> record_diseaseDTOs = new ArrayList<RecordDiseaseDTO>();
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.GET_RECORD_DISEASE_ID);
			ps.setString(1, personId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				RecordDiseaseDTO record_diseaseDTO = new RecordDiseaseDTO(resultSet.getString("PersonId"),
						resultSet.getInt("diseaseId"), resultSet.getTimestamp("recordTime"));
				record_diseaseDTOs.add(record_diseaseDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch patient diseases." + e.getMessage());
			throw new PhmException("Failed to fetch patient diseases." + e.getMessage());
		}
		return record_diseaseDTOs;
	}

	/**
	 * Method to fetch all rows from OBSERVATION table in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of ObservationDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static List<ObservationDTO> getAllObservations(Connection connection) throws PhmException {
		List<ObservationDTO> observationDTOs = new ArrayList<ObservationDTO>();
		try {
			ResultSet resultSet = connection.createStatement().executeQuery(StringsUtil.OBSERVATION_QUERY);
			while (resultSet.next()) {
				ObservationDTO observationDTO = new ObservationDTO(resultSet.getInt("observationId"),
						resultSet.getString("personId"), resultSet.getInt("recommendationId"),
						resultSet.getString("observationValue"), resultSet.getTimestamp("recordTime"),
						resultSet.getTimestamp("observationTime"));
				observationDTOs.add(observationDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all Observations." + e.getMessage());
			throw new PhmException("Failed to fetch all Observations." + e.getMessage());
		}
		return observationDTOs;
	}

	/**
	 * Method to fetch all rows from OBSERVATION table in database for a
	 * specific Person.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of ObservationDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static List<ObservationDTO> getPersonObservations(Connection connection, String personId)
			throws PhmException {
		List<ObservationDTO> observationDTOs = new ArrayList<ObservationDTO>();
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.VIEW_OBSERVATIONS);
			ps.setString(1, personId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				ObservationDTO observationDTO = new ObservationDTO(resultSet.getInt("observationId"),
						resultSet.getString("personId"), resultSet.getInt("recommendationId"),
						resultSet.getString("observationValue"), resultSet.getTimestamp("recordTime"),
						resultSet.getTimestamp("observationTime"));
				observationDTOs.add(observationDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch patient Observations." + e.getMessage());
			throw new PhmException("Failed to fetch patient Observations." + e.getMessage());
		}
		return observationDTOs;
	}

	/**
	 * Method to fetch Recommendation_Desc from Recommendation table in database
	 * for a specific Observation_Id.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of ObservationDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static String getObservationType(Connection connection, int observationId) throws PhmException {
		String observation_type = null;
		try {
			PreparedStatement ps = connection
					.prepareStatement(StringsUtil.RECOMMENDATION_DESCRIPTION_FROM_OBSERVATION_ID);
			ps.setInt(1, observationId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				observation_type = resultSet.getString("Description");
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch Observation type." + e.getMessage());
			throw new PhmException("Failed to fetch Observation type." + e.getMessage());
		}
		return observation_type;
	}

	/**
	 * Method to fetch all rows from PERSON table in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of PersonDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static List<PersonDTO> getAllPersons(Connection connection) throws PhmException {
		List<PersonDTO> personDTOs = new ArrayList<PersonDTO>();
		try {
			ResultSet resultSet = connection.createStatement().executeQuery(StringsUtil.PERSON_QUERY);
			while (resultSet.next()) {
				PersonDTO personDTO = new PersonDTO(resultSet.getString("personId"), resultSet.getString("personName"),
						resultSet.getString("username"), resultSet.getString("password"),
						resultSet.getString("address"), resultSet.getString("dob"), resultSet.getString("gender"),
						resultSet.getString("contactInfo"));
				personDTOs.add(personDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all Persons." + e.getMessage());
			throw new PhmException("Failed to fetch all Persons." + e.getMessage());
		}
		return personDTOs;
	}

	/**
	 * 
	 */
	public static List<PersonDTO> getMyPatients(Connection connection, String health_supporterId) throws PhmException {
		List<PersonDTO> personDTOs = new ArrayList<PersonDTO>();
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.GET_PATIENTS_OF_HEALTH_SUPPORTER);
			ps.setString(1, health_supporterId);
			ps.setString(2, health_supporterId);
			ps.setString(3, health_supporterId);
			ps.setString(4, health_supporterId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				PersonDTO personDTO = new PersonDTO(resultSet.getString("personId"), resultSet.getString("personName"),
						resultSet.getString("username"), resultSet.getString("password"),
						resultSet.getString("address"), resultSet.getString("dob"), resultSet.getString("gender"),
						resultSet.getString("contactInfo"));
				personDTOs.add(personDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all patients." + e.getMessage());
			throw new PhmException("Failed to fetch all patients." + e.getMessage());
		}
		return personDTOs;
	}

	/**
	 * Method to fetch patient type from Sick table in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of PersonDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static String getPatientType(Connection connection, String personId) throws PhmException {
		String status = null;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.IS_PERSON_SICK_PATIENT);
			ps.setString(1, personId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				int value = resultSet.getInt("VALUE");
				if (value > 0)
					status = "SICK";
				else
					status = "WELL";
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch patient type." + e.getMessage());
			throw new PhmException("Failed to fetch patient type." + e.getMessage());
		}
		return status;
	}

	/**
	 * Method to fetch patient type from Sick table in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of PersonDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static int getStatusHealthSupporter(Connection connection, String personId) throws PhmException {
		int count = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.IS_PERSON_HEALTH_SUPPORTER);
			ps.setString(1, personId);
			ps.setString(2, personId);
			ps.setString(3, personId);
			ps.setString(4, personId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				int value = resultSet.getInt("VALUE");
				if (value > 0)
					count = value;
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch count for healthsupporters." + e.getMessage());
			throw new PhmException("Failed to fetch count for healthsupporters." + e.getMessage());
		}
		return count;
	}

	/**
	 * Method to fetch all rows from RECOMMENDATION table in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of AlertDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static List<RecommendationDTO> getAllRecommendations(Connection connection) throws PhmException {
		List<RecommendationDTO> recommendationDTOs = new ArrayList<RecommendationDTO>();
		try {
			ResultSet resultSet = connection.createStatement().executeQuery(StringsUtil.RECOMMENDATION_QUERY);
			while (resultSet.next()) {
				RecommendationDTO recommendationDTO = new RecommendationDTO(resultSet.getInt("recommendationId"),
						resultSet.getString("description"), resultSet.getString("frequency"),
						resultSet.getString("lowerBound"), resultSet.getString("upperBound"),
						resultSet.getString("metric"), resultSet.getString("value"));
				recommendationDTOs.add(recommendationDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all Recommendations." + e.getMessage());
			throw new PhmException("Failed to fetch all Recommendations." + e.getMessage());
		}
		return recommendationDTOs;
	}

	/**
	 * 
	 */
	public static List<RecommendationDTO> getRecommendations(Connection connection, String patientName)
			throws PhmException {
		List<RecommendationDTO> recommendationDTOs = new ArrayList<RecommendationDTO>();
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.GET_SUG_RECOMMENDATIONS);
			ps.setString(1, patientName);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				RecommendationDTO recommendationDTO = new RecommendationDTO(resultSet.getInt("recommendationId"),
						resultSet.getString("description"), resultSet.getString("frequency"),
						resultSet.getString("lowerBound"), resultSet.getString("upperBound"),
						resultSet.getString("metric"), resultSet.getString("value"));
				recommendationDTOs.add(recommendationDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all Recommendations." + e.getMessage());
			throw new PhmException("Failed to fetch all Recommendations." + e.getMessage());
		}
		return recommendationDTOs;
	}

	/**
	 * Method to fetch .
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of AlertDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static List<RecommendationDTO> getRecommendationByDId(Connection connection, int diseaseId)
			throws PhmException {
		List<RecommendationDTO> recommendationDTOs = new ArrayList<RecommendationDTO>();
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.GET_RECOMMENDATION_BY_DID);
			ps.setInt(1, diseaseId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				RecommendationDTO recommendationDTO = new RecommendationDTO(resultSet.getInt("recommendationId"),
						resultSet.getString("description"), resultSet.getString("frequency"),
						resultSet.getString("lowerBound"), resultSet.getString("upperBound"),
						resultSet.getString("metric"), resultSet.getString("value"));
				recommendationDTOs.add(recommendationDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all Recommendations by ID." + e.getMessage());
			throw new PhmException("Failed to fetch all Recommendations by ID." + e.getMessage());
		}
		return recommendationDTOs;
	}

	/**
	 * Method to fetch .
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of AlertDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static List<RecommendationDTO> getRecommendationByPId(Connection connection, String personId)
			throws PhmException {
		List<RecommendationDTO> recommendationDTOs = new ArrayList<RecommendationDTO>();
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.GET_RECOMMENDATION_BY_PID);
			ps.setString(1, personId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				RecommendationDTO recommendationDTO = new RecommendationDTO(resultSet.getInt("recommendationId"),
						resultSet.getString("description"), resultSet.getString("frequency"),
						resultSet.getString("lowerBound"), resultSet.getString("upperBound"),
						resultSet.getString("metric"), resultSet.getString("value"));
				recommendationDTOs.add(recommendationDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all Recommendations by P_ID." + e.getMessage());
			throw new PhmException("Failed to fetch all Recommendationsby P_ID." + e.getMessage());
		}
		return recommendationDTOs;
	}

	/**
	 * 
	 */
	public static List<RecommendationDTO> getPatientRecommendations(Connection connection, int recom_ID)
			throws PhmException {
		List<RecommendationDTO> recommendationDTOs = new ArrayList<RecommendationDTO>();
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.GET_RECOMMENDATIONS_BY_RID);
			ps.setInt(1, recom_ID);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				RecommendationDTO recommendationDTO = new RecommendationDTO(resultSet.getInt("recommendationId"),
						resultSet.getString("description"), resultSet.getString("frequency"),
						resultSet.getString("lowerBound"), resultSet.getString("upperBound"),
						resultSet.getString("metric"), resultSet.getString("value"));
				recommendationDTOs.add(recommendationDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch Patient's Recommendations." + e.getMessage());
			throw new PhmException("Failed to fetch Patient's Recommendations." + e.getMessage());
		}
		return recommendationDTOs;
	}

	/**
	 * Method to fetch all rows from RECORD_DISEASE table in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of RecordDiseaseDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static List<RecordDiseaseDTO> getAllRecordDiseases(Connection connection) throws PhmException {
		List<RecordDiseaseDTO> recordDiseaseDTOs = new ArrayList<RecordDiseaseDTO>();
		try {
			ResultSet resultSet = connection.createStatement().executeQuery(StringsUtil.RECRD_DISEASE_QUERY);
			while (resultSet.next()) {
				RecordDiseaseDTO recordDiseaseDTO = new RecordDiseaseDTO(resultSet.getString("personId"),
						resultSet.getInt("diseaseId"), resultSet.getTimestamp("recordTime"));
				recordDiseaseDTOs.add(recordDiseaseDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all Record Diseases." + e.getMessage());
			throw new PhmException("Failed to fetch all Record Diseases." + e.getMessage());
		}
		return recordDiseaseDTOs;
	}

	/**
	 * Method to fetch all rows from RECORD_DISEASE table in database for
	 * Specific person.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of RecordDiseaseDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static List<RecordDiseaseDTO> getPersonDiseases(Connection connection, String personId) throws PhmException {
		List<RecordDiseaseDTO> recordDiseaseDTOs = new ArrayList<RecordDiseaseDTO>();
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.VIEW_DISEASES);
			ps.setString(1, personId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				RecordDiseaseDTO recordDiseaseDTO = new RecordDiseaseDTO(resultSet.getString("personId"),
						resultSet.getInt("diseaseId"), resultSet.getTimestamp("recordTime"));
				recordDiseaseDTOs.add(recordDiseaseDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch patient's diseases." + e.getMessage());
			throw new PhmException("Failed to fetch patient's diseases." + e.getMessage());
		}
		return recordDiseaseDTOs;
	}

	/**
	 * Method to fetch all rows from SICK_PERSON table in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of SickPersonDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static List<SickPersonDTO> getAllSickPersons(Connection connection) throws PhmException {
		List<SickPersonDTO> sickPersonDTOs = new ArrayList<SickPersonDTO>();
		try {
			ResultSet resultSet = connection.createStatement().executeQuery(StringsUtil.SICK_PERSON_QUERY);
			while (resultSet.next()) {
				SickPersonDTO sickPersonDTO = new SickPersonDTO(resultSet.getString("personId"),
						resultSet.getString("healthSupporter1Id"), resultSet.getString("healthSupporter2Id"),
						resultSet.getDate("hs1AuthDate"), resultSet.getDate("hs2AuthDate"));
				sickPersonDTOs.add(sickPersonDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all Sick Persons." + e.getMessage());
			throw new PhmException("Failed to fetch all Sick Persons." + e.getMessage());
		}
		return sickPersonDTOs;
	}

	/**
	 * 
	 */
	public static SickPersonDTO getSickPersonHealthSupporter(Connection connection, String patientId)
			throws PhmException {
		SickPersonDTO sickPersonDTO = null;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.HP_SICK_PERSON_QUERY);
			ps.setString(1, patientId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				sickPersonDTO = new SickPersonDTO(resultSet.getString("personId"),
						resultSet.getString("healthSupporter1Id"), resultSet.getString("healthSupporter2Id"),
						resultSet.getDate("hs1AuthDate"), resultSet.getDate("hs2AuthDate"));
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch Sick Person's HPs." + e.getMessage());
			throw new PhmException("Failed to fetch Sick Person's HPs." + e.getMessage());
		}
		return sickPersonDTO;
	}

	/**
	 * Method to fetch all rows from SPECIFIC_RECOMMENDATION table in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of SpecificRecommendationDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static List<SpecificRecommendationDTO> getAllSpecificRecommendations(Connection connection)
			throws PhmException {
		List<SpecificRecommendationDTO> specificRecommendationDTOs = new ArrayList<SpecificRecommendationDTO>();
		try {
			ResultSet resultSet = connection.createStatement().executeQuery(StringsUtil.SPE_RECOMMENDATION_QUERY);
			while (resultSet.next()) {
				SpecificRecommendationDTO specificRecommendationDTO = new SpecificRecommendationDTO(
						resultSet.getInt("recommendationId"), resultSet.getString("personId"));
				specificRecommendationDTOs.add(specificRecommendationDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all Specific Recommendations." + e.getMessage());
			throw new PhmException("Failed to fetch all Specific Recommendations." + e.getMessage());
		}
		return specificRecommendationDTOs;
	}

	/**
	 * 
	 */
	public static List<SpecificRecommendationDTO> getPatientSpecificRecommendations(Connection connection,
			String personId) throws PhmException {
		List<SpecificRecommendationDTO> specificRecommendationDTOs = new ArrayList<SpecificRecommendationDTO>();
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.PATIENT_SPECIFIC_RECOMMENDATIONS);
			ps.setString(1, personId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				SpecificRecommendationDTO specificRecommendationDTO = new SpecificRecommendationDTO(
						resultSet.getInt("recommendationId"), resultSet.getString("personId"));
				specificRecommendationDTOs.add(specificRecommendationDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all Specific Recommendations." + e.getMessage());
			throw new PhmException("Failed to fetch all Specific Recommendations." + e.getMessage());
		}
		return specificRecommendationDTOs;
	}

	/**
	 * Method to fetch all rows from STANDARD_RECOMMENDATION table in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of StandardRecommendationDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static List<StandardRecommendationDTO> getAllStandardRecommendations(Connection connection)
			throws PhmException {
		List<StandardRecommendationDTO> standardRecommendationDTOs = new ArrayList<StandardRecommendationDTO>();
		try {
			ResultSet resultSet = connection.createStatement().executeQuery(StringsUtil.STD_RECOMMENDATION_QUERY);
			while (resultSet.next()) {
				StandardRecommendationDTO standardRecommendationDTO = new StandardRecommendationDTO(
						resultSet.getInt("diseaseId"), resultSet.getInt("recommendationId"));
				standardRecommendationDTOs.add(standardRecommendationDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all Standard Recommendations." + e.getMessage());
			throw new PhmException("Failed to fetch all Standard Recommendations." + e.getMessage());
		}
		return standardRecommendationDTOs;
	}

	/**
	 * Method to fetch all rows from STANDARD_RECOMMENDATION table in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of StandardRecommendationDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static List<StandardRecommendationDTO> getPatientStandardRecommendations(Connection connection,
			int diseaseId) throws PhmException {
		List<StandardRecommendationDTO> standardRecommendationDTOs = new ArrayList<StandardRecommendationDTO>();
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.PATIENT_STANDARD_RECOMENDATIONS);
			ps.setInt(1, diseaseId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				StandardRecommendationDTO standardRecommendationDTO = new StandardRecommendationDTO(
						resultSet.getInt("diseaseId"), resultSet.getInt("recommendationId"));
				standardRecommendationDTOs.add(standardRecommendationDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all Standard Recommendations." + e.getMessage());
			throw new PhmException("Failed to fetch all Standard Recommendations." + e.getMessage());
		}
		return standardRecommendationDTOs;
	}

	/**
	 * Method to fetch all rows from WELL_PERSON table in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of WellPersonDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static List<WellPersonDTO> getAllWellPersons(Connection connection) throws PhmException {
		List<WellPersonDTO> wellPersonDTOs = new ArrayList<WellPersonDTO>();
		try {
			ResultSet resultSet = connection.createStatement().executeQuery(StringsUtil.WELL_PERSON_QUERY);
			while (resultSet.next()) {
				WellPersonDTO wellPersonDTO = new WellPersonDTO(resultSet.getString("personId"),
						resultSet.getString("healthSupporter1Id"), resultSet.getString("healthSupporter2Id"),
						resultSet.getDate("hs1AuthDate"), resultSet.getDate("hs2AuthDate"));
				wellPersonDTOs.add(wellPersonDTO);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all Well Persons." + e.getMessage());
			throw new PhmException("Failed to fetch all Well Persons." + e.getMessage());
		}
		return wellPersonDTOs;
	}
	
	/**
	 * Method to fetch all rows from WELL_PERSON table in database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of WellPersonDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static WellPersonDTO getWellPerson(Connection connection, String personId) throws PhmException {
		WellPersonDTO wellPersonDTO = null;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.WELL_PERSON_QUERY_BY_ID);
			ps.setString(1, personId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				wellPersonDTO = new WellPersonDTO(resultSet.getString("personId"),
						resultSet.getString("healthSupporter1Id"), resultSet.getString("healthSupporter2Id"),
						resultSet.getDate("hs1AuthDate"), resultSet.getDate("hs2AuthDate"));
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch requested Well Person" + e.getMessage());
			throw new PhmException("Failed to fetch requested Well Person" + e.getMessage());
		}
		return wellPersonDTO;
	}

	/**
	 * 
	 */
	public static WellPersonDTO getWellPersonHealthSupporter(Connection connection, String patientId)
			throws PhmException {
		WellPersonDTO wellPersonDTO = null;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.HP_WELL_PERSON_QUERY);
			ps.setString(1, patientId);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				wellPersonDTO = new WellPersonDTO(resultSet.getString("personId"),
						resultSet.getString("healthSupporter1Id"), resultSet.getString("healthSupporter2Id"),
						resultSet.getDate("hs1AuthDate"), resultSet.getDate("hs2AuthDate"));
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all Well Person's HPs." + e.getMessage());
			throw new PhmException("Failed to fetch all Well Person's HPs." + e.getMessage());
		}
		return wellPersonDTO;
	}

	public static SpecificRecommendationDTO getSpecificRecommendationById(Connection connection, int recomID) throws PhmException {
		// TODO Auto-generated method stub
		SpecificRecommendationDTO specificRecommendationDTO = null;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.SPECIFIC_RECOMMENDATIONS);
			ps.setInt(1, recomID);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				specificRecommendationDTO = new SpecificRecommendationDTO(
						resultSet.getInt("recommendationId"), resultSet.getString("personId"));
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all Specific Recommendations." + e.getMessage());
			throw new PhmException("Failed to fetch all Specific Recommendations." + e.getMessage());
		}
		return specificRecommendationDTO;
	}
}
