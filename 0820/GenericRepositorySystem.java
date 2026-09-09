import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;

    Product(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Repository<T> {
    private List<T> items = new ArrayList<>();

    void add(T item) {
        items.add(item);
    }

    T get(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    void remove(T item) {
        items.remove(item);
    }

    int size() {
        return items.size();
    }

    void printAll() {
        System.out.println("Repository contents: " + items);
    }
}

public class GenericRepositorySystem {
    public static void main(String[] args) {
        Repository<String> stringRepo = new Repository<>();
        stringRepo.add("Java");
        stringRepo.add("Python");
        stringRepo.add("C++");
        System.out.println("String Repo Size: " + stringRepo.size());
        stringRepo.remove("Python");
        stringRepo.printAll();

        Repository<Product> productRepo = new Repository<>();
        Product p1 = new Product("Laptop");
        Product p2 = new Product("Mouse");
        productRepo.add(p1);
        productRepo.add(p2);
        System.out.println("Product at index 1: " + productRepo.get(1));
        productRepo.printAll();
    }
}