import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ServiceTicket {
    private String id;
    private String desc;
    private String status; 

    ServiceTicket(String id, String desc) {
        this.id = id;
        this.desc = desc;
        this.status = "Waiting";
    }

    String getId() { return id; }
    void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return id + " (" + desc + ") - " + status;
    }
}

public class ServiceCenterWorkflow {
    private Map<String, ServiceTicket> map = new HashMap<>();
    private Deque<ServiceTicket> waitingQueue = new ArrayDeque<>();
    private Deque<ServiceTicket> completedStack = new ArrayDeque<>();
    private Set<String> idSet = new HashSet<>();

    boolean createTicket(String id, String desc) {
        if (!idSet.add(id)) return false;
        ServiceTicket ticket = new ServiceTicket(id, desc);
        map.put(id, ticket);
        waitingQueue.offerLast(ticket);
        return true;
    }

    void processNext() {
        ServiceTicket ticket = waitingQueue.pollFirst();
        if (ticket != null) {
            ticket.setStatus("Completed");
            completedStack.push(ticket);
            System.out.println("處理完成: " + ticket);
        } else {
            System.out.println("目前無等待中工單");
        }
    }

    void cancelWaiting(String id) {
        ServiceTicket ticket = map.get(id);
        if (ticket != null && ticket.toString().contains("Waiting")) {
            waitingQueue.remove(ticket);
            ticket.setStatus("Cancelled");
            System.out.println("已取消: " + ticket);
        } else {
            System.out.println("取消失敗: 找不到或非等待中工單 (" + id + ")");
        }
    }

    void undoLastCompletion() {
        ServiceTicket ticket = completedStack.pollFirst();
        if (ticket != null) {
            ticket.setStatus("Waiting");
            waitingQueue.offerFirst(ticket); 
            System.out.println("已復原至等待隊列最前方: " + ticket);
        } else {
            System.out.println("復原失敗: 無已完成工單");
        }
    }

    void findById(String id) {
        System.out.println("查詢 " + id + ": " + (map.containsKey(id) ? map.get(id) : "找不到"));
    }

    void printSummary() {
        System.out.println("狀態統計 -> 待處理: " + waitingQueue.size() + ", 已完成: " + completedStack.size() + ", 總數: " + map.size());
    }

    public static void main(String[] args) {
        ServiceCenterWorkflow center = new ServiceCenterWorkflow();
        center.createTicket("S01", "Login issue");
        center.createTicket("S02", "Payment issue");
        center.createTicket("S03", "Bug report");
        System.out.println("重複建立 S01: " + center.createTicket("S01", "Another issue"));

        center.cancelWaiting("S02");
        center.cancelWaiting("S99"); 
        
        center.processNext(); 
        center.processNext(); 
        center.processNext(); 
        
        center.undoLastCompletion();
        center.undoLastCompletion(); 
        
        center.findById("S01");
        center.printSummary();
    }
}