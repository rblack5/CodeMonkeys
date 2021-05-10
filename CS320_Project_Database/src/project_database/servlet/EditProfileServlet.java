package project_database.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.mindrot.jbcrypt.BCrypt;

import project_database.controller.EditProfileController;
import project_database.database.FindMatchingUserByUserID;
import project_database.database.FindMatchingUserByUsername;
import project_database.database.UpdateUser;
import project_database.model.EditProfileModel;
import project_database.model.UserModel;

public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("EditProfile Servlet: doGet");
		HttpSession session = req.getSession();
		String name = (String) session.getAttribute("username");
		UserModel user = (UserModel) session.getAttribute("user");
		
		session.setAttribute("editProfileName", name);
		session.setAttribute("editProfileBio", user.getBio());
		
		req.getRequestDispatcher("/_view/editProfile.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("EditProfile Servlet: doPost");
		
		HttpSession session = req.getSession();

		// holds the error message text, if there is any
		String errorMessage = null;
		Boolean passedTests = true;
		
		// Create the model
		EditProfileModel model = new EditProfileModel();
		String username = "";
		String bio = "";
		
		// decode POSTed form parameters
		try {
			// Obtain the name,bio from the doGet
			username = req.getParameter("username");
			bio = req.getParameter("bio");
			String password = req.getParameter("code1");
			String password2 = req.getParameter("password2");
			String postTheme = req.getParameter("postTheme");
			String accountTheme = req.getParameter("accountTheme");
			
			String userIDString = (String) session.getAttribute("userID");
			int userID = Integer.parseInt(userIDString);
			
			if (username.contains("\"") || username.contains("\'")) {
				errorMessage = "No quotes or apostrophes allowed in the username!";
				System.out.println("Invalid Fields");
				System.out.println(username);
				System.out.println(password);
				System.out.println(password2);
				passedTests = false;
			}
			
			if (password.contains("\"") || password.contains("\'")) {
				errorMessage = "No quotes or apostrophes allowed in passwords!";
				System.out.println("Invalid Fields");
				System.out.println(username);
				System.out.println(password);
				System.out.println(password2);
				passedTests = false;
			}
			
			if (bio.contains("\"")) {
				errorMessage = "No quotes or allowed in bio!";
				System.out.println("Invalid Fields");
				passedTests = false;
			}
			
			if (username.length() < 3) {
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
			
			if (bio.length() > 3000) {
				errorMessage = "A bio cannot be longer than 3000 characters";
				System.out.println("Invalid Fields");
				passedTests = false;
			}
			
			if (bio.length() < 1) {
				errorMessage = "A bio must be atleast 1 character";
				System.out.println("Invalid Fields");
				passedTests = false;
			}
			
			
			System.out.println("Checking matching passwords...");
			if (!password.equals(password2)) {
				errorMessage = "Passwords do not match";
				System.out.println("Passwords do not match");
				passedTests = false;
			}
			
			if (!(username.equals((String)session.getAttribute("username")))) {
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
			}
			
			if (passedTests) {
				UpdateUser u = new UpdateUser();
				FindMatchingUserByUserID f = new FindMatchingUserByUserID();
				
				u.updateUser(userID, username, password, password2, bio, postTheme, accountTheme);
				
				UserModel user = f.findMatchingUserByUserID(userID);
				
				model.setBio(user.getBio());
				model.setName(user.getUsername());
				model.setPassword(user.getPassword());
				
				
				session.setAttribute("user", user);
				session.setAttribute("username", user.getUsername());
			}
			// Prints name,bio to the console
			System.out.println("Name: " + username);
			System.out.println("Bio: " + bio);
			System.out.println("Password: " + password);
			System.out.println("Password2: " + password2);
			System.out.println("Theme: " + postTheme);
			
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
		}
		
		// This breaks shit, be careful
		//req.setAttribute("editProfile", model);
		
		if (!passedTests) {
			// this adds the errorMessage text and the result to the response
			req.setAttribute("errorMessage", errorMessage);
			
			session.setAttribute("editProfileName", username);
			session.setAttribute("editProfileBio", bio);
			
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/editProfile.jsp").forward(req, resp);
		}
		else {
			session.removeAttribute("editProfileName");
			session.removeAttribute("editProfileBio");
			
			resp.sendRedirect("/project_database/profile");
		}
	}
		
}
