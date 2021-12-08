package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JButton;

import org.junit.jupiter.api.Test;
import org.junit.After;
import org.junit.Before;

import product.Game;
import product.GUI;


class TestEmptyBoard {
	
	private Game game = new Game();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	void testNewBoard() {
		for (int row = 0; row <game.getTotalRows(); ++row) {
			for (int col = 0; col < game.getTotalColumns(); ++col) {
				assertEquals("", game.getCell(row, col), new JButton()); 
			}
		}
	}
	
	@Test
	public void testInvalid() {
		assertEquals("", game.getCell(3, 0), null); 
	}


}
