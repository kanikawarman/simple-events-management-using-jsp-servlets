package websys;

import jakarta.servlet.ServletException;
import java.sql.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class ViewEvents
 */
public class ViewEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewEvents() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
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
		// validate user login and confirm the entry in DB if new register
		PrintWriter pw = response.getWriter();
		
		// set content type
		response.setContentType("text/html");

		pw.println("<html>");
		pw.println("<head><title>Display all Events</title></head>");
		pw.println("<link rel= \"stylesheet\" href = \"invitationStyle.css\">");
		pw.println("<script type=\"text/javascript\" src=\"invitationValidatorJSP.js\">");
		pw.println("</script>");
		pw.println("<body>");
		pw.println("<h1> Registered Events </h1>");
		pw.println("<table>");
		pw.println("<tr>");
		pw.println("<th><p id=\"one\" style = \"position:relative; top: 30px\">Upcoming events</p></th>");
		pw.println("<th><p id=\"one\" style = \"position:relative; right: -760px; top: 30px\"><a href = \"createEvent.jsp\">Create event</a></p></th>");
		pw.println("<th><p id=\"one\" style = \"position:relative; right: -740px; top: 30px\"><a href = \"eventLogin.jsp\">Logout</a></p></th>");
		pw.println("</tr>");
		pw.println("</table>");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// load the JDBC driver class
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/websys", "root",
					"Mysqlpwd");
			PreparedStatement statement = (PreparedStatement) con.prepareStatement("Select * from event_info where eventname is not null");
			/* sql structure to select instances from the table */
			ResultSet rs = statement.executeQuery();
			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				String event_name = rs.getString("eventname");
				String user_name = rs.getString("username");
				String date = rs.getString("date");
				String time = rs.getString("time");
				String location = rs.getString("location");
				String description = rs.getString("description");

				// Display values
				// pw.println("<form>");
				pw.println(
						"<form name=\"ViewEvents\" onSubmit=\"displayCount\" method=\"post\" action = \"UserValidate\" ");

				pw.println("<div>");
				pw.println("<label><b>User Name: </b></label>" + user_name + "<br>");
				pw.println("<input type=\"hidden\" name = \"username\" value =' " + user_name + " '>");
				pw.println("</div>");

				pw.println("<div>");
				pw.println("<label><b>Event Name: </b></label>" + event_name + "<br>");
				pw.println("<input type=\"hidden\" name = \"eventname\" value =' " + event_name + " '>");
				pw.println("</div>");

				pw.println("<div>");
				pw.println("<label><b>Event Date: </b></label>" + date + "<br>");
				pw.println("<input type=\"hidden\" name = \"date\" value =" + date + ">");
				pw.println("</div>");

				pw.println("<div>");
				pw.println("<label><b>Event Time: </b></label>" + time + "<br>");
				pw.println("<input type=\"hidden\" name = \"time\" value =" + time + ">");
				pw.println("</div>");

				pw.println("<div>");
				pw.println("<label><b>Event Location: </b></label>" + location + "<br>");
				pw.println("<input type=\"hidden\" name = \"location\" value =' " + location + " '>");
				pw.println("</div>");

				pw.println("<div>");
				pw.println("<label><b>Event Description: </b></label>" + description + "<br>");
				pw.println("<input type=\"hidden\" name = \"description\" value =' " + description + " '>");
				pw.println("</div>");

				pw.println("<p id=\"two\">Confirm your presence by clicking on one of the\n"
						+ "		   			below buttons.</p>");
				pw.println(
						"<input type=\"submit\" name = \"action\" style='margin-right: 16px' value=\"Yes, Attend\" >");
				pw.println(
						"<input type=\"submit\" name = \"action\"  value=\"Sorry, No!\" style='margin-right: 16px'>");
				pw.println(
						"<input type=\"submit\" name = \"action\"  value=\"Delete Event!\" >");

				pw.println("</form>");
			}//end of while

			// Clean-up environment
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

		pw.println("</body></html>");
		pw.close();

	}
}