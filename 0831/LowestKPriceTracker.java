import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LowestKPriceTracker {
    public static List<Integer> lowestK(List<Integer> prices, int k) {
        if (prices == null || k <= 0) {
            return List.of();
        }

        // 維護大小為 K 的 Max Heap，Root 隨時保存這 K 個最小候選中的「最大門檻值」
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for (Integer price : prices) {
            if (price == null || price < 0) {
                continue;
            }

            if (maxHeap.size() < k) {
                maxHeap.offer(price);
            } else if (price < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(price);
            }
        }

        List<Integer> result = new ArrayList<>(maxHeap);
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        List<Integer> prices = List.of(150, 45, 90, 10, -5, 200, 30, 85, 20);
        
        System.out.println("Lowest 4 prices: " + lowestK(prices, 4));
        System.out.println("k <= 0 test: " + lowestK(prices, 0));
        System.out.println("null input test: " + lowestK(null, 3));
    }
}