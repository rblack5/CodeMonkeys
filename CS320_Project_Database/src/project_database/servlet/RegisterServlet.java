package project_database.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project_database.controller.LoginController;
import project_database.controller.PostController;
import project_database.database.FindMatchingUserByUsername;
import project_database.database.ViewAllUsers;
import project_database.model.PostModel;
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
		String username = "N/A";
		String password = "N/A";
		String password2 = "N/A";
		String message = "";
		String loggedInMessage = "";
		
		PostController postController = new PostController();
		List <PostModel> posts = postController.getAllPosts();
		
		// Create the model
		System.out.println("Creating model....");
		
		UserModel model = new UserModel();
		
		// decode POSTed form parameters
		try {
			// Obtain the email and password from the doGet
			username = req.getParameter("username");
			password = req.getParameter("password");
			password2 = req.getParameter("password2");
			System.out.println("Username get: " + username);
			System.out.println("Password get: " + password);
			System.out.println("Password2 get: " + password);
			System.out.println("Username get len: " + username.length());
			System.out.println("Password get len: " + password.length());
			System.out.println("Password2 get len: " + password.length());
			
			if (username.length() < 3 || password.length() < 5 || password2.length() < 5) {
				errorMessage = "Usernames must be atleast 3 characters long, and Passwords must be atleast 5 characters long";
				System.out.println("Invalid Fields");
				System.out.println(username);
				System.out.println(password);
				System.out.println(password2);
				passedTests = false;
			}
			
			if (username.length() > 20 || password.length() > 20 || password2.length() > 20) {
				errorMessage = "Usernames and passwords cannot be longer than 20 characters";
				System.out.println("Invalid Fields");
				System.out.println(username);
				System.out.println(password);
				System.out.println(password2);
				passedTests = false;
			}
			
			System.out.println("Checking matching passwords...");
			if(!password.equals(password2)) {
				errorMessage = "Passwords do not match";
				System.out.println("Passwords do not match");
				passedTests = false;
			}
			
			try {
				FindMatchingUserByUsername g = new FindMatchingUserByUsername();
				UserModel user = g.findMatchingUserByUsername(username);
				if (user.getUsername().length() > 1) {
					errorMessage = "Username already exists";
					System.out.println("Username already exists");
					passedTests = false;
				}
			} catch (Exception e) {
				System.out.println("Username does not exist");
			}
			
			
			
			
			// otherwise, data is good
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			if (passedTests) {
				// send the values to the model
				model.setPassword(password);
				model.setUsername(username);
				
				System.out.println("Creating controller....");
				LoginController controller = new LoginController();
				// message = "Account " + username + " successfully created, now time to login!";
				message = "Welcome, " + username;
				// System.out.println(message);
				controller.setModel(model);
				controller.createAccount(model);
				
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
			
			HttpSession session = req.getSession();
			session.setAttribute("registerUsername", username);
			session.setAttribute("registerPassword", password);
			session.setAttribute("registerPassword2", password2);
			
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/register.jsp").forward(req, resp);
		}
		else if (passedTests) {
			// set the attribute named "register" to return the model
			req.setAttribute("register", model);
			req.setAttribute("message", message);
			req.setAttribute("posts", posts);
			// Forward to view to render the result HTML document
			HttpSession session = req.getSession();
			session.removeAttribute("registerUsername");
			session.removeAttribute("registerPassword");
			session.removeAttribute("registerPassword2");
			
			// Login was successful, now we should create a session so that the rest
			// of the website knows that the person is logged in
			session.setAttribute("username", username);
			
			// We are doing this to find what the userID of the user is, and setting
			// the session attribute so that we can use that userID throughout the site.
			// Also need to convert it to a string so that it can be worked with easier.
			FindMatchingUserByUsername g = new FindMatchingUserByUsername();
			UserModel user = g.findMatchingUserByUsername(username);
			int ID = user.getUserID();
			String StringID = String.valueOf(ID);
			System.out.println("Register Servlet userID is now:" + StringID);
			session.setAttribute("userID", StringID);
			
			req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
		}
	}
	
}
