public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * TODO
     * Best-case runtime: O(n)
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(n^2)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        if (array.length <= 1) {
            return array;
        }

        for (int i = 1; i < array.length; i++) { // Iterate through array
            int insertVal = array[i];
            int insertIndex = i;
            while ((insertIndex > 0) && (array[insertIndex - 1] > insertVal)) {
                array[insertIndex] = array[insertIndex - 1];
                insertIndex--;
            }
            array[insertIndex] = insertVal;
        }

        return array;
    }
}
