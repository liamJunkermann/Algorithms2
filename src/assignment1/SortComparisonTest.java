
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *                              Insertion   Selection   Merge   Quick
 * numbers1000.txt              6ms         4ms         1ms     7ms
 * numbers1000Duplicates        7ms         10ms        1ms     2ms
 * numbers10000                 65ms        27ms        3ms     62ms   
 * numbersNearlyOrdered1000     1ms         8ms         2ms     6ms  
 * numbersReverse1000           11ms        4ms         1ms     6ms
 * numbersSorted1000            1ms         10ms        1ms     6ms    
 */
//-------------------------------------------------------------------------
/**
 * Test class for SortComparison.java
 *
 * @author Liam Junkermann
 * @version HT 2020
 */
@RunWith(JUnit4.class)
public class SortComparisonTest {
    // ~ Constructor ........................................................
    @Test
    public void testConstructor() {
        new SortComparison();
    }

    // ~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty() {
        double[] a = new double[0];
        SortComparison.insertionSort(a);
        SortComparison.selectionSort(a);
        SortComparison.quickSort(a);
        SortComparison.mergeSort(a);
    }

    @Test
    public void testInsertionSort() {
        double unsorted[] = { 2, 8, 5, 1, 4, 9, 7, 3, 0, 6 };
        double sorted[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        assertTrue(Arrays.equals(sorted, SortComparison.insertionSort(unsorted)));
    }

    @Test
    public void testSelectionSort() {
        double unsorted[] = { 2, 8, 5, 1, 4, 9, 7, 3, 0, 6 };
        double sorted[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        assertTrue(Arrays.equals(sorted, SortComparison.selectionSort(unsorted)));
    }

    @Test
    public void testQuickSort() {
        double unsorted[] = { 2, 8, 5, 1, 4, 9, 7, 3, 0, 6 };
        double sorted[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        assertTrue(Arrays.equals(sorted, SortComparison.quickSort(unsorted)));
    }

    @Test
    public void testMergeSort() {
        double unsorted[] = { 2, 8, 5, 1, 4, 9, 7, 3, 0, 6 };
        double sorted[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        assertTrue(Arrays.equals(sorted, SortComparison.mergeSort(unsorted)));
    }

    // ----------------------------------------------------------
    /**
     * Main Method. Use this main method to create the experiments needed to answer
     * the experimental performance questions of this assignment.
     * 
     * @throws FileNotFoundException
     *
     */
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/liam/Documents/SFCode/Algorithms2/src/assignment1/numbersSorted1000.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String string;
        double[] arr = new double[1000];
        int index = 0;
        double num = 0;
        while ((string = reader.readLine()) != null) {
            num = Double.valueOf(string);
            arr[index++] = num;
        }
        reader.close();

        long startTime = System.currentTimeMillis();
        SortComparison.insertionSort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println("Runtime of insertion: " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        SortComparison.selectionSort(arr);
        endTime = System.currentTimeMillis();
        System.out.println("Runtime of selectionSort: " + (endTime - startTime));
        startTime = System.currentTimeMillis();

        SortComparison.mergeSort(arr);
        endTime = System.currentTimeMillis();
        System.out.println("Runtime of Mergesort: " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        SortComparison.quickSort(arr);
        endTime = System.currentTimeMillis();
        System.out.println("Runtime of quickSort: " + (endTime - startTime));

    }

}
