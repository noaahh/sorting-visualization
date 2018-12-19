package bo.algorithms;

import bo.SortingAlgorithm;

public class InsertionSort extends SortingAlgorithm {
	public InsertionSort(int size) {
		super(size);
	}
	
	@Override 
	public void sort() {
		float key = values[iteration];
		int j = iteration - 1;
		
		while (j >= 0 && values[j] > key) {
			values[j + 1] = values[j];
			j--;
		}
		values[j + 1] = key;
	}
}
