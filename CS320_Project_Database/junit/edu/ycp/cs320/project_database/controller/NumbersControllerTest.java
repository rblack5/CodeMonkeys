package edu.ycp.cs320.project_database.controller;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.project_database.model.Numbers;
import project_database.controller.NumbersController;

public class NumbersControllerTest {
	private NumbersController controller;
	private Numbers model;

	@Before
	public void setUp() {
		model = new Numbers();
		controller = new NumbersController();
		controller.setModel(model);
		
		model.setFirst(10);
		model.setSecond(9);
		model.setThird(1);
	}
	
	@Test
	public void testAdd() {
		controller.add();
		assert(model.getResult() == 20);
		
		model.setFirst(20);
		controller.add();
		assert(model.getResult() == 30);
		
		model.setThird(11);
		controller.add();
		assert(model.getResult() == 40);
	}
	
	@Test
	public void testMultiply() {
		controller.multiply();
		assert(model.getResult() == 90);
		
		model.setFirst(20);
		controller.multiply();
		assert(model.getResult() == 180);
		
		model.setFirst(2);
		controller.multiply();
		assert(model.getResult() == 18);
	}
}
