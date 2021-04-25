package project_database.controller;

import java.util.List;

import project_database.database.ViewAllUsers;
import project_database.model.PostModel;
import project_database.model.UserModel;

public class ProfileController {
	private UserModel model;
	
	public void setModel(UserModel model) {
		this.model = model;
	}
	
	public UserModel getModel() {
		return this.model;
	}
	
	public UserModel findUser(int userID) {
		ViewAllUsers g = new ViewAllUsers();
		List<UserModel> users = g.getUserList();
		
		for (UserModel user : users) {
			if(user.getUserID() == userID) {
				return user;
			}
		}
		return null;
	}
}
