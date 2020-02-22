package Game;

/**
 * The Coordinates the game uses. The Snakes parts and the Foods are also painted to the Panel based on their Coordinates.
 *
 */

public class Coordinates {
	
	/**
	 * The X Coordinate on the Panel.
	 */
	
	private int x;
	
	/**
	 * The Y Coordinate on the Panel.
	 */
	
	private int y;
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Coordinates(int x, int y) {
		this.setX(x);
		this.setY(y);
		
	}

}
