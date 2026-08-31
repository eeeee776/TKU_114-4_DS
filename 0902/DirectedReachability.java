import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class DirectedReachability {
    static boolean isReachable(Map<String, List<String>> graph, String from, String to) {
        if (graph == null || from == null || to == null) return false;
        if (!graph.containsKey(from) || !graph.containsKey(to)) return false;
        
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        
        queue.offer(from);
        visited.add(from);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(to)) return true;

            for (String next : graph.getOrDefault(current, List.of())) {
                if (graph.containsKey(next) && visited.add(next)) {
                    queue.offer(next);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // 有向圖設定：A -> B -> C, X -> Y, D (孤立)
        Map<String, List<String>> graph = Map.of(
            "A", List.of("B"), "B", List.of("C"), "C", List.of(),
            "X", List.of("Y"), "Y", List.of("X"), "D", List.of()
        );

        System.out.println("一般案例 (A 到 C): " + isReachable(graph, "A", "C")); // true
        System.out.println("一般案例 (C 到 A): " + isReachable(graph, "C", "A")); // false (有向)
        System.out.println("邊界案例 (起點等於終點): " + isReachable(graph, "A", "A")); // true
        System.out.println("邊界案例 (不同 component): " + isReachable(graph, "A", "X")); // false
        System.out.println("邊界案例 (Missing Vertex): " + isReachable(graph, "A", "Z")); // false
    }
}