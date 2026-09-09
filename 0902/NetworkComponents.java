import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class NetworkComponents {
    public static class ComponentReport {
        public final List<List<String>> components;
        public final int componentCount;
        public final List<String> maxComponent;

        public ComponentReport(List<List<String>> components, int componentCount, List<String> maxComponent) {
            this.components = components;
            this.componentCount = componentCount;
            this.maxComponent = maxComponent;
        }

        @Override
        public String toString() {
            return "Components: " + components + "\nCount: " + componentCount + "\nMax: " + maxComponent;
        }
    }

    public static ComponentReport analyze(Map<String, List<String>> graph) {
        if (graph == null) return new ComponentReport(List.of(), 0, List.of());
        
        List<List<String>> allComponents = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        List<String> maxComp = new ArrayList<>();

        for (String start : graph.keySet()) {
            if (!visited.contains(start)) {
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
                Collections.sort(component);
                allComponents.add(component);
                
                if (component.size() > maxComp.size()) {
                    maxComp = component;
                }
            }
        }
        return new ComponentReport(allComponents, allComponents.size(), maxComp);
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = Map.of(
            "A", List.of("B"), "B", List.of("A"),
            "C", List.of("D", "E"), "D", List.of("C"), "E", List.of("C"),
            "F", List.of()
        );
        System.out.println(analyze(graph));
    }
}