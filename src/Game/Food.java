package Game;
import java.awt.Component;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

/**
 * 
 * The class of the Food object.
 *
 */

public class Food {
	
	Component c;
	
	/**
	 * The coordinates of the Food.
	 */
	
	Coordinates coord;
	
	/**
	 * Gets the Foods Coordinates.
	 * @return Food Coordinates.
	 */
	
	public Coordinates getCoord() {
		return coord;
	}
	
	/**
	 * The picture of the Food.
	 */
	
	private ImageIcon food;
	
	/**
	 * Gets the ImageIcon of the Food.
	 * @return	the ImageIcon.
	 */
	public ImageIcon getFood() {
		return food;
	}

	/**
	 * Sets the games first Foods Coordinates.
	 */
	
	public Food() {
		coord = new Coordinates(this.randNumber1X(), this.randNumber1Y());
		food = new ImageIcon("Zöld.png");
	}
	
	/**
	 * Sets the Coordinates of the Food to the given Coordinates.
	 * @param c		The given Coordinates.
	 */
	
	public Food(Coordinates c) {
		coord = c;
		food = new ImageIcon("Zöld.png");
	}
	
	/**
	 * Sets the Coordinates of the Food during the gameplay.
	 */
	
	public void nFood() {
		coord = new Coordinates(this.randNumberX(), this.randNumberY());
		food = new ImageIcon("Zöld.png");
	}
	
	/**
	 * Generates a random X integer for the first Foods X coordinate.
	 * @return		The generated integer.
	 */
	
	private int randNumber1X() {
		int randN;
		do {
			randN = ThreadLocalRandom.current().nextInt(30, 930 + 1);
		}while(randN % 30 != 0 || randN == 210);
		return randN;
	}
	
	/**
	 * Generates a random Y integer for the first Foods Y coordinate.
	 * @return		The generated integer.
	 */
	
	private int randNumber1Y() {
		int randN;
		do {
			randN = ThreadLocalRandom.current().nextInt(150, 930 + 1);
		}while(randN % 30 != 0 || (randN == 120 || randN == 90 || randN == 60 || randN == 810 || randN == 840 || randN == 870));
		return randN;
	}
	
	/**
	 * Generates a random X integer for the Foods X coordinate during the game.
	 * @return		The generated integer.
	 */
	
	private int randNumberX() {
		int randN;
		do {
			randN = ThreadLocalRandom.current().nextInt(30, 930 + 1);
		
		}while(randN % 30 != 0);
		return randN;
	}
	
	/**
	 * Generates a random X integer for the Foods X coordinate during the game.
	 * @return		The generated integer.
	 */
	
	private int randNumberY() {
		int randN;
		do {
			randN = ThreadLocalRandom.current().nextInt(150, 930 + 1);
		
		}while(randN % 30 != 0);
		return randN;
	}
	
	/**
	 * Paints the Food.
	 * @param g		Graphics class.
	 */
	
	public void paintComponent(Graphics g) {
		
		food.paintIcon(c, g, this.coord.getX(), this.coord.getY());
		
	}
	
}
