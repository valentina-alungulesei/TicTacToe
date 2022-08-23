package ticTacToe.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

// This is a frame which allows the user to pick the wanted character to play with
public class WelcomeFrame extends JFrame {
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 500;
	
	private JPanel titlePanel;
	private JPanel pickCharPanel;
	private JRadioButton xRadioButton;
	private JRadioButton oRadioButton;
	private ButtonGroup buttonGroup;
	private JButton startButton;
	private char userpickedChar;
	
	public WelcomeFrame() {
		super("Tic Tac Toe");
		configure();
		build();
	}

	private void configure() {
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBackground(Color.white);
		this.setLayout(null);
		this.setVisible(true);	
	}
	
	private void build() {
		this.add(getTitlePanel());
		this.add(getPickCharPanel());
		this.add(getStartButton());
	}

	private JPanel getTitlePanel() {
		if (this.titlePanel == null) {
			
			JLabel label = new JLabel("~ Welcome ~");
			
			this.titlePanel = new JPanel();
			this.titlePanel.setBounds(0, 50, WIDTH, 50);
			this.titlePanel.add(label);
		}
		return titlePanel;
	}
	
	private JPanel getPickCharPanel() {
		if (this.pickCharPanel == null) {
			
			JLabel textLabel = new JLabel("Pick the character with what you want to play");
			textLabel.setHorizontalAlignment(SwingConstants.CENTER);
			textLabel.setBounds(0, 50, WIDTH, 20);
			
			buttonGroup = new ButtonGroup();
			buttonGroup.add(getXRadioButton());
			buttonGroup.add(getORadioButton());
			
			this.pickCharPanel = new JPanel();
			this.pickCharPanel.setBounds(0, 125, WIDTH, 200);
			this.pickCharPanel.setLayout(null);
			this.pickCharPanel.add(getXRadioButton());
			this.pickCharPanel.add(getORadioButton());
			this.pickCharPanel.add(textLabel);
		}
		return pickCharPanel;
	}

	private JRadioButton getXRadioButton() {
		if (xRadioButton == null) {
			xRadioButton = new JRadioButton("X");
			xRadioButton.setBounds((WIDTH/2) - 80, 100, 50, 20);
			xRadioButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == xRadioButton) {
						userpickedChar = 'X';
						getStartButton().setEnabled(true);
						System.out.println("User picked charcater: " + userpickedChar);
					}
				}
			});
		}
		return xRadioButton;
	}

	private JRadioButton getORadioButton() {
		if (oRadioButton == null) {
			oRadioButton = new JRadioButton("O");
			oRadioButton.setBounds((WIDTH/2) + 40, 100, 50, 20);
			oRadioButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == oRadioButton) {
						userpickedChar = 'O';
						getStartButton().setEnabled(true);
						System.out.println("User picked charcater: " + userpickedChar);
					}
				}
			}); 
		}
		return oRadioButton;
	}
	
	private JButton getStartButton() {
		if (startButton == null) {
			startButton = new JButton("Start the game");
			startButton.setBounds((WIDTH/2) - 50, 360, 100, 20);
			startButton.setEnabled(false);
			startButton.addActionListener(new ActionListener () {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == startButton) {
						System.out.println("The start button was pressed");
					}
				}
				
			});
		}
		return startButton;
	}
}
