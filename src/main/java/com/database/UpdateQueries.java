package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.exception.PhmException;
import com.util.StringsUtil;

/**
 * @author Sumit
 *
 */
public class UpdateQueries {

	/**
	 * Method to update the alert table in database and set the is viewed column
	 * as true. If flag clear mandatory is set then mandatory alerts are also
	 * cleared.
	 * 
	 * @param clearMandatory
	 *            if mandatory alerts need to be cleared as well.
	 * @return boolean value indicating that alerts were cleared successfully
	 */
	public static boolean clearAlerts(Connection con, int alertId, String personId, boolean clearMandatory)
			throws PhmException {
		boolean status = false;
		try {
			PreparedStatement ps = null;
			if (clearMandatory) {
				ps = con.prepareStatement(StringsUtil.CLEAR_ALERT_BY_HS);
			} else {
				ps = con.prepareStatement(StringsUtil.CLEAR_ALERT_BY_PATIENT);
			}
			ps.setString(1, personId);
			ps.setInt(2, alertId);
			int rows = ps.executeUpdate();
			if (rows > 0)
				status = true;
			else
				status = false;

		} catch (SQLException e) {
			System.out.println("Failed to Clear Alerts." + e.getMessage());
			throw new PhmException("Failed to Clear Alerts." + e.getMessage());
		}
		return status;
	}

	/**
	 * Method to update Person table database.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return List of PersonDTO objects
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static boolean updatePersonProfile(Connection connection, String personId, String fullname, String password,
			String address, String contact, String gender) throws PhmException {
		boolean status = false;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.UPDATE_PERSON_PROFILE);
			ps.setString(1, fullname);
			ps.setString(2, password);
			ps.setString(3, address);
			ps.setString(4, gender);
			ps.setString(5, contact);
			ps.setString(6, personId);
			int rows = ps.executeUpdate();

			if (rows > 0)
				status = true;
			else
				status = false;

		} catch (SQLException e) {
			System.out.println("Failed to update profile" + e.getMessage());
			throw new PhmException("Failed to update profile" + e.getMessage());
		}
		return status;
	}

	/**
	 * Method to delete Disease from Record Disease.
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static boolean deletePatientDisease(Connection connection, String personId, int diseaseId)
			throws PhmException {
		boolean status = false;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.DELETE_RECORD_DISEASE);
			ps.setString(1, personId);
			ps.setInt(2, diseaseId);
			int rows = ps.executeUpdate();
			if (rows > 0)
				status = true;
			else
				status = false;

		} catch (SQLException e) {
			System.out.println("Failed to delete patient." + e.getMessage());
			throw new PhmException("Failed to delete patient." + e.getMessage());
		}
		return status;
	}

	/**
	 * Method to Sick Person update health Supporter 1
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return
	 * @throws PhmException
	 *             if some error occurs
	 * @throws ParseException 
	 */
	public static boolean updateSickPersonHealthSupporter1(Connection connection, String personId, String hp1, String auth_date)
			throws PhmException, ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		boolean status = false;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.UPDATE_SICK_PERSON_FIRST_HEALTH_SUPPORTER);
			ps.setString(1, hp1);
			java.util.Date utilDate = formatter.parse(auth_date);
			java.sql.Date hp_auth_date =  new java.sql.Date(utilDate.getTime());
			ps.setDate(2, hp_auth_date);
			ps.setString(3, personId);
			int rows = ps.executeUpdate();
			if (rows > 0)
				status = true;
			else
				status = false;

		} catch (SQLException e) {
			System.out.println("Failed to update Sick Person's HP1." + e.getMessage());
			throw new PhmException("Failed to update Sick Person's HP1." + e.getMessage());
		}
		return status;
	}

	/**
	 * Method to Well Person update health Supporter 1
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return
	 * @throws PhmException
	 *             if some error occurs
	 * @throws ParseException 
	 */
	public static boolean updateWellPersonHealthSupporter1(Connection connection, String personId, String hp1, String auth_date)
			throws PhmException, ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		boolean status = false;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.UPDATE_WELL_PERSON_FIRST_HEALTH_SUPPORTER);
			ps.setString(1, hp1);
			java.util.Date utilDate = formatter.parse(auth_date);
			java.sql.Date hp_auth_date =  new java.sql.Date(utilDate.getTime());
			ps.setDate(2, hp_auth_date);
			ps.setString(3, personId);
			int rows = ps.executeUpdate();
			if (rows > 0)
				status = true;
			else
				status = false;

		} catch (SQLException e) {
			System.out.println("Failed to update Well Person's HP1." + e.getMessage());
			throw new PhmException("Failed to update Well Person's HP1." + e.getMessage());
		}
		return status;
	}

	/**
	 * Method to Sick Person update health Supporter 2
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return
	 * @throws PhmException
	 *             if some error occurs
	 * @throws ParseException 
	 */
	public static boolean updateSickPersonHealthSupporter2(Connection connection, String personId, String hp2, String auth_date)
			throws PhmException, ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		boolean status = false;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.UPDATE_SICK_PERSON_SECOND_HEALTH_SUPPORTER);
			ps.setString(1, hp2);
			java.util.Date utilDate = formatter.parse(auth_date);
			java.sql.Date hp_auth_date =  new java.sql.Date(utilDate.getTime());
			ps.setDate(2, hp_auth_date);
			ps.setString(2, personId);
			int rows = ps.executeUpdate();
			if (rows > 0)
				status = true;
			else
				status = false;

		} catch (SQLException e) {
			System.out.println("Failed to update Sick Person's HP2." + e.getMessage());
			throw new PhmException("Failed to update Sick Person's HP2." + e.getMessage());
		}
		return status;
	}

	/**
	 * Method to Well Person update health Supporter 2
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return
	 * @throws PhmException
	 *             if some error occurs
	 * @throws ParseException 
	 */
	public static boolean updateWellPersonHealthSupporter2(Connection connection, String personId, String hp2, String auth_date)
			throws PhmException, ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		boolean status = false;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.UPDATE_WELL_PERSON_SECOND_HEALTH_SUPPORTER);
			ps.setString(1, hp2);
			java.util.Date utilDate = formatter.parse(auth_date);
			java.sql.Date hp_auth_date =  new java.sql.Date(utilDate.getTime());
			ps.setDate(2, hp_auth_date);
			ps.setString(2, personId);
			int rows = ps.executeUpdate();
			if (rows > 0)
				status = true;
			else
				status = false;

		} catch (SQLException e) {
			System.out.println("Failed to update Well Person's HP2." + e.getMessage());
			throw new PhmException("Failed to update Well Person's HP2." + e.getMessage());
		}
		return status;
	}
	
	/**
	 * Method to Well Person update health Supporter 2
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static boolean deleteSickHealthSupporter2(Connection connection, String personId)
			throws PhmException {
		boolean status = false;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.DELETE_SICK_PERSON_SECOND_HEALTH_SUPPORTER);
			ps.setString(1, personId);
			int rows = ps.executeUpdate();
			if (rows > 0)
				status = true;
			else
				status = false;

		} catch (SQLException e) {
			System.out.println("Failed to delete Sick Patient HP2." + e.getMessage());
			throw new PhmException("Failed to delete Sick Patient HP2." + e.getMessage());
		}
		return status;
	}
	
	/**
	 * Method to Well Person update health Supporter 2
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static boolean deleteWellHealthSupporter1(Connection connection, String personId)
			throws PhmException {
		boolean status = false;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.DELETE_WELL_PERSON_FIRST_HEALTH_SUPPORTER);
			ps.setString(1, personId);
			int rows = ps.executeUpdate();
			if (rows > 0)
				status = true;
			else
				status = false;

		} catch (SQLException e) {
			System.out.println("Failed to delete Well Patient HP1." + e.getMessage());
			throw new PhmException("Failed to delete Well Patient HP1." + e.getMessage());
		}
		return status;
	}
	
	/**
	 * Method to Well Person update health Supporter 2
	 * 
	 * @param connection
	 *            the database connection to use
	 * @return
	 * @throws PhmException
	 *             if some error occurs
	 */
	public static boolean deleteWellHealthSupporter2(Connection connection, String personId)
			throws PhmException {
		boolean status = false;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.DELETE_WELL_PERSON_SECOND_HEALTH_SUPPORTER);
			ps.setString(1, personId);
			int rows = ps.executeUpdate();
			if (rows > 0)
				status = true;
			else
				status = false;

		} catch (SQLException e) {
			System.out.println("Failed to delete Well Patient HP1." + e.getMessage());
			throw new PhmException("Failed to delete Well Patient HP1." + e.getMessage());
		}
		return status;
	}
	
	
	public static boolean updateRecommendation(Connection connection, int new1, int old1,String personId)
			throws PhmException {
		boolean status = false;
		try {
			PreparedStatement ps = connection.prepareStatement(StringsUtil.UPDATE_SPECIFIC_RECOMMENDATION);
			ps.setInt(1, new1);
			ps.setInt(2, old1);
			ps.setString(3, personId);
			
			int rows = ps.executeUpdate();
			if (rows > 0)
				status = true;
			else
				status = false;

		} catch (SQLException e) {
			System.out.println("Failed to delete Well Patient HP1." + e.getMessage());
			throw new PhmException("Failed to delete Well Patient HP1." + e.getMessage());
		}
		return status;
	}
}
