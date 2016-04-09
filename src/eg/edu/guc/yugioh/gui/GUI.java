package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GUI extends JFrame {
  
	private activePlayerPanel app ;
	private opponentPlayerPanel opp ;
	private activePlayerHandPanel aphp ;
	private JLabel d1;
	private JLabel d2;
	private opponentPlayerHandPanel ophp ;
	private JLabel lifePoints1 ;
	private JLabel lifePoints2 ;
	private JLabel playerName1 ;
	private JLabel playerName2 ;
	private JButton DeckButton1 ;
	private JButton GraveyardButton1;
	private JButton DeckButton2 ;
	private JButton GraveyardButton2;
	private EndPhaseButton endPhaseButton;
	private EndTurnButton endTurnButton;
	private SideView sv;
	private JLabel attackAndDefence ;	
	public JLabel bg;

	public GUI () throws IOException{
		super("YOGIOH");
		setBounds(0, 0, 1365, 770);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setBackground(Color.BLACK);
		 bg = new JLabel();
		 bg.setBounds(0,0,1365,770);
		// bg.setBackground(Color.BLACK);
		 bg.setIcon((new ImageIcon("E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"+"background.jpg")));
		 setContentPane(bg);
		sv=new SideView();
		sv.setBounds(10, 123, 280, 350);
		app = new activePlayerPanel();
		app.setBounds(300, 379, 800, 246);
		
		opp = new opponentPlayerPanel();
		opp.setBounds(300, 123, 800,246);
		aphp = new activePlayerHandPanel();
		aphp.setBounds(0, 635, 1365, 105);
		//aphp.setBackground(Color.BLACK);
		ophp = new opponentPlayerHandPanel();
		ophp.setBounds(0, 10, 1365, 105);
		//ophp.setBackground(Color.BLACK);
		lifePoints2 = new JLabel("Life Points :");
		lifePoints2.setBounds(1150, 180, 100, 20);
		lifePoints2.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		lifePoints1 = new JLabel("Life Points :");
		lifePoints1.setBounds(1150, 459, 100, 20);
		lifePoints1.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		playerName2 = new JLabel("Player Name :");
		playerName2.setBounds(1150, 135, 150, 40);
		playerName2.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		playerName1 = new JLabel("Player Name :");
		playerName1.setBounds(1150, 410, 150, 40);
		playerName1.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		DeckButton1 = new JButton();
		DeckButton1.setBounds(1250 ,510, 75, 110);
		DeckButton1.setIcon(new ImageIcon("E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"
								+ "Back.png"));
		d1 = new JLabel("3wwww");
		d1.setBounds(1278,490,50,20);
		//DeckButton1.setForeground(Color.white);
		DeckButton2 = new JButton();
		DeckButton2.setBounds(1250 ,254, 75, 110);
		d2 = new JLabel("3wwww");
		d2.setBounds(1278, 235,50,20);
		DeckButton2.setIcon(new ImageIcon("E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"
				+ "Back.png"));
		GraveyardButton1 = new JButton();
		GraveyardButton1.setBounds(1150, 510, 75, 110);
		GraveyardButton1.setIcon(new ImageIcon("E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"
				+ "graveYard.jpg"));
		GraveyardButton2 = new JButton();
		GraveyardButton2.setBounds(1150, 254, 75, 110);
		GraveyardButton2.setIcon(new ImageIcon("E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"
				+ "graveYard.jpg"));
		endPhaseButton =new EndPhaseButton();
		endPhaseButton.setBounds(80, 509, 150, 53);
		endPhaseButton.setOpaque(false);
		endPhaseButton.setContentAreaFilled(false);
		endPhaseButton.setBorderPainted(false);
		endTurnButton =new EndTurnButton();
		endTurnButton.setBounds(70, 572, 182, 53);
		//endTurnButton.setIcon(new ImageIcon("E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"
			//	+ "endTurn.png"));
		endTurnButton.setOpaque(false);
		endTurnButton.setContentAreaFilled(false);
		endTurnButton.setBorderPainted(false);
		attackAndDefence = new JLabel("");
		attackAndDefence.setBounds(66, 475, 280, 30);
		
		getContentPane().add(app);
		getContentPane().add(opp);
		getContentPane().add(aphp);
		getContentPane().add(ophp);
		getContentPane().add(lifePoints1);
		getContentPane().add(lifePoints2);
		getContentPane().add(playerName1);
		getContentPane().add(playerName2);
		getContentPane().add(DeckButton1);
		getContentPane().add(DeckButton2);
		getContentPane().add(GraveyardButton1);
		getContentPane().add(GraveyardButton2);
		getContentPane().add(endPhaseButton);
		getContentPane().add(endTurnButton);
		getContentPane().add(sv);
		getContentPane().add(d1);
		getContentPane().add(d2);	
		getContentPane().add(attackAndDefence);
		

	}
	 

	public JLabel getAttackAndDefence() {
		return attackAndDefence;
	}

	public void setAttackAndDefence(JLabel attackAndDefence) {
		this.attackAndDefence = attackAndDefence;
	}

	public JLabel getD1() {
		return d1;
	}

	public void setD1(JLabel d1) {
		this.d1 = d1;
	}

	public JLabel getD2() {
		return d2;
	}

	public void setD2(JLabel d2) {
		this.d2 = d2;
	}

	public activePlayerHandPanel getAphp() {
		return aphp;
	}

	public void setAphp(activePlayerHandPanel aphp) {
		this.aphp = aphp;
	}

	public opponentPlayerHandPanel getOphp() {
		return ophp;
	}

	public void setOphp(opponentPlayerHandPanel ophp) {
		this.ophp = ophp;
	}

	public JLabel getPlayerName2() {
		return playerName2;
	}

	public void setPlayerName2(JLabel playerName2) {
		this.playerName2 = playerName2;
	}
	
	public activePlayerPanel getApp() {
		return app;
	}

	public void setApp(activePlayerPanel app) {
		this.app = app;
	}

	public opponentPlayerPanel getOpp() {
		return opp;
	}

	public void setOpp(opponentPlayerPanel opp) {
		this.opp = opp;
	}

	public JLabel getLifePoints1() {
		return lifePoints1;
	}

	public void setLifePoints1(JLabel lifePoints1) {
		this.lifePoints1 = lifePoints1;
	}

	public JLabel getLifePoints2() {
		return lifePoints2;
	}

	public void setLifePoints2(JLabel lifePoints2) {
		this.lifePoints2 = lifePoints2;
	}

	public JLabel getPlayerName1() {
		return playerName1;
	}

	public void setPlayerName1(JLabel playerName1) {
		this.playerName1 = playerName1;
	}

	public JButton getDeckButton1() {
		return DeckButton1;
	}

	public void setDeckButton1(JButton deckButton1) {
		DeckButton1 = deckButton1;
	}

	public JButton getGraveyardButton1() {
		return GraveyardButton1;
	}

	public void setGraveyardButton1(JButton graveyardButton1) {
		GraveyardButton1 = graveyardButton1;
	}

	public JButton getDeckButton2() {
		return DeckButton2;
	}

	public void setDeckButton2(JButton deckButton2) {
		DeckButton2 = deckButton2;
	}

	public JButton getGraveyardButton2() {
		return GraveyardButton2;
	}

	public void setGraveyardButton2(JButton graveyardButton2) {
		GraveyardButton2 = graveyardButton2;
	}

	public EndPhaseButton getEndPhaseButton() {
		return endPhaseButton;
	}

	public JLabel getBg() {
		return bg;
	}

	public void setBg(JLabel bg) {
		this.bg = bg;
	}

	public void setEndPhaseButton(EndPhaseButton endPhaseButton) {
		this.endPhaseButton = endPhaseButton;
	}

	public EndTurnButton getEndTurnButton() {
		return endTurnButton;
	}

	public void setEndTurnButton(EndTurnButton endTurnButton) {
		this.endTurnButton = endTurnButton;
	}
	public SideView getSv() {
		return sv;
	}

	public void setSv(SideView sv) {
		this.sv = sv;
	}
}
