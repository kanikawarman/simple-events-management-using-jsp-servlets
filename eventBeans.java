package websys;

import java.io.*;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.Statement;

import java.sql.SQLException;

public class eventBeans implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String eventname;
	private String username;
	private String date;
	private String time;
	private String location;
	private String description;
//	private Connection con = null;
//	private Statement st = null;

	public eventBeans() {

	/*	try {

			// Load the database driver

			Class.forName("com.mysql.jdbc.Driver");

			// Get a Connection to the database

			con = DriverManager.getConnection("jdbc:mysql://localhost/websys", "root", "Mysqlpwd");

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} */

	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the event_name
	 */
	public String getEventname() {
		return eventname;
	}

	/**
	 * @param event_name the event_name to set
	 */
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/*public void insert()

	{

		try

		{

			String sql = "insert into event_info(username,eventname,location,description,date,time) values('" + username
					+ "','" + eventname + "','" + location + "','" + description + "','" + date + "','" + time + "')";

			st = con.createStatement();

			st.executeUpdate(sql);

			st.close();

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}*/

}
