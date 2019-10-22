package model.algorithms;

/**
 * Represents the insertion-sort.
 * 
 * @author Florin Barbisch
 */
public class InsertionSort extends AbstractSortAlgorithm {

	public InsertionSort(int size) {
		super("Insertion Sort", size);
	}

	public void execute() {
		int length = getSize();
		for(int i = 0; i <= length; i++){
		    int value = getValue(i);
		    int j = i + 1;
		    while(j >= 0 && getValue(j) > value){
		      swap(j + 1, getValue(j));
		      j--;
		    }
		    sorting.updateSingle(j + 1, value, getDelay());
		  }
	}

	@Override
	public String getName() {
		return "Insertion Sort";
	}

}
