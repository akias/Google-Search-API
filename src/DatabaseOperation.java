import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseOperation {
	private static DatabaseOperation instance = null;
	// singleton pattern
	public static DatabaseOperation getInstance() { 
		if (instance == null) {
			instance = new DatabaseOperation();
		}
		return instance;
	}

	public boolean saveSite(Result re) { // add data to database
		boolean result = false;
		Connection conn = null;
		try {

			conn = DatabaseConnection.getCon(); // get the connection
			String sqlInset = "insert into search_list(title,link) values(?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sqlInset); // throw exception
			stmt.setString(1, re.getTitle()); // the first data about sql
			stmt.setString(2, re.getLink()); // the second data about sql
			int i = stmt.executeUpdate(); // execute the operation and get how many colums is changed
			if (i == 1) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // must close the connection
			try {
				conn.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
