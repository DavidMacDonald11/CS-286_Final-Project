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
			HelpfulResultSet rs;
			System.out.println("What is your SQL command?");
			
			do {
				System.out.print(": ");
				
				String command = scnr.nextLine();
				rs = db.command(command);
				
				System.out.println();
			} while(rs == null);
				
			rs.printData();
			rs.closeStatement();
			
			System.out.print("\nWould you like to go again? (y/n): ");
			input = scnr.nextLine().toLowerCase();
			
			System.out.println();
		} while(input.charAt(0) == 'y');
		
		db.close();
		scnr.close();
		
		System.out.println("Goodbye!");
	}
	
	private Driver() {}
}
