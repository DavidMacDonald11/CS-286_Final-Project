import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Allows your program to connect to an sql database.
 * Be sure to have sqlite-jdbc in your build path.
 * @author David MacDonald
 * @version November 17, 2019
 */
public class Database {
	private final String PATH;
	private final String URL;
	private Connection conn;
	
	public Database(String dbPath) {
		PATH = dbPath;
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
			
			System.out.println("Connected to " + PATH);
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
			
			System.out.println("Closed " + PATH);
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public HelpfulResultSet command(String sql) {
		try {
			Statement toSend = conn.createStatement();
			toSend.execute(sql);
			
			return new HelpfulResultSet(toSend.getResultSet());
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
