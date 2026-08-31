import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollisionBucketReport {
    public static void generateReport(List<Integer> keys, int bucketCount) {
        if (bucketCount <= 0) {
            throw new IllegalArgumentException("bucketCount must be positive");
        }

        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        if (keys != null) {
            for (Integer key : keys) {
                if (key == null) continue;
                int index = Math.floorMod(Integer.hashCode(key), bucketCount);
                List<Integer> chain = buckets.get(index);
                // 依教材語意，相同 key 不重複存入同一 bucket
                if (!chain.contains(key)) {
                    chain.add(key);
                }
            }
        }

        int totalCollisions = 0;
        int maxChainLength = 0;

        System.out.println("=== Bucket Report (Count: " + bucketCount + ") ===");
        for (int i = 0; i < bucketCount; i++) {
            List<Integer> chain = buckets.get(i);
            int size = chain.size();
            System.out.println("Bucket [" + i + "] (size " + size + "): " + chain);

            if (size > 1) {
                totalCollisions += (size - 1);
            }
            if (size > maxChainLength) {
                maxChainLength = size;
            }
        }

        System.out.println("-----------------------------------");
        System.out.println("Total Collisions: " + totalCollisions);
        System.out.println("Longest Chain Length: " + maxChainLength);
    }

    public static void main(String[] args) {
        List<Integer> keys = List.of(12, -3, 7, 22, -13, 7, 17, 32, 0);
        generateReport(keys, 5);

        System.out.println("\nTesting Empty List:");
        generateReport(Collections.emptyList(), 3);
    }
}