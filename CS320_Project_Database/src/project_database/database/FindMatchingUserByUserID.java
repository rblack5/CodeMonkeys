package project_database.database;

import java.util.List;
import java.util.Scanner;

import project_database.model.Pair;
import project_database.model.PostModel;
import project_database.model.UserModel;
import project_database.persist.DerbyDatabase;
// import project_database.persist.DatabaseProvider;
// import project_database.persist.IDatabase;

public class FindMatchingUserByUserID {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		// InitDatabase.init(keyboard);
		
		System.out.print("Enter the post title: ");
		String postTitle = keyboard.nextLine();
		
		System.out.print("Enter the post ID: ");
		String postID = keyboard.nextLine();
		
		// get the DB instance and execute transaction
		// IDatabase db = DatabaseProvider.getInstance();
		
		DerbyDatabase db = new DerbyDatabase();
		
		List<Pair<UserModel, PostModel>> userPostList = db.findUserModelAndPostModelByTitle(postTitle, postID);
		
		// check if anything was returned and output the list
		if (userPostList.isEmpty()) {
			System.out.println("No posts found with title  <" + postTitle + "> and ID of <" + postID + ">");
		}
		else { 
			for (Pair<UserModel, PostModel>  userPost : userPostList) {
				UserModel user = userPost.getLeft();
				PostModel post = userPost.getRight();
				System.out.println(user.getUsername() + "," + user.getUserID() + "," + post.getTitle() + "," + post.getPostID());
			}
		}
	}
	
	public UserModel findMatchingUserByUserID(int userID) {
		DerbyDatabase db = new DerbyDatabase();
		UserModel user = db.findMatchingUserByUserID(userID);
		return user;
	}
	
	
}
