
public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * Best-case runtime: O(N) each branch, so O(NlogN) tptal.
     * Worst-case runtime: O(N)
     * Average-case runtime: O(NlogN)
     *
     * Space-complexity: O(N)
     */
    @Override
    public int[] sort(int[] array) {
        if (array.length <= 3) {
            InsertionSort is = new InsertionSort();
            return  is.sort(array);
        }
        int middle = (int) Math.floor(array.length/2.0);
        int rem = array.length % 2; // Remainder, deals with odd array lengths (eg: 125)

        int[] left = new int[middle];
        int[] right = new int[middle + rem];

        for (int i = 0; i < middle; i++) {
            left[i] = array[i];
        }
        for (int i = middle; i < array.length; i++) {
            right[i - middle] = array[i];
        }

        left = sort(left);
        right = sort(right);
        int[] temp =  merge(left, right);
        return temp;
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        int[] ab = new int[a.length + b.length];
        int indexA = 0;
        int indexB = 0;

        for (int i = 0; i < ab.length; i++) {
            if ((indexA >= a.length) || (indexB >= b.length)) {
                if (indexA >= a.length) {
                    ab[i] = b[indexB];
                    indexB++;
                } else {
                    ab[i] = a[indexA];
                    indexA++;
                }
            } else {
                if (a[indexA] < b[indexB]) {
                    ab[i] = a[indexA];
                    indexA++;
                } else {
                    ab[i] = b[indexB];
                    indexB++;
                }
            }
        }
        return ab;
    }

}
