package Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import Rank.RankData;


/**
 * 
 * The game is rendered and played in this panel. 
 *
 */

public class normalSnake extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The snakes, the game will be played.
	 */
	
	private Snake Bsnake, Rsnake;
	
	/**
	 * The number of the foods in the game.
	 */
	
	private static int foodNumber;
	
	/**
	 * The storing list of the foods in use.
	 */
	
	private ArrayList<Food> fl;
	
	/**
	 * Fires ActionEvents.
	 */
	
	private Timer timer;
	
	/**
	 * The interval between the timers ActionEvent fires.
	 */
	
	private int delay;
		
	/**
	 * The Red players points.
	 */
	
	private static int Rpoints = 0;
	
	/**
	 * The Blue players points.
	 */
	
	private static int Bpoints = 0;
	
	/**
	 * The Red players round.
	 */
	
	private static int Rround = 1;
	
	/**
	 * The Blue players round.
	 */
	
	private static int Bround = 1;
	
	
	private JTextField rpt, bpt;
	
	/**
	 * The name of the Blue Snakes player.
	 */
	
	static String pNameB;
	
	/**
	 * The name of the Red Snakes player.
	 */
	
	static String pNameR;
	
	/**
	 * The mode of the game.
	 */
	
	static String type = "Normal";
	
	static RankData rdata;
	
	boolean gameOver = false;
	
	JPanel goP;

	/**
	 * Constructs the game Panel.
	 * @param p1	The name of the Blue Snakes player.
	 * @param p2	The name of the Red Snakes player.
	 * @param d		The delay between the ticks. The 
	 * @param fn	The number of the food once on the Panel during gameplay.
	 */
	
	public normalSnake(String p1, String p2, int d, int fn) {
		
		pNameB = p1;
		pNameR = p2;
		delay = d;
		foodNumber = fn;
		
		this.set();
				
		timer = new Timer(delay, new TimerListener());
		timer.start();
		
		//A játék panel formázása
		this.setLayout(new BorderLayout());
		//A panel felsõ részén elhelyezkedõ nevek elhelyezkedése
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());
		top.setBackground(Color.BLACK);
		
		ImageIcon titleImage = new ImageIcon("SnakeMultiplayer.png");
		JLabel ti = new JLabel(titleImage);
		top.add(ti, BorderLayout.PAGE_START);
		
		//A kék játékos pontjai
		JPanel bp = new JPanel(new FlowLayout());
		bp.setBackground(Color.BLACK);
		JLabel bpl = new JLabel(pNameB + " players points:");
		bpl.setForeground(Color.BLUE);
		bp.add(bpl);
		
		bpt = new JTextField(6);
		bpt.setEditable(false);
		bp.add(bpt);
		
		top.add(bp, BorderLayout.LINE_START);
		
		//A piros játékos pontjai
		JPanel rp = new JPanel(new FlowLayout());
		rp.setBackground(Color.BLACK);
		JLabel rpl = new JLabel(pNameR + " players points:");
		rpl.setForeground(Color.RED);
		rp.add(rpl);
		
		rpt = new JTextField(6);
		rpt.setEditable(false);
		rp.add(rpt);
		
		top.add(rp, BorderLayout.LINE_END);
		
		this.addKeyListener(new KeyboardHandler());
		this.setFocusable(true);
		
		//játék vége panel
		goP = new JPanel();
		goP.setLayout(new BoxLayout(goP, BoxLayout.Y_AXIS));
		goP.setBackground(Color.BLACK);
		
		ImageIcon GO = new ImageIcon("GameOver.png");
		JLabel gol = new JLabel(GO);
		gol.setAlignmentX(CENTER_ALIGNMENT);
		
		goP.add(gol);
		
		goP.add(Box.createRigidArea(new Dimension(0, 5)));
		
		JLabel retry = new JLabel();
		retry.setText("If you want to restart press the Retry button.");
		retry.setForeground(Color.WHITE);
		retry.setAlignmentX(CENTER_ALIGNMENT);
		
		goP.add(retry);
		
		goP.add(Box.createRigidArea(new Dimension(0, 5)));
		
		JButton rb = (JButton) goP.add(new JButton("Retry"));
		rb.setAlignmentX(CENTER_ALIGNMENT);
		rb.setBackground(Color.LIGHT_GRAY);
		rb.setForeground(Color.BLACK);
		rb.setFont(new Font("Arial", Font.BOLD, 13));
		rb.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent arg0) {
			
        		gameOver = false;
        		Rround++;
        		Bround++;
        		set();
        		repaint();
        		timer.restart();
        		
		} });
		
		JLabel exit = new JLabel();
		exit.setText("If you want to exit press the Exit button.");
		exit.setForeground(Color.WHITE);
		exit.setAlignmentX(CENTER_ALIGNMENT);
		
		goP.add(Box.createRigidArea(new Dimension(0, 5)));
		
		goP.add(exit);
		
		JButton eb = (JButton) goP.add(new JButton("Exit"));
		eb.setAlignmentX(CENTER_ALIGNMENT);
		eb.setBackground(Color.LIGHT_GRAY);
		eb.setForeground(Color.BLACK);
		eb.setFont(new Font("Arial", Font.BOLD, 13));
		eb.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent arg0) {
        		
        		JFrame mf = (JFrame) SwingUtilities.getWindowAncestor(normalSnake.this);  
        		mf.dispose();
        		
		} });
		
		goP.setVisible(false);
		
		this.add(goP, BorderLayout.CENTER);
		
		this.add(top, BorderLayout.PAGE_START);		
		
	}
	
	/**
	 * Setting up the Snakes and the Food storing lists.
	 */
	
	public void set() {
		
		Rpoints = 0;
		Bpoints = 0;
		
		Bsnake = new Snake("b");
		Rsnake = new Snake("r");
		
		//a ételbogyók létrehozása
		fl = new ArrayList<Food>();
		for(int i = 0; i < foodNumber; i++) {
			Food nf = new Food();
			if(i > 0) {
				do {
					nf = new Food();
				}while(isDifferent(fl, nf) != true);
			}
			fl.add(nf);
		}
		
	}
	
	/**
	 * Checks, if the f Food is different, than the others in the fl List, and also checks if the f Food is not on top of any of the snakes.
	 * @param fl	The List.
	 * @param f		The food.
	 * @return		True if it is different, False if it is not different. Same returns with the snakes.
	 */
	
	//Ellenõrzi, hogy az "f" étel különbözik e az "fl" ételek lista többie lemétõl
	public boolean isDifferent(ArrayList<Food> fl, Food f) {
		
		//ellenõrzés, hogy új étel generálódik
		for(int i = 0; i < fl.size(); i++) {
			if(fl.get(i).coord.getX() == f.coord.getX() && fl.get(i).coord.getY() == f.coord.getY() )
				return false;
		}
		//ellenõrzés, hogy nem a kék kígyó testére kerül az új étel
		for(int i = 0; i < Bsnake.getLength(); i++) {
			for(int j = 0; j < fl.size(); j++) {
				if(Bsnake.getCoor(i).getX() == fl.get(j).coord.getX() && Bsnake.getCoor(i).getY() == fl.get(j).coord.getY()) {
					return false;
				}
			}
		}
		//ellenõrzés, hogy nem a piros kígyó testére kerül az új étel
		for(int i = 0; i < Rsnake.getLength(); i++) {
			for(int j = 0; j < fl.size(); j++) {
				if(Rsnake.getCoor(i).getX() == fl.get(j).coord.getX() && Rsnake.getCoor(i).getY() == fl.get(j).coord.getY()) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Adds f Food to the given index in the fl List.
	 * @param fl	The List.
	 * @param k		The given index.
	 * @return		The List.
	 */
	
	//Az "fl" ételek listájához ad "k" darab új ételt
	public ArrayList<Food> addFood(ArrayList<Food> fl, int k) {
		
		Food f = new Food();
		f.nFood();
		do {
			f.nFood();
		}while(isDifferent(fl, f) == true);
		fl.set(k, f);
		return fl;
			
	}
	
	/**
	 * Checks if the game needs to end.
	 * @return	True if it needs to end, False, if it does not.
	 */
	
	//Ellenõrzi, hogy vége van-e a játéknak
	public boolean gameOver() {
			
			for(int i = 0; i < Bsnake.getLength(); i++) {
				for(int j = 0; j < Rsnake.getLength(); j++) {
					if(Bsnake.getCoor(i).getX() == Rsnake.getCoor(j).getX() && Bsnake.getCoor(i).getY() == Rsnake.getCoor(j).getY()) {
						return true;
					}
				}
			}
			if(Bsnake.getLength() >= 4) {
				if(Bsnake.hitItself() == true)
					return true;
			}
			if(Rsnake.getLength() >= 4) {
				if(Rsnake.hitItself() == true)
					return true;
			}
			
			return false;
		}
	
	/**
	 * Paints the panel.
	 */
	
	//A játékban lévõ objektumok "festése"
	public void paintComponent(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 1, 990, 990);
		
		g.setColor(Color.WHITE);
		//x = 30, y=150 -tõl x = 960, y = 960-ig megrajzolja a pálya keretét
		g.drawRect(30, 150, 930, 810);
		
		//kígyók kirajzolása
		Bsnake.paintComponent(g);
		Rsnake.paintComponent(g);
		
		//étel kirajzolása
		for(int i = 0; i < fl.size(); i++) {
			fl.get(i).paintComponent(g);
		}
	}		
	
	/**
	 * 
	 * The Listener for the Timer.
	 *
	 */
	
	public class TimerListener implements ActionListener {
	
		//A timer tickjeire figyel
		public void actionPerformed(ActionEvent arg0) {
			
			//Ha játék vége van
			if(gameOver() == true) {
				timer.stop();
				
				try {
					serializeToXML();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			gameOver = true;
								
			goP.setVisible(true);
		
			}else {
				
				goP.setVisible(false);
				
				//A kígyók mozgatása
				Bsnake.Move();
				Rsnake.Move();
				
				//A játékosok pontjainak frissítése
				rpt.setText(Integer.toString(Rpoints));
				bpt.setText(Integer.toString(Bpoints));
				
				//Az étel elfogyasztása
				for(int i = 0; i < fl.size(); i++) {
					
					if(Bsnake.getHeadX() == fl.get(i).coord.getX() && Bsnake.getHeadY() == fl.get(i).coord.getY()) {
						Bpoints++;
						fl = addFood(fl, i);
						Bsnake.addLength();
					}
					if(Rsnake.getHeadX() == fl.get(i).coord.getX() && Rsnake.getHeadY() == fl.get(i).coord.getY()) {
						Rpoints++;
						fl = addFood(fl, i);
						Rsnake.addLength();
					}
				
				}
				
				//Az ablak õjrafestése
				repaint();
			}
		}
	}
	
	/**
	 * Loads the players data to the rdata.xml file.
	 * @throws IOException	If the file is not found.
	 */
	
	//Elmenti a játékosok adatait a ranks.xml fájlba
	private static void serializeToXML () throws IOException {
				
		rdata = new RankData();
		
		if(isThere() == true) {
			rdata = deserializeFromXML();
		} 
		
		FileOutputStream fos = new FileOutputStream("ranks.xml");
		XMLEncoder encoder = new XMLEncoder(fos);
		encoder.setExceptionListener(new ExceptionListener() {
			public void exceptionThrown(Exception e) {
				System.out.println("Exception!: " + e.toString());
			}
		});
					
		rdata.addRank(pNameB, type, Bpoints, Bround);
		rdata.addRank(pNameR, type, Rpoints, Rround);
		
		encoder.writeObject(rdata);
		encoder.close();
		fos.close();
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
		RankData data = (RankData) decoder.readObject();
		decoder.close();
		fis.close();
		return data;
	}
	
	/**
	 * Checks if the "ranks.xml" file exists.
	 * @return	True if it exists, False if it does not.
	 * @throws IOException	If the file is not found.
	 */
	
	//Ellenõrzi, hogy létezik e a ranks.xml fájl
	private static boolean isThere() throws IOException {
		File f = new File(System.getProperty("user.dir"));
		File temp = new File(f.getCanonicalPath() + "/" + "ranks.xml");
		if(temp.exists() == true)
			return true;
		else
			return false;
		
	}
	
	/**
	 * 
	 * The Key Handlers for the game.
	 *
	 */
	
	private class KeyboardHandler implements KeyListener {

		//billentyû lenyomását érzékeli
		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				Rsnake.setDirection(Direction.UP);
			}
			
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {				
				Rsnake.setDirection(Direction.DOWN);
			}
			
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				Rsnake.setDirection(Direction.LEFT);
			}
			
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				Rsnake.setDirection(Direction.RIGHT);
			}
			
			if(e.getKeyCode() == KeyEvent.VK_W) {
				Bsnake.setDirection(Direction.UP);
			}
			
			if(e.getKeyCode() == KeyEvent.VK_S) {
				Bsnake.setDirection(Direction.DOWN);
			}
			
			if(e.getKeyCode() == KeyEvent.VK_A) {
				Bsnake.setDirection(Direction.LEFT);
			}
			
			if(e.getKeyCode() == KeyEvent.VK_D) {
				Bsnake.setDirection(Direction.RIGHT);
			}
			
		}

		public void keyReleased(KeyEvent arg0) {

			
		}

		public void keyTyped(KeyEvent arg0) {
			
		}

	}
}

