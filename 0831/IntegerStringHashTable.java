import java.util.ArrayList;
import java.util.List;

public class IntegerStringHashTable {
    private record HashEntry(int key, String value) {}

    private final List<List<HashEntry>> buckets;
    private int size;

    public IntegerStringHashTable(int bucketCount) {
        if (bucketCount <= 0) throw new IllegalArgumentException("bucketCount must be positive");
        buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
        size = 0;
    }

    private int hashIndex(int key) {
        return Math.floorMod(Integer.hashCode(key), buckets.size());
    }

    public void put(int key, String value) {
        List<HashEntry> chain = buckets.get(hashIndex(key));
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key() == key) {
                chain.set(i, new HashEntry(key, value));
                return;
            }
        }
        chain.add(new HashEntry(key, value));
        size++;
    }

    public String get(int key) {
        List<HashEntry> chain = buckets.get(hashIndex(key));
        for (HashEntry entry : chain) {
            if (entry.key() == key) return entry.value();
        }
        return null;
    }

    public boolean containsKey(int key) {
        return get(key) != null;
    }

    public boolean remove(int key) {
        List<HashEntry> chain = buckets.get(hashIndex(key));
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key() == key) {
                chain.remove(i);
                size--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void bucketReport() {
        System.out.println("Total size: " + size);
        for (int i = 0; i < buckets.size(); i++) {
            System.out.println("Bucket " + i + ": " + buckets.get(i));
        }
    }

    public static void main(String[] args) {
        IntegerStringHashTable table = new IntegerStringHashTable(4);
        table.put(10, "A");
        table.put(20, "B");
        table.put(14, "C");
        table.put(10, "A_UPDATED");
        table.put(-5, "D");

        System.out.println("Size: " + table.size());
        System.out.println("Get 10: " + table.get(10));
        System.out.println("Contains 20: " + table.containsKey(20));
        System.out.println("Remove 14: " + table.remove(14));
        System.out.println("Remove 99: " + table.remove(99));
        
        table.bucketReport();
    }
}