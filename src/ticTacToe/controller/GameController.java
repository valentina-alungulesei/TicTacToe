package ticTacToe.controller;

import ticTacToe.model.GameBoard;
import ticTacToe.model.Player;
import ticTacToe.view.GameBoardFrame;
import ticTacToe.view.IMoveListener;
import ticTacToe.view.IStartButtonListener;
import ticTacToe.view.WelcomeFrame;

// There should be only one instance of this class, therefore Singleton Design Pattern is used.
public class GameController {
	
	private static GameController controller;
	
	private WelcomeFrame welcomeFrame;
	private GameBoard gameBoard;
	private GameBoardFrame gameBoardFrame;
	
	private GameController() {
		System.out.println("Game controller was created");
	}
	
	public static GameController getInstance() {
		synchronized (GameController.class) {
			if (controller == null) {
				controller = new GameController();
			}
		}
		return controller;
	}
	
	public void showGUI() {
		initializeWelcomeFrame();
	}
	
	public char getUserCharacter() {
		return getGameBoard().getUser().getCharacter();
	}
	
	public char getCpuCharacter() {
		return getGameBoard().getCpu().getCharacter();
	}
	
	public boolean isUserFirst() {
		return getGameBoard().isUserFirst();
	}
	
	private void initializeWelcomeFrame() {
		if(welcomeFrame == null) {
			welcomeFrame = new WelcomeFrame();
			welcomeFrame.addStartButtonListener(new IStartButtonListener() {
				
				@Override
				public void buttonWasPressed() {
	
					welcomeFrame.dispose();
					
					initializeGameBoardFrame();
				}
			});
		}
	}

	private void initializeGameBoardFrame() {
	
		gameBoardFrame = new GameBoardFrame();
		gameBoardFrame.addMoveListener(new IMoveListener() {
			@Override
			public void playerMoved(int i, char playerChar) {
				if (playerChar == getGameBoard().getUser().getCharacter()) {
					getGameBoard().addMove(i, getGameBoard().getUser());
					getGameBoard().checkWinner(getGameBoard().getUser());
				} else {
					getGameBoard().addMove(i, getGameBoard().getCpu());
					getGameBoard().checkWinner(getGameBoard().getCpu());
				}

				if (getGameBoard().isThereAWinner() == true) {
					gameBoardFrame.showTheWinner(gameBoard.getWinningCombo());
				}
			}		
		});
	}
	
	private GameBoard getGameBoard() {
		
		if (gameBoard == null) {
			
			Player user = new Player(welcomeFrame.getUserPickedChar());
		
			char cpuChar;
			if (welcomeFrame.getUserPickedChar() != 0 && user.getCharacter() == 'X'){
				cpuChar = 'O';
			} else {
				cpuChar = 'X';
			}
			
			Player cpu = new Player(cpuChar);
			
			gameBoard = new GameBoard(user, cpu);
		}
		return gameBoard;
	}
}
