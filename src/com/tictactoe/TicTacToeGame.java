package com.tictactoe;

public class TicTacToeGame {

	public static void main(String[] args) {
		System.out.println("Welcome to tic tac toe game");
		Player pravin = new HumanPlayer("Pravin");
		Player robo = new ComputerPlayer("Robo");
		Board board = new Board(pravin, robo);
		board.showBoard();
	}

}
