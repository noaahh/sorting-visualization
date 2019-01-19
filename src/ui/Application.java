package ui;

import java.awt.EventQueue;

import javax.swing.SwingWorker;

import algorithms.BubbleSort;
import algorithms.ISortAlgorithm;

public class Application {

	private static Window frmApplication = new Window();

	private final Sorting sorting;
	private final ISortAlgorithm algorithm;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					Application.frmApplication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Application() {
		sorting = new Sorting();
		algorithm = new BubbleSort();

		initialize();
	}

	private void longSleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	private void shuffleAndWait() {
		sorting.shuffle();
		longSleep();
	}

	private void initialize() {
		frmApplication = new Window();
		frmApplication.getContentPane().add(sorting);

		shuffleAndWait();
		sorting.setName(algorithm.getName());
		sorting.setAlgorithm(algorithm);
		longSleep();

		SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				try {
					Thread.sleep(250);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				shuffleAndWait();

				sorting.setName(algorithm.getName());
				sorting.setAlgorithm(algorithm);

				algorithm.execute(sorting);
				sorting.highlightArray();
				longSleep();
				return null;
			}
		};

		swingWorker.execute();
	}

	public static Window getwindow() {
		return frmApplication;
	}
}
