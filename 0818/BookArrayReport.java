class Book {
    private String id;
    private String title;
    private int price;
    private int stock;

    Book(String id, String title, int price, int stock) {
        this.id = id;
        this.title = title;
        this.price = Math.max(0, price);
        this.stock = Math.max(0, stock);
    }

    int getPrice() {
        return price;
    }

    int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "書號: " + id + ", 書名: " + title + ", 價格: $" + price + ", 庫存: " + stock;
    }
}

public class BookArrayReport {
    public static void main(String[] args) {
        Book[] books = {
            new Book("B001", "Java 程式設計", 650, 5),
            new Book("B002", "資料結構與演算法", 800, 2),
            new Book("B003", "物件導向設計", 550, 10),
            new Book("B004", "AI 輔助開發", 450, 3)
        };

        System.out.println("--- 1. 所有書籍清單 ---");
        for (Book book : books) {
            System.out.println(book);
        }

        int totalValue = 0;
        for (Book book : books) {
            totalValue += book.getPrice() * book.getStock();
        }
        System.out.println("\n--- 2. 庫存總價值 ---");
        System.out.println("總價值: $" + totalValue);

        Book mostExpensiveBook = books[0];
        for (Book book : books) {
            if (book.getPrice() > mostExpensiveBook.getPrice()) {
                mostExpensiveBook = book;
            }
        }
        System.out.println("\n--- 3. 價格最高的書 ---");
        System.out.println(mostExpensiveBook);

        System.out.println("\n--- 4. 庫存警報 (庫存 <= 3) ---");
        for (Book book : books) {
            if (book.getStock() <= 3) {
                System.out.println(book);
            }
        }
    }
}