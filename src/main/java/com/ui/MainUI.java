/**
 * 
 */
package com.ui;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import com.exception.PhmException;
import com.util.StringsUtil;

/**
 * @author Sumit
 *
 */
public class MainUI {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ParseException, SQLException, IOException {
		showScreen();
	}

	public static void showScreen() throws ParseException, SQLException, IOException {
		try {
			Scanner sc = new Scanner(System.in);
			int input=0;
			boolean flag = true;
			while (flag) {
				sc = new Scanner(System.in);
				System.out.println(StringsUtil.LOGIN_MESSAGE);
				System.out.println("Select an option");
				System.out.println("1. Login");
				System.out.println("2. Create User");
				System.out.println("3. Exit");
				System.out.println("Enter Choice: ");
				input = Integer.valueOf(sc.nextLine());	
				
				switch (input) {
				case 1:
					PhmLogin login = new PhmLogin();
						login.showLoginScreen();
					break;
				case 2:
					NewPhmUser newUser = new NewPhmUser();
					newUser.showScreen();
					break;
				case 3:
					flag = false;
					System.out.println("Thank you for using Phm Application");
					break;
				default:
					System.out.println("Invalid input, try again.!");						
					break;
				}
				
			}
			sc.close();
		} catch (PhmException pe) {
			System.out.println("ERROR" + pe.getMessage());
			pe.printStackTrace();
		}
	}

}
