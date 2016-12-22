package com.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import com.database.ConnectionManager;
import com.database.SelectQueries;
import com.exception.PhmException;
import com.model.PersonDTO;

/*
 * @author Animesh
 * 
 */

public class HealthSupporterScreen {
	
	public static void showScreen(PersonDTO health_supporter) throws PhmException, SQLException, ParseException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int input = -1;
		String input1;
		boolean flag = true;
		int count = 1;
		Connection con = new ConnectionManager().getConnection();
		List<PersonDTO> patients_list = SelectQueries.getMyPatients(con, health_supporter.getPersonId());
		while(flag)
		{
			System.out.println("List of Your Patients");
			for(PersonDTO patient: patients_list)
			{
				System.out.println(count++ + ". Patient Id: " + patient.getPersonId() + "\t Patient Name: " + patient.getPersonName());
			}
			System.out.println();
			System.out.println("Your Actions");
			System.out.println("1. Patient's Account");
			System.out.println("2. Recommendations");
			System.out.println("3. Go Back");
			System.out.println("Choose Option.");
			input = Integer.valueOf(sc.nextLine());
			
			switch(input)
			{
			case 1:
				System.out.println("Enter Patient ID: (from below List)");
				for(PersonDTO patient: patients_list)
				{
					System.out.println("Patient Id: " + patient.getPersonId() + "\t Patient Name: " + patient.getPersonName());
				}
				
				input1 = sc.nextLine();
				PatientAccount.showScreen(input1, health_supporter.getPersonId());
				break;
			case 2:
				Recommendation.showScreenforHP(health_supporter);
				break;
			case 3:
				flag = false;
				break;
			default:
				System.out.println("Invalid input. Try again.");
				break;
			}
		}
		con.close();
	}
}
