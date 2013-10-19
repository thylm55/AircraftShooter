package models;

import techniques.Turbojet;
import constants.Constants;

public class Fighter extends Aircraft implements Turbojet {
	
	private Point[] turbojet;
	private int hitTurbojetCounts = 0;
	
	public Fighter(Point header, int direction) {
		super(header, direction);
		
		setAppearance(Constants.FIGHTER_APPEARANCE);
		setTurbojet(Constants.FIGHTER_TURBOJET);
		setCabin(Constants.FIGHTER_CABIN);
	}
	
	public Fighter(String input) {
		super(input);
		
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
			if (bullet.isAElement(turbojet)) hitTurbojetCounts++;
			if (hitTurbojetCounts == turbojet.length) setCrashed(true);
		}
	}
}