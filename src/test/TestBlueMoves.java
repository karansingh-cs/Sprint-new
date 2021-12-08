package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import javax.swing.JButton;

import org.junit.jupiter.api.Test;
import org.junit.After;
import org.junit.Before;

import product.Game;
import product.GUI;


class TestBlueMoves {


	private Game game;
	private GUI board;

	@Before
	public void setUp() throws Exception {
		game = new Game();
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testBlueMoveVacantCell() {
		game.blueMove();
		assertEquals("", game.getCell(0, 0), "S");
		assertEquals("", game.getTurn(), "Red");
	}

	@Test
	public void testBlueMoveNonVacantCell() {
		game.blueMove();
		assertEquals("", game.getCell(1, 0), "O");
		assertEquals("", game.getTurn(), "Blue");
		game.blueMove();
		assertEquals("", game.getTurn(), "Blue");
	}

	@Test
	public void testBlueInvalidRowMove() {
		game.blueMove();
		assertEquals("", game.getTurn(), "Blue");
	}

	@Test
	public void testBlueInvalidColumnMove() {
		game.blueMove();
		assertEquals("", game.getTurn(), "Blue");
	}

}
