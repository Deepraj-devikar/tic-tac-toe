package com.tictactoe.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.tictactoe.Board;
import com.tictactoe.ComputerPlayer;

@RunWith(Parameterized.class)
public class BoardMakeMoveTest {
	private Board board;
	private Method makeMoveMethod;
	private int numberOfMoves;
	private int[] locations;
	private int[] movingPlayerIndexes;
	private char[] expectedBoardArray; 
	
	@Before
	public void setUp() throws Exception {
		board = new Board(
				new ComputerPlayer("Player 1"), 
				new ComputerPlayer("Player 2")
				);
		makeMoveMethod = Board.class
				.getDeclaredMethod("makeMove", Integer.class, Integer.class);
		makeMoveMethod.setAccessible(true);
	}
	
	public BoardMakeMoveTest(int numberOfMoves, int[] locations, int[] movingPlayerIndexes, char[] expectedBoardArray) {
		this.numberOfMoves = numberOfMoves;
		this.locations = locations;
		this.movingPlayerIndexes = movingPlayerIndexes;
		this.expectedBoardArray = expectedBoardArray;
	}

	@Parameterized.Parameters
	public static Collection testInputs() {
		// 1 => for number of moves have to play
		// 2 => for locations on board where have to play moves
		// 3 => for indexes of players whose moves have to play
		// 4 => for expected board information after played move
		
		Object[][] inputs = new Object[5][4];
		
		int[] locations;
		int[] movingPlayerIndexes;
		char[] expectedBoardArray;
		
		inputs[0][0] = 2;
		locations = new int[2];
		locations[0] = 5;
		locations[1] = 9;
		inputs[0][1] = locations;
		movingPlayerIndexes = new int[2];
		movingPlayerIndexes[0] = 0;
		movingPlayerIndexes[1] = 1;
		inputs[0][2] = movingPlayerIndexes;
		expectedBoardArray = new char[10];
		expectedBoardArray[0] = ' ';
		expectedBoardArray[1] = ' ';
		expectedBoardArray[2] = ' ';
		expectedBoardArray[3] = ' ';
		expectedBoardArray[4] = ' ';
		expectedBoardArray[5] = 'O';
		expectedBoardArray[6] = ' ';
		expectedBoardArray[7] = ' ';
		expectedBoardArray[8] = ' ';
		expectedBoardArray[9] = 'X';
		inputs[0][3] = expectedBoardArray;	
		
		inputs[1][0] = 2;
		locations = new int[2];
		locations[0] = 15;
		locations[1] = 9;
		inputs[1][1] = locations;
		movingPlayerIndexes = new int[2];
		movingPlayerIndexes[0] = 0;
		movingPlayerIndexes[1] = 1;
		inputs[1][2] = movingPlayerIndexes;
		expectedBoardArray = new char[10];
		expectedBoardArray[0] = ' ';
		expectedBoardArray[1] = ' ';
		expectedBoardArray[2] = ' ';
		expectedBoardArray[3] = ' ';
		expectedBoardArray[4] = ' ';
		expectedBoardArray[5] = ' ';
		expectedBoardArray[6] = ' ';
		expectedBoardArray[7] = ' ';
		expectedBoardArray[8] = ' ';
		expectedBoardArray[9] = 'X';
		inputs[1][3] = expectedBoardArray;	
		
		inputs[2][0] = 2;
		locations = new int[2];
		locations[0] = 7;
		locations[1] = 4;
		inputs[2][1] = locations;
		movingPlayerIndexes = new int[2];
		movingPlayerIndexes[0] = 0;
		movingPlayerIndexes[1] = 1;
		inputs[2][2] = movingPlayerIndexes;
		expectedBoardArray = new char[10];
		expectedBoardArray[0] = ' ';
		expectedBoardArray[1] = ' ';
		expectedBoardArray[2] = ' ';
		expectedBoardArray[3] = ' ';
		expectedBoardArray[4] = 'X';
		expectedBoardArray[5] = ' ';
		expectedBoardArray[6] = ' ';
		expectedBoardArray[7] = 'O';
		expectedBoardArray[8] = ' ';
		expectedBoardArray[9] = ' ';
		inputs[2][3] = expectedBoardArray;	
		
		inputs[3][0] = 2;
		locations = new int[2];
		locations[0] = 5;
		locations[1] = 9;
		inputs[3][1] = locations;
		movingPlayerIndexes = new int[2];
		movingPlayerIndexes[0] = 0;
		movingPlayerIndexes[1] = 1;
		inputs[3][2] = movingPlayerIndexes;
		expectedBoardArray = new char[10];
		expectedBoardArray[0] = ' ';
		expectedBoardArray[1] = ' ';
		expectedBoardArray[2] = ' ';
		expectedBoardArray[3] = ' ';
		expectedBoardArray[4] = ' ';
		expectedBoardArray[5] = 'O';
		expectedBoardArray[6] = ' ';
		expectedBoardArray[7] = ' ';
		expectedBoardArray[8] = ' ';
		expectedBoardArray[9] = 'X';
		inputs[3][3] = expectedBoardArray;	
		
		inputs[4][0] = 2;
		locations = new int[2];
		locations[0] = 5;
		locations[1] = 9;
		inputs[4][1] = locations;
		movingPlayerIndexes = new int[2];
		movingPlayerIndexes[0] = 0;
		movingPlayerIndexes[1] = 1;
		inputs[4][2] = movingPlayerIndexes;
		expectedBoardArray = new char[10];
		expectedBoardArray[0] = ' ';
		expectedBoardArray[1] = ' ';
		expectedBoardArray[2] = ' ';
		expectedBoardArray[3] = ' ';
		expectedBoardArray[4] = ' ';
		expectedBoardArray[5] = 'O';
		expectedBoardArray[6] = ' ';
		expectedBoardArray[7] = ' ';
		expectedBoardArray[8] = ' ';
		expectedBoardArray[9] = 'X';
		inputs[4][3] = expectedBoardArray;	
		
		return Arrays.asList(inputs);
	}
	
	@Test
	public void test() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for(int i = 0; i < numberOfMoves; i++) {
			makeMoveMethod.invoke(board, locations[i], movingPlayerIndexes[i]);	
		}
		Assert.assertArrayEquals(expectedBoardArray, board.getCurrentBoard());
	}

}
