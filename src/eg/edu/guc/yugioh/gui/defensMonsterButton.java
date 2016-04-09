package eg.edu.guc.yugioh.gui;

import java.awt.Dimension;

public class defensMonsterButton extends MonsterButton {

	public defensMonsterButton(String s) {
		super(s);
		setPreferredSize(new Dimension(110,75));
		setToolTipText("Click to switch it to Attack mode");
}

}
