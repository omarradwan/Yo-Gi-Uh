package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class PotOfGreed extends SpellCard{

	public PotOfGreed(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}
	public void action(MonsterCard monster) throws IOException {
		getBoard().getActivePlayer().getField().addNCardsToHand(2);
	}
}
