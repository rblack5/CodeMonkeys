package project_database.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import project_database.controller.EditProfileController;
import project_database.database.FindMatchingUserByUserID;
import project_database.database.UpdateUser;
import project_database.model.EditProfileModel;
import project_database.model.UserModel;

public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("EditProfile Servlet: doGet");
		
		req.getRequestDispatcher("/_view/editProfile.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("EditProfile Servlet: doPost");
		
		HttpSession session = req.getSession();

		// holds the error message text, if there is any
		String errorMessage = null;
		
		// Create the model
		EditProfileModel model = new EditProfileModel();
		
		// decode POSTed form parameters
		try {
			// Obtain the name,bio from the doGet
			String username = req.getParameter("username");
			String bio = req.getParameter("bio");
			String password = req.getParameter("password");
			String postTheme = req.getParameter("check");
			
			String userIDString = (String) session.getAttribute("userID");
			int userID = Integer.parseInt(userIDString);
				
			UpdateUser u = new UpdateUser();
			FindMatchingUserByUserID f = new FindMatchingUserByUserID();
			
			u.updateUser(userID, username, password, bio, postTheme);
			
			UserModel user = f.findMatchingUserByUserID(userID);
			
			model.setBio(user.getBio());
			model.setName(user.getUsername());
			model.setPassword(user.getPassword());
			
			session.setAttribute("user", user);
			session.setAttribute("username", user.getUsername());
				
			// Prints name,bio to the console
			System.out.println("Name: " + username);
			System.out.println("Bio: " + bio);
			System.out.println("Password: " + password);
			System.out.println("Theme: " + postTheme);
			
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
		}
		
		// This breaks shit, be careful
		//req.setAttribute("editProfile", model);
		
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/editProfile.jsp").forward(req, resp);
	}
		
}
