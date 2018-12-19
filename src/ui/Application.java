package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import bo.algorithms.InsertionSort;

public class Application {

	private JFrame frmApplication;
	Visualization visualization;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frmApplication.setVisible(true);

					// Start window
					Runnable runableSort = new Runnable() {
						@Override
						public void run() {
							window.visualization.draw();
						}
					};
					Thread windowSort = new Thread(runableSort);
					windowSort.start();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Application() {
		initialize();
	}

	private void initialize() {
		int width = 1000, height = 1000;

		frmApplication = new JFrame();
		frmApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmApplication.setSize(width, height);
		frmApplication.setTitle("Visualization of Sorting Algorithms");
		frmApplication.setResizable(true);

		visualization = new Visualization(new InsertionSort(width), width, height);
		frmApplication.getContentPane().add(visualization);
	}

}
