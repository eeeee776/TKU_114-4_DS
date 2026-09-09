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
    public static class PathResult {
        public final List<String> path;
        public final int edgeCount;

        public PathResult(List<String> path, int edgeCount) {
            this.path = path;
            this.edgeCount = edgeCount;
        }

        @Override
        public String toString() {
            return "Path: " + path + " | Edges: " + edgeCount;
        }
    }

    public static PathResult findShortest(Map<String, List<String>> graph, String start, String target) {
        if (graph == null || start == null || target == null) return new PathResult(List.of(), -1);
        if (!graph.containsKey(start) || !graph.containsKey(target)) return new PathResult(List.of(), -1);
        if (start.equals(target)) return new PathResult(List.of(start), 0);

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> predecessor = new HashMap<>();

        queue.offer(start);
        visited.add(start);

        boolean found = false;
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(target)) {
                found = true;
                break;
            }
            for (String next : graph.getOrDefault(current, List.of())) {
                if (graph.containsKey(next) && visited.add(next)) {
                    predecessor.put(next, current);
                    queue.offer(next);
                }
            }
        }

        if (!found) return new PathResult(List.of(), -1);

        List<String> path = new ArrayList<>();
        for (String at = target; at != null; at = predecessor.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        
        return new PathResult(path, path.size() - 1);
    }

    public static void main(String[] args) {
        Map<String, List<String>> metro = Map.of(
            "TPE", List.of("ZSN", "XMN"),
            "ZSN", List.of("TPE", "BQA"),
            "XMN", List.of("TPE", "BQA", "SGN"),
            "BQA", List.of("ZSN", "XMN"),
            "SGN", List.of("XMN")
        );

        System.out.println(findShortest(metro, "TPE", "BQA"));
        System.out.println(findShortest(metro, "SGN", "ZSN"));
        System.out.println(findShortest(metro, "TPE", "TPE"));
        System.out.println(findShortest(metro, "TPE", "XXX"));
    }
}