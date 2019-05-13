import java.util.ArrayList;

public class State {
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
}
