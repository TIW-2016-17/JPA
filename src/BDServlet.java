
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class BDServlet
 */
public class BDServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String DRIVER = "com.mysql.jdbc.Driver";

	private static final String DATABASE = "usersdb";
	private static final String SERVERNAME = "localhost";
	private static final String PORT = "3306";
	private static final String USERNAME = "root"; // complete
	private static final String PASSWORD = "admin"; // complete

	private static final String URL = "jdbc:mysql://" + SERVERNAME + ":" + PORT + "/" + DATABASE;

	////////////////////////////////////////////////////////////////////////////////////////
	public void init() {

		// Lee del contexto de servlet (Sesión a nivel de aplicación)
		//ServletContext context = getServletContext();
	}

	////////////////////////////////////////////////////////////////////////////////////////
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		// Set the Content Type
		res.setContentType("text/html");

		try (PrintWriter out = res.getWriter()) {

			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head><title>BDServlet</title></head>");
			out.println("<body style=\"background-color:#ffff66\">");
			out.println("<h1 style=\"color=:#666600\">Database: User</h1></br>");
			out.println("<form method=\"post\" action=\"" + "\">"); // called by
																	// POST
																	// itself

			try {

				// 1- Load driver
				Class.forName(DRIVER).newInstance();
				// 2- Obtain a Connection object --> con
				// complete
				
				

				try (Connection con = java.sql.DriverManager.getConnection(URL,USERNAME, PASSWORD);) {

					if (con == null) {
						System.out.println("--->UNABLE TO CONNECT TO SERVER:" + SERVERNAME);
					} else {

						// 3- Obtain an Statement object -> st
						try (Statement st = con.createStatement()) {

							// Retrieve users from the ResultSet --> rs
							ResultSet rs = st.executeQuery("select * from users");

							out.println("<p style=\"color:#ff0000\">Users:</p>");
							while (rs.next()) {
								out.println("<p style=\"color:#ff0000\">" + rs.getString("idusers") + " - "
										+ rs.getString("name") + "  " + rs.getString("surename") + "</p>");
							}
						}
					}

				}
			} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e
					) {
				out.println("<p style=\"color:#ff0000\">" + e.getMessage() + "</p>");
			}

			out.println("</form>");
			out.println("</body></html>");

		}
	}

	////////////////////////////////////////////////////////////////////////////////////////
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	}
}