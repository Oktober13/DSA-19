import java.util.ArrayList;
import java.util.List;

public class CoinsOnAClock {
    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        List<char[]> results = new ArrayList<>();
        findSoln(results, new char[hoursInDay], 0, pennies, nickels, dimes, hoursInDay);
        return results;
    }

    /** Finds solutions to the clock problem.
     * @param results List of char[] : all valid solutions to the coin game
     * @param  state Char[] of the current solution being worked on
     * @param index current hour of day
     * @param pennies num pennies remaining
     * @param  nickels num nickels remaining
     * @param dimes num dimes remaining
     * @param hours num hours left to assign **/
    public static boolean findSoln (List<char[]> results, char[] state, int index, int pennies, int nickels, int dimes, int hours) {
        // WRAP INDEX
        index = index % state.length;

        // ALL INVALID STATES
        if ((pennies < 0) || (nickels < 0) || (dimes < 0)) {
            return false;
        }
        if (hours == 0) {
            results.add(state.clone());
            return true;
        }
        if ((state[index] == 'p') || (state[index] == 'n') || (state[index] == 'd') ) {
            return false;
        }
        // ITERATE THROUGH POSSIBLE COINS
        for (int i = 0; i < 3; i++) {
            switch(i) {
                case 0: // Pennies
                    state[index] = 'p';
                    findSoln(results, state, index+1,pennies-1, nickels, dimes,hours-1);
                    break;
                case 1: // Nickels
                    state[index] = 'n';
                    findSoln(results, state, index+5, pennies, nickels-1, dimes,hours-1);
                    break;
                case 2: // Dimes
                    state[index] = 'd';
                    findSoln(results, state, index+10, pennies, nickels, dimes-1,hours-1);
                    break;
            }
            state[index] = '0';
        }
        return false; // No solutions found
    }
}
