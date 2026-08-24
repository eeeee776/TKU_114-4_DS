import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class StoreProduct implements Comparable<StoreProduct> {
    private final String id;
    private final String name;
    private final int price;
    private final int stock;

    public StoreProduct(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getStock() { return stock; }

    // Natural order: 依 id 升冪
    @Override
    public int compareTo(StoreProduct other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - 價格:%d 庫存:%d", id, name, price, stock);
    }
}

public class ProductComparatorPractice {
    public static void main(String[] args) {
        List<StoreProduct> products = List.of(
            new StoreProduct("P03", "Mouse", 500, 10),
            new StoreProduct("P01", "Keyboard", 1200, 5),
            new StoreProduct("P05", "Monitor", 5000, 2),
            new StoreProduct("P02", "USB Cable", 500, 20), // 價格與 Mouse 同
            new StoreProduct("P04", "Headset", 1500, 5)    // 庫存與 Keyboard 同
        );

        System.out.println("=== 原始加入順序 ===");
        products.forEach(System.out::println);

        // 1. Natural order (ID 升冪)
        List<StoreProduct> byId = new ArrayList<>(products);
        byId.sort(null); // 傳入 null 會使用 class 本身的 compareTo
        System.out.println("\n=== 依 ID 升冪 (Natural Order) ===");
        byId.forEach(System.out::println);

        // 2. 依 price 升冪，同價時依 name
        List<StoreProduct> byPrice = new ArrayList<>(products);
        byPrice.sort(Comparator.comparingInt(StoreProduct::getPrice)
                               .thenComparing(StoreProduct::getName));
        System.out.println("\n=== 依 價格升冪 -> 名稱升冪 ===");
        byPrice.forEach(System.out::println);

        // 3. 依 stock 降冪，同庫存時依 id
        List<StoreProduct> byStock = new ArrayList<>(products);
        byStock.sort(Comparator.comparingInt(StoreProduct::getStock).reversed()
                               .thenComparing(StoreProduct::getId));
        System.out.println("\n=== 依 庫存降冪 -> ID 升冪 ===");
        byStock.forEach(System.out::println);
    }
}