import java.util.ArrayList;
import java.util.List;

public class DataStructureDecisionReport {
    public static class Decision {
        public final String scenario;
        public final String structure;
        public final String reason;
        public final String bigO;

        public Decision(String scenario, String structure, String reason, String bigO) {
            this.scenario = scenario;
            this.structure = structure;
            this.reason = reason;
            this.bigO = bigO;
        }

        @Override
        public String toString() {
            return String.format("Scenario: %-25s | Structure: %-25s | Reason: %-35s | Big-O: %s", 
                                 scenario, structure, reason, bigO);
        }
    }

    public static List<Decision> getDecisions() {
        List<Decision> list = new ArrayList<>();
        list.add(new Decision("Random Access", "ArrayList", "Direct index access", "O(1)"));
        list.add(new Decision("FIFO Task Queue", "ArrayDeque (Queue)", "First-in first-out processing", "O(1)"));
        list.add(new Decision("LIFO History", "ArrayDeque (Stack)", "Last-in first-out state tracking", "O(1)"));
        list.add(new Decision("Sorted Range Queries", "BST / TreeMap", "Maintains sorted order", "O(log n)"));
        list.add(new Decision("Highest Priority Task", "PriorityQueue (Heap)", "Efficient extraction of min/max", "O(log n)"));
        list.add(new Decision("Exact Key Lookup", "HashMap", "Fast key-value mapping", "O(1)"));
        list.add(new Decision("Unique Tags", "HashSet", "Prevents duplicates, fast check", "O(1)"));
        list.add(new Decision("Network Routing", "Graph (Adjacency List)", "Tracks complex relationships", "O(V+E)"));
        list.add(new Decision("Dense Matrix Math", "Graph (Adjacency Matrix)", "Fast edge existence check", "O(1)"));
        list.add(new Decision("Top K Elements", "Min Heap (Fixed Size)", "Maintains K largest elements", "O(log K)"));
        list.add(new Decision("Sequential Inserts", "ArrayList", "Fast append at the end", "O(1)"));
        list.add(new Decision("Shortest Unweighted Path", "BFS Queue", "Layer-by-layer traversal", "O(V+E)"));
        return list;
    }

    public static void main(String[] args) {
        for (Decision d : getDecisions()) {
            System.out.println(d);
        }
    }
}