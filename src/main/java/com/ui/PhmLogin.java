package com.ui;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import com.database.ConnectionManager;
import com.database.SelectQueries;
import com.exception.PhmException;
import com.model.PersonDTO;
import com.util.StringsUtil;

/**
 * @author Sumit
 *
 */
public class PhmLogin {

	/**
	 * Method to login to database. This method will ask for the userName
	 * (=unityID) and password (=unity password, encrypted))
	 * 
	 * @param username
	 *            the userName
	 * @param password
	 *            the encrypted password
	 * @throws PhmException
	 *             If some error occurs
	 * @throws SQLException
	 */
	public PersonDTO doLogin(String username, String password) throws PhmException, SQLException {
		// TODO: password encryption
		Connection con = new ConnectionManager().getConnection();
		PersonDTO person = SelectQueries.getLoginPerson(username, password, con);
		if (person == null) {
			System.out.println("Failed to Login!");
		} else {
			// login is successful, call the stored proc.
			CallableStatement cStmt = con.prepareCall("{call check_freq(?)}");
			cStmt.setString(1, person.getPersonId());
			cStmt.executeQuery();
			ResultSet rs = cStmt.getResultSet();
			cStmt.close();
		}
		con.close();
		return person;
	}

	public void showLoginScreen() throws PhmException, ParseException, SQLException, IOException {
		Scanner sc = new Scanner(System.in);
		PersonDTO person = null;
		boolean flag = true;
		while (flag) {
			System.out.println(StringsUtil.LOGIN_MESSAGE);
			System.out.println("Please Login: ");
			System.out.println("Username:");
			String userName = sc.nextLine();
			System.out.println("Password: ");
			// Process p = Runtime.getRuntime().exec("stty -echo");
			String password = sc.nextLine();
			// p = Runtime.getRuntime().exec("stty echo");
			person = doLogin(userName, password);
			if (person == null) {
				System.out.println("Incorrect Login Credentials");
				System.out.println("Choose Option: ");
				System.out.println("1. Login Again");
				System.out.println("2. Back");
				int option = Integer.valueOf(sc.nextLine());
				if (option == 2) {
					flag = false;
					break;
				}
			} else {
				System.out.println("Login Succesfull");
				flag = false;
			}
		}
		if (null != person) {
			UserScreen user = new UserScreen();
			user.showUserScreen(person);
		}
	}

}