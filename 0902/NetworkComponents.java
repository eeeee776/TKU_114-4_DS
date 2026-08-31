import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class NetworkComponents {
    static class ComponentReport {
        List<List<String>> components;
        int count;
        List<String> largestComponent;

        ComponentReport(List<List<String>> components) {
            this.components = components;
            this.count = components.size();
            this.largestComponent = List.of();
            for (List<String> comp : components) {
                if (comp.size() > largestComponent.size()) {
                    this.largestComponent = comp;
                }
            }
        }
        @Override
        public String toString() {
            return String.format("總數: %d, 最大群體: %s, 詳細名單: %s", count, largestComponent, components);
        }
    }

    static ComponentReport analyze(Map<String, List<String>> graph) {
        List<List<String>> allComponents = new ArrayList<>();
        if (graph == null || graph.isEmpty()) return new ComponentReport(allComponents);

        Set<String> visited = new HashSet<>();

        for (String start : graph.keySet()) {
            if (visited.contains(start)) continue;
            
            List<String> component = new ArrayList<>();
            Queue<String> queue = new ArrayDeque<>();
            queue.offer(start);
            visited.add(start);

            while (!queue.isEmpty()) {
                String current = queue.poll();
                component.add(current);

                for (String next : graph.getOrDefault(current, List.of())) {
                    if (graph.containsKey(next) && visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }
            allComponents.add(component);
        }
        return new ComponentReport(allComponents);
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = Map.of(
            "A", List.of("B"), "B", List.of("A", "C"), "C", List.of("B"), // 群體 1 (大小 3)
            "D", List.of("E"), "E", List.of("D"),                         // 群體 2 (大小 2)
            "F", List.of()                                                // 群體 3 (大小 1, 孤立)
        );

        System.out.println("一般案例: \n" + analyze(graph));
        System.out.println("\n邊界案例 (Empty Graph): \n" + analyze(Map.of()));
        System.out.println("\n邊界案例 (Null Graph): \n" + analyze(null));
    }
}