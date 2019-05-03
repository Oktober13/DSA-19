import java.util.ArrayList;

public class State implements Comparable<State>{
    int i;
    int j;
    int weight;
    ArrayList<State> moves = new ArrayList<>();
    Boolean visited = false;
    Boolean isObst = false;

    public State(int x, int y) {
        i = x;
        j = y;
        weight = 0;
    }

    public State(int x, int y, int w) {
        i = x;
        j = y;
        weight = w;
    }

    @Override
    public int compareTo(State c) {
        State tempGoal = new State(9,9); // TODO: This sucks, fix it
        if (JumpPointSearch.heuristic(this, new State(9,9)) > JumpPointSearch.heuristic(c,new State(9,9))) {
            return -1;
        } else if (JumpPointSearch.heuristic(this, new State(9,9)) < JumpPointSearch.heuristic(c,new State(9,9))) {
            return 1;
        } else {
            return 0;
        }
    }
}
