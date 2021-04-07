package project_database.controller;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import project_database.model.UserModel;

public class LoginControllerTest {
	private UserModel model, model2;
	private LoginController controller;
	
	@Before
	public void setUp() {
		model = new UserModel();
		controller = new LoginController();
	}
	
	@Test
	public void testSetModel() {
		model2 = new UserModel();
		model2.setUsername("hi");
		controller.setModel(model2);
		assert(model2.getUsername().equals("hi"));
		controller.setModel(model);
	}
	
	@Test
	public void testCheckLogIn() {
		model.setUsername("user");
		model.setPassword("password");
		assert(controller.checkLogIn(model));
	}
	
	@Test
	public void testImportCSV() {
		List<UserModel> users = controller.importCSV();
		assert(users.get(0).getUsername().equals("test"));
	}
	
}
