import java.util.List;

public class HeapPropertyValidator {
    public static boolean isMinHeap(List<Integer> heap) {
        if (heap == null) return false;
        if (heap.size() <= 1) return true;

        int size = heap.size();
        for (int i = 0; i < size / 2; i++) {
            int left = i * 2 + 1;
            int right = i * 2 + 2;

            if (left < size && heap.get(i) > heap.get(left)) {
                return false;
            }
            if (right < size && heap.get(i) > heap.get(right)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMaxHeap(List<Integer> heap) {
        if (heap == null) return false;
        if (heap.size() <= 1) return true;

        int size = heap.size();
        for (int i = 0; i < size / 2; i++) {
            int left = i * 2 + 1;
            int right = i * 2 + 2;

            if (left < size && heap.get(i) < heap.get(left)) {
                return false;
            }
            if (right < size && heap.get(i) < heap.get(right)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> minTree = List.of(5, 10, 20, 15, 30, 25);
        List<Integer> maxTree = List.of(50, 40, 30, 10, 20, 15);
        List<Integer> invalidTree = List.of(10, 5, 20);

        System.out.println("minTree isMin: " + isMinHeap(minTree));         // true
        System.out.println("minTree isMax: " + isMaxHeap(minTree));         // false
        System.out.println("maxTree isMax: " + isMaxHeap(maxTree));         // true
        System.out.println("invalidTree isMin: " + isMinHeap(invalidTree)); // false
        System.out.println("null test: " + isMinHeap(null));                // false
        System.out.println("empty test: " + isMinHeap(List.of()));          // true
    }
}