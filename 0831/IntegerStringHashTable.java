import java.util.ArrayList;
import java.util.List;

public class IntegerStringHashTable {
    private record Entry(int key, String value) {}

    private final List<List<Entry>> buckets;
    private int size;
    private final int capacity;

    public IntegerStringHashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            this.buckets.add(new ArrayList<>());
        }
        this.size = 0;
    }

    private int getBucketIndex(int key) {
        return Math.floorMod(Integer.hashCode(key), capacity);
    }

    public void put(int key, String value) {
        int index = getBucketIndex(key);
        List<Entry> chain = buckets.get(index);
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key() == key) {
                chain.set(i, new Entry(key, value)); // key 相同，僅更新 value
                return;
            }
        }
        chain.add(new Entry(key, value));
        size++;
    }

    public String get(int key) {
        int index = getBucketIndex(key);
        for (Entry entry : buckets.get(index)) {
            if (entry.key() == key) {
                return entry.value();
            }
        }
        return null;
    }

    public boolean containsKey(int key) {
        return get(key) != null;
    }

    public boolean remove(int key) {
        int index = getBucketIndex(key);
        List<Entry> chain = buckets.get(index);
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

    public boolean isEmpty() {
        return size == 0;
    }

    public void bucketReport() {
        System.out.println("=== Hash Table 內部 Bucket 狀態 (總數: " + size + ") ===");
        for (int i = 0; i < capacity; i++) {
            List<Entry> chain = buckets.get(i);
            System.out.printf("Bucket [%02d] (長度 %d): ", i, chain.size());
            for (Entry e : chain) {
                System.out.print("[" + e.key() + "=" + e.value() + "] -> ");
            }
            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        IntegerStringHashTable table = new IntegerStringHashTable(5);

        table.put(10, "Ten");
        table.put(15, "Fifteen"); // 10 與 15 在 mod 5 碰撞
        table.put(-3, "Negative Three");
        table.put(7, "Seven");
        table.put(12, "Twelve");

        System.out.println("新增 5 筆資料後的狀態:");
        table.bucketReport();

        // 覆蓋 key 測試
        System.out.println("\n更新 key=15 為 'Fifteen-Updated'...");
        table.put(15, "Fifteen-Updated");
        System.out.println("size 應維持 5: 實際為 " + table.size());
        System.out.println("取得 key=15: " + table.get(15));

        // 刪除測試
        System.out.println("刪除 key=10: " + table.remove(10));
        System.out.println("再次查詢 key=10 是否存在: " + table.containsKey(10));
        System.out.println("刪除後 size: " + table.size());
        table.bucketReport();
    }
}