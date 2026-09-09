import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Q12_CampusDispatchSystem {
    public record Request(String id, String location, int priority, long sequence) {}

    private final Map<String, List<String>> graph = new LinkedHashMap<>();
    private final Set<String> requestIds = new HashSet<>();
    private final PriorityQueue<Request> pq = new PriorityQueue<>(
        Comparator.comparingInt(Request::priority).thenComparingLong(Request::sequence)
    );

    public boolean addLocation(String location) {
        if (location == null || location.isBlank()) return false;
        if (graph.containsKey(location)) return false;
        graph.put(location, new ArrayList<>());
        return true;
    }

    public boolean addRoad(String first, String second) {
        if (first == null || second == null) return false;
        if (!graph.containsKey(first) || !graph.containsKey(second)) return false;
        if (first.equals(second)) return false;
        List<String> list1 = graph.get(first);
        List<String> list2 = graph.get(second);
        if (!list1.contains(second)) {
            list1.add(second);
            list2.add(first);
            return true;
        }
        return false;
    }

    public boolean submit(Request request) {
        if (request == null || request.id() == null || request.location() == null) return false;
        if (!graph.containsKey(request.location())) return false;
        if (!requestIds.add(request.id())) return false;
        pq.offer(request);
        return true;
    }

    public Request nextReachable(String serviceCenter) {
        if (serviceCenter == null || !graph.containsKey(serviceCenter)) return null;

        Set<String> reachableNodes = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(serviceCenter);
        reachableNodes.add(serviceCenter);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            for (String neighbor : graph.get(current)) {
                if (!reachableNodes.contains(neighbor)) {
                    reachableNodes.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        List<Request> stashed = new ArrayList<>();
        Request found = null;
        while (!pq.isEmpty()) {
            Request r = pq.poll();
            if (reachableNodes.contains(r.location())) {
                found = r;
                break;
            } else {
                stashed.add(r);
            }
        }

        for (Request r : stashed) {
            pq.offer(r);
        }

        if (found != null) {
            requestIds.remove(found.id());
        }

        return found;
    }

    public List<String> route(String start, String target) {
        List<String> result = new ArrayList<>();
        if (start == null || target == null) return result;
        if (!graph.containsKey(start) || !graph.containsKey(target)) return result;
        if (start.equals(target)) {
            result.add(start);
            return result;
        }

        Map<String, String> predecessor = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();

        queue.offer(start);
        visited.add(start);

        boolean found = false;
        while (!queue.isEmpty() && !found) {
            String current = queue.poll();
            for (String neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    predecessor.put(neighbor, current);
                    if (neighbor.equals(target)) {
                        found = true;
                        break;
                    }
                    queue.offer(neighbor);
                }
            }
        }

        if (!found) return result;

        String current = target;
        while (current != null) {
            result.add(current);
            current = predecessor.get(current);
        }
        Collections.reverse(result);
        return result;
    }

    public int pendingCount() {
        return pq.size();
    }
}