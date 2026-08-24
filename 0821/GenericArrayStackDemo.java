class ArrayStack<T> {
    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        // Java 不允許直接 new T[]，需透過 Object[] 轉型
        data = (T[]) new Object[Math.max(1, capacity)];
    }

    public boolean push(T value) {
        if (isFull() || value == null) return false;
        data[size++] = value;
        return true;
    }

    public T pop() {
        if (isEmpty()) return null;
        size--;
        T value = data[size];
        data[size] = null; // 清除參考，避免 memory leak
        return value;
    }

    public T peek() {
        return isEmpty() ? null : data[size - 1];
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == data.length; }
}

public class GenericArrayStackDemo {
    public static void main(String[] args) {
        // 測試 String 型態
        ArrayStack<String> stringStack = new ArrayStack<>(2);
        stringStack.push("Hello");
        stringStack.push("World");
        System.out.println("String Stack full? " + stringStack.isFull());
        System.out.println("String Stack pop: " + stringStack.pop());

        // 測試 Integer 型態
        ArrayStack<Integer> intStack = new ArrayStack<>(3);
        intStack.push(100);
        intStack.push(200);
        System.out.println("Integer Stack peek: " + intStack.peek());
        System.out.println("Integer Stack pop: " + intStack.pop());
    }
}