import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Allows your program to connect to an sql database.
 * Be sure to have sqlite-jdbc in your build path.
 * @author David MacDonald
 * @version November 17, 2019
 */
public class Database {
	private final String URL;
	private Connection conn;
	
	public Database(String dbPath) {
		URL = "jdbc:sqlite:" + dbPath;
	}
	
	/**
	 * Connects to the database.
	 */
	public void connect() {
		try {
			if(conn == null) {
				conn = DriverManager.getConnection(URL);
			}
			
			System.out.println("Connected.");
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Closes the database.
	 */
	public void close() {
		try {
			if(conn != null) { conn.close(); }
			
			System.out.println("Closed.");
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
