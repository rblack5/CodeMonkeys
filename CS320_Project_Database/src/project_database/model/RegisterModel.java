package project_database.model;

public class RegisterModel {
	private String Username, password, password2;
	private boolean samePassword;
	
	public RegisterModel() {
	}
	
	public void setUsername(String Username) {
		this.Username = Username;
	}
	
	public String getUsername() {
		return this.Username;
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
