package project_database.model;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import project_database.controller.EditProfileController;

public class EditProfileTest {
	private EditProfileModel model;
	private EditProfileController controller;
	
	@Before
	public void setUp() {
		model = new EditProfileModel();
		controller = new EditProfileController();
	}
	
	@Test
	public void testSetUsername1() {
		model.setName("test");
		assert(model.getName().equals("test"));
		model.setName("test 2");
		assert(model.getName().equals("test 2"));
		model.setName("test 3!$#");
		assert(model.getName().equals("test 3!$#"));
		model.setName("woohoo@gmail.com");
		assert(model.getName().equals("woohoo@gmail.com"));
	}
	@Test
	public void testSetUsername2() {
		model.setName("123321123");
		assert(model.getName().equals("123321123"));
		model.setName("qwerty");
		assert(model.getName().equals("qwerty"));
		model.setName("test 3!$#");
		assert(model.getName().equals("test 3!$#"));
		model.setName("bepsi&Co");
		assert(model.getName().equals("bepsi&Co"));
	}
	
	@Test
	public void testSetBio1() {
		model.setBio("test");
		assert(model.getBio().equals("test"));
		model.setBio("test 2");
		assert(model.getBio().equals("test 2"));
		model.setBio("test 3!$#");
		assert(model.getBio().equals("test 3!$#"));
		model.setBio("This is my bio, I am a user with a bio. Yay!");
		assert(model.getBio().equals("This is my bio, I am a user with a bio. Yay!"));
	}
	@Test
	public void testSetBio2() {
		model.setBio(" test");
		assert(model.getBio().equals(" test"));
		model.setBio(" test 2");
		assert(model.getBio().equals(" test 2"));
		model.setBio("");
		assert(model.getBio().equals(""));
		model.setBio(" This is my bio, I am a user with a bio. Let's go!");
		assert(model.getBio().equals(" This is my bio, I am a user with a bio. Let's go!"));
	}
	
	@Test
	public void testSetPassword1() {
		model.setBio("test");
		assert(model.getBio().equals("test"));
		model.setBio("test 2");
		assert(model.getBio().equals("test 2"));
		model.setBio("test 3!$#");
		assert(model.getBio().equals("test 3!$#"));
		model.setBio("password123");
		assert(model.getBio().equals("password123"));
	}
	@Test
	public void testSetPassword2() {
		model.setBio("test");
		assert(model.getBio().equals("test"));
		model.setBio("test 2");
		assert(model.getBio().equals("test 2"));
		model.setBio("test 3!$#");
		assert(model.getBio().equals("test 3!$#"));
		model.setBio("pass_word-");
		assert(model.getBio().equals("pass_word-"));
	}
	
	
}
