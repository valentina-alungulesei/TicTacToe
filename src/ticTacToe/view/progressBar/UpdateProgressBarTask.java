package ticTacToe.view.progressBar;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import ticTacToe.view.IProgreesBarTaskListener;

public class UpdateProgressBarTask extends Thread {

	private MyProgressBar progressBar;
	private String message;
	
	private List<IProgreesBarTaskListener> listeners;

	public UpdateProgressBarTask(MyProgressBar progressBar, String message) {
		this.progressBar = progressBar;
		this.message = message;
		
		this.listeners = new ArrayList<IProgreesBarTaskListener>();
	}
	
	public void addListener(IProgreesBarTaskListener listener) {
		this.listeners.add(listener);
	}

	public void run() {

		for (int index = 0; index <= 100; index++) {

			final int counter = index;

			SwingUtilities.invokeLater(new Runnable() {

				public void run() {
					progressBar.setValue(counter);
				}
			});
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.err.println("An error has occured with the progress bar!");
				e.printStackTrace();
			}
		}
		progressBar.setStringPainted(true);
		progressBar.setString(message);
		
		for (IProgreesBarTaskListener listener : listeners) {
			listener.taskEnded();
		}
	}
}
