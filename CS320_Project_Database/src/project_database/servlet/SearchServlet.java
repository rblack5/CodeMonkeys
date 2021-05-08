package project_database.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project_database.controller.LoginController;
import project_database.controller.PostController;
import project_database.model.PostModel;
import project_database.model.UserModel;
import project_database.persist.DerbyDatabase;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Search Servlet: doGet");
		
		String searcher = req.getParameter("search");
		DerbyDatabase db = new DerbyDatabase();
		List<PostModel> posts = db.searchPosts(searcher);
		Collections.reverse(posts);
//		req.setAttribute("posts", posts);
//		if (posts.isEmpty()) {
//			req.setAttribute("message", "No posts matched your search!");
//		}
//		
		// This is the easy way out
		HttpSession session = req.getSession();
		session.setAttribute("posts", posts);
		
		// doesnt work (below) - only way to get the posts variable across is with sessions
		// resp.setHeader("posts", posts);
		resp.sendRedirect("/project_database/home");
		
		// req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
}
