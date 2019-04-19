/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.util.*;

public class Solver {

    public int minMoves = 0;
    private State solutionState;
    private boolean solved = false;
    private HashSet<State> checked = new HashSet<>();

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State implements Comparable<State> {
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;
        boolean solvable;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            this.cost = moves + board.manhattan();
            if (prev == null) {
                this.solvable = board.solvable(); // Only check the first state
            } else {
                this.solvable = true;
            }
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }

        public int compareTo(State b) {
            if ( this.cost > b.cost) {
                return 1;
            } else if ( this.cost < b.cost ) {
                return -1;
            }
            return 0;
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        while (state.prev != null) {
            state = state.prev;
        }
        return state;
    }

    private Boolean inchecked() {
        for (State state : checked) {
            if (equals(state)) {
                return true;
            }
        }
        return false;
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        State s = new State(initial, 0, null);

        if (s.solvable) {
            PriorityQueue<State> costOrder = new PriorityQueue<>();

            for (Board neighbor : new Board(s.board.tiles).neighbors()) {
                costOrder.add(new State(neighbor, 0, null));
            }

            while (!s.board.isGoal() && (costOrder.size() != 0)) {
                // Pop top option and set equal to current state
                State bestOption = costOrder.peek();
                costOrder.remove(bestOption);

                bestOption.moves += 1;
                bestOption.prev = s;
                s = bestOption;
                checked.add(s); // Visited current state

                for (Board neighbor : s.board.neighbors()) {
                    if (!checked.contains(neighbor)) {
                        costOrder.add(new State(neighbor, s.moves, s));
                    }
                }
            }
            this.solved = true;
            minMoves = s.moves;
        }
    }

    /*
     * Is the input board a solvable state
     * Research how to check this without exploring all states
     */
    public boolean isSolvable() {
        return solved;
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        List<Board> results = null;
        return null;
    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space
     */
    public static void main(String[] args) {
        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board initial = new Board(initState);

        Solver solver = new Solver(initial);
    }
}
