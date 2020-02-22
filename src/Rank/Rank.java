package Rank;

public class Rank {
	
	/**
	 * The name of the player.
	 */
	
	//J�t�kos neve
	private String name;
	
	/**
	 * The type of the game.
	 */
	
	//J�t�k t�pusa
	private String type;
	
	/**
	 * The points the player got.
	 */
	
	//J�t�kos el�rt pontja
	private int pts;
	
	/**
	 * The round of the game.
	 */
	
	//J�t�kos pr�b�lkoz�sa
	private int round;
	
	public Rank(String name, String type, int pts, int round) {
		this.name = name;
		this.type = type;
		this.pts = pts;
		this.round = round;
	}
	
	public Rank() {
		
	}
	
	//Getterek �s Setterek
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