package ticTacToe.model;

public class Player {

	private String playerName;
	private char playerChar;


	public Player (char playerCahar) {
		this.playerChar = playerCahar;

	}
	
	public void setName(String name) {
		this.playerName = name;
	}
	
	public String getName() {
		return playerName;
	}

	public char getCharacter() {
		return playerChar;
	}

	public void setCharacter(char character) {
		this.playerChar = character;
	}
}
