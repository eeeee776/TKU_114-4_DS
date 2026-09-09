import java.util.ArrayList;
import java.util.List;

public class MetroMatrixGraph {
    private final List<String> stations;
    private final boolean[][] edges;

    public MetroMatrixGraph(List<String> stations) {
        if (stations == null || stations.isEmpty()) throw new IllegalArgumentException();
        this.stations = List.copyOf(stations);
        this.edges = new boolean[stations.size()][stations.size()];
    }

    private int getIndex(String station) {
        int idx = stations.indexOf(station);
        if (idx == -1) throw new IllegalArgumentException();
        return idx;
    }

    public void addConnection(String s1, String s2) {
        int i = getIndex(s1);
        int j = getIndex(s2);
        edges[i][j] = true;
        edges[j][i] = true;
    }

    public List<String> getNeighbors(String station) {
        int idx = getIndex(station);
        List<String> neighbors = new ArrayList<>();
        for (int j = 0; j < stations.size(); j++) {
            if (edges[idx][j]) {
                neighbors.add(stations.get(j));
            }
        }
        return neighbors;
    }

    public int getDegree(String station) {
        return getNeighbors(station).size();
    }

    public int getTotalEdges() {
        int count = 0;
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j]) count++;
            }
        }
        return count / 2;
    }

    public void printMatrixReport() {
        System.out.print("  ");
        for (String s : stations) System.out.print(s + " ");
        System.out.println();
        for (int i = 0; i < edges.length; i++) {
            System.out.print(stations.get(i) + " ");
            for (int j = 0; j < edges[i].length; j++) {
                System.out.print((edges[i][j] ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MetroMatrixGraph metro = new MetroMatrixGraph(List.of("TPE", "ZSN", "BQA", "XMN"));
        metro.addConnection("TPE", "ZSN");
        metro.addConnection("ZSN", "BQA");
        metro.addConnection("TPE", "XMN");

        System.out.println("TPE Neighbors: " + metro.getNeighbors("TPE"));
        System.out.println("ZSN Degree: " + metro.getDegree("ZSN"));
        System.out.println("Total Edges: " + metro.getTotalEdges());
        
        System.out.println("\nMatrix Report:");
        metro.printMatrixReport();
    }
}