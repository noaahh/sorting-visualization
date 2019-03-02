package algorithms;

/**
 * Handles the {@link #getDelay() delay} of the sorting-algorithm.
 * @author Florin Barbisch
 */
public abstract class AbstractSortAlgorithm implements ISortAlgorithm {

	/**
	 * To be overridden by subclasses
	 */
	protected long delay = 2;

	@Override
	public long getDelay() {
		return delay;
	}

	@Override
	public void setDelay(long delay) {
		this.delay = delay;
	}

}