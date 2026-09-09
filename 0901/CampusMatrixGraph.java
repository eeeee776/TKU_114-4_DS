import java.util.ArrayList;
import java.util.List;

public class CampusMatrixGraph {
    private final List<String> locations;
    private final boolean[][] edges;

    public CampusMatrixGraph(List<String> locations) {
        if (locations == null || locations.isEmpty()) throw new IllegalArgumentException();
        this.locations = List.copyOf(locations);
        this.edges = new boolean[locations.size()][locations.size()];
    }

    private int getIndex(String loc) {
        int idx = locations.indexOf(loc);
        if (idx == -1) throw new IllegalArgumentException("Location not found");
        return idx;
    }

    public void addEdge(String loc1, String loc2) {
        int i = getIndex(loc1);
        int j = getIndex(loc2);
        if (i != j) {
            edges[i][j] = true;
            edges[j][i] = true;
        }
    }

    public void removeEdge(String loc1, String loc2) {
        int i = getIndex(loc1);
        int j = getIndex(loc2);
        edges[i][j] = false;
        edges[j][i] = false;
    }

    public int getDegree(String loc) {
        int i = getIndex(loc);
        int count = 0;
        for (boolean e : edges[i]) {
            if (e) count++;
        }
        return count;
    }

    public List<String> getNeighbors(String loc) {
        int i = getIndex(loc);
        List<String> neighbors = new ArrayList<>();
        for (int j = 0; j < locations.size(); j++) {
            if (edges[i][j]) neighbors.add(locations.get(j));
        }
        return neighbors;
    }

    public int getEdgeCount() {
        int sum = 0;
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j]) sum++;
            }
        }
        return sum / 2;
    }

    public static void main(String[] args) {
        CampusMatrixGraph campus = new CampusMatrixGraph(List.of("Gate", "Lib", "Dorm", "Gym"));
        campus.addEdge("Gate", "Lib");
        campus.addEdge("Lib", "Dorm");
        campus.addEdge("Dorm", "Gym");
        campus.addEdge("Gate", "Gym");
        
        campus.addEdge("Gate", "Lib"); 
        
        System.out.println("Total Edges: " + campus.getEdgeCount());
        System.out.println("Lib Degree: " + campus.getDegree("Lib"));
        System.out.println("Gate Neighbors: " + campus.getNeighbors("Gate"));
        
        campus.removeEdge("Dorm", "Gym");
        System.out.println("Total Edges after removal: " + campus.getEdgeCount());
    }
}