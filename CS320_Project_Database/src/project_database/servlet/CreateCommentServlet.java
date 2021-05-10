package project_database.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project_database.controller.PostController;
import project_database.database.FindMatchingCommentsWithPostID;
import project_database.database.InsertNewComment;
import project_database.database.InsertNewPost;
import project_database.model.CommentModel;
import project_database.model.PostModel;
import project_database.model.UserModel;
import project_database.persist.DerbyDatabase;

public class CreateCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Servlet: doGet");
		HttpSession session = req.getSession();
		session.removeAttribute("posts");
		
		req.getRequestDispatcher("/_view/create.jsp").forward(req, resp);
	}
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Comment Servlet: doPost");
		
		HttpSession session = req.getSession();
		
		PostModel currentPost = (PostModel) session.getAttribute("currentPost");
		DerbyDatabase db = new DerbyDatabase();
		
		// holds the error message text, if there is any
		String errorMessage = null;
		Boolean invalidInfo = false;
		String title = "";
		String body = "";
		
		// Create the model
		CommentModel model = new CommentModel();
		CommentModel comment = null;
		// decode POSTed form parameters
		try {
			// Obtain the email and password from the doGet
			body = req.getParameter("body");
			
			System.out.println("Body after getParameter: " + body);
						
			if (body.contains("\"")) {
				errorMessage = "No quotes allowed in body!";
				System.out.println("Invalid Fields");
				invalidInfo = true;
			}

			// check for errors in the form data (error message is not yet implemented)
			if (body == null) {
				errorMessage = "Please enter required fields";
				invalidInfo = true;		
			}
			
			if (body.length() > 3000) {
				errorMessage = "The body cannot be longer than 3000 characters";
				invalidInfo = true;
			}
			
			if (body.length() < 1) {
				errorMessage = "Comments must have a body";
				invalidInfo = true;
			}
			
			if (!invalidInfo) {
				// otherwise, data is good
				// must create the controller each time, since it doesn't persist between POSTs
				// the view does not alter data, only controller methods should be used for that
				// thus, always call a controller method to operate on the data
			
				// Send the values to the model
				model.setBody(body);
				String username = (String) session.getAttribute("username");
				System.out.println("username is: " + username);
				String userID = (String) session.getAttribute("userID");
				System.out.println("userID is: " + userID);
				
				int realUserID = Integer.parseInt(userID);
				
				UserModel user = new UserModel();
				user = (UserModel) session.getAttribute("user");
				
				if (user.getPostTheme().equals("light")) {
					model.setTextStyle("all: unset; color: #444444; white-space: pre-wrap;");
					model.setBackgroundStyle("background-color: #ffffff;");
					model.setLinkStyle("all: unset; color:blue; cursor:pointer; text-decoration:underline;");
					model.setTitleStyle("font-size: 2.20em; font-weight: bolder; color: #444444;");
				}
				else if (user.getPostTheme().equals("dark")) {
					model.setTextStyle("all: unset; color:white; white-space: pre-wrap;");
					model.setBackgroundStyle("background-color: #222C2D;");
					model.setLinkStyle("all: unset; color:white; cursor:pointer; text-decoration:underline;");
					model.setTitleStyle("font-size: 2.20em; font-weight: bolder; color:white;");
				}
				else if (user.getPostTheme().equals("fire")) {
					model.setTextStyle("all: unset; color:black; white-space: pre-wrap;");
					model.setBackgroundStyle("background-image: linear-gradient(orange, red);");
					model.setLinkStyle("all: unset; color:black; cursor:pointer; text-decoration:underline;");
					model.setTitleStyle("font-size: 2.20em; font-weight: bolder; color:black;");
				}
				else if (user.getPostTheme().equals("gold")) {
					model.setTextStyle("all: unset; color: #000080; white-space: pre-wrap;");
					//#FFD700
					// url('${pageContext.request.contextPath}/_view/images/default.jpg');
					model.setBackgroundStyle("background-image: url('https://media.discordapp.net/attachments/805890300677062666/841213062875054080/283561966e2b7c98f4534e14d847f473.png?width=1202&height=676');");
					model.setLinkStyle("all: unset; color:#000080; cursor:pointer; text-decoration:underline;");
					model.setTitleStyle("font-size: 2.20em; font-weight: bolder; color:#000080;");
				}
				
				model.setUserID(realUserID);
				
				model.setUsername(username);
				
				System.out.println("model.getUserID =>" + model.getUserID());
				System.out.println("model.getUsername =>" + model.getUsername());
				
				LocalDate dateCreated = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
				System.out.println(dateCreated.format(formatter));
				
				if (user.getPostTheme().equals("light")) {
					model.setTextStyle("all: unset; color: #444444; white-space: pre-wrap;");
					model.setBackgroundStyle("background-color: #ffffff;");
					model.setLinkStyle("all: unset; color:blue; cursor:pointer; text-decoration:underline;");
					model.setTitleStyle("font-size: 2.20em; font-weight: bolder; color: #444444;");
				}
				else if (user.getPostTheme().equals("dark")) {
					model.setTextStyle("all: unset; color:white; white-space: pre-wrap;");
					model.setBackgroundStyle("background-color: #222C2D;");
					model.setLinkStyle("all: unset; color:white; cursor:pointer; text-decoration:underline;");
					model.setTitleStyle("font-size: 2.20em; font-weight: bolder; color:white;");
				}
				else if (user.getPostTheme().equals("fire")) {
					model.setTextStyle("all: unset; color:black; white-space: pre-wrap;");
					model.setBackgroundStyle("background-image: linear-gradient(orange, red);");
					model.setLinkStyle("all: unset; color:black; cursor:pointer; text-decoration:underline;");
					model.setTitleStyle("font-size: 2.20em; font-weight: bolder; color:black;");
				}
				else if (user.getPostTheme().equals("gold")) {
					model.setTextStyle("all: unset; color: #000080; white-space: pre-wrap;");
					//#FFD700
					// url('${pageContext.request.contextPath}/_view/images/default.jpg');
					model.setBackgroundStyle("background-image: url('https://media.discordapp.net/attachments/805890300677062666/841213062875054080/283561966e2b7c98f4534e14d847f473.png?width=1202&height=676');");
					model.setLinkStyle("all: unset; color:#000080; cursor:pointer; text-decoration:underline;");
					model.setTitleStyle("font-size: 2.20em; font-weight: bolder; color:#000080;");
				}
				
				InsertNewComment g = new InsertNewComment();
				
				g.insertNewComment(realUserID, currentPost.getPostID(), username, body, model.getTextStyle(), model.getBackgroundStyle(), model.getLinkStyle(),
						model.getTitleStyle(), dateCreated.format(formatter));
//				PostController controller = new PostController();
//				post = controller.createPost(model);
//				
				UserModel updatedUser = db.findMatchingUserByUserID(realUserID);
				
				db.updateImage(realUserID, updatedUser.getUserImage());
				
				
				
			}
			
		// This part is probably unnecessary but I am keeping it to help us write catch methods
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
		}
		
		if (invalidInfo) {
			// set the attribute named "create" to return the model
			req.setAttribute("create", model);
			
			// this adds the errorMessage text and the result to the response
			req.setAttribute("errorMessage", errorMessage);
			
			// saving what the user put incase it failed
			req.setAttribute("postTitle", title);
			req.setAttribute("postBody", body);
			
			session.setAttribute("createCommentBody", body);
			req.setAttribute("post", currentPost);
			
			List<CommentModel> commentList = new ArrayList<CommentModel>();

			FindMatchingCommentsWithPostID g = new FindMatchingCommentsWithPostID();
			commentList = g.findMatchingCommentsWithPostID(currentPost.getPostID());
			Collections.reverse(commentList);
			session.setAttribute("currentCommentList", commentList);
			// Forward to view to render the result HTML document
			req.getRequestDispatcher("/_view/post.jsp").forward(req, resp);
		}
		else if (!invalidInfo){
			req.setAttribute("post", currentPost);
			req.setAttribute("errorMessage", errorMessage);
			req.removeAttribute("postTitle");
			req.removeAttribute("postBody");
			
			session.removeAttribute("createCommentBody");
			
			session.setAttribute("currentPost", currentPost);
			
			List<CommentModel> commentList = new ArrayList<CommentModel>();

			FindMatchingCommentsWithPostID g = new FindMatchingCommentsWithPostID();
			commentList = g.findMatchingCommentsWithPostID(currentPost.getPostID());
			Collections.reverse(commentList);
			session.setAttribute("currentCommentList", commentList);
			
			req.getRequestDispatcher("/_view/post.jsp").forward(req, resp);
		}
	}
}
