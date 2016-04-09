package eg.edu.guc.yugioh.board.player;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.DefenseMonsterAttackException;
import eg.edu.guc.yugioh.exceptions.EmptyFieldException;
import eg.edu.guc.yugioh.exceptions.MissingFieldException;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.UnknownCardTypeException;
import eg.edu.guc.yugioh.exceptions.UnknownSpellCardException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;

public class Player implements Duelist {

	private String name;
	private int lifePoints;
	private Field field;
	private boolean summonned;

	public Player(String name) throws IOException , FileNotFoundException , UnexpectedFormatException, CloneNotSupportedException {
		this.name = name;
		lifePoints = 8000;
		field = new Field();
		summonned = false;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoint) {
		this.lifePoints = lifePoint;
	}

	public String getName() {
		return name;
	}

	public Field getField() {
		return field;
	}

	@Override
	public boolean summonMonster(MonsterCard monster) {
		if (Card.getBoard().getWinner() == null
				&& Card.getBoard().getActivePlayer() == this) {
			Field f = Card.getBoard().getActivePlayer().getField();
			if (f.getMonstersArea().size() < 5) {
				if (f.getPhase() == Phase.MAIN1 || f.getPhase() == Phase.MAIN2) {
					if (!summonned) {
						int h = f.getHand().size();
						f.addMonsterToField(monster, Mode.ATTACK, false);
						if (f.getHand().size() < h) {
							summonned = true;
							monster.setSwitched(true);

							return true;
						}
					} else
						throw new MultipleMonsterAdditionException();
				} else
					throw new WrongPhaseException();
			} else
				throw new NoMonsterSpaceException();
		}
		return false;
	}

	@Override
	public boolean summonMonster(MonsterCard monster,
			ArrayList<MonsterCard> sacrifices) {
		if (Card.getBoard().getWinner() == null
				&& Card.getBoard().getActivePlayer() == this) {
			Field f = Card.getBoard().getActivePlayer().getField();
			if (f.getMonstersArea().size() < 5) {
				if (f.getPhase() == Phase.MAIN1 || f.getPhase() == Phase.MAIN2) {
					if (!summonned) {
						int h = f.getHand().size();
						f.addMonsterToField(monster, Mode.ATTACK, sacrifices);
						if (f.getHand().size() < h) {
							summonned = true;
							monster.setSwitched(true);

							return true;
						}
					} else
						throw new MultipleMonsterAdditionException();
				} else
					throw new WrongPhaseException();
			} else
				throw new NoMonsterSpaceException();
		}
		return false;
	}

	@Override
	public boolean setMonster(MonsterCard monster) {
		if (Card.getBoard().getWinner() == null
				&& Card.getBoard().getActivePlayer() == this) {
			Field f = Card.getBoard().getActivePlayer().getField();
			if (f.getMonstersArea().size() < 5) {
				if (f.getPhase() == Phase.MAIN1 || f.getPhase() == Phase.MAIN2) {
					if (!summonned) {
						int h = f.getHand().size();
						f.addMonsterToField(monster, Mode.DEFENSE, true);
						if (f.getHand().size() < h) {
							summonned = true;
							monster.setSwitched(true);
							return true;
						}
					} else
						throw new MultipleMonsterAdditionException();

				} else
					throw new WrongPhaseException();
			} else
				throw new NoMonsterSpaceException();

		}
		return false;
	}

	@Override
	public boolean setMonster(MonsterCard monster,
			ArrayList<MonsterCard> sacrifices) {
		if (Card.getBoard().getWinner() == null
				&& Card.getBoard().getActivePlayer() == this) {
			Field f = Card.getBoard().getActivePlayer().getField();
			if (f.getMonstersArea().size() < 5) {
				if (f.getPhase() == Phase.MAIN1 || f.getPhase() == Phase.MAIN2) {
					if (!summonned) {
						int h = f.getHand().size();
						monster.setHidden(true);
						f.addMonsterToField(monster, Mode.DEFENSE, sacrifices);
						if (f.getHand().size() < h) {
							summonned = true;
							monster.setSwitched(true);

							return true;
						}
					} else
						throw new MultipleMonsterAdditionException();
				} else
					throw new WrongPhaseException();
			}else
				throw new NoMonsterSpaceException();
		}
		return false;
	}

	@Override
	public boolean setSpell(SpellCard spell) throws IOException {
		if (Card.getBoard().getWinner() == null
				&& Card.getBoard().getActivePlayer() == this) {
			Field f = Card.getBoard().getActivePlayer().getField();
			if (f.getPhase() == Phase.MAIN1 || f.getPhase() == Phase.MAIN2) {
				if (spell.getLocation() == Location.HAND) {
					int h = f.getHand().size();
					f.addSpellToField(spell, null, true);
					if (f.getHand().size() < h)
						return true;
				}
			} else
				throw new WrongPhaseException();
		}
		return false;
	}

	@Override
	public boolean activateSpell(SpellCard spell, MonsterCard monster)
			throws IOException {
		if (Card.getBoard().getWinner() == null
				&& Card.getBoard().getActivePlayer() == this) {
			Field f = Card.getBoard().getActivePlayer().getField();
			if (f.getPhase() == Phase.MAIN1 || f.getPhase() == Phase.MAIN2) {
				int sa = f.getSpellArea().size();
				int h = f.getHand().size();
				if (spell.getLocation() == Location.FIELD)
					f.activateSetSpell(spell, monster);
				else if (spell.getLocation() == Location.HAND) {
					f.addSpellToField(spell, monster, false);
				}
				if (f.getSpellArea().size() < sa
						|| getField().getHand().size() < h)
					return true;
			} else
				throw new WrongPhaseException();
		}
		return false;
	}

	@Override
	public boolean declareAttack(MonsterCard activeMonster,
			MonsterCard opponentMonster) {
		if (Card.getBoard().getWinner() == null
				&& Card.getBoard().getActivePlayer() == this) {
			Player ap = Card.getBoard().getActivePlayer();
			Player op = Card.getBoard().getOpponentPlayer();
			if (ap.getField().getPhase() == Phase.BATTLE) {
				if (!activeMonster.isAttackedOnce()) {
					if (opponentMonster.getMode() == Mode.ATTACK) {
						if (activeMonster.getMode() == Mode.ATTACK) {

							int result = activeMonster.getAttackPoints()
									- opponentMonster.getAttackPoints();
							if (result > 0) {
								op.getField().removeMonsterToGraveyard(
										opponentMonster);
								op.setLifePoints(op.getLifePoints() - result);

							} else if (result < 0) {
								ap.getField().removeMonsterToGraveyard(
										activeMonster);
								ap.setLifePoints(ap.getLifePoints() + result);
							} else {
								op.getField().removeMonsterToGraveyard(
										opponentMonster);
								ap.getField().removeMonsterToGraveyard(
										activeMonster);
							}

						} else
							throw new DefenseMonsterAttackException();
					} else if (opponentMonster.getMode() == Mode.DEFENSE)
						if (activeMonster.getMode() == Mode.ATTACK) {

							int result = activeMonster.getAttackPoints()
									- opponentMonster.getDefensePoints();
							if (result > 0) {

								op.getField().removeMonsterToGraveyard(
										opponentMonster);
							} else if (result < 0)
								ap.setLifePoints(ap.getLifePoints() + result);
						} else
							throw new DefenseMonsterAttackException();
					if (ap.getLifePoints() <= 0)
						Card.getBoard().setWinner(op);
					if (op.getLifePoints() <= 0)
						Card.getBoard().setWinner(ap);
					activeMonster.setAttackedOnce(true);
					opponentMonster.setHidden(false);
					return true;
				} else
					throw new MonsterMultipleAttackException();
			} else
				throw new WrongPhaseException();
		}
		return false;
	}

	@Override
	public boolean declareAttack(MonsterCard activeMonster) {
		if (Card.getBoard().getWinner() == null
				&& Card.getBoard().getActivePlayer() == this) {
			if (Card.getBoard().getActivePlayer().getField().getPhase() == Phase.BATTLE) {
				if (!activeMonster.isAttackedOnce()) {
					if (activeMonster.getMode() == Mode.ATTACK) {
						if (Card.getBoard().getOpponentPlayer().getField()
								.getMonstersArea().isEmpty()) {
							Card.getBoard()
									.getOpponentPlayer()
									.setLifePoints(
											Card.getBoard().getOpponentPlayer()
													.getLifePoints()
													- activeMonster
															.getAttackPoints());
							if (Card.getBoard().getOpponentPlayer()
									.getLifePoints() <= 0)
								Card.getBoard().setWinner(
										Card.getBoard().getActivePlayer());
							activeMonster.setAttackedOnce(true);
							return true;
						}
					} else
						throw new DefenseMonsterAttackException();
				} else
					throw new MonsterMultipleAttackException();
			} else
				throw new WrongPhaseException();
		}
		return false;
	}

	@Override
	public void addCardToHand() {
		if (Card.getBoard().getActivePlayer() == this) {
			if (Card.getBoard().getActivePlayer().getField().getDeck()
					.getDeck().isEmpty())
				Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
			else
				Card.getBoard().getActivePlayer().getField().addCardToHand();
		}
	}

	@Override
	public void addNCardsToHand(int n) {
		if (Card.getBoard().getActivePlayer() == this) {
			if (Card.getBoard().getActivePlayer().getField().getDeck()
					.getDeck().isEmpty())
				Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
			else
				Card.getBoard().getActivePlayer().getField().addNCardsToHand(n);
		}
	}

	@Override
	public void endPhase() {
		if (Card.getBoard().getActivePlayer() == this) {
			Field f = Card.getBoard().getActivePlayer().getField();
			if (Card.getBoard().getWinner() == null) {
				if (f.getPhase() == Phase.MAIN1)
					f.setPhase(Phase.BATTLE);
				else if (f.getPhase() == Phase.BATTLE)
					f.setPhase(Phase.MAIN2);
				else if (f.getPhase() == Phase.MAIN2)
					endTurn();
			}
		}
	}

	@Override
	public boolean endTurn() {
		if (Card.getBoard().getActivePlayer() == this) {
			if (Card.getBoard().getActivePlayer().getField().getPhase() == Phase.MAIN2
					|| Card.getBoard().getActivePlayer().getField().getPhase() == Phase.MAIN1
					|| Card.getBoard().getActivePlayer().getField().getPhase() == Phase.BATTLE) {
				
				Card.getBoard().nextPlayer();
				Card.getBoard().getActivePlayer().getField()
						.setPhase(Phase.MAIN1);
				// MonsterCard.setSummonned(false);
				// MonsterCard.setSwitched(false);
				ArrayList<MonsterCard> am = this.getField().getMonstersArea();
				for (int i = 0; i < am.size(); i++) {
					am.get(i).setSwitched(false);
					am.get(i).setAttackedOnce(false);
				}
				summonned = false;

				return true;
			}
		}
		return false;
	}

	@Override
	public boolean switchMonsterMode(MonsterCard monster) {
		if (Card.getBoard().getActivePlayer().getField().getPhase() == Phase.MAIN2
				|| Card.getBoard().getActivePlayer().getField().getPhase() == Phase.MAIN1) {
			if (monster.getLocation() == Location.FIELD) {
				if (!monster.isSwitched()) {
					if (monster.getMode() == Mode.ATTACK)
						monster.setMode(Mode.DEFENSE);
					else {
						monster.setMode(Mode.ATTACK);
						monster.setHidden(false);
					}
					monster.setSwitched(true);
					return true;
				}
			}
		} else
			throw new WrongPhaseException();
		return false;
	}
}