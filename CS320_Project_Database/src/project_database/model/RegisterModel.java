package project_database.model;

public class RegisterModel {
	private String email, password, password2;
	
	public RegisterModel() {
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
	
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	public String getPassword2() {
		return this.password2;
	}
}
