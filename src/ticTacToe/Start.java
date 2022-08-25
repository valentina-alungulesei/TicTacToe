package ticTacToe;

import java.util.Random;

import javax.swing.SwingUtilities;

import ticTacToe.controller.GameController;

// This is the main class
public class Start {
	
	public static Random random = new Random();
	
	public static void main (String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				 GameController.getInstance().showGUI();
			}
		});
	}
}
