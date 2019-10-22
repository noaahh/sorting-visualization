package model.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import model.AlgorithmListener;

/**
 * Handles the {@link #getDelay() delay} of the sorting-algorithm.
 * @author Florin Barbisch
 */
public abstract class AbstractSortAlgorithm implements SortAlgorithm {

	private List<AlgorithmListener> listeners = new ArrayList<>();
	
	private final String name;
	
	private int arrayAccesses = 0;
	private int[] array;
	private final int min; 
	private final int max; 
	
	/**
	 * To be overridden by subclasses
	 */
	protected final long delay = 2;
	
	public AbstractSortAlgorithm(String name, int size) {
		if (size < 1) throw new IllegalArgumentException("size");
		this.name = name;
		
		array = new int[size];
		for (int i = 0; i < array.length; i++) array[i] = i;
		Random random = new Random();
		for (int i = 0; i < array.length; i++) {
			int swapWithIndex = random.nextInt(array.length - 1);
			int temp = array[i];
			array[i] = array[swapWithIndex];
			array[swapWithIndex] = temp;
		}
		
		this.min = Arrays.stream(array)
				.min()
				.orElseThrow(() -> new IllegalArgumentException("min"));
		this.max = Arrays.stream(array)
				.max()
				.orElseThrow(() -> new IllegalArgumentException("max"));
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getSize() {
		return array.length;
	}
	
	protected final int getValue(int index) {
		arrayAccesses++;
		return array[index];
	}

	protected final void swap(int indexA, int indexB) {
		if (indexA < 0 || indexA >= array.length || indexB < 0 || indexB >= array.length) 
			throw new IndexOutOfBoundsException("index");
		if (indexA == indexB) 
			throw new IllegalArgumentException("indexes indentical");
		
		int bValue = array[indexB];
		array[indexB] = array[indexA];
		array[indexA] = bValue;
		fireSwap(indexA, indexB);
	}

	@Override
	public int[] getValues() {
		return array;
	}

	@Override
	public int getAccesses() {
		return arrayAccesses;
	}

	@Override
	public long getDelay() {
		return delay;
	}

	@Override
	public int getMinValue() {
		return min;
	}

	@Override
	public int getMaxValue() {
		return max;
	}

	@Override
	public void addAlgorithmListener(AlgorithmListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeAlgorithmListener(AlgorithmListener listener) {
		listeners.remove(listener);
	}

	protected void fireSwap(int indexA, int indexB) {
		arrayAccesses++;
		listeners.forEach(l -> l.swaped(indexA, indexB));
	}
	
	protected void fireStarted() {
		listeners.forEach(AlgorithmListener::started);
	}
	
	protected void fireFinished() {
		listeners.forEach(AlgorithmListener::finished);
	}
	
}