import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LowestKPriceTracker {
    static List<Integer> lowestK(List<Integer> prices, int k) {
        if (prices == null || k <= 0) return new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (Integer price : prices) {
            if (price == null || price < 0) continue;
            maxHeap.offer(price);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        List<Integer> result = new ArrayList<>(maxHeap);
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        List<Integer> prices = new ArrayList<>();
        prices.add(150);
        prices.add(50);
        prices.add(200);
        prices.add(80);
        prices.add(-10);
        prices.add(null);
        prices.add(30);

        System.out.println(lowestK(prices, 3));
        System.out.println(lowestK(prices, 0));
        System.out.println(lowestK(null, 3));
    }
}