import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Patient {
    String id;
    String name;

    public Patient(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String toString() { return "[" + id + "] " + name; }
}

public class ClinicQueueSystem {
    private Deque<Patient> waitingQueue = new ArrayDeque<>();
    private List<Patient> completedList = new ArrayList<>();

    public void register(String id, String name) {
        Patient p = new Patient(id, name);
        waitingQueue.offerLast(p);
        System.out.println("掛號成功: " + p);
    }

    public void cancel(String id) {
        // 利用 removeIf 走訪並移除特定條件的物件
        boolean removed = waitingQueue.removeIf(p -> p.id.equals(id));
        System.out.println("取消病歷號 " + id + (removed ? " 成功。" : " 失敗，查無此人。"));
    }

    public void callNext() {
        Patient p = waitingQueue.pollFirst();
        if (p == null) {
            System.out.println("目前無人等候。");
        } else {
            System.out.println("請進診間: " + p);
            completedList.add(p);
        }
    }

    public void peekNext() {
        Patient p = waitingQueue.peekFirst();
        System.out.println("下一位: " + (p == null ? "無" : p));
    }

    public void printCompleted() {
        System.out.println("=== 當日完成清單 ===");
        completedList.forEach(System.out::println);
        System.out.println("==================\n");
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();
        clinic.register("A01", "王小明");
        clinic.register("A02", "李小華");
        clinic.register("A03", "陳大牛");

        clinic.peekNext(); // A01
        clinic.cancel("A02"); // 取消成功
        
        clinic.callNext(); // 叫號 A01
        clinic.callNext(); // 叫號 A03 (因為 A02 被取消了)
        clinic.callNext(); // 無人等候
        
        clinic.printCompleted();
    }
}