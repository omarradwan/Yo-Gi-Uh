package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class HarpieFeatherDuster extends SpellCard{

	public HarpieFeatherDuster(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	public void action(MonsterCard monster) throws IOException {
		ArrayList<SpellCard> as = getBoard().getOpponentPlayer().getField().getSpellArea();
		getBoard().getOpponentPlayer().getField().removeSpellToGraveyard(as);
	}
}
