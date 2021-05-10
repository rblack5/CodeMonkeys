package project_database.model;

import java.io.InputStream;
import java.sql.Blob;

public class UserModel {
	private String username, password, bio, dateJoined, postTheme, accountTheme, userImage;
	private int userID;
	private boolean adminStatus;
	
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
	
	public void setPostTheme(String theme) {
		this.postTheme = theme;
	}
	
	public String getPostTheme() {
		return this.postTheme;
	}
	
	public void setAccountTheme(String accTheme) {
		this.accountTheme = accTheme;
	}
	
	public String getAccountTheme() {
		return this.accountTheme;
	}
	
	public void setAdminStatus(Boolean adminStatus) {
		this.adminStatus = adminStatus;
	}
	
	public Boolean getAdminStatus() {
		return this.adminStatus;
	}
	
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	
	public String getUserImage() {
		return this.userImage;
	}
}
