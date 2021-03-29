package project_database.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project_database.controller.LoginController;
import project_database.model.LoginModel;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doGet");
		
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doPost");
		

		// holds the error message text, if there is any
		String errorMessage = null;
		String loggedInMessage = null;
		
		// Create the model
		LoginModel model = new LoginModel();
		
		// decode POSTed form parameters
		try {
			// Obtain the email and password from the doGet
			String email = req.getParameter("email");
			String password = req.getParameter("password");

			// check for errors in the form data (error message is not yet implemented)
			if (email == "" || password == "") {
				errorMessage = "Please enter the specified information";
			}
			// otherwise, data is good
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
				
				// send the values to the model
				// System.out.println(password);
				
				model.setPassword(password);
				model.setUsername(email);
				
				LoginController controller = new LoginController();
				controller.setModel(model);
				
				model.setLoggedIn(controller.checkLogIn(model));
				
				System.out.println("IsLoggedIn: " + model.getLoggedIn() );
				
				if(model.getLoggedIn() == false) {
					errorMessage = "Incorrect account information, please try again.";
				}
				else {
					// Login was successful, now we should create a session so that the rest
					// of the website knows that the person is logged in
					HttpSession session = req.getSession();
					session.setAttribute("username", model.getUsername());
					
					loggedInMessage = "Welcome, " + model.getUsername();
				}
				
				// Output the email and password to the console to verify that the program has reached this point.
				System.out.println("Email: " + email);
				System.out.println("Password: " + password);
			}
			
		// This part is probably unnecessary but I am keeping it to help us write catch methods
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
		}
		
		if (model.getLoggedIn() == false) {
			// set the attribute named "login" to return the model
			req.setAttribute("login", model);
			
			// this adds the errorMessage text and the result to the response
			req.setAttribute("errorMessage", errorMessage);
			
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			req.setAttribute("login", model);
			
			req.setAttribute("loggedInMessage", loggedInMessage);
			req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
		}
	}
	
}
