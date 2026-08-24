import java.util.ArrayList;
import java.util.List;

class Repository<T> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        if (item != null) {
            items.add(item);
        }
    }

    public T get(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public boolean remove(T item) {
        return items.remove(item);
    }

    public int size() {
        return items.size();
    }

    public void printAll() {
        System.out.println("Repository 內容 (共 " + size() + " 筆):");
        for (T item : items) {
            System.out.println(" - " + item);
        }
    }
}

class Product {
    private final String name;
    private final int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

public class GenericRepositorySystem {
    public static void main(String[] args) {
        // 測試字串 Repository
        Repository<String> stringRepo = new Repository<>();
        stringRepo.add("Java");
        stringRepo.add("Generics");
        stringRepo.printAll();

        // 測試自訂物件 Repository
        Repository<Product> productRepo = new Repository<>();
        Product p1 = new Product("Laptop", 35000);
        productRepo.add(p1);
        productRepo.add(new Product("Mouse", 800));
        
        System.out.println("\n移除前：");
        productRepo.printAll();
        
        productRepo.remove(p1);
        System.out.println("\n移除後：");
        productRepo.printAll();
    }
}