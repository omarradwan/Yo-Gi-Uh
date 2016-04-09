package eg.edu.guc.yugioh.cards;

import java.io.IOException;

import eg.edu.guc.yugioh.board.Board;

public abstract class Card implements Cloneable {

	private String name;
	private String description;
	private boolean isHidden;
	private Location location;
	private static  Board board;

	public Card(String name, String description) {

		this.name = name;
		this.description = description;
		this.isHidden = true;
		this.location = Location.DECK;
	}
	
	public static Board getBoard() {
		return board;
	}

	public static void setBoard(Board board) {
		 Card.board = board;
	}

	

	public abstract void action(MonsterCard monster) throws IOException ;
	
	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

}
