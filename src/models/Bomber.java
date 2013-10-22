package models;

import techniques.Engine;
import constants.Constants;

/**
 * @author MAY THY
 *
 * Class to define bomber. A bomber is a aircraft has equipment engine technology.
 */
public class Bomber extends Aircraft implements Engine {
	
	// array elements of engine
	private Point[] engine;
	
	// number of bullets attacked engine
	private int hitEngineCounts = 0;
	
	// number of elements of engine
	private int engineCounts;
	
	public Bomber(Point header, int direction) {
		super(header, direction);
		
		// TODO Styling bomber here
		setAppearance(Constants.BOMBER_APPEARANCE);
		setEngine(Constants.BOMBER_ENGINE);
		setCabin(Constants.BOMBER_CABIN);
	}
	
	public Bomber(String input) {
		super(input);
		
		// TODO Styling bomber here
		setAppearance(Constants.BOMBER_APPEARANCE);
		setEngine(Constants.BOMBER_ENGINE);
		setCabin(Constants.BOMBER_CABIN);
	}

	@Override
	public void addToMap(Map map) {
		super.addToMap(map);
		for (Point element: engine) {
			map.addPoint(element, Constants.SYMBOL_ENGINE);
		}
	}
	
	@Override
	public void setEngine(Point[] data) {
		engine = initComponent(data);
		engineCounts = data.length;
	}

	@Override
	public Point[] getEngine() {
		return engine;
	}
	
	@Override
	public Point[] getPoints() {
		Point[] result = super.getPoints();
		for (Point element: engine) {
			result = element.addToArray(result);
		}
		return result;
	}
	
	@Override
	public void gotBullet(Point bullet) {
		super.gotBullet(bullet);
		if (!getCrashed()) {
			gotBulletIntoBody(10, bullet);
		}
		if (!getCrashed()) {
			if (bullet.isAElement(engine)) {
				setHit(true);
				hitEngineCounts++;
				engine = bullet.removeFromArray(engine);
			}
			if (hitEngineCounts == engineCounts) setCrashed(true);
		}
	}
}
