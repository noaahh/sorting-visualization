package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JPanel;

import algorithms.ISortAlgorithm;

public class Sorting extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final double BAR_HEIGHT_PERCENT = 512.0 / 720.0;
	private static final int BAR_WIDTH = 5;
	public static final int WINDOW_HEIGHT = 720;
	public static final int WINDOW_WIDTH = 500;
	private static final int NUM_BARS = WINDOW_WIDTH / BAR_WIDTH;

	private final ISortAlgorithm algorithm;

	private int arrayAccesses = 0;
	private final int[] array;

	public Sorting(ISortAlgorithm algorithm) {
		setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

		this.algorithm = algorithm;
		setBackground(Color.GRAY);
		array = new int[NUM_BARS];

		for (int i = 0; i < NUM_BARS; i++) {
			array[i] = i;
		}
	}

	private void update() {
		repaint();

		try {
			Thread.sleep(algorithm.getDelay());
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public void swap(int firstIndex, int secondIndex, long millisecondDelay) {
		swap(firstIndex, secondIndex, millisecondDelay, true);
	}
	
	private void swap(int firstIndex, int secondIndex, long millisecondDelay, boolean isAccess) {
		int temp = array[firstIndex];
		array[firstIndex] = array[secondIndex];
		array[secondIndex] = temp;
		if (isAccess) {
			arrayAccesses++;
		}

		update();
	}

	public void updateSingle(int index, int value, long millisecondDelay) {
		updateSingle(index, value, millisecondDelay, true);
	}
	
	private void updateSingle(int index, int value, long millisecondDelay, boolean isAccess) {
		array[index] = value;
		if (isAccess) {
			arrayAccesses++;
		}
		update();
	}

	public void shuffle() {
		arrayAccesses = 0;
		Random rng = new Random();
		for (int i = 0; i < array.length; i++) {
			int swapWithIndex = rng.nextInt(array.length - 1);
			swap(i, swapWithIndex, 5, false);
		}
	}

	public void highlightArray() {
		for (int i = 0; i < getValuesSize(); i++) {
			updateSingle(i, getValue(i, false), 5, false);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D panelGraphics = (Graphics2D) g.create();

		try {
			Map<RenderingHints.Key, Object> renderingHints = new HashMap<>();
			renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			panelGraphics.addRenderingHints(renderingHints);
			panelGraphics.setColor(Color.WHITE);
			panelGraphics.setFont(new Font("Calibri Light", Font.BOLD, 20));
			panelGraphics.drawString("Current algorithm: " + algorithm.getName(), 10, 30);
			panelGraphics.drawString("Current delay:     " + algorithm.getDelay() + "ms", 10, 55);
			panelGraphics.drawString("Array accesses:    " + arrayAccesses, 10, 80);

			drawBars(panelGraphics);
		} finally {
			panelGraphics.dispose();
		}
	}

	private void drawBars(Graphics2D panelGraphics) {
		int barWidth = getWidth() / NUM_BARS;
		int bufferedImageWidth = barWidth * NUM_BARS;
		int bufferedImageHeight = getHeight();

		if (bufferedImageHeight > 0 && bufferedImageWidth > 0) {
			if (bufferedImageWidth < 256) {
				bufferedImageWidth = 256;
			}

			double maxValue = getMaxValue();

			BufferedImage bufferedImage = new BufferedImage(bufferedImageWidth, bufferedImageHeight,
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D bufferedGraphics = null;
			try {
				bufferedGraphics = bufferedImage.createGraphics();

				for (int x = 0; x < NUM_BARS; x++) {
					double currentValue = getValue(x, false);
					double percentOfMax = currentValue / maxValue;
					double heightPercentOfPanel = percentOfMax * BAR_HEIGHT_PERCENT;
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

	public int getMaxValue() {
		return Arrays.stream(this.array).max().orElse(Integer.MIN_VALUE);
	}
	
	/**
	 * Returns the value of the array at the given.
	 */
	public int getValue(int index) {
		return getValue(index, true);
	}
	
	private int getValue(int index, boolean isAccess) {
		if (isAccess) arrayAccesses++;
		return this.array[index];
	}

	public int getValuesSize() {
		return this.array.length;
	}
	
	public ISortAlgorithm getAlgorithm() {
		return algorithm;
	}
	
	public void run() {
		algorithm.execute(this);
	}
	
}
