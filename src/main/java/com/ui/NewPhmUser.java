package com.ui;

import java.sql.Connection;
import java.text.ParseException;
import java.util.Scanner;

import com.database.ConnectionManager;
import com.database.InsertQueries;
import com.exception.PhmException;
import com.model.PersonDTO;
import com.util.StringsUtil;

/**
 * @author Sumit
 *
 */
public class NewPhmUser {

	/**
	 * Method to sign up a new user
	 * 
	 * @param name
	 *            the name of user
	 * @param username
	 *            the userName of user
	 * @param password
	 *            the encrypted password of user.
	 * @return
	 */
	public boolean signUp(String name, String username, String password) {
		return true;
	}

	public void showScreen() throws PhmException, ParseException {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.out.println(StringsUtil.LOGIN_MESSAGE);
			System.out.println("Create New User");
			System.out.println("Enter full name: ");
			String name = sc.nextLine();
			System.out.println("Enter a Username: ");
			String username = sc.nextLine();
			System.out.println("Enter a Password: ");
			String password = sc.nextLine();
			System.out.println("Enter Address: ");
			String address = sc.nextLine();
			System.out.println("Enter DOB (MM/dd/yyyy): ");
			String dob = sc.nextLine();
			System.out.println("Enter Gender: ");
			String gender = sc.nextLine();
			System.out.println("Enter Contact Information: ");
			String contact = sc.nextLine();
			PersonDTO person = new PersonDTO(null, name, username, password, address, dob, gender, contact);
			boolean status = insertPerson(person);
			if (status) {
				System.out.println("Account Created Successfully");
				flag = false;
				break;
			} else {
				System.out.println("Failed to create account");
			}
		}
		// sc.close();
	}

	private boolean insertPerson(PersonDTO person) throws PhmException, ParseException {
		Connection con = new ConnectionManager().getConnection();
		return InsertQueries.insertPerson(con, person);
	}

}
