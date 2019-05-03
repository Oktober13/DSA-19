import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JumpPointSearchTest {

    private JumpPointSearch jps;
    private State current;
    private State goal;

    @BeforeEach
    public void setUp() throws Exception {
        jps = new JumpPointSearch();
        current = new State(0,0);
        goal = new State(9,9);
    }

    // Test board methods

    /**
     * Test method for unsolved heuristic.
     */
    @Test
    public void testManhattan() {
        assertEquals(jps.heuristic(current, goal), 18);
    }

    // Test solver with several initial board states

    /**
     * Test method for Solver - Unsolvable puzzle
     */
    @Test
    public void testSolverUnsolvable() {
        // Unsolvable puzzle
        for (int i = 0; i < jps.field.length; i++) {
            jps.field[i][3].isObst = true;
        }
        jps.solve();
        assertEquals(jps.goal.visited, false);
    }

    /**
     * Test method for Solver - Easy puzzle
     */
    @Test
    public void testSolverEasy() {
        jps.field[5][5].isObst = true;
        jps.solve();
        assertEquals(jps.goal.visited, true);
        assertEquals(jps.goal.moves.size(), 5);
    }

    @Test
    public void testSolverAverage() {
        jps.field[6][5].isObst = true;
        jps.field[5][5].isObst = true;
        jps.field[4][5].isObst = true;
        jps.solve();
        assertEquals(jps.goal.visited, true);
        assertEquals(jps.goal.moves.size(), 5);
    }


    @Test
    public void testSolverMedium() {
        for (int i = 0; i < jps.field.length - 1; i++) {
            jps.field[i][3].isObst = true;
        }

        for (int i = 1; i < jps.field.length; i++) {
            jps.field[i][5].isObst = true;
        }
        jps.solve();
        assertEquals(jps.goal.visited, true);
        assertEquals(jps.goal.moves.size(), 7);
    }

}
