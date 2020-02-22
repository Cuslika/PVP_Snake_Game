package Rank;

public class Rank {
	
	/**
	 * The name of the player.
	 */
	
	//Játékos neve
	private String name;
	
	/**
	 * The type of the game.
	 */
	
	//Játék típusa
	private String type;
	
	/**
	 * The points the player got.
	 */
	
	//Játékos elért pontja
	private int pts;
	
	/**
	 * The round of the game.
	 */
	
	//Játékos próbálkozása
	private int round;
	
	public Rank(String name, String type, int pts, int round) {
		this.name = name;
		this.type = type;
		this.pts = pts;
		this.round = round;
	}
	
	public Rank() {
		
	}
	
	//Getterek és Setterek
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public int getPts() {
		return pts;
	}

	public void setPts(int pts) {
		this.pts = pts;
	}
	
	public int getTries() {
		return round;
	}

	public void setTries(int round) {
		this.round = round;
	}
					
}