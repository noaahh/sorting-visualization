package ui;

import java.awt.EventQueue;

import javax.swing.SwingWorker;

import algorithms.BubbleSort;
import algorithms.ISortAlgorithm;
import algorithms.InsertionSort;

public class Application {

	private final Window frmApplication;

	private final Sorting sorting;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new Application().frmApplication.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Application() {
		frmApplication = new Window();
		sorting = new Sorting(new BubbleSort());
		initialize();
	}

	private void shuffleAndWait() {
		sorting.shuffle();
		sleep(1000);
	}

	private void initialize() {
		frmApplication.getContentPane().add(sorting);

		SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				sleep(250);
				shuffleAndWait();

				sorting.run();
				sorting.highlightArray();
				sleep(1000);
				return null;
			}
		};

		swingWorker.execute();
	}
	
	private static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
}
