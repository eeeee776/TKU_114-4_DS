import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IterativeDfsTrace {
    public static List<String> trace(Map<String, List<String>> graph, String start) {
        List<String> result = new ArrayList<>();
        if (graph == null || start == null || !graph.containsKey(start)) return result;
        
        ArrayDeque<String> stack = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        
        stack.push(start);
        System.out.println("PUSH: " + start + " | Stack: " + stack + " | Visited: " + visited);
        
        while (!stack.isEmpty()) {
            String current = stack.pop();
            System.out.println("POP: " + current + " | Stack: " + stack + " | Visited: " + visited);
            
            if (!visited.add(current)) continue;
            
            result.add(current);
            List<String> neighbors = graph.getOrDefault(current, List.of());
            
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                String next = neighbors.get(i);
                if (graph.containsKey(next) && !visited.contains(next)) {
                    stack.push(next);
                    System.out.println("PUSH: " + next + " | Stack: " + stack + " | Visited: " + visited);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("D"));
        graph.put("C", List.of("D"));
        graph.put("D", List.of("A"));
        
        System.out.println("Final Result: " + trace(graph, "A"));
    }
}