class Book {
    private String id;
    private String title;
    private int price;
    private int stock;

    Book(String id, String title, int price, int stock) {
        this.id = id;
        this.title = title;
        // 簡單驗證價格與庫存不為負數
        this.price = Math.max(0, price);
        this.stock = Math.max(0, stock);
    }

    int getPrice() {
        return price;
    }

    int getStock() {
        return stock;
    }

    // 讓物件自己計算自己的總價值，封裝商業邏輯
    int getInventoryValue() {
        return price * stock;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (定價: %d, 庫存: %d)", id, title, price, stock);
    }
}

public class BookArrayReport {
    public static void main(String[] args) {
        // 建立物件陣列，裡面存放 4 個完整的 Book 物件
        Book[] books = {
            new Book("B001", "Java 深入淺出", 600, 5),
            new Book("B002", "資料結構與演算法", 800, 2),
            new Book("B003", "物件導向設計模式", 550, 0),
            new Book("B004", "Clean Code", 700, 3)
        };

        System.out.println("--- 1. 書籍清單 ---");
        int totalValue = 0;
        Book mostExpensiveBook = books[0]; // 假設第一本是最貴的

        for (Book book : books) {
            System.out.println(book); // 自動呼叫 toString()
            
            // 2. 累加庫存總價值
            totalValue += book.getInventoryValue();
            
            // 3. 找出價格最高的書
            if (book.getPrice() > mostExpensiveBook.getPrice()) {
                mostExpensiveBook = book;
            }
        }

        System.out.println("\n--- 2. 庫存統計 ---");
        System.out.println("庫存總價值: $" + totalValue);
        System.out.println("價格最高的書: " + mostExpensiveBook);

        System.out.println("\n--- 3. 庫存警報 (數量 <= 3) ---");
        for (Book book : books) {
            if (book.getStock() <= 3) {
                System.out.println(book);
            }
        }
    }
}