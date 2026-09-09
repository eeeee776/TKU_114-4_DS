import java.util.ArrayList;
import java.util.List;

public class IntegratedStructureAudit {
    public record AuditEntry(String scenario, String selectedStructure, String expectedBigO) {}

    public static List<AuditEntry> generateAuditReport() {
        List<AuditEntry> report = new ArrayList<>();
        report.add(new AuditEntry("Fast index-based element retrieval", "ArrayList", "O(1)"));
        report.add(new AuditEntry("First-In-First-Out task processing", "ArrayDeque (Queue)", "O(1)"));
        report.add(new AuditEntry("Last-In-First-Out state tracking", "ArrayDeque (Stack)", "O(1)"));
        report.add(new AuditEntry("Maintain elements in sorted order for range queries", "Binary Search Tree", "O(log n)"));
        report.add(new AuditEntry("Repeatedly extracting the highest priority task", "PriorityQueue (Min/Max Heap)", "O(log n)"));
        report.add(new AuditEntry("Exact match lookup via unique string key", "HashMap", "O(1)"));
        report.add(new AuditEntry("Checking for duplicate ID occurrences", "HashSet", "O(1)"));
        report.add(new AuditEntry("Finding shortest path in unweighted network", "Graph with BFS", "O(V+E)"));
        report.add(new AuditEntry("Checking if a deep dependency path exists", "Graph with DFS", "O(V+E)"));
        return report;
    }

    public static void printDiagnostics() {
        List<AuditEntry> entries = generateAuditReport();
        System.out.println("--- Data Structure & Algorithm Audit ---");
        for (AuditEntry entry : entries) {
            System.out.printf("Scenario: %-55s | Structure: %-30s | Big-O: %s\n", 
                              entry.scenario(), entry.selectedStructure(), entry.expectedBigO());
        }
    }

    public static void main(String[] args) {
        printDiagnostics();
    }
}