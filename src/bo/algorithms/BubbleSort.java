package bo.algorithms;

import bo.SortingAlgorithm;

public class BubbleSort extends SortingAlgorithm {

	public BubbleSort(int size) {
		super(size);
	}

	@Override
	public void sort() {
		for (int j = 0; j < values.length - iteration - 1; j++) {
			float a = values[j];
			float b = values[j + 1];

			if (a > b) {
				swap(values, j, j + 1);
			}
		}
	}

	private void swap(float[] arr, int a, int b) {
		float temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

}
