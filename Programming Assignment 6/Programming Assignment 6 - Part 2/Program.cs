using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Programming_Assignment_6___Part_2
{
    /// <summary>
    /// This program maintains easy access of the median value of a set of numbers as they are added one by one
    /// to the set via the median maintainence algorithm. 
    /// </summary>
    class Program
    {
        // Two Sorted-Sets (C# has no Heap stucture) to hold upper and lower half of values
        static SortedSet<int> hLow = new SortedSet<int>();
        static SortedSet<int> hHigh = new SortedSet<int>();
        
        /// <summary>
        /// Adds the given number the appropriate heap of values - hLow or hHigh. Heaps are rebalanced if difference
        /// in size after addition is greater than one. 
        /// </summary>
        /// <param name="number"> Value to be added to hLow or hHigh. Added to hLow if less than max of hLow and to hHigh otherwise.</param>
        private static void addToHeap(int number)
        {
            // Get the current middle values (min of upper heap and max of lower heap)
            int low = hLow.Max;
            int high = hHigh.Min;

            // Add number to the appropriate heap
            if (number < low)
                hLow.Add(number);
            else
                hHigh.Add(number);

            // Rebalance heap, so difference in count for each is no greater than one
            rebalanceHeap();
        }

        /// <summary>
        /// Rebalances heaps if difference in size after addition is greater than one. Rebalancing occurs by moving
        /// the min of the heap of higher values or the max of the heap of lower values to the opposing heap. 
        /// </summary>
        private static void rebalanceHeap()
        {
            // Get the current middle values (min of upper heap and max of lower heap)
            int low = hLow.Min;
            int high = hHigh.Min;

            // If necessary, take the min/max value from the longer heap and move it to the other heap
            // This ensures the count for each heap is never greater than one
            if (hHigh.Count - hLow.Count >= 2)
            {
                hLow.Add(high);
                hHigh.Remove(high);
            }
            else if (hHigh.Count - hLow.Count <= -2)
            {
                hHigh.Add(low);
                hLow.Remove(low);
            }
        }

        /// <summary>
        /// Returns the cummulative median value of all elements in the heaps of upper and lower values. 
        /// </summary>
        /// <returns>k/2 element if k is even, (k+1)/2 element if k is odd. k is the total number of elements in the two heaps.</returns>
        private static int getMedianValue()
        {
            // Return the k/2 element if even number of elements, (k+1)/2 element if odd number of elements
            // Heaps are always balanced so same length if even number of elements and different by one if 
            // odd number of elements. 
            if ((hLow.Count + hHigh.Count) % 2 == 0 || hLow.Count > hHigh.Count)
                return hLow.Max;
            else
                return hHigh.Min;
        }

        static void Main(string[] args)
        {
            // Get all lines from the text file provided
            String filePath = @"C:\Users\ahill\Desktop\Programming Assignment 6\Programming Assignment 6\Median.txt";
            string[] lines = System.IO.File.ReadAllLines(filePath);

            List<int> medians = new List<int>();

            // Add elements to the two heaps according to the median maintenance algorithm (O(log n))
            foreach (String s in lines)
            {
                int number = Convert.ToInt32(s);

                addToHeap(number);
                int currentMedian = getMedianValue();

                // Maintain a running list of median values as numbers are added one by one from the file
                medians.Add(currentMedian);
            }

            // Write the last four digits of the sum of all median values to the console
            Console.WriteLine(medians.Sum() % 10000);
            Console.ReadKey();
        }
    }
}
