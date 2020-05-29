package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;

public class BtnListener implements ActionListener{
	
	private Board gameboard;
	
	
	public BtnListener(Board b) {
		this.gameboard = b;
	}
	
	public void actionPerformed(ActionEvent e) {
		String comStr = e.getActionCommand();
		if((AbstractButton)e.getSource() == gameboard.getMenuBtnList().get(0)) {
			for(JButton obj: gameboard.getMenuBtnList()) {
				((AbstractButton) obj).setVisible(false);
				this.gameboard.setGameStatus(Board.NEXT);
			}
		}
		if(comStr.equals("return to Menu")) {
			for(JButton obj: gameboard.getMenuBtnList()) {
				((AbstractButton) obj).setVisible(true);
			}
			for(JButton obj: gameboard.getReturnBtnList()) {
				((AbstractButton) obj).setVisible(false);
			}
			this.gameboard.setGameStatus(Board.MENU);
		}
		if(comStr.equals("restart")) {
			for(JButton obj: gameboard.getMenuBtnList()) {
				((AbstractButton) obj).setVisible(false);
			}
			for(JButton obj: gameboard.getReturnBtnList()) {
				((AbstractButton) obj).setVisible(false);
			}
			this.gameboard.setGameStatus(Board.RESTART);
		}
	}
}

