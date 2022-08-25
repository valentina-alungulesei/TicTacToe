package ticTacToe.view.progressBar;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;

public class MyProgressBar extends JProgressBar {
	
	/**
	 * @param min
	 * @param max
	 */
	public MyProgressBar(int min, int max) {
		super(min, max);
		configure();
	}

	private void configure() {
		setDoubleBuffered(true);
		setUI(new MyProgressBarUI());
		setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225), 1));
	}
}
