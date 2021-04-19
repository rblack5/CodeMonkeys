package project_database.model;

public class EditProfileModel {
	private String name, bio, password;
	int userID;
	
	public EditProfileModel() {
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public String getBio() {
		return this.bio;
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
	
}
