import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
			
			try {
				ResultSet rs = db.command("SELECT * FROM Station_Data");
				rs.next();
				
				ResultSetMetaData rsmd = rs.getMetaData();
				String toPrint = "";
				
				for(int i = 1; toPrint != null && i < 18; i++) {
					toPrint = rsmd.getColumnName(i);
					
					if(toPrint == null) { break; }
					System.out.print(toPrint + "  ");
				}
				
				System.out.println("\n\nWaiting...");
				scnr.nextLine();
				
				
				do {
					toPrint = "";
					
					for(int i = 1; toPrint != null && i < 18; i++) {
						toPrint = rs.getString(i);
						
						if(toPrint == null) { break; }
						System.out.print(toPrint + "  ");
					}
					
					System.out.println();
				} while(rs.next());
				
				rs.getStatement().close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			System.out.print("\n\nWould you like to go again? (y/n): ");
			input = scnr.nextLine().toLowerCase();
			
			System.out.println();
		} while(input.charAt(0) == 'y');
		
		db.close();
		scnr.close();
		
		System.out.println("Goodbye!");
	}
	
	private Driver() {}
}
