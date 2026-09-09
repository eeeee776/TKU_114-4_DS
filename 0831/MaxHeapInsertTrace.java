import java.util.ArrayList;
import java.util.List;

public class MaxHeapInsertTrace {
    private final List<Integer> data = new ArrayList<>();

    public void add(int value) {
        data.add(value);
        int index = data.size() - 1;
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (data.get(parent) >= data.get(index)) break;
            int temp = data.get(parent);
            data.set(parent, data.get(index));
            data.set(index, temp);
            index = parent;
        }
        System.out.println(snapshot());
    }

    public Integer peekMax() {
        return data.isEmpty() ? null : data.get(0);
    }

    public List<Integer> snapshot() {
        return List.copyOf(data);
    }

    public static void main(String[] args) {
        MaxHeapInsertTrace heap = new MaxHeapInsertTrace();
        int[] values = {25, 40, 10, 50, 30, 50};
        for (int v : values) {
            heap.add(v);
        }
        System.out.println("Root: " + heap.peekMax());
    }
}