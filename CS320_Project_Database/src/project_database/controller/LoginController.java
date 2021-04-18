package project_database.controller;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import project_database.database.*;
import project_database.model.UserModel;

public class LoginController {	
	// Obtain the path for the .csv file
	private Path pathToFile = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "users.csv");
	private UserModel model;

	
	
	public void setModel(UserModel model) {
		this.model = model;
	}
	
	public List<UserModel> importCSV() {
		List<UserModel> users = new ArrayList<>();
				
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)){
			
			// Read the first line, remove the quotations. Quotations are exported from a CSV by default.
			String line = br.readLine();
			line = line.replace("\"", "");
			
			while(line != null) {
				
				String[] attributes = line.split(",");
				
				String userID = attributes[0];
				String username = attributes[1];
				String password = attributes[2];
				
				UserModel user = new UserModel();
				user.setUserID(Integer.parseInt(userID));
				user.setUsername(username);
				user.setPassword(password);
				
				users.add(user);
				
				// Read a new line, remove the quotes.
				line = br.readLine();
				if(line != null) {
					line = line.replace("\"", "");
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public boolean checkLogIn(UserModel model) {
		// List<UserModel> users = importCSV();
		
		ViewAllUsers g = new ViewAllUsers();
		List<UserModel> users = g.getUserList();
		
		for(int i = 0; i < users.size(); i++) {		
			if(users.get(i).getUsername().equals(model.getUsername()) && users.get(i).getPassword().equals(model.getPassword())) {
				return true;
			}
		}
		return false;
	
	}
	
	public void createAccount() throws IOException {
//		Path pathToFile = Paths.get(FileSystems.getDefault().getPath("").toAbsolutePath().toString(), "users.csv");
//		List <UserModel> users = importCSV();
//		
//		FileWriter writer = new FileWriter(pathToFile.toString(), true);
//		
//		String userInfo = "\"" + String.join(",", String.valueOf(users.size()),model.getUsername(), model.getPassword()) + "\"";
//		
//		System.out.println(userInfo);
//		
//		writer.write(userInfo);
//		writer.write("\n");
//		
//		writer.close();
		
		InsertNewUser g = new InsertNewUser();
		g.insertNewUser(model.getUsername(), model.getPassword());
	}
}
