import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ServiceRequestSystem {
    static class Request implements Comparable<Request> {
        String id;
        int priority; // 數字越大優先級越高
        String description;

        Request(String id, int priority, String description) {
            this.id = id;
            this.priority = priority;
            this.description = description;
        }

        @Override
        public int compareTo(Request other) {
            return Integer.compare(other.priority, this.priority); // 降冪排序
        }
        @Override
        public String toString() { return String.format("[%s] (P:%d) %s", id, priority, description); }
    }

    private Map<String, Request> lookup = new HashMap<>();
    private PriorityQueue<Request> queue = new PriorityQueue<>();

    public void addRequest(String id, int priority, String desc) {
        if (lookup.containsKey(id)) return;
        Request req = new Request(id, priority, desc);
        lookup.put(id, req);
        queue.offer(req);
    }

    public Request processNext() {
        Request next = queue.poll();
        if (next != null) {
            lookup.remove(next.id); // 保持一致性
        }
        return next;
    }

    public boolean cancelRequest(String id) {
        Request req = lookup.remove(id);
        if (req != null) {
            queue.remove(req); // PriorityQueue 的 remove 是 O(n)，實務上若有效能考量會改用 Lazy Deletion
            return true;
        }
        return false;
    }

    public void printStatus() {
        System.out.printf("系統狀態: HashMap 大小 = %d, Queue 大小 = %d%n", lookup.size(), queue.size());
    }

    public static void main(String[] args) {
        ServiceRequestSystem system = new ServiceRequestSystem();
        
        system.addRequest("R1", 1, "密碼重置");
        system.addRequest("R2", 5, "伺服器當機 (緊急)");
        system.addRequest("R3", 3, "權限申請");

        System.out.println("--- 新增後狀態 ---");
        system.printStatus();

        System.out.println("\n--- 取消操作 (一般案例) ---");
        System.out.println("取消 R3 成功? " + system.cancelRequest("R3"));
        system.printStatus();

        System.out.println("\n--- 處理任務 (依照優先權) ---");
        System.out.println("處理中: " + system.processNext());
        System.out.println("處理中: " + system.processNext());
        system.printStatus();

        System.out.println("\n--- 邊界案例 (Empty) ---");
        System.out.println("處理中: " + system.processNext()); // null
        System.out.println("取消 R99 成功? " + system.cancelRequest("R99")); // false
    }
}