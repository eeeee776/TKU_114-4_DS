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
            if (data.get(parent) <= data.get(index)) break;
            int temp = data.get(parent);
            data.set(parent, data.get(index));
            data.set(index, temp);
            index = parent;
        }
    }

    public int peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return data.get(0);
    }

    public int removeMin() {
        if (isEmpty()) throw new NoSuchElementException();
        int result = data.get(0);
        int last = data.remove(data.size() - 1);
        if (!data.isEmpty()) {
            data.set(0, last);
            int index = 0;
            while (true) {
                int left = index * 2 + 1;
                int right = index * 2 + 2;
                if (left >= data.size()) break;
                int smaller = left;
                if (right < data.size() && data.get(right) < data.get(left)) {
                    smaller = right;
                }
                if (data.get(index) <= data.get(smaller)) break;
                int temp = data.get(index);
                data.set(index, data.get(smaller));
                data.set(smaller, temp);
                index = smaller;
            }
        }
        return result;
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public static void main(String[] args) {
        IntegerMinHeap heap = new IntegerMinHeap();
        int[] values = {40, 10, 30, 50, 20};
        for (int v : values) {
            heap.add(v);
        }
        while (!heap.isEmpty()) {
            System.out.print(heap.removeMin() + " ");
        }
        System.out.println();
    }
}