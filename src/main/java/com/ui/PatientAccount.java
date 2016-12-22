package com.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import com.database.ConnectionManager;
import com.database.SelectQueries;
import com.exception.PhmException;
import com.model.PersonDTO;
import com.util.StringsUtil;

public class PatientAccount {

	public static void showScreen(String patientId, String health_supporterId) throws PhmException, SQLException, ParseException {
		// TODO Auto-generated method stub
				
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		int input=-1;
		Connection con = new ConnectionManager().getConnection();
		PersonDTO patientName = SelectQueries.getPatientDetails(con, patientId);
		
		while (flag) {
			sc = new Scanner(System.in);
			System.out.println(StringsUtil.LOGIN_MESSAGE);
			System.out.println("Select an option for "+ patientName.getPersonName());
			System.out.println("1. View Profile");
			System.out.println("2. Add an Observation");
			System.out.println("3. View Standard Recommendations");
			System.out.println("4. View Specific Recommendations");
			System.out.println("5. Add Specific Recommendations");
			System.out.println("6. Edit Specific Recommendation");
			System.out.println("7. View Alerts");
			System.out.println("8. Go Back To My Homepage.");
			
			input = Integer.valueOf(sc.nextLine());
			
			switch(input)
			{
			case 1:
				UserScreen.viewProfile(patientName);
				break;
			case 2:
				Observation.addObservation(patientName);
				break;
			case 3:
				Recommendation.viewStandardRecommendation(patientName);
				break;
			case 4:
				Recommendation.viewSpecificRecommendation(patientName);
				break;
			case 5:
				Recommendation.addSpecificRecommendation(patientName);
				break;
			case 6:
				Recommendation.editSpecificRecommendation(patientName);
				break;	
			case 7:
				AlertScreen.showScreen(health_supporterId, patientName.getPersonId());
				break;
			case 8:
				flag = false;
				break;
			default:
				System.out.println("Invalid Input. Try again.");
				break;
			}
		}
		con.close();
	}

}
