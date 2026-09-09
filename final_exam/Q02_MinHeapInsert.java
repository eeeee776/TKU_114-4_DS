import java.util.ArrayList;
import java.util.List;

public class Q02_MinHeapInsert {
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

    public Integer peek() {
        if (data.isEmpty()) return null;
        return data.get(0);
    }

    public int size() {
        return data.size();
    }

    public List<Integer> snapshot() {
        return new ArrayList<>(data);
    }

    public boolean isValidMinHeap() {
        for (int i = 0; i < data.size(); i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < data.size() && data.get(left) < data.get(i)) return false;
            if (right < data.size() && data.get(right) < data.get(i)) return false;
        }
        return true;
    }
}