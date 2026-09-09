import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q10_UnweightedShortestPath {
    public static List<String> shortestPath(Map<String, List<String>> graph, String start, String target) {
        List<String> result = new ArrayList<>();
        if (graph == null || start == null || target == null) return result;
        if (!graph.containsKey(start) || !graph.containsKey(target)) return result;
        if (start.equals(target)) {
            result.add(start);
            return result;
        }

        Map<String, String> predecessor = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();

        queue.offer(start);
        visited.add(start);

        boolean found = false;
        while (!queue.isEmpty() && !found) {
            String current = queue.poll();
            List<String> neighbors = graph.get(current);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor) && graph.containsKey(neighbor)) {
                        visited.add(neighbor);
                        predecessor.put(neighbor, current);
                        if (neighbor.equals(target)) {
                            found = true;
                            break;
                        }
                        queue.offer(neighbor);
                    }
                }
            }
        }

        if (!found) return result;

        String current = target;
        while (current != null) {
            result.add(current);
            current = predecessor.get(current);
        }
        Collections.reverse(result);
        return result;
    }
}