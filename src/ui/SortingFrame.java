package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import model.algorithms.SortAlgorithm;

public class SortingFrame extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final SortAlgorithm algorithm;
	private int indexA, indexB = -1;

	public SortingFrame(SortAlgorithm algorithm, Dimension dimension) {
		this.algorithm = algorithm;
		
		setPreferredSize(dimension);
		setBackground(Color.GRAY);
	}

	public void update(int indexA, int indexB) {
		this.indexA = indexA;
		this.indexB = indexB; 
		repaint();
		paintComponent(this.getGraphics());
		
		
		try {
			Thread.sleep(algorithm.getDelay());
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	public void update() {
		update(-1, -1);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D panelGraphics = (Graphics2D) g.create();

		try {
			Map<RenderingHints.Key, Object> renderingHints = new HashMap<>();
			renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			panelGraphics.addRenderingHints(renderingHints);
			panelGraphics.setColor(Color.BLACK);
			panelGraphics.setFont(new Font("Calibri Light", Font.BOLD, 20));
			panelGraphics.drawString("Current algorithm: " + algorithm.getName(), 10, 30);
			panelGraphics.drawString("Current delay:     " + algorithm.getDelay() + "ms", 10, 55);
			panelGraphics.drawString("Array accesses:    " + algorithm.getAccesses(), 10, 80);

			drawBars(panelGraphics);
		} finally {
			panelGraphics.dispose();
		}
	}

	private void drawBars(Graphics2D panelGraphics) { 
		int[] array = algorithm.getValues();
		int barWidth = getWidth() /  algorithm.getSize();
		int bufferedImageWidth = barWidth *  algorithm.getSize();
		int bufferedImageHeight = getHeight();

		if (bufferedImageHeight > 0 && bufferedImageWidth > 0) {
			if (bufferedImageWidth < 256) {
				bufferedImageWidth = 256;
			}

			double maxValue = algorithm.getMaxValue();

			BufferedImage bufferedImage = new BufferedImage(bufferedImageWidth, bufferedImageHeight,
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D bufferedGraphics = null;
			try {
				bufferedGraphics = bufferedImage.createGraphics();

				for (int x = 0; x <  algorithm.getSize(); x++) {
					double currentValue = array[x];
					double percentOfMax = currentValue / maxValue;
					double heightPercentOfPanel = percentOfMax * (HEIGHT / WIDTH);
					int height = (int) (heightPercentOfPanel * (double) getHeight());
					int xBegin = x + (barWidth - 1) * x;
					int yBegin = getHeight() - height;

					bufferedGraphics.setColor(new Color(255, 255, 255));
					bufferedGraphics.fillRect(xBegin, yBegin, barWidth, height);
				}
			} finally {
				if (bufferedGraphics != null) {
					bufferedGraphics.dispose();
				}
			}

			panelGraphics.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), 0, 0, bufferedImage.getWidth(),
					bufferedImage.getHeight(), null);
		}
	}
}
