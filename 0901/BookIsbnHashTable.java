import java.util.ArrayList;
import java.util.List;

public class BookIsbnHashTable {
    private record BookEntry(String isbn, String title) {}

    private final List<List<BookEntry>> buckets;
    private int size;

    public BookIsbnHashTable(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be > 0");
        buckets = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    private int hashIndex(String isbn) {
        if (isbn == null) throw new IllegalArgumentException("ISBN cannot be null");
        return Math.floorMod(isbn.hashCode(), buckets.size());
    }

    public void put(String isbn, String title) {
        int index = hashIndex(isbn);
        List<BookEntry> chain = buckets.get(index);
        
        // 檢查是否已存在，存在則更新
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).isbn().equals(isbn)) {
                chain.set(i, new BookEntry(isbn, title));
                return;
            }
        }
        
        // 不存在則新增
        chain.add(new BookEntry(isbn, title));
        size++;
    }

    public String search(String isbn) {
        int index = hashIndex(isbn);
        for (BookEntry entry : buckets.get(index)) {
            if (entry.isbn().equals(isbn)) return entry.title();
        }
        return null;
    }

    public boolean remove(String isbn) {
        int index = hashIndex(isbn);
        List<BookEntry> chain = buckets.get(index);
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).isbn().equals(isbn)) {
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

    public double loadFactor() {
        return (double) size / buckets.size();
    }

    public void printBucketReport() {
        System.out.println("=== Hash Table 內部結構 ===");
        for (int i = 0; i < buckets.size(); i++) {
            System.out.println("Bucket " + i + " -> " + buckets.get(i));
        }
    }

    public static void main(String[] args) {
        BookIsbnHashTable table = new BookIsbnHashTable(5);
        table.put("978-0134685991", "Effective Java");
        table.put("978-0201633610", "Design Patterns");
        table.put("978-0134685991", "Effective Java (3rd Edition)"); // 測試更新
        table.put("978-1491956250", "Learning Java");
        
        table.printBucketReport();
        System.out.println("Size: " + table.size());
        System.out.printf("Load Factor: %.2f%n", table.loadFactor());
        System.out.println("Search '978-0134685991': " + table.search("978-0134685991"));
        
        table.remove("978-0201633610");
        System.out.println("After removing Design Patterns, Size: " + table.size());
    }
}