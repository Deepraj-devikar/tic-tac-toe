package com.tictactoe.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tictactoe.Board;
import com.tictactoe.ComputerPlayer;

public class BoardTest {
	Board board;
	
	@Before
	public void setUp() throws Exception {
		board = new Board(new ComputerPlayer("Robo 1"), new ComputerPlayer("Robo 2"));
	}

	@Test
	public void test() {
		char[] expectedBoardArray = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
		Assert.assertArrayEquals(expectedBoardArray, board.getCurrentBoard());
	}
}
