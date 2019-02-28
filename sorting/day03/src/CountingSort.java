public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: O(N)
     * 
     * k: maximum element in array A
     */
    public static void countingSort(int[] A) {
        int min = 1000000000; // Really large number
        int max = -1000000000; // Really small number

        for (int i = 0; i < A.length; i++) {
            if (A[i] > max) { // Get max number to avoid overflows
                max = A[i];
            }
            if (A[i] < min) { // Get minimum number to handle negatives
                min = A[i];
            }
        }

        int[] count = new int[(max - min + 1)];
        int[] output = new int[A.length];

        for (int i = 0; i < A.length; i++) { // Get frequency of int
            count[A[i] - min] = count[A[i] - min] += 1;
        }

        for (int i = 1; i < count.length; i++) { // Get actual location of int
            count[i] += count[i-1];
        }

        for (int i = A.length - 1; i >= 0; i--) { // Working from end of array to avoid having to shift previously placed numbers.
            output[count[A[i] - min] - 1] = A[i]; // If min is negative, this will shift the desired sorted index right.
            --count[A[i] - min]; // Decrement frequency count of that number
        }

        for (int i = 0; i < A.length; i++)
        {
            A[i] = output[i];
        }
        A = output;
    }

}
