package com.tictactoe;

public abstract class Player {
	// on which board player is playing
	protected Board board;
	// players identity letter on board
	protected char identityLetter;
	// board information
	protected char[] boardArray;
	// name of player
	protected String name;
	
	/**
	 * create player with players name
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * set player to board
	 * @param board
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	public String getName() {
		return name;
	}

	/**
	 * specify players identity letter on board
	 * @param identityLetter
	 */
	public void noteIdentityLetter(char identityLetter) {
		this.identityLetter = identityLetter;
	}
	
	/*
	 * player will get information regarding board
	 */
	protected abstract void checkBoard();
}
