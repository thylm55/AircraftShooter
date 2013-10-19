package models;

import java.util.ArrayList;
import java.util.List;


public class Map {
	
	private int maxRow;
	private int maxColumn;
	
	private int[][] arrMap;
	
	private Point[] arrPoints;
	
	private int[] arrDisplayNumbers;
	
	private int numberPoints = 0;
	
	private List<Aircraft> listAircraft;
	
	public Map(int maxRow, int maxColumn) {
		this.maxRow = maxRow;
		this.maxColumn = maxColumn;
		this.arrPoints = new Point[maxRow * maxColumn];
		this.arrDisplayNumbers = new int[maxRow * maxColumn];
		this.listAircraft = new ArrayList<Aircraft>();
		
		arrMap = new int[maxRow][maxColumn];
		for (int i = 0; i < maxRow; i++) {
			for (int j = 0; j < maxColumn; j++) {
				arrMap[i][j] = 0;
			}
		}
	}
	
	public void addPoint(Point point, int displayNumber) {
		arrPoints[numberPoints] = point;
		arrDisplayNumbers[numberPoints] = displayNumber;
		
		if (point.getRow() >= 0 && point.getColumn() >= 0) {
			if (point.getRow() < maxRow && point.getColumn() < maxColumn) {
				arrMap[point.getRow()][point.getColumn()] = displayNumber;
			}
		}
		
		numberPoints++;
	}
	
	public void display() {
		for (Aircraft aircraft: listAircraft) {
			if (!aircraft.getCrashed()) aircraft.addToMap(this);
		}
		for (int i = 0; i < maxRow; i++) {
			System.out.println();
			for (int j = 0; j < maxColumn; j++) {
				System.out.print(arrMap[i][j]);
			}
		}	
	}
	
	public void log() {
		for (Aircraft aircraft: listAircraft) {
			if (!aircraft.getCrashed()) aircraft.addToMap(this);
		}
		for (int i = 0; i < maxRow; i++) {
			System.out.println();
			for (int j = 0; j < maxColumn; j++) {
				if (j == maxColumn - 1) {
					if (arrMap[i][j] > 0) System.out.print("1");
					else System.out.print("0");
				} else {
					if (arrMap[i][j] > 0) System.out.print("1 ");
					else System.out.print("0 ");
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
	
	public void removeAircraft(int index) {
		listAircraft.remove(index);
	}
	
	public List<Aircraft> getListAircraft() {
		return listAircraft;
	}
	
	public void gotBullet(Point bullet) {
		for (Aircraft aircraft: listAircraft) {
			aircraft.gotBullet(bullet);
		}
	}
}
