package project_database.controller;

import project_database.model.LoginModel;
import project_database.model.RegisterModel;

public class RegisterController {
	private RegisterModel model;

	public void setModel(RegisterModel model) {
		this.model = model;
	}
	
	public boolean checkSamePassword(RegisterModel model) {
		if(model.getPassword().equals(model.getPassword2())) {
			return true;
		}
		return false;
	}
	
}
