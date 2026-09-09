import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayMinHeap {
    private int[] data;
    private int size;

    public ArrayMinHeap(int initialCapacity) {
        data = new int[Math.max(1, initialCapacity)];
        size = 0;
    }

    public void add(int value) {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[size] = value;
        int index = size;
        size++;
        
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (data[parent] <= data[index]) break;
            int temp = data[parent];
            data[parent] = data[index];
            data[index] = temp;
            index = parent;
        }
    }

    public int remove() {
        if (size == 0) throw new NoSuchElementException("Heap is empty");
        int result = data[0];
        size--;
        if (size > 0) {
            data[0] = data[size];
            int index = 0;
            while (true) {
                int left = index * 2 + 1;
                int right = index * 2 + 2;
                if (left >= size) break;

                int smaller = left;
                if (right < size && data[right] < data[left]) {
                    smaller = right;
                }

                if (data[index] <= data[smaller]) break;

                int temp = data[index];
                data[index] = data[smaller];
                data[smaller] = temp;
                index = smaller;
            }
        }
        return result;
    }

    public int peek() {
        if (size == 0) throw new NoSuchElementException("Heap is empty");
        return data[0];
    }

    public int[] snapshot() {
        return Arrays.copyOf(data, size);
    }

    public static void main(String[] args) {
        ArrayMinHeap heap = new ArrayMinHeap(2);
        for (int i = 20; i >= 1; i--) {
            heap.add(i);
        }

        System.out.println("Snapshot after 20 inserts: " + Arrays.toString(heap.snapshot()));
        System.out.println("Min: " + heap.peek());
        
        System.out.print("Removals: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(heap.remove() + " ");
        }
        System.out.println();
        System.out.println("Snapshot after 5 removals: " + Arrays.toString(heap.snapshot()));
    }
}