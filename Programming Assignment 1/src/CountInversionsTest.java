import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;


public class CountInversionsTest {

	@Test
	public void testMergeAndCountSplitInversions() {
		// Single element arrays, no inversion
		int[] a1 = {1};
		int[] b1 = {2};
		
		// Multiple element arrays, no inversion
		int[] a2 = {1,2,3};
		int[] b2 = {4,5,6};
		
		// Multiple element arrays, inversion
		int[] a3 = {4,5,6};
		int[] b3 = {1,2,3};
		
		// Correct results for corresponding inputs
		int[] result1 = {1,2};
		int[] result2 = {1,2,3,4,5,6};
		int[] result3 = {1,2,3,4,5,6};
		
		CountInversions.inversions = 0; // reset global count
		
		assertArrayEquals(result1, CountInversions.mergeAndCountSplitInversions(a1, b1));
		assertEquals(0, CountInversions.inversions);
		
		assertArrayEquals(result2, CountInversions.mergeAndCountSplitInversions(a2, b2));
		assertEquals(0, CountInversions.inversions);
		
		assertArrayEquals(result3, CountInversions.mergeAndCountSplitInversions(a3, b3));
		assertEquals(9, CountInversions.inversions);
	}

	@Test
	public void testSortAndCountInversions() {
		int[] input1 = {1}; // one element
		int[] input2 = {1, 2}; // multiple elements, no inversions 
		int[] input3 = {1, 2, 3, 5, 4}; // multiple elements, 1 inversions
		int[] input4 = {5, 4, 3, 2, 1}; // multiple elements, reverse order
		
		// Correct results for corresponding inputs
		int[] result1 = {1};
		int[] result2 = {1,2};
		int[] result3 = {1,2,3,4,5};
		int[] result4 = {1,2,3,4,5};
		
		CountInversions.inversions = 0; // reset global count
		
		assertArrayEquals(result1, CountInversions.sortAndCountInversions(input1));
		assertEquals(0, CountInversions.inversions);
		
		assertArrayEquals(result2, CountInversions.sortAndCountInversions(input2));
		assertEquals(0, CountInversions.inversions);
		
		assertArrayEquals(result3, CountInversions.sortAndCountInversions(input3));
		assertEquals(1, CountInversions.inversions);
		
		CountInversions.inversions = 0; // reset global count
		
		assertArrayEquals(result4, CountInversions.sortAndCountInversions(input4));
		assertEquals(10, CountInversions.inversions);
	}
	
	@Test 
	public void testFileToArray() throws IOException {
		// Test file with integers on eahc line
		String fileName = "/Users/andrewhill/Dropbox/Coursera/Algorithms Design and Analysis, Part 1 (Stanford)/Code/Programming Assignment1/src/testFileToArray.txt";
		
		// Expected output 
		int[] fileArray = {1,2,3,4,5,6,7,10,11};
		
		assertArrayEquals(fileArray, CountInversions.fileToArray(fileName));
	}
}
