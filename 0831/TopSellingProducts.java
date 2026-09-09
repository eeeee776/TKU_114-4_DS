import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class ProductSales {
    String id;
    int sales;

    ProductSales(String id, int sales) {
        this.id = id;
        this.sales = sales;
    }

    @Override
    public String toString() {
        return id + "(" + sales + ")";
    }
}

public class TopSellingProducts {
    public static List<ProductSales> getTopK(List<ProductSales> records, int k) {
        if (records == null || k <= 0) return new ArrayList<>();

        Map<String, Integer> salesMap = new HashMap<>();
        for (ProductSales p : records) {
            if (p != null && p.id != null) {
                salesMap.put(p.id, salesMap.getOrDefault(p.id, 0) + Math.max(0, p.sales));
            }
        }

        Comparator<ProductSales> minHeapComp = Comparator.comparingInt((ProductSales p) -> p.sales)
                .thenComparing(p -> p.id, Comparator.reverseOrder());

        PriorityQueue<ProductSales> heap = new PriorityQueue<>(minHeapComp);

        for (Map.Entry<String, Integer> entry : salesMap.entrySet()) {
            heap.offer(new ProductSales(entry.getKey(), entry.getValue()));
            if (heap.size() > k) {
                heap.poll();
            }
        }

        List<ProductSales> result = new ArrayList<>();
        while (!heap.isEmpty()) {
            result.add(heap.poll());
        }

        result.sort(Comparator.comparingInt((ProductSales p) -> p.sales).reversed()
                .thenComparing(p -> p.id));

        return result;
    }

    public static void main(String[] args) {
        List<ProductSales> records = new ArrayList<>();
        records.add(new ProductSales("P01", 100));
        records.add(new ProductSales("P02", 150));
        records.add(new ProductSales("P01", 50));
        records.add(new ProductSales("P03", 200));
        records.add(new ProductSales("P04", 150));
        records.add(new ProductSales("P05", 80));

        System.out.println("Top 3: " + getTopK(records, 3));
        System.out.println("Top 1: " + getTopK(records, 1));
        System.out.println("Top 10: " + getTopK(records, 10));
    }
}