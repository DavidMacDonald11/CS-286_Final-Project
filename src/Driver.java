import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author David MacDonald
 * @version November 17, 2019
 */
public final class Driver {	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		String input = "";
		
		Database db = new Database("weather_stations.db");
		db.connect();
		
		do {
			System.out.println("Testing...\n");
			
			ResultSet rs = db.command("SELECT * FROM Station_Data");
			
			try {
				System.out.println(rs.getString(1));
				rs.getStatement().close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			System.out.print("Would you like to go again? (y/n): ");
			input = scnr.nextLine().toLowerCase();
			
			System.out.println();
		} while(input.charAt(0) == 'y');
		
		db.close();
		scnr.close();
		
		System.out.println("Goodbye!");
	}
	
	private Driver() {}
}
