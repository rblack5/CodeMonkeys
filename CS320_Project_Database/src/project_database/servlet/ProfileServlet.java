package project_database.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project_database.controller.ProfileController;
import project_database.model.PostModel;
import project_database.model.UserModel;

public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Profile Servlet: doGet");
		
		if(req.getParameter("userID") != null) {
		String userID = req.getParameter("userID");
		
		ProfileController controller = new ProfileController();
		
		int intUserID;
		intUserID = Integer.parseInt(userID);
		System.out.println(intUserID + " ==> userID string as an Int");
		
		UserModel user = controller.findUser(intUserID);
		
		req.setAttribute("user", user);
		}
		
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
	}
}
