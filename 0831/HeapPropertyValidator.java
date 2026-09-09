import java.util.List;

public class HeapPropertyValidator {
    static boolean isMinHeap(List<Integer> heap) {
        if (heap == null) return false;
        for (int i = 0; i < heap.size(); i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < heap.size() && heap.get(i) > heap.get(left)) return false;
            if (right < heap.size() && heap.get(i) > heap.get(right)) return false;
        }
        return true;
    }

    static boolean isMaxHeap(List<Integer> heap) {
        if (heap == null) return false;
        for (int i = 0; i < heap.size(); i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < heap.size() && heap.get(i) < heap.get(left)) return false;
            if (right < heap.size() && heap.get(i) < heap.get(right)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isMinHeap(List.of(10, 20, 30, 40, 50)));
        System.out.println(isMinHeap(List.of(10, 50, 30, 20, 40)));
        System.out.println(isMaxHeap(List.of(50, 40, 30, 20, 10)));
        System.out.println(isMaxHeap(List.of(50, 20, 30, 40, 10)));
        System.out.println(isMinHeap(List.of()));
        System.out.println(isMaxHeap(List.of(42)));
        System.out.println(isMinHeap(null));
    }
}