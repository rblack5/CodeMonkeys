package project_database.database;


import java.util.Scanner;

import project_database.model.CommentModel;
import project_database.model.PostModel;
import project_database.model.UserModel;
import project_database.persist.DerbyDatabase;

public class InsertNewComment {

	public void insertNewComment(int userID, int postID, String username, String commentBody, String textStyle, String backgroundStyle, 
			String linkStyle, String titleStyle, String dateCreated) {
			DerbyDatabase db = new DerbyDatabase();
			System.out.println("User id is ==> " + userID);
			PostModel post;
			UserModel user;
			CommentModel comment;
		
			user = db.findMatchingUserByUserID(userID);
			
			comment = db.insertNewComment(userID, postID, username, commentBody, textStyle, backgroundStyle, linkStyle, titleStyle, dateCreated, user.getUserImage());
			System.out.println("Success");
			System.out.println("Comment ID is: " + comment.getPostID());
	}

}
