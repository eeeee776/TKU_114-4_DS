import java.util.ArrayList;
import java.util.List;

public class MaxHeapInsertTrace {
    private final List<Integer> data = new ArrayList<>();

    public void add(int value) {
        data.add(value);
        int index = data.size() - 1;

        while (index > 0) {
            int parent = (index - 1) / 2;
            if (data.get(parent) >= data.get(index)) {
                break;
            }
            swap(parent, index);
            index = parent;
        }
        System.out.println("after add " + value + " -> " + data);
    }

    public Integer peekMax() {
        return data.isEmpty() ? null : data.get(0);
    }

    public List<Integer> snapshot() {
        return List.copyOf(data);
    }

    private void swap(int i, int j) {
        int temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    public static void main(String[] args) {
        MaxHeapInsertTrace maxHeap = new MaxHeapInsertTrace();
        int[] input = {25, 40, 10, 50, 30, 50};

        for (int val : input) {
            maxHeap.add(val);
        }

        System.out.println("Final Heap Snapshot: " + maxHeap.snapshot());
        System.out.println("Root (peekMax): " + maxHeap.peekMax());
    }
}