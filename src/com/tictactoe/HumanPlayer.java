package com.tictactoe;

import java.util.Scanner;

public class HumanPlayer extends Player{
	// scanner for getting move instruction from user as console input
	Scanner scanner;
	
	/**
	 * create human player with its unique name
	 * @param name
	 */
	public HumanPlayer(String name) {
		super(name);
		scanner = new Scanner(System.in);
	}

	/*
	 * show board information to human player on console
	 */
	@Override
	protected void checkBoard() {
		board.showBoard();
	}

}
