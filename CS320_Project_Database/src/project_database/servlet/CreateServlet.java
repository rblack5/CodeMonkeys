package project_database.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project_database.controller.PostController;
import project_database.model.PostModel;
import project_database.model.UserModel;

public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Servlet: doGet");
		HttpSession session = req.getSession();
		session.removeAttribute("posts");
		
		req.getRequestDispatcher("/_view/create.jsp").forward(req, resp);
	}
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Servlet: doPost");
		

		// holds the error message text, if there is any
		String errorMessage = null;
		Boolean invalidInfo = false;
		String title = "";
		String body = "";
		
		// Create the model
		PostModel model = new PostModel();
		PostModel post = null;
		// decode POSTed form parameters
		try {
			// Obtain the email and password from the doGet
			title = req.getParameter("title");
			body = req.getParameter("body");
			
			System.out.println("Title after getParameter: " + title);
			System.out.println("Body after getParameter: " + body);
			
			if (title.contains("\"")) {
				errorMessage = "No quotes allowed in title!";
				System.out.println("Invalid Fields");
				invalidInfo = true;
			}
			
			if (body.contains("\"")) {
				errorMessage = "No quotes allowed in body!";
				System.out.println("Invalid Fields");
				invalidInfo = true;
			}

			// check for errors in the form data (error message is not yet implemented)
			if (title == null || body == null) {
				errorMessage = "Please enter required fields";
				invalidInfo = true;
				
			}
			
			if (title.length() > 70 || body.length() > 1000) {
				errorMessage = "Title cannot be longer than 70 characters, and the body cannot be longer than 1000 characters";
				invalidInfo = true;
			}
			
			if (title.length() < 1 || body.length() < 1) {
				errorMessage = "Posts must have a title and body";
				invalidInfo = true;
			}
			
			if (!invalidInfo) {
				// otherwise, data is good
				// must create the controller each time, since it doesn't persist between POSTs
				// the view does not alter data, only controller methods should be used for that
				// thus, always call a controller method to operate on the data
			
				// Send the values to the model
				model.setTitle(title);
				model.setBody(body);
				HttpSession session = req.getSession();
				String username = (String) session.getAttribute("username");
				System.out.println("username is: " + username);
				String userID = (String) session.getAttribute("userID");
				System.out.println("userID is: " + userID);
				
				int realUserID = Integer.parseInt(userID);
				
				UserModel user = new UserModel();
				user = (UserModel) session.getAttribute("user");
				
				if (user.getPostTheme().equals("light")) {
					model.setTextStyle("all: unset; color: #444444;");
					model.setBackgroundStyle("background-color: #ffffff;");
					model.setLinkStyle("all: unset; color:blue; cursor:pointer; text-decoration:underline;");
					model.setTitleStyle("font-size: 2.20em; font-weight: bolder; color: #444444;");
				}
				else if (user.getPostTheme().equals("dark")) {
					model.setTextStyle("all: unset; color:white;");
					model.setBackgroundStyle("background-color: #222C2D;");
					model.setLinkStyle("all: unset; color:white; cursor:pointer; text-decoration:underline;");
					model.setTitleStyle("font-size: 2.20em; font-weight: bolder; color:white;");
				}
				else if (user.getPostTheme().equals("fire")) {
					model.setTextStyle("all: unset; color:black;");
					model.setBackgroundStyle("background-image: linear-gradient(orange, red);");
					model.setLinkStyle("all: unset; color:black; cursor:pointer; text-decoration:underline;");
					model.setTitleStyle("font-size: 2.20em; font-weight: bolder; color:black;");
				}
				
				model.setUserID(realUserID);
				
				model.setUsername(username);
				
				System.out.println("model.getUserID =>" + model.getUserID());
				System.out.println("model.getUsername =>" + model.getUsername());
				
				PostController controller = new PostController();
				post = controller.createPost(model);
			}
			
		// This part is probably unnecessary but I am keeping it to help us write catch methods
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
		}
		
		if (invalidInfo) {
			// set the attribute named "create" to return the model
			req.setAttribute("create", model);
			
			// this adds the errorMessage text and the result to the response
			req.setAttribute("errorMessage", errorMessage);
			
			// saving what the user put incase it failed
			req.setAttribute("postTitle", title);
			req.setAttribute("postBody", body);
			
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/create.jsp").forward(req, resp);
		}
		else {
			req.setAttribute("post", post);
			req.setAttribute("errorMessage", errorMessage);
			req.removeAttribute("postTitle");
			req.removeAttribute("postBody");
			req.getRequestDispatcher("/_view/post.jsp").forward(req, resp);
		}
	}
}
