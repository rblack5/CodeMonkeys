package project_database.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project_database.controller.ProfileController;
import project_database.model.PostModel;
import project_database.model.UserModel;

public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Profile Servlet: doGet");
		HttpSession session = req.getSession();
		session.removeAttribute("posts");
				
		if (req.getParameter("userID") != null) {
			String userID = req.getParameter("userID");
				
			ProfileController controller = new ProfileController();
			
			if (userID.equals(session.getAttribute("userID"))) {
				System.out.println("User ID's match");
				req.setAttribute("userMatch", true);
			}
			
			int intUserID;
			intUserID = Integer.parseInt(userID);
			System.out.println(intUserID + " ==> userID string as an Int");
			
			UserModel user = controller.findUser(intUserID);
			
			req.setAttribute("user", user);
		}
		
		if (req.getParameter("userID") == null) {
			System.out.println("User ID's match");
			req.setAttribute("userMatch", true);
		}
		
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
	}
}
