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
	public List<Pair<UserModel, PostModel>> findUserModelAndPostModelByTitle(final String postTitle, final String postID) {
		return executeTransaction(new Transaction<List<Pair<UserModel,PostModel>>>() {
			@Override
			public List<Pair<UserModel, PostModel>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					// retrieve all attributes from both PostModels and UserModels tables
					stmt = conn.prepareStatement(
							"select Users.*, Posts.* " +
							"  from Users, Posts " +
							" where Users.userID = Posts.userID " +
							"   and Posts.postTitle = ? and Posts.postID = ?"
					);
					stmt.setString(1, postTitle);
					stmt.setString(2, postID);
					
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
						System.out.println("<" + postTitle + "> with ID <" + postID + "> of was not found in the PostModels table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public List<UserModel> viewAllUsers() {
		return executeTransaction(new Transaction<List<UserModel>>() {
			@Override
			public List<UserModel> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					// retrieve all attributes from both PostModels and UserModels tables
					stmt = conn.prepareStatement(
							"select Users.*" +
							"  from Users"
					);
					List<UserModel> result = new ArrayList<UserModel>();
					
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						// create new UserModel object
						// retrieve attributes from resultSet starting with index 1
						UserModel UserModel = new UserModel();
						loadUserModel(UserModel, resultSet, 1);
						
						result.add(UserModel);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("No users found.");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public List<PostModel> viewAllPosts() {
		return executeTransaction(new Transaction<List<PostModel>>() {
			@Override
			public List<PostModel> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					// retrieve all attributes from both PostModels and UserModels tables
					stmt = conn.prepareStatement(
							"select Posts.* " +
							"  from Posts"
					);
					List<PostModel> result = new ArrayList<PostModel>();
					
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						// create new PostModel object
						// retrieve attributes from resultSet starting at index 4
						PostModel PostModel = new PostModel();
						loadPostModel(PostModel, resultSet, 1);
						
						result.add(PostModel);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("No posts found.");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public UserModel findMatchingUserByUserID(int userID) {
		return executeTransaction(new Transaction<UserModel>() {
			@Override
			public UserModel execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					// retrieve all attributes from both PostModels and UserModels tables
					stmt = conn.prepareStatement(
							"select Users.*" +
							"  from Users" +
							" where Users.userID = ? "
					);
					stmt.setInt(1, userID);
					UserModel result = new UserModel();
					
					
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					Boolean found = false;
					
					
					while (resultSet.next()) {
						found = true;
						
						// create new UserModel object
						// retrieve attributes from resultSet starting with index 1
						UserModel UserModel = new UserModel();
						loadUserModel(UserModel, resultSet, 1);
						
						result = UserModel;
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("No users found.");
					}
					
					
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public UserModel findMatchingUserByUsername(String username) {
		return executeTransaction(new Transaction<UserModel>() {
			@Override
			public UserModel execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					// retrieve all attributes from both PostModels and UserModels tables
					stmt = conn.prepareStatement(
							"select Users.*" +
							"  from Users" +
							" where Users.username = ? "
					);
					stmt.setString(1, username);
					UserModel result = new UserModel();
					
					
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					Boolean found = false;
					
					
					while (resultSet.next()) {
						found = true;
						
						// create new UserModel object
						// retrieve attributes from resultSet starting with index 1
						UserModel UserModel = new UserModel();
						loadUserModel(UserModel, resultSet, 1);
						
						result = UserModel;
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("No users found.");
					}
					
					
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	
	public List<Pair<UserModel, PostModel>> insertNewUser(String username, String password, String bio) {
		return executeTransaction(new Transaction<List<Pair<UserModel, PostModel>>>() {
			
			@SuppressWarnings("resource")
			@Override
			public List<Pair<UserModel, PostModel>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				String UserModel_id;
				List<Pair<UserModel, PostModel>> result = new ArrayList<Pair<UserModel,PostModel>>();
					
					// Prepare the statement to insert the new PostModel into the PostModels table.
				try {
					stmt = conn.prepareStatement(
							" INSERT INTO Users (username, password) "
							+ "VALUES (?, ?) "		
							);
					
					stmt.setString(1, username);
					stmt.setString(2, password);
					
					
					
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
	
	public List<Pair<UserModel, PostModel>> insertNewPost(int userID, String username, String postTitle, String postBody) {
		return executeTransaction(new Transaction<List<Pair<UserModel, PostModel>>>() {
			
			@SuppressWarnings("resource")
			@Override
			public List<Pair<UserModel, PostModel>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				String UserModel_id;
				List<Pair<UserModel, PostModel>> result = new ArrayList<Pair<UserModel,PostModel>>();
					
					// Prepare the statement to insert the new PostModel into the PostModels table.
				try {
					stmt = conn.prepareStatement(
							" INSERT INTO Posts (userID, username, postTitle, postBody) "
							+ "VALUES (?, ?, ?, ?) "		
							);
					
					stmt.setInt(1, userID);
					stmt.setString(2, username);
					stmt.setString(3, postTitle);
					stmt.setString(4, postBody);

					
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
		UserModel.setPassword(resultSet.getString(index++));
	}
	
	private void loadPostModel(PostModel PostModel, ResultSet resultSet, int index) throws SQLException {
		PostModel.setPostID(resultSet.getInt(index++));
		PostModel.setUserID(resultSet.getInt(index++));
		PostModel.setUsername(resultSet.getString(index++));
		PostModel.setTitle(resultSet.getString(index++));
		PostModel.setBody(resultSet.getString(index++));	
	}
	
	public void dropAllTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				try {
					stmt3 = conn.prepareStatement(
							"DROP TABLE Users"
					);	
					stmt3.executeUpdate();
					
					stmt4 = conn.prepareStatement(
							"DROP TABLE Posts"
					);	
					stmt4.executeUpdate();
					return true;
				} finally {
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
				}
			}
		});
	}
	
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				try {		
					stmt1 = conn.prepareStatement(
							"create table Users (" +
							"	userID integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +									
							"	username varchar(40)," +
							"	password varchar(40)" +
							"	bio varchar(40)," +
							")"
					);	
					stmt1.executeUpdate();
					stmt2 = conn.prepareStatement(
							"create table Posts (" +
							"	postID integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	userID integer, " +
							"	username varchar(40)," +
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
