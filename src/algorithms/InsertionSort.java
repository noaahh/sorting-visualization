package algorithms;

import ui.Sorting;

/**
 * Represents the insertion-sort.
 * 
 * @author Florin Barbisch
 */
public class InsertionSort extends AbstractSortAlgorithm {

	@Override
	public void execute(Sorting sorting) {
		for(int i = 1; i < sorting.getValuesSize(); i++){
		    int value = sorting.getValue(i);
		    int j = i - 1;
		    while(j >= 0 && sorting.getValue(j) > value){
		      sorting.updateSingle(j + 1, sorting.getValue(j), getDelay());
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
