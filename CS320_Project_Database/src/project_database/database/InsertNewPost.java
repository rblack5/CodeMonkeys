package project_database.database;


import java.util.Scanner;

import project_database.model.PostModel;
import project_database.persist.DerbyDatabase;

public class InsertNewPost {
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
		System.out.print("Enter a userID: ");
		int userID = keyboard.nextInt();
		System.out.print("Enter a date: ");
		String date = keyboard.nextLine();
		
		String textStyle = "all: unset;";
		String backgroundStyle = "";
		String linkStyle = "all: unset; color:blue; cursor:pointer; text-decoration:underline;";
		String titleStyle = "color: #444444;";
		// get the DB instance and execute transaction
		PostModel post;
		post = db.insertNewPost(userID, username, postTitle, postBody, textStyle, backgroundStyle, linkStyle, titleStyle, date);
		
		System.out.println("Success");
		System.out.println("Post ID is: " + post.getPostID());
		keyboard.close();
	}
	
	public PostModel insertNewPost(int userID, String username, String postTitle, String postBody, String textStyle, String backgroundStyle, String linkStyle, 
			String titleStyle, String dateCreated) {
		DerbyDatabase db = new DerbyDatabase();
		System.out.println("User id is ==> " + userID);
		PostModel post;
		
		post = db.insertNewPost(userID, username, postTitle, postBody, textStyle, backgroundStyle, linkStyle, titleStyle, dateCreated);
		System.out.println("Success");
		System.out.println("Post ID is: " + post.getPostID());
		return post;
	}

}
