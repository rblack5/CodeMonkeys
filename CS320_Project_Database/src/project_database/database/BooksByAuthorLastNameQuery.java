//package project_database.database;
//
//import java.util.List;
//import java.util.Scanner;
//
//import project_database.model.Pair;
//import project_database.persist.DatabaseProvider;
//import project_database.persist.IDatabase;
//
//public class BooksByAuthorLastNameQuery {
//	public static void main(String[] args) throws Exception {
//		Scanner keyboard = new Scanner(System.in);
//
//		// Create the default IDatabase instance
//		InitDatabase.init(keyboard);
//		
//		System.out.print("Enter the author's last name: ");
//		String lastName = keyboard.nextLine();
//		
//		// get the DB instance and execute transaction
//		IDatabase db = DatabaseProvider.getInstance();
//		List<Pair<Author, Book>> authorBookList = db.findAuthorAndBookByLastname(lastName);
//		
//		// check if anything was returned and output the list
//		if (authorBookList.isEmpty()) {
//			System.out.println("No books found with author <" + lastName + ">");
//		}
//		else {
//			for (Pair<Author, Book> authorBook : authorBookList) {
//				Author author = authorBook.getLeft();
//				Book book = authorBook.getRight();
//				System.out.println(author.getLastname() + "," + author.getFirstname() + "," + book.getTitle() + "," + book.getIsbn() + "," + book.getPublished());
//			}
//		}
//	}
//}
