package project_database.model;

public class UserModel {
	private String username, password, bio, dateJoined;
	int userID;
	
	public UserModel() {
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public String getBio() {
		return this.bio;
	}
	
	public void setDateJoined(String dateJoined) {
		this.dateJoined = dateJoined;
	}
	
	public String getDateJoined() {
		return this.dateJoined;
	}
}
