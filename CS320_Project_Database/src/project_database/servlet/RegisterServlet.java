package project_database.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project_database.controller.RegisterController;
import project_database.model.RegisterModel;

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
		
		// Create the model
		RegisterModel model = new RegisterModel();
		
		// decode POSTed form parameters
		try {
			// Obtain the email and password from the doGet
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String password2 = req.getParameter("password2");

			// check for errors in the form data (error message is not yet implemented)
			if (email == null || password == null || password2 == null) {
				errorMessage = "Please enter the required fields";
			}
			// otherwise, data is good
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
				
				if(!password.equals(password2)) {
					errorMessage = "Passwords no not match";
					System.out.println("Passwords do not match");
				}else {
					// send the values to the model
					model.setPassword(password);
					model.setPassword2(password2);
					model.setEmail(email);
					
					RegisterController controller = new RegisterController();
					controller.setModel(model);
					
					// Output the email and password to the console to verify that the program has reached this point.
					System.out.println("Email: " + email);
					System.out.println("Password: " + password);
					System.out.println("Password2: " + password2);
				}
				
				
				
				
				
				
			}
			
		// This part is probably unnecessary but I am keeping it to help us write catch methods
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
		}
		
		
		// set the attribute named "register" to return the model
		req.setAttribute("register", model);
		
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/register.jsp").forward(req, resp);
	}
	
}
