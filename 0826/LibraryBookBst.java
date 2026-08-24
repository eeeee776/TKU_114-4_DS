class Book {
    int isbn;
    String title;
    boolean available;

    Book(int isbn, String title) {
        this.isbn = isbn;
        this.title = title;
        this.available = true;
    }
    @Override
    public String toString() { return "[" + isbn + "] " + title + " (Available: " + available + ")"; }
}

class BookNode {
    Book data; BookNode left, right;
    BookNode(Book data) { this.data = data; }
}

public class LibraryBookBst {
    private BookNode root;

    public void add(Book book) {
        if (root == null) { root = new BookNode(book); return; }
        BookNode cur = root;
        while (true) {
            if (book.isbn == cur.data.isbn) return;
            if (book.isbn < cur.data.isbn) {
                if (cur.left == null) { cur.left = new BookNode(book); return; }
                cur = cur.left;
            } else {
                if (cur.right == null) { cur.right = new BookNode(book); return; }
                cur = cur.right;
            }
        }
    }

    public Book find(int isbn) {
        BookNode cur = root;
        while (cur != null) {
            if (isbn == cur.data.isbn) return cur.data;
            cur = isbn < cur.data.isbn ? cur.left : cur.right;
        }
        return null;
    }

    public boolean borrowBook(int isbn) {
        Book b = find(isbn);
        if (b != null && b.available) {
            b.available = false;
            return true;
        }
        return false;
    }

    public boolean remove(int isbn) {
        Book b = find(isbn);
        if (b == null) return false;
        if (!b.available) {
            System.out.println("Cannot remove: Book " + isbn + " is currently borrowed.");
            return false;
        }
        root = removeNode(root, isbn);
        return true;
    }

    private BookNode removeNode(BookNode node, int isbn) {
        // ... (標準刪除流程，為精簡版面略)
        if (isbn < node.data.isbn) node.left = removeNode(node.left, isbn);
        else if (isbn > node.data.isbn) node.right = removeNode(node.right, isbn);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            BookNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.data = successor.data;
            node.right = removeNode(node.right, successor.data.isbn);
        }
        return node;
    }

    public static void main(String[] args) {
        LibraryBookBst lib = new LibraryBookBst();
        lib.add(new Book(1002, "Clean Code"));
        lib.add(new Book(1001, "Java Concurrency"));
        lib.add(new Book(1003, "Design Patterns"));

        System.out.println("Borrow 1002: " + lib.borrowBook(1002));
        System.out.println("Try remove borrowed 1002:");
        lib.remove(1002); // 應該印出錯誤訊息且回傳 false
        
        System.out.println("Remove available 1001: " + lib.remove(1001)); // true
    }
}