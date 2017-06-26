import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	private static Connection conn = null;

	public static Connection getCon() {
		try {
			// connection with database
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			// password
			String psw = "root123";
			// quick-ticket-data is the name of database
			String url = "jdbc:mysql://localhost:3306/quick_ticket_data";
			conn = DriverManager.getConnection(url, user, psw); // get the connection
		} catch (Exception e) {
			System.out.println("faild");
			e.printStackTrace();
		}
		return conn;
	}
}
