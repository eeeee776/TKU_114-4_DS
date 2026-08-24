
import java.util.Arrays;

class CircularQueue<T> {
    private final Object[] data;
    private int front = 0;
    private int rear = 0;
    private int size = 0;

    public CircularQueue(int capacity) {
        data = new Object[Math.max(1, capacity)];
    }

    public boolean enqueue(T value) {
        if (size == data.length) return false;
        data[rear] = value;
        rear = (rear + 1) % data.length;
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (size == 0) return null;
        T value = (T) data[front];
        data[front] = null; // 清空參考
        front = (front + 1) % data.length;
        size--;
        return value;
    }

    public void printState() {
        System.out.printf("Array: %s | front: %d | rear: %d | size: %d%n",
                Arrays.toString(data), front, rear, size);
    }
    
    public int size() { return size; }
}

public class CircularQueuePractice {
    public static void main(String[] args) {
        CircularQueue<String> queue = new CircularQueue<>(4);

        System.out.println("--- enqueue A, B, C ---");
        queue.enqueue("A"); queue.enqueue("B"); queue.enqueue("C");
        queue.printState();

        System.out.println("--- dequeue x2 ---");
        queue.dequeue(); queue.dequeue();
        queue.printState();

        System.out.println("--- enqueue D, E, F ---");
        queue.enqueue("D"); queue.enqueue("E"); 
        boolean fSuccess = queue.enqueue("F"); // 容量 4，目前已有 C, D, E，加入 F 後滿載
        System.out.println("加入 F 成功? " + fSuccess);
        queue.printState();

        System.out.println("--- dequeue, enqueue G ---");
        queue.dequeue(); 
        queue.enqueue("G");
        queue.printState();

        System.out.println("--- 取出所有元素 (FIFO) ---");
        while(queue.size() > 0) {
            System.out.print(queue.dequeue() + " ");
        }
        System.out.println();
    }
}