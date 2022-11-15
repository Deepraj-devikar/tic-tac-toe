package com.tictactoe.test;

import java.lang.reflect.Field;
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
import com.tictactoe.Player;

@RunWith(Parameterized.class)
public class TestComputerAbilityToBlockOpponent {
	private Board board;
	private Player player;
	private Method setPlayerIdentityLetterMethod;
	private Field boardArrayField;
	private char[] boardArray;
	private int expectedMove;
	
	@Before
	public void setUp() throws Exception {
		player = new ComputerPlayer("Blocking Player");
		board = new Board(player, new ComputerPlayer("Other Player"));
		
		setPlayerIdentityLetterMethod = Board.class
				.getDeclaredMethod("setPlayerIdentityLetter", Integer.class, Character.class);
		setPlayerIdentityLetterMethod.setAccessible(true);
		setPlayerIdentityLetterMethod.invoke(board, 0, 'X');
		
		boardArrayField = Board.class.getDeclaredField("boardArray");
		boardArrayField.setAccessible(true);
	}
	
	public TestComputerAbilityToBlockOpponent(char[] boardArray, int expectedMove) {
		this.boardArray = boardArray;
		this.expectedMove = expectedMove;
	}
	
	@Parameterized.Parameters
	public static Collection testInputs() {
		// 1 => for setting board information
		// 2 => for expected move have to play by computer to win game
		
		Object[][] inputs = new Object[5][2];
		
		char[] boardArray;
		
		boardArray = new char[10];
		boardArray[0] = ' ';
		boardArray[1] = ' ';
		boardArray[2] = ' ';
		boardArray[3] = ' ';
		boardArray[4] = 'O';
		boardArray[5] = 'O';
		boardArray[6] = ' ';
		boardArray[7] = ' ';
		boardArray[8] = ' ';
		boardArray[9] = ' ';
		inputs[0][0] = boardArray;
		inputs[0][1] = 6;
		
		boardArray = new char[10];
		boardArray[0] = ' ';
		boardArray[1] = 'O';
		boardArray[2] = ' ';
		boardArray[3] = ' ';
		boardArray[4] = ' ';
		boardArray[5] = ' ';
		boardArray[6] = ' ';
		boardArray[7] = 'O';
		boardArray[8] = ' ';
		boardArray[9] = ' ';
		inputs[1][0] = boardArray;
		inputs[1][1] = 4;
		
		boardArray = new char[10];
		boardArray[0] = ' ';
		boardArray[1] = 'O';
		boardArray[2] = ' ';
		boardArray[3] = ' ';
		boardArray[4] = ' ';
		boardArray[5] = 'O';
		boardArray[6] = ' ';
		boardArray[7] = ' ';
		boardArray[8] = ' ';
		boardArray[9] = ' ';
		inputs[2][0] = boardArray;
		inputs[2][1] = 9;
		
		boardArray = new char[10];
		boardArray[0] = ' ';
		boardArray[1] = ' ';
		boardArray[2] = ' ';
		boardArray[3] = 'O';
		boardArray[4] = ' ';
		boardArray[5] = ' ';
		boardArray[6] = 'O';
		boardArray[7] = ' ';
		boardArray[8] = ' ';
		boardArray[9] = ' ';
		inputs[3][0] = boardArray;
		inputs[3][1] = 9;
		
		boardArray = new char[10];
		boardArray[0] = ' ';
		boardArray[1] = ' ';
		boardArray[2] = ' ';
		boardArray[3] = ' ';
		boardArray[4] = ' ';
		boardArray[5] = 'O';
		boardArray[6] = ' ';
		boardArray[7] = ' ';
		boardArray[8] = ' ';
		boardArray[9] = 'O';
		inputs[4][0] = boardArray;
		inputs[4][1] = 1;
		
		return Arrays.asList(inputs);
	}

	@Test
	public void test() throws IllegalArgumentException, IllegalAccessException {
		boardArrayField.set(board, boardArray);
		Assert.assertEquals(expectedMove, player.makeMove());
	}

}
