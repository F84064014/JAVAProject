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
	private List<String> tanklist;
	private int curmap;
	private int curtank1;
	private int curtank2;
	
	
	public BtnListener(Board b) {
		this.gameboard = b;
		maplist = new ArrayList<String>();
		maplist.add("default");
		maplist.add("test1");
		maplist.add("test2");
		curmap=0;
		
		tanklist = new ArrayList<String>();
		tanklist.add("tank1");
		tanklist.add("tank2");
		tanklist.add("tank3");
		curtank1 = 0;
		curtank2 = 0;
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
		if(comStr.equals("nextTank1")) {
			curtank1++;
			if(curtank1 >= maplist.size())
				curtank1 = 0;
			this.gameboard.setTankType(tanklist.get(curtank1), "tanker1");
		}
		if(comStr.equals("lastTank1")) {
			curtank1++;
			if(curtank1 >= maplist.size())
				curtank1 = 0;
			this.gameboard.setTankType(tanklist.get(curtank1), "tanker1");
		}
		if(comStr.equals("nextTank2")) {
			curtank2++;
			if(curtank2 >= maplist.size())
				curtank2 = 0;
			this.gameboard.setTankType(tanklist.get(curtank2), "tanker2");
		}
		if(comStr.equals("lastTank2")) {
			curtank2++;
			if(curtank2 >= maplist.size())
				curtank2 = 0;
			this.gameboard.setTankType(tanklist.get(curtank2),"tanker2");
		}
	}
}

