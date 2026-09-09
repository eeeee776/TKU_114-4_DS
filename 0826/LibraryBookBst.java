import java.util.ArrayList;
import java.util.List;

class LibraryBook {
    String isbn;
    String title;
    String author;
    boolean available;

    LibraryBook(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    @Override
    public String toString() {
        return isbn + " " + title + " (" + (available ? "Available" : "Borrowed") + ")";
    }
}

class BookNode {
    LibraryBook data;
    BookNode left;
    BookNode right;

    BookNode(LibraryBook data) {
        this.data = data;
    }
}

public class LibraryBookBst {
    private BookNode root;

    boolean add(LibraryBook book) {
        if (book == null || book.isbn == null) return false;
        if (root == null) {
            root = new BookNode(book);
            return true;
        }
        BookNode current = root;
        while (true) {
            int cmp = book.isbn.compareTo(current.data.isbn);
            if (cmp == 0) return false;
            if (cmp < 0) {
                if (current.left == null) {
                    current.left = new BookNode(book);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new BookNode(book);
                    return true;
                }
                current = current.right;
            }
        }
    }

    LibraryBook find(String isbn) {
        if (isbn == null) return null;
        BookNode current = root;
        while (current != null) {
            int cmp = isbn.compareTo(current.data.isbn);
            if (cmp == 0) return current.data;
            current = cmp < 0 ? current.left : current.right;
        }
        return null;
    }

    boolean borrow(String isbn) {
        LibraryBook b = find(isbn);
        if (b != null && b.available) {
            b.available = false;
            return true;
        }
        return false;
    }

    boolean returnBook(String isbn) {
        LibraryBook b = find(isbn);
        if (b != null && !b.available) {
            b.available = true;
            return true;
        }
        return false;
    }

    boolean remove(String isbn) {
        LibraryBook b = find(isbn);
        if (b == null || !b.available) return false;
        root = removeHelper(root, isbn);
        return true;
    }

    private BookNode removeHelper(BookNode node, String isbn) {
        if (node == null) return null;
        int cmp = isbn.compareTo(node.data.isbn);
        if (cmp < 0) {
            node.left = removeHelper(node.left, isbn);
        } else if (cmp > 0) {
            node.right = removeHelper(node.right, isbn);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            BookNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.data = successor.data;
            node.right = removeHelper(node.right, successor.data.isbn);
        }
        return node;
    }

    List<LibraryBook> rangeQuery(String start, String end) {
        List<LibraryBook> res = new ArrayList<>();
        if (start != null && end != null && start.compareTo(end) <= 0) {
            rangeHelper(root, start, end, res);
        }
        return res;
    }

    private void rangeHelper(BookNode node, String start, String end, List<LibraryBook> res) {
        if (node == null) return;
        if (node.data.isbn.compareTo(start) > 0) rangeHelper(node.left, start, end, res);
        if (node.data.isbn.compareTo(start) >= 0 && node.data.isbn.compareTo(end) <= 0) res.add(node.data);
        if (node.data.isbn.compareTo(end) < 0) rangeHelper(node.right, start, end, res);
    }

    void inorderReport() {
        inorderHelper(root);
        System.out.println();
    }

    private void inorderHelper(BookNode node) {
        if (node == null) return;
        inorderHelper(node.left);
        System.out.println(node.data);
        inorderHelper(node.right);
    }

    public static void main(String[] args) {
        LibraryBookBst lib = new LibraryBookBst();
        lib.add(new LibraryBook("B002", "Java", "Author A"));
        lib.add(new LibraryBook("B001", "C++", "Author B"));
        lib.add(new LibraryBook("B003", "Python", "Author C"));

        lib.borrow("B002");
        System.out.println("Remove borrowed: " + lib.remove("B002"));
        lib.returnBook("B002");
        System.out.println("Remove returned: " + lib.remove("B002"));

        lib.inorderReport();
    }
}