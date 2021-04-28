package project_database.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project_database.database.UpdatePost;

public class EditPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("EditProfile Servlet: doGet");
		
		req.getRequestDispatcher("/_view/editPost.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("EditPost Servlet: doPost");
		
		HttpSession session = req.getSession();

		// holds the error message text, if there is any
		String errorMessage = null;
		
		// decode POSTed form parameters
		try {
			// Obtain the name,bio from the doGet
			String postTitle = req.getParameter("title");
			System.out.println("UPDATED TITLE: " + postTitle);
			String postBody = req.getParameter("body");
			System.out.println("UPDATED BODY: " + postBody);
			String postIDString = (String) req.getParameter("postID");
			System.out.println("POST ID STRING: " + postIDString);
			int postID = Integer.parseInt(postIDString);
			System.out.println("POST ID: " + postID);
			UpdatePost u = new UpdatePost();
			u.updatePost(postID, postTitle, postBody);
			
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
		}
		
		// This breaks shit, be careful
		//req.setAttribute("editProfile", model);
		
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/editPost.jsp").forward(req, resp);
	}
}
