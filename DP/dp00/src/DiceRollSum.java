import java.util.HashMap;

public class DiceRollSum {
    // Runtime: O(N) originally, but O(1) amortized
    // Space: O(N)
    public static int diceRollSum(int N) {
        HashMap<Integer, Integer> seen = new HashMap<>() {{
            put(0, 0);
            put(1, 1);
            put(2, 2);
            put(3, 4);
            put(4, 8);
            put(5, 16);
            put(6, 32);
        }};

        int sum = diceRoll(N, seen);

        if (sum == 0) { // Empty set
            return 1;
        }
        return sum;
    }

    public static int diceRoll(int N, HashMap<Integer, Integer> seen) {
        if (seen.containsKey(N)) { // If <key,value> has been memoized, return value.
            return seen.get(N);
        }

        int sum = diceRoll(N-1, seen) + diceRoll(N-2, seen) + diceRoll(N-3, seen) + diceRoll(N-4, seen) + diceRoll(N-5, seen) + diceRoll(N-6, seen);
        return sum;
    }

}
