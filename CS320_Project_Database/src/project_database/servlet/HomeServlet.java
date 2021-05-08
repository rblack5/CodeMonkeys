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

import project_database.controller.PostController;
import project_database.database.FindMatchingUserByUserID;
import project_database.model.PostModel;
import project_database.model.UserModel;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Home Servlet: doGet");
		HttpSession session = req.getSession();
		List <PostModel> posts = new ArrayList<PostModel>();
		PostController controller = new PostController();
		
		if (session.getAttribute("loggedInMessage") != null) {
			System.out.println("Logged in message is not null!");
			req.setAttribute("message", session.getAttribute("loggedInMessage"));
		}
		
		if (session.getAttribute("posts") == null) {
			posts = controller.getAllPosts();
			Collections.reverse(posts);
		}
		else {
			posts = (List<PostModel>) session.getAttribute("posts");
			Collections.reverse(posts);
			if (posts.isEmpty()) {
				req.setAttribute("message", "No posts matched your search!");
			}
		}
		
		int page = 0;
		int postsPerPage = 6;
		// System.out.println(posts.size());
		int numPages = posts.size() / postsPerPage;
		int remainder = posts.size() % postsPerPage;
		// System.out.println(remainder);
		if (remainder == 0)
			numPages = numPages - 1;

		if (posts.size() > postsPerPage) {
			
			if(req.getParameter("page") != null) {
	            page = Integer.parseInt(req.getParameter("page"));
	            System.out.println("Page is: " + page);
			}
					
			List <PostModel> pagePosts = new ArrayList<PostModel>();
			
			// use a try statement here because unless the number is perfectly divisable by the number of posts per page, 
			// this is expected to fail
			try {
				for (int x = 0; x < postsPerPage; ++x) {
					pagePosts.add(posts.get(x+(page*postsPerPage)));
				}
			}
			catch (Exception e) {
				
			}
	
			req.setAttribute("posts", pagePosts);
		}
		else {
			req.setAttribute("posts", posts);
		}
		
		// need to do this to avoid null exception for non-logged in users

		if (session.getAttribute("userID") == null) {
			int userID = 0;
			req.setAttribute("userID", userID);
		}
		
		// cleaning things up
		// session.removeAttribute("posts");
		session.removeAttribute("pageNumberPlus");
		session.removeAttribute("pageNumberMinus");
		session.removeAttribute("loggedInMessage");
		
		if (page+1 <= numPages)
			session.setAttribute("pageNumberPlus", page+1);
		
		if (page-1 >= 0)
			session.setAttribute("pageNumberMinus", page-1);
		
		req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
	}
	
}
