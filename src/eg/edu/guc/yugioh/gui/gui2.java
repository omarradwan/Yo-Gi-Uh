package eg.edu.guc.yugioh.gui;

import java.awt.Color;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.listeners.Main;

public class gui2 extends JFrame implements ActionListener{
JLabel bg2 ;

JButton Start;
String p1Name;
String p2Name ;
JTextField p1 ;
JTextField p2 ;
	public gui2() {
		super("3w");
		setBounds(0,0,1365,770);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		bg2 = new JLabel();
		bg2.setBounds(0,0,1365,770);
		bg2.setIcon((new ImageIcon("E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"+"Background.jpg")));
		setContentPane(bg2);
		p1 = new JTextField();
		p2 = new JTextField();
		p1.setBounds(600, 150, 200, 30);
		p2.setBounds(600, 230, 200, 30);
		Start = new JButton();
		Start.setBounds(560, 320, 280, 280);
		Start.setIcon(new ImageIcon("E:/Semester 4/CSEN401/Yu-Gi-Oh/src/eg/edu/guc/yugioh/gui/"+"Start.png"));
		Start.setOpaque(false);
		Start.setContentAreaFilled(false);
		Start.setBorderPainted(false);
		p1Name = p1.getText();
		p2Name = p2.getText();
		System.out.println(p1Name);
		getContentPane().add(p1);
		getContentPane().add(p2);
		getContentPane().add(Start);
		Start.addActionListener(this);
		this.revalidate();
		this.repaint();
		
	}
public static void main(String[] args) {
	gui2 gui = new gui2();
}
@Override
public void actionPerformed(ActionEvent e) {
	try {
		Main main = new Main(this.getP1().getText(),this.getP2().getText());
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (UnexpectedFormatException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (CloneNotSupportedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	this.setVisible(false);
}

public JLabel getBg2() {
	return bg2;
}
public void setBg2(JLabel bg2) {
	this.bg2 = bg2;
}
public JButton getStart() {
	return Start;
}
public void setStart(JButton start) {
	Start = start;
}
public String getP1Name() {
	return p1Name;
}
public void setP1Name(String p1Name) {
	this.p1Name = p1Name;
}
public String getP2Name() {
	return p2Name;
}
public void setP2Name(String p2Name) {
	this.p2Name = p2Name;
}
public JTextField getP1() {
	return p1;
}
public void setP1(JTextField p1) {
	this.p1 = p1;
}
public JTextField getP2() {
	return p2;
}
public void setP2(JTextField p2) {
	this.p2 = p2;
}
}
