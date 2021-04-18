package project_database.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project_database.database.*;
import project_database.controller.LoginController;
import project_database.controller.PostController;
import project_database.model.PostModel;
import project_database.model.UserModel;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// test
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
		
		PostController postController = new PostController();
		List <PostModel> posts = postController.getAllPosts();
		
		// Create the model
		UserModel model = new UserModel();
		
		// Create the controller
		LoginController controller = new LoginController();
		
		// decode POSTed form parameters
		try {
			// Obtain the email and password from the doGet
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			// check for errors in the form data (error message is not yet implemented)
			if (username.equals("") || password.equals("")) {
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
				model.setUsername(username);
				
				if(controller.checkLogIn(model) == false) {
					errorMessage = "Incorrect account information, please try again.";
				}
				else {
					// Login was successful, now we should create a session so that the rest
					// of the website knows that the person is logged in
					HttpSession session = req.getSession();
					session.setAttribute("username", model.getUsername());
					
					// We are doing this to find what the userID of the user is, and setting
					// the session attribute so that we can use that userID throughout the site.
					// Also need to convert it to a string so that it can be worked with easier.
					FindMatchingUserByUsername g = new FindMatchingUserByUsername();
					UserModel user = g.findMatchingUserByUserID(model.getUsername());
					int ID = user.getUserID();
					String StringID = String.valueOf(ID);
					System.out.println("Login servlet userID is now:" + StringID);
					session.setAttribute("userID", StringID);
					
					loggedInMessage = "Welcome, " + model.getUsername();
				}
				
			}
			
		// This part is probably unnecessary but I am keeping it to help us write catch methods
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
		}
		
		if (controller.checkLogIn(model) == false) {
			// set the attribute named "login" to return the model
			req.setAttribute("login", model);
			
			req.setAttribute("posts", posts);
			
			// this adds the errorMessage text and the result to the response
			req.setAttribute("errorMessage", errorMessage);
			
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else {
			req.setAttribute("login", model);
			
			req.setAttribute("posts", posts);
			
			req.setAttribute("loggedInMessage", loggedInMessage);
			req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
		}
	}
	
}
