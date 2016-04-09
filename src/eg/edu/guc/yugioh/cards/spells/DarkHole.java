package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class DarkHole extends Raigeki {

	public DarkHole(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	public void action(MonsterCard monster) throws IOException {
		
		super.action(monster);
		
		getBoard()
				.getActivePlayer()
				.getField()
				.removeMonsterToGraveyard(
						getBoard().getActivePlayer().getField()
								.getMonstersArea());

	}
}
