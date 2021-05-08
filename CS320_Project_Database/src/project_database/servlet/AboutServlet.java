package project_database.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AboutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("About Servlet: doGet");
		// do this to reset the searched posts on the home page
		HttpSession session = req.getSession();
		session.removeAttribute("posts");
		req.getRequestDispatcher("/_view/about.jsp").forward(req, resp);
	}
}
