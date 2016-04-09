package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;

public abstract class SpellCard extends Card  {

	public SpellCard(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

}
