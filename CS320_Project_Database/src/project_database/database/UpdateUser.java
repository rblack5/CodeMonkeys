package project_database.database;

import java.io.InputStream;
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
		System.out.print("Enter a post theme: ");
		String postTheme = keyboard.nextLine();
		System.out.print("Enter an account theme: ");
		String accountTheme = keyboard.nextLine();
		System.out.print("Enter a userID: ");
		int userID = keyboard.nextInt();
		
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
		
		// get the DB instance and execute transaction
		db.updateUser(userID, username, hashedPassword, bio, postTheme, accountTheme);
		
		System.out.println("Success");
		
	}
	public void updateUser(int userID, String username, String password, String password2, String bio, String postTheme, String accountTheme) {
		DerbyDatabase db = new DerbyDatabase();
		UserModel original = db.findMatchingUserByUserID(userID);
		String originalPassword = original.getPassword();
		System.out.println("Original Password: " + originalPassword);
		
		if (username == null || username.trim().isEmpty() || username.equals("")) {
			username = original.getUsername();
		}
		
		if (bio == null || bio.trim().isEmpty() || bio.equals("")) {
			bio = original.getBio();
		}
		
//		if (password == null || password.trim().isEmpty() || password.equals("")) {
//			String originalPassword = original.getPassword();
//		}
		
		if (postTheme == null) {
			postTheme = original.getPostTheme();
		}
		
		if (accountTheme == null) {
			accountTheme = original.getAccountTheme();
		}
		
		if ((password == null || password.trim().isEmpty() || password.equals(""))) {
			System.out.println("Password is NOT being rehashed!");
			db.updateUser(userID, username, originalPassword, bio, postTheme, accountTheme);
		}
		else {
			System.out.println("Password is being rehashed!");
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
			db.updateUser(userID, username, hashedPassword, bio, postTheme, accountTheme);
		}
		System.out.println("Success");
	
	}
}
