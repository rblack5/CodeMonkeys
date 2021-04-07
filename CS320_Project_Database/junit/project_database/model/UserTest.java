package project_database.model;

import static org.junit.Assert.assertArrayEquals;

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
		model.setUsername("test");
		assert(model.getUsername().equals("test"));
		model.setUsername("test 2");
		assert(model.getUsername().equals("test 2"));
		model.setUsername("test 3!$#");
		assert(model.getUsername().equals("test 3!$#"));
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
	public void testSetUserID() {
		model.setUserID(1000);
		assert(model.getUserID() == 1000);
		model.setUserID(123);
		assert(model.getUserID() == 123);
		model.setUserID(4);
		assert(model.getUserID() == 4);
		
	}
	
	@Test
	public void testCreateAccountCheckLogin() throws IOException {
		model.setUsername("test");
		model.setPassword("test");
		
		controller.setModel(model);
		controller.createAccount();
		
		assert(controller.checkLogIn(model));
	}
}
