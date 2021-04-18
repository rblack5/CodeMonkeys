package project_database.database;

import java.util.List;
import java.util.Scanner;

import project_database.model.Pair;
import project_database.model.PostModel;
import project_database.model.UserModel;
import project_database.persist.DerbyDatabase;
// import project_database.persist.DatabaseProvider;
// import project_database.persist.IDatabase;

public class ViewAllPosts {
	public static void main(String[] args) throws Exception {

		DerbyDatabase db = new DerbyDatabase();
		
		List<PostModel> postList = db.viewAllPosts();
		
		// check if anything was returned and output the list
		if (postList.isEmpty()) {
			System.out.println("There are no posts.");
		}
		else { 
			for (PostModel post : postList) {
				System.out.println("PostID: " + post.getPostID() + " | PostAuthorID: " + post.getUserID() +  " | Title: " + post.getTitle() + " | Body: " + post.getBody());
			}
		}
	}
	
	public List<PostModel> getPostList() {
		DerbyDatabase db = new DerbyDatabase();
		List<PostModel> postList = db.viewAllPosts();
		return postList;
	}
}
