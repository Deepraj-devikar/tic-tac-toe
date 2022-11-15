package com.tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.tictactoe.Board.Coin;

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

	/*
	 * board will ask for play move move location should be in range from 1 to 9
	 */
	@Override
	public int makeMove() {
		checkBoard();
		System.out.println(name+" chance to play your identity letter is '"+identityLetter+"'");
		int location;
		do {
			System.out.println("Select move location on board in range from 1 to 9");
			location = readNumber();
		} while(location < 1 || 9 < location);
		return location;
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

	@Override
	public String headsOrTails() {
		System.out.println("Heads or Tails : ");
		System.out.println("0 for HEAD");
		System.out.println("1 for TAIL");
		System.out.println(name+" enter your option here : ");
		System.out.println("Note:- if you entered other than these options then will assume as HEAD");
		int choice = readNumber() == 1 ? 1 : 0;
		return Coin.values()[choice].toString();
	}

}
