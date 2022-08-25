package ticTacToe.view;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ticTacToe.controller.GameController;

import ticTacToe.view.progressBar.MyProgressBar;
import ticTacToe.view.progressBar.UpdateProgressBarTask;

import java.awt.Color;
import java.awt.GridLayout;

public class GameBoardFrame extends AFrame  implements ActionListener {

	private boolean isUserTurn;
	
	private JPanel infoPanel;
	private MyProgressBar progressBar;
	private JLabel textLabel;
	private JPanel buttonsPanel;
	private JButton[] buttons;
	
	private UpdateProgressBarTask updateProgressBarTask;
	
	private List<IMoveListener> moveListeners;
	
	public GameBoardFrame() {
		isUserTurn = GameController.getInstance().isUserFirst();
		
		updateProgressBarTask = new UpdateProgressBarTask(getProgressBar(), getProgressMessage());
		moveListeners = new ArrayList<IMoveListener>();
		
		build();
		updateView();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < buttons.length; i++) {
		
			if (e.getSource() == buttons[i] && isUserTurn && buttons[i].getText().equals("")) {
				isUserTurn = false;
				changeTurn(GameController.getInstance().getUserCharacter(), buttons[i], "CPU's turn");
				
				for (IMoveListener listener : moveListeners) {
					listener.playerMoved(i, GameController.getInstance().getUserCharacter());
				}
			}
			else if (e.getSource() == buttons[i] && !isUserTurn && buttons[i].getText().equals("")) {
				isUserTurn = true;
				changeTurn(GameController.getInstance().getCpuCharacter(), buttons[i], "Your turn");
				
				for (IMoveListener listener : moveListeners) {
					listener.playerMoved(i, GameController.getInstance().getCpuCharacter());
				}
			}
		}
	}
	
	public void addMoveListener(IMoveListener listener) {
		this.moveListeners.add(listener);
	}
	
	public void showTheWinner(int[] positions) {
		resetinfoPanel();
		getTextLabel().setText("There is a winner");
		
		int buttonsToDisable[] = new int[9];
		
		for (int i = 0; i < buttons.length; i++) {
			buttonsToDisable[i] = i;
			for (int positionIndex = 0; positionIndex < positions.length; positionIndex++) {
				if (positions[positionIndex] == i) {
					buttons[i].setForeground(new Color(255, 0, 0));
					buttonsToDisable[i] = 10;
				} 
			}
		}
		
		for (int index = 0; index < buttonsToDisable.length; index++) {
			if (buttonsToDisable[index] != 10) {
				buttons[index].setEnabled(false);
			}
		}
	}
	
	private void build() {
		this.add(getInfoPanel());
		this.add(getButtonsPanel());
	}
	
	private void updateView() {
		updateProgressBarTask.addListener(new IProgreesBarTaskListener() {
			
			@Override
			public void taskEnded() {
				
				updateTextLabel();
				enableButtons();
			}
		});
	}

	private String getProgressMessage() {
		StringBuilder messageBuilder = new StringBuilder("First turn is up to ... ");
		
		if (GameController.getInstance().isUserFirst()) {
			messageBuilder.append("you");
		}
		else {
			messageBuilder.append("CPU");
		}
		return messageBuilder.toString();
	}
	
	private JPanel getInfoPanel() {
		if(infoPanel == null) {
			infoPanel = new JPanel();
			infoPanel.setBounds(10, 5, ADimensions.WIDTH - 20, ADimensions.HEIGHT / 7);
			infoPanel.setLayout(null);
			
			infoPanel.add(getProgressBar());
			infoPanel.add(getTextLabel());
			updateProgressBarTask.start();
		}
		return infoPanel;
	}
	
	private MyProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new MyProgressBar(0, 100);
			progressBar.setBounds(0, 10, ADimensions.WIDTH - 20, 30);
			progressBar.setStringPainted(true);
			progressBar.setString("Randomizing first turn ...");
		}
		return progressBar;
	}
	
	private void updateTextLabel() {
		if (GameController.getInstance().isUserFirst()) {
			getTextLabel().setText("Place an " + GameController.getInstance().getUserCharacter() + " on the board");
			this.repaint();
		}
		else {
			getTextLabel().setText("Wait for the CPU to make it's move");
			this.repaint();
		}
	}
	
	private JLabel getTextLabel() {
		if (textLabel == null) {
			textLabel = new JLabel("",JLabel.CENTER);
			textLabel.setBounds(0, 50, ADimensions.WIDTH - 20, 20);
		}
		return textLabel;
	}
	
	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
		}
		
		buttonsPanel.setLayout(new GridLayout(3, 3));
		buttonsPanel.setBounds(50, 100, ADimensions.WIDTH - 100, ADimensions.HEIGHT - ADimensions.HEIGHT / 4);
		
		buttons = new JButton[9];
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setEnabled(false);
			buttons[i].addActionListener(this);
			buttonsPanel.add(buttons[i]);
		}
		
		return buttonsPanel;
	}
	
	private void enableButtons() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(true);
		}
	}

	private void changeTurn(char character, JButton button, String message) {
		resetinfoPanel();
		
		button.setText(String.valueOf(character));

		getTextLabel().setText(message);

		this.repaint();
	}
	
	private void resetinfoPanel() {
		getTextLabel().setText("");
		getTextLabel().setBounds(0, 30, ADimensions.WIDTH - 20, 20);
		
		infoPanel.removeAll();
		infoPanel.add(getTextLabel());
	}
}
