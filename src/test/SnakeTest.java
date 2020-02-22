package test;

import java.awt.Color;
import java.io.IOException;

import javax.swing.ImageIcon;

import org.junit.Assert;
import org.junit.Test;

import Game.Direction;
import Game.Food;
import Game.Menu;
import Game.Snake;
import Rank.Rank;
import Rank.RankData;
import Rank.RankFrame;

public class SnakeTest {
		
	@Test
	public void testPosMenuXY() {
		
		Menu m = new Menu();
		double x = m.getAlignmentX();
		double y = m.getAlignmentY();
		Assert.assertEquals(0.5, x, 0);
		Assert.assertEquals(0.5, y, 0);
		
	}
	
	@Test
	public void testMenuSize() {
		
		Menu m = new Menu();
		int w = m.getWidth();
		int h = m.getHeight();
		Assert.assertEquals(787, w, 0);
		Assert.assertEquals(478, h, 0);
		
	}
	
	@Test
	public void testRankFrame() throws IOException {
		
		RankFrame rf = new RankFrame();
		String title = rf.getTitle();
		Assert.assertEquals("Ranks", title);		
	}
	
	@Test
	public void testRankFrameBckg() throws IOException {
		
		RankFrame rf = new RankFrame();
		Color background = rf.getContentPane().getBackground();
		Assert.assertEquals(Color.BLACK, background);		
	}
	
	@Test
	public void testSnakeHead() {
		
		Snake sssnake = new Snake("b");
		int sx = sssnake.getHeadX();
		int sy = sssnake.getHeadY();
		Assert.assertEquals(120, sx);
		Assert.assertEquals(210, sy);
		
	}

	@Test
	public void testSnakeDirection() {
		
		Snake sssnake = new Snake("r");
		Direction sd = sssnake.getDirection();
		Assert.assertEquals(Direction.LEFT, sd);
		
	}
	@Test
	public void testSnakeSetDirection() {
		Snake sssnake = new Snake("b");
		sssnake.setDirection(Direction.LEFT);
		Assert.assertEquals(Direction.RIGHT, sssnake.getDirection());
	}
	
	@Test
	public void testFood() {
		
		Food f = new Food();
		Assert.assertEquals(new ImageIcon("Zöld.png"), f.getFood());
	}
	
	@Test
	public void testFoodCoord() {
		
		Food f = new Food();
		int i = f.getCoord().getX();
		Assert.assertEquals(f.getCoord().getX(), i);
		
	}
	
	@Test
	public void testRank() {
		
		Rank testRank = new Rank("Test", "Test", 1, 0);
		Assert.assertEquals("Test", testRank.getName());
		
	}
	
	@Test
	public void testRankData() {
		RankData testData = new RankData();
		testData.addRank("Test", "Test", 1, 0);
		Assert.assertEquals("Test", testData.getRanks().get(0).getType());
	}
}
