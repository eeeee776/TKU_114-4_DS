import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q08_BfsTraversal {
    public static List<String> bfs(Map<String, List<String>> graph, String start) {
        List<String> result = new ArrayList<>();
        if (graph == null || start == null || !graph.containsKey(start)) return result;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            result.add(current);
            List<String> neighbors = graph.get(current);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor) && graph.containsKey(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return result;
    }

    public static Map<String, Integer> distanceFrom(Map<String, List<String>> graph, String start) {
        Map<String, Integer> distances = new HashMap<>();
        if (graph == null || start == null || !graph.containsKey(start)) return distances;
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDist = distances.get(current);
            List<String> neighbors = graph.get(current);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (!distances.containsKey(neighbor) && graph.containsKey(neighbor)) {
                        distances.put(neighbor, currentDist + 1);
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return distances;
    }
}