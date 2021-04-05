package project_database.model;

import org.junit.Before;
import org.junit.Test;

import project_database.controller.LoginController;

public class UserTest {
	private UserModel model;
	private LoginController loginController;
	
	@Before
	public void setUp() {
		model = new UserModel();
		loginController = new LoginController();
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
	public void testCreateAccountCheckLogin() {
		
	}
}
