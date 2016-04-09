package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class ChangeOfHeart extends SpellCard {

	public ChangeOfHeart(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}
	
	public void action(MonsterCard monster)throws IOException{
		if(getBoard().getActivePlayer().getField().getMonstersArea().size()<5){
		getBoard().getActivePlayer().getField().getMonstersArea().add(monster);
		getBoard().getOpponentPlayer().getField().getMonstersArea().remove(monster);
		}
	}

}
