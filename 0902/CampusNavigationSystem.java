import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CampusNavigationSystem {
    private Map<String, String> locationDetails = new HashMap<>();
    private Map<String, List<String>> graph = new HashMap<>();

    public void addLocation(String id, String name) {
        locationDetails.put(id, name);
        graph.putIfAbsent(id, new ArrayList<>());
    }

    public void addPath(String id1, String id2) {
        if (graph.containsKey(id1) && graph.containsKey(id2)) {
            graph.get(id1).add(id2);
            graph.get(id2).add(id1); // 無向圖
        }
    }

    // BFS 尋找最短路徑，並回傳地點名稱清單
    public List<String> navigate(String startId, String targetId) {
        if (!graph.containsKey(startId) || !graph.containsKey(targetId)) {
            System.out.println("[錯誤] 起點或終點不存在於地圖中");
            return List.of();
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> previous = new HashMap<>();

        queue.offer(startId);
        visited.add(startId);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(targetId)) break;

            for (String next : graph.getOrDefault(current, List.of())) {
                if (visited.add(next)) {
                    previous.put(next, current);
                    queue.offer(next);
                }
            }
        }

        if (!visited.contains(targetId)) {
            return List.of(); // 無法到達
        }

        List<String> pathNames = new ArrayList<>();
        for (String at = targetId; at != null; at = previous.get(at)) {
            pathNames.add(locationDetails.get(at));
        }
        Collections.reverse(pathNames);
        return pathNames;
    }

    public static void main(String[] args) {
        CampusNavigationSystem campus = new CampusNavigationSystem();
        
        // 建立地點
        campus.addLocation("L1", "校門口");
        campus.addLocation("L2", "行政大樓");
        campus.addLocation("L3", "圖書館");
        campus.addLocation("L4", "資訊大樓");
        campus.addLocation("L5", "學生餐廳");
        campus.addLocation("L6", "新校區 (未開放)");

        // 建立道路
        campus.addPath("L1", "L2");
        campus.addPath("L1", "L5");
        campus.addPath("L2", "L3");
        campus.addPath("L3", "L4");
        campus.addPath("L5", "L4");

        System.out.println("一般案例 (校門口到資訊大樓): " + campus.navigate("L1", "L4"));
        System.out.println("邊界案例 (原地導航): " + campus.navigate("L3", "L3"));
        System.out.println("邊界案例 (無路徑可達孤立節點): " + campus.navigate("L1", "L6"));
        System.out.println("邊界案例 (Missing Vertex): " + campus.navigate("L1", "L99"));
    }
}