package business;

import java.awt.Dimension;

import javax.swing.SwingWorker;

import model.AlgorithmListener;
import model.algorithms.SortAlgorithm;
import ui.SortingFrame;
import ui.Window;

public class Controller {
	public static final int WINDOW_HEIGHT = 720;
	public static final int WINDOW_WIDTH = 500;
	
	private final Window frmApplication;
	private final SortingFrame sorting;
	private final SortAlgorithm sortAlgorithm;

	public Controller(SortAlgorithm sortAlgorithm) {
		frmApplication = new Window();
		sorting = new SortingFrame(sortAlgorithm, new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		frmApplication.setResizable(false);
		sorting.setVisible(true);
		frmApplication.getContentPane().add(sorting);
		this.sortAlgorithm = sortAlgorithm;
		this.sortAlgorithm.addAlgorithmListener(al);
		run();
	}
	
	AlgorithmListener al = new AlgorithmListener() {
		
		@Override
		public void swaped(int indexA, int indexB) {
			sorting.update(indexA, indexB);
		}
		
		@Override
		public void started() {
			sorting.update();
		}
		
		@Override
		public void finished() {
			sorting.update();
		}
	};
	
	public void run() {
		SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {

				sortAlgorithm.execute();
				
				Thread.sleep(1000);
				return null;
			}
		};

		swingWorker.execute();
	}
}
