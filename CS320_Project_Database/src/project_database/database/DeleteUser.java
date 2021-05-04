package project_database.database;

import java.util.Scanner;

import project_database.model.PostModel;
import project_database.persist.DerbyDatabase;

public class DeleteUser {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		DerbyDatabase db = new DerbyDatabase();
		
		System.out.print("Enter a userID: ");
		int userID = keyboard.nextInt();
		
		// get the DB instance and execute transaction
		db.deleteUser(userID);
		
		System.out.println("Success");
		keyboard.close();
	}
	
	public void deleteUser(int userID) {
		DerbyDatabase db = new DerbyDatabase();
		db.deleteUser(userID);
		System.out.println("Success");
		return;
	}
}
