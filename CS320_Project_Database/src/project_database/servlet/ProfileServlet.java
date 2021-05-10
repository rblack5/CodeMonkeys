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

import project_database.controller.ProfileController;
import project_database.database.FindMatchingPostsByUserID;
import project_database.database.ViewAllPosts;
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
		
		// if the user clicked on a post to get to the profile page
		if (req.getParameter("userID") != null) {
			String userID = req.getParameter("userID");
				
			ProfileController controller = new ProfileController();
			
			if (userID.equals(session.getAttribute("userID"))) {
				System.out.println("User ID's match");
				req.setAttribute("userMatch", true);
			}
			
			// req.setAttribute("userID", req.getParameter("userID"));
			
			int intUserID;
			intUserID = Integer.parseInt(userID);
			System.out.println(intUserID + " ==> userID string as an Int");
			
			UserModel user = controller.findUser(intUserID);
			
			req.setAttribute("user", user);
			
			FindMatchingPostsByUserID g = new FindMatchingPostsByUserID();
			List<PostModel> posts = g.findMatchingUserByUserID(Integer.parseInt(userID));
			Collections.reverse(posts);
			
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
				
			// cleaning things up
			// session.removeAttribute("posts");
			session.removeAttribute("pageNumberPlus");
			session.removeAttribute("pageNumberMinus");
			session.removeAttribute("loggedInMessage");
			
			if (page+1 <= numPages)
				session.setAttribute("pageNumberPlus", page+1);
			
			if (page-1 >= 0)
				session.setAttribute("pageNumberMinus", page-1);
			
			req.setAttribute("textStyle", "all: unset; color: #511f11 !important;");
			
			req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
		}
		
		// if the user did not click on a post to get to the profile page
		else if (req.getParameter("userID") == null) {
			System.out.println("User ID's match");
			req.setAttribute("userMatch", true);
			FindMatchingPostsByUserID g = new FindMatchingPostsByUserID();
			List<PostModel> posts = g.findMatchingUserByUserID(Integer.parseInt((String) session.getAttribute("userID")));
			Collections.reverse(posts);
			
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
					
			// cleaning things up
			// session.removeAttribute("posts");
			session.removeAttribute("pageNumberPlus");
			session.removeAttribute("pageNumberMinus");
			session.removeAttribute("loggedInMessage");
			
			if (page+1 <= numPages)
				session.setAttribute("pageNumberPlus", page+1);
			
			if (page-1 >= 0)
				session.setAttribute("pageNumberMinus", page-1);
			
			req.setAttribute("textStyle", "all: unset; color: #511f11 !important;");
			
			req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
		}
	}
}
