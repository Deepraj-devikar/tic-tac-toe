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

}
