package eg.edu.guc.yugioh.listeners;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.gui.GUI;

public class Main {

	public Main(String pn1 , String pn2) throws IOException, UnexpectedFormatException, CloneNotSupportedException {
		
		Board board = new Board() ;
		//String pn1 = JOptionPane.showInputDialog("esm el karem eh ?");
		//String pn2 = JOptionPane.showInputDialog("esm el karem el tany eh ?");
		Player p1 = new Player(pn1);
		Player p2 = new Player(pn2);
		GUI gui = new GUI();
		gui.getPlayerName1().setText(pn1);
		gui.getPlayerName2().setText(pn2);
		gui.getLifePoints1().setText("8000");
		gui.getLifePoints2().setText("8000");
		gui.setVisible(true);
		//gui.getContentPane().setBackground(Color.BLACK);
		Controller c = new Controller(board, gui, p1, p2);
	}
}
