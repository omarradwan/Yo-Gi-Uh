package eg.edu.guc.yugioh.gui;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SpellButton extends JButton{

	public SpellButton(String s) {
		super();
		setPreferredSize(new Dimension(75,110));
		setIcon(new ImageIcon("E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"+s+".png"));
	}
	}

