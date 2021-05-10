package project_database.database;

import java.util.List;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

import project_database.model.Pair;
import project_database.model.PostModel;
import project_database.model.UserModel;
import project_database.persist.DerbyDatabase;

public class InsertNewUser {
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
		System.out.print("Enter a post theme (light, dark, gold...): ");
		String postTheme = keyboard.nextLine();
		System.out.print("Enter a date joined: ");
		String dateJoined = keyboard.nextLine();
		System.out.print("Admin Status? Y/N ");
		String response = keyboard.nextLine();
		System.out.print("Enter an account theme (light, dark): ");
		String accountTheme = keyboard.nextLine();
//		System.out.print("Enter a userID: ");
//		int userID = keyboard.nextInt();
		if (response.equals("y") || response.equals("Y")) {
			adminStatus = true;
		}
		
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
		// get the DB instance and execute transaction
		db.insertNewUser(username, hashedPassword, bio, dateJoined, postTheme, adminStatus, accountTheme, "https://i.pinimg.com/originals/2b/05/ef/2b05ef3609b862dfb7dfcbe95b7de949.jpg");
		
		System.out.println("Success");
		
	}
	
	public void insertNewUser(String username, String password, String bio, String dateJoined) {
		DerbyDatabase db = new DerbyDatabase();
		Boolean adminStatus = false;
		bio = "This user does not have a bio yet!";
		String postTheme = "light";
		String accountTheme = "light";
		db.insertNewUser(username, password, bio, dateJoined, postTheme, adminStatus, accountTheme, "https://i.pinimg.com/originals/2b/05/ef/2b05ef3609b862dfb7dfcbe95b7de949.jpg");
		
		System.out.println("Success");
	}

}
