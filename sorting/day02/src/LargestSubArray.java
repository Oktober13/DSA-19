import java.util.HashMap;

public class LargestSubArray {
    static int[] largestSubarray(int[] nums) {
        int sumFromZero = 0;
        HashMap<Integer, Integer> internalGroups = new HashMap<>();
        int maxSizeStartingIndex = 0;
        int maxSizeEndingIndex = -1;
        internalGroups.put(0, -1);

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 0) { // Turn all 0s to -1s to distinguish them from 1s in the sum
                nums[i] = -1;
            }

            sumFromZero += nums[i]; // Update sum

            // Trigger condition- equal # 1s and 0s from index 0 to index i.
            if (sumFromZero == 0) {
                maxSizeEndingIndex = i;
            }

            // Check for partner integer to close groups
            if (internalGroups.getOrDefault(sumFromZero, 90000) == 90000) {
                internalGroups.put(sumFromZero, i);
            } else {
                int startingIndex = internalGroups.get(sumFromZero) + 1;
                int endingIndex = i;
                if ((endingIndex - startingIndex) > (maxSizeEndingIndex - maxSizeStartingIndex)) {
                    maxSizeStartingIndex = startingIndex;
                    maxSizeEndingIndex = endingIndex;
                }
            }
        }

        if ((maxSizeEndingIndex - maxSizeStartingIndex) < 0) {
            return null;
        } else {
            return new int[]{maxSizeStartingIndex, maxSizeEndingIndex};
        }
    }
}
