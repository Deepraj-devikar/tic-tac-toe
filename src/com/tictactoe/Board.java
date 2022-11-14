package com.tictactoe;

public class Board {
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
		this.playersIdentityLetters[0] = 'X';
		player1.setBoard(this);
		player2.setBoard(this);
	}
	
	/*
	 * show board in human understandable form
	 */
	public void showBoard() {
		System.out.println(showCurrentBoard()+"\n\n"+showCurrentPlayOptionBoard());
	}
	
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
}
