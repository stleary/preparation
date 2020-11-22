package preparations;

import java.util.Arrays;

public class RadixSort {

	/**
	 * Just getting the largest number in the list
	 * 
	 * @param arr list to be checked
	 * @return largest number in list
	 */
	private int getMax(int[] arr) {
		int max = -1;
		for (int i = 0; i < arr.length; ++i) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}

	private void countSort(int[] arr, int n, int exp) {

		if (arr.length == 0 || n == 0) {
			return;
		}

		// count will initially holds how many times each number appears
		// ex: count[3] = 1 means there is a single 3 in the list (or whatever digit is
		// selected by the exp). Since there can only be 10 digits, make that the size.
		int[] count = new int[10];
		Arrays.fill(count,  0);

		// store how many times each number occurs in count
		for (int a : arr) {
			count[(a / exp) % 10] += 1;
		}

		// convert count into the position of each number in the output
		for (int i = 1; i < 10; ++i) {
			count[i] += count[i - 1];
		}
		
		// set up the output array
		//      	0  1    2    3    4    5   6
		// arr: 	5, 243, 123, 168, 205, 60, 1
		// 
		//          0  1  2  3  4  5  6  7  8  9  
		// count	1  2  2  4  4  6  6  6  7  7  
		// 
		// i=6  exp=1 	arr[i]=1   arrexp=(arr[i]/exp)%10:1 	arrcount=count[(arr[i]/exp)%10]:2	output[2-1]=1	
		// output[1] = 1
		// count[arrexp]=1
		int [] output = new int[n];
		for (int i = n-1; i >= 0; --i) {
			int arrexp = (arr[i] / exp) % 10;
			int arrcount = count[arrexp];
			output[arrcount - 1] = arr[i];
			count[arrexp] -= 1;
		}
		
		// update arr from output
		for (int i = 0; i < n; ++i) {
			arr[i] = output[i];
		}

//		// generate the sorted list
//
//		// ex: 0 1 2 3 (keys or index)
//		// count 0 2 3 4
//		// result: arr 1 1 2 3
//
//		// where to output the next number in arr
//		int indx = 0;
//		for (int i = 0; i < a_max; ++i) {
//			// count[i] - indx_start = how many of this number will need to output
//			int indx_start = indx;
//			while (count[i] > indx_start) {
//				arr[indx] = i;
//				indx += 1;
//				count[i] -= 1;
//			}
//		}
	}

	/**
	 * Radix sort is similar to count sort, but you first have to group the numbers
	 * by significant digit
	 * 
	 * @param arr list to be sorted in place
	 */
	private void radixSort(int[] arr) {
		int a_max = this.getMax(arr);

		for (int exp = 1; (a_max / exp) > 0; exp *= 10) {
			countSort(arr, arr.length, exp);
		}
	}

	public static void main(String[] args) {
		int[] arr = { 5, 243, 123, 168, 205, 60, 1 };
		System.out.println("Before radix sort: " + Arrays.toString(arr));
		RadixSort rs = new RadixSort();
		rs.radixSort(arr);
		System.out.println("After radix sort: " + Arrays.toString(arr));

	}

}
