package ticTacToe.view.progressBar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class MyProgressBarUI extends BasicProgressBarUI {

	@Override
	public void paintDeterminate(Graphics g, JComponent c) {

		if (!(g instanceof Graphics2D)) {
			return;
		}

		Insets b = progressBar.getInsets(); // area for border
		int barRectWidth = progressBar.getWidth() - (b.right + b.left);
		int barRectHeight = progressBar.getHeight() - (b.top + b.bottom);

		if (barRectWidth <= 0 || barRectHeight <= 0) {
			return;
		}

		int amountFull = getAmountFull(b, barRectWidth, barRectHeight);

		if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
			g.setColor(new Color(255, 255, 255));
//			c.setFont(AFont.plain15());
			g.fillRect(b.left, b.top, amountFull, barRectHeight);

		} else {
			// VERTICAL
		}

		if (progressBar.isStringPainted()) {
			paintString(g, b.left, b.top, barRectWidth, barRectHeight, amountFull, b);
		}
	}
}
