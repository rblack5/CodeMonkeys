package project_database.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project_database.model.UserModel;
import project_database.model.PostModel;

public class DerbyDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

//	@Override
//	public List<Pair<UserModel, Book>> findUserModelAndBookByTitle(final String title) {
//		return executeTransaction(new Transaction<List<Pair<UserModel,Book>>>() {
//			@Override
//			public List<Pair<UserModel, Book>> execute(Connection conn) throws SQLException {
//				PreparedStatement stmt = null;
//				ResultSet resultSet = null;
//				
//				try {
//					// retrieve all attributes from both Books and UserModels tables
//					stmt = conn.prepareStatement(
//							"select UserModels.*, books.* " +
//							"  from UserModels, books " +
//							" where UserModels.UserModel_id = books.UserModel_id " +
//							"   and books.title = ?"
//					);
//					stmt.setString(1, title);
//					
//					List<Pair<UserModel, Book>> result = new ArrayList<Pair<UserModel,Book>>();
//					
//					resultSet = stmt.executeQuery();
//					
//					// for testing that a result was returned
//					Boolean found = false;
//					
//					while (resultSet.next()) {
//						found = true;
//						
//						// create new UserModel object
//						// retrieve attributes from resultSet starting with index 1
//						UserModel UserModel = new UserModel();
//						loadUserModel(UserModel, resultSet, 1);
//						
//						// create new Book object
//						// retrieve attributes from resultSet starting at index 4
//						Book book = new Book();
//						loadBook(book, resultSet, 4);
//						
//						result.add(new Pair<UserModel, Book>(UserModel, book));
//					}
//					
//					// check if the title was found
//					if (!found) {
//						System.out.println("<" + title + "> was not found in the books table");
//					}
//					
//					return result;
//				} finally {
//					DBUtil.closeQuietly(resultSet);
//					DBUtil.closeQuietly(stmt);
//				}
//			}
//		});
//	}
	
//	public List<Pair<UserModel, Book>> findUserModelAndBookByLastname(final String lastName) {
//		return executeTransaction(new Transaction<List<Pair<UserModel,Book>>>() {
//			@Override
//			public List<Pair<UserModel, Book>> execute(Connection conn) throws SQLException {
//				PreparedStatement stmt = null;
//				ResultSet resultSet = null;
//				
//				try {
//					// retrieve all attributes from both Books and UserModels tables
//					stmt = conn.prepareStatement(
//							"select UserModels.*, books.* "
//							+ "  from UserModels, books "
//							+ "  where UserModels.UserModel_id = books.UserModel_id "
//							+ "        and UserModels.lastname = ?"
//							+ "        order by books.title ASC"
//					);
//					stmt.setString(1, lastName);
//					
//					List<Pair<UserModel, Book>> result = new ArrayList<Pair<UserModel,Book>>();
//					
//					resultSet = stmt.executeQuery();
//					
//					// for testing that a result was returned
//					Boolean found = false;
//					
//					while (resultSet.next()) {
//						found = true;
//						
//						// create new UserModel object
//						// retrieve attributes from resultSet starting with index 1
//						UserModel UserModel = new UserModel();
//						loadUserModel(UserModel, resultSet, 1);
//						
//						// create new Book object
//						// retrieve attributes from resultSet starting at index 4
//						Book book = new Book();
//						loadBook(book, resultSet, 4);
//						
//						result.add(new Pair<UserModel, Book>(UserModel, book));
//					}
//					
//					// check if the title was found
//					if (!found) {
//						System.out.println("<" + lastName + "> was not found in the books table");
//					}
//					
//					return result;
//				} finally {
//					DBUtil.closeQuietly(resultSet);
//					DBUtil.closeQuietly(stmt);
//				}
//			}
//		});
//	}
//	
//	public List<Pair<UserModel, Book>> insertNewBookWithUserModel(String firstName, String lastName, String title, String ISBN, int yearPublished) {
//		return executeTransaction(new Transaction<List<Pair<UserModel, Book>>>() {
//			
//			@SuppressWarnings("resource")
//			@Override
//			public List<Pair<UserModel, Book>> execute(Connection conn) throws SQLException {
//				PreparedStatement stmt = null;
//				ResultSet resultSet = null;
//				String UserModel_id;
//				List<Pair<UserModel, Book>> result = new ArrayList<Pair<UserModel,Book>>();
//				
//				try {
//					// Prepare the statement to obtain an existing UserModel_id.
//					stmt = conn.prepareStatement(
//							"select UserModels.UserModel_id"
//							+ " from UserModels "
//							+ " where UserModels.firstname = ? and UserModels.lastname = ?"
//							);
//					
//					stmt.setString(1, firstName);
//					stmt.setString(2, lastName);
//					
//					// Return the UserModel_id for the existing UserModel. If no UserModel_id is returned, move onto the else statement.
//					resultSet = stmt.executeQuery();
//					resultSet.next();
//					
//					if(resultSet.next()) {
//						UserModel_id = resultSet.getString("UserModel_id");
//					}
//					else {
//						// Prepare the insert statement for the new UserModel.
//						stmt = conn.prepareStatement(
//								" INSERT INTO UserModels (firstname, lastname) "
//								+ " VALUES (?, ?)"
//								);
//						
//						stmt.setString(1, firstName);
//						stmt.setString(2, lastName);
//						
//						// **Insert the new UserModel**
//						// ResultSet.executeUpdate is used for statements such as INSERT, UPDATE, and DELETE,
//						// while ResultSet.executeQuery is used for statements that return tabular data.
//						
//						stmt.executeUpdate();				
//						
//						// Obtain the new UserModel_id
//						stmt = conn.prepareStatement(
//								"select UserModels.UserModel_id"
//								+ " from UserModels "
//								+ " where UserModels.firstname = ? and UserModels.lastname = ?"
//								);
//						
//						stmt.setString(1, firstName);
//						stmt.setString(2, lastName);
//						
//						resultSet = stmt.executeQuery();
//						resultSet.next();
//						
//						UserModel_id = resultSet.getString("UserModel_id");
//					}
//					
//					// Prepare the statement to insert the new book into the books table. 
//					stmt = conn.prepareStatement(
//							" INSERT INTO books (UserModel_id, title, ISBN, published) "
//							+ "VALUES (?, ?, ?, ?) "		
//							);
//					
//					stmt.setString(1, UserModel_id);
//					stmt.setString(2, title);
//					stmt.setString(3, ISBN);
//					stmt.setInt(4, yearPublished);
//					
//					// Execute the query and insert the new book into the books table.
//					stmt.executeUpdate();
//					
//					return result;
//					
//				} finally {
//					DBUtil.closeQuietly(resultSet);
//					DBUtil.closeQuietly(stmt);
//					DBUtil.closeQuietly(conn);
//				}
//			}
//		});
//	}
//	
//	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
//		try {
//			return doExecuteTransaction(txn);
//		} catch (SQLException e) {
//			throw new PersistenceException("Transaction failed", e);
//		}
//	}
//	
//	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
//		Connection conn = connect();
//		
//		try {
//			int numAttempts = 0;
//			boolean success = false;
//			ResultType result = null;
//			
//			while (!success && numAttempts < MAX_ATTEMPTS) {
//				try {
//					result = txn.execute(conn);
//					conn.commit();
//					success = true;
//				} catch (SQLException e) {
//					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
//						// Deadlock: retry (unless max retry count has been reached)
//						numAttempts++;
//					} else {
//						// Some other kind of SQLException
//						throw e;
//					}
//				}
//			}
//			
//			if (!success) {
//				throw new SQLException("Transaction failed (too many retries)");
//			}
//			
//			// Success!
//			return result;
//		} finally {
//			DBUtil.closeQuietly(conn);
//		}
//	}
//
//	private Connection connect() throws SQLException {
//		Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
//		
//		// Set autocommit to false to allow execution of
//		// multiple queries/statements as part of the same transaction.
//		conn.setAutoCommit(false);
//		
//		return conn;
//	}
//	
//	private void loadUserModel(UserModel UserModel, ResultSet resultSet, int index) throws SQLException {
//		UserModel.setUserModelId(resultSet.getInt(index++));
//		UserModel.setLastname(resultSet.getString(index++));
//		UserModel.setFirstname(resultSet.getString(index++));
//	}
//	
//	private void loadBook(Book book, ResultSet resultSet, int index) throws SQLException {
//		book.setBookId(resultSet.getInt(index++));
//		book.setUserModelId(resultSet.getInt(index++));
//		book.setTitle(resultSet.getString(index++));
//		book.setIsbn(resultSet.getString(index++));
//		book.setPublished(resultSet.getInt(index++));		
//	}
//	
//	public void createTables() {
//		executeTransaction(new Transaction<Boolean>() {
//			@Override
//			public Boolean execute(Connection conn) throws SQLException {
//				PreparedStatement stmt1 = null;
//				PreparedStatement stmt2 = null;
//				
//				try {
//					stmt1 = conn.prepareStatement(
//						"create table UserModels (" +
//						"	UserModel_id integer primary key " +
//						"		generated always as identity (start with 1, increment by 1), " +									
//						"	lastname varchar(40)," +
//						"	firstname varchar(40)" +
//						")"
//					);	
//					stmt1.executeUpdate();
//					
//					stmt2 = conn.prepareStatement(
//							"create table books (" +
//							"	book_id integer primary key " +
//							"		generated always as identity (start with 1, increment by 1), " +
//							"	UserModel_id integer constraint UserModel_id references UserModels, " +
//							"	title varchar(70)," +
//							"	isbn varchar(15)," +
//							"   published integer " +
//							")"
//					);
//					stmt2.executeUpdate();
//					
//					return true;
//				} finally {
//					DBUtil.closeQuietly(stmt1);
//					DBUtil.closeQuietly(stmt2);
//				}
//			}
//		});
//	}
//	
//	public void loadInitialData() {
//		executeTransaction(new Transaction<Boolean>() {
//			@Override
//			public Boolean execute(Connection conn) throws SQLException {
//				List<UserModel> UserModelList;
//				List<Book> bookList;
//				
//				try {
//					UserModelList = InitialData.getUserModels();
//					bookList = InitialData.getBooks();
//				} catch (IOException e) {
//					throw new SQLException("Couldn't read initial data", e);
//				}
//
//				PreparedStatement insertUserModel = null;
//				PreparedStatement insertBook   = null;
//
//				try {
//					// populate UserModels table (do UserModels first, since UserModel_id is foreign key in books table)
//					insertUserModel = conn.prepareStatement("insert into UserModels (lastname, firstname) values (?, ?)");
//					for (UserModel UserModel : UserModelList) {
////						insertUserModel.setInt(1, UserModel.getUserModelId());	// auto-generated primary key, don't insert this
//						insertUserModel.setString(1, UserModel.getLastname());
//						insertUserModel.setString(2, UserModel.getFirstname());
//						insertUserModel.addBatch();
//					}
//					insertUserModel.executeBatch();
//					
//					// populate books table (do this after UserModels table,
//					// since UserModel_id must exist in UserModels table before inserting book)
//					insertBook = conn.prepareStatement("insert into books (UserModel_id, title, isbn, published) values (?, ?, ?, ?)");
//					for (Book book : bookList) {
////						insertBook.setInt(1, book.getBookId());		// auto-generated primary key, don't insert this
//						insertBook.setInt(1, book.getUserModelId());
//						insertBook.setString(2, book.getTitle());
//						insertBook.setString(3, book.getIsbn());
//						insertBook.setInt(4,  book.getPublished());
//						insertBook.addBatch();
//					}
//					insertBook.executeBatch();
//					
//					return true;
//				} finally {
//					DBUtil.closeQuietly(insertBook);
//					DBUtil.closeQuietly(insertUserModel);
//				}
//			}
//		});
//	}
//	
//	// The main method creates the database tables and loads the initial data.
//	public static void main(String[] args) throws IOException {
//		System.out.println("Creating tables...");
//		DerbyDatabase db = new DerbyDatabase();
//		db.createTables();
//		
//		System.out.println("Loading initial data...");
//		db.loadInitialData();
//		
//		System.out.println("Success!");
//	}
}
