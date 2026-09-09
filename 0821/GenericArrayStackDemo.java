class ArrayStack<T> {
    private Object[] data;
    private int size;

    ArrayStack(int capacity) {
        data = new Object[Math.max(1, capacity)];
    }

    boolean push(T value) {
        if (isFull()) return false;
        data[size++] = value;
        return true;
    }

    @SuppressWarnings("unchecked")
    T pop() {
        if (isEmpty()) return null;
        T value = (T) data[--size];
        data[size] = null;
        return value;
    }

    @SuppressWarnings("unchecked")
    T peek() {
        return isEmpty() ? null : (T) data[size - 1];
    }

    int size() { return size; }
    boolean isEmpty() { return size == 0; }
    boolean isFull() { return size == data.length; }
}

public class GenericArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack<String> stringStack = new ArrayStack<>(2);
        System.out.println("Push A: " + stringStack.push("A"));
        System.out.println("Push B: " + stringStack.push("B"));
        System.out.println("Push C (Full): " + stringStack.push("C"));
        System.out.println("Pop: " + stringStack.pop());

        ArrayStack<Integer> intStack = new ArrayStack<>(3);
        intStack.push(10);
        intStack.push(20);
        System.out.println("Peek Int: " + intStack.peek());
        System.out.println("Size Int: " + intStack.size());
    }
}