import java.util.*;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[][] tiles;

    // Create a 2D array representing the solved board state- 0 is the blank spot
    public int[][] goal = {{1,2,3},{4,5,6},{7,8,0}};

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        tiles = new int[b.length][b.length];
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                tiles[j][i] = b[j][i];
            }
        }
        n = b.length;
    }

    /*
     * Size of the board 
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        return tiles.length;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                int value = tiles[j][i];
                if (value != 0) {
                    int goalI = (value - 1) % size(); // col
                    int goalJ = (value - 1) / size(); // row
                    sum += Math.abs(i - goalI) + Math.abs(j - goalJ);
                }
            }
        }
        return sum;
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        if (manhattan() == 0) {
            return true;
        }
        return false;
    }

    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */
    public boolean solvable() {
        int inversion = 0;
        int[] flat = Arrays.stream(tiles)
                .flatMapToInt(Arrays::stream)
                .toArray();

        for (int i = 0; i < flat.length; i++) {
            for (int j = i+1; j < flat.length; j++) {
                if ((flat[i] != 0) && (flat[j] != 0) && (flat[i] > flat[j])) {
                    inversion = inversion + 1;
                }
            }
        }

        if ((inversion % 2) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Return all neighboring boards in the state tree
     */
    public Iterable<Board> neighbors() {
        List<Board> results = new LinkedList<>();
        Board board;

        int i = 0;
        int j = 0;

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                if (tiles[y][x] == 0) { // Found the blank spot!
                    i = x;
                    j = y;
                }
            }
        }

        if (validSwitch(i-1, j)) {
            board = new Board(tiles);
            addSoln(results, board, i, j, i-1, j);

        }
        if (validSwitch(i+1, j)) {
            board = new Board(tiles);
            addSoln(results, board, i, j, i+1, j);
        }
        if (validSwitch(i, j-1)) {
            board = new Board(tiles);
            addSoln(results, board, i, j, i, j-1);
        }
        if (validSwitch(i, j+1)) {
            board = new Board(tiles);
            addSoln(results, board, i, j, i, j+1);
        }

        return results;
    }

    public void printpls(Board board) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                System.out.print(board.tiles[x][y] + "\t");

            }
        }
    }

    private boolean validSwitch(int i, int j) {
        if ((i < 0) || (i >= size()) || (j < 0) || (j >= size())) { return false; }
        return true;
    }

    private void addSoln(List<Board> results, Board board, int blank_i, int blank_j, int swap_i, int swap_j) {
        int blank = board.tiles[blank_j][blank_i];
        board.tiles[blank_j][blank_i] = board.tiles[swap_j][swap_i];
        board.tiles[swap_j][swap_i] = blank;

        results.add(board);
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}};
        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
    }
}
