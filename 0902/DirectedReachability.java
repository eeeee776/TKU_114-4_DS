import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class DirectedReachability {
    public static boolean isReachable(Map<String, List<String>> graph, String from, String to) {
        if (graph == null || from == null || to == null) return false;
        if (!graph.containsKey(from) || !graph.containsKey(to)) return false;
        if (from.equals(to)) return true;
        
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        
        queue.offer(from);
        visited.add(from);
        
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(to)) return true;
            
            for (String neighbor : graph.getOrDefault(current, List.of())) {
                if (graph.containsKey(neighbor) && visited.add(neighbor)) {
                    queue.offer(neighbor);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = Map.of(
            "A", List.of("B"),
            "B", List.of("C"),
            "C", List.of(),
            "D", List.of("A")
        );
        
        System.out.println(isReachable(graph, "A", "C"));
        System.out.println(isReachable(graph, "C", "A"));
        System.out.println(isReachable(graph, "D", "C"));
        System.out.println(isReachable(graph, "X", "Y"));
    }
}