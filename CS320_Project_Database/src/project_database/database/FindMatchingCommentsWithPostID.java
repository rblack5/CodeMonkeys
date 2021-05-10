package project_database.database;

import java.util.List;
import java.util.Scanner;

import project_database.model.CommentModel;
import project_database.model.Pair;
import project_database.model.PostModel;
import project_database.model.UserModel;
import project_database.persist.DerbyDatabase;
// import project_database.persist.DatabaseProvider;
// import project_database.persist.IDatabase;

public class FindMatchingCommentsWithPostID {
	public List<CommentModel> findMatchingCommentsWithPostID(int postID) {
		DerbyDatabase db = new DerbyDatabase();
		List<CommentModel> commentList = db.findMatchingCommentsWithPostID(postID);
		return commentList;
	}
}
