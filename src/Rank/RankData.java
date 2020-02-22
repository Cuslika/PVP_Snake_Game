package Rank;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * The Ranks are stored in this class.
 *
 */

public class RankData extends AbstractTableModel {
	

	private static final long serialVersionUID = 1L;
	
	
	private List<Rank> ranks = new ArrayList<Rank>();

	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		return getRanks().size();
	}

	public Object getValueAt(int arg0, int arg1) {
		Rank rank = getRanks().get(arg0);
		switch(arg1) {
		case 0: return rank.getName();
		case 1: return rank.getType();
		case 2: return rank.getPts();
		default: return rank.getTries();
		}
	}
	
	public String getColumnName(int i) {
		
		switch(i) {
		case 0: return "Name";
		case 1: return "Game type";
		case 2: return "Points";
		default: return "Round";
		
		}
	}
	
	public Class<?> getColumnClass(int i) {
		//Rank r = ranks.get(0);
		
		switch(i) {
		case 0: return String.class;
		case 1: return String.class;
		case 2: return int.class;
		default: return int.class;
		}
	}
	
	public void addRank(String name, String type,  int pts, int trs) {
		getRanks().add(new Rank(name, type, pts, trs));
		this.fireTableDataChanged();
	}

	public List<Rank> getRanks() {
		return ranks;
	}

	public void setRanks(List<Rank> ranks) {
		this.ranks = ranks;
	}

}