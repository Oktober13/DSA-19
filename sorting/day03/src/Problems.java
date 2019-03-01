import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        CountingSort.countingSort(A);
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        LinkedList<String>[] L = new LinkedList[26];

        int digit;

        for (int i = 0; i < L.length; i++) {
            L[i] = new LinkedList<>();
        }

        for (String i : A) {
            digit = getNthCharacter(i, n);
            L[digit].add(i);
        }

        int j = 0; // index in A to place numbers
        for (LinkedList list : L) {
            while (list.size() != 0) {
                A[j] = (String) list.pop();
                j += 1;
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        for (int j = 0; j < stringLength; j++) {
            countingSortByCharacter(S, j);
        }
    }

    /**
     * @param A The array to count swaps in
     */

    public static int countSwaps(int[] A) {
        // TODO
        return 0;
    }

}
