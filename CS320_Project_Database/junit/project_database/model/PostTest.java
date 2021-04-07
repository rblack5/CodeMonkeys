package project_database.model;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import project_database.controller.PostController;

public class PostTest {
	private PostModel model;
	private PostController controller;
	
	@Before
	public void setUp() {
		model = new PostModel();
	}
	
	@Test
	public void testSetTitle() {
		model.setTitle("Title");
		assert(model.getTitle()=="Title");
		
		model.setTitle("Title2");
		assert(model.getTitle()=="Title2");
		
		model.setTitle("Title2!$#");
		assert(model.getTitle()=="Title2!$#");
	}
	
	@Test
	public void testSetBody() {
		model.setBody("TestBody");
		assert(model.getBody()=="TestBody");
		
		model.setBody("Test Body");
		assert(model.getBody()=="Test Body");
		
		model.setBody("Test Body!$#");
		assert(model.getBody()=="Test Body!$#");
	}
	
	@Test
	public void testSetPostID() {
		model.setPostID(1000);
		assert(model.getPostID() == 1000);
		model.setPostID(10);
		assert(model.getPostID() == 10);
		model.setPostID(23);
		assert(model.getPostID() == 23);
	}
	
	@Test
	public void testImport() throws IOException {
		PostModel post = controller.findPost(2);
		
		assert(post.getTitle().equals("test"));
		assert(post.getPostID() == 2);
	}
}
