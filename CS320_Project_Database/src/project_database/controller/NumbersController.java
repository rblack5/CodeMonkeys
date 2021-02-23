package project_database.controller;

import project_database.model.Numbers;

public class NumbersController {
	private Numbers model;
	
	public void setModel(Numbers model) {
		this.model= model;
	}
	
	public void add() {
		this.model.setResult(model.getFirst() + model.getSecond() + model.getThird());
	}
	
	public void multiply() {
		this.model.setResult(model.getFirst()*model.getSecond());
	}
}
