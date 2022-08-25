package ticTacToe.view;

import java.awt.Color;

import javax.swing.JFrame;

public abstract class AFrame extends JFrame {
	
	public AFrame() {
		super("Tic Tac Toe");
		configure();
	}

	private void configure() {
		this.setSize(ADimensions.WIDTH, ADimensions.HEIGHT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBackground(Color.white);
		this.setLayout(null);
		this.setVisible(true);	
	}
}
