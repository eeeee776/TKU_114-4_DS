import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class DeliveryTask {
    private String id;
    private String destination;
    private boolean completed;

    DeliveryTask(String id, String destination) {
        this.id = id;
        this.destination = destination;
    }

    String getId() { return id; }
    void setCompleted(boolean completed) { this.completed = completed; }
    
    @Override
    public String toString() {
        return id + " to " + destination + " (Done: " + completed + ")";
    }
}

public class DeliveryWorkflowSystem {
    private Map<String, DeliveryTask> map = new HashMap<>();
    private Deque<DeliveryTask> waitingQueue = new ArrayDeque<>();
    private Deque<DeliveryTask> completedStack = new ArrayDeque<>();

    boolean addTask(String id, String destination) {
        if (map.containsKey(id)) return false;
        DeliveryTask task = new DeliveryTask(id, destination);
        map.put(id, task);
        waitingQueue.offerLast(task);
        return true;
    }

    void processNext() {
        DeliveryTask task = waitingQueue.pollFirst();
        if (task != null) {
            task.setCompleted(true);
            completedStack.push(task);
            System.out.println("已配送: " + task);
        }
    }

    void undo() {
        DeliveryTask task = completedStack.pollFirst();
        if (task != null) {
            task.setCompleted(false);
            waitingQueue.offerFirst(task);
            System.out.println("復原配送: " + task);
        }
    }

    void printQueryAndStats(String id) {
        System.out.println("查詢結果: " + map.get(id));
        System.out.println("總數: " + map.size() + " | 待處理: " + waitingQueue.size() + " | 已完成: " + completedStack.size());
    }

    public static void main(String[] args) {
        DeliveryWorkflowSystem sys = new DeliveryWorkflowSystem();
        sys.addTask("D01", "Taipei");
        sys.addTask("D02", "Taichung");
        System.out.println("重複加入 D01: " + sys.addTask("D01", "Tainan"));
        
        sys.processNext();
        sys.processNext();
        sys.undo();
        
        sys.printQueryAndStats("D02");
    }
}