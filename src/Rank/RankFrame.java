package Rank;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

/**
 * The Frame for the rankings. In this frame the players points, and the details of their games are shown.
 *
 */

public class RankFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The data storage.
	 */
	
	static private RankData data;
	private JTable jt;
	
	public RankFrame() throws IOException {
		
		this.setTitle("Ranks");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//Beolvasás		
		data = deserializeFromXML();
		
		
		//minimális méret
		this.setMinimumSize(new Dimension(650, 300));
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().setBackground(Color.BLACK);
				
		this.setLayout(new BorderLayout());
		
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		jp.setBackground(Color.BLACK);
		
		jp.add(Box.createRigidArea(new Dimension(0, 5)));
		
		ImageIcon ranksI = new ImageIcon("Ranks.png");
		JLabel jl = new JLabel(ranksI);
		jl.setAlignmentX(CENTER_ALIGNMENT);
		jp.add(jl, BoxLayout.Y_AXIS);
		
		jp.add(Box.createRigidArea(new Dimension(0, 5)));
		
		JPanel jpt = new JPanel();
		jpt.setBackground(Color.BLACK);
		
		jt = new JTable(data);
		jt.setBackground(Color.BLACK);
		jt.setForeground(Color.WHITE);
		jt.setFillsViewportHeight(rootPaneCheckingEnabled);
		jt.setAlignmentX(CENTER_ALIGNMENT);
		//rendezés
		jt.setRowSorter(new TableRowSorter<RankData>(data));
		
		jpt.add(jt);
		
		this.add(jp, BorderLayout.NORTH);
		this.add(jpt);
		//görgetés
		this.add(new JScrollPane(jt));
		
		JPanel jp4 = new JPanel();
		jp4.setAlignmentX(CENTER_ALIGNMENT);
		jp4.setLayout(new FlowLayout());
		jp4.setBackground(Color.BLACK);
		//vissza gomg
		JButton back = (JButton) jp4.add(new JButton("Back"));
		back.setBackground(Color.LIGHT_GRAY);
		back.setForeground(Color.BLACK);
		back.setFont(new Font("Arial", Font.BOLD, 13));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		this.add(jp4, BorderLayout.SOUTH);
		
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * Loads the players data from the "rdata.xml" file.
	 * @return	The loaded data.
	 * @throws IOException	If the file is not found.
	 */
	
	//Beolvassa a ranks.xml fájl tartalmát
	private static RankData deserializeFromXML() throws IOException {
		
		FileInputStream fis = new FileInputStream("ranks.xml");
		XMLDecoder decoder = new XMLDecoder(fis);
		RankData rdata = (RankData) decoder.readObject();
		decoder.close();
		fis.close();
		return rdata;
	}
	
}