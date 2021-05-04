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
import project_database.database.DeleteUser;
import project_database.model.PostModel;
import project_database.model.UserModel;

public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Delete Servlet: doGet");
		
		HttpSession session = req.getSession();
		String profileUserID = req.getParameter("userID");
		UserModel currentUser = (UserModel) session.getAttribute("user");
		
		int intUserID = Integer.parseInt(profileUserID);
		
		DeleteUser d = new DeleteUser();
		
		d.deleteUser(intUserID);
		
		if(currentUser.getUserID() == intUserID) {
		session.setAttribute("LoggedIn", false);
		session.setAttribute("user", null);
		session.setAttribute("userID", null);
		session.setAttribute("intUserID", null);
		session.setAttribute("adminStatus", false);
		
		req.getRequestDispatcher("/_view/logout.jsp").forward(req, resp);
		}
		else {
		PostController controller = new PostController();
		List <PostModel> posts = controller.getAllPosts();
		
		req.setAttribute("posts", posts);
		req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("DeletePostServlet: doPost");
		
	}
}
