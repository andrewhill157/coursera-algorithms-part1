import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;


public class QuickSortTest {
	
	@Test
	public void testQuickSort() {
		QuickSort.list = Arrays.asList(1);
		List<Integer> result1 = Arrays.asList(1);
		QuickSort.quicksort(QuickSort.list,0,QuickSort.list.size()-1);
		assertEquals(result1, QuickSort.list);
		
		QuickSort.list = Arrays.asList(1,2,3,4,5,6,7,8,9);
		List<Integer> result2 = Arrays.asList(1,2,3,4,5,6,7,8,9);
		QuickSort.quicksort(QuickSort.list,0,QuickSort.list.size()-1);
		assertEquals(result2, QuickSort.list);
		
		QuickSort.list = Arrays.asList(4,6,5,3,2,1,7,8,9);
		List<Integer> result3 = Arrays.asList(1,2,3,4,5,6,7,8,9);
		QuickSort.quicksort(QuickSort.list,0,QuickSort.list.size()-1);
		assertEquals(result3, QuickSort.list);
		
		QuickSort.list = Arrays.asList(2,3);
		List<Integer> result4 = Arrays.asList(2,3);
		QuickSort.quicksort(QuickSort.list,0,QuickSort.list.size()-1);
		assertEquals(result4, QuickSort.list);
		
		QuickSort.list = Arrays.asList(3, 8, 2, 5, 1, 4, 7, 6);
		QuickSort.comparisons = 0;
		List<Integer> result5 = Arrays.asList(1,2,3,4,5,6,7,8);
		QuickSort.quicksort(QuickSort.list,0,QuickSort.list.size()-1);
		assertEquals(result5, QuickSort.list);
		assertEquals(QuickSort.comparisons, 15); // test comparison count
	}
	
	@Test
	public void testPartition() {
		
		QuickSort.list = Arrays.asList(1);
		List<Integer> result1 = Arrays.asList(1);
		QuickSort.partition(QuickSort.list,0,QuickSort.list.size()-1);
		assertEquals(result1, QuickSort.list);
		
		QuickSort.list = Arrays.asList(1,2,3,4,5,6,7,8,9);
		List<Integer> result2 = Arrays.asList(1,2,3,4,5,6,7,8,9);
		QuickSort.partition(QuickSort.list,0,QuickSort.list.size()-1);
		assertEquals(result2, QuickSort.list);
		
		QuickSort.list = Arrays.asList(4,6,5,3,2,1,7,8,9);
		List<Integer> result3 = Arrays.asList(1,3,2,4,5,6,7,8,9);
		QuickSort.partition(QuickSort.list,0,QuickSort.list.size()-1);
		assertEquals(result3, QuickSort.list);
		
		QuickSort.list = Arrays.asList(2,3);
		List<Integer> result4 = Arrays.asList(2,3);
		QuickSort.partition(QuickSort.list,0,QuickSort.list.size()-1);
		assertEquals(result4, QuickSort.list);
	}
	
	@Test 
	public void testFileToArray() throws IOException {
		// Test file with integers on each line
		String fileName = "/Users/andrewhill/Dropbox/Coursera/Algorithms Design and Analysis, Part 1 (Stanford)/Code/Programming Assignment 2/src/testFileToArray.txt";
		
		// Expected output 
		List<Integer> fileList = Arrays.asList(1,2,3,4,5,6,7,10,11);
		
		assertEquals(fileList, QuickSort.fileToArray(fileName));
	}
	
	@Test
	public void testMedianOfThree() {
		List<Integer> input1 = Arrays.asList(8,2,4,5,7,1);
		List<Integer> input2 = Arrays.asList(4,10,6,7);
		List<Integer> input3 = Arrays.asList(6,4,5,7,10);
		List<Integer> input4 = Arrays.asList(6,4,5,7,10);
		
		int result1 = QuickSort.medianOfThree(input1, 0, input1.size()-1);
		int result2 = QuickSort.medianOfThree(input2, 0, input2.size()-1);
		int result3 = QuickSort.medianOfThree(input3, 0, input3.size()-1);
		int result4 = QuickSort.medianOfThree(input3, 0, input3.size()-3);
		
		assertEquals(result1, 2);
		assertEquals(result2, 3);
		assertEquals(result3, 0);
		assertEquals(result4, 2);
	}

}
