package project_database.database;

import java.io.InputStream;
import java.util.Scanner;

import project_database.model.UserModel;
import project_database.persist.DerbyDatabase;

public class UpdateImage {
	public static void main(String[] args) throws Exception {
		
	}
	public void updatePost(int userID, InputStream userImage) {
		DerbyDatabase db = new DerbyDatabase();
		
		db.updateImage(userID, userImage);
		System.out.println("Success");
	
	}
}
