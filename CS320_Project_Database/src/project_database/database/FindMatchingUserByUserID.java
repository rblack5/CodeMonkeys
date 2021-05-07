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
		
		System.out.print("Enter the userID: ");
		int userID = keyboard.nextInt();
		
		DerbyDatabase db = new DerbyDatabase();
		UserModel user = db.findMatchingUserByUserID(userID);
		System.out.println("UserID: " + user.getUserID() + " | Username: " + user.getUsername() +  " | Password: " + user.getPassword() +  " | Bio: " + user.getBio()
		 + " | DateJoined: " + user.getDateJoined()  + " | AdminStatus: " + user.getAdminStatus());
	}
	
	public UserModel findMatchingUserByUserID(int userID) {
		DerbyDatabase db = new DerbyDatabase();
		UserModel user = db.findMatchingUserByUserID(userID);
		return user;
	}
	
	
}
