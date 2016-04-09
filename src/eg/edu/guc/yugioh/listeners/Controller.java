package eg.edu.guc.yugioh.listeners;

import java.awt.SecondaryLoop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Deck;
import eg.edu.guc.yugioh.board.player.Phase;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.CardDestruction;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.DarkHole;
import eg.edu.guc.yugioh.cards.spells.GracefulDice;
import eg.edu.guc.yugioh.cards.spells.HarpieFeatherDuster;
import eg.edu.guc.yugioh.cards.spells.HeavyStorm;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.MonsterReborn;
import eg.edu.guc.yugioh.cards.spells.PotOfGreed;
import eg.edu.guc.yugioh.cards.spells.Raigeki;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.DefenseMonsterAttackException;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;
import eg.edu.guc.yugioh.gui.EndPhaseButton;
import eg.edu.guc.yugioh.gui.EndTurnButton;
import eg.edu.guc.yugioh.gui.GUI;
import eg.edu.guc.yugioh.gui.HandButton;
import eg.edu.guc.yugioh.gui.MonsterButton;
import eg.edu.guc.yugioh.gui.SpellButton;
import eg.edu.guc.yugioh.gui.activePlayerHandPanel;
import eg.edu.guc.yugioh.gui.defensMonsterButton;
import eg.edu.guc.yugioh.gui.gui2;

public class Controller implements ActionListener, MouseListener {
	Board board;
	GUI gui;
	Player p1;
	Player p2;
	JButton firstclick;
	JButton secondclick;
	JButton thirdclick;
	int attackpoints;
	int defensePoints;

	public Controller(Board board, GUI gui, Player p1, Player p2)
			throws IOException {
		this.board = board;
		this.gui = gui;
		this.p1 = p1;
		this.p2 = p2;
		// firstclick=null;
		// secondclick=null;
		// thirdclick=null;
		board.startGame(p1, p2);
		fillButtons();
		addListnerToButtons();
		attackpoints = 0;
		defensePoints = 0;
	}

	public void addListnerToButtons() {
		gui.getGraveyardButton1().addActionListener(this);
		gui.getGraveyardButton1().addMouseListener(this);
		gui.getGraveyardButton2().addActionListener(this);
		gui.getGraveyardButton2().addMouseListener(this);
		gui.getDeckButton1().addActionListener(this);
		gui.getDeckButton2().addActionListener(this);
		if (gui.getEndPhaseButton().getActionListeners().length < 1)
			gui.getEndPhaseButton().addActionListener(this);
		if (gui.getEndTurnButton().getActionListeners().length < 1)
			gui.getEndTurnButton().addActionListener(this);
		ArrayList<MonsterButton> amp = gui.getApp().getApma().getMonsters();
		for (int i = 0; i < amp.size(); i++) {
			if (amp.get(i).getActionListeners().length < 1)
				amp.get(i).addActionListener(this);
			amp.get(i).addMouseListener(this);
		}
		ArrayList<SpellButton> asp = gui.getApp().getApsa().getSpells();
		for (int i = 0; i < asp.size(); i++) {
			if (asp.get(i).getActionListeners().length < 1)
				asp.get(i).addMouseListener(this);
			asp.get(i).addActionListener(this);
		}

		ArrayList<MonsterButton> omp = gui.getOpp().getOpma().getMonsters();
		for (int i = 0; i < omp.size(); i++) {
			if (omp.get(i).getActionListeners().length < 1)
				omp.get(i).addMouseListener(this);
			omp.get(i).addActionListener(this);
		}

		ArrayList<SpellButton> osp = gui.getOpp().getOpsa().getSpells();
		for (int i = 0; i < osp.size(); i++) {
			if (osp.get(i).getActionListeners().length < 1)
				osp.get(i).addMouseListener(this);
			osp.get(i).addActionListener(this);
		}

		ArrayList<HandButton> ahp = gui.getAphp().getHand();
		for (int i = 0; i < ahp.size(); i++) {
			if (ahp.get(i).getActionListeners().length < 1)
				ahp.get(i).addMouseListener(this);
			ahp.get(i).addActionListener(this);
		}

		ArrayList<HandButton> ohp = gui.getOphp().getHand();
		for (int i = 0; i < ohp.size(); i++) {
			if (ohp.get(i).getActionListeners().length < 1)
				ohp.get(i).addMouseListener(this);
			ohp.get(i).addActionListener(this);

		}
	}

	public void fillButtons() throws IOException {

		if (board.getWinner() != null) {
			JOptionPane.showMessageDialog(null, board.getWinner().getName()
					+ " 7tt 3alek", "b77", JOptionPane.PLAIN_MESSAGE);
			gui.setVisible(false);
			try {
				gui2 tany = new gui2();
			} catch (Exception e) {

			}
		}

		ArrayList<MonsterCard> activemonsterarea = p1.getField()
				.getMonstersArea();
		gui.getApp().getApma().getMonsters().clear();
		gui.getApp().getApma().removeAll();
		gui.getApp().getApma().revalidate();
		gui.getApp().getApma().repaint();
		for (int i = 0; i < activemonsterarea.size(); i++) {
			String s = p1.getField().getMonstersArea().get(i).getName();
			if (p1.getField().getMonstersArea().get(i).getMode() == Mode.ATTACK) {
				MonsterButton mb = new MonsterButton(s);
				gui.getApp().getApma().getMonsters().add(mb);
				gui.getApp().getApma().add(mb);
			} else {
				defensMonsterButton dmb = new defensMonsterButton(s + "r");
				gui.getApp().getApma().getMonsters().add(dmb);
				gui.getApp().getApma().add(dmb);
				if (activemonsterarea.get(i).isHidden()) {
					dmb.setIcon(new ImageIcon(
							"E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"
									+ "Backr.png"));
				}
			}
		}

		ArrayList<SpellCard> activespellarea = p1.getField().getSpellArea();
		gui.getApp().getApsa().getSpells().clear();
		gui.getApp().getApsa().removeAll();
		gui.getApp().getApsa().revalidate();
		gui.getApp().getApsa().repaint();
		for (int i = 0; i < activespellarea.size(); i++) {
			String s = p1.getField().getSpellArea().get(i).getName();
			SpellButton sb = new SpellButton(s);
			gui.getApp().getApsa().getSpells().add(sb);
			gui.getApp().getApsa().add(sb);
			if (activespellarea.get(i).isHidden())
				sb.setIcon(new ImageIcon(
						"E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"
								+ "Back.png"));
		}
		ArrayList<MonsterCard> opponentmonsterarea = p2.getField()
				.getMonstersArea();
		gui.getOpp().getOpma().getMonsters().clear();
		gui.getOpp().getOpma().removeAll();
		gui.getOpp().getOpma().revalidate();
		gui.getOpp().getOpma().repaint();
		for (int i = 0; i < opponentmonsterarea.size(); i++) {
			String s = p2.getField().getMonstersArea().get(i).getName();
			if (p2.getField().getMonstersArea().get(i).getMode() == Mode.ATTACK) {
				MonsterButton mb = new MonsterButton(s);
				gui.getOpp().getOpma().getMonsters().add(mb);
				gui.getOpp().getOpma().add(mb);
			} else {
				defensMonsterButton dmb = new defensMonsterButton(s + "r");
				gui.getOpp().getOpma().getMonsters().add(dmb);
				gui.getOpp().getOpma().add(dmb);
				if (opponentmonsterarea.get(i).isHidden()) {
					dmb.setIcon(new ImageIcon(
							"E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"
									+ "Backr.png"));
				}
			}
		}

		ArrayList<SpellCard> opponentspellarea = p2.getField().getSpellArea();
		gui.getOpp().getOpsa().getSpells().clear();
		gui.getOpp().getOpsa().removeAll();
		gui.getOpp().getOpsa().revalidate();
		gui.getOpp().getOpsa().repaint();
		for (int i = 0; i < opponentspellarea.size(); i++) {
			String s = p2.getField().getSpellArea().get(i).getName();
			SpellButton sb = new SpellButton(s);
			gui.getOpp().getOpsa().getSpells().add(sb);
			gui.getOpp().getOpsa().add(sb);
			if (opponentspellarea.get(i).isHidden())
				sb.setIcon(new ImageIcon(
						"E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"
								+ "Back.png"));
		}
		ArrayList<Card> p1h = p1.getField().getHand();
		gui.getAphp().getHand().clear();
		gui.getAphp().removeAll();
		gui.getAphp().revalidate();
		gui.getAphp().repaint();
		for (int i = 0; i < p1h.size(); i++) {
			String s = p1.getField().getHand().get(i).getName();
			HandButton hb = new HandButton(s);
			if (!p1.equals(board.getActivePlayer())) {
				hb.setIcon(new ImageIcon(
						"E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"
								+ "Back.png"));
			}
			gui.getAphp().getHand().add(hb);
			gui.getAphp().add(hb);

		}
		
		ArrayList<Card> p2h = p2.getField().getHand();
		gui.getOphp().getHand().clear();
		gui.getOphp().removeAll();
		gui.getOphp().revalidate();
		gui.getOphp().repaint();
		for (int i = 0; i < p2h.size(); i++) {
			if (p2.equals(board.getActivePlayer()))
				p2.getField().getHand().get(i).setHidden(false);
			else
				p2.getField().getHand().get(i).setHidden(true);
			String s = p2.getField().getHand().get(i).getName();
			HandButton hb = new HandButton(s);
			gui.getOphp().getHand().add(hb);
			gui.getOphp().add(hb);
			if (p2h.get(i).isHidden())
				hb.setIcon(new ImageIcon(
						"E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"
								+ "Back.png"));

		}
		//gui.getScrollPane2().add(gui.getOphp());
		gui.getOphp().revalidate();
		gui.getOphp().repaint();

		EndPhaseButton endPhase = gui.getEndPhaseButton();
		String s = "";
		if ((board.getActivePlayer().getField().getPhase() == Phase.MAIN1))
			s = "MAIN 1";
		else if (board.getActivePlayer().getField().getPhase() == Phase.MAIN2)
			s = "MAIN 2";
		else if (board.getActivePlayer().getField().getPhase() == Phase.BATTLE)
			s = "BATTLE";
		endPhase.setLabel("END "+s);
		//gui.setEndPhaseButton(endPhase);
		String x1 = "" + p1.getLifePoints();
		gui.getLifePoints1().setText(x1);
		String x2 = "" + p2.getLifePoints();
		gui.getLifePoints2().setText(x2);
		
		EndTurnButton endTurn = gui.getEndTurnButton();
		String s2 = board.getActivePlayer().getName();
		endTurn.setLabel("END "+s2+"'s TURN");

		String deck1 = "" + p1.getField().getDeck().getDeck().size();
		gui.getD1().setText(deck1);
		String deck2 = "" + p2.getField().getDeck().getDeck().size();
		gui.getD2().setText(deck2);
		if (!p1.getField().getGraveyard().isEmpty()) {
			String name1 = p1.getField().getGraveyard()
					.get(p1.getField().getGraveyard().size() - 1).getName();
			gui.getGraveyardButton1().setIcon(
					new ImageIcon(
							"E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"
									+ name1 + ".png"));
		}
		if (!p2.getField().getGraveyard().isEmpty()) {
			String name2 = p2.getField().getGraveyard()
					.get(p2.getField().getGraveyard().size() - 1).getName();
			gui.getGraveyardButton2().setIcon(
					new ImageIcon(
							"E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"
									+ name2 + ".png"));
		}

	}

	public void addWithoutSacri(MonsterCard c, Player p) throws IOException {
		String[] options = { "Summon", "Set" };
		int select = JOptionPane.showOptionDialog(null, "t7eb eh ?", "Monster",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
				options, options[0]);
		if (select == 0) {
			p.summonMonster((MonsterCard) c);
			fillButtons();
			addListnerToButtons();

		} else if (select == 1) {
			p.setMonster((MonsterCard) c);
			fillButtons();
			addListnerToButtons();
		} else
			firstclick = null;
	}

	public void addWithOneSacri(MonsterCard c, Player p) throws IOException {
		int i = 0;
		if (p.equals(p1)) {
			if (!gui.getApp().getApma().getMonsters().contains(secondclick)) {
				JOptionPane.showMessageDialog(null,
						"monster mn 3andak ya 7amada -_-", ":3",
						JOptionPane.PLAIN_MESSAGE);
				secondclick = null;
				return;
			} else
				i = gui.getApp().getApma().getMonsters().indexOf(secondclick);
		} else {
			if (!gui.getOpp().getOpma().getMonsters().contains(secondclick)) {
				JOptionPane.showMessageDialog(null,
						"monster mn 3andak ya 7amada -_-", ":3",
						JOptionPane.PLAIN_MESSAGE);
				secondclick = null;
				return;
			} else
				i = gui.getOpp().getOpma().getMonsters().indexOf(secondclick);
		}
		MonsterCard mc = p.getField().getMonstersArea().get(i);
		System.out.println(mc.getName());
		ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>();
		sacrifices.add(mc);

		String[] options = { "Summon", "Set" };
		int select = JOptionPane.showOptionDialog(null, "t7eb eh ?", "Monster",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[0]);
		if (select == 0) {
			p.summonMonster((MonsterCard) c, sacrifices);
			fillButtons();
			addListnerToButtons();
			firstclick = null;
			secondclick = null;

		} else if (select == 1) {
			p.setMonster((MonsterCard) c, sacrifices);
			fillButtons();
			addListnerToButtons();
			firstclick = null;
			secondclick = null;
		} else {
			firstclick = null;
			secondclick = null;
		}
	}

	public void addWithTwoSacri(Card c, Player p) throws IOException {
		int i = 0;
		int j = 0;
		if (p.equals(p1)) {
			if (!gui.getApp().getApma().getMonsters().contains(secondclick)) {
				JOptionPane.showMessageDialog(null,
						"monster mn 3andak ya 7amada -_-", ":3",
						JOptionPane.PLAIN_MESSAGE);
				secondclick = null;
				return;
			} else
				i = gui.getApp().getApma().getMonsters().indexOf(secondclick);
		} else {
			if (!gui.getOpp().getOpma().getMonsters().contains(secondclick)) {
				JOptionPane.showMessageDialog(null,
						"monster mn 3andak ya 7amada -_-", ":3",
						JOptionPane.PLAIN_MESSAGE);
				secondclick = null;
				return;
			} else
				i = gui.getOpp().getOpma().getMonsters().indexOf(secondclick);
		}
		MonsterCard mc1 = p.getField().getMonstersArea().get(i);
		if (p.equals(p1)) {
			if (!gui.getApp().getApma().getMonsters().contains(thirdclick)) {
				JOptionPane.showMessageDialog(null,
						"el monster el tany mn 3andak bardo ya 7amada -_-",
						":3", JOptionPane.PLAIN_MESSAGE);
				thirdclick = null;
				return;
			} else
				j = gui.getApp().getApma().getMonsters().indexOf(thirdclick);
		} else {
			if (!gui.getOpp().getOpma().getMonsters().contains(thirdclick)) {
				JOptionPane.showMessageDialog(null,
						"el monster el tany mn 3andak bardo ya 7amada -_-",
						":3", JOptionPane.PLAIN_MESSAGE);
				thirdclick = null;
				return;
			} else
				j = gui.getOpp().getOpma().getMonsters().indexOf(thirdclick);
		}
		MonsterCard mc2 = p.getField().getMonstersArea().get(j);
		ArrayList<MonsterCard> sacrifices = new ArrayList<MonsterCard>();
		sacrifices.add(mc1);
		sacrifices.add(mc2);
		String[] options = { "Summon", "Set" };
		int select = JOptionPane.showOptionDialog(null, "t7eb eh ?", "Monster",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
				options, options[0]);
		if (select == 0) {
			p.summonMonster((MonsterCard) c, sacrifices);
			fillButtons();
			addListnerToButtons();
			firstclick = null;
			secondclick = null;
			thirdclick = null;

		} else if (select == 1) {
			p.setMonster((MonsterCard) c, sacrifices);
			fillButtons();
			addListnerToButtons();
			firstclick = null;
			secondclick = null;
			thirdclick = null;
		} else {
			firstclick = null;
			secondclick = null;
			thirdclick = null;
		}
	}

	public void attackMonster(MonsterCard mine, MonsterCard his, Player p)
			throws IOException {
		p.declareAttack(mine, his);
		fillButtons();
		addListnerToButtons();

	}

	public void attackLifePoints(MonsterCard mine, Player p) throws IOException {
		p.declareAttack(mine);
		fillButtons();
		addListnerToButtons();

	}

	public void switchMonster(MonsterCard mine, Player p) throws IOException {
		if (!p.switchMonsterMode(mine)) {
			JOptionPane.showMessageDialog(null,
					"you can't switch the Mode of the monster twice per turn",
					":3", JOptionPane.PLAIN_MESSAGE);}
		fillButtons();
		addListnerToButtons();
	}

	public void activateSpell2(SpellCard sc, Player p) throws IOException {
		int i = 0;
		String name = sc.getName();
		switch (name) {
		case "Change Of Heart":
			if (board.getOpponentPlayer().getField().getMonstersArea()
					.isEmpty()) {
				JOptionPane.showMessageDialog(null, "ma7eltosh 7aga !", ":3",
						JOptionPane.PLAIN_MESSAGE);
				firstclick = null;
				secondclick = null;
				return;
			}
			if (p.equals(p1)) {
				if (!gui.getOpp().getOpma().getMonsters().contains(secondclick)) {
					JOptionPane
							.showMessageDialog(
									null,
									"nrakkez shwaya ? -_- , choose one of the opponent monsters",
									":3", JOptionPane.PLAIN_MESSAGE);
					secondclick = null;
					return;
				} else
					i = gui.getOpp().getOpma().getMonsters()
							.indexOf(secondclick);
			} else {
				if (!gui.getApp().getApma().getMonsters().contains(secondclick)) {
					JOptionPane
							.showMessageDialog(
									null,
									"nrakkez shwaya ? -_- choose one of the opponent monsters",
									":3", JOptionPane.PLAIN_MESSAGE);
					secondclick = null;
					return;
				} else
					i = gui.getApp().getApma().getMonsters()
							.indexOf(secondclick);
			}
			MonsterCard his = board.getOpponentPlayer().getField()
					.getMonstersArea().get(i);
			p.activateSpell(sc, his);
			fillButtons();
			addListnerToButtons();
			firstclick = null;
			secondclick = null;
			break;
		case "Mage Power":
			if (p.equals(p1)) {
				if (!gui.getApp().getApma().getMonsters().contains(secondclick)) {
					JOptionPane
							.showMessageDialog(
									null,
									"nrakkez shwaya ? -_- choose one of your monsters bn2ool",
									":3", JOptionPane.PLAIN_MESSAGE);
					secondclick = null;
					return;
				} else
					i = gui.getApp().getApma().getMonsters()
							.indexOf(secondclick);

			} else {
				if (!gui.getOpp().getOpma().getMonsters().contains(secondclick)) {
					JOptionPane
							.showMessageDialog(
									null,
									"nrakkez shwaya ? -_- choose one of your monsters bn2ool",
									":3", JOptionPane.PLAIN_MESSAGE);
					secondclick = null;
					return;
				} else
					i = gui.getOpp().getOpma().getMonsters()
							.indexOf(secondclick);
			}
			MonsterCard mine = p.getField().getMonstersArea().get(i);
			p.activateSpell(sc, mine);
			fillButtons();
			addListnerToButtons();
			firstclick = null;
			secondclick = null;
			break;
		default:
			firstclick = null;
			secondclick = null;
		}
	}

	public void activateSpell(SpellCard sc, Player p) throws IOException {

		int i = 0;
		String name = sc.getName();
		switch (name) {
		case "Change Of Heart":
			JOptionPane.showMessageDialog(null,
					"choose what you want from the opponent monsters", "3:)",
					JOptionPane.PLAIN_MESSAGE);
			break;
		case "Mage Power":
			JOptionPane.showMessageDialog(null,
					"choose one of your monsters to make him fashe5",
					"Mage Power", JOptionPane.PLAIN_MESSAGE);
			break;
		default:
			p.activateSpell(sc, null);
			fillButtons();
			addListnerToButtons();
			firstclick = null;
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (p1.equals(board.getActivePlayer()))
				actionPerformed2(e, p1);
			else {
				actionPerformed2(e, p2);
			}
		} catch (DefenseMonsterAttackException e1) {
			JOptionPane.showMessageDialog(null, "mayenfa3sh b defence a7maar",
					"esss esss", JOptionPane.PLAIN_MESSAGE);
			firstclick = null;
			secondclick = null;
		} catch (MonsterMultipleAttackException e1) {
			JOptionPane.showMessageDialog(null, "marra kfaya", "esss esss",
					JOptionPane.PLAIN_MESSAGE);
			firstclick = null;
			secondclick = null;

		} catch (MultipleMonsterAdditionException e1) {
			JOptionPane.showMessageDialog(null, "wa7ed kfaya", "esss esss",
					JOptionPane.PLAIN_MESSAGE);
			firstclick = null;
			secondclick = null;
		} catch (NoMonsterSpaceException e1) {
			JOptionPane.showMessageDialog(null, "ma3adsh fe mkan", "esss esss",
					JOptionPane.PLAIN_MESSAGE);
			firstclick = null;
			secondclick = null;
		} catch (NoSpellSpaceException e1) {
			JOptionPane.showMessageDialog(null, "ma3adsh fe mkan", "esss esss",
					JOptionPane.PLAIN_MESSAGE);
			firstclick = null;
			secondclick = null;
		} catch (WrongPhaseException e1) {
			JOptionPane.showMessageDialog(null, "msh phaso", "esss esss",
					JOptionPane.PLAIN_MESSAGE);
			firstclick = null;
			secondclick = null;

		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "wa7da kfaya", "esss esss",
					JOptionPane.PLAIN_MESSAGE);
		}
	}

	public void actionPerformed2(ActionEvent e, Player p) throws IOException {
		int i = 0;
		int j = 0;
		if (e.getSource() instanceof EndTurnButton) {
			// firstclick = (EndTurnButton) e.getSource();
			p.endTurn();
			fillButtons();
			addListnerToButtons();
			firstclick = null;
			secondclick = null;
			thirdclick = null;
		} else if (e.getSource() instanceof EndPhaseButton) {
			// firstclick = (EndPhaseButton) e.getSource();
			p.endPhase();
			fillButtons();
			addListnerToButtons();
			if (board.getActivePlayer().getField().getPhase() == Phase.BATTLE)
				JOptionPane.showMessageDialog(null,
						"choose a monster to Attack with", "Attack",
						JOptionPane.PLAIN_MESSAGE);
			firstclick = null;
			secondclick = null;
			thirdclick = null;
		}

		else if (firstclick == null) {
			if (e.getSource() instanceof HandButton) {
				if (p.getField().getPhase() == Phase.MAIN1
						|| p.getField().getPhase() == Phase.MAIN2) {
					firstclick = (HandButton) e.getSource();
					if (p.equals(p1)) {
						i = gui.getAphp().getHand().indexOf(firstclick);
					} else if (p.equals(p2))
						i = gui.getOphp().getHand().indexOf(firstclick);
					Card c = p.getField().getHand().get(i);
					if (c instanceof MonsterCard) {
						if (((MonsterCard) c).getLevel() <= 4) {
							addWithoutSacri((MonsterCard) c, p);
							firstclick = null;
							return;
						} else if (((MonsterCard) c).getLevel() <= 6) {
							if (p.getField().getMonstersArea().size() >= 1) {
								JOptionPane
										.showMessageDialog(
												null,
												"da77y bel omm 3shan el ganeen y3eesh :v",
												"sacrifice",
												JOptionPane.PLAIN_MESSAGE);
								return;
							} else {
								JOptionPane.showMessageDialog(null,
										"ma3andaksh ykammel", "-_-",
										JOptionPane.PLAIN_MESSAGE);
								firstclick = null;
							}
						} else if (((MonsterCard) c).getLevel() <= 8) {
							if (p.getField().getMonstersArea().size() >= 2) {
								JOptionPane
										.showMessageDialog(
												null,
												"da77y bel abb wel omm 3shan el ganeen y3eesh :v",
												"sacrifice",
												JOptionPane.PLAIN_MESSAGE);
								return;
							} else {
								JOptionPane.showMessageDialog(null,
										"ma3aksh ykammel", "-_-",
										JOptionPane.PLAIN_MESSAGE);
								firstclick = null;
							}
						}

					} else if (c instanceof SpellCard) {
						String[] options = { "activate", "Set" };
						int select = JOptionPane.showOptionDialog(null,
								"t7eb eh ?", "Spell",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.WARNING_MESSAGE, null, options,
								options[0]);
						if (select == 0) {
							activateSpell((SpellCard) c, p);

						} else if (select == 1) {
							p.setSpell((SpellCard) c);
							fillButtons();
							addListnerToButtons();
							firstclick = null;
						} else
							firstclick = null;

					}

				} else
					firstclick = null;

			} else if (e.getSource() instanceof MonsterButton) {
				if (p.getField().getPhase() == Phase.BATTLE) {
					if (board.getOpponentPlayer().getField().getMonstersArea()
							.size() != 0) {
						firstclick = (MonsterButton) e.getSource();
						if (p.equals(p1)) {
							if (!gui.getApp().getApma().getMonsters()
									.contains(firstclick)) {
								JOptionPane.showMessageDialog(null,
										"Choose a monster of yours -_-",
										"Attack", JOptionPane.PLAIN_MESSAGE);
								firstclick = null;
								return;
							}
						} else {
							if (!gui.getOpp().getOpma().getMonsters()
									.contains(firstclick)) {
								JOptionPane.showMessageDialog(null,
										"Choose a monster of yours -_-",
										"Attack", JOptionPane.PLAIN_MESSAGE);
								firstclick = null;
								return;
							}
						}
						JOptionPane.showMessageDialog(null,
								"Choose a monster w 7ott 3aleh", "Attack",
								JOptionPane.PLAIN_MESSAGE);
						return;
					} else {
						firstclick = (MonsterButton) e.getSource();
						if (p.equals(p1)) {
							i = gui.getApp().getApma().getMonsters()
									.indexOf(firstclick);
						} else
							i = gui.getOpp().getOpma().getMonsters()
									.indexOf(firstclick);
						MonsterCard mine = p.getField().getMonstersArea()
								.get(i);
						attackLifePoints(mine, p);
						firstclick = null;

					}
				} else if (p.getField().getPhase() != Phase.BATTLE) {

					firstclick = (MonsterButton) e.getSource();
					if (p.equals(p1)) {
						if (!gui.getApp().getApma().getMonsters()
								.contains(firstclick)) {
							JOptionPane.showMessageDialog(null,
									"5allek fe wo7oshak", "Attack",
									JOptionPane.PLAIN_MESSAGE);
							firstclick = null;
							return;
						} else
							i = gui.getApp().getApma().getMonsters()
									.indexOf(firstclick);

					} else {
						if (!gui.getOpp().getOpma().getMonsters()
								.contains(firstclick)) {
							JOptionPane.showMessageDialog(null,
									"5allek fe wo7oshak", "Attack",
									JOptionPane.PLAIN_MESSAGE);
							firstclick = null;
							return;
						} else
							i = gui.getOpp().getOpma().getMonsters()
									.indexOf(firstclick);
					}
					MonsterCard mine = p.getField().getMonstersArea().get(i);
					switchMonster(mine, p);
					firstclick = null;
				}
			} else if (e.getSource() instanceof SpellButton) {
				firstclick = (SpellButton) e.getSource();
				if (p.equals(p1)) {
					if (!gui.getApp().getApsa().getSpells()
							.contains(firstclick)) {
						JOptionPane.showMessageDialog(null,
								"5allek fe Spellak", "Attack",
								JOptionPane.PLAIN_MESSAGE);
						firstclick = null;
						return;
					} else
						i = gui.getApp().getApsa().getSpells()
								.indexOf(firstclick);

				} else {
					if (!gui.getOpp().getOpsa().getSpells()
							.contains(firstclick)) {
						JOptionPane.showMessageDialog(null,
								"5allek fe Spellak", "Attack",
								JOptionPane.PLAIN_MESSAGE);
						firstclick = null;
						return;
					} else
						i = gui.getOpp().getOpsa().getSpells()
								.indexOf(firstclick);
				}
				SpellCard mine = p.getField().getSpellArea().get(i);
				activateSpell(mine, p);

			}

		} else if (secondclick == null) {
			if (e.getSource() instanceof MonsterButton) {
				if (firstclick instanceof HandButton) {
					if (p.equals(p1)) {
						i = gui.getAphp().getHand().indexOf(firstclick);
					} else if (p.equals(p2))
						i = gui.getOphp().getHand().indexOf(firstclick);
					Card c = p.getField().getHand().get(i);
					if (c instanceof MonsterCard) {
						secondclick = (MonsterButton) e.getSource();
						if (((MonsterCard) c).getLevel() == 5
								|| ((MonsterCard) c).getLevel() == 6) {
							addWithOneSacri((MonsterCard) c, p);
							return;
						} else if (((MonsterCard) c).getLevel() == 7
								|| ((MonsterCard) c).getLevel() == 8) {
							if (p.getField().getMonstersArea().size() >= 2) {

							} else {
								firstclick = null;
								secondclick = null;
							}

						}
					} else {
						secondclick = (MonsterButton) e.getSource();
						activateSpell2((SpellCard) c, p);
						return;
					}
				} else if (firstclick instanceof MonsterButton) {
					if (p.getField().getPhase() == Phase.BATTLE) {
						secondclick = (MonsterButton) e.getSource();
						if (p.equals(p1))
							j = gui.getApp().getApma().getMonsters()
									.indexOf(firstclick);
						else if (p.equals(p2))
							j = gui.getOpp().getOpma().getMonsters()
									.indexOf(firstclick);
						MonsterCard mine = p.getField().getMonstersArea()
								.get(j);

						if (p.equals(p2)) {
							if (gui.getApp().getApma().getMonsters()
									.contains(secondclick)) {
								j = gui.getApp().getApma().getMonsters()
										.indexOf(secondclick);
								MonsterCard mc = board.getOpponentPlayer()
										.getField().getMonstersArea().get(j);
								attackMonster(mine, mc, p);
								firstclick = null;
								secondclick = null;
							} else {
								secondclick = null;
								JOptionPane.showMessageDialog(null,
										"hathagem nafsak yahbal !", "-_-",
										JOptionPane.PLAIN_MESSAGE);
								return;
							}

						} else if (p.equals(p1)) {
							if (gui.getOpp().getOpma().getMonsters()
									.contains(secondclick)) {
								j = gui.getOpp().getOpma().getMonsters()
										.indexOf(secondclick);
								MonsterCard mc = board.getOpponentPlayer()
										.getField().getMonstersArea().get(j);
								attackMonster(mine, mc, p);
								firstclick = null;
								secondclick = null;
							} else {
								secondclick = null;
								JOptionPane.showMessageDialog(null,
										"hathagem nafsak yahbal !", "-_-",
										JOptionPane.PLAIN_MESSAGE);
								return;
							}
						}

					}
				} else if (firstclick instanceof SpellButton) {
					secondclick = (MonsterButton) e.getSource();
					if (p.equals(p1))
						j = gui.getApp().getApsa().getSpells()
								.indexOf(firstclick);
					else if (p.equals(p2))
						j = gui.getOpp().getOpsa().getSpells()
								.indexOf(firstclick);
					SpellCard sc = p.getField().getSpellArea().get(j);
					activateSpell2(sc, p);
				}

			} else {
				secondclick = null;
			}
		} else if (thirdclick == null) {
			if (e.getSource() instanceof MonsterButton
					&& firstclick instanceof HandButton
					&& secondclick instanceof MonsterButton) {
				thirdclick = (MonsterButton) e.getSource();
				if (p.equals(p1)) {
					i = gui.getAphp().getHand().indexOf(firstclick);
				} else if (p.equals(p2))
					i = gui.getOphp().getHand().indexOf(firstclick);
				Card c = p.getField().getHand().get(i);
				addWithTwoSacri(c, p);
			} else
				thirdclick = null;
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// int i = 0;

		String name = "";
		if (e.getSource() instanceof MonsterButton) {
			if (gui.getApp().getApma().getMonsters().contains(e.getSource())) {
				int i = gui.getApp().getApma().getMonsters()
						.indexOf(e.getSource());
				MonsterCard mc = p1.getField().getMonstersArea().get(i);
				attackpoints = mc.getAttackPoints();
				defensePoints = mc.getDefensePoints();
				gui.getAttackAndDefence().setText(
						"Attack : " + attackpoints + " / Defense : "
								+ defensePoints);

				name = p1.getField().getMonstersArea().get(i).getName();

			} else {
				int i = gui.getOpp().getOpma().getMonsters()
						.indexOf(e.getSource());
				MonsterCard mc = p2.getField().getMonstersArea().get(i);
				attackpoints = mc.getAttackPoints();
				defensePoints = mc.getDefensePoints();
				gui.getAttackAndDefence().setText(
						"Attack : " + attackpoints + " / Defense : "
								+ defensePoints);

				name = p2.getField().getMonstersArea().get(i).getName();
			}

			gui.getSv().setIcon(
					new ImageIcon(
							"E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"
									+ name + "1" + ".png"));

		} else if (e.getSource() instanceof SpellButton) {
			gui.getAttackAndDefence().setText("");

			if (p1.equals(board.getActivePlayer())) {
				if (gui.getApp().getApsa().getSpells().contains(e.getSource())) {
					int i = gui.getApp().getApsa().getSpells()
							.indexOf(e.getSource());

					name = p1.getField().getSpellArea().get(i).getName();
				} else
					name = "Back";
			} else {
				if (gui.getOpp().getOpsa().getSpells().contains(e.getSource())) {
					int i = gui.getOpp().getOpsa().getSpells()
							.indexOf(e.getSource());
					name = p2.getField().getSpellArea().get(i).getName();
				} else
					name = "Back";
			}

			gui.getSv().setIcon(
					new ImageIcon(
							"E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"
									+ name + "1" + ".png"));

		} else if (e.getSource() instanceof HandButton) {
			if (p1.equals(board.getActivePlayer())) {
				if (gui.getAphp().getHand()
						.contains((HandButton) e.getSource())) {
					int i = gui.getAphp().getHand().indexOf(e.getSource());
					if (p1.getField().getHand().get(i) instanceof MonsterCard) {
						MonsterCard mc = (MonsterCard) p1.getField().getHand()
								.get(i);
						attackpoints = mc.getAttackPoints();
						defensePoints = mc.getDefensePoints();
						gui.getAttackAndDefence().setText(
								"Attack : " + attackpoints + " / Defense : "
										+ defensePoints);
					} else
						gui.getAttackAndDefence().setText("");

					name = p1.getField().getHand().get(i).getName();
				} else {
					name = "Back";
					gui.getAttackAndDefence().setText("");
				}
			} else {
				if (gui.getOphp().getHand()
						.contains((HandButton) e.getSource())) {
					int i = gui.getOphp().getHand().indexOf(e.getSource());
					if (p2.getField().getHand().get(i) instanceof MonsterCard) {
						MonsterCard mc = (MonsterCard) p2.getField().getHand()
								.get(i);
						attackpoints = mc.getAttackPoints();
						defensePoints = mc.getDefensePoints();
						gui.getAttackAndDefence().setText(
								"Attack : " + attackpoints + " / Defense : "
										+ defensePoints);
					} else
						gui.getAttackAndDefence().setText("");
					name = p2.getField().getHand().get(i).getName();
				} else {
					gui.getAttackAndDefence().setText("");
					name = "Back";
				}
			}
			gui.getSv().setIcon(
					new ImageIcon(
							"E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"
									+ name + "1" + ".png"));
		} else if (gui.getGraveyardButton1().equals(e.getSource())) {
			gui.getAttackAndDefence().setText("");
			if (!p1.getField().getGraveyard().isEmpty()) {
				name = p1.getField().getGraveyard()
						.get(p1.getField().getGraveyard().size() - 1).getName();
				gui.getSv().setIcon(
						new ImageIcon(
								"E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"
										+ name + "1" + ".png"));
			}
		} else if (gui.getGraveyardButton2().equals(e.getSource())) {
			gui.getAttackAndDefence().setText("");
			if (!p2.getField().getGraveyard().isEmpty()) {
				name = p2.getField().getGraveyard()
						.get(p2.getField().getGraveyard().size() - 1).getName();
				gui.getSv().setIcon(
						new ImageIcon(
								"E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"
										+ name + "1" + ".png"));
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}