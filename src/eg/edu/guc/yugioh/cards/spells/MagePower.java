package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class MagePower extends SpellCard{

	public MagePower(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}
	public void action(MonsterCard monster) throws IOException {
		
		int x = getBoard().getActivePlayer().getField().getSpellArea().size()-1; 
		System.out.println(x);
		monster.setAttackPoints(monster.getAttackPoints()+500*x);
		monster.setDefensePoints(monster.getDefensePoints()+500*x);
	}
}
