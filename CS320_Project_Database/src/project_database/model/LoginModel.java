package project_database.model;

public class LoginModel {
	private String email, password;
	
	public LoginModel() {
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return this.email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
}
