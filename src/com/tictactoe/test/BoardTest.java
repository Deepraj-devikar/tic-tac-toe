package com.tictactoe.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tictactoe.Board;

public class BoardTest {
	Board board;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@Test
	public void test() {
		char[] expectedBoardArray = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
		Assert.assertArrayEquals(expectedBoardArray, board.getCurrentBoard());
	}
}
