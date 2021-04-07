package project_database.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project_database.controller.PostController;
import project_database.model.PostModel;

public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Servlet: doGet");
		
		req.getRequestDispatcher("/_view/create.jsp").forward(req, resp);
	}
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Servlet: doPost");
		

		// holds the error message text, if there is any
		String errorMessage = null;
		
		// Create the model
		PostModel model = new PostModel();
		
		// decode POSTed form parameters
		try {
			// Obtain the email and password from the doGet
			String title = req.getParameter("title");
			String body = req.getParameter("body");
			
			System.out.println("Title after getParameter: " + title);
			System.out.println("Body after getParameter: " + body);

			// check for errors in the form data (error message is not yet implemented)
			if (title == null || body == null) {
				errorMessage = "Please enter required fields";
			}else {
				// otherwise, data is good
				// must create the controller each time, since it doesn't persist between POSTs
				// the view does not alter data, only controller methods should be used for that
				// thus, always call a controller method to operate on the data
			
				// Send the values to the model
				model.setTitle(title);
				model.setBody(body);
				
				PostController controller = new PostController();
				controller.createPost(model);
			}
			
		// This part is probably unnecessary but I am keeping it to help us write catch methods
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
		}
		
		
		// set the attribute named "create" to return the model
		req.setAttribute("create", model);
		
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/create.jsp").forward(req, resp);
	}
}
