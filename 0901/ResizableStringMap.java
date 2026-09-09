import java.util.ArrayList;
import java.util.List;

public class ResizableStringMap {
    private record HashEntry(String key, String value) {}

    private List<List<HashEntry>> buckets;
    private int size;

    public ResizableStringMap(int initialCapacity) {
        if (initialCapacity <= 0) throw new IllegalArgumentException();
        buckets = new ArrayList<>();
        for (int i = 0; i < initialCapacity; i++) buckets.add(new ArrayList<>());
    }

    private int getIndex(String key, int capacity) {
        return Math.floorMod(key.hashCode(), capacity);
    }

    public void put(String key, String value) {
        if (key == null) return;
        
        if ((double) size / buckets.size() > 0.75) {
            rehash();
        }

        int index = getIndex(key, buckets.size());
        List<HashEntry> chain = buckets.get(index);
        
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key().equals(key)) {
                chain.set(i, new HashEntry(key, value));
                return;
            }
        }
        chain.add(new HashEntry(key, value));
        size++;
    }

    private void rehash() {
        int newCapacity = buckets.size() * 2 + 1;
        List<List<HashEntry>> newBuckets = new ArrayList<>();
        for (int i = 0; i < newCapacity; i++) newBuckets.add(new ArrayList<>());

        for (List<HashEntry> chain : buckets) {
            for (HashEntry entry : chain) {
                int newIndex = getIndex(entry.key(), newCapacity);
                newBuckets.get(newIndex).add(entry);
            }
        }
        buckets = newBuckets;
    }

    public String get(String key) {
        if (key == null) return null;
        int index = getIndex(key, buckets.size());
        for (HashEntry entry : buckets.get(index)) {
            if (entry.key().equals(key)) return entry.value();
        }
        return null;
    }

    public int size() {
        return size;
    }

    public int bucketCount() {
        return buckets.size();
    }

    public static void main(String[] args) {
        ResizableStringMap map = new ResizableStringMap(2);
        map.put("A", "1");
        map.put("B", "2");
        System.out.println("Buckets after 2 items: " + map.bucketCount());
        map.put("C", "3");
        System.out.println("Buckets after rehash: " + map.bucketCount());
        map.put("A", "99"); 
        System.out.println("Size: " + map.size() + ", Value A: " + map.get("A"));
    }
}