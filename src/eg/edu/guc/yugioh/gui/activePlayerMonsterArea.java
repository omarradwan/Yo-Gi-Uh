package eg.edu.guc.yugioh.gui;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class activePlayerMonsterArea extends JPanel{
	private ArrayList<MonsterButton> monsters;
	private BufferedImage image;
	public activePlayerMonsterArea() {
		super();
		monsters = new ArrayList<MonsterButton>();
		setSize(800,123);
		setLayout(new FlowLayout(FlowLayout.LEFT , 70 , 10));
		try{
			image = ImageIO.read(new File("E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/apma.png"));
		}catch(IOException e){
			System.out.println("3w");
		}		
	}
	public ArrayList<MonsterButton> getMonsters() {
		return monsters;
	}
	public void setMonsters(ArrayList<MonsterButton> monsters) {
		this.monsters = monsters;
	}

	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }
}
