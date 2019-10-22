package model.algorithms;

public class BubbleSort extends AbstractSortAlgorithm {

	
	public BubbleSort(int size) {
		super("Bubble Sort", size);
	}

	public void execute() {
		int length = getSize();
        for (int i = 0; i <= length; i++) {
            for (int j = 0; j < length - i -1; j++) {
                int j2 = j + 1;
				if (getValue(j) > getValue(j2)) {
                	swap(j, j2);
                }
            }
        }
        fireFinished();
	}

}
