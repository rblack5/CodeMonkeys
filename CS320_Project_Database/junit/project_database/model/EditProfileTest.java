package project_database.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		model.setPassword("test");
		assert(model.getPassword().equals("test"));
		model.setPassword("test 2");
		assert(model.getPassword().equals("test 2"));
		model.setPassword("test 3!$#");
		assert(model.getPassword().equals("test 3!$#"));
		model.setPassword("password123");
		assert(model.getPassword().equals("password123"));
	}
	@Test
	public void testSetPassword2() {
		model.setPassword("test");
		assert(model.getPassword().equals("test"));
		model.setPassword("test 2");
		assert(model.getPassword().equals("test 2"));
		model.setPassword("test 3!$#");
		assert(model.getPassword().equals("test 3!$#"));
		model.setPassword("pass_word-");
		assert(model.getPassword().equals("pass_word-"));
	}
	
	@Test
	public void testSamePass() {
		model.setPassword("Pass3");
		model.setPassword2("Pass3");
		assertTrue(model.getPassword().equals(model.getPassword2()));
		model.setPassword2("pass3");
		assertFalse(model.getPassword().equals(model.getPassword2()));
	}
	
	@Test
	public void testSamePass2() {
		model.setPassword("Cs159");
		model.setPassword2("Cs159");
		assertTrue(model.getPassword().equals(model.getPassword2()));
		model.setPassword2("CS159");
		assertFalse(model.getPassword().equals(model.getPassword2()));
	}
	
	@Test
	public void testSamePass3() {
		model.setPassword("money$");
		model.setPassword2("money$");
		assertTrue(model.getPassword().equals(model.getPassword2()));
		model.setPassword2("money$ ");
		assertFalse(model.getPassword().equals(model.getPassword2()));
	}
	
	@Test
	public void testSamePass4() {
		model.setPassword("oogabooga");
		model.setPassword2("oogabooga");
		assertTrue(model.getPassword().equals(model.getPassword2()));
		model.setPassword2("oogabooga_");
		assertFalse(model.getPassword().equals(model.getPassword2()));
	}
	
	@Test
	public void testValidPass() {
		model.setPassword("oogabooga");
		model.setPassword2("oogabooga");
		assertTrue(model.getPassword().equals(model.getPassword2()));
		
		char index;
	    boolean hasCap = false;
	    boolean hasLow = false;
	    boolean hasNum = false;
	    for(int i=0;i < model.getPassword().length();i++) {
	        index = model.getPassword().charAt(i);
	        if(Character.isDigit(index)){
	            hasNum = true;
	        }else if (Character.isLowerCase(index)) {
	            hasLow = true;
	        }else if (Character.isUpperCase(index)) {
	            hasCap = true;
	        }
	        assertFalse(hasNum && hasCap && hasLow);
	            
	    }
	
	}
	
	
	@Test
	public void testValidPass2() {
		model.setPassword("beauty2");
		model.setPassword2("beauty2");
		assertTrue(model.getPassword().equals(model.getPassword2()));
		
		char index;
	    boolean hasCap = false;
	    boolean hasLow = false;
	    boolean hasNum = false;
	    for(int i=0;i < model.getPassword().length();i++) {
	        index = model.getPassword().charAt(i);
	        if(Character.isDigit(index)){
	            hasNum = true;
	        }else if (Character.isLowerCase(index)) {
	            hasLow = true;
	        }else if (Character.isUpperCase(index)) {
	            hasCap = true;
	        }
	        assertFalse(hasNum && hasCap && hasLow);
	            
	    }
	
	}
	
	@Test
	public void testValidPass3() {
		model.setPassword("CAPlow123");
		model.setPassword2("CAPlow123");
		assertTrue(model.getPassword().equals(model.getPassword2()));
		
		char index;
	    boolean hasCap = false;
	    boolean hasLow = false;
	    boolean hasNum = false;
	    for(int i=0;i < model.getPassword().length();i++) {
	        index = model.getPassword().charAt(i);
	        if(Character.isDigit(index)){
	            hasNum = true;
	        }else if (Character.isLowerCase(index)) {
	            hasLow = true;
	        }else if (Character.isUpperCase(index)) {
	            hasCap = true;
	        }      
	    }
	  assertTrue(hasNum && hasCap && hasLow);
	
	}
	
	@Test
	public void testValidPass4() {
		model.setPassword("CAPlow123");
		model.setPassword2("CAPlow124");
		assertFalse(model.getPassword().equals(model.getPassword2()));
		
		char index;
	    boolean hasCap = false;
	    boolean hasLow = false;
	    boolean hasNum = false;
	    for(int i=0;i < model.getPassword().length();i++) {
	        index = model.getPassword().charAt(i);
	        if(Character.isDigit(index)){
	            hasNum = true;
	        }else if (Character.isLowerCase(index)) {
	            hasLow = true;
	        }else if (Character.isUpperCase(index)) {
	            hasCap = true;
	        }      
	    }
	  assertTrue(hasNum && hasCap && hasLow);
	
	}
}
