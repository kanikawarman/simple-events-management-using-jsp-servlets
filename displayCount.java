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
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import websys.eventBeans;

/**
 * Servlet implementation class displayCount
 */
public class displayCount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public displayCount() {
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

		PrintWriter pw = response.getWriter();

		// set content type
		response.setContentType("text/html");
		
		pw.println("<html>");
		pw.println("<head><title>Display Count Servlet</title></head>");
		pw.println("<link rel= \"stylesheet\" href = \"invitationStyle.css\">");
		pw.println("<body>");
		pw.println("<h1> People Count </h1>");
		pw.println("<table>");
		pw.println("<tr>");
		pw.println("<th><p id=\"one\" style = \"position:relative; top: 30px\">Total Count of the responded people for the events</p></th>");
		pw.println("<th><p id=\"one\" style = \"position:relative; right: -300px; top: 30px\"><a href = \"ViewEvents\">View events</a></p></th>");
		pw.println("<th><p id=\"one\" style = \"position:relative; right: -300px; top: 30px\"><a href = \"createEvent.jsp\">Create event</a></p></th>");
		pw.println("<th><p id=\"one\" style = \"position:relative; right: -300px; top: 30px\"><a href = \"eventLogin.jsp\">Logout</a></p></th>");
		
		pw.println("</tr>");
		pw.println("</table>");
		pw.println("<form name = \"displayCount\" action = \"UserValidate\">");
		pw.println("<table class = \"count\">");
		pw.println("<tr>");
		pw.println("<th class = \"count\" align = \"center\"><b>User Name </b></label></th>");
		pw.println("<th class = \"count\" align = \"center\"><b>Event Name </b></label></th>");
		pw.println("<th class = \"count\" align = \"center\"><b>People Attending </b></label></th>");
		pw.println("<th class = \"count\" align = \"center\"><b>People not Attending </b></label></th>");
		pw.println("<th class = \"count\" align = \"center\"><b>Option </b></label></th>");
		pw.println("</tr>");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// load the JDBC driver class
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/websys", "root",
					"Mysqlpwd");
			PreparedStatement statement = (PreparedStatement) con
					.prepareStatement("Select username, eventname, yes_count, no_count from event_info order by yes_count desc;");
			// sql structure to select instances from the table
			ResultSet rs = statement.executeQuery();

			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				String user_name = rs.getString(1);
				String event_name = rs.getString(2);
				int yesCount = rs.getInt(3);
				int noCount = rs.getInt(4);
				
				pw.println("<form>");
				pw.println("<tr>");
				pw.println("<td class = \"count\">");
				pw.println(user_name);
				pw.println("<input type=\"hidden\" name = \"username\" value =' " + user_name + " '>");
				pw.println("</td>");
				pw.println("<td class = \"count\">");
				pw.println(event_name);
				pw.println("<input type=\"hidden\" name = \"eventname\" value =' " + event_name + " '>");
				pw.println("</td>");
				pw.println("<td class = \"count\">");
				pw.println(yesCount);
				pw.println("</td>");
				pw.println("<td class = \"count\">");
				pw.println(noCount);
				pw.println("</td>");
				pw.println("<td class = \"count\">");
				pw.println("<input  type=\"submit\" value=\"Reset\" name=\"action\">");
				pw.println("</td>");
				pw.println("</tr>");
				pw.println("</form>");
			}

			pw.println("</table>");
			pw.println("</form>");

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