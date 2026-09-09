import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LogisticsWeightedGraph {
    public record Route(String destination, int cost) {}

    private final Map<String, List<Route>> network = new LinkedHashMap<>();

    public void addLocation(String location) {
        if (location != null && !location.isBlank()) {
            network.putIfAbsent(location, new ArrayList<>());
        }
    }

    public boolean addOrUpdateRoute(String from, String to, int cost) {
        if (cost < 0 || !network.containsKey(from) || !network.containsKey(to)) return false;
        List<Route> routes = network.get(from);
        
        for (int i = 0; i < routes.size(); i++) {
            if (routes.get(i).destination().equals(to)) {
                routes.set(i, new Route(to, cost));
                return true;
            }
        }
        routes.add(new Route(to, cost));
        return true;
    }

    public boolean removeRoute(String from, String to) {
        if (!network.containsKey(from)) return false;
        List<Route> routes = network.get(from);
        for (int i = 0; i < routes.size(); i++) {
            if (routes.get(i).destination().equals(to)) {
                routes.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getCost(String from, String to) {
        if (!network.containsKey(from)) return -1;
        for (Route r : network.get(from)) {
            if (r.destination().equals(to)) return r.cost();
        }
        return -1;
    }

    public List<Route> getOutgoingRoutes(String location) {
        return new ArrayList<>(network.getOrDefault(location, new ArrayList<>()));
    }

    public static void main(String[] args) {
        LogisticsWeightedGraph logistics = new LogisticsWeightedGraph();
        logistics.addLocation("Hub_A");
        logistics.addLocation("Hub_B");
        logistics.addLocation("Hub_C");

        System.out.println("Add A->B (50): " + logistics.addOrUpdateRoute("Hub_A", "Hub_B", 50));
        System.out.println("Add A->C (100): " + logistics.addOrUpdateRoute("Hub_A", "Hub_C", 100));
        System.out.println("Add A->B (-10) [Invalid]: " + logistics.addOrUpdateRoute("Hub_A", "Hub_B", -10));
        System.out.println("Update A->C (80): " + logistics.addOrUpdateRoute("Hub_A", "Hub_C", 80));

        System.out.println("Cost A->C: " + logistics.getCost("Hub_A", "Hub_C"));
        System.out.println("Routes from A: " + logistics.getOutgoingRoutes("Hub_A"));

        System.out.println("Remove A->B: " + logistics.removeRoute("Hub_A", "Hub_B"));
        System.out.println("Routes from A after removal: " + logistics.getOutgoingRoutes("Hub_A"));
    }
}