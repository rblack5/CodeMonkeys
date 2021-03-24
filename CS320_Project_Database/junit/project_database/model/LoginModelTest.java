package project_database.model;

import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

import project_database.model.LoginModel;

public class LoginModelTest {
	private LoginModel model;

	@Before
	public void setUp() {
		model = new LoginModel();
	}
	
	@Test
	public void testSetEmail1() {
		model.setEmail("test@ycp.edu");
		assert(model.getEmail().equals("test@ycp.edu"));
	}
	
	@Test
	public void testSetEmail2() {
		model.setEmail("cs320@gmail.com");
		assert(model.getEmail().equals("cs320@gmail.com"));
	}
	
	@Test
	public void testSetEmail3() {
		model.setEmail("blueMonday80@wasdpa.org");
		assert(model.getEmail().equals("blueMonday80@wasdpa.org"));
	}
	
	@Test
	public void testSetPassword1() {
		model.setPassword("password");
		assert(model.getPassword().equals("password"));
	}
	
	@Test
	public void testSetPassword2() {
		model.setPassword("abc123$");
		assert(model.getPassword().equals("abc123$"));
	}
	
	@Test
	public void testSetPassword3() {
		model.setPassword("caseSensitive");
		assertNotEquals(model.getPassword(), "CASEsensitive");
	}
	
	@Test
	public void testIsLoggedIn1() {
		model.setLoggedIn(true);
		assert(model.getLoggedIn());
	}
}
