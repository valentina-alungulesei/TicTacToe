package ticTacToe;

import javax.swing.SwingUtilities;

import ticTacToe.view.WelcomeFrame;

// This is the main class
public class Start {
	
	public static void main (String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				 WelcomeFrame welcomeFrame = new WelcomeFrame();
			}
		});
	}
}
