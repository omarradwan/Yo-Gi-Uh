package eg.edu.guc.yugioh.board.player;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;

public class Field {
	private Phase phase;
	private ArrayList<MonsterCard> monstersArea;
	private ArrayList<SpellCard> spellArea;
	private Deck deck;
	private ArrayList<Card> hand;
	private ArrayList<Card> graveyard;

	public Field() throws IOException , FileNotFoundException , UnexpectedFormatException, CloneNotSupportedException {
		monstersArea = new ArrayList<MonsterCard>(5);
		spellArea = new ArrayList<SpellCard>(5);
		deck = new Deck();
		hand = new ArrayList<Card>();
		graveyard = new ArrayList<Card>();
		phase = phase.MAIN1;
	}

	public void addMonsterToField(MonsterCard monster, Mode m, boolean isHidden) {
		if (monstersArea.size() < 5) {
			if (monster.getLocation() == Location.HAND) {
				hand.remove(monster);
				monstersArea.add(monster);
				monster.setHidden(isHidden);
				monster.setMode(m);
				monster.setLocation(Location.FIELD);
			}
		} else 
			throw new NoMonsterSpaceException();
	}

	public void addMonsterToField(MonsterCard monster, Mode mode,
			ArrayList<MonsterCard> sacrifices) {
		if (monster.getLocation() == Location.HAND) {
			if ((monster.getLevel() >= 7 && sacrifices.size() == 2)
					|| (monster.getLevel() >= 5 && sacrifices.size() == 1)) {
				removeMonsterToGraveyard(sacrifices);
				if(monster.getMode()==mode.ATTACK)
					monster.setHidden(false);
				addMonsterToField(monster, mode, monster.isHidden());
			}
		}
	}

	public void removeMonsterToGraveyard(MonsterCard monster) {
		if (monster.getLocation() == Location.FIELD) {
			monstersArea.remove(monster);
			graveyard.add(monster);
			monster.setLocation(Location.GRAVEYARD);
		}
	}

	public void removeMonsterToGraveyard(ArrayList<MonsterCard> monsters) {
		ArrayList<MonsterCard> x = new ArrayList<MonsterCard>();
		x = (ArrayList<MonsterCard>) monsters.clone();
		for (int i = 0; i < x.size(); i++) {
			removeMonsterToGraveyard(x.get(i));
		}
	}

	public void addSpellToField(SpellCard action, MonsterCard monster,
			boolean hidden) throws IOException {
		if (spellArea.size() < 5) {
			hand.remove(action);
			spellArea.add(action);
			action.setHidden(hidden);
			action.setLocation(Location.FIELD);
			if (action.isHidden() == false)
				activateSetSpell(action, monster);
		} 
		else
			throw new NoSpellSpaceException();
	}

	public void activateSetSpell(SpellCard action, MonsterCard monster)
			throws IOException {
		if (spellArea.contains(action)) {
			action.action(monster);
			removeSpellToGraveyard(action);
		}
	}

	public void removeSpellToGraveyard(SpellCard spell) {
		if (spellArea.contains(spell)) {
			spellArea.remove(spell);
			graveyard.add(spell);
			spell.setLocation(Location.GRAVEYARD);
		}
	}

	public void removeSpellToGraveyard(ArrayList<SpellCard> spells) {
		ArrayList<SpellCard> x = new ArrayList<SpellCard>();
		x = (ArrayList<SpellCard>) spells.clone();
		for (int i = 0; i < x.size(); i++) {
			removeSpellToGraveyard(x.get(i));
		}
	}

	public void addCardToHand() {
		if (deck.getDeck().isEmpty())
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
		else {
			Card c = deck.drawOneCard();
			hand.add(c);
			c.setLocation(Location.HAND);
		}
	}

	public void addNCardsToHand(int n) {
		for (int i = 0; i < n; i++) {
			addCardToHand();
		}
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public ArrayList<MonsterCard> getMonstersArea() {
		return monstersArea;
	}

	public ArrayList<SpellCard> getSpellArea() {
		return spellArea;
	}

	public Deck getDeck() {
		return deck;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public ArrayList<Card> getGraveyard() {
		return graveyard;
	}
}
