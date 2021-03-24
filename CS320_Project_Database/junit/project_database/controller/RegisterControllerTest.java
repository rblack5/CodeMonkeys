package project_database.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import project_database.model.RegisterModel;

public class RegisterControllerTest {
	private RegisterController controller;
	private RegisterModel model;

	@Before
	public void setUp() {
		model = new RegisterModel();
		controller = new RegisterController();
		controller.setModel(model);
		
	}
	
	@Test
	public void testSamePassword1() {
		model.setPassword("ooga");
		model.setPassword2("booga");
		
		assert(controller.checkSamePassword(model)==false);
	}
	
	@Test
	public void testSamePassword2() {
		model.setPassword("stackOverflow");
		model.setPassword2("StackOverflow");
		
		assert(controller.checkSamePassword(model)==false);
	}
	
	@Test
	public void testSamePassword3() {
		model.setPassword("goop");
		model.setPassword2("goop");
		
		assert(controller.checkSamePassword(model)==true);
	}
	
	@Test
	public void testSamePassword4() {
		model.setPassword("987456321");
		model.setPassword2("987456321");
		
		assert(controller.checkSamePassword(model)==true);
	}
	
	@Test
	public void testSamePassword5() {
		model.setPassword("TrixAre4Kids");
		model.setPassword2("trixAre4Kids");
		
		assert(controller.checkSamePassword(model)==false);
	}
	
	@Test
	public void testSamePassword6() {
		model.setPassword("bologna456 ");
		model.setPassword2("bologna456");
		
		assert(controller.checkSamePassword(model)==false);
	}
}
