package algorithms;

import ui.Sorting;

public class BubbleSort implements ISortAlgorithm {
	
	private long delay = 2;
	
	@Override
	public void execute(Sorting sorting) {
		int length = sorting.getValuesSize();
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (sorting.getValue(j) > sorting.getValue(j + 1)) {
                	sorting.swap(j, j + 1, getDelay(), true);
                }
            }
        }
	}

	@Override
	public String getName() {
		return "Bubble Sort";
	}

	@Override
	public long getDelay() {
		return this.delay;
	}

	@Override
	public void setDelay(long delay) {
		this.delay = delay;
	}

}
