import java.util.Arrays;

class DynamicArray<T> {
    private Object[] data;
    private int size;

    DynamicArray(int initialCapacity) {
        data = new Object[Math.max(1, initialCapacity)];
    }

    void add(T value) {
        ensureCapacity();
        data[size++] = value;
    }

    void add(int index, T value) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index: " + index);
        ensureCapacity();
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    @SuppressWarnings("unchecked")
    T set(int index, T value) {
        checkIndex(index);
        T old = (T) data[index];
        data[index] = value;
        return old;
    }

    @SuppressWarnings("unchecked")
    T remove(int index) {
        checkIndex(index);
        T removed = (T) data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--size] = null;
        return removed;
    }

    int size() { return size; }
    int capacity() { return data.length; }

    private void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }
    
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(data, size));
    }
}

public class DynamicArrayPractice {
    public static void main(String[] args) {
        DynamicArray<String> arr = new DynamicArray<>(2);
        arr.add("A");
        arr.add("B");
        arr.add(1, "X"); 
        System.out.println("After add at 1: " + arr + ", Capacity: " + arr.capacity());
        
        System.out.println("Removed at 0: " + arr.remove(0));
        System.out.println("After remove: " + arr);

        DynamicArray<Integer> intArr = new DynamicArray<>(1);
        intArr.add(100);
        try {
            intArr.remove(5);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Expected error caught: " + e.getMessage());
        }
    }
}