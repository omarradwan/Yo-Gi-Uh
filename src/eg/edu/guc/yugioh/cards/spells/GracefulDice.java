package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class GracefulDice extends SpellCard {

	public GracefulDice(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	public void action(MonsterCard monster) throws IOException {
	
		Random r = new Random();
		int x = r.nextInt(9)+1;
		ArrayList<MonsterCard> am = getBoard().getActivePlayer().getField().getMonstersArea();
		for (int i = 0; i < am.size(); i++) {
			am.get(i).setAttackPoints(am.get(i).getAttackPoints()+100*x);
			am.get(i).setDefensePoints(am.get(i).getDefensePoints()+100*x);
		}
	}
}
