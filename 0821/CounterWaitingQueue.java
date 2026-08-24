import java.util.ArrayDeque;
import java.util.Deque;

class Customer {
    private String name;
    public Customer(String name) { this.name = name; }
    public String getName() { return name; }
    @Override public String toString() { return name; }
}

public class CounterWaitingQueue {
    public static void main(String[] args) {
        Deque<Customer> waitingQueue = new ArrayDeque<>();

        // 1. 加入隊列
        waitingQueue.offerLast(new Customer("Alice"));
        waitingQueue.offerLast(new Customer("Bob"));
        waitingQueue.offerLast(new Customer("Charlie"));
        System.out.println("目前等候人數: " + waitingQueue.size());

        // 2. 查看下一位
        Customer next = waitingQueue.peekFirst();
        System.out.println("下一位請準備: " + (next != null ? next.getName() : "無人等候"));

        // 3. 服務下一位
        while (!waitingQueue.isEmpty()) {
            Customer serving = waitingQueue.pollFirst();
            System.out.println("正在服務: " + serving.getName() + "，剩餘等候人數: " + waitingQueue.size());
        }

        // 4. 空隊列處理
        Customer emptyTest = waitingQueue.pollFirst();
        if (emptyTest == null) {
            System.out.println("目前櫃台無人等候，服務人員可休息。");
        }
    }
}