package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Rank.RankFrame;

/**
 * 
 * The main menu of the game. The game can be started, or the settings window, and other windows can be reached from here.
 *
 */

public class Menu extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	
	//Az elsõ sé második játékos neve
	public static String Pname1 = "", Pname2 = "";
	
	//A játékosok nevét ellenõrzi, hogy megadták-e már õket
	static boolean FNS = false, SNS = false, pvp = false;
	
	//A játék sebessége
	private static int delay = 120;
	
	//Az ételek alap száma
	private static int foodNumber = 3, pvpfoodNumber = 6;
		
	//A menü ablaka
	public Menu() {
		
		ImageIcon titleImage = new ImageIcon("SnakeMultiplayer.png");
		
		//Alapok az ablakhoz
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Menu");
		this.getContentPane().setBackground(Color.BLACK);
		
		//Cím
		JLabel jl = new JLabel(titleImage);
		jl.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(jl);
		
		//Alak
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		
		//Gombok
		
			//Játék
			JButton jbp = new JButton("Play");			
			jbp.setAlignmentX(Component.CENTER_ALIGNMENT);
			jbp.setBackground(Color.lightGray);
			jbp.setForeground(Color.BLACK);
			jbp.setPreferredSize(new Dimension(20, 20));
			jbp.setFont(new Font("Arial", Font.PLAIN, 40));
			jbp.setPreferredSize(new Dimension(50, 50));
			jbp.addActionListener(new PBL());
			this.add(jbp);
			
			this.add(Box.createRigidArea(new Dimension(0, 20)));
			
			//Rangsor
			JButton jbr = new JButton("Ranks");
			jbr.setAlignmentX(Component.CENTER_ALIGNMENT);
			jbr.setBackground(Color.lightGray);
			jbr.setForeground(Color.BLACK);
			jbr.setPreferredSize(new Dimension(20, 20));
			jbr.setFont(new Font("Arial", Font.PLAIN, 40));
			jbr.setPreferredSize(new Dimension(50, 50));
			jbr.addActionListener(new RBL());
			this.add(jbr);
			
			this.add(Box.createRigidArea(new Dimension(0, 20)));
			
			//Irányítás
			JButton jbhtp = new JButton("How to Play");
			jbhtp.setAlignmentX(Component.CENTER_ALIGNMENT);
			jbhtp.setBackground(Color.lightGray);
			jbhtp.setForeground(Color.BLACK);
			jbhtp.setFont(new Font("Arial", Font.PLAIN, 40));
			jbhtp.setPreferredSize(new Dimension(50, 50));
			jbhtp.addActionListener(new HTPBL());
			this.add(jbhtp);
			
			this.add(Box.createRigidArea(new Dimension(0, 20)));
			
			//Beállítások
			JButton jbs = new JButton("Settings");
			jbs.setAlignmentX(Component.CENTER_ALIGNMENT);
			jbs.setBackground(Color.lightGray);
			jbs.setForeground(Color.BLACK);
			jbs.setFont(new Font("Arial", Font.PLAIN, 40));
			jbs.setPreferredSize(new Dimension(50, 50));
			jbs.addActionListener(new SBL());
			this.add(jbs);
			
			this.add(Box.createRigidArea(new Dimension(0, 20)));
			
			//Kilépés
			JButton jbe = new JButton("Exit");
			jbe.setAlignmentX(Component.CENTER_ALIGNMENT);
			jbe.setBackground(Color.lightGray);
			jbe.setForeground(Color.BLACK);
			jbe.setFont(new Font("Arial", Font.PLAIN, 40));
			jbe.setPreferredSize(new Dimension(50, 50));
			jbe.addActionListener(new EBL());
			this.add(jbe);
			
		//Az ablakkal kapcsolatos dolgok
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setResizable(false);
		
	}
	
	/**
	 * The windows, where the players add their names.
	 */
	
	//A játékosok nevét ebben az ablakban kell megadni
	public static void addNames() {
		//Az ablak
		final JFrame frame = new JFrame("Player names");
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Panelek
		ImageIcon rI = new ImageIcon("Names.png");
		JLabel jip = new JLabel(rI);
		frame.add(jip, BorderLayout.NORTH);
		
		//Elsõ játékos sora
		JPanel jp1 = new JPanel(new FlowLayout());
		jp1.setBackground(Color.BLACK);
		
		JLabel jlp1 = new JLabel("Blue snake players name:");
		jlp1.setForeground(Color.WHITE);
		jp1.add(jlp1);
		
		JPanel jpc = new JPanel();
		jpc.setLayout(new BorderLayout());
		
		final JTextField jtf1 = (JTextField) jp1.add(new JTextField(15));
				
		JButton S1Button = (JButton) jp1.add(new JButton("Set"));
		S1Button.setBackground(Color.LIGHT_GRAY);
		S1Button.setForeground(Color.BLACK);
		S1Button.setFont(new Font("Arial", Font.BOLD, 13));
        S1Button.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent arg0) {
			Pname1 = jtf1.getText();
			FNS = true;
			if(SNS == true) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				game();
			}
	
		} }); 
        
        jpc.add(jp1, BorderLayout.NORTH);
        
        //Második játékos sora
        JPanel jp2 = new JPanel(new FlowLayout());
        jp2.setBackground(Color.BLACK);
        JLabel jlp2 = new JLabel("Red snakes players name:");
		jlp2.setForeground(Color.WHITE);
		jp2.add(jlp2);
		final JTextField jtf2 = (JTextField) jp2.add(new JTextField(15));
		frame.add(jp2, BorderLayout.SOUTH);
	
		JButton S2Button = (JButton) jp2.add(new JButton("Set"));
		S2Button.setBackground(Color.LIGHT_GRAY);
		S2Button.setForeground(Color.BLACK);
		S2Button.setFont(new Font("Arial", Font.BOLD, 13));
        S2Button.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent arg0) {
			Pname2 = jtf2.getText();
			SNS = true;
			if(FNS == true) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				game();
			}
		} }); 
        
        jpc.add(jp2, BorderLayout.SOUTH);
        
        frame.add(jpc, BorderLayout.CENTER);
        
        JPanel jp4 = new JPanel();
		jp4.setAlignmentX(CENTER_ALIGNMENT);
		jp4.setLayout(new FlowLayout());
		jp4.setBackground(Color.BLACK);
		
		//Vissza gomg
		JButton back = (JButton) jp4.add(new JButton("Back"));
		back.setBackground(Color.LIGHT_GRAY);
		back.setForeground(Color.BLACK);
		back.setFont(new Font("Arial", Font.BOLD, 13));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		
		frame.add(jp4, BorderLayout.SOUTH);
        
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setFocusable(false);
		
	}
	
	/**
	 * The game is played in this window.
	 */
	
	//A játék ablaka attól függõen, milyen mód lett kiválasztva
	public static void game() {
			
		if(pvp == false) {
			
			JFrame frame = new JFrame("Snake Game");
			frame.setBackground(Color.BLACK);
			frame.setBounds(0, 0, 990, 1000);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
			normalSnake nS = new normalSnake(Pname1, Pname2, delay, foodNumber);
			frame.add(nS);
			
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			
		}else {
			
			JFrame frame = new JFrame("PVPSnake");
			frame.setBackground(Color.BLACK);
			frame.setBounds(0, 0, 990, 1000);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
			pvpSnake pvpS = new pvpSnake(Pname1, Pname2, delay, pvpfoodNumber);
			frame.add(pvpS);
			
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
		}
		
	}
	
	/**
	 * Shows the details and points of the previous games.
	 * @throws IOException	If the file is not there throws IOException.
	 * 
	 */
	
	//Rangok ablak
	public static void Ranks() throws IOException  {
		
		@SuppressWarnings("unused")
		RankFrame rf = new RankFrame();
				
	}
	
	/**
	 * In this window the use instructions are shown.
	 */
	
	//Beállítások ablak
	public static void HowToPlay() {
		
		//az ablak
		final JFrame htp = new JFrame("How to Play");
		htp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		htp.setMinimumSize(new Dimension(600, 300));
		htp.getContentPane().setBackground(Color.BLACK);
		htp.setLayout(new BorderLayout());
		
		//az ablak fõ panele
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		jp.setBackground(Color.BLACK);
		
		//helyköz
		jp.add(Box.createRigidArea(new Dimension(0, 5)));
		
		//felsõ kép
		ImageIcon htpI = new ImageIcon("HowToPlay.png");
		JLabel ijl = new JLabel(htpI);
		ijl.setAlignmentX(CENTER_ALIGNMENT);
		jp.add(ijl, BoxLayout.Y_AXIS);
		
		//helyköz
		jp.add(Box.createRigidArea(new Dimension(0, 5)));
		
		//a középsõ panel
		JPanel jpm = new JPanel();
		jpm.setLayout(new BorderLayout());
		jpm.setBackground(Color.BLACK);
		
				//bal oldali panel a nyílas utasítások számára
				JPanel jpl = new JPanel();
				jpl.setLayout(new BorderLayout());
				jpl.setBackground(Color.BLACK);
				
				//a kék kígyót irányít utasításai
				JLabel apjl = new JLabel();
				apjl.setText("The Blue snake can be moved with the ARROW keys.");
				apjl.setForeground(Color.BLUE);
				apjl.setAlignmentX(LEFT_ALIGNMENT);
				
				jpl.add(apjl, BorderLayout.PAGE_START);
				
				//az utasításokhoz szolgáló kép
				ImageIcon arrowK = new ImageIcon("ArrowKeys.png");
				JLabel ajl = new JLabel(arrowK);
				ajl.setAlignmentX(LEFT_ALIGNMENT);
				
				jpl.add(ajl, BorderLayout.PAGE_END);
				
				jpm.add(jpl, BorderLayout.WEST);
				
				
		
		
				//jobb oldali panel a nyílas utasítások számára
				JPanel jpr = new JPanel();
				jpr.setLayout(new BorderLayout());
				jpr.setBackground(Color.BLACK);
				
				//a kék kígyót irányít utasításai
				JLabel apjr = new JLabel();
				apjr.setText("The Red snake can be moved with the WASD keys.");
				apjr.setForeground(Color.RED);
				apjr.setAlignmentX(RIGHT_ALIGNMENT);
				
				jpr.add(apjr, BorderLayout.PAGE_END);
		
				//az utasításokhoz szolgáló kép
				ImageIcon wsadK = new ImageIcon("WSAD.png");
				JLabel wjl = new JLabel(wsadK);
				wjl.setAlignmentX(RIGHT_ALIGNMENT);
				
				jpr.add(wjl, BorderLayout.PAGE_START);
				
				jpm.add(jpr, BorderLayout.EAST);
				
				
		
		jpm.setAlignmentX(CENTER_ALIGNMENT);
		jp.add(jpm, BoxLayout.LINE_AXIS);
		
		JPanel jp4 = new JPanel();
		jp4.setAlignmentX(CENTER_ALIGNMENT);
		jp4.setLayout(new FlowLayout());
		jp4.setBackground(Color.BLACK);
		
		//Vissza gomg
		JButton back = (JButton) jp4.add(new JButton("Back"));
		back.setBackground(Color.LIGHT_GRAY);
		back.setForeground(Color.BLACK);
		back.setFont(new Font("Arial", Font.BOLD, 13));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				htp.dispose();
			}
		});
		
		htp.add(jp, BorderLayout.WEST);
		
		htp.add(jp4, BorderLayout.SOUTH);
		
		htp.setVisible(true);
		htp.setLocationRelativeTo(null);
		htp.setResizable(false);
		
	}
	
	/**
	 * Settings windows. The starting numbers, and the game mode can be changed here.
	 */
	
	//A beállítások ablaka
	public static void Settings() {
		
		final JFrame frame = new JFrame("Settings");
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		ImageIcon si = new ImageIcon("Settings.png");
		JLabel jli = new JLabel(si);
		jli.setAlignmentX(CENTER_ALIGNMENT);
		frame.add(jli);
		
			//a játék sebessége
			JPanel jp1 = new JPanel();
			jp1.setAlignmentX(CENTER_ALIGNMENT);
			jp1.setLayout(new FlowLayout());
			jp1.setBackground(Color.BLACK);
			
			JLabel sl = new JLabel();
			sl.setForeground(Color.WHITE);
			sl.setText("The games speed:");
			
			jp1.add(sl);
			
			final JTextField jtfs = (JTextField) jp1.add(new JTextField(4));
			jtfs.setText(Integer.toString(delay));
					
			JButton S1Button = (JButton) jp1.add(new JButton("Set"));
			S1Button.setBackground(Color.LIGHT_GRAY);
			S1Button.setForeground(Color.BLACK);
			S1Button.setFont(new Font("Arial", Font.BOLD, 13));
	        S1Button.addActionListener(new ActionListener() { 
	        	public void actionPerformed(ActionEvent arg0) {
				delay = Integer.valueOf(jtfs.getText());
				}
				
			} ); 
	        
	     frame.add(jp1);
	     
	        //a játékban szereplõ ételek száma módtól függõen
	     	JPanel jp2 = new JPanel();
	     	jp2.setAlignmentX(CENTER_ALIGNMENT);
	        jp2.setLayout(new FlowLayout());
	        jp2.setBackground(Color.BLACK);
	        
	        JLabel fl = new JLabel();
	        fl.setForeground(Color.WHITE);
			fl.setText("The number of food in the game:");
			
			jp2.add(fl);
	        
	        final JTextField jtff = new JTextField(4);
	        jtff.setText(Integer.toString(foodNumber));
			
			jp2.add(jtff);
	        
	        final JButton S2Button = new JButton("Set");
	        S2Button.setBackground(Color.LIGHT_GRAY);
			S2Button.setForeground(Color.BLACK);
			S2Button.setFont(new Font("Arial", Font.BOLD, 13));
			S2Button.addActionListener(new ActionListener() { 
	        	public void actionPerformed(ActionEvent arg0) {
				foodNumber = Integer.valueOf(jtff.getText());
				}
				
			} );
			
			jp2.add(S2Button);
			
			final JButton S3Button = new JButton("Set");
			S3Button.setBackground(Color.LIGHT_GRAY);
			S3Button.setForeground(Color.BLACK);
			S3Button.setFont(new Font("Arial", Font.BOLD, 13));
			S3Button.addActionListener(new ActionListener() { 
	        	public void actionPerformed(ActionEvent arg0) {
				pvpfoodNumber = Integer.valueOf(jtff.getText());
				}
			});
			
			S3Button.setVisible(false);
			jp2.add(S3Button);
					
	     	     	 
     	 frame.add(jp2);
	    
     	 //a játék módjának kiválasztása
		JPanel jp3 = new JPanel();
		jp3.setAlignmentX(CENTER_ALIGNMENT);
		jp3.setLayout(new FlowLayout());
		jp3.setBackground(Color.BLACK);
		
		final JButton normal = (JButton) jp3.add(new JButton("Normal"));
		if(pvp == false) {
			normal.setBackground(Color.GREEN);
		}else {
			normal.setBackground(Color.LIGHT_GRAY);
		}
		normal.setForeground(Color.BLACK);
		normal.setFont(new Font("Arial", Font.BOLD, 13));
		
		final JButton epic = (JButton) jp3.add(new JButton("EPIC"));
		if(pvp == true) {
			epic.setBackground(Color.GREEN);
		}else {
			epic.setBackground(Color.LIGHT_GRAY);
		}
		epic.setForeground(Color.BLACK);
		epic.setFont(new Font("Arial", Font.BOLD, 13));
		
		
		epic.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent arg0) {
			pvp = true;
			epic.setBackground(Color.GREEN);
			normal.setBackground(Color.LIGHT_GRAY);
		
			jtff.setText(Integer.toString(pvpfoodNumber));
			
			S2Button.setVisible(false);
			S3Button.setVisible(true);
			}
			
		} );
		
		normal.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent arg0) {
			pvp = false;
			epic.setBackground(Color.LIGHT_GRAY);
			normal.setBackground(Color.GREEN);
		
			jtff.setText(Integer.toString(foodNumber));
			
			S3Button.setVisible(false);
			S2Button.setVisible(true);
			
			}
			
		} );
		
		frame.add(jp3);
		
		JPanel jp4 = new JPanel();
		jp4.setAlignmentX(CENTER_ALIGNMENT);
		jp4.setLayout(new FlowLayout());
		jp4.setBackground(Color.BLACK);
		
		JButton back = (JButton) jp4.add(new JButton("Back"));
		back.setBackground(Color.LIGHT_GRAY);
		back.setForeground(Color.BLACK);
		back.setFont(new Font("Arial", Font.BOLD, 13));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		
		frame.add(jp4);
				
		
		frame.setVisible(true);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		
	}
	
	/**
	 * The ActionListener for the Play button.
	 */
	
	//A menü gombok lenyomásait figyelik
	private class PBL implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			addNames();
			FNS = false;
			SNS = false;
			
		}
	}
	
	/**
	 * The ActionListener for the Ranks button.
	 */
	
	private class RBL implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			try {
				Ranks();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * The ActionListener for the How to Play button.
	 */
	
	private class HTPBL implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			HowToPlay();
		}
	}
	
	/**
	 * The ActionListener for the Settings button.
	 */
	
	private class SBL implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			
			Settings();
			
		}
	}
	
	/**
	 * The ActionListener for the Exit button.
	 */
	
	private class EBL implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			Menu.this.dispose();			
		}
		
	}
	
//	private class PBL implements ActionListener{
//
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			game();
//		}
//	}
	
}