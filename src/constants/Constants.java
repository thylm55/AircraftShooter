package constants;

import models.Point;

public class Constants {
	public static final int SYMBOL_APPEARANCE = 1;
	public static final int SYMBOL_TURBOJET = 2;
	public static final int SYMBOL_CABIN = 3;
	public static final int SYMBOL_ENGINE = 4;
	
	public static final Point[] FIGHTER_APPEARANCE = 
								{new Point(0, 0), new Point(1, -1), new Point(1, 1),
								new Point(2, -2), new Point(2, 0), new Point(2, 2),
								new Point(3, -1), new Point(3, 1)};
	public static final Point[] FIGHTER_TURBOJET = {new Point(3, 0)};
	public static final Point FIGHTER_CABIN = new Point(1, 0);
	
	public static final Point[] BOMBER_APPEARANCE =
								{new Point(1, -1), new Point(1, 0), new Point(1, 1),
								new Point(2, -3), new Point(2, -1), new Point(2, 0), new Point(2, 1), new Point(2, 3),
								new Point(3, -4), new Point(3, -3), new Point(3, -2), new Point(3, 0), new Point(3, 2), new Point(3, 3), new Point(3, 4),
								new Point(4, 0),
								new Point(5, 0),
								new Point(6, -1), new Point(6, 1)};
	public static final Point BOMBER_CABIN = new Point(0, 0);
	public static final Point[] BOMBER_ENGINE = {new Point(2, -2), new Point(2, 2), new Point(6, 0)};
	
	public static final Point[] HELICOPTER_APPEARANCE =
								{new Point(0, 0), new Point(1, -1), new Point(1, 0), new Point(1, 1),
								new Point(2, -1), new Point(2, 0), new Point(2, 1),
								new Point(3, 0), new Point(4, 0), new Point(5, 0)};
}
