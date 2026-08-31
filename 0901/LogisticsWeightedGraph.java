import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogisticsWeightedGraph {
    public record Route(String destination, int cost) {}

    private final Map<String, List<Route>> network = new HashMap<>();

    public void addLocation(String location) {
        if (location == null || location.isBlank()) return;
        network.putIfAbsent(location, new ArrayList<>());
    }

    public void addOrUpdateRoute(String from, String to, int cost) {
        if (!network.containsKey(from) || !network.containsKey(to)) {
            throw new IllegalArgumentException("地點不存在: " + from + " 或 " + to);
        }
        if (cost < 0) {
            throw new IllegalArgumentException("物流成本不可為負數: " + cost);
        }

        List<Route> routes = network.get(from);
        // 檢查是否已有該路線，有則更新
        for (int i = 0; i < routes.size(); i++) {
            if (routes.get(i).destination().equals(to)) {
                routes.set(i, new Route(to, cost));
                return;
            }
        }
        // 沒有則新增
        routes.add(new Route(to, cost));
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
        return -1; // -1 代表無此路徑
    }
    
    public void printRoutesFrom(String location) {
        System.out.println("從 " + location + " 出發的物流路線: " + network.getOrDefault(location, List.of()));
    }

    public static void main(String[] args) {
        LogisticsWeightedGraph logistics = new LogisticsWeightedGraph();
        logistics.addLocation("Taipei");
        logistics.addLocation("Taichung");
        logistics.addLocation("Kaohsiung");

        // 新增路線
        logistics.addOrUpdateRoute("Taipei", "Taichung", 300);
        logistics.addOrUpdateRoute("Taichung", "Kaohsiung", 400);
        
        // 測試更新
        logistics.addOrUpdateRoute("Taipei", "Taichung", 250); 
        
        logistics.printRoutesFrom("Taipei");
        System.out.println("Taichung -> Kaohsiung 成本: " + logistics.getCost("Taichung", "Kaohsiung"));

        // 測試防呆 (會拋出 Exception)
        try {
            logistics.addOrUpdateRoute("Taipei", "Kaohsiung", -50);
        } catch (IllegalArgumentException e) {
            System.out.println("預期捕捉到錯誤: " + e.getMessage());
        }
    }
}