import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BfsLayerReport {
    public static Map<String, Integer> reportLayers(Map<String, List<String>> graph, String start) {
        Map<String, Integer> distances = new HashMap<>();
        if (graph == null || start == null || !graph.containsKey(start)) return distances;
        
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        distances.put(start, 0);
        
        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDist = distances.get(current);
            
            for (String neighbor : graph.getOrDefault(current, List.of())) {
                if (graph.containsKey(neighbor) && !distances.containsKey(neighbor)) {
                    distances.put(neighbor, currentDist + 1);
                    queue.offer(neighbor);
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", List.of("B", "C"));
        graph.put("B", List.of("D"));
        graph.put("C", List.of("D", "E"));
        graph.put("D", List.of("F"));
        graph.put("E", List.of("F"));
        graph.put("F", List.of());

        System.out.println(reportLayers(graph, "A"));
        System.out.println(reportLayers(graph, "X"));
        System.out.println(reportLayers(null, "A"));
    }
}