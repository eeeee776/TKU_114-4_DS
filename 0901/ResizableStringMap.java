import java.util.ArrayList;
import java.util.List;

public class ResizableStringMap {
    private record Entry(String key, String value) {}

    private List<List<Entry>> buckets;
    private int size;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    public ResizableStringMap(int initialCapacity) {
        buckets = new ArrayList<>();
        for (int i = 0; i < initialCapacity; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    private int index(String key, int bucketCount) {
        if (key == null) throw new IllegalArgumentException("key cannot be null");
        return Math.floorMod(key.hashCode(), bucketCount);
    }

    public void put(String key, String value) {
        if (loadFactor() > LOAD_FACTOR_THRESHOLD) {
            rehash();
        }

        int idx = index(key, buckets.size());
        List<Entry> chain = buckets.get(idx);
        
        // 更新既有 key
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key().equals(key)) {
                chain.set(i, new Entry(key, value));
                return;
            }
        }
        
        // 新增 entry
        chain.add(new Entry(key, value));
        size++;
    }

    public String get(String key) {
        int idx = index(key, buckets.size());
        for (Entry entry : buckets.get(idx)) {
            if (entry.key().equals(key)) return entry.value();
        }
        return null;
    }

    private void rehash() {
        int newCapacity = buckets.size() * 2 + 1;
        List<List<Entry>> newBuckets = new ArrayList<>();
        for (int i = 0; i < newCapacity; i++) {
            newBuckets.add(new ArrayList<>());
        }

        System.out.println("[System] Rehashing... old capacity: " + buckets.size() + ", new capacity: " + newCapacity);

        // 重新分配所有 entry 到新 bucket
        for (List<Entry> chain : buckets) {
            for (Entry entry : chain) {
                int newIdx = index(entry.key(), newCapacity);
                newBuckets.get(newIdx).add(entry);
            }
        }
        buckets = newBuckets;
    }

    public double loadFactor() {
        return (double) size / buckets.size();
    }

    public static void main(String[] args) {
        ResizableStringMap map = new ResizableStringMap(3);
        map.put("A", "Apple");
        map.put("B", "Banana");
        System.out.printf("Size: %d, Load Factor: %.2f%n", map.size, map.loadFactor());
        
        // 第三次 put 會觸發 rehash (3/3 = 1.0 > 0.75)
        map.put("C", "Cherry"); 
        map.put("D", "Date");
        
        System.out.printf("Size: %d, Load Factor: %.2f, Buckets: %d%n", map.size, map.loadFactor(), map.buckets.size());
        System.out.println("Get C: " + map.get("C"));
    }
}