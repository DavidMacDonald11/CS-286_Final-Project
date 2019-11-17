package fin.util;

import java.util.Scanner;

/**
 * @author David MacDonald
 * @version November 17, 2019
 */
public final class Driver {	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		String input = "";
		
		do {
			
			System.out.println("Would you like to go again? (y/n)");
			input = scnr.nextLine().toLowerCase();
		} while(input.charAt(0) == 'y');
		
		System.out.println("Goodbye!");
		scnr.close();
	}
	
	private Driver() {}
}
