import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        // Order cow list
        Arrays.sort(occupied);

        int total = occupied.length; // All stalls with cows WILL be blocked
        ArrayList<Integer> gaps = new ArrayList<>();

        // Get gaps
        for (int i = 1; i < total; i++) {
            int gap = occupied[i] - occupied[i - 1]; // A gap is when cows' stalls are not right next to each other
            if (gap > 1) {
                gaps.add(gap - 1);
            }
        }

        // Get rid of smallest gaps first to minimize stalls blocked
        Collections.sort(gaps);

        int boards = gaps.size() + 1;       // Start off with enough board to patch all gaps
        while (boards > M) {                // While you need more boards than available
            total += gaps.remove(0);  // Increase number of blocked stalls and eliminate smallest gaps
            boards--;                       // Delete that gap's board
        }

        return total;
    }
}