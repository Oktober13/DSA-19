import java.util.HashMap;
import java.util.Map;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        Map<Double, Integer> map;
        int boom = 0;

        for (int i = 0; i < points.length; i++) {
            map = new HashMap<>();

            for (int j = 0; j < points.length; j++) {
                double dist = getDist(points[i], points[j]);

                int count = map.getOrDefault(dist, 0);
                if (dist != 0) { // If not self
                    map.put(dist, count + 1);
                }
            }

            for (Double distance : map.keySet()) { // Check for equidistant points
//                int count = map.get(distance) / 2;
                int count = map.get(distance);
                if (count > 1) {
                    boom += count * (count - 1);
                }
            }
        }

        return boom;
    }

    private static double getDist(int[] A, int[] B) {
        return Math.sqrt(Math.pow((double) A[0] - B[0], 2.0) + Math.pow((double) A[1] - B[1], 2.0));
    }
}
