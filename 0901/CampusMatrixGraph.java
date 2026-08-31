import java.util.ArrayList;
import java.util.List;

public class CampusMatrixGraph {
    private final List<String> locations;
    private final boolean[][] edges;

    public CampusMatrixGraph(List<String> locations) {
        this.locations = List.copyOf(locations);
        int n = locations.size();
        this.edges = new boolean[n][n];
    }

    private int indexOf(String location) {
        int idx = locations.indexOf(location);
        if (idx < 0) throw new IllegalArgumentException("Unknown location: " + location);
        return idx;
    }

    public void addEdge(String loc1, String loc2) {
        int u = indexOf(loc1);
        int v = indexOf(loc2);
        edges[u][v] = true;
        edges[v][u] = true;
    }

    public void removeEdge(String loc1, String loc2) {
        int u = indexOf(loc1);
        int v = indexOf(loc2);
        edges[u][v] = false;
        edges[v][u] = false;
    }

    public int degree(String location) {
        int count = 0;
        int row = indexOf(location);
        for (boolean hasEdge : edges[row]) {
            if (hasEdge) count++;
        }
        return count;
    }

    public List<String> neighbors(String location) {
        List<String> result = new ArrayList<>();
        int row = indexOf(location);
        for (int i = 0; i < locations.size(); i++) {
            if (edges[row][i]) result.add(locations.get(i));
        }
        return result;
    }

    public int edgeCount() {
        int sum = 0;
        // Matrix 中，(i, j) 和 (j, i) 都是 true，所以算總度數再除以 2
        for (int i = 0; i < locations.size(); i++) {
            for (int j = 0; j < locations.size(); j++) {
                if (edges[i][j]) sum++;
            }
        }
        return sum / 2;
    }

    public static void main(String[] args) {
        List<String> campus = List.of("Library", "Cafeteria", "Dorm", "Gym");
        CampusMatrixGraph graph = new CampusMatrixGraph(campus);
        
        graph.addEdge("Library", "Cafeteria");
        graph.addEdge("Cafeteria", "Dorm");
        graph.addEdge("Cafeteria", "Gym");
        graph.addEdge("Library", "Gym"); // 重複新增測試應使用 Set 或無視，這裡 boolean 覆蓋無妨
        
        System.out.println("Library degree: " + graph.degree("Library"));
        System.out.println("Cafeteria neighbors: " + graph.neighbors("Cafeteria"));
        System.out.println("Total edge count: " + graph.edgeCount());
        
        graph.removeEdge("Library", "Gym");
        System.out.println("After removing Library-Gym, total edge count: " + graph.edgeCount());
    }
}