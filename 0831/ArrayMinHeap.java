import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayMinHeap {
    private int[] data;
    private int size;
    private static final int INITIAL_CAPACITY = 4;

    public ArrayMinHeap() {
        this.data = new int[INITIAL_CAPACITY];
        this.size = 0;
    }

    public void add(int value) {
        ensureCapacity();
        data[size] = value;
        bubbleUp(size);
        size++;
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return data[0];
    }

    public int removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        int min = data[0];
        data[0] = data[size - 1];
        size--;
        if (size > 0) {
            bubbleDown(0);
        }
        return min;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int[] snapshot() {
        return Arrays.copyOf(data, size);
    }

    private void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

    private void bubbleUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (data[parent] <= data[index]) {
                break;
            }
            swap(parent, index);
            index = parent;
        }
    }

    private void bubbleDown(int index) {
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            if (left >= size) return;

            int smaller = left;
            if (right < size && data[right] < data[left]) {
                smaller = right;
            }

            if (data[index] <= data[smaller]) {
                return;
            }

            swap(index, smaller);
            index = smaller;
        }
    }

    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main(String[] args) {
        ArrayMinHeap heap = new ArrayMinHeap();
        int[] testData = {
            95, 12, 34, 78, 5, 23, 89, 44, 1, 67,
            55, 30, 71, 18, 9, 82, 37, 60, 4, 50, 100
        };

        System.out.println("新增 21 筆資料（驗證多次擴容）...");
        for (int v : testData) {
            heap.add(v);
        }

        System.out.println("當前 Heap 大小: " + heap.size());
        System.out.println("當前 Heap 陣列快照: " + Arrays.toString(heap.snapshot()));
        System.out.println("當前最小元素 (peek): " + heap.peek());

        System.out.print("取出所有元素驗證排序: ");
        int previous = Integer.MIN_VALUE;
        while (!heap.isEmpty()) {
            int current = heap.removeMin();
            System.out.print(current + " ");
            if (current < previous) {
                throw new IllegalStateException("Heap 特性被破壞: " + current + " < " + previous);
            }
            previous = current;
        }
        System.out.println("\n所有元素皆成功以非遞減順序輸出。");
    }
}