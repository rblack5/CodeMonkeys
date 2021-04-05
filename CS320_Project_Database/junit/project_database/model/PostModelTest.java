package project_database.model;

import org.junit.Before;
import org.junit.Test;

public class PostModelTest {
	private PostModel model;
	
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
}
