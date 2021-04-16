package project_database.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project_database.controller.LoginController;
import project_database.model.UserModel;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Register Servlet: doGet");
		
		req.getRequestDispatcher("/_view/register.jsp").forward(req, resp);
	}
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Register Servlet: doPost");
		

		// holds the error message text, if there is any
		String errorMessage = null;
		Boolean passedTests = true;
		String username = "none";
		String message = "";
		
		// Create the model
		System.out.println("Creating model....");
		
		UserModel model = new UserModel();
		
		// decode POSTed form parameters
		try {
			// Obtain the email and password from the doGet
			username = req.getParameter("username");
			String password = req.getParameter("password");
			String password2 = req.getParameter("password2");
			
			
			
			// check for errors in the form data (error message is not yet implemented)
			if (username == null || password == null || password2 == null) {
				errorMessage = "Please enter the required fields";
				System.out.println("Missing required fields");
				System.out.println(username);
				System.out.println(password);
				System.out.println(password2);
				passedTests = false;
			}
			
			// otherwise, data is good
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
				System.out.println("Checking matching passwords...");
				
				if(!password.equals(password2)) {
					errorMessage = "Passwords do not match";
					System.out.println("Passwords do not match");
					passedTests = false;
				}else {
					// send the values to the model
					model.setPassword(password);
					model.setUsername(username);
					
					System.out.println("Creating controller....");
					LoginController controller = new LoginController();
					message = "Account " + username + " successfully created, now time to login!";
					System.out.println(message);
					controller.setModel(model);
					controller.createAccount();
					
				}
			}
			
		// This part is probably unnecessary but I am keeping it to help us write catch methods
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
		}
		
		
		if (!passedTests) {
			// set the attribute named "register" to return the model
			req.setAttribute("register", model);
			
			// this adds the errorMessage text and the result to the response
			req.setAttribute("errorMessage", errorMessage);
			
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/register.jsp").forward(req, resp);
		}
		else if (passedTests) {
			// set the attribute named "register" to return the model
			req.setAttribute("register", model);
			req.setAttribute("message", message);
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
	}
	
}
