import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IterativeDfsTrace {
    static List<String> traceIterativeDfs(Map<String, List<String>> graph, String start) {
        List<String> order = new ArrayList<>();
        if (graph == null || start == null || !graph.containsKey(start)) {
            System.out.println("[警告] Graph 無效或起點不存在");
            return order;
        }

        ArrayDeque<String> stack = new ArrayDeque<>();
        Set<String> visited = new LinkedHashSet<>();
        
        stack.push(start);
        System.out.printf("Push [%s] -> Stack: %s, Visited: %s%n", start, stack, visited);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            System.out.printf("Pop  [%s] -> Stack: %s, Visited: %s%n", current, stack, visited);
            
            if (!visited.add(current)) continue;
            order.add(current);
            System.out.printf("Visit[%s] -> Stack: %s, Visited: %s%n", current, stack, visited);

            List<String> neighbors = graph.getOrDefault(current, List.of());
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                String next = neighbors.get(i);
                if (graph.containsKey(next) && !visited.contains(next)) {
                    stack.push(next);
                    System.out.printf("Push [%s] -> Stack: %s, Visited: %s%n", next, stack, visited);
                }
            }
        }
        return order;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = Map.of(
            "1", List.of("2", "3"), "2", List.of("4"),
            "3", List.of("4"), "4", List.of()
        );

        System.out.println("--- 追蹤開始 (一般案例) ---");
        List<String> result = traceIterativeDfs(graph, "1");
        System.out.println("走訪結果: " + result);

        System.out.println("\n--- 追蹤開始 (Missing Vertex) ---");
        traceIterativeDfs(graph, "X");
    }
}