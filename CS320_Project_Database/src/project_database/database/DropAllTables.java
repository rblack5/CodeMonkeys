package project_database.database;

import java.util.List;
import java.util.Scanner;

import project_database.model.Pair;
import project_database.model.PostModel;
import project_database.model.UserModel;
import project_database.persist.DerbyDatabase;

public class DropAllTables {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		DerbyDatabase db = new DerbyDatabase();
		
//		System.out.print("Are you are? Type yes if so.");
//		String response = keyboard.nextLine();
//		if (response.equals("yes")) {
//			db.dropAllTables();
//			System.out.println("Success");
//		}
//		else {
//			System.out.println("Nothing was dropped.");
//		}
		db.dropAllTables();
		System.out.println("Success");
	}

}
