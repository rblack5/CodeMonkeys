package project_database.controller;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import project_database.model.PostModel;

public class PostControllerTest {
	private PostModel model;
	private PostController controller;
	
	@Before
	public void setUp() {
		model = new PostModel();
		controller = new PostController();
	}
	
	@Test
	public void testSetModel() {
		this.model.setUserID(10);
		controller.setModel(model);
		
		assert(controller.getModel().getUserID() == 10);
		
		this.model.setUserID(12);
		controller.setModel(model);
		
		assert(controller.getModel().getUserID() == 12);
	}
	
	@Test
	public void testImportCSV() {
		List<PostModel> posts = controller.importCSV();
		
		assert(posts.get(0).getPostID() == 0);
	}
	
	public void testFindPost() {
		assert(controller.findPost(0).getTitle() == "ExamplePost");
	}
}
