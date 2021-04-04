package project_database.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;


import project_database.model.RegisterModel;

public class RegisterController {
	private RegisterModel model;

	public void setModel(RegisterModel model) {
		this.model = model;
	}
	
	public boolean checkSamePassword(RegisterModel model) {
		if(model.getPassword().equals(model.getPassword2())) {
			return true;
		}
		return false;
	}
	
	
	public void createAccount() throws IOException {
		Path pathToFile = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "users.csv");
		
		FileWriter writer = new FileWriter(pathToFile.toString(), true);
		
		String userInfo = "\"" + String.join(",", model.getUsername(), model.getPassword()) + "\"";
		
		System.out.println(userInfo);
		
		writer.write(userInfo);
		writer.write("\n");
		
		writer.close();
	}
}
