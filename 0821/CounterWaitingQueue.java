import java.util.ArrayDeque;
import java.util.Deque;

class Customer {
    private String name;

    Customer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class CounterWaitingQueue {
    public static void main(String[] args) {
        Deque<Customer> waitingQueue = new ArrayDeque<>();

        waitingQueue.offerLast(new Customer("Amy"));
        waitingQueue.offerLast(new Customer("Ben"));
        waitingQueue.offerLast(new Customer("Cara"));

        System.out.println("目前等候人數: " + waitingQueue.size());
        System.out.println("下一位準備: " + waitingQueue.peekFirst());

        System.out.println("服務中: " + waitingQueue.pollFirst());
        System.out.println("服務中: " + waitingQueue.pollFirst());

        System.out.println("目前等候人數: " + waitingQueue.size());
        System.out.println("服務中: " + waitingQueue.pollFirst());
        
        System.out.println("無人等候時服務: " + waitingQueue.pollFirst());
    }
}