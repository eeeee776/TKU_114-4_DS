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
    private final Map<String, List<String>> graph = new HashMap<>();

    public boolean addLocation(String location) {
        if (location == null || location.isBlank()) return false;
        String loc = location.trim();
        if (graph.containsKey(loc)) return false;
        graph.put(loc, new ArrayList<>());
        return true;
    }

    public boolean addRoad(String location1, String location2) {
        if (location1 == null || location2 == null) return false;
        String loc1 = location1.trim();
        String loc2 = location2.trim();
        if (!graph.containsKey(loc1) || !graph.containsKey(loc2)) return false;
        if (loc1.equals(loc2)) return false;

        List<String> list1 = graph.get(loc1);
        List<String> list2 = graph.get(loc2);
        if (!list1.contains(loc2)) {
            list1.add(loc2);
            list2.add(loc1);
            return true;
        }
        return false;
    }

    public List<String> navigate(String start, String target) {
        List<String> result = new ArrayList<>();
        if (start == null || target == null) return result;
        String s = start.trim();
        String t = target.trim();
        if (!graph.containsKey(s) || !graph.containsKey(t)) return result;
        if (s.equals(t)) {
            result.add(s);
            return result;
        }

        Map<String, String> predecessor = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();

        queue.offer(s);
        visited.add(s);

        boolean found = false;
        while (!queue.isEmpty() && !found) {
            String current = queue.poll();
            for (String neighbor : graph.get(current)) {
                if (visited.add(neighbor)) {
                    predecessor.put(neighbor, current);
                    if (neighbor.equals(t)) {
                        found = true;
                        break;
                    }
                    queue.offer(neighbor);
                }
            }
        }

        if (!found) return result;

        String current = t;
        while (current != null) {
            result.add(current);
            current = predecessor.get(current);
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        CampusNavigationSystem map = new CampusNavigationSystem();
        map.addLocation("Gate");
        map.addLocation("Library");
        map.addLocation("Cafeteria");
        map.addLocation("Dorm");

        map.addRoad("Gate", "Library");
        map.addRoad("Library", "Cafeteria");
        map.addRoad("Library", "Dorm");
        map.addRoad("Cafeteria", "Dorm");

        System.out.println("Route Gate -> Dorm: " + map.navigate("Gate", "Dorm"));
        System.out.println("Route Dorm -> Gate: " + map.navigate("Dorm", "Gate"));
        System.out.println("Route Gate -> Missing: " + map.navigate("Gate", "Gym"));
    }
}