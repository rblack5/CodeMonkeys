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

			UpdatePost u = new UpdatePost();
			u.updatePost(postID, postTitle, postBody);
			
			// session.removeAttribute("currentPost");
			
		} catch (NumberFormatException e) {
			errorMessage = "Invalid Error";
		}
		
		PostModel newModel = new PostModel();
		newModel = sessionPost;
		newModel.setBody(postBody);
		newModel.setTitle(postTitle);
		req.setAttribute("post", sessionPost);
		
		// This breaks shit, be careful
		//req.setAttribute("editProfile", model);
		
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/post.jsp").forward(req, resp);
	}
}
