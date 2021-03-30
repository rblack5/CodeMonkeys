package project_database.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import project_database.model.UserModel;

public class LoginTest {
	UserModel model;
	LoginController controller;
	@Before
	public void setup() {
	model = new UserModel();
	controller = new LoginController();
	
	model.setUsername("admin");
	model.setPassword("admin");
	}
	
	
	@Test
	public void testCheckLogIn() {
		Boolean testCase = controller.checkLogIn(model);
		
		assertTrue(testCase);
	}
	

}
