import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BfsLayerReport {
    /**
     * 計算圖中每個可達節點距離起點的最少 edge 數。
     */
    static Map<String, Integer> getLayerReport(Map<String, List<String>> graph, String start) {
        Map<String, Integer> layers = new LinkedHashMap<>();
        if (graph == null || start == null || !graph.containsKey(start)) return layers;

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        layers.put(start, 0); // 起點距離為 0

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentLayer = layers.get(current);

            for (String next : graph.getOrDefault(current, List.of())) {
                if (graph.containsKey(next) && !layers.containsKey(next)) {
                    layers.put(next, currentLayer + 1);
                    queue.offer(next);
                }
            }
        }
        return layers;
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = Map.of(
            "A", List.of("B", "C"), "B", List.of("D"),
            "C", List.of("D", "E"), "D", List.of("F"),
            "E", List.of("F"), "F", List.of(), "Isolated", List.of()
        );

        System.out.println("一般案例 (起點 A): " + getLayerReport(graph, "A"));
        System.out.println("一般案例 (起點 C): " + getLayerReport(graph, "C"));
        System.out.println("邊界案例 (孤立節點): " + getLayerReport(graph, "Isolated"));
        System.out.println("邊界案例 (Missing Vertex): " + getLayerReport(graph, "X"));
        System.out.println("邊界案例 (Empty Graph): " + getLayerReport(Map.of(), "A"));
        System.out.println("邊界案例 (Null Graph): " + getLayerReport(null, "A"));
    }
}