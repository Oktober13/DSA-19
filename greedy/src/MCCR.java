import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MCCR {
    public static int MCCR (EdgeWeightedGraph G) {
        return MCCRecurCondensedTwo(G);
    }

    public static int MCCRecurCondensedTwo (EdgeWeightedGraph graph) { // Prim's Algorithm
        ArrayList<Integer> edgesPlaced = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, Integer> previousNodes = new HashMap<>();
        IndexPQ queue = new IndexPQ(graph.numberOfV());

        for (int i = 0; i < graph.numberOfV(); i++) { // For each vertice, add to list of nodes to visit & mark as visited.
            queue.insert(i, Integer.MAX_VALUE);
            visited.add(i);
        }

        queue.changeKey(0,0); // Change the weight of the starting vertice to 0
        previousNodes.put(0,-1);      // Add to Hashmap of previously chosen nodes
        visited.add(0);               // Mark as visited.

        while (!queue.isEmpty()) { // Pop off vertices
            int min = queue.delMin(); // Pop off worst option
            visited.remove(min);      // Remove from visited nodes
            edgesPlaced.add(min);     // Place edge

            Iterable<Edge> initialEdges = graph.edges(min); // Get neighbors of the min key

            // Checks placed edges for potential cycles
            for (Edge e :initialEdges) {
                int target = e.other(min);

                // Another path with less weight- update list of previous nodes and add best edge to PQ
                if (visited.contains(target) && e.weight() < (int) queue.keys[target]) {
                    previousNodes.put(target, min);
                    queue.changeKey(target, e.weight());
                }
            }
        }

        return getWeights(previousNodes, graph); // Get sum of all node weights in current embedding
    }

    public static int getWeights (HashMap<Integer,Integer> prev, EdgeWeightedGraph graph) {
        int weightSum = 0;

        for (int prevNode :prev.keySet()) {
            for (Edge edge : graph.edges(prevNode)) {
                int e = edge.other(prevNode);
                if (prev.get(prevNode) == e) {
                    weightSum = weightSum + edge.weight();
                }
            }
        }

        return weightSum;
    }
}