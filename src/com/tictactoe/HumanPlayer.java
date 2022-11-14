package com.tictactoe;

import java.util.InputMismatchException;
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

	/*
	 * choose identity letter form identity letter option given by board
	 * human player will choose identity letter using console as user input
	 */
	@Override
	public char chooseIdentityLetter(char[] identityLetterOptions) {
		System.out.println(name+" you got chance to choose identity letter according to you : ");
		System.out.println("Identity letters in options are 'O' and 'X' : ");
		System.out.println("enter 'x' or 'X' for selecting 'X' othrewise enter any letter : ");
		System.out.println("Enter your option here : ");
		String choice = scanner.nextLine();
		char charX;
		char charNonX;
		if(Character.compare(identityLetterOptions[0], 'x') == 0 || Character.compare(identityLetterOptions[0], 'X') == 0) {
			charX = identityLetterOptions[0];
			charNonX = identityLetterOptions[1];
		} else {
			charX = identityLetterOptions[1];
			charNonX = identityLetterOptions[0];
		}
		return choice.contains("x") || choice.contains("X") ? charX : charNonX;
	}

	@Override
	public int makeMove() {
		checkBoard();
		System.out.println(name+" chance to play your identity letter is '"+identityLetter+"'");
		return readNumber();
	}
	
	private int readNumber() {
		int number;
		while(true) {
			try {
				number = scanner.nextInt();
				break;
			}catch(InputMismatchException e) {
				System.out.println("Please enter number : ");
			}finally {
				scanner.nextLine();
			}
		}
		return number;
	}

	@Override
	protected void winSequences() {	
	}

}
