import java.util.ArrayList;
import java.util.List;

public class CollisionBucketReport {
    static void report(int[] keys, int bucketCount) {
        if (bucketCount <= 0) return;
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
        if (keys != null) {
            for (int key : keys) {
                int index = Math.floorMod(Integer.hashCode(key), bucketCount);
                if (!buckets.get(index).contains(key)) {
                    buckets.get(index).add(key);
                }
            }
        }
        int totalCollisions = 0;
        int maxChain = 0;
        for (int i = 0; i < buckets.size(); i++) {
            List<Integer> chain = buckets.get(i);
            System.out.println("Bucket " + i + ": " + chain);
            if (chain.size() > 1) {
                totalCollisions += (chain.size() - 1);
            }
            if (chain.size() > maxChain) {
                maxChain = chain.size();
            }
        }
        System.out.println("Total Collisions: " + totalCollisions);
        System.out.println("Max Chain Length: " + maxChain);
    }

    public static void main(String[] args) {
        int[] keys = {15, -3, 22, 15, 8, 42, 99, -3};
        report(keys, 5);
        System.out.println("--- Empty ---");
        report(new int[]{}, 3);
        System.out.println("--- Null ---");
        report(null, 3);
    }
}