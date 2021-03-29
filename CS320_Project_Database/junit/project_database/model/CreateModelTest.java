package project_database.model;

import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

import project_database.model.CreateModel;

public class CreateModelTest {
	private CreateModel model;

	@Before
	public void setUp() {
		model = new CreateModel();
	}
	
	@Test
	public void testSetTitle1() {
		model.setTitle("Intro to CS");
		assert(model.getTitle().equals("Intro to CS"));
	}
	
	@Test
	public void testSetTitle2() {
		model.setTitle("How to Pass EE");
		assert(model.getTitle().equals("How to Pass EE"));
	}
	
	@Test
	public void testSetTitle3() {
		model.setTitle("Shepherd of Fire");
		assert(model.getTitle().equals("Shepherd of Fire"));
	}
	
	@Test
	public void testSetBody1() {
		model.setBody("So my foots totally stuck in there right, I'm freaking out, the dog's having a seizure and I still got half a pie left.");
		assert(model.getBody().equals("So my foots totally stuck in there right, I'm freaking out, the dog's having a seizure and I still got half a pie left."));
	}
	
	@Test
	public void testSetBody2() {
		model.setBody("This wasn't how it was supposed to go. We were supposed to secure the package, and be in and out clean.");
		assert(model.getBody().equals("This wasn't how it was supposed to go. We were supposed to secure the package, and be in and out clean."));
	}
	
	
	
}
