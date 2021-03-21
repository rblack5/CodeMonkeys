package project_database.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import project_database.controller.GuessingGameController;
import project_database.model.GuessingGame;
import project_database.model.LoginModel;
import project_database.model.Numbers;

public class LoginControllerTest {
	private LoginController controller;
	private LoginModel model;

	@Before
	public void setUp() {
		model = new LoginModel();
		controller = new LoginController();
		controller.setModel(model);
		
	}
	
	@Test
	public void testCheckLogIn() {
		model.setPassword("hunter2");
		assertTrue(controller.checkLogIn(model));
	}
}
