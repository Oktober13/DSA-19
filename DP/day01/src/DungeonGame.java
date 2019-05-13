public class DungeonGame {

    public static int minInitialHealth(int[][] map) {
        int[][] health = new int[map.length][map[0].length];
        health[map.length-1][map[0].length-1] = Math.max((1 - map[map.length-1][map[0].length-1]), 1);
        health = findPath(map, health);

        return health[0][0];
    }

    private static int[][] findPath(int[][] map, int[][] health) {
        for (int i = map.length-2; i >= 0; i--) { // Initialize last column
            health[i][map[0].length-1] = Math.max(health[i+1][map[0].length-1] - map[i][map[0].length-1], 1);
        }
        for (int j = map[0].length-2; j >= 0; j--) { // Initialize first row
            health[map.length-1][j] = Math.max(health[map.length-1][j+1] - map[map.length-1][j], 1);
        }

        // Recursively populate health
        for (int i = map.length-2; i >= 0; i--) {
            for (int j = map[0].length-2; j >= 0; j--) {
                health[i][j] = Math.min(Math.max(health[i+1][j] - map[i][j], 1), Math.max(health[i][j+1] - map[i][j], 1));
            }
        }
        return health;
    }
}
