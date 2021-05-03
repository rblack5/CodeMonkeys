package project_database.database;

import java.util.Scanner;

import project_database.model.PostModel;
import project_database.persist.DerbyDatabase;

public class DeletePost {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		DerbyDatabase db = new DerbyDatabase();
		
		System.out.print("Enter a postID: ");
		int postID = keyboard.nextInt();
		
		// get the DB instance and execute transaction
		PostModel post;
		post = db.deletePost(postID);
		
		System.out.println("Success");
		System.out.println("Post ID is: " + post.getPostID());
		keyboard.close();
	}
	
	public PostModel deletePost(int postID) {
		DerbyDatabase db = new DerbyDatabase();
		System.out.println("Posst ID is ==> " + postID);
		PostModel post;
		post = db.deletePost(postID);
		System.out.println("Success");
		return post;
	}
}
