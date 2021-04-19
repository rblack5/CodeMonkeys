package project_database.database;

import java.util.List;
import java.util.Scanner;

import project_database.model.Pair;
import project_database.model.PostModel;
import project_database.model.UserModel;
import project_database.persist.DerbyDatabase;

public class InsertNewUser {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		DerbyDatabase db = new DerbyDatabase();
		
		System.out.print("Enter a username: ");
		String username = keyboard.nextLine();
		System.out.print("Enter a password: ");
		String password = keyboard.nextLine();
		System.out.print("Enter a bio: ");
		String bio = keyboard.nextLine();
//		System.out.print("Enter a userID: ");
//		int userID = keyboard.nextInt();

		
		// get the DB instance and execute transaction
		db.insertNewUser(username, password, bio);
		
		System.out.println("Success");
		
	}
	
	public void insertNewUser(String username, String password, String bio) {
		DerbyDatabase db = new DerbyDatabase();
		db.insertNewUser(username, password, bio);
		System.out.println("Success");
	}

}
