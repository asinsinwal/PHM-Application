package com.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.database.ConnectionManager;
import com.database.SelectQueries;
import com.database.UpdateQueries;
import com.exception.PhmException;
import com.model.AlertDTO;
import com.util.StringsUtil;

/**
 * @author Sumit
 *
 */
public class AlertScreen {

	/**
	 * Method to fetch and list all the alerts of a user or, all the alerts of
	 * the patient of a health supporter. If patientId is null, then self alerts
	 * will be shown for personId else, the alerts for patientIds would be
	 * shown.
	 * 
	 * @param personId
	 *            the person Id of the person whose alerts are to be shown.
	 * @param patientId
	 *            the patient id if health supporter is viewing the alerts or
	 *            else null.
	 * @throws PhmException
	 *             If error
	 * @throws SQLException
	 *             If error while fetching alerts
	 */
	public static void showScreen(String personId, String patientId) throws PhmException, SQLException {
		// TODO: list all alerts
		// give option to clear some or all non-mandatory alerts
		// back option.
		boolean isNotHs = true;
		if (null == patientId) {
			isNotHs = true;
		} else {
			isNotHs = false;
		}
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		Map<Integer, Integer> alertindexMap = new HashMap<Integer, Integer>();
		while (flag) {
			System.out.println(StringsUtil.LOGIN_MESSAGE);
			List<AlertDTO> alerts = null;
			if (isNotHs) { // case when self viewing
				alerts = getAlerts(personId);
				System.out.println("You have the following Alerts: ");
			} else { // case when viewing for patient
				alerts = getAlerts(patientId);
				System.out.println("Your patient has the following Alerts: ");
			}
			int i = 1;
			if (null == alerts || alerts.size() == 0) {
				System.out.println("No Alerts to show!");
			} else {
				for (AlertDTO alert : alerts) {
					int index = i++;
					alertindexMap.put(index, alert.getAlertId());
					System.out.println(index + ") " + alert.getDescription());
				}
				System.out.println("\nSelect Choice by Character");
				System.out.println("c) Clear alerts.");
			}
			System.out.println("b) Back.");
			System.out.println("Enter Choice: ");
			String choice = sc.nextLine();
			if ("b".equalsIgnoreCase(choice)) {
				flag = false;
				break;
			} else if ("c".equalsIgnoreCase(choice)) {
				System.out.println("Enter Alert ID to be cleared: ");
				try {
					int alertId = Integer.valueOf(sc.nextLine());
					if (alertId > i) {
						throw new NumberFormatException();
					}
					Connection con = new ConnectionManager().getConnection();
					boolean status = false;
					if (isNotHs) {
						// if patient is clearing for himself, then he cannot
						// clear mandatory alerts.
						// -> for all mandatory alerts show a message that he
						// needs to enter observation to clear that alert.
						status = UpdateQueries.clearAlerts(con, alertindexMap.get(alertId), personId, false);
						if (status) {
							System.out.println("Alert Cleared!");
						} else {
							System.out.println(
									"You cannot clear this alert as it is a mandatory alert. Either enter observation or contact your health supporter to clear this alert.");
						}
					} else {
						// if HS is clearing for patient, then he can clear all
						// without checking mandatory.
						status = UpdateQueries.clearAlerts(con, alertindexMap.get(alertId), patientId, true);
						if (status) {
							System.out.println("Alert Cleared!");
						} else {
							System.out.println("There was some prolem in clearing the alert.");
						}
					}
					con.close();
				} catch (NumberFormatException nfe) {
					System.out.println("Invaild AlertId!");
				}
			}
		}
	}

	/**
	 * Method to fetch all the alerts for a person from database
	 * 
	 * @param personId
	 *            the person ID
	 * @return the list of alert DTO objects
	 * @throws PhmException
	 *             If error
	 */
	private static List<AlertDTO> getAlerts(String personId) throws PhmException, SQLException {
		Connection con = new ConnectionManager().getConnection();
		List<AlertDTO> alerts = SelectQueries.getPatientAlerts(con, personId);
		con.close();
		return alerts;
	}
}
