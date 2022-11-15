package com.tictactoe;

import java.util.Scanner;

public class TicTacToeGame {

	public static void main(String[] args) {
		System.out.println("Welcome to tic tac toe game");
		Scanner scanner = new Scanner(System.in);
		String haveToPlayAnotherGame;
		do {
			Player pravin = new HumanPlayer("Pravin");
			Player robo = new ComputerPlayer("Robo");
			Board board = new Board(pravin, robo);
			board.showBoard();
			board.play();
			System.out.println("If you have to play one more game then enter 'yes' or 'y' other wise enter 'no' or 'n' : ");
			haveToPlayAnotherGame = scanner.nextLine().toLowerCase();
		}while(haveToPlayAnotherGame.contains("y"));
		scanner.close();
	}

}
