package models;

import constants.Constants;

/**
 * @author MAY THY
 *
 * Class to define aircraft.
 */
public class Aircraft {
	
	/**
	 * Constructor to initials an aircraft with special header & direction
	 * @param header
	 * @param direction
	 */
	public Aircraft(Point header, int direction) {
		this.cabin = null;
		this.header = header;
		this.direction = direction;
		this.crashed = false;
	}
	
	/**
	 * Constructor to initials an aircraft from a string.
	 * @param input
	 */
	public Aircraft(String input) {
		String[] splitted = input.split(" ");
		
		int headerRow = Integer.parseInt(splitted[1]);
		int headerCol = Integer.parseInt(splitted[2]);
		this.header = new Point(headerRow, headerCol);
		
		if (splitted[3].equals("N")) this.direction = 0;
		if (splitted[3].equals("S")) this.direction = 1;
		if (splitted[3].equals("E")) this.direction = 2;
		if (splitted[3].equals("W")) this.direction = 3;

		this.cabin = null;
		this.crashed = false;
		
	}
	
	// header position of a aircraft
	protected Point header;
	
	protected Point cabin;
	
	// save direction of a aircraft
	protected int direction;
	
	// save appearance's array of positions
	protected Point[] appearance;
	
	// state of aircraft after got bullet
	protected boolean hit;
	
	// save state of aircraft
	protected boolean crashed;
	
	// value to save number of bullet hit to body of aircraft
	protected int hitBodyCounts;
	
	// set cabin for aircraft
	protected void setCabin(Point cabin) {
		this.cabin = initComponent(cabin);
	}
	
	// set appearance for aircraft
	protected void setAppearance(Point[] data) {
		this.appearance = initComponent(data);
	}
	
	/**
	 * set state of aircraft is hit or miss
	 * @param hit
	 */
	public void setHit(boolean hit) {
		this.hit = hit;
	}
	
	/**
	 * get latest hit/miss state of aircraft
	 * @return
	 */
	public boolean isHit() {
		return hit;
	}
	
	// set state of aircraft is crashed or not
	protected void setCrashed(boolean crashed) {
		this.crashed = crashed;
	}
	
	/**
	 * get crashed state of aircraft
	 * @return
	 */
	public boolean getCrashed() {
		return crashed;
	}
	
	// return appearance's point array
	protected Point[] getAppearance() {
		return appearance;
	}
	
	// method to initial a component of aircraft
	protected Point[] initComponent(Point[] data) {
		Point[] result = new Point[data.length];
		int i = 0;
		for (Point p: data) {
			Point spinned = p.spin(new Point(0, 0), direction);
			Point plus = spinned.addition(header);
			result[i] = plus;
			i++;
		}
		return result;
	}
	
	// initial components of aircraft
	protected Point initComponent(Point data) {
		Point spinned = data.spin(new Point(0, 0), direction);
		Point plus = spinned.addition(header);
		return plus;
	}
	
	/**
	 * add aircraft to map
	 * @param map
	 */
	public void addToMap(Map map) {
		// add cabin if having
		if (cabin != null) {
			map.addPoint(cabin, Constants.SYMBOL_CABIN);
		}
		
		// add body
		for (Point element: appearance) {
			map.addPoint(element, Constants.SYMBOL_APPEARANCE);
		}
	}
	
	// return array of points aircraft occupy
	protected Point[] getPoints() {
		if (cabin != null) {
			return cabin.addToArray(appearance);
		}
		return appearance;
	}
	
	/**
	 * aircraft got a bullet
	 * @param bullet
	 */
	public void gotBullet(Point bullet) {
		setHit(false);
		if (cabin != null) {
			if (bullet.equals(cabin)) {
				setHit(true);
				setCrashed(true);
			}
		}
	}
	
	// aircraft got a bullet into body
	protected void gotBulletIntoBody(int conditionCrash, Point bullet) {
		if (bullet.isAElement(getPoints())) {
			setHit(true);
			hitBodyCounts++;
			appearance = bullet.removeFromArray(appearance);
		}
		if (hitBodyCounts == conditionCrash) setCrashed(true);
	}
}
