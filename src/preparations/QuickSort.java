package preparations;

import java.util.ArrayList;
import java.util.Arrays;


public class QuickSort {

	/**
	 * partitions the list in place into low and high slices, relative to the pivot.
	 * The pivot is arbitrarily selected as the last element of the list
	 * @param arr list to be partitioned
	 * @param low low index of slice of list to be partitioned
	 * @param high high index of slice of list to be partitioned
	 * @return index of the pivot, after partitioning
	 */
	private int partition(int [] arr, int low, int high) {
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
	 * @param arr list to be sorted
	 * @param low low slice (inclusive)
	 * @param high high slice (inclusive)
	 */
	private void quickSortRecursive(int [] arr, int low, int high) {
		if (low >= high) {
			return;
		}
		int partitionIndex = partition(arr, low, high);
		this.quickSortRecursive(arr, low, partitionIndex-1);
		this.quickSortRecursive(arr, partitionIndex+1, high);
	}
	
	/**
	 * Runs the quicksort test
	 * @param argv not used
	 */
	public static void main(String [] argv) {
		int [] arr = {9, 1, 4, 5, 2, 8, 3, 6, 7};
		System.out.println("Before quicksort: " + Arrays.toString(arr));
		int low = 0;
		int high = arr.length - 1;
		QuickSort qs = new QuickSort();
		qs.quickSortRecursive(arr, low, high);
		System.out.println("After quicksort: " + Arrays.toString(arr));
	}
}
