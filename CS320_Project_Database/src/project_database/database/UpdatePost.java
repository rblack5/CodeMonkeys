package project_database.database;

import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

import project_database.model.UserModel;
import project_database.persist.DerbyDatabase;

public class UpdatePost {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		DerbyDatabase db = new DerbyDatabase();
		
		System.out.print("Enter post title: ");
		String postTitle = keyboard.nextLine();
		System.out.print("Enter post body: ");
		String postBody = keyboard.nextLine();
		System.out.print("Enter a postID: ");
		int postID = keyboard.nextInt();
			
		// get the DB instance and execute transaction
		db.updatePost(postID, postTitle, postBody);
		
		keyboard.close();
		System.out.println("Success");
		
	}
	public void updatePost(int postID, String postTitle, String postBody) {
		DerbyDatabase db = new DerbyDatabase();
		UserModel original = db.findMatchingUserByUserID(postID);
		
		if(postTitle == null || postTitle.trim().isEmpty()) {
			postTitle = original.getUsername();
		}
		if(postBody == null || postBody.trim().isEmpty()) {
			postBody = original.getPassword();
		}
		
		db.updatePost(postID, postTitle, postBody);
		System.out.println("Success");
	
	}
}
