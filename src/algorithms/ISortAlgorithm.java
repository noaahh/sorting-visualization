package algorithms;

import ui.Sorting;

public interface ISortAlgorithm {
	public String getName();

	public long getDelay();

	public void setDelay(long delay);

	public void execute(Sorting sorting);
}
