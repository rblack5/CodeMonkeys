package project_database.model;

import java.util.ArrayList;

public class LoginModel {
	private String email, password;
	private Boolean isLoggedIn;
	
	public ArrayList<String> passwords = new ArrayList<String>(); 
	
	public LoginModel() {
		isLoggedIn = false;
		
		passwords.add("admin");
		passwords.add("hunter2");
		passwords.add("password");
		passwords.add("codemonkeys");
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setLoggedIn(Boolean isLoggedIn ) {
		this.isLoggedIn = isLoggedIn;
	}
	
	public boolean getLoggedIn() {
		return this.isLoggedIn;
	}
}
