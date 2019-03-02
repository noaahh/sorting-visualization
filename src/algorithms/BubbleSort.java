package algorithms;

import ui.Sorting;

public class BubbleSort extends AbstractSortAlgorithm {
	
	@Override
	public void execute(Sorting sorting) {
		int length = sorting.getValuesSize();
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (sorting.getValue(j) > sorting.getValue(j + 1)) {
                	sorting.swap(j, j + 1, getDelay());
                }
            }
        }
	}

	@Override
	public String getName() {
		return "Bubble Sort";
	}

}
