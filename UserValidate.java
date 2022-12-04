package websys;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class UserValidate
 */
public class UserValidate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserValidate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String strAction = request.getParameter("action");

		if (strAction != null && !strAction.equals("")) {
			if (strAction.equals("Login")) {
				validateLogin(request, response);
			} else if (strAction.equals("Register")) {
				addUser(request, response);
			} else if (strAction.equals("Submit")) {
				submitEvent(request, response);
			} else if (strAction.equals("Delete Event!")) {
				deleteEvent(request, response);
			} else if (strAction.equals("Yes, Attend")) {
				updateCount(request, response);
			} else if (strAction.equals("Sorry, No!")) {
				updateCount(request, response);
			} else if (strAction.equals("Reset")) {
				resetCount(request, response);
			}
		}
	}

	private void resetCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// reset the counts to 0 for the event
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String session_user_name = (String) session.getAttribute("username");
		String username = request.getParameter("username").trim();
		String eventname = request.getParameter("eventname").trim();
		
		if (session_user_name.equals(username.trim())) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				// load the JDBC driver class
				Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/websys", "root",
						"Mysqlpwd");
				PreparedStatement statement = (PreparedStatement) con.prepareStatement(
						"update event_info set yes_count = 0, no_count = 0 where trim(username) = ? AND trim(eventname) = ?");
				statement.setString(1, username);
				statement.setString(2, eventname);
				statement.executeUpdate();
				statement.close();
				con.close();

				// After updating count, forward to display page
				RequestDispatcher rd = request.getRequestDispatcher("displayCount");
				rd.forward(request, response);

			} catch (SQLException se) {
				// Handle errors for JDBC
				se.printStackTrace();
			} catch (Exception e) {
				// Handle errors for Class.forName
				e.printStackTrace();
			} finally {
			} // end try
		}
			else {
				out.print("<html><head>");
				out.print(
						"<script type=\"text/javascript\">confirm(\"You are not authorized to reset count of other's event!\");");
				out.print("window.location.href = \'displayCount\'");
				out.print("</script></head><body></body></html>");
			}		
	}

	private void updateCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// update the yes_count in the DB for the event
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String session_user_name = (String) session.getAttribute("username");
		String username = request.getParameter("username").trim();
		String eventname = request.getParameter("eventname").trim();
		String strAction = request.getParameter("action");
		String sql;

		if (session_user_name.equals(username.trim())) {
				out.print("<html><head>");
				out.print(
						"<script type=\"text/javascript\">confirm(\"You are not authorized to respond to your own event!\");");
				out.print("window.location.href = \'ViewEvents\'");
				out.print("</script></head><body></body></html>");
		} 
		else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				// load the JDBC driver class
				Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/websys", "root",
						"Mysqlpwd");
				if (strAction.equals("Yes, Attend")) {
					sql = "update event_info set yes_count = yes_count + 1  where trim(username) = ? AND trim(eventname) = ?";
				} else {
					sql = "update event_info set no_count = no_count + 1  where trim(username) = ? AND trim(eventname) = ?";
				} 
				PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
				statement.setString(1, username);
				statement.setString(2, eventname);
				statement.executeUpdate();
				statement.close();
				con.close();

				// After deleting, forward to display page
				RequestDispatcher rd = request.getRequestDispatcher("displayCount");
				rd.forward(request, response);

			} catch (SQLException se) {
				// Handle errors for JDBC
				se.printStackTrace();
			} catch (Exception e) {
				// Handle errors for Class.forName
				e.printStackTrace();
			} finally {
			} // end try
			}
		}

	private void deleteEvent(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// delete the event from the DB. Make sure the user logged in can only delete
		// his events.
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String session_user_name = (String) session.getAttribute("username");
		String username = request.getParameter("username").trim();
		String eventname = request.getParameter("eventname").trim();
		String location = request.getParameter("location").trim();

		if (session_user_name.equals(username.trim())) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				// load the JDBC driver class
				Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/websys", "root",
						"Mysqlpwd");
				PreparedStatement statement = (PreparedStatement) con.prepareStatement(
						"delete from event_info where trim(username) = ? AND trim(eventname) = ? AND trim(location) = ?");
				statement.setString(1, username);
				statement.setString(2, eventname);
				statement.setString(3, location);
				statement.executeUpdate();
				// con.commit();
				statement.close();
				con.close();

				// After deleting, forward to display page
				RequestDispatcher rd = request.getRequestDispatcher("ViewEvents");
				rd.forward(request, response);

			} catch (SQLException se) {
				// Handle errors for JDBC
				se.printStackTrace();
			} catch (Exception e) {
				// Handle errors for Class.forName
				e.printStackTrace();
			} finally {
			} // end try
		} else {
			out.print("<html><head>");
			out.print("<script type=\"text/javascript\">confirm(\"You are not authorized to delete this event!\");");
			out.print("window.location.href = \'ViewEvents\'");
			out.print("</script></head><body></body></html>");
		}
	}

	private void submitEvent(HttpServletRequest request, HttpServletResponse response) {
		// save the event details in the DB
		HttpSession session = request.getSession();
		eventBeans eb = (eventBeans) session.getAttribute("eventbean");
		String user_name = eb.getUsername();
		String event_name = eb.getEventname();
		String date = eb.getDate();
		String time = eb.getTime();
		String location = eb.getLocation();
		String desc = eb.getDescription();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// load the JDBC driver class
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/websys", "root",
					"Mysqlpwd");
			PreparedStatement statement = (PreparedStatement) con.prepareStatement(
					"insert into event_info (username, eventname, date, time, location, description) values (?, ?, ?, ?, ?, ?);");
			statement.setString(1, user_name);
			statement.setString(2, event_name);
			statement.setString(3, date);
			statement.setString(4, time);
			statement.setString(5, location);
			statement.setString(6, desc);
			statement.executeUpdate();
			statement.close();
			con.close();

			// After submitting, forward to display page
			RequestDispatcher rd = request.getRequestDispatcher("displayCount");
			rd.forward(request, response);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
		} // end try
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) {
		// get the details from the use registration form and submit those details in DB
		String user_name = request.getParameter("uname");
		String user_pwd = request.getParameter("password");
		// session
		HttpSession session = request.getSession();
		session.setAttribute("username", user_name);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// load the JDBC driver class
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/websys", "root",
					"Mysqlpwd");
			PreparedStatement statement = (PreparedStatement) con
					.prepareStatement("insert into user_info (username, password) values (?, ?);");
			statement.setString(1, user_name);
			statement.setString(2, user_pwd);
			statement.executeUpdate();
			RequestDispatcher rd = request.getRequestDispatcher("ViewEvents");
			rd.forward(request, response);

			statement.close();
			con.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
		} // end try

	}

	private void validateLogin(HttpServletRequest request, HttpServletResponse response) {
		// validate the login details entered by the user
		String user_name = request.getParameter("username");
		String user_pwd = request.getParameter("pwd");
		// session
		HttpSession session = request.getSession();
		session.setAttribute("username", user_name);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// load the JDBC driver class
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/websys", "root",
					"Mysqlpwd");
			PreparedStatement statement = (PreparedStatement) con
					.prepareStatement("select password from user_info where username = ?;");
			statement.setString(1, user_name);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				if (user_pwd.equals(rs.getString(1))) {
					RequestDispatcher rd = request.getRequestDispatcher("ViewEvents");
					rd.forward(request, response);
				} else {
					PrintWriter out = response.getWriter();
					out.print("<html><head>");
					out.print("<script type=\"text/javascript\">confirm(\"Invalid Login, Try Again\");");
					out.print("window.location.href = \'eventLogin.jsp\'");
					out.print("</script></head><body></body></html>");

				}
			}
			rs.close();
			statement.close();
			con.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
		} // end try

	}

}
