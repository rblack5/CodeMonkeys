package project_database.controller;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import project_database.model.UserModel;

public class LoginControllerTest {
	private UserModel model, model1, model2, model3, model4, model5;
	private LoginController controller;
	
	@Before
	public void setUp() {
		model = new UserModel();
		controller = new LoginController();
	}
	
	@Test
	public void testSetModel() {
		model1 = new UserModel();
		model1.setUsername("hi");
		controller.setModel(model1);
		assert(model1.getUsername().equals("hi"));
		controller.setModel(model);
	}
	
	@Test
	public void testSetModel2() {
		model2 = new UserModel();
		model2.setUsername("name with space");
		controller.setModel(model2);
		assert(model2.getUsername().equals("name with space"));
		controller.setModel(model);
	}
	
	@Test
	public void testSetModel3() {
		model3 = new UserModel();
		model3.setUsername("nameWith'Character");
		controller.setModel(model3);
		assert(model3.getUsername().equals("nameWith'Character"));
		controller.setModel(model);
	}
	
	@Test
	public void testSetModel4() {
		model4 = new UserModel();
		model4.setUsername("nameWith\"Quote");
		controller.setModel(model4);
		assert(model4.getUsername().equals("nameWith\"Quote"));
		controller.setModel(model);
	}
	
	@Test
	public void testSetModel5() {
		model5 = new UserModel();
		model5.setUsername("nAMeWiThCAseSeNSiTiVE");
		controller.setModel(model5);
		assert(model5.getUsername()!=("NaMEwItHcaSeSenSItivE"));
		controller.setModel(model);
	}
	
	
	
	@Test
	public void testImportCSV() {
		List<UserModel> users = controller.importCSV();
		assert(users.get(0).getUsername().equals("test"));
	}
	
}
