package Game;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * 
 * The class of the Snake used in the game. 
 *
 */

public class Snake {
	
	/**
	 * The List of the Snake. Its Coordinates are stored in it.
	 */
	
	private ArrayList<Coordinates> snake = new ArrayList<Coordinates>();
	
	/**
	 * The Direction the snake is headed to.
	 */
	
	private Direction direction;

	public Direction getDirection() {
		return direction;
	}

	/**
	 * The pictures of the snakes head, and body.
	 */
	
	private ImageIcon body, head;
	
	Component c;
	
	/**
	 * The Constructor of the Snake. It fills the List with the given Coordinates according to which snake it is.
	 * @param p		This holds the information, which Snake this is going to be.
	 */
	
	public Snake(String p) {
		
		if(p == "B" || p == "b") {
			body = new ImageIcon("Kék.png");
			head = new ImageIcon("KFehér.png");
			
			this.direction = Direction.RIGHT;
			
			snake.add(new Coordinates(120, 210));
			snake.add(new Coordinates(90, 210));
			snake.add(new Coordinates(60, 210));
			
		} else
		if(p == "R" || p == "r") {
			body = new ImageIcon("Piros.png");
			head = new ImageIcon("PFehér.png");
			
			this.direction = Direction.LEFT;
			
			snake.add(new Coordinates(810, 210));
			snake.add(new Coordinates(840, 210));
			snake.add(new Coordinates(870, 210));
		}
	}
	
	public Coordinates getCoor(int i) {
		return this.snake.get(i);
	}
	
	public int getLength() {
		return this.snake.size();
	}
	
	public int getHeadX() {
		return this.snake.get(0).getX();
	}
	
	public int getHeadY() {
		return this.snake.get(0).getY();
	}
	
	public void addLength() {
		if(this.direction == Direction.UP)
			this.snake.add(new Coordinates(this.snake.get(this.snake.size()-1).getX(), this.snake.get(this.snake.size()-1).getY() + 30));
		if(this.direction == Direction.DOWN)
			this.snake.add(new Coordinates(this.snake.get(this.snake.size()-1).getX(), this.snake.get(this.snake.size()-1).getY() - 30));
		if(this.direction == Direction.LEFT)
			this.snake.add(new Coordinates(this.snake.get(this.snake.size()-1).getX() - 30, this.snake.get(this.snake.size()-1).getY()));
		if(this.direction == Direction.RIGHT)
			this.snake.add(new Coordinates(this.snake.get(this.snake.size()-1).getX() + 30, this.snake.get(this.snake.size()-1).getY()));
	}
	
	/**
	 * Removes the given length from the Snake.
	 * @param i		The given length.
	 */
	
	public void loseLength(int i) {
		
		for(int j = i - 1; j < snake.size(); j++)
			snake.remove(j);
	}
	
	/**
	 * Paints the Snake object.
	 * @param g		Graphics class.
	 */
	
	public void paintComponent(Graphics g) {
				
		if(snake.get(0).getX() == 0) {
			snake.get(0).setX(930);
		}
		if(snake.get(0).getX() == 960) {
			snake.get(0).setX(30);
		}
		if(snake.get(0).getY() == 120) {
			snake.get(0).setY(930);
		}
		if(snake.get(0).getY() == 960) {
			snake.get(0).setY(150);
		}
		
		head.paintIcon(c, g, snake.get(0).getX(), snake.get(0).getY());
		
		for(int i = 1; i < snake.size(); i++) {
			
			body.paintIcon(c, g, snake.get(i).getX(), snake.get(i).getY());
			
		}
		
	}
	
	/**
	 * Sets the direction of the snake.
	 * @param d		The new Direction.
	 */
	
	public void setDirection(Direction d) {
		
		if((this.direction == Direction.UP || this.direction == Direction.DOWN) && (d == Direction.LEFT || d == Direction.RIGHT)) {
			this.direction = d;
		}
		if((this.direction == Direction.LEFT || this.direction == Direction.RIGHT) && (d == Direction.UP || d == Direction.DOWN)) {
			this.direction = d;
		}
		
	}
	
	/**
	 * Adds a new body part to the end of the Snake.
	 * @param c		The Coordinates of the new part.
	 */
	
	public void changeSnake(Coordinates c) {

		ArrayList<Coordinates> temp = new ArrayList<Coordinates>();
		temp.add(c);
		for(int i = 0; i < snake.size() - 1; i++) {
			temp.add(snake.get(i));
		}
		snake = temp;
	}
	
	/**
	 * Checks if the Snake hit itself.
	 * @return		True if it hit itself, false otherwise.
	 */
	
	public boolean hitItself() {
		for(int j = 3; j < snake.size(); j++) {
			if(snake.get(0).getX() == snake.get(j).getX() && snake.get(0).getY() == snake.get(j).getY())
				return true;
		}
		return false;
	}

	/**
	 * Moves the Snake according to its Direction.
	 */
	
	public void Move() {
		
		switch(this.direction) {
			case UP: changeSnake(new Coordinates(snake.get(0).getX(),snake.get(0).getY() - 30));
				break;
			case DOWN: changeSnake(new Coordinates(snake.get(0).getX(),snake.get(0).getY() + 30));				
				break;
			case LEFT: changeSnake(new Coordinates(snake.get(0).getX() - 30,snake.get(0).getY()));				
				break;
			case RIGHT: changeSnake(new Coordinates(snake.get(0).getX() + 30,snake.get(0).getY()));
				break;
			default: throw new Error();
		
		}
	}
}
