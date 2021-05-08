package project_database.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project_database.controller.PostController;
import project_database.database.DeletePost;
import project_database.model.PostModel;

public class DeletePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Delete Servlet: doGet");
		
		String postID = req.getParameter("postID");
		
		int postIDInt = Integer.parseInt(postID);
		
		DeletePost d = new DeletePost();
		
		d.deletePost(postIDInt);
		
		resp.sendRedirect("/project_database/home");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("DeletePostServlet: doPost");
		
	}
}
