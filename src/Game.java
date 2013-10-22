

import java.util.Scanner;

import models.Map;
import models.Point;

public class Game {
	
	private static Scanner keyboard;

	public static void main(String args[]) {
		Map map = null;
		if (args.length == 1) {
			map = new Map(args[0], args[0]+".log");
		} else if (args.length == 2) {
			map = new Map(args[0], args[1]);
		}
		
		String myPoint = "";
		keyboard = new Scanner(System.in);
		while (keyboard.hasNextLine()) {			
			System.out.print("\nEnter a point: ");
			myPoint = keyboard.nextLine();
			String[] splitted = myPoint.split(" ");
			
			try {
				int myRow = Integer.parseInt(splitted[0]);
				int myColumn = Integer.parseInt(splitted[1]);
				Point bullet = new Point(myRow, myColumn);
				map.gotBullet(bullet);
				map.display();
				map.printLog();
			} catch (ArrayIndexOutOfBoundsException e){
				e.printStackTrace();
				System.out.print("Wrong input. Please try again.");
			} catch (NumberFormatException e) {
				System.out.print("Wrong format. Please try again.");
			}
			
			if (map.isGameOver()) {
				System.out.println("You win!");
				break;
			}
		}
		System.out.println("Game Over!");
	}
}
