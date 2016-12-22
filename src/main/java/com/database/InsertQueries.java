/**
 * 
 */
package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.exception.PhmException;
import com.model.ObservationDTO;
import com.model.PersonDTO;
import com.model.RecommendationDTO;
import com.model.RecordDiseaseDTO;
import com.model.SickPersonDTO;
import com.util.StringsUtil;

/**
 * @author Sumit
 *
 */
public class InsertQueries {

	public static boolean insertPerson(Connection connection, PersonDTO personDTO) throws PhmException, java.text.ParseException {
		
		 SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		 
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.INSERT_PERSON);
			ps.setString(1, personDTO.getPersonName());
			ps.setString(2, personDTO.getUsername());
			ps.setString(3, personDTO.getPassword());
			ps.setString(4, personDTO.getAddress());
			java.util.Date utilDate = formatter.parse(personDTO.getDob());
			java.sql.Date dob_date =  new java.sql.Date(utilDate.getTime());
			ps.setDate(5, dob_date);
			ps.setString(6, personDTO.getGender());
			ps.setString(7, personDTO.getContactInfo());
			
			ps.executeUpdate();
			System.out.println("User has been added.!");
		} catch (SQLException e) {
			throw new PhmException("Errror While inserting Person " + e.getMessage());
		}
		return true;
	}
	
	public static boolean recordDisease(Connection connection, RecordDiseaseDTO record_diseaseDTO) throws PhmException, java.text.ParseException {
		
		 boolean status=false;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.RECORD_DISEASE);
			ps.setString(1, record_diseaseDTO.getPersonId());
			ps.setInt(2, record_diseaseDTO.getDiseaseId());
			int value = ps.executeUpdate();
			if(value>0)
				status = true;
			else
				status = false;
		} catch (SQLException e) {
			throw new PhmException("Errror While inserting record " + e.getMessage());
		}
		return status;
	}
	
	public static boolean insertObservation(Connection connection, ObservationDTO observationDTO) throws PhmException, java.text.ParseException {
		 
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.INSERT_OBSERVATION);
			ps.setString(1, observationDTO.getPersonId());
			ps.setInt(2, observationDTO.getRecommendationId());
			ps.setString(3, observationDTO.getObservationValue());
			ps.setTimestamp(4, observationDTO.getObservationTime());
			ps.executeUpdate();
			System.out.println("Observation has been added.!");
		} catch (SQLException e) {
			throw new PhmException("Errror While inserting observation " + e.getMessage());
		}
		return true;
	}
	
	public static boolean insertRecommendation(Connection connection, RecommendationDTO recommendationDTO) throws PhmException, java.text.ParseException {
		
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.INSERT_RECOMMENDATION);
			ps.setString(1, recommendationDTO.getFrequency());
			ps.setString(2, recommendationDTO.getDescription());
			ps.setString(3, recommendationDTO.getMetric());
			ps.setString(4, recommendationDTO.getLowerLimit());
			ps.setString(5, recommendationDTO.getUpperLimit());
			ps.setString(6, recommendationDTO.getValue());
			ps.executeUpdate();
			System.out.println("Recommendation has been added.!");
		} catch (SQLException e) {
			throw new PhmException("Errror While inserting recommendation " + e.getMessage());
		}
		return true;
	}
	
public static boolean insertSpecificRecommendation(Connection connection, String patientId, int recommendationId) throws PhmException, java.text.ParseException {
		
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.INSERT_SPECIFIC_RECOMMENDATION);
			ps.setString(1, patientId);
			ps.setInt(2,  recommendationId);
			ps.executeUpdate();
			System.out.println("Specific Recommendation has been added.!");
		} catch (SQLException e) {
			throw new PhmException("Errror While inserting specific recommendation " + e.getMessage());
		}
		return true;
	}

public static boolean insertSickPerson(Connection connection, SickPersonDTO sickPatient) throws PhmException, java.text.ParseException {
	
	try {
		PreparedStatement ps = connection.prepareStatement(StringsUtil.INSERT_SICK_PERSON);
		ps.setString(1, sickPatient.getPersonId());
		ps.setString(2, sickPatient.getHealthSupporter1Id());
		ps.setString(3, sickPatient.getHealthSupporter2Id());
		ps.setDate(4, sickPatient.getHs1AuthDate());
		ps.setDate(5, sickPatient.getHs2AuthDate());
		ps.executeUpdate();
		return true;
	} catch (SQLException e) {
		throw new PhmException("Errror While inserting new category of patient " + e.getMessage());
	}
}
}
