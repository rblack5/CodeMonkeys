package project_database.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project_database.controller.PostController;
import project_database.database.UpdatePost;
import project_database.model.PostModel;

public class EditPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("EditPost Servlet: doGet");
		Boolean wentThruNormal = true;
		HttpSession session = req.getSession();
		PostModel sessionPost = new PostModel();
		sessionPost = (PostModel) session.getAttribute("currentPost");
		
		Object sessionAdminStatus = session.getAttribute("adminStatus");
		System.out.println("SessionAdminStatus is: " + sessionAdminStatus);

		// FOR SESSIONS IT IS GET ATTRIBUTE, FOR REQUESTS IT IS GET PARAMETER!!!
		String passedPostID = req.getParameter("postID");
		System.out.println("Passed post ID: "+ passedPostID);
		double x = 0;
		System.out.println(x + " => Doing checks");
		try {
			String sessionUserID = (String) session.getAttribute("userID");
			if (passedPostID == null)
				x = 3 / 0; // cause an error by dividing by zero
			int intSessionUserID = Integer.parseInt(sessionUserID);
			// keep this here because it fails if the person is not at least logged in 

			// check if authors do not match, and if the person is not an admin
			if ((intSessionUserID != sessionPost.getUserID()) && (sessionAdminStatus.equals("null") || sessionAdminStatus.equals("false"))) {
				x = 3 / 0; // cause an error by dividing by zero
			}
		}
		catch (Exception e) {
			wentThruNormal = false;
			PostController postController = new PostController();
			List <PostModel> posts = postController.getAllPosts();
			req.setAttribute("message", "You cannot edit a post if it is not yours!");
			req.setAttribute("posts", posts);
		}
		
		if (wentThruNormal) {
			session.setAttribute("editTitle", sessionPost.getTitle());
			session.setAttribute("editBody", sessionPost.getBody());
			req.getRequestDispatcher("/_view/editPost.jsp").forward(req, resp);
		}
		else {
			req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("EditPost Servlet: doPost");
		
		HttpSession session = req.getSession();
		PostModel sessionPost = new PostModel();
		String postBody = "";
		String postTitle = "";
		Boolean invalidInfo = false;
		sessionPost = (PostModel) session.getAttribute("currentPost");

		// holds the error message text, if there is any
		String errorMessage = null;
		
		// decode POSTed form parameters
		try {
			
			int postID = sessionPost.getPostID();
			System.out.println("The correct postID: " + postID);
			
			// Obtain the name,bio from the doGet
			postTitle = req.getParameter("title");
			System.out.println("UPDATED TITLE: " + postTitle);
			postBody = req.getParameter("body");
			System.out.println("UPDATED BODY: " + postBody);
			
			if (postTitle.contains("\"")) {
				errorMessage = "No quotes allowed in title!";
				System.out.println("Invalid Fields");
				invalidInfo = true;
			}
			
			if (postBody.contains("\"")) {
				errorMessage = "No quotes allowed in body!";
				System.out.println("Invalid Fields");
				invalidInfo = true;
			}

			// check for errors in the form data (error message is not yet implemented)
			if (postTitle == null || postBody == null) {
				errorMessage = "Please enter required fields";
				invalidInfo = true;
				
			}
			else if (postTitle.length() > 70 || postBody.length() > 3000) {
				errorMessage = "Title cannot be longer than 70 characters, and the body cannot be longer than 3000 characters";
				invalidInfo = true;
			}
			else if (postTitle.length() < 1 || postBody.length() < 1) {
				errorMessage = "Posts must have a title and body";
				invalidInfo = true;
			}
			
			if (!invalidInfo) {
				UpdatePost u = new UpdatePost();
				u.updatePost(postID, postTitle, postBody);
			}
			
			// session.removeAttribute("currentPost");
			
		} catch (NumberFormatException e) {
			errorMessage = "Invalid Error";
		}
		
		if (!invalidInfo) {
			PostModel newModel = new PostModel();
			newModel = sessionPost;
			newModel.setBody(postBody);
			newModel.setTitle(postTitle);
			req.setAttribute("post", sessionPost);
			
			// This breaks shit, be careful
			//req.setAttribute("editProfile", model);
			
			// this adds the errorMessage text and the result to the response
			req.setAttribute("errorMessage", errorMessage);
			session.removeAttribute("editTitle");
			session.removeAttribute("editBody");
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/post.jsp").forward(req, resp);
		}
		else {
			// set the attribute named "create" to return the model
			// req.setAttribute("create", model);
			
			// this adds the errorMessage text and the result to the response
			req.setAttribute("errorMessage", errorMessage);
			
			// saving what the user put incase it failed
			req.setAttribute("postTitle", postTitle);
			req.setAttribute("postBody", postBody);
			
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/editPost.jsp").forward(req, resp);
		}
	}
}
