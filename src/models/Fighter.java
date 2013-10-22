package models;

import techniques.Turbojet;
import constants.Constants;

/**
 * @author MAY THY
 *
 * Class to define fighter. A fighter is a aircraft has equipment turbojet technology.
 */
public class Fighter extends Aircraft implements Turbojet {
	
	// array elements of turbojet
	private Point[] turbojet;
	
	// number of bullets attacked turbojet
	private int hitTurbojetCounts = 0;
	
	// number of elements of turbojet
	private int turbojetCounts;
	
	public Fighter(Point header, int direction) {
		super(header, direction);
		
		// TODO Styling fighter here
		setAppearance(Constants.FIGHTER_APPEARANCE);
		setTurbojet(Constants.FIGHTER_TURBOJET);
		setCabin(Constants.FIGHTER_CABIN);
	}
	
	public Fighter(String input) {
		super(input);
		
		// TODO Styling fighter here
		setAppearance(Constants.FIGHTER_APPEARANCE);
		setTurbojet(Constants.FIGHTER_TURBOJET);
		setCabin(Constants.FIGHTER_CABIN);
	}
	
	@Override
	public void addToMap(Map map) {
		super.addToMap(map);
		for (Point element: turbojet) {
			map.addPoint(element, Constants.SYMBOL_TURBOJET);
		}
	}

	@Override
	public Point[] getTurbojet() {
		return turbojet;
	}

	@Override
	public void setTurbojet(Point[] data) {
		turbojet = initComponent(data);
		turbojetCounts = data.length;
	}
	
	@Override
	public Point[] getPoints() {
		Point[] result = super.getPoints();
		for (Point element: turbojet) {
			result = element.addToArray(result);
		}
		return result;
	}
	
	@Override
	public void gotBullet(Point bullet) {
		super.gotBullet(bullet);
		if (!getCrashed()) {
			gotBulletIntoBody(4, bullet);
		}
		if (!getCrashed()) {
			if (bullet.isAElement(turbojet)) {
				setHit(true);
				hitTurbojetCounts++;
				turbojet = bullet.removeFromArray(turbojet);
			}
			if (hitTurbojetCounts == turbojetCounts) setCrashed(true);
		}
	}
}