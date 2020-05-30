package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;

public class BtnListener implements ActionListener{
	
	private Board gameboard;
	private List<String> maplist;
	private int curmap;
	
	
	public BtnListener(Board b) {
		this.gameboard = b;
		maplist = new ArrayList<String>();
		maplist.add("default");
		maplist.add("test1");
		maplist.add("test2");
		curmap=0;
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
		if(comStr.equals("next")) {
			curmap++;
			if(curmap >= maplist.size())
				curmap = 0;
			this.gameboard.setGameMap(maplist.get(curmap));
		}
		
		if(comStr.equals("last")) {
			curmap--;
			if(curmap < 0)
				curmap = maplist.size()-1;
			this.gameboard.setGameMap(maplist.get(curmap));
		}
	}
}

