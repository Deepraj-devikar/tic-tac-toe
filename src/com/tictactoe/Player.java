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
	// player have to know arranging sequence to win
	protected int[][] winSequences;
	
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
	
	/**
	 * choose identity letter for him from identity letter options given
	 * @param identityLetterOptions
	 * @return identityLetter
	 */
	public abstract char chooseIdentityLetter(char[] identityLetterOptions);
	
	/**
	 * give move to board 
	 * move will be any location from 0 to 9
	 * @return move
	 */
	public abstract int makeMove();
	
	/*
	 * player will write win sequences according to his knowledge
	 */
	protected abstract void winSequences();
	
	/*
	 * player will say head or tails what he wants
	 */
	public abstract String headsOrTails();
	
}
