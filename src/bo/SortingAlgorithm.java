package bo;

import java.util.Random;

public abstract class SortingAlgorithm {
	public float[] values;
	public int size;
	public int iteration;
	
	protected SortingAlgorithm(int size) {
		this.size = size;
		values = new float[size];
		
		setup();
	}
	
	protected void setup() {
		Random random = new Random();
		for (int i = 0; i < values.length; i++) {
			values[i] = random.nextFloat() * size;
		}
	}
	
	public abstract void sort();
}
