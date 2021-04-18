package project_database.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project_database.model.Pair;
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

	// @Override
	public List<Pair<UserModel, PostModel>> findUserModelAndPostModelByTitle(final String title) {
		return executeTransaction(new Transaction<List<Pair<UserModel,PostModel>>>() {
			@Override
			public List<Pair<UserModel, PostModel>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					// retrieve all attributes from both PostModels and UserModels tables
					System.out.println("Checkpoint 1");
					stmt = conn.prepareStatement(
							"select Users.*, Posts.* " +
							"  from Users, Posts " +
							" where Users.userID = Posts.userID " +
							"   and Posts.postTitle = ?"
					);
					stmt.setString(1, title);
					
					System.out.println("Checkpoint 2");
					
					List<Pair<UserModel, PostModel>> result = new ArrayList<Pair<UserModel,PostModel>>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						// create new UserModel object
						// retrieve attributes from resultSet starting with index 1
						UserModel UserModel = new UserModel();
						loadUserModel(UserModel, resultSet, 1);
						
						// create new PostModel object
						// retrieve attributes from resultSet starting at index 4
						PostModel PostModel = new PostModel();
						loadPostModel(PostModel, resultSet, 4);
						
						result.add(new Pair<UserModel, PostModel>(UserModel, PostModel));
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("<" + title + "> was not found in the PostModels table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public List<Pair<UserModel, PostModel>> insertNewUser(String username, String password) {
		return executeTransaction(new Transaction<List<Pair<UserModel, PostModel>>>() {
			
			@SuppressWarnings("resource")
			@Override
			public List<Pair<UserModel, PostModel>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				String UserModel_id;
				List<Pair<UserModel, PostModel>> result = new ArrayList<Pair<UserModel,PostModel>>();
				
//				try {
//					// Prepare the statement to obtain an existing UserModel_id.
//					stmt = conn.prepareStatement(
//							"select UserModels.UserModel_id"
//							+ " from UserModels "
//							+ " where UserModels.firstname = ? and UserModels.lastname = ?"
//							);
//					
//					stmt.setString(1, username);
//					stmt.setString(2, userID);
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
					
					// Prepare the statement to insert the new PostModel into the PostModels table.
				try {
					stmt = conn.prepareStatement(
							" INSERT INTO Users (username, password) "
							+ "VALUES (?, ?) "		
							);
					
					stmt.setString(1, username);
					stmt.setString(2, password);
					
					// stmt.setInt(4, postID);
					
					// Execute the query and insert the new PostModel into the PostModels table.
					stmt.executeUpdate();
					
					return result;
				}
				finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(conn);
					}
				
			}
		});
	}
	
	public List<Pair<UserModel, PostModel>> insertNewPostWithUser(String username, int userID, String postTitle, int postID, String postBody) {
		return executeTransaction(new Transaction<List<Pair<UserModel, PostModel>>>() {
			
			@SuppressWarnings("resource")
			@Override
			public List<Pair<UserModel, PostModel>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				String UserModel_id;
				List<Pair<UserModel, PostModel>> result = new ArrayList<Pair<UserModel,PostModel>>();
				
//				try {
//					// Prepare the statement to obtain an existing UserModel_id.
//					stmt = conn.prepareStatement(
//							"select UserModels.UserModel_id"
//							+ " from UserModels "
//							+ " where UserModels.firstname = ? and UserModels.lastname = ?"
//							);
//					
//					stmt.setString(1, username);
//					stmt.setString(2, userID);
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
					
					// Prepare the statement to insert the new PostModel into the PostModels table.
				try {
					stmt = conn.prepareStatement(
							" INSERT INTO Posts (userID, postTitle, postBody) "
							+ "VALUES (?, ?, ?) "		
							);
					
					stmt.setInt(1, userID);
					stmt.setString(2, postTitle);
					stmt.setString(3, postBody);
					// stmt.setInt(4, postID);
					
					// Execute the query and insert the new PostModel into the PostModels table.
					stmt.executeUpdate();
					
					return result;
				}
				finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(conn);
					}
				
			}
		});
	}
	
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		
		// Set autocommit to false to allow execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	private void loadUserModel(UserModel UserModel, ResultSet resultSet, int index) throws SQLException {
		UserModel.setUserID(resultSet.getInt(index++));
		UserModel.setUsername(resultSet.getString(index++));
	}
	
	private void loadPostModel(PostModel PostModel, ResultSet resultSet, int index) throws SQLException {
		PostModel.setPostID(resultSet.getInt(index++));
		PostModel.setUserID(resultSet.getInt(index++));
		PostModel.setTitle(resultSet.getString(index++));	
	}
	
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				try {
//					stmt3 = conn.prepareStatement(
//							"DROP TABLE Users"
//					);	
//					stmt3.executeUpdate();
//					
//					stmt4 = conn.prepareStatement(
//							"DROP TABLE Posts"
//					);	
//					stmt4.executeUpdate();
					
					stmt1 = conn.prepareStatement(
							"create table Users (" +
							"	userID integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +									
							"	username varchar(40)," +
							"	password varchar(40)" +
							")"
					);	
					stmt1.executeUpdate();
					stmt2 = conn.prepareStatement(
							"create table Posts (" +
							"	postID integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	userID integer, " +
							"	postTitle varchar(70)," +
							"	postBody varchar(200)" +
							")"
					);
					stmt2.executeUpdate();
					//	"	UserID integer constraint UserModel_id references UserModels, " +
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	
//	public void loadInitialData() {
//		executeTransaction(new Transaction<Boolean>() {
//			@Override
//			public Boolean execute(Connection conn) throws SQLException {
//				List<UserModel> UserModelList;
//				List<PostModel> PostModelList;
//				
//				try {
//					UserModelList = InitialData.getUserModels();
//					PostModelList = InitialData.getPostModels();
//				} catch (IOException e) {
//					throw new SQLException("Couldn't read initial data", e);
//				}
//
//				PreparedStatement insertUserModel = null;
//				PreparedStatement insertPostModel   = null;
//
//				try {
//					// populate UserModels table (do UserModels first, since UserModel_id is foreign key in PostModels table)
//					insertUserModel = conn.prepareStatement("insert into UserModels (lastname, firstname) values (?, ?)");
//					for (UserModel UserModel : UserModelList) {
////						insertUserModel.setInt(1, UserModel.getUserModelId());	// auto-generated primary key, don't insert this
//						insertUserModel.setString(1, UserModel.getLastname());
//						insertUserModel.setString(2, UserModel.getFirstname());
//						insertUserModel.addBatch();
//					}
//					insertUserModel.executeBatch();
//					
//					// populate PostModels table (do this after UserModels table,
//					// since UserModel_id must exist in UserModels table before inserting PostModel)
//					insertPostModel = conn.prepareStatement("insert into PostModels (UserModel_id, title, isbn, published) values (?, ?, ?, ?)");
//					for (PostModel PostModel : PostModelList) {
////						insertPostModel.setInt(1, PostModel.getPostModelId());		// auto-generated primary key, don't insert this
//						insertPostModel.setInt(1, PostModel.getUserModelId());
//						insertPostModel.setString(2, PostModel.getTitle());
//						insertPostModel.setString(3, PostModel.getIsbn());
//						insertPostModel.setInt(4,  PostModel.getPublished());
//						insertPostModel.addBatch();
//					}
//					insertPostModel.executeBatch();
//					
//					return true;
//				} finally {
//					DBUtil.closeQuietly(insertPostModel);
//					DBUtil.closeQuietly(insertUserModel);
//				}
//			}
//		});
//	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		// System.out.println("Loading initial data...");
		// db.loadInitialData();
		
		System.out.println("Success!");
	}

}
