package com.tictactoe;

import java.util.Hashtable;
import java.util.Scanner;

import com.tictactoe.Board.Coin;

public class ComputerPlayer extends Player{
	private Scanner scanner;
	
	/**
	 * attact decision will be based on our attact count on any win sequence and opponents attact count on that sequence
	 * and will return his decision in form of boolean variable 
	 * 
	 * @author Deepraj Devikar
	 *
	 */
	@FunctionalInterface
	private interface AttactDecision{
		boolean decision(int ourAttactsCount, int opponentAttactsCount);
	}
	
	/**
	 * attact will have two attact decisions that are attact to win or attact to block opponent to win
	 * 
	 * ATTACT TO WIN:
	 * we can win in only one move when our attact count on win sequence is 2 and opponent attact count is 0
	 * so we have to make only one move on this win sequence and we will win
	 * 
	 * ATTACT TO BLOCK:
	 * when our attact count is 0 on win sequence and opponent attact count is 2 
	 * so player make one on this win sequence and he can win
	 * that time have to block him from winning in one move only by playing one move on that win sequence
	 * 
	 * @author Deepraj Devikar
	 *
	 */
	private class Attact{
		AttactDecision attactToWin = (ourAttactsCount, opponentAttactsCount) -> ourAttactsCount == 2 && opponentAttactsCount == 0;
		AttactDecision attactToBlock = (ourAttactsCount, opponentAttactsCount) -> ourAttactsCount == 0 && opponentAttactsCount == 2;
	}
	
	/**
	 * create computer player with its unique name
	 * @param name
	 */
	public ComputerPlayer(String name) {
		super(name);
		winSequences();
		scanner = new Scanner(System.in);
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

	/*
	 * computer player will decide his move at which location
	 * 1) first he will see can he win in only one move if yes then he will play at that location and win
	 * 2) second he will see opponent winning in only one move if yes then he will block opponent from winning in one move only
	 * 3) third he will make list of all location based on priority and will play higher priority move
	 */
	@Override
	public int makeMove() {
		board.showBoard();
		checkBoard();
		Attact attact = new Attact();
		int locationToAttact;
		for(int attactTypeCounter = 1; attactTypeCounter <= 2; attactTypeCounter++) {
			locationToAttact = makeAttact(attactTypeCounter == 1 ? attact.attactToWin : attact.attactToBlock);
			if(locationToAttact != 0) {
				return locationToAttact;
			}
		}
		locationToAttact = makeAttact();
		if(locationToAttact != 0) {
			return locationToAttact;
		}
		System.out.println(name+" make your own move");
		return scanner.nextInt();
	}

	/**
	 * make attact on the basis of attact decision that is attact to win or attact to block
	 * 
	 * @param attactDecision
	 * @return
	 */
	private int makeAttact(AttactDecision attactDecision) {
		for(int winSequenceNumber = 0; winSequenceNumber < winSequences.length; winSequenceNumber++) {
			int ourAttactsCount = 0;
			int opponentAttactsCount = 0;
			int notAttactedTillNow = 0;
			for(int checkingNumber = 0; checkingNumber < winSequences[winSequenceNumber].length; checkingNumber++) {
				if(Character.compare(identityLetter, boardArray[winSequences[winSequenceNumber][checkingNumber]]) == 0) {
					ourAttactsCount++;
				} else if(Character.isLetter(boardArray[winSequences[winSequenceNumber][checkingNumber]])) {
					opponentAttactsCount++;
				} else {
					notAttactedTillNow = winSequences[winSequenceNumber][checkingNumber];
				}
			}
			if(attactDecision.decision(ourAttactsCount, opponentAttactsCount)) {
				return notAttactedTillNow;
			}
		}
		return 0;
	}
	
	/*
	 * make attact on the basis of locations priority
	 */
	private int makeAttact() {
		Hashtable<Integer, int[]> pointsCount = new Hashtable<Integer, int[]>();
		for(int i = 1; i <= 9; i++) {
			pointsCount.put(i, new int[2]);	
		}
		int[] tempPointsCount;
		for(int winSequenceNumber = 0; winSequenceNumber < winSequences.length; winSequenceNumber++) {
			int ourAttactsCount = 0;
			int opponentAttactsCount = 0;
			int notAttactedTillNow[] = new int[3];
			int notAttactedTillNowCount = 0;
			for(int checkingNumber = 0; checkingNumber < winSequences[winSequenceNumber].length; checkingNumber++) {
				if(Character.compare(identityLetter, boardArray[winSequences[winSequenceNumber][checkingNumber]]) == 0) {
					ourAttactsCount++;
				} else if(Character.isLetter(boardArray[winSequences[winSequenceNumber][checkingNumber]])) {
					opponentAttactsCount++;
					break;
				} else {
					notAttactedTillNow[notAttactedTillNowCount++] = winSequences[winSequenceNumber][checkingNumber];
				}
			}
			if(opponentAttactsCount == 0) {
				for(int i = 0; i < 3 - ourAttactsCount; i++) {
					tempPointsCount = pointsCount.get(notAttactedTillNow[i]);
					tempPointsCount[ourAttactsCount] = tempPointsCount[ourAttactsCount] + 1;
					pointsCount.put(notAttactedTillNow[i], tempPointsCount);
					if(tempPointsCount[ourAttactsCount] >= 2 && ourAttactsCount == 1) {
						return notAttactedTillNow[i];
					}
				}
			}
		}
		tempPointsCount = pointsCount.get(1);
		int maxPoint = tempPointsCount[0] + (tempPointsCount[1] * 2);
		int maxPosition = 1;
		for(int position = 2; position  <= 9; position++) {
			tempPointsCount = pointsCount.get(position);
			int tempPoint = tempPointsCount[0] + (tempPointsCount[1] * 2);
			if(maxPoint < tempPoint) {
				maxPoint = tempPoint;
				maxPosition = position;
			}
		}		
		return maxPosition;
	}

	/*
	 * write down win sequences
	 */
	@Override
	protected void winSequences() {
		winSequences = new int[8][3];
		for(int i = 0; i < 8; i++) {
			winSequences[i] = new int[3];
		}
		for(int i = 1; i < 8; i = i + 3) {
			for(int j = i; j < i + 3; j++) {
				winSequences[(int) (i / 3)][j - i] = j;
			}
		}
		for(int i = 1; i < 4; i++) {
			for(int j = i; j < i + 7; j = j + 3) {
				winSequences[i + 2][(int) ((j - i) / 3)] = j;
			}
		}
		
		winSequences[6][0] = 1;
		winSequences[6][1] = 5;
		winSequences[6][2] = 9;
		
		winSequences[7][0] = 3;
		winSequences[7][1] = 5;
		winSequences[7][2] = 7;
	}

	@Override
	public String headsOrTails() {
		return Coin.values()[(int) (Math.floor(Math.random() * 10) % 2)].toString();
	}
}
