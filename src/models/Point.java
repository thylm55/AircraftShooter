package models;

public class Point {
	private int row;
	private int column;
	
	public Point(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void print() {
		System.out.println("(" + this.row + ", " + this.column + ")");
	}
	
/*	public Point multiply(int n) {
		int mX = this.row * n;
		int mY = this.column * n;
		Point mP = new Point(mX, mY);
		return mP;
	}*/
	
	public Point addition(Point point) {
		int aRow = this.row + point.row;
		int aColumn = this.column + point.column;
		return new Point(aRow, aColumn);
	}
	
	public Point spin(Point center, int direction) {
		// South
		if (direction == 1) {
			int resultX = 2 * center.row - this.row;
			int resultY = 2 * center.column - this.column;
			return new Point(resultX, resultY);
		
		// East
		} else if (direction == 2) {
			int resultX = 0;
			int resultY = 0;
			if ((this.row <= center.row && this.column >= center.column)
					|| (this.row >= center.row && this.column <= center.column)) {
				resultX = this.column;
				resultY = 2 * center.row - this.row;
			}
			if ((this.row > center.row && this.column > center.column)
					|| (this.row < center.row && this.column < center.column)) {
				resultX = - 2 * center.column + this.column;
				resultY = - this.row;
			}
			return new Point(resultX, resultY);
			
		// West
		} else if (direction == 3) {
			int resultX = 0;
			int resultY = 0;
			if ((this.row <= center.row && this.column >= center.column)
					|| (this.row >= center.row && this.column <= center.column)) {
				resultX = 2 * center.column - this.column;
				resultY = this.row;
			}
			if ((this.row > center.row && this.column > center.column)
					|| (this.row < center.row && this.column < center.column)) {
				resultX = - this.column;
				resultY = - 2 * center.row + this.row;
			}
			return new Point(resultX, resultY);
		
		// Default, not spin
		} else {
			return this;
		}
	}
	
	public boolean equals(Point point) {
		if (this.row == point.row  && this.column == point.column) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isAElement(Point[] points) {
		boolean isAElement = false;
		for (Point point: points) {
			if (this.equals(point)) isAElement = true;
		}
		return isAElement;
	}
	
	public Point[] addToArray(Point[] points) {
		int size = points.length;
		Point[] newPoints = new Point[size + 1];
		for (int i = 0; i < size; i++) {
			newPoints[i] = points[i];
		}
		newPoints[size] = this;
		return newPoints;
	}
	
	public boolean search(Point[] points) {
		boolean result = false;
		for (Point point: points) {
			if (this.equals(point)) result = true;
		}
		return result;
	}
	
	public Point[] removeFromArray(Point[] points) {
		if (this.search(points)) {
			int size = points.length;
			Point[] newPoints = new Point[size - 1];
			int i = 0;
			for (Point point: points) {
				if (!this.equals(point)) {
					newPoints[i] = point;
					i++;
				}
			}
			return newPoints;
		} else {
			return points;
		}
	}
}
