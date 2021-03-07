
// -------------------------------------------------------------------------

/**
 * This class contains static methods that implementing sorting of an array of
 * numbers using different sort algorithms.
 *
 * @author Liam Junkermann
 * @version HT 2020
 */

class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort. This method is static, thus it
     * can be called as SortComparison.sort(a)
     * 
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double[] insertionSort(double a[]) {
        for (int i = 0; i < a.length; i++) {
            double curr = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > curr)
                a[j + 1] = a[j--];
            a[j + 1] = curr;
        }
        return a;
    }// end insertionsort

    /**
     * Sorts an array of doubles using Selection Sort. This method is static, thus
     * it can be called as SortComparison.sort(a)
     * 
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double[] selectionSort(double a[]) {
        for (int i = 0; i < a.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min])
                    min = j;
            }
            double temp = a[min];
            a[min] = a[i];
            a[i] = temp;
        }
        return a;
    }// end selectionsort

    /**
     * Sorts an array of doubles using Quick Sort. This method is static, thus it
     * can be called as SortComparison.sort(a)
     * 
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double[] quickSort(double a[]) {
        return quickSortRecursive(a, 0, a.length - 1);
    }// end quicksort

    private static double[] quickSortRecursive(double a[], int lo, int hi) {
        if (lo < hi) {
            int split = split(a, lo, hi);
            quickSortRecursive(a, lo, split - 1);
            quickSortRecursive(a, split + 1, hi);
        }
        return a;
    }

    private static int split(double a[], int lo, int hi) {
        double pivot = a[hi];
        int index = lo - 1;
        for (int i = lo; i < hi; i++) {
            if (a[i] < pivot) {
                double temp = a[++index];
                a[index] = a[i];
                a[i] = temp;
            }
        }
        double temp = a[index + 1];
        a[index + 1] = a[hi];
        a[hi] = temp;
        return index + 1;
    }

    /**
     * Sorts an array of doubles using Merge Sort. This method is static, thus it
     * can be called as SortComparison.sort(a)
     * 
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort. This
     * method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted
     *         order.
     */

    static double[] mergeSort(double a[]) {
        return mergeSortRecursive(a, 0, a.length - 1);
    }// end mergesort

    private static double[] mergeSortRecursive(double a[], int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortRecursive(a, left, mid);
            mergeSortRecursive(a, mid + 1, right);
            merge(a, left, mid, right);
        }
        return a;
    }

    private static void merge(double a[], int left, int mid, int right) {
        int leftLen = mid - left + 1;
        int rightLen = right - mid;
        double leftArr[] = new double[leftLen];
        double rightArr[] = new double[rightLen];

        for (int i = 0; i < leftLen; i++) {
            leftArr[i] = a[left + i];
        }
        for (int i = 0; i < rightLen; i++) {
            rightArr[i] = a[mid + 1 + i];
        }
        int leftI = 0;
        int rightI = 0;
        int i = left;
        while (leftI < leftLen && rightI < rightLen) {
            if (leftArr[leftI] <= rightArr[rightI]) {
                a[i] = leftArr[leftI++];
            } else {
                a[i] = rightArr[rightI++];
            }
            i++;
        }
        while (leftI < leftLen) {
            a[i++] = leftArr[leftI++];
        }
        while (rightI < rightLen) {
            a[i++] = rightArr[rightI++];
        }
    }

    public static void main(String[] args) {

        // todo: do experiments as per assignment instructions
    }

}// end class
