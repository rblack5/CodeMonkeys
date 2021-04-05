package project_database.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project_database.controller.PostController;
import project_database.model.PostModel;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Home Servlet: doGet");
		
		PostController controller = new PostController();
		List <PostModel> posts = controller.importCSV(); 
		
		req.setAttribute("posts", posts);
		
		
		req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
	}
	
}
