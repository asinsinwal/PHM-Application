/**
 * 
 */
package com.database;

import java.sql.Connection;
import java.sql.DriverManager;

import com.exception.PhmException;
import com.util.SqlConfig;

/**
 * @author Sumit
 *
 */
public class ConnectionManager {

	private Connection connection;
	private String username = SqlConfig.getValue("jdbc.username");
	private String password = SqlConfig.getValue("jdbc.password");

	public Connection getConnection() throws PhmException {
		try {
			Class.forName(SqlConfig.getValue("jdbc.driver"));
			connection = DriverManager.getConnection(SqlConfig.getValue("jdbc.url"), username, password);
		} catch (Exception e) {
			System.out.println("Failed to get Database Connection.");
			throw new PhmException("Failed to get Database Connection.");
		}
		return connection;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
