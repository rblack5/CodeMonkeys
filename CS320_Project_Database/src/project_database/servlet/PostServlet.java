package project_database.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project_database.controller.LoginController;
import project_database.controller.PostController;
import project_database.database.FindMatchingCommentsWithPostID;
import project_database.model.CommentModel;
import project_database.model.PostModel;
import project_database.model.UserModel;

public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Post Servlet: doGet");
		HttpSession session = req.getSession();
		
		String postID = req.getParameter("postID");
		System.out.println(postID);
		
		PostModel model = new PostModel();
		PostController controller = new PostController();
		
		int intPostID;
		intPostID = Integer.parseInt(postID);
		System.out.println(intPostID + " ==> postID string as an Int");
		
		PostModel post = controller.findPost(intPostID);
		
		req.setAttribute("post", post);
		
		List<CommentModel> commentList = new ArrayList<CommentModel>();

		
		FindMatchingCommentsWithPostID g = new FindMatchingCommentsWithPostID();
		commentList = g.findMatchingCommentsWithPostID(intPostID);
		Collections.reverse(commentList);
		session.setAttribute("currentCommentList", commentList);
		// This is the easy way out

		session.setAttribute("currentPost", post);

		
		req.getRequestDispatcher("/_view/post.jsp").forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
}
