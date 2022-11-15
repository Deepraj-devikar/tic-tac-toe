package com.tictactoe.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tictactoe.Board;
import com.tictactoe.ComputerPlayer;

public class TestComputerPlayTillComplete {
	Board board;
	
	@Before
	public void setUp() throws Exception {
		board = new Board(
				new ComputerPlayer("Player 1"),
				new ComputerPlayer("Player 2"));
	}

	@Test
	public void test() {
		board.play();
		char[] boardArray = board.getCurrentBoard();
		for(int i = 1; i < 10; i++) {
			Assert.assertTrue(Character.isLetter(boardArray[i]));
		}
		Assert.assertTrue(board.isCompleted());
	}

}
