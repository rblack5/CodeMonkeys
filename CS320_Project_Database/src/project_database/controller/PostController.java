package project_database.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import project_database.model.PostModel;
import project_database.model.UserModel;

public class PostController {
	private PostModel post;
	Path pathToFile = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "posts.csv");
	
	public void setPost(PostModel post) {
		this.post = post;
	}
	
	public PostModel getPost() {
		return this.post;
	}
	
	public List<PostModel> importCSV() {
		List<PostModel> posts = new ArrayList<>();
				
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)){
			
			// Read the first line, remove the quotations. Quotations are exported from a CSV by default.
			String line = br.readLine();
			line = line.replace("\"", "");
			
			while(line != null) {
				
				String[] attributes = line.split(",");
				
				String title = attributes[0];
				String body = attributes[1];
				
				PostModel post = new PostModel();
				post.setTitle(title);
				post.setBody(body);
				
				posts.add(post);
				
				// Read a new line, remove the quotes.
				line = br.readLine();
				if(line != null) {
					line = line.replace("\"", "");
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return posts;
	}
	
	public PostModel findPost(String title) {
		List<PostModel> posts = importCSV();
		
		for (PostModel post: posts) {
			if(post.getTitle().equals(title)) {
				return post;
			}
		}
		return null;
	}
}
