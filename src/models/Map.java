package models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author MAY THY
 *
 * Class to define map in game.
 */
public class Map {
	
	// attribute max of row number
	private int maxRow;
	
	// attribute max of column number
	private int maxColumn;
	
	// save state every point to printing log
	private int[][] arrMap;
	
	// save state every point to displaying
	private String[][] arrDisplay;
	
	// to save aircraft
	private List<Aircraft> listAircraft;
	
	// save current bullet this map gotten
	private Point currentBullet;
	
	// save every bullet to list
	private List<Point> listBullet;
	
	// save current state of map, hit or miss
	private boolean hit = false;
	
	// output file
	private String outputFile;
	
	// flag to check if game is over
	private boolean isGameOver = false;
	
	public boolean isGameOver() {
		return isGameOver;
	}
	
	public Map(int maxRow, int maxColumn, String outputFile) {
		this.maxRow = maxRow;
		this.maxColumn = maxColumn;
		this.outputFile = outputFile;
		initVariables();
		initOutput(outputFile);
	}
	
	public Map(String inputFile, String outputFile) {
		this.outputFile = outputFile;
		
		// initial input file
	    try {
	        Scanner scanner = new Scanner(new File(inputFile));
	 
	        this.maxRow = scanner.nextInt();
	        this.maxColumn = scanner.nextInt();
	       
	        initVariables();
			
			while (scanner.hasNextLine()) {
				String nextLine = scanner.nextLine();
				addAircraft(nextLine);
			}
			
	        scanner.close();
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }

	    initOutput(outputFile);
	}
	
	public void initVariables() {
		this.listAircraft = new ArrayList<Aircraft>();
		this.currentBullet = null;
		this.listBullet = new ArrayList<Point>();
		
		arrMap = new int[maxRow][maxColumn];
		for (int i = 0; i < maxRow; i++) {
			for (int j = 0; j < maxColumn; j++) {
				arrMap[i][j] = 0;
			}
		}
		
		arrDisplay = new String[maxRow][maxColumn];
		for (int i = 0; i < maxRow; i++) {
			for (int j = 0; j < maxColumn; j++) {
				arrDisplay[i][j] = ".";
			}
		}
	}
	
	private void initOutput(String outputFile) {
	    // initial output file
		try {
			PrintWriter log = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
			log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * add a point with a state to map
	 * @param point
	 * @param displayNumber
	 */
	public void addPoint(Point point, int displayNumber) {
		if (point.getRow() >= 0 && point.getColumn() >= 0) {
			if (point.getRow() < maxRow && point.getColumn() < maxColumn) {
				arrMap[point.getRow()][point.getColumn()] = displayNumber;
			}
		}
	}
	
	private void reload() {
		for (int i = 0; i < maxRow; i++) {
			for (int j = 0; j < maxColumn; j++) {
				arrMap[i][j] = 0;
			}
		}
		for (Aircraft aircraft: listAircraft) {
			if (!aircraft.getCrashed()) aircraft.addToMap(this);
		}
	}
	
	/**
	 * Display map to screen
	 */
	public void display() {
		for (int i = 0; i < maxRow; i++) {
			System.out.println();
			for (int j = 0; j < maxColumn; j++) {
				System.out.print(arrDisplay[i][j]+" ");
			}
		}
	}
	
	/**
	 * print map to file
	 */
	public void printLog() {
		if (currentBullet != null) {
			if (currentBullet.getRow() < maxRow && currentBullet.getColumn() < maxColumn
					&& currentBullet.getRow() >= 0 && currentBullet.getColumn() >= 0) {
				reload();
				try {
					PrintWriter log = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, true)));
					if (hit) {
						log.print(currentBullet.getRow() + "\t" + currentBullet.getColumn() + "\t" + "hit");
					} else {
						log.print(currentBullet.getRow() + "\t" + currentBullet.getColumn() + "\t" + "miss");
					}
					
					for (int i = 0; i < maxRow; i++) {
						log.println();
						for (int j = 0; j < maxColumn; j++) {
							if (arrMap[i][j] > 0) log.print("1 ");
							else log.print("0 ");
						}
					}
					log.println();
					log.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void addAircraft(String input) {
		String[] splitted = input.split(" ");
		
		if (splitted[0].equals("H")) {
			Helicopter helicopter = new Helicopter(input);
			listAircraft.add(helicopter);
		}
		if (splitted[0].equals("F")) {
			Fighter fighter = new Fighter(input);
			listAircraft.add(fighter);
		}
		if (splitted[0].equals("B")) {
			Bomber bomber = new Bomber(input);
			listAircraft.add(bomber);
		}
	}
	
	/**
	 * when map got a bullet
	 * @param bullet
	 */
	public void gotBullet(Point bullet) {
		bullet.print();
		currentBullet = bullet;
		listBullet.add(bullet);
		hit = false;
		isGameOver = true;
		
		if (currentBullet.getRow() < maxRow && currentBullet.getColumn() < maxColumn
				&& currentBullet.getRow() >= 0 && currentBullet.getColumn() >= 0) {
			for (Aircraft aircraft: listAircraft) {
				if (!aircraft.getCrashed()) {
					aircraft.gotBullet(bullet);
					if (aircraft.isHit()) hit = true;
				}
				if (!aircraft.getCrashed()) {
					isGameOver = false;
				}
				if (aircraft.getCrashed()) {
					for (Point element: aircraft.getPoints()) {
						if (element.getRow() < maxRow && element.getColumn() < maxColumn
								&& element.getRow() >= 0 && element.getColumn() >= 0) {
							int elementR = element.getRow();
							int elementC = element.getColumn();
							arrDisplay[elementR][elementC] = "x";
						}
					}
				}
			}
			
			// set value to display
			if (hit) {
				System.out.println("HIT");
				arrDisplay[currentBullet.getRow()][currentBullet.getColumn()] = "x";
			} else {
				System.out.println("MISS");
				if (!arrDisplay[currentBullet.getRow()][currentBullet.getColumn()].equals("x"))
					arrDisplay[currentBullet.getRow()][currentBullet.getColumn()] = " ";
			}
			
		} else {
			isGameOver = false;
			System.out.println("Out of map.");
		}
	}
}
