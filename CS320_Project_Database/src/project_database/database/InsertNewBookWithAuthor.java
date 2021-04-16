//package project_database.database;
//
//import java.util.List;
//import java.util.Scanner;
//
//import edu.ycp.cs320.booksdb.model.Author;
//import edu.ycp.cs320.booksdb.model.Book;
//import edu.ycp.cs320.booksdb.model.Pair;
//import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
//import edu.ycp.cs320.booksdb.persist.IDatabase;
//
//public class InsertNewBookWithAuthor {
//	public static void main(String[] args) throws Exception {
//		Scanner keyboard = new Scanner(System.in);
//
//		// Create the default IDatabase instance
//		InitDatabase.init(keyboard);
//		
//		System.out.print("Enter a first name: ");
//		String firstName = keyboard.nextLine();
//		System.out.print("Enter a last name: ");
//		String lastName = keyboard.nextLine();
//		System.out.print("Enter a title: ");
//		String title = keyboard.nextLine();
//		System.out.print("Enter an ISBN: ");
//		String ISBN = keyboard.nextLine();
//		System.out.print("Enter the year published: ");
//		int yearPublished = keyboard.nextInt();
//		keyboard.nextLine();
//		
//		// get the DB instance and execute transaction
//		IDatabase db = DatabaseProvider.getInstance();
//		db.insertNewBookWithAuthor(firstName, lastName, title, ISBN, yearPublished);
//			
//	}
//}
