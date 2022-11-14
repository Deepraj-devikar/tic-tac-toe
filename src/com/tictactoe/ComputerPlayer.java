package com.tictactoe;

public class ComputerPlayer extends Player{

	/**
	 * create computer player with its unique name
	 * @param name
	 */
	public ComputerPlayer(String name) {
		super(name);
	}

	/*
	 * get board information from board and note it down for decision of next move
	 */
	@Override
	protected void checkBoard() {
		boardArray = board.getCurrentBoard();
	}

	/*
	 * randomly choose identity letter from identity letter options given by board
	 */
	@Override
	public char chooseIdentityLetter(char[] identityLetterOptions) {
		return identityLetterOptions[(int) (Math.floor(Math.random() * 10) % 2)];
	}

}
