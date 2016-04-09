package eg.edu.guc.yugioh.cards.spells;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class MonsterReborn extends SpellCard{

	public MonsterReborn(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}
	public void action(MonsterCard monster) throws IOException {
		int max = 0 ;
		MonsterCard m = null;
		ArrayList<Card> active = getBoard().getActivePlayer().getField().getGraveyard();
		ArrayList<Card> oppo = getBoard().getOpponentPlayer().getField().getGraveyard();
		ArrayList<MonsterCard> total = new ArrayList<MonsterCard>();
		for (int i = 0; i <oppo.size(); i++) {
			if(!oppo.isEmpty()){
			if (oppo.get(i) instanceof MonsterCard ){
				total.add((MonsterCard) oppo.get(i));
				}
		}
		}
		for (int i = 0; i <active.size() ; i++) {
			if(!active.isEmpty()){
			if (active.get(i) instanceof MonsterCard ){
				total.add((MonsterCard) active.get(i));
				}
		}
		}
		if(!total.isEmpty()){
		for (int i = 0; i < total.size(); i++) {
			if(total.get(i).getAttackPoints() > max){
				max = total.get(i).getAttackPoints();
				m = total.get(i);
			}
			}
		}else
			JOptionPane.showMessageDialog(null, "both graveyard are empty",
					":v", JOptionPane.PLAIN_MESSAGE);
			
		if(m!=null){
			getBoard().getActivePlayer().getField().getGraveyard().remove(m);
			getBoard().getActivePlayer().getField().getMonstersArea().add(m);
			m.setHidden(false);
			m.setMode(Mode.ATTACK);
			m.setSwitched(true);
			m.setLocation(Location.FIELD);
		}
		
	}
}

