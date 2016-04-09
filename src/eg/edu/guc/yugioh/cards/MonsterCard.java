package eg.edu.guc.yugioh.cards;

import java.io.IOException;

import eg.edu.guc.yugioh.board.player.Phase;

public class MonsterCard extends Card {

	private int level;
	private int defensePoints;
	private int attackPoints;
	private Mode mode;
	private boolean switched;
	private boolean attackedOnce;
	

	public MonsterCard(String name, String description, int level, int attack,
			int defence) {
		super(name, description);
		this.level = level;
		this.defensePoints = defence;
		this.attackPoints = attack;
		mode = Mode.DEFENSE;
		setSwitched(false);
		setAttackedOnce(false);
		// TODO Auto-generated constructor stub
	}

	public int getLevel() {
		return level;
	}

	public int getDefensePoints() {
		return defensePoints;
	}

	public void setDefensePoints(int defensePoint) {
		this.defensePoints = defensePoint;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public void setAttackPoints(int attackPoint) {
		this.attackPoints = attackPoint;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	@Override
	public void action(MonsterCard monster) throws IOException {
		// Card.getBoard().getActivePlayer().getField().setPhase(Phase.BATTLE);
		Card.getBoard().getActivePlayer().declareAttack(this, monster);
	}

	public void action() throws IOException {
		// Card.getBoard().getActivePlayer().getField().setPhase(Phase.BATTLE);
		Card.getBoard().getActivePlayer().declareAttack(this);
	}

	public boolean isSwitched() {
		return switched;
	}

	public void setSwitched(boolean switched) {
		this.switched = switched;
	}

	public boolean isAttackedOnce() {
		return attackedOnce;
	}

	public void setAttackedOnce(boolean attackedOnce) {
		this.attackedOnce = attackedOnce;
	}

	
}
