package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import bo.SortingAlgorithm;

public class Visualization extends JPanel {
	private static final long serialVersionUID = 7214753443467211839L;
	public int height, width;
	private SortingAlgorithm algorithm;

	public Visualization(SortingAlgorithm algorithm, int width, int height) {
		this.width = width;
		this.height = height;

		this.algorithm = algorithm;
	}

	public void draw() {

		for (algorithm.iteration = 0; algorithm.iteration < algorithm.values.length; algorithm.iteration++) {
			algorithm.sort();

			try {
				Thread.sleep(10);
			} catch (Exception e) {
				System.err.println("There is a problem...");
			} finally {
				paint(this.getGraphics());
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		for (int x = 0; x < algorithm.values.length; x++) {
			int y = Math.round(algorithm.values[x]);

			g.setColor(Color.BLACK);
			g.clearRect(x, height, 1, -y);
			g.drawLine(x, 0, x, height - y);
		}
	}
}
