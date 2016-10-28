
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cours;
import entities.Object;
import entities.Section;
import entities.User;

import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

		/****************************************************************************
		 ****************** CREATION OF A NEW USER USING JPA ************************
		 ****************************************************************************/

		// Create one entity user, set its attributes and make it persist
		
		
		//newUser(new entities.User("name test","surename test"));

		//printSections();
		
		//printObjectsFromCourse(18);

		newLearningObject(new entities.Object("New Object",
				"The description of the new Object","a Route...",
				new ArrayList<Cours>()),19);
		
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

	private void newLearningObject(entities.Object newObject, int courseId) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAexample1");
			
			EntityManager em = emf.createEntityManager();

			EntityTransaction et = em.getTransaction();
			et.begin();
			
			Cours curse = em.find(Cours.class,courseId);
			
			newObject.getCourses().add(curse);
			
			curse.getObjects().add(newObject);
			
			em.persist(newObject);
			
			et.commit();
			em.close();
		
	}

	private void newUser(User user) {

		// 1 Create the factory of Entity Manager
				EntityManagerFactory factory = 
						Persistence.createEntityManagerFactory("JPAexample1");

				// 2 Create the Entity Manager
				EntityManager em = factory.createEntityManager();

				// 3 Get one EntityTransaction and start it
				EntityTransaction tx = em.getTransaction();
				tx.begin();
				
				em.persist(user);
		
				// 4 Commmit the transaction
				tx.commit();

				// 5 Close the manager
				em.close();
	}
	
	/**
	 * Print all the names of the sections 
	 */
	private void printSections() {
		System.out.println("######## Printing sections ##########");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAexample1");
		
		EntityManager em = emf.createEntityManager();

		EntityTransaction et = em.getTransaction();
		et.begin();
		// Select * from Section
		TypedQuery<Section> q = em.createQuery("select s from Section s",Section.class);
		
		for(Section s:q.getResultList()){
			System.out.println(s.getName());
		}
		
		et.commit();
		em.close();
	
		
	}
	
	/**
	 * n Print the name of all the objects included in ta course
	 * @param idCourse
	 */
	private void printObjectsFromCourse(int idCourse) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAexample1");
		
		EntityManager em = emf.createEntityManager();

		EntityTransaction et = em.getTransaction();
		et.begin();
		/* select o.name 
		 * from Objects o, CoursesObjects co, Courses c 
		 * where c.id_course = co.id_course and co.id_object = o.id_object and c.id_course = 18;
		
	*/
		TypedQuery<entities.Object> q = em.createQuery("select o from Object o "
								+ "	JOIN o.courses c "
								+ "	WHERE c.idCourse = "+idCourse,entities.Object.class);

		System.out.println(" ##### Printing objects from course "+idCourse+" #######");

		for (entities.Object obj : q.getResultList()){
			
			System.out.println(obj.getName());
		}
		
		et.commit();
		em.close();
	}



	////////////////////////////////////////////////////////////////////////////////////////
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	}
}