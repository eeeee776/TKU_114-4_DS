import java.util.ArrayList;
import java.util.List;

public class Q03_MinHeapRemove {
    private final List<Integer> data;

    public Q03_MinHeapRemove(List<Integer> values) {
        this.data = new ArrayList<>();
        if (values != null) {
            for (Integer v : values) {
                if (v != null) {
                    data.add(v);
                }
            }
            for (int i = data.size() / 2 - 1; i >= 0; i--) {
                bubbleDown(i);
            }
        }
    }

    private void bubbleDown(int index) {
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

    public Integer removeMin() {
        if (data.isEmpty()) return null;
        int result = data.get(0);
        int last = data.remove(data.size() - 1);
        if (!data.isEmpty()) {
            data.set(0, last);
            bubbleDown(0);
        }
        return result;
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
}