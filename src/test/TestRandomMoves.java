package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;

import product.Game;
import product.GUI;

class TestRandomMoves {


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
		board.makeRandomMove();
		assertEquals("", game.getCell(0, 0), "S");
		assertEquals("", game.getTurn(),"Blue");
	}
	
	@Test
	public void testRedMoveVacantCell() {
		board.makeRandomMove();
		assertEquals("", game.getCell(0, 0), "O");
		assertEquals("", game.getTurn(), "Red");
	}
	
	

	@Test
	public void testBlueMoveNonVacantCell() {
		board.blueRandomMove();
		assertEquals("", game.getCell(1, 0), "S");
		assertEquals("", game.getTurn(), "Blue");
		board.blueRandomMove();
		assertEquals("", game.getTurn(), "Blue");
	}

	@Test
	public void testBlueInvalidRowMove() {
		board.blueRandomMove();
		assertEquals("", game.getTurn(), "Blue");
	}

	@Test
	public void testBlueInvalidColumnMove() {
		board.blueRandomMove();
		assertEquals("", game.getTurn(), "Blue");
	}
	
	@Test
	public void testRedMoveNonVacantCell() {
		board.redRandomMove();
		assertEquals("", game.getCell(1, 0), "O");
		assertEquals("", game.getTurn(), "Red");
		board.redRandomMove();
		assertEquals("", game.getTurn(), "Red");
	}

	@Test
	public void testRedInvalidRowMove() {
		board.redRandomMove();
		assertEquals("", game.getTurn(), "Red");
	}

	@Test
	public void testRedInvalidColumnMove() {
		board.redRandomMove();
		assertEquals("", game.getTurn(), "Red");
	}


}
