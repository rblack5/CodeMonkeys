package project_database.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import project_database.controller.LoginController;

public class UserTest {
	private UserModel model;
	private LoginController controller;
	
	@Before
	public void setUp() {
		model = new UserModel();
		controller = new LoginController();
	}
	
	@Test
	public void testSetUsername() {
		model.setUsername("testewew");
		assert(model.getUsername().equals("testewew"));
		model.setUsername("test=2");
		assert(model.getUsername().equals("test=2"));
		model.setUsername("test___3!$#");
		assert(model.getUsername().equals("test___3!$#"));
	}
	
	@Test
	public void testSetUsername2() {
		model.setUsername("test9887");
		assert(model.getUsername().equals("test9887"));
		model.setUsername("test opop");
		assert(model.getUsername().equals("test opop"));
		model.setUsername("R Snyder");
		assert(model.getUsername().equals("R Snyder"));
	}
	
	@Test
	public void testSetUsername3() {
		model.setUsername("ooga");
		assert(model.getUsername().equals("ooga"));
		model.setUsername("booga");
		assert(model.getUsername().equals("booga"));
		model.setUsername("test 3!$#");
		assert(model.getUsername().equals("test 3!$#"));
	}
	
	@Test
	public void testSetUsername4() {
		model.setUsername("username");
		assert(model.getUsername().equals("username"));
		model.setUsername("tank-");
		assert(model.getUsername().equals("tank-"));
		model.setUsername("kino115");
		assert(model.getUsername().equals("kino115"));
	}
	
	@Test
	public void testSetUsername5() {
		model.setUsername("test''");
		assert(model.getUsername().equals("test''"));
		model.setUsername("test 2\"");
		assert(model.getUsername().equals("test 2\""));
		model.setUsername("test <>3!$#");
		assert(model.getUsername().equals("test <>3!$#"));
	}
	
	@Test
	public void testSetPassword() {
		model.setPassword("test");
		assert(model.getPassword().equals("test"));
		model.setPassword("test 2");
		assert(model.getPassword().equals("test 2"));
		model.setPassword("test 3!$#");
		assert(model.getPassword().equals("test 3!$#"));
	}
	
	@Test
	public void testSetPassword2() {
		model.setPassword("fefe");
		assert(model.getPassword().equals("fefe"));
		model.setPassword("   h");
		assert(model.getPassword().equals("   h"));
		model.setPassword("3!$#");
		assert(model.getPassword().equals("3!$#"));
	}
	
	@Test
	public void testSetPassword3() {
		model.setPassword("<>test");
		assert(model.getPassword().equals("<>test"));
		model.setPassword("//test 2");
		assert(model.getPassword().equals("//test 2"));
		model.setPassword("'test 3!$#'");
		assert(model.getPassword().equals("'test 3!$#'"));
	}
	
	@Test
	public void testSetPassword4() {
		model.setPassword("123test");
		assert(model.getPassword().equals("123test"));
		model.setPassword("test+=-_2");
		assert(model.getPassword().equals("test+=-_2"));
		model.setPassword("test[]{}3!$#");
		assert(model.getPassword().equals("test[]{}3!$#"));
	}
	
	@Test
	public void testSetPassword_2() {
		model.setPassword("test");
		assert(model.getPassword().equals("test"));
		model.setPassword("test 2");
		assert(model.getPassword().equals("test 2"));
		model.setPassword("test 3!$#");
		assert(model.getPassword().equals("test 3!$#"));
	}
	
	@Test
	public void testSetPassword_2_2() {
		model.setPassword("fefe");
		assert(model.getPassword().equals("fefe"));
		model.setPassword("   h");
		assert(model.getPassword().equals("   h"));
		model.setPassword("3!$#");
		assert(model.getPassword().equals("3!$#"));
	}
	
	@Test
	public void testSetPassword_2_3() {
		model.setPassword("<>test");
		assert(model.getPassword().equals("<>test"));
		model.setPassword("//test 2");
		assert(model.getPassword().equals("//test 2"));
		model.setPassword("'test 3!$#'");
		assert(model.getPassword().equals("'test 3!$#'"));
	}
	
	@Test
	public void testSetPassword_2_4() {
		model.setPassword("123test");
		assert(model.getPassword().equals("123test"));
		model.setPassword("test+=-_2");
		assert(model.getPassword().equals("test+=-_2"));
		model.setPassword("test[]{}3!$#");
		assert(model.getPassword().equals("test[]{}3!$#"));
	}
	
	@Test
	public void testSetUserID() {
		model.setUserID(1000);
		assert(model.getUserID() == 1000);
		model.setUserID(123);
		assert(model.getUserID() == 123);
		model.setUserID(4);
		assert(model.getUserID() == 4);
		
	}
	
	@Test
	public void testSetUserID2() {
		model.setUserID(554);
		assert(model.getUserID() == 554);
		model.setUserID(742);
		assert(model.getUserID() == 742);
		model.setUserID(732);
		assert(model.getUserID() == 732);
		
	}
	
	@Test
	public void testSetUserID3() {
		model.setUserID(866);
		assert(model.getUserID() == 866);
		model.setUserID(91);
		assert(model.getUserID() == 91);
		model.setUserID(52);
		assert(model.getUserID() == 52);
		
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
	public void testSetBio3() {
		model.setBio("test");
		assert(model.getBio().equals("test"));
		model.setBio("test 2");
		assert(model.getBio().equals("test 2"));
		model.setBio("test3!$#");
		assert(model.getBio().equals("test3!$#"));
		model.setBio("biobiobio");
		assert(model.getBio().equals("biobiobio"));
	}
	@Test
	public void testSetBio4() {
		model.setBio(" ");
		assert(model.getBio().equals(" "));
		model.setBio("test2");
		assert(model.getBio().equals("test2"));
		model.setBio("No bio");
		assert(model.getBio().equals("No bio"));
		model.setBio("Big plays, bigger player");
		assert(model.getBio().equals("Big plays, bigger player"));
	}
	
	
	@Test
	public void testSetPostTheme() {
		model.setPostTheme("Light");
		assert(model.getPostTheme().equals("Light"));
		
	}
	
	@Test
	public void testSetPostTheme2() {
		model.setPostTheme("Dark");
		assert(model.getPostTheme().equals("Dark"));
		
	}
	
	@Test
	public void testSetPostTheme3() {
		model.setPostTheme("Fire");
		assert(model.getPostTheme().equals("Fire"));
		
	}
	
	@Test
	public void testSetPostTheme4() {
		model.setPostTheme("Gold");
		assert(model.getPostTheme().equals("Gold"));
		
	}
	
	
	@Test
	public void testSetAccountTheme() {
		model.setAccountTheme("Light");
		assert(model.getAccountTheme().equals("Light"));
		
	}
	
	@Test
	public void testSetAccountTheme2() {
		model.setAccountTheme("Dark");
		assert(model.getAccountTheme().equals("Dark"));
		
	}
	
	@Test
	public void testSetDateJoined() {
		model.setDateJoined("05022001");
		assert(model.getDateJoined().equals("05022001"));
		
	}
	
	@Test
	public void testSetDateJoined2() {
		model.setDateJoined("05102021");
		assert(model.getDateJoined().equals("05102021"));
		
	}
	
	@Test
	public void testSetDateJoined3() {
		model.setDateJoined("12311919");
		assert(model.getDateJoined().equals("12311919"));
		
	}
	
	@Test
	public void testSetDateJoined4() {
		model.setDateJoined("09081954");
		assert(model.getDateJoined().equals("09081954"));
		
	}
	
	
	@Test
	public void testAdminStatus() {
		model.setAdminStatus(true);
		assertTrue(model.getAdminStatus());
		
	}
	
	@Test
	public void testAdminStatus2() {
		model.setAdminStatus(false);
		assertTrue(model.getAdminStatus()==false);
		
	}
	
	@Test
	public void testAdminStatus3() {
		model.setAdminStatus(false);
		assertFalse(model.getAdminStatus());
		
	}
	
}
