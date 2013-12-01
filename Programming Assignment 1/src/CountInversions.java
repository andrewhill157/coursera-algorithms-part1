import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountInversions {
	/**
	 * Global count of the number of inversions
	 */
	public static long inversions = 0;
	
	/**
	 * Returns a sorted copy of the input array and sets inversions (global) to the number of inversions 
	 * Inversions: number of instances where a[i] > a[j] for i < j
	 * 
	 * inversions (global) is incremented when split inversions are detected
	 * 
	 * Behavior is unspecified for arrays with less than one element
	 * 
	 * @param a - array of integers containing at least one element
	 * @return sorted array containing the elements of a
	 */
	public static int[] sortAndCountInversions(int[] a) {
		
		if(a.length == 1) { // Base-case, array has one element, return array (already sorted)
			return a;
		} else { 
			// Recursively sort each half of the array seperately and then merge the results, keeping track of inversions
			int[] firstHalf = sortAndCountInversions(Arrays.copyOfRange(a,0, a.length/2)); // sort first half
			int[] secondHalf = sortAndCountInversions(Arrays.copyOfRange(a, (a.length/2), a.length)); // sort second half
			return mergeAndCountSplitInversions(firstHalf, secondHalf); // merge results and count inversions
		}
	}
	
	/**
	 * Merges the arrays a and b, such that the resulting array is in sorted ascending order. 
	 * inversions (global) is incremented when split inversions are detected
	 * Split inversions: number of instances where a[i] > a[j] for i <= n/2 and j > n/2, where n is the length of a
	 * 
	 * Behavior unspecified for arrays with less than one element
	 * 
	 * @param a - array of integers sorted in ascending order containing at least one element
	 * @param b - array of integers sorted in ascending order containing at least one element
	 * @return array of elements containing the combined elements of a and b in sorted ascending order
	 */
	public static int[] mergeAndCountSplitInversions(int[] a, int[] b) {
		int j = 0; // index for a
		int k = 0; // index for b
		
		// array to store merged a and b arrays
		int[] result = new int[a.length+b.length];
		
		// merge the arrays element by element, keeping track of split inversions
		for(int i = 0; i < result.length; i++) {
			if(j < a.length && k < b.length) { // there are remaining elements in both a and b
				
				// transfer smaller of a[j], b[k] to results
				if(a[j] <= b[k]) {
					result[i] = a[j];
					j++;
				} else {
					result[i] = b[k];
					k++;
					
					// inversion; add number of remaining elements of a to inversions
					inversions += a.length - j; 
				}
				
			} else if(j < a.length) { // there are remaining elements only in a
				result[i] = a[j];
				j++;
			} else if(k < b.length) { // there are remaining elements only in b
				result[i] = b[k];
				k++;
			}
		}
		return result;
	}
	
	
	/**
	 * Populates array with integers specified individually in each line of a text file.
	 * 
	 * Behavior is unspecified for:
	 * 1. Invalid file paths
	 * 2. Non-.txt files
	 * 3. Files with no entries
	 * 4. Files with more than one integer per line of the file
	 * 5. Files with non-integer entries
	 * 
	 * @param filePath - string specifying the full path to a file containing one integer per line
	 * @return array of integers, as specified by each line in the file from top to bottom
	 * @throws IOException 
	 */
	public static int[] fileToArray(String fileName) throws IOException {
		
		// Open necessary objects to read file contents
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		// ArrayList to store the file contents
		List<Integer> entries = new ArrayList<Integer>();
		
		// Store each line of the file in ArrayList after converting to an int
		String line = bufferedReader.readLine();
		while(line != null) {
			entries.add(Integer.parseInt(line));
			line = bufferedReader.readLine(); // get the next line
		} 
		
		bufferedReader.close(); // clean up
		return arrayListToArray(entries);
	}
	
	/**
	 * Produces an array of ints from an ArrayList of Integers
	 * 
	 * @param a - ArrayList of Integers containing at least one element
	 * @return an array of ints with all the elements of a
	 */
	private static int[] arrayListToArray(List<Integer> a) {
		int[] result = new int[a.size()];
		
		// Transfer each element of a into the new array
		for(int i = 0; i < a.size(); i++) {
			result[i] = a.get(i);
		} 
		return result;
	}
	
	/**
	 * Main method of the class. Counts inversions found in the file provided for the assignment.
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		inversions = 0;
		String fileName = "/Users/andrewhill/Dropbox/Coursera/Algorithms Design and Analysis, Part 1 (Stanford)/Code/Programming Assignment1/src/assignmentFile.txt";
		int[] numbers = fileToArray(fileName);
		int[] sortedNumbers = sortAndCountInversions(numbers);
		System.out.println(inversions);
	}
}
