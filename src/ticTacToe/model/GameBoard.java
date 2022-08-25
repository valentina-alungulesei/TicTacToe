package ticTacToe.model;

import ticTacToe.Start;

public class GameBoard {
	
	private Player user;
	private Player cpu;
	private char[] moves;
	private int[] winningCombo;
	private boolean isThereAWinner;
	
	public GameBoard(Player user, Player cpu) {
		this.user = user;
		this.cpu = cpu;
		
		this.moves = new char[9];
	}

	public Player getUser() {
		return user;
	}

	public Player getCpu() {
		return cpu;
	}

	// Randomize the user first turn
	public boolean isUserFirst() {
		if (Start.random.nextInt(2) == 0) {
			return true;
		}
		else { 
			return false;
		}
	}
	
	public void addMove(int index, Player player) {
		this.moves[index] = player.getCharacter();
	}

	public void checkWinner(Player player) {
		
		char playerChar = player.getCharacter();
		
		for (int i = 0; i < moves.length; i++) {
			
			// Vertical winning conditions
			if (moves[0] == playerChar && moves[3] == playerChar && moves[6] == playerChar) {
				setWinningCombo(0, 3, 6);
			}
			else if (moves[1] == playerChar && moves[4] == playerChar && moves[7] == playerChar) {
				setWinningCombo(1, 4, 7);
			}
			else if (moves[2] == playerChar && moves[5] == playerChar && moves[8] == playerChar) {
				setWinningCombo(2, 5, 8);
			}
			
			// Horizontal winning conditions
			if (moves[0] == playerChar && moves[1] == playerChar && moves[2] == playerChar) {
				setWinningCombo(0, 1, 2);
			}
			else if (moves[3] == playerChar && moves[4] == playerChar && moves[5] == playerChar) {
				setWinningCombo(3, 4, 5);
			}
			else if (moves[6] == playerChar && moves[7] == playerChar && moves[8] == playerChar) {
				setWinningCombo(6, 7, 8);
			}
			
			// Diagonal conditions
			if (moves[0] == playerChar && moves[4] == playerChar && moves[8] == playerChar) {
				setWinningCombo(0, 4, 8);
			}
			else if (moves[2] == playerChar && moves[4] == playerChar && moves[6] == playerChar) {
				setWinningCombo(2, 4, 6);
			}
			break;
		}
	}
	
	public boolean isThereAWinner() {
		return this.isThereAWinner;
	}
	
	public int[] getWinningCombo() {
		return winningCombo;
	}
	
	private void setWinningCombo(int a, int b, int c) {
		if (winningCombo == null) {
			winningCombo = new int[3];
		}
		winningCombo[0] = a;
		winningCombo[1] = b;
		winningCombo[2] = c;
		
		isThereAWinner = true;
	}
}
