
import java.util.Arrays;

class DynamicArray<T> {
    private Object[] data;
    private int size;

    public DynamicArray(int initialCapacity) {
        data = new Object[Math.max(1, initialCapacity)];
    }

    public void add(T value) {
        ensureCapacity();
        data[size++] = value;
    }

    public void add(int index, T value) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("index: " + index);
        ensureCapacity();
        // 將 index 及其後的元素往後移一格
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    @SuppressWarnings("unchecked")
    public T set(int index, T value) {
        checkIndex(index);
        T oldValue = (T) data[index];
        data[index] = value;
        return oldValue;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        T removedValue = (T) data[index];
        // 將 index 後的元素往前移一格
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null; // 清除舊參考
        return removedValue;
    }

    public int size() { return size; }
    public int capacity() { return data.length; }

    private void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(data, size));
    }
}

public class DynamicArrayPractice {
    public static void main(String[] args) {
        DynamicArray<String> list = new DynamicArray<>(2);
        
        list.add("A");
        list.add("C");
        list.add(1, "B"); // 觸發擴容，並在中間插入
        System.out.println("目前陣列: " + list + ", Capacity: " + list.capacity());
        
        list.remove(0); // 測試刪除
        System.out.println("刪除 index 0 後: " + list);
        
        // 測試例外情形
        try {
            list.get(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("成功捕捉例外: " + e.getMessage());
        }
        
        try {
            list.remove(list.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("成功捕捉例外: " + e.getMessage());
        }
    }
}