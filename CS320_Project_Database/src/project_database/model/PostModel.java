package project_database.model;

public class PostModel {
	private String title, body;
	int postID, userID;
	
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
	
	public void setTest(String title) {
		this.title = title;
	}
	
}
