import java.util.Arrays;

public class SplitCoins {
    /*
    * Subproblem: Should we add this number to set A or not?
    * Guess Soln: Add everything until you hit 1/2 sum
    * Recurrence relation: Either add the current number or don't and recur with one less item
    * Memoize: Sums are saved in DP. If they exist, they're just returned.
    * Solve: :)
    */

    public static int splitCoins(int[] coins) {
        Arrays.sort(coins);
        int desired = 0;
        int sum = 0;

        for (int i = 0; i < coins.length; i++) {
            sum += coins[i];
        }
        desired = sum / 2;

        int[][] DP = new int [desired + 1][coins.length];

        for (int[] row: DP) { // Fill with -1 to mark fresh array
            Arrays.fill(row, -1);
        }

        int pile1 = addTo(0, desired, coins.length-1, coins, DP);
        int pile2 = sum - pile1;
        return Math.abs(pile1 - pile2);
    }

    private static int addTo(int currentSum, int desiredSum, int index, int[] coins, int[][] DP) {
        // Have no items
        if (index == -1) {
            return 0;
        }

        // If already memoized
        if (DP[currentSum][index] != -1) {
            return DP[currentSum][index];
        }

        // Can’t put the current item in
        if ((currentSum + coins[index]) > desiredSum) {
            DP[currentSum][index] = addTo(currentSum, desiredSum, index - 1, coins, DP);
        }

        // Choice to include or not include item index
        else {
            DP[currentSum][index] = Math.max(addTo(currentSum, desiredSum,index - 1, coins, DP),
                    addTo(currentSum + coins[index], desiredSum, index - 1, coins, DP) + coins[index]);
        }

        return DP[currentSum][index];

    }
}
