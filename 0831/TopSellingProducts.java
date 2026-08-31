import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopSellingProducts {
    public record Product(String id, int sales) {}

    public static List<Product> topKProducts(List<Product> transactions, int k) {
        if (transactions == null || k <= 0) {
            return List.of();
        }

        // 1. 合併重複商品銷量
        Map<String, Integer> salesMap = new HashMap<>();
        for (Product p : transactions) {
            if (p != null && p.id() != null && p.sales() > 0) {
                salesMap.merge(p.id(), p.sales(), Integer::sum);
            }
        }

        // 2. 建立大小為 K 的 Min-Heap
        // 淘汰規則：銷售量越少者優先被淘汰；若銷售量相同，字典序越「大」者優先被淘汰
        Comparator<Product> minHeapComparator = Comparator
                .comparingInt(Product::sales)
                .thenComparing(Product::id, Comparator.reverseOrder());

        PriorityQueue<Product> pq = new PriorityQueue<>(minHeapComparator);

        for (Map.Entry<String, Integer> entry : salesMap.entrySet()) {
            Product current = new Product(entry.getKey(), entry.getValue());
            if (pq.size() < k) {
                pq.offer(current);
            } else if (minHeapComparator.compare(current, pq.peek()) > 0) {
                pq.poll();
                pq.offer(current);
            }
        }

        // 3. 輸出結果排序（銷量高在前；銷量相同字典序小在前）
        Comparator<Product> finalOrder = Comparator
                .comparingInt(Product::sales).reversed()
                .thenComparing(Product::id);

        List<Product> result = new ArrayList<>(pq);
        result.sort(finalOrder);
        return result;
    }

    public static void main(String[] args) {
        List<Product> transactions = List.of(
            new Product("Keyboard", 50),
            new Product("Mouse", 80),
            new Product("Monitor", 120),
            new Product("Keyboard", 70), // Keyboard 累計達 120 (與 Monitor 同分)
            new Product("USB-Cable", 200),
            new Product("Desk-Mat", 30),
            new Product("Webcam", 80)    // Webcam 與 Mouse 同為 80，但 "Mouse" 字典序較前
        );

        int k = 3;
        List<Product> top3 = topKProducts(transactions, k);
        System.out.println("Top " + k + " 熱門商品榜單:");
        for (Product p : top3) {
            System.out.printf("商品: %-10s | 總銷量: %d%n", p.id(), p.sales());
        }
    }
}