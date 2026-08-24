import java.util.*;

class DeliveryTask {
    String id;
    String destination;
    boolean isDelivered;

    public DeliveryTask(String id, String destination) {
        this.id = id;
        this.destination = destination;
        this.isDelivered = false;
    }
    
    @Override
    public String toString() { 
        return id + "(" + destination + ") - " + (isDelivered ? "已送達" : "配送中");
    }
}

public class DeliveryWorkflowSystem {
    private Map<String, DeliveryTask> taskMap = new HashMap<>();
    private Deque<DeliveryTask> waitingQueue = new ArrayDeque<>();
    private Deque<DeliveryTask> historyStack = new ArrayDeque<>();

    public void addDelivery(String id, String dest) {
        if (taskMap.containsKey(id)) {
            System.out.println("新增失敗，編號 " + id + " 已存在。");
            return;
        }
        DeliveryTask task = new DeliveryTask(id, dest);
        taskMap.put(id, task);
        waitingQueue.offerLast(task);
        System.out.println("新增配送任務: " + task);
    }

    public void processNext() {
        DeliveryTask task = waitingQueue.pollFirst();
        if (task != null) {
            task.isDelivered = true;
            historyStack.push(task);
            System.out.println("處理完成: " + task);
        } else {
            System.out.println("無等待中的配送。");
        }
    }

    public void undoLast() {
        DeliveryTask task = historyStack.pollFirst();
        if (task != null) {
            task.isDelivered = false;
            waitingQueue.offerFirst(task); // 放回隊列前端優先處理
            System.out.println("復原操作: " + task);
        }
    }

    public void query(String id) {
        DeliveryTask task = taskMap.get(id);
        System.out.println("查詢結果: " + (task == null ? "查無此編號" : task));
    }

    public void printStats() {
        System.out.println("[統計] 待配送: " + waitingQueue.size() + ", 已完成: " + historyStack.size());
    }

    public static void main(String[] args) {
        DeliveryWorkflowSystem sys = new DeliveryWorkflowSystem();
        sys.addDelivery("T101", "台北市");
        sys.addDelivery("T102", "新北市");
        sys.addDelivery("T101", "桃園市"); // 測試重複

        sys.processNext();
        sys.printStats();
        
        sys.query("T101"); // 狀態應為已送達
        
        sys.undoLast();
        sys.query("T101"); // 狀態應變回配送中
        sys.printStats();
    }
}