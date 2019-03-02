package algorithms;

import ui.Sorting;

/**
 * An Interface which represents a sorting-algorithm and does the sorting-logic
 * in {@link #execute(Sorting)}.
 * 
 * @author Noah Leuenberger
 * @author Florin Barbisch
 */
public interface ISortAlgorithm {

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
	 * Sets the delay before the next "iteration" happens.
	 * 
	 * @param delay in {@code ms}
	 */
	public void setDelay(long delay);

	/**
	 * Sorts the Array provided by the {@link Sorting}.
	 * 
	 * @see Sorting#swap(int, int, long)
	 * @see Sorting#getValue(int)
	 */
	public void execute(Sorting sorting);
}
