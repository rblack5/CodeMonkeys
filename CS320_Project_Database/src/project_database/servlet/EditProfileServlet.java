package project_database.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project_database.controller.EditProfileController;
import project_database.model.EditProfileModel;

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
		

		// holds the error message text, if there is any
		String errorMessage = null;
		
		// Create the model
		EditProfileModel model = new EditProfileModel();
		
		// decode POSTed form parameters
		try {
			// Obtain the name,bio from the doGet
			String name = req.getParameter("name");
			String bio = req.getParameter("bio");
			String password = req.getParameter("password");

			
			// Send the values to the model
			model.setName(name);
			model.setBio(bio);
			model.setPassword(password);
				
			EditProfileController controller = new EditProfileController();
			controller.setModel(model);
				
			// Prints name,bio to the console
			System.out.println("Name: " + name);
			System.out.println("Bio: " + bio);
			System.out.println("Password: " + password);
			
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
		}
		
		
		req.setAttribute("editProfile", model);
		
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/editProfile.jsp").forward(req, resp);
	}
		
}
