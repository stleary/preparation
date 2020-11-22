package preparations;



// Cracking the coding interview 5th edition ch 5 bit manipulation questions
public class BitOps {

	/**
	 * Problem 5.1
	 * Insert the value m into the value n. The value m will overwrite the bits of n,
	 * between bits i (low) and j (high). i and j bits will be separated by at least
	 * the bits in m, so m in will always fit. If the i-j gap is too large for m,
	 * then 0's will be filled on a j-side.
	 * 
	 * @param m the value to be inserted
	 * @param n the value to be updated 
	 * @param i the low bit of the insertion (inclusive, counting from 0)
	 * @param j the high bit of the insertion  (inclusive, counting from 0)
	 * @return the updated n value
	 */
	private int insertValue(int m, int n, int i, int j) {
		// empty left side bits not shown
		// ex: m=13 n=20 i=2 j=5
		// n:       00010100
		//            j  i   
		// m:         1101
		// result: 000110100 = 52
		
		// ex: m=13 n = 71 i=2 j=5
		//     n:  01000111
		//           j  i
		//     m:    1101
		// result: 1110111 = 119
		
		// clear the target bits in n
		for (int k = i; k <= j; ++k) {
			int v = 1 << k;
			v = ~v;
			n &= n;
		}
		// set the target bits from m
		m = m << i;
		n |= m;
		return n;
	}
	
	
	
	
	public static void main(String [] args) {
		
		BitOps bitOps = new BitOps();
		
		// insert value, problem 5.1
		int result = bitOps.insertValue(13, 20, 2, 5);
		System.out.println("n=20, m=13, i=2, j=5 Result: " + result);
		result = bitOps.insertValue(13, 71, 2, 5);
		System.out.println("n=71, m=13, i=2, j=5 Result: " + result);
		
		
		
		
		
		
		
	}

}
