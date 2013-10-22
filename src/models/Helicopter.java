package models;

import constants.Constants;

/**
 * @author MAY THY
 *
 * Class to define helicopter.
 */
public class Helicopter extends Aircraft {

	public Helicopter(Point header, int direction) {
		super(header, direction);

		// TODO Styling helicopter here
		setAppearance(Constants.HELICOPTER_APPEARANCE);
	}
	
	public Helicopter(String input) {
		super(input);
		
		// TODO Styling helicopter here
		setAppearance(Constants.HELICOPTER_APPEARANCE);
	}
	
	@Override
	public void gotBullet(Point bullet) {
		super.gotBullet(bullet);
		gotBulletIntoBody(1, bullet);
	}
}
