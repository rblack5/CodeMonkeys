package project_database.database;

import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

import project_database.model.UserModel;
import project_database.persist.DerbyDatabase;

public class UpdateUser {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		DerbyDatabase db = new DerbyDatabase();
		
		Boolean adminStatus = false;
		
		System.out.print("Enter a username: ");
		String username = keyboard.nextLine();
		System.out.print("Enter a password: ");
		String password = keyboard.nextLine();
		System.out.print("Enter a bio: ");
		String bio = keyboard.nextLine();
		System.out.print("Enter a userID: ");
		int userID = keyboard.nextInt();
		
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
		
		// get the DB instance and execute transaction
		db.updateUser(userID, username, hashedPassword, bio);
		
		System.out.println("Success");
		
	}
	public void updateUser(int userID, String username, String password, String bio) {
		DerbyDatabase db = new DerbyDatabase();
		UserModel original = db.findMatchingUserByUserID(userID);
		
		if(username == null || username.trim().isEmpty()) {
			username = original.getUsername();
		}
		if(password == null || password.trim().isEmpty()) {
			password = original.getPassword();
		}
		if(bio == null || bio.trim().isEmpty()) {
			bio = original.getBio();
		}
		
		db.updateUser(userID, username, password, bio);
		System.out.println("Success");
	
	}
}
