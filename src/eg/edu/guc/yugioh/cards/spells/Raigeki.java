package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class Raigeki extends SpellCard{

	public Raigeki(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}
	public void action(MonsterCard monster) throws IOException {

		getBoard()
		.getOpponentPlayer()
		.getField()
		.removeMonsterToGraveyard(
				getBoard().getOpponentPlayer().getField()
						.getMonstersArea());
	}
}
