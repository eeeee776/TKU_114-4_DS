import java.util.ArrayList;
import java.util.List;

public class Q06_AdjacencyMatrixGraph {
    private final List<String> vertices;
    private final boolean[][] matrix;

    public Q06_AdjacencyMatrixGraph(List<String> vertices) {
        if (vertices == null || vertices.isEmpty()) throw new IllegalArgumentException();
        this.vertices = new ArrayList<>(vertices);
        this.matrix = new boolean[this.vertices.size()][this.vertices.size()];
    }

    private int getIndex(String vertex) {
        if (vertex == null) return -1;
        return vertices.indexOf(vertex);
    }

    public boolean addEdge(String first, String second) {
        int i = getIndex(first);
        int j = getIndex(second);
        if (i == -1 || j == -1 || i == j) return false;
        if (matrix[i][j]) return false;
        matrix[i][j] = true;
        matrix[j][i] = true;
        return true;
    }

    public boolean removeEdge(String first, String second) {
        int i = getIndex(first);
        int j = getIndex(second);
        if (i == -1 || j == -1) return false;
        if (!matrix[i][j]) return false;
        matrix[i][j] = false;
        matrix[j][i] = false;
        return true;
    }

    public boolean hasEdge(String first, String second) {
        int i = getIndex(first);
        int j = getIndex(second);
        if (i == -1 || j == -1) return false;
        return matrix[i][j];
    }

    public int degree(String vertex) {
        int i = getIndex(vertex);
        if (i == -1) return 0;
        int d = 0;
        for (int j = 0; j < vertices.size(); j++) {
            if (matrix[i][j]) d++;
        }
        return d;
    }

    public List<String> neighbors(String vertex) {
        List<String> n = new ArrayList<>();
        int i = getIndex(vertex);
        if (i == -1) return n;
        for (int j = 0; j < vertices.size(); j++) {
            if (matrix[i][j]) {
                n.add(vertices.get(j));
            }
        }
        return n;
    }
}