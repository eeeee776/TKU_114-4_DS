import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class IntegerMinHeap {
    private final List<Integer> data = new ArrayList<>();

    public void add(int value) {
        data.add(value);
        int index = data.size() - 1;
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (data.get(parent) <= data.get(index)) {
                break;
            }
            swap(parent, index);
            index = parent;
        }
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return data.get(0);
    }

    public int removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        int result = data.get(0);
        int last = data.remove(data.size() - 1);
        if (!isEmpty()) {
            data.set(0, last);
            bubbleDown(0);
        }
        return result;
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private void bubbleDown(int index) {
        int size = data.size();
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            if (left >= size) {
                return;
            }

            int smaller = left;
            if (right < size && data.get(right) < data.get(left)) {
                smaller = right;
            }

            if (data.get(index) <= data.get(smaller)) {
                return;
            }

            swap(index, smaller);
            index = smaller;
        }
    }

    private void swap(int i, int j) {
        int temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    public static void main(String[] args) {
        IntegerMinHeap heap = new IntegerMinHeap();
        int[] values = {45, 12, 85, 32, 89, 39, 69, 22};
        for (int v : values) {
            heap.add(v);
        }

        System.out.println("Initial size: " + heap.size());
        System.out.println("Peek: " + heap.peek());

        System.out.print("Removed order (should be non-decreasing): ");
        int previous = Integer.MIN_VALUE;
        while (!heap.isEmpty()) {
            int current = heap.removeMin();
            System.out.print(current + " ");
            if (current < previous) {
                throw new IllegalStateException("Heap order violated: " + current + " < " + previous);
            }
            previous = current;
        }
        System.out.println();

        try {
            heap.peek();
        } catch (NoSuchElementException e) {
            System.out.println("Verified peek on empty heap throws NoSuchElementException");
        }
    }
}