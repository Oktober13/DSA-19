import java.util.Arrays;

public class LongestIncreasingSubsequence {

    // Runtime: TODO
    // Space: TODO
    public static int LIS(int[] A) {
        int[] sequence = new int[A.length];
        int max = 0;

        for (int i = 0; i < A.length; i++) {
            max = -1; // Maximum length of subsequence
            for (int j = 0; j < i; j++) {
                if(A[i] > A[j]) {
                    if ((max == -1) || (max < sequence[j] + 1)) {
                        max = 1 + sequence[j];
                    }
                }
            }

            if (max == -1) { // No prev elements were smaller than sequence[i]
                max = 1;
            }
            sequence[i] = max;
        }

        Arrays.sort(sequence);

        if (sequence.length == 0) {
            return 0;
        }
        return sequence[sequence.length - 1];
    }
}