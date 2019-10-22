package model.algorithms;

import model.AlgorithmListener;
import ui.SortingFrame;

/**
 * An Interface which represents a sorting-algorithm and does the sorting-logic
 * in {@link #execute(SortingFrame)}.
 * 
 * @author Noah Leuenberger
 * @author Florin Barbisch
 */
public interface SortAlgorithm {
	
	/**
	 * Returns the name of this sorting-algorithm.
	 */
	public String getName();

	/**
	 * Gets the delay before the next "iteration" happens.
	 * 
	 * @return the delay in {@code ms}
	 */
	public long getDelay();

	/**
	 * Sorts the Array provided by the {@link SortingFrame}.
	 * 
	 * @see SortingFrame#swap(int, int, long)
	 * @see SortingFrame#getValue(int)
	 */
	public void execute();
	
	public int getSize();
	
	public int[] getValues();
	
	public int getAccesses();
	
	public int getMinValue();
	
	public int getMaxValue();

	public void addAlgorithmListener(AlgorithmListener listener);

	public void removeAlgorithmListener(AlgorithmListener listener);

}
