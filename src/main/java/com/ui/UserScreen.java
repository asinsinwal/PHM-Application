/**
 * 
 */
package com.ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.database.ConnectionManager;
import com.database.SelectQueries;
import com.database.UpdateQueries;
import com.exception.PhmException;
import com.model.PersonDTO;
import com.util.StringsUtil;

/**
 * @author Sumit
 *
 */
public class UserScreen {
	
	int count = 0;

	public void showUserScreen(PersonDTO person) throws PhmException {
		try{
			System.out.println("Hello, " + person.getPersonName());
			
			boolean checkHealthSup = checkHealthSupporter(person);
			Scanner sc = new Scanner(System.in);
			boolean flag = true;
			int input=-1;
			
			while (flag) {
				sc = new Scanner(System.in);
				System.out.println(StringsUtil.LOGIN_MESSAGE);
				System.out.println("Select an option ");
				System.out.println("1. Profile");
				System.out.println("2. Diagnoses");
				System.out.println("3. Health Indicators");
				System.out.println("4. Alerts");
				System.out.println("5. Health Supporters");
				
			
				if(checkHealthSup)
				{
					System.out.println("\nHealth Supporter Option");
					System.out.print("6. View Patient(s): \t");
					System.out.print("You have "+ count + " Patient(s) under you.\n");
				}
				
				System.out.println("0. Log Out");
				System.out.println("Enter Choice: ");
				input = Integer.valueOf(sc.nextLine());	
				
				switch (input) {
				case 0:
					System.out.println("Bye " + person.getPersonName());
					System.out.println();
					flag = false;
					break;
				case 1:
					personalize(person);
					break;
				case 2:
					Disease.showScreen(person);
					break;
				case 3:
					Recommendation.showScreen(person);
					break;
				case 4:
					AlertScreen.showScreen(person.getPersonId(),null);
					break;
				case 5:
					HealthSupporter.showScreen(person);
					break;
				case 6:
					if(checkHealthSup)
					{
						HealthSupporterScreen.showScreen(person);
					}
					else
					{
						System.out.println("Invalid option. Try again.!");
					}
					break;
				default:
					System.out.println("Invalid option. Try again.!");			
					break;
				}
			}
		} catch (Exception pe) {
			System.out.println("ERROR" + pe.getMessage());
			pe.printStackTrace();
		}
	}

	private void personalize(PersonDTO person) throws PhmException, SQLException {
		// TODO Auto-generated method stub
		Scanner sc1 = new Scanner(System.in);
		boolean flag1 = true;
		int input1=-1;
		while (flag1) {
			sc1 = new Scanner(System.in);
			System.out.println(StringsUtil.LOGIN_MESSAGE);
			System.out.println("Select an option ");
			System.out.println("1. View Profile");
			System.out.println("2. Edit Profile");
			System.out.println("3. Go Back");
			
			System.out.println("Enter Choice: ");
			input1 = Integer.valueOf(sc1.nextLine());	
			
			switch (input1) {
			
			case 1:
				viewProfile(person);
				break;
			case 2:
				editProfile(person);
				break;
			case 3:
				flag1 = false;
				break;
			default:
				System.out.println("Invalid option. Try again.!");			
				break;
			}	
		}
		
	}
	
	public static void viewProfile(PersonDTO person1) throws PhmException, SQLException
	{
		Connection con = new ConnectionManager().getConnection();
		PersonDTO person = SelectQueries.getPatientDetails(con, person1.getPersonId());
	
		Scanner sc = new Scanner(System.in);System.out.println("Your Profile");
		System.out.println("Name: \t\t" + person.getPersonName());
		System.out.println("Username: \t" + person.getUsername());
		System.out.println("Date of Birth: \t" + person.getDob());
		System.out.println("Address: \t" + person.getAddress());
		if("M".equalsIgnoreCase(person.getGender()))
		{
			System.out.println("Gender: \tMale");
		}
		else if("F".equalsIgnoreCase(person.getGender()))
		{
			System.out.println("Gender: \tFemale");
		}
		else
		{
			System.out.println("Gender: \t" + person.getGender());
		}
		System.out.println("Contant Info: \t" + person.getContactInfo());
		
		String status = SelectQueries.getPatientType(con, person.getPersonId());
		System.out.println("Patient category:\t" + status);
		System.out.println();
		boolean flag = true;
		System.out.println("1. Go Back");
		while(flag)
		{
			
			System.out.println("Enter Choice:");
			int in = Integer.valueOf(sc.nextLine());
			if(in == 1)
			{
				flag = false;
			}
			else
				System.out.println("1. Go Back (Enter 1 to go back)");
		}
		con.close();
	}
	
	private static void editProfile(PersonDTO person) throws PhmException, SQLException
	{
		System.out.println("Edit Exisiting Profile");
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter full name: ");
		String fullname = sc.nextLine();
		System.out.println("Enter a Password: ");
		String password = sc.nextLine();
		System.out.println("Enter Address: ");
		String address = sc.nextLine();
		System.out.println("Enter Contact Info: ");
		String contact = sc.nextLine();
		System.out.println("Enter Gender: ");
		String gender = sc.nextLine();
		Connection con = new ConnectionManager().getConnection();
		boolean status = UpdateQueries.updatePersonProfile(con, person.getPersonId(),fullname,password,address, contact, gender);
		if(status)
			System.out.println("Profile Update Successfully.");
		con.close();
	}

	private boolean checkHealthSupporter(PersonDTO person) throws PhmException, SQLException {
		// TODO Auto-generated method stub
		boolean status = false;
		Connection con = new ConnectionManager().getConnection();
		count = SelectQueries.getStatusHealthSupporter(con, person.getPersonId());
		con.close();
		if(count>0)
			status = true;
		else
			status = false;
		return status;
	}
}
