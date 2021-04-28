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
		
		System.out.println("EditProfile Servlet: doGet");
		
		HttpSession session = req.getSession();
		PostModel sessionPost = new PostModel();
		sessionPost = (PostModel) session.getAttribute("currentPost");
		
//		if (sessionPost.getUsername() == null) {
		req.getRequestDispatcher("/_view/editPost.jsp").forward(req, resp);
//		}
//		else {
//			PostController postController = new PostController();
//			List <PostModel> posts = postController.getAllPosts();
//			
//			req.setAttribute("posts", posts);
//			req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
//		}

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
