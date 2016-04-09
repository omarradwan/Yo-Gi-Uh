package eg.edu.guc.yugioh.gui;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.layout.Background;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class activePlayerPanel extends JPanel {
	
	
	public activePlayerMonsterArea getApma() {
		return apma;
	}


	public void setApma(activePlayerMonsterArea apma) {
		this.apma = apma;
	}


	public activePlayerSpellArea getApsa() {
		return apsa;
	}


	public void setApsa(activePlayerSpellArea apsa) {
		this.apsa = apsa;
	}


	private activePlayerMonsterArea apma ;
	private activePlayerSpellArea apsa ;
	public activePlayerPanel() throws IOException {
		super();
		apma = new activePlayerMonsterArea();
		apsa = new activePlayerSpellArea();
		setLayout(new GridLayout(2,1,0,3));
		add(apma);
		add(apsa);
		 
			
	
	}
	

}
