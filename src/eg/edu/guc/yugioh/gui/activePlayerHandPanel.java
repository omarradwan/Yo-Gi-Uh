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
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

public class activePlayerHandPanel extends JPanel{
	ArrayList<HandButton> hand;
	private BufferedImage image;
	public activePlayerHandPanel() {
		super();
		setLayout(new FlowLayout(FlowLayout.CENTER,-10,0));
		hand = new ArrayList<HandButton>();
		try{
			image = ImageIO.read(new File("E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/aphp.jpg"));
		}catch(IOException e){
			System.out.println("3w");
		}	
	}

	public ArrayList<HandButton> getHand() {
		return hand;
	}


	public void setHand(ArrayList<HandButton> hand) {
		this.hand = hand;
	}
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }
}
