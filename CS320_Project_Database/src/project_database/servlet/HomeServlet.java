package project_database.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project_database.controller.PostController;
import project_database.database.FindMatchingUserByUserID;
import project_database.model.PostModel;
import project_database.model.UserModel;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Home Servlet: doGet");
		
		PostController controller = new PostController();
		List <PostModel> posts = controller.getAllPosts();

		req.setAttribute("posts", posts);
		
		// need to do this to avoid null exception for non-logged in users
		HttpSession session = req.getSession();
		if (session.getAttribute("userID") == null) {
			int userID = 0;
			req.setAttribute("userID", userID);
		}
		
		req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
	}
	
}
