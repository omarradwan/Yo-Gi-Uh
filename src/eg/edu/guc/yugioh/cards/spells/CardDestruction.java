package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import eg.edu.guc.yugioh.board.player.Field;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class CardDestruction extends SpellCard {

	public CardDestruction(String name, String description) {
		super(name, description);
		
	}
public void action(MonsterCard monster)throws IOException{
	
	ArrayList<Card> ac = getBoard().getActivePlayer().getField().getHand();
	ArrayList<Card> aco =getBoard().getOpponentPlayer().getField().getHand();
	
	int x = ac.size();
	int y = aco.size();
	
	for (int i = 0; i < x; i++) {
		ac.get(i).setLocation(Location.GRAVEYARD);
		getBoard().getActivePlayer().getField().getGraveyard().add(ac.get(i));
	}
	ac.clear();
	
	for (int i = 0; i < y; i++) {
		aco.get(i).setLocation(Location.GRAVEYARD);
		getBoard().getOpponentPlayer().getField().getGraveyard().add(aco.get(i));
	}
	aco.clear();
	
	this.getBoard().getActivePlayer().getField().addNCardsToHand(x);
	this.getBoard().getOpponentPlayer().getField().addNCardsToHand(y);

}
}

