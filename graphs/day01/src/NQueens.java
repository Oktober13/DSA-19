import java.util.ArrayList;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }

    public static boolean check(char[][] board, int j, int i) {
        int x = board[j].length - 1;
        while (x >= 0) {
            if (board[j][x] == 'Q') return false;
            x--;

        }
        int y = board.length - 1;
        while (y >= 0) {
            if (board[y][i] == 'Q') return false;
            y--;
        }
        if (checkDiagonal(board, j, i)) {
            return false;
        }
        return true;
    }


    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOfQ(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                B[i][j] = A[i][j];
            }
//            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        }
        return B;
    }

    public static List<char[][]> nQueensSolutions(int n) {
        char[][] empty = new char[n][n];
        List<char[][]> answers = new ArrayList<>();
        for (int i = 0; i < n; i++ ) {
            for (int j = 0; j < n; j++)
            {
                empty[i][j] = '.'; // value is your chosen integer for that index
            }
        }

        NQueens.solve(answers, empty, 0);
        return answers;
    }

    public static void solve(List<char[][]> p, char[][] mat, int col) {
        if (col == (mat.length )) { // If lower range == higher range (all queens placed)
            p.add(copyOfQ(mat));
            return;
        } else {
            for (int row = 0; row < mat[0].length; row++) { // Iterate through range and place r-l queens
                if (check(mat, col, row)) {
                    mat[col][row] = 'Q'; // Place queen
                    solve(p, mat, col + 1);
                    mat[col][row] = '.'; // Place queen // Backtrack
                }
            }
        }
        return;
    }
}
