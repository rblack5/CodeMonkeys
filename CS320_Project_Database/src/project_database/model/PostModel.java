package project_database.model;

public class PostModel {
	private String title, body, username, dateCreated;
	private int postID, userID;
	
	public PostModel() {
		
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getBody() {
		return this.body;
	}
	
	public void setPostID(int post_id) {
		this.postID = post_id;
	}
	
	public int getPostID() {
		return this.postID;
	}
	
	public void setUserID(int user_id) {
		this.userID = user_id;
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public String getDateCreated() {
		return this.dateCreated;
	}
	
}
