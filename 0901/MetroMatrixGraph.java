import java.util.ArrayList;
import java.util.List;

public class MetroMatrixGraph {
    private final List<String> stations;
    private final boolean[][] edges;

    public MetroMatrixGraph(List<String> stations) {
        this.stations = List.copyOf(stations);
        int n = stations.size();
        this.edges = new boolean[n][n];
    }

    private int indexOf(String station) {
        int idx = stations.indexOf(station);
        if (idx < 0) throw new IllegalArgumentException("未知的車站: " + station);
        return idx;
    }

    public void addRoute(String s1, String s2) {
        int u = indexOf(s1);
        int v = indexOf(s2);
        edges[u][v] = true;
        edges[v][u] = true;
    }

    public List<String> getNeighbors(String station) {
        List<String> result = new ArrayList<>();
        int row = indexOf(station);
        for (int i = 0; i < stations.size(); i++) {
            if (edges[row][i]) result.add(stations.get(i));
        }
        return result;
    }

    public int degree(String station) {
        return getNeighbors(station).size();
    }

    public int edgeCount() {
        int sum = 0;
        for (int i = 0; i < stations.size(); i++) {
            for (int j = 0; j < stations.size(); j++) {
                if (edges[i][j]) sum++;
            }
        }
        return sum / 2; // 無向圖除以2
    }

    public void printMatrix() {
        System.out.println("=== 捷運路線連線矩陣 ===");
        System.out.print(String.format("%-8s", ""));
        for (String s : stations) System.out.print(String.format("%-8s", s));
        System.out.println();

        for (int i = 0; i < stations.size(); i++) {
            System.out.print(String.format("%-8s", stations.get(i)));
            for (int j = 0; j < stations.size(); j++) {
                System.out.print(String.format("%-8s", edges[i][j] ? "1" : "0"));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        List<String> lines = List.of("Taipei", "Zhongshan", "Shuanglian", "Minquan");
        MetroMatrixGraph metro = new MetroMatrixGraph(lines);
        
        metro.addRoute("Taipei", "Zhongshan");
        metro.addRoute("Zhongshan", "Shuanglian");
        metro.addRoute("Shuanglian", "Minquan");

        metro.printMatrix();
        System.out.println("\nZhongshan 鄰站: " + metro.getNeighbors("Zhongshan"));
        System.out.println("Zhongshan 的 degree: " + metro.degree("Zhongshan"));
        System.out.println("總路線段數: " + metro.edgeCount());
    }
}