package com.tictactoe;

public class Board {
	// move is valid or invalid
	public enum Move{VALID, INVALID};	
	public enum Coin{HEAD, TAIL}
	
	// board information and all players moves will be store in board array 
	private char[] boardArray;
	// players for the tic tac toe game
	private Player[] players;
	// players identification letters for tic tac toe game according to index of players
	private char[] playersIdentityLetters;
	
	/**
	 * creates board with 2 players
	 * setup empty board
	 * 
	 * @param player1
	 * @param player2
	 */
	public Board(Player player1, Player player2) {
		boardArray = new char[10];
		for(int i = 0; i < 10; i++) {
			boardArray[i] = ' ';	
		}
		this.players = new Player[2];
		this.players[0] = player1;
		this.players[1] = player2;
		this.playersIdentityLetters = new char[2];
		this.playersIdentityLetters[0] = 'O';
		this.playersIdentityLetters[1] = 'X';
		player1.setBoard(this);
		player2.setBoard(this);
	}
	
	/**
	 * player index will be either 0 or one because board game has only 2 players
	 * if player index is 0 then other player index will be 1 and vice versa 
	 * 
	 * @param playerIndex
	 * @return otherPlayerIndex
	 */
	private int otherPlayerIndex(int playerIndex) {
		return (playerIndex + 1) % 2;
	}
	
	/**
	 * set identity letter of player to specified indexed player
	 * and another identity letter to another player
	 * 
	 * if index is not 0 or 1 then it will keep player identity index default
	 * that are player identity letter of player who is at index 0 is 'O'
	 * and player identity letter of player who is at index 1 is 'X'
	 * 
	 * @param playerIndex
	 * @param playerIdentityLetter
	 */
	private void setPlayerIdentityLetter(Integer playerIndex,Character playerIdentityLetter) {
		try {
			if(playerIdentityLetter == 'X' || playerIdentityLetter == 'x') {
				playersIdentityLetters[playerIndex] = 'X';
				playersIdentityLetters[otherPlayerIndex(playerIndex)] = 'O';
			} else {
				playersIdentityLetters[playerIndex] = 'O';
				playersIdentityLetters[otherPlayerIndex(playerIndex)] = 'X';
			}	
		} catch(ArrayIndexOutOfBoundsException exception) {
			System.out.println("Player index should be 0 or 1.");
		}
	}
	
	/*
	 * show board in human understandable form
	 */
	public void showBoard() {
		System.out.println(showCurrentBoard()+"\n\n"+showCurrentPlayOptionBoard());
	}
	
	/**
	 * information of board in human understandable form
	 * @return information of board
	 */
	public String showCurrentBoard() {
		String result = "Current Board : \n";
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				result += "   "+boardArray[(i * 3) + j + 1]+(j == 2 ? "   " : "   |");
			}
			if(i != 2) {
				result += "\n-----------------------\n";
			}
		}
		return result;
	}
	
	/**
	 * information of board in human understandable form so player can make correct move
	 * @return information of board for choose play option
	 */
	public String showCurrentPlayOptionBoard() {
		String result = "Current Play Option On Board : \n";
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(!Character.isLetter(boardArray[(i * 3) + j + 1])) {
					result += "   "+((i * 3) + j + 1)+(j == 2 ? "   " : "   |");
				} else {
					result += "   "+boardArray[(i * 3) + j + 1]+(j == 2 ? "   " : "   |");	
				}
			}
			if(i != 2) {
				result += "\n-----------------------\n";
			}
		}
		return result;
	}
	
	public char[] getCurrentBoard() {
		return boardArray;
	}
	
	/**
	 * make move of player which player index we are getting in parameters to that location 
	 * move is possible when location is in the range from 1 to 9 and moving player index is 0 or 1
	 * and also location is not already occupied by any of the player
	 * 
	 * @param location
	 * @param movingPlayerIndex
	 */
	private Move makeMove(Integer location, Integer movingPlayerIndex) {
		if(location != 0) {
			try {
				if(!Character.isLetter(boardArray[location])) {
					boardArray[location] = playersIdentityLetters[movingPlayerIndex];
					return Move.VALID;
				}	
			} catch(ArrayIndexOutOfBoundsException exception) {
				System.out.println("move location should be in range from 1 to 9 and player index should be 0 or 1");
			}
		} else {
			System.out.println("move location should be in range from 1 to 9 and player index should be 0 or 1");
		}
		return Move.INVALID;
	}
	
	/**
	 * will ask one player for heads or tails
	 * @return index of player who will play first
	 */
	private int toss() {
		for(int playerNumber = 0; playerNumber < players.length; playerNumber++) {
			if(players[playerNumber] instanceof HumanPlayer) {
				return Coin.values()[(int) Math.floor(Math.random() * 10) % 2]
						.toString()
						.equals(players[playerNumber].headsOrTails()) 
						? playerNumber 
								: otherPlayerIndex(playerNumber);
			}
		}
		return Coin.values()[(int) Math.floor(Math.random() * 10) % 2]
				.toString()
				.equals(players[0].headsOrTails()) 
				? 0 
						: 1;
	}
	
	/*
	 * board game will start here board will ask to random player to choose identity letter
	 * from specified identity letter options
	 */
	public void play() {		
		int playerIndex = toss();
		System.out.println(players[playerIndex].getName()+" have won the toss and will play first");
		setPlayerIdentityLetter(playerIndex, players[playerIndex].chooseIdentityLetter(playersIdentityLetters));
		players[playerIndex].noteIdentityLetter(playersIdentityLetters[playerIndex]);
		players[otherPlayerIndex(playerIndex)].noteIdentityLetter(playersIdentityLetters[otherPlayerIndex(playerIndex)]);
		while(makeMove(players[playerIndex].makeMove(), playerIndex).equals(Move.INVALID));
		playerIndex = otherPlayerIndex(playerIndex);
		while(makeMove(players[playerIndex].makeMove(), playerIndex).equals(Move.INVALID));
		showBoard();
	}
	
	public char[] getPlayerIdentityLetter() {
		return playersIdentityLetters;
	}
}
