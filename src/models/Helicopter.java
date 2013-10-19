package models;

import constants.Constants;

public class Helicopter extends Aircraft {

	public Helicopter(Point header, int direction) {
		super(header, direction);

		setAppearance(Constants.HELICOPTER_APPEARANCE);
	}
	
	public Helicopter(String input) {
		super(input);
		
		setAppearance(Constants.HELICOPTER_APPEARANCE);
	}
	
	@Override
	public void gotBullet(Point bullet) {
		super.gotBullet(bullet);
		gotBulletIntoBody(1, bullet);
	}
}
