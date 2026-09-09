import java.util.ArrayList;
import java.util.List;

public class StudentIdHashAnalysis {
    public static void analyze(List<String> studentIds, int bucketCount) {
        if (studentIds == null || bucketCount <= 0) return;

        List<List<String>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        int uniqueCount = 0;
        for (String id : studentIds) {
            if (id == null) continue;
            int index = Math.floorMod(id.hashCode(), bucketCount);
            if (!buckets.get(index).contains(id)) {
                buckets.get(index).add(id);
                uniqueCount++;
            }
        }

        int totalCollisions = 0;
        int maxChain = 0;

        for (int i = 0; i < buckets.size(); i++) {
            int chainSize = buckets.get(i).size();
            System.out.println("Bucket " + i + " count: " + chainSize);
            if (chainSize > 1) {
                totalCollisions += (chainSize - 1);
            }
            if (chainSize > maxChain) {
                maxChain = chainSize;
            }
        }

        double avgChain = uniqueCount == 0 ? 0.0 : (double) uniqueCount / bucketCount;

        System.out.println("Total Unique IDs: " + uniqueCount);
        System.out.println("Total Collisions: " + totalCollisions);
        System.out.println("Max Chain Length: " + maxChain);
        System.out.printf("Average Chain Length: %.2f\n", avgChain);
        System.out.println("----------------------------------------");
    }

    public static void main(String[] args) {
        List<String> ids = List.of(
            "114001", "114002", "114003", "114004", 
            "114005", "114006", "114007", "114008",
            "114009", "114010", "114001"
        );

        System.out.println("=== Analysis with 5 Buckets ===");
        analyze(ids, 5);

        System.out.println("=== Analysis with 11 Buckets ===");
        analyze(ids, 11);
    }
}