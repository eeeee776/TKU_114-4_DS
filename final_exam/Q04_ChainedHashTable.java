import java.util.ArrayList;
import java.util.List;

public class Q04_ChainedHashTable {
    private record Entry(int key, String value) {}

    private final List<List<Entry>> buckets;
    private int size;

    public Q04_ChainedHashTable(int bucketCount) {
        if (bucketCount <= 0) throw new IllegalArgumentException();
        buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
        size = 0;
    }

    private int getIndex(int key) {
        return Math.floorMod(Integer.hashCode(key), buckets.size());
    }

    public void put(int key, String value) {
        int index = getIndex(key);
        List<Entry> chain = buckets.get(index);
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key() == key) {
                chain.set(i, new Entry(key, value));
                return;
            }
        }
        chain.add(new Entry(key, value));
        size++;
    }

    public String get(int key) {
        int index = getIndex(key);
        for (Entry entry : buckets.get(index)) {
            if (entry.key() == key) {
                return entry.value();
            }
        }
        return null;
    }

    public boolean remove(int key) {
        int index = getIndex(key);
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

    public int longestChain() {
        int max = 0;
        for (List<Entry> chain : buckets) {
            if (chain.size() > max) {
                max = chain.size();
            }
        }
        return max;
    }
}