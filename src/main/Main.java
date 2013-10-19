package main;

import models.Map;
import models.Point;


public class Main {
	
	public static void main(String args[]) {
		Map map = new Map(50, 50);
		
		map.addAircraft("H 3 5 N");
		map.addAircraft("B 20 20 N");
		map.addAircraft("F 10 10 N");
		
		/*map.gotBullet(new Point(12, 10));
		map.gotBullet(new Point(13, 9));
		map.gotBullet(new Point(13, 11));
		map.gotBullet(new Point(12, 9));*/
		//map.gotBullet(new Point(20, 20));
		
		System.out.println(map.getListAircraft().size()+"");
		map.log();

		/*Helicopter redBull = new Helicopter(new Point(0, 3), 0);
		redBull.gotBullet(new Point(1, 3));
		//System.out.println(redBull.getCrashed()+"");
		
		Fighter F11 = new Fighter(new Point(2, 3), 0);
		F11.gotBullet(new Point(5, 3));
		F11.gotBullet(new Point(5, 4));
		F11.gotBullet(new Point(4, 3));
		F11.gotBullet(new Point(4, 5));
		//System.out.println(F11.getCrashed()+"");
		
		Bomber B52 = new Bomber(new Point(1, 5), 0);
		Bomber B53 = new Bomber("B 1 5 N");
		
		B52.gotBullet(new Point(2, 4));
		B52.gotBullet(new Point(2, 5));
		B52.gotBullet(new Point(2, 6));
		B52.gotBullet(new Point(3, 2));
		B52.gotBullet(new Point(3, 4));
		B52.gotBullet(new Point(3, 5));
		B52.gotBullet(new Point(3, 6));
		//B52.gotBullet(new Point(3, 8));
		//B52.gotBullet(new Point(4, 1));
		//B52.gotBullet(new Point(4, 2));
		//B52.gotBullet(new Point(3, 3));
		B52.gotBullet(new Point(3, 7));
		B52.gotBullet(new Point(7, 5));
		//B52.gotBullet(new Point(1, 5));
		System.out.println(B52.getCrashed()+"");
		
		B53.addToMap(map);
		map.display();*/
		
		/*Point ps[] = {new Point(0, 1), new Point(0, 2), new Point(0, 3)};
		Point p = new Point(0, 2);
		Point ps2[] = p.removeFromArray(ps);
		for (Point p2: ps2) {
			p2.print();
		}*/
	}
}
