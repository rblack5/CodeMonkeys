package project_database.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import project_database.controller.LoginController;

public class LoginTest {
	private LoginModel model;
	private LoginController controller;
	
	@Before
	public void setUp() {
		model = new LoginModel();
		controller = new LoginController();
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
	public void testValidPass() {
		model.setPassword("oogabooga");
		
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
