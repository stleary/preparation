package preparations;

import java.util.Arrays;
import java.util.Stack;

public class QuickSort {

    /**
     * partitions the list in place into low and high slices, relative to the pivot.
     * The pivot is arbitrarily selected as the last element of the list
     * 
     * @param arr  list to be partitioned
     * @param low  low index of slice of list to be partitioned
     * @param high high index of slice of list to be partitioned
     * @return index of the pivot, after partitioning
     */
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int partitionIndex = -1;

        for (int i = 0; i < high; ++i) {
            if (arr[i] < pivot) {
                partitionIndex += 1;
                int temp = arr[partitionIndex];
                arr[partitionIndex] = arr[i];
                arr[i] = temp;
            }
        }
        partitionIndex += 1;
        int temp = arr[partitionIndex];
        arr[partitionIndex] = arr[high];
        arr[high] = temp;

        return partitionIndex;
    }

    /**
     * Sorts the list quickly and recursively
     * 
     * @param arr  list to be sorted
     * @param low  low slice (inclusive)
     * @param high high slice (inclusive)
     */
    private void quickSortRecursive(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int partitionIndex = partition(arr, low, high);
        this.quickSortRecursive(arr, low, partitionIndex - 1);
        this.quickSortRecursive(arr, partitionIndex + 1, high);
    }

    /**
     * sorts list quickly and iteratively
     * @param arr list to be sorted
     * @param low low index, inclusive
     * @param high high index, inclusive
     */
    private void quickSortIterative(int[] arr, int low, int high) {
    	
    	if (low == high || arr.length == 0) {
    		return;
    	}
    	// entry order: low, pivot-1, pivot+1, high
    	Stack<Integer> stack = new Stack<Integer>();

    	int pivotIndex = this.partition(arr, low, high);
    	if (low < pivotIndex) {
    		stack.push(low);
    		stack.push(pivotIndex-1);
    	}
    	if (high > pivotIndex) {
    		stack.push(pivotIndex+1);
    		stack.push(high);
    	}
    	while (!stack.empty()) {
    		high = stack.pop();
    		low = stack.pop();
    		if (low >= high) {
    			continue;
    		}
    		pivotIndex = partition(arr, low, high);
        	if (low < pivotIndex) {
        		stack.push(low);
        		stack.push(pivotIndex-1);
        	}
        	if (high > pivotIndex) {
        		stack.push(pivotIndex+1);
        		stack.push(high);
        	}
    	}
    }

    /**
     * Runs the quicksort test
     * 
     * @param argv not used
     */
    public static void main(String[] argv) {
        int[] arrRecurse = { 9, 1, 4, 5, 2, 8, 3, 6, 7 };
        int[] arrIter = { 9, 1, 4, 5, 2, 8, 3, 6, 7 };
        System.out.println("Before quicksort: " + Arrays.toString(arrRecurse));
        int low = 0;
        int high = arrRecurse.length - 1;

        QuickSort qs = new QuickSort();

        qs.quickSortRecursive(arrRecurse, low, high);
        System.out.println("After quicksort recursive: " + Arrays.toString(arrRecurse));

        qs.quickSortIterative(arrIter, low, high);
        System.out.println("After quicksort iterative: " + Arrays.toString(arrIter));
    }
}
