package project_database.database;

import java.util.List;
import java.util.Scanner;

import project_database.model.Pair;
import project_database.model.PostModel;
import project_database.model.UserModel;
import project_database.persist.DerbyDatabase;

public class InsertNewPostWithUser {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		DerbyDatabase db = new DerbyDatabase();
		
		System.out.print("Enter a username: ");
		String username = keyboard.nextLine();
		System.out.print("Enter a post title: ");
		String postTitle = keyboard.nextLine();
		System.out.print("Enter a post body: ");
		String postBody = keyboard.nextLine();
		System.out.print("Enter a postID: ");
		int postID = keyboard.nextInt();
		System.out.print("Enter a userID: ");
		int userID = keyboard.nextInt();
		
		// get the DB instance and execute transaction
		db.insertNewPostWithUser(username, userID, postTitle, postID, postBody);
		
		System.out.println("Success");
		
	}

}
