package project_database.model;

import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

public class RegisterModelTest {
	private LoginModel model;

	@Before
	public void setUp() {
		model = new LoginModel();
	}
	
	@Test
	public void testSetUsername1() {
		model.setUsername("test@ycp.edu");
		assert(model.getUsername().equals("test@ycp.edu"));
	}
	
	@Test
	public void testSetUsername2() {
		model.setUsername("cs320@gmail.com");
		assert(model.getUsername().equals("cs320@gmail.com"));
	}
	
	@Test
	public void testSetUsername3() {
		model.setUsername("blueMonday80@wasdpa.org");
		assert(model.getUsername().equals("blueMonday80@wasdpa.org"));
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
}
