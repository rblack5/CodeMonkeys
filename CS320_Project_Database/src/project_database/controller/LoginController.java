package project_database.controller;

import project_database.model.LoginModel;

public class LoginController {
	private LoginModel model;

	public void setModel(LoginModel model) {
		this.model = model;
	}
	
	public boolean checkLogIn(LoginModel model) {
		for(int i = 0; i < model.passwords.size(); i++) {
			// System.out.println(model.getPassword() + "===");
			// System.out.println(model.passwords.get(i));
			if(model.getPassword().equals(model.passwords.get(i))) {
				// System.out.println(i);
				return true;
			}
		}
		
		return false;
	}
}
