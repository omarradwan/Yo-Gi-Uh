package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class HeavyStorm extends HarpieFeatherDuster{

	public HeavyStorm(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}
	public void action(MonsterCard monster) throws IOException {
		
		ArrayList<SpellCard> aso = getBoard().getOpponentPlayer().getField().getSpellArea();
		getBoard().getOpponentPlayer().getField().removeSpellToGraveyard(aso);
		
		ArrayList<SpellCard> as = getBoard().getActivePlayer().getField().getSpellArea();
		getBoard().getActivePlayer().getField().removeSpellToGraveyard(as);
	}
}
