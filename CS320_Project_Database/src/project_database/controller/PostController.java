package project_database.controller;

import java.io.BufferedReader;
import java.io.FileWriter;
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
	PostModel model;
	
	public void setModel(PostModel post) {
		this.post = post;
	}
	
	public PostModel getModel() {
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
				
				String post_id = attributes[0];
				String title = attributes[1];
				String body = attributes[2];
				
				PostModel post = new PostModel();
				post.setPostID(Integer.parseInt(post_id));
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
	
	public void createPost(PostModel model) throws IOException {
		List<PostModel> posts = importCSV();
		FileWriter writer = new FileWriter(pathToFile.toString(), true);
		
		String newPost = "\"" + String.join(",", String.valueOf(posts.size()),model.getTitle(), model.getBody()) + "\"";
		
		writer.write(newPost);
		writer.write("\n");
		
		writer.close();
	}
	
	public PostModel findPost(int post_id) {
		List<PostModel> posts = importCSV();
		
		for (PostModel post: posts) {
			if(post.getPostID() == post_id) {
				return post;
			}
		}
		return null;
	}
}
