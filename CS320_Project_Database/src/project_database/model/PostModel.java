package project_database.model;

public class PostModel {
	private String title, body;
	int post_id, user_id;
	
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
		this.post_id = post_id;
	}
	
	public int getPostID() {
		return this.post_id;
	}
	
	public void setUserID(int user_id) {
		this.user_id = user_id;
	}
	
	public int getUserID() {
		return this.user_id;
	}
	
}
