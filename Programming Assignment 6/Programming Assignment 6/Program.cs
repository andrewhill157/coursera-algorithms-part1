using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Programming_Assignment_6
{
    /// <summary>
    /// This program is an illustration of the two-sum problem described in the course, where we want to determine whether 
    /// or not a target value can be represented by the sum of any two unique elements in a given list.
    /// 
    /// For some reason, this implementation is quite slow (probably still better than brute force). This is likely a product of 
    /// two things:
    /// 1. The heap data structure approach described in lecture may not be as good. It is possible that the dictionary used in C# 
    ///     is creating lots of collisions and using chaining due to a poorly suited hash function. 
    ///     This will cause O(1) access to elements to break down, degrading behavior to closer to O(n) - causes overal O(n-squared algorithm). 
    /// 2. The way we are looping over the range of values may be inefficient. People in the forums have mentioned tricks for reducing the number
    ///     of iterations required for this loop. It is possible that this is degrading performance for the task significantly. 
    /// </summary>
    class Program
    {
        static Dictionary<long, long> dictionary;
        static List<long> list;

        /// <summary>
        /// Adds values from the provided file to the dictionary and list of values. The file should have 
        /// exactly one integer per line with no additional characters.
        /// </summary>
        /// <param name="filePath">Verbatum string of the full path to the file</param>
        private static void loadFromFile(string filePath)
        {
            dictionary = new Dictionary<long, long>();
            list = new List<long>();
           
            string[] lines = System.IO.File.ReadAllLines(filePath);
           
            // Add items to the list and dictionary if they are unique
            foreach (string s in lines)
            {
                long number = Convert.ToInt64(s);
                if (!dictionary.ContainsKey(number))
                {
                    dictionary.Add(number, number);
                    list.Add(number);
                }
            }
        }
        
        /// <summary>
        /// Determines whether there are two unique elements in the list that add up to the target value.
        /// </summary>
        /// <param name="target">The value used as the target value (sum of two unique elements in list).</param>
        /// <returns>Returns one if the target value is a sum of two unique elements in a list of numbers. Returns zero otherwise.</returns>
        public static int twoSums(int target)
        {
            foreach (long n in list)
            {
                long complement = target - n;

                // Spec states that only unique values can be added to one another
                // If the  complement is the number itself, there is not a unique element in the list.  
                if (complement == n) 
                    return 0;

                if (dictionary.ContainsKey(complement))
                    return 1;
            }
            return 0;
        }

        static void Main(string[] args)
        {
            // Load data from provided file
            try
            {
                string filePath = @"C:\Users\ahill\Desktop\Programming Assignment 6\Programming Assignment 6\algo1-programming_prob-2sum.txt";
                loadFromFile(filePath);
            }
            catch (Exception ex) { Console.Write("Error loading file - " + ex.Message); }

            // Running sum of numbers that can be represented by a sum of two unique elements in the list
            int total = 0;

            // Spec asks us to determine how many of the numbers from -10000 and 10000 are the sum of two unique
            // elements in the provided list of integers. 
            for (int i = -10000; i <= 10000; i++)
            {
                Console.WriteLine("Iteration " + i);
                total += twoSums(i);
            }
            Console.WriteLine(total);
            
        }
    }
}
