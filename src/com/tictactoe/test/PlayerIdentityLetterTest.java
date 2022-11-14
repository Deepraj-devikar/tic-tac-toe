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
public class PlayerIdentityLetterTest {
	private Board board;
	private Method setPlayerIdentityLetterMethod;
	private int playerIndex;
	private char playerIdentityLetter;
	private char[] expectedPlayersIdentityLetters; 
	
	@Before
	public void setUp() throws Exception {
		board = new Board(
				new ComputerPlayer("Player 1"), 
				new ComputerPlayer("Player 2")
				);
		setPlayerIdentityLetterMethod = Board.class
				.getDeclaredMethod("setPlayerIdentityLetter", Integer.class, Character.class);
		setPlayerIdentityLetterMethod.setAccessible(true);
	}
	
	public PlayerIdentityLetterTest(
			int playerIndex, 
			char playerIdentityLetter, 
			char expectedPlayerIdentityLetterIndex0, 
			char expectedPlayerIdentityLetterIndex1
			) {
		this.playerIndex = playerIndex;
		this.playerIdentityLetter = playerIdentityLetter;
		this.expectedPlayersIdentityLetters = new char[2];
		this.expectedPlayersIdentityLetters[0] = expectedPlayerIdentityLetterIndex0;
		this.expectedPlayersIdentityLetters[1] = expectedPlayerIdentityLetterIndex1;
	}
	
	@Parameterized.Parameters
	public static Collection testInputs() {
		// 1 => for setPlayerIdentityLetter methods first parameter i.e. playerIndex
		// 2 => for setPlayerIdentityLetter methods second parameter 
		// 		i.e. player identity character have to assign to specified player
		// 3 => expected player identity character for player at index 0
		// 4 => expected player identity character for player at index 1
		return Arrays.asList(new Object[][] {
			{0, 'X', 'X', 'O'},
			{0, 'x', 'X', 'O'},
			{0, 'O', 'O', 'X'},
			{0, 'o', 'O', 'X'},
			{0, 'a', 'O', 'X'},
			{0, 'A', 'O', 'X'},
			{1, 'X', 'O', 'X'},
			{1, 'x', 'O', 'X'},
			{1, 'O', 'X', 'O'},
			{1, 'o', 'X', 'O'},
			{1, 'a', 'X', 'O'},
			{1, 'A', 'X', 'O'},
		});
	}
	
	@Test
	public void test() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		setPlayerIdentityLetterMethod.invoke(board, playerIndex, playerIdentityLetter);
		char[] playersIdentityLetters = board.getPlayerIdentityLetter();
		for(int tempPlayerIndex = 0; tempPlayerIndex < expectedPlayersIdentityLetters.length; tempPlayerIndex++) {
			Assert.assertEquals(expectedPlayersIdentityLetters[tempPlayerIndex], playersIdentityLetters[tempPlayerIndex]);
		}
	}
	
}
