package project_database.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import project_database.model.LoginModel;
import project_database.model.UserModel;

public class LoginController {
	private List<UserModel> users = new ArrayList<>();
	private Path pathToFile = Paths.get("C:\\Users\\Ryan\\eclipse-CS320-TeamProject\\CodeMonkeys\\CS320_Project_Database\\users.csv");
	
	public void importCSV() {
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)){
			
			String line = br.readLine();
			
			System.out.println(line);
			
			while(line != null) {
				
				String[] attributes = line.split(",");
				
				String username = attributes[0];
				String password = attributes[1];
				
				UserModel user = new UserModel();
				user.setUsername(username);
				user.setPassword(password);
				
				users.add(user);
				
				line = br.readLine();
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public boolean checkLogIn(UserModel model) {
		
		importCSV();
		
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getUsername() == model.getUsername() && users.get(i).getPassword() == model.getPassword()) {
				return true;
			}
		}
		return false;
	
	}
}
