import java.util.*;

//Implementing JPS
public class JumpPointSearch {

    public State[][] field = new State[10][10];
    public State current;
    public State goal;
    public ArrayList<State> moves = new ArrayList<>();

    // initialize field
    public JumpPointSearch() {
        current = new State(0,0);
        goal = new State(field.length, field[0].length);

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = new State(i,j);
            }
        }
    }

    // initialize field
    public JumpPointSearch(State[] T) {
        current = new State(0,0);
        goal = new State(field.length, field[0].length);

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = new State(i,j);
            }
        }
    }

    public boolean isSolved() {
        if ((goal.i == current.i) && (goal.j == current.j)) {
            goal.visited = true;
            return true;
        }
        return false;
    }

    private boolean onGrid(int i, int j) {
        i = current.i + i;
        j = current.j + j;
        if (i >= 0 && i < field.length && j >= 0 && j < field[0].length) {
            return true;
        }
        return false;
    }

    /**
     * Manhattan distance to goal. Should not overestimate.
     * TODO: Figure out how to integrate weighted field
     **/
    public static int heuristic(State current, State goal) {
        int manhattan = Math.abs(current.i - goal.i) + Math.abs(current.j - goal.j);
        return manhattan;
    }

    public PriorityQueue<State> getNeighbors() {
        PriorityQueue<State> neighbors = new PriorityQueue<>();

        for (int i = current.i; i < field.length; i++) { // Left jump
            if (isSolved()) { break; }
            if (!onGrid(i,current.j)) {
                break;
            }
            if (field[i][current.j].isObst) {
                neighbors.add(field[i-1][current.j]);
                break;
            }
        }

        for (int j = current.j; j < field.length; j++) { // Down jump
            if (isSolved()) { break; }
            if (!onGrid(current.i, j)) {
                break;
            }
            if (field[j][current.j].isObst) {
                neighbors.add(field[j-1][current.j]);
                break;
            }
        }

        for (int i = current.i; i >= 0; i--) { // Right jump
            if (isSolved()) { break; }
            if (!onGrid(i,current.j)) {
                break;
            }
            if (field[i][current.j].isObst) {
                neighbors.add(field[i+1][current.j]);
                break;
            }
        }

        for (int j = current.j; j >= 0; j++) { // Up jump
            if (isSolved()) { break; }
            if (!onGrid(current.i, j)) {
                break;
            }
            if (field[j][current.j].isObst) {
                neighbors.add(field[j+1][current.j]);
                break;
            }
        }

        if (neighbors.size() == 0 && onGrid(current.i + 1, current.j + 1)) {
            if (!field[current.i + 1][current.j + 1].isObst) {
                current = field[current.i + 1][current.j + 1];
                neighbors.addAll(getNeighbors());
            } else {
                neighbors.add(field[current.i][current.j]);
            }
        }

        // TODO: Add neighbor cases around obstacle

        return neighbors;
    }

    // return the list of rotations needed to solve a rubik's cube
    public List<State> solve() {

        PriorityQueue<State> neighbors = new PriorityQueue<>();
        this.current.visited = true;

        while (!isSolved()) {
            for (Iterator<State> iterator = getNeighbors().iterator(); iterator.hasNext(); ) {
                State next = iterator.next();
                if (next.visited) {
                    iterator.remove();
                } else {
                    neighbors.add(next);
//                    next.visited = true;
                }
            }

            if (neighbors.size() == 0) { // Explored all neighbor nodes
                return null;
            }
            State choose = neighbors.poll();
            choose.visited = true;
            current = choose; // Get best fringe node
            moves = choose.moves; // Get moves to that fringe node
        }

        for (State m : moves) {
            System.out.print(m.i);
            System.out.print(" ");
            System.out.print(m.j);
            System.out.print("      ");
        }
        System.out.println("");
        return moves;
    }
}
