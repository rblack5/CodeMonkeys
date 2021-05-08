package project_database.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project_database.model.PostModel;
import project_database.persist.DerbyDatabase;

public class SortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Sort Servlet: doGet");
		
		String sorter = req.getParameter("sort");
		DerbyDatabase db = new DerbyDatabase();
		List<PostModel> posts = db.viewAllPosts();
		
		if (sorter.equals("newest"))
			Collections.reverse(posts);
		
		req.setAttribute("posts", posts);
		if (posts.isEmpty()) {
			req.setAttribute("message", "There are no posts!");
		}
		
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
