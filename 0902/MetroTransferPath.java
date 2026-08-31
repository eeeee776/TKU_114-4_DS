import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MetroTransferPath {
    static class PathResult {
        List<String> path;
        int edgeCount;

        PathResult(List<String> path, int edgeCount) {
            this.path = path;
            this.edgeCount = edgeCount;
        }
        @Override
        public String toString() { return String.format("Path: %s (Edges: %d)", path, edgeCount); }
    }

    static PathResult findShortestPath(Map<String, List<String>> graph, String start, String target) {
        if (graph == null || !graph.containsKey(start) || !graph.containsKey(target)) {
            return new PathResult(List.of(), 0);
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> previous = new HashMap<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(target)) break;

            for (String next : graph.getOrDefault(current, List.of())) {
                if (graph.containsKey(next) && visited.add(next)) {
                    previous.put(next, current);
                    queue.offer(next);
                }
            }
        }

        if (!visited.contains(target)) return new PathResult(List.of(), 0);

        List<String> path = new ArrayList<>();
        for (String at = target; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return new PathResult(path, path.size() - 1); // Edge 數為節點數 - 1
    }

    public static void main(String[] args) {
        Map<String, List<String>> metro = Map.of(
            "StationA", List.of("StationB", "StationC"),
            "StationB", List.of("StationA", "StationD"),
            "StationC", List.of("StationA", "StationD", "StationE"),
            "StationD", List.of("StationB", "StationC", "StationF"),
            "StationE", List.of("StationC", "StationF"),
            "StationF", List.of("StationD", "StationE"),
            "Unreachable", List.of()
        );

        System.out.println("一般案例 (A 到 F): " + findShortestPath(metro, "StationA", "StationF"));
        System.out.println("邊界案例 (A 到 A): " + findShortestPath(metro, "StationA", "StationA"));
        System.out.println("邊界案例 (無可達路徑): " + findShortestPath(metro, "StationA", "Unreachable"));
        System.out.println("邊界案例 (Missing Vertex): " + findShortestPath(metro, "StationA", "GhostStation"));
    }
}