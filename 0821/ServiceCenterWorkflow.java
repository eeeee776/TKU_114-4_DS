import java.util.*;

class ServiceTicket {
    String id;
    String description;
    String status; // WAITING, COMPLETED, CANCELLED

    public ServiceTicket(String id, String description) {
        this.id = id;
        this.description = description;
        this.status = "WAITING";
    }
    @Override public String toString() { return id + " [" + status + "] " + description; }
}

public class ServiceCenterWorkflow {
    private Map<String, ServiceTicket> ticketMap = new HashMap<>();
    private Set<String> existingIds = new HashSet<>();
    private Deque<ServiceTicket> waitingQueue = new ArrayDeque<>();
    private Deque<ServiceTicket> completedStack = new ArrayDeque<>();

    public void createTicket(String id, String desc) {
        if (!existingIds.add(id)) {
            System.out.println("錯誤：票號 " + id + " 已存在。");
            return;
        }
        ServiceTicket ticket = new ServiceTicket(id, desc);
        ticketMap.put(id, ticket);
        waitingQueue.offerLast(ticket);
        System.out.println("建立票券: " + ticket);
    }

    public void processNext() {
        ServiceTicket ticket = waitingQueue.pollFirst();
        if (ticket == null) {
            System.out.println("目前無等待中的票券。");
            return;
        }
        ticket.status = "COMPLETED";
        completedStack.push(ticket);
        System.out.println("處理完成: " + ticket);
    }

    public void cancelWaiting(String id) {
        ServiceTicket ticket = ticketMap.get(id);
        if (ticket == null || !ticket.status.equals("WAITING")) {
            System.out.println("取消失敗：找不到票號 " + id + " 或該票券不在等待中。");
            return;
        }
        ticket.status = "CANCELLED";
        waitingQueue.remove(ticket); // 將其從 Queue 移除，Map 依然保留以供查詢
        System.out.println("已取消票券: " + ticket);
    }

    public void undoLastCompletion() {
        ServiceTicket ticket = completedStack.pollFirst();
        if (ticket == null) {
            System.out.println("復原失敗：沒有已完成的票券。");
            return;
        }
        ticket.status = "WAITING";
        waitingQueue.offerFirst(ticket); // 退回等待隊列的最前面
        System.out.println("已復原票券狀態: " + ticket);
    }

    public void findById(String id) {
        ServiceTicket ticket = ticketMap.get(id);
        System.out.println("查詢結果: " + (ticket != null ? ticket : "查無此票號"));
    }

    public void printSummary() {
        System.out.println("\n--- 狀態總結 ---");
        System.out.println("等待中: " + waitingQueue.size() + " | 已完成歷程: " + completedStack.size());
        System.out.println("等待隊列: " + waitingQueue);
        System.out.println("---------------\n");
    }

    public static void main(String[] args) {
        ServiceCenterWorkflow center = new ServiceCenterWorkflow();
        center.createTicket("S01", "網路報修");
        center.createTicket("S02", "密碼重置");
        center.createTicket("S03", "設備申請");
        center.createTicket("S01", "重複報修"); // 應該失敗

        center.cancelWaiting("S02"); // 取消成功
        center.cancelWaiting("S99"); // 取消不存在

        center.processNext(); // 處理 S01
        center.printSummary(); // 等待中: S03

        center.undoLastCompletion(); // 復原 S01
        center.undoLastCompletion(); // 復原失敗 (Stack 為空)
        
        center.printSummary(); // 等待中: S01(排第一), S03
        
        center.findById("S02"); // 應該顯示 CANCELLED
    }
}