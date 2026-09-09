import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class StoreProduct implements Comparable<StoreProduct> {
    private String id;
    private String name;
    private int price;
    private int stock;

    StoreProduct(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    String getId() { return id; }
    String getName() { return name; }
    int getPrice() { return price; }
    int getStock() { return stock; }

    @Override
    public int compareTo(StoreProduct other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return id + " " + name + " $" + price + " (stock:" + stock + ")";
    }
}

public class ProductComparatorPractice {
    public static void main(String[] args) {
        List<StoreProduct> products = new ArrayList<>();
        products.add(new StoreProduct("P03", "Mouse", 500, 10));
        products.add(new StoreProduct("P01", "Keyboard", 1500, 5));
        products.add(new StoreProduct("P05", "Monitor", 5000, 5));
        products.add(new StoreProduct("P02", "USB", 500, 20));
        products.add(new StoreProduct("P04", "Pad", 300, 20));

        List<StoreProduct> byId = new ArrayList<>(products);
        Collections.sort(byId);
        System.out.println("--- 1. Natural Order (by ID ASC) ---");
        for (StoreProduct p : byId) System.out.println(p);

        List<StoreProduct> byPriceThenName = new ArrayList<>(products);
        byPriceThenName.sort(Comparator.comparingInt(StoreProduct::getPrice).thenComparing(StoreProduct::getName));
        System.out.println("\n--- 2. By Price ASC, then Name ASC ---");
        for (StoreProduct p : byPriceThenName) System.out.println(p);

        List<StoreProduct> byStockThenId = new ArrayList<>(products);
        byStockThenId.sort(Comparator.comparingInt(StoreProduct::getStock).reversed().thenComparing(StoreProduct::getId));
        System.out.println("\n--- 3. By Stock DESC, then ID ASC ---");
        for (StoreProduct p : byStockThenId) System.out.println(p);
    }
}