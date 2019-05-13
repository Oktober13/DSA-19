import java.util.Collections;
import java.util.HashMap;

public class DungeonGame {

    public static int minInitialHealth(int[][] map) {
        int[][] health = new int[map.length][map[0].length];
        health[0][0] = map[0][0];
        return findPath(map, health,map.length-1, map[0].length-1);
    }

    private static int findPath(int[][] map, int[][] health, int i, int j) {
        if (health[i][j] == map[i][j] + 1) {
            return health[i][j];
        }

        HashMap<Integer, int[]> neighbors = new HashMap<>();

        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1 ; l++) {
                if (!(k == 0 && l == 0) && (inBoard(i + k, j + l, map.length, map[0].length))) {
                    int[] coord = new int[]{i + k, j + l};
                    neighbors.put(health[i + k][j + l], coord);
                }
            }
        }

        Integer min = Collections.max(neighbors.keySet());
        int newI = neighbors.get(min)[0];
        int newJ = neighbors.get(min)[1];

        health[i][j] = findPath(map, health, newI, newJ) + map[i][j];
        return health[i][j];
    }

    private static boolean inBoard(int i, int j, int length, int width) {
        if (i >= 0 && i < length && j >= 0 && j < width) {
            return true;
        }
        return false;
    }
}
