package project_database.database;

import java.util.List;
import java.util.Scanner;

import project_database.model.Pair;
import project_database.model.PostModel;
import project_database.model.UserModel;
import project_database.persist.DerbyDatabase;
// import project_database.persist.DatabaseProvider;
// import project_database.persist.IDatabase;

public class ViewAllUsers {
	public static void main(String[] args) throws Exception {

		DerbyDatabase db = new DerbyDatabase();
		
		List<UserModel> userList = db.viewAllUsers();
		
		// check if anything was returned and output the list
		if (userList.isEmpty()) {
			System.out.println("There are no users.");
		}
		else { 
			for (UserModel user : userList) {
				System.out.println("UserID: " + user.getUserID() + " | Username: " + user.getUsername() +  " | Password: " + user.getPassword());
			}
		}
	}
	
	
}
