import java.util.ArrayList;
import java.util.List;

class BookEntry {
    String isbn;
    String title;
    String author;

    BookEntry(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return isbn + ": " + title + " by " + author;
    }
}

public class BookIsbnHashTable {
    private final List<List<BookEntry>> buckets;
    private int size;

    public BookIsbnHashTable(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException();
        buckets = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    private int getIndex(String isbn) {
        return Math.floorMod(isbn.hashCode(), buckets.size());
    }

    public void put(String isbn, String title, String author) {
        if (isbn == null || isbn.isBlank()) return;
        int index = getIndex(isbn);
        List<BookEntry> chain = buckets.get(index);
        
        for (BookEntry entry : chain) {
            if (entry.isbn.equals(isbn)) {
                entry.title = title;
                entry.author = author;
                return;
            }
        }
        chain.add(new BookEntry(isbn, title, author));
        size++;
    }

    public String get(String isbn) {
        if (isbn == null) return null;
        List<BookEntry> chain = buckets.get(getIndex(isbn));
        for (BookEntry entry : chain) {
            if (entry.isbn.equals(isbn)) return entry.title;
        }
        return null;
    }

    public boolean remove(String isbn) {
        if (isbn == null) return false;
        List<BookEntry> chain = buckets.get(getIndex(isbn));
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).isbn.equals(isbn)) {
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

    public void bucketReport() {
        System.out.println("--- Bucket Report (Load Factor: " + String.format("%.2f", loadFactor()) + ") ---");
        for (int i = 0; i < buckets.size(); i++) {
            System.out.println("Bucket " + i + ": " + buckets.get(i));
        }
    }

    public static void main(String[] args) {
        BookIsbnHashTable table = new BookIsbnHashTable(3);
        table.put("978-A", "Java Fundamentals", "Alice");
        table.put("978-B", "Data Structures", "Bob");
        table.put("978-A", "Java Advanced", "Alice"); 
        table.put("978-C", "Algorithms", "Cara");

        table.bucketReport();

        System.out.println("Get 978-B: " + table.get("978-B"));
        System.out.println("Remove 978-B: " + table.remove("978-B"));
        System.out.println("Remove 978-Z: " + table.remove("978-Z"));
        
        table.bucketReport();
    }
}