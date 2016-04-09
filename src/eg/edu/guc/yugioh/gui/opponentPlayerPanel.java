package eg.edu.guc.yugioh.gui;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class opponentPlayerPanel extends JPanel{
	private opponentPlayerMonsterArea opma ;
	private opponentPlayerSpellArea opsa ;

	public opponentPlayerPanel() throws IOException {
		super();
		opma = new opponentPlayerMonsterArea();
		opsa = new opponentPlayerSpellArea();
		setLayout(new GridLayout(2,1,0,3));
		add(opsa);
		add(opma);
	}
	
	public opponentPlayerMonsterArea getOpma() {
		return opma;
	}

	public void setOpma(opponentPlayerMonsterArea opma) {
		this.opma = opma;
	}

	public opponentPlayerSpellArea getOpsa() {
		return opsa;
	}

	public void setOpsa(opponentPlayerSpellArea opsa) {
		this.opsa = opsa;
	}


}
