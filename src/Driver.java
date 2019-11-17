import java.util.Scanner;

/**
 * @author David MacDonald
 * @version November 17, 2019
 */
public final class Driver {	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		String input = "";
		
		Database db = new Database("weather_station.db");
		
		do {
			System.out.println("Attempting to connect to db...");
			db.connect();
			
			System.out.println("\nAttempting to close db...");
			db.close();
			
			System.out.print("\nWould you like to go again? (y/n): ");
			input = scnr.nextLine().toLowerCase();
		} while(input.charAt(0) == 'y');
		
		System.out.println("Goodbye!");
		scnr.close();
	}
	
	private Driver() {}
}
