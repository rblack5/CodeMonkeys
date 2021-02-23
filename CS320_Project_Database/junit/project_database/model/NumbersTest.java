package project_database.model;

import org.junit.Before;
import org.junit.Test;

import project_database.model.Numbers;

public class NumbersTest {
	private Numbers model;

	@Before
	public void setUp() {
		model = new Numbers();
	}
	
	@Test
	public void testSetFirst() {
		model.setFirst(10);
		assert(model.getFirst()==10);
	}
	
	@Test
	public void testSetSecond() {
		model.setSecond(9);
		assert(model.getSecond()==9);
	}
	
	@Test
	public void testSetThird() {
		model.setThird(1);
		assert(model.getThird()==1);
	}
	
	@Test
	public void testSetResult() {
		model.setResult(3);
		assert(model.getResult()==3);
	}	
}
