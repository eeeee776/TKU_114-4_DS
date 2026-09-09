import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Patient {
    private String id;
    private String name;

    Patient(String id, String name) {
        this.id = id;
        this.name = name;
    }

    String getId() { return id; }
    
    @Override
    public String toString() {
        return "[" + id + "] " + name;
    }
}

public class ClinicQueueSystem {
    private Deque<Patient> waitingQueue = new ArrayDeque<>();
    private List<Patient> completedList = new ArrayList<>();

    void register(String id, String name) {
        Patient p = new Patient(id, name);
        waitingQueue.offerLast(p);
        System.out.println("掛號成功: " + p);
    }

    void cancel(String id) {
        boolean removed = waitingQueue.removeIf(p -> p.getId().equals(id));
        System.out.println("取消掛號 (" + id + "): " + (removed ? "成功" : "找不到病歷號"));
    }

    void callNext() {
        Patient p = waitingQueue.pollFirst();
        if (p != null) {
            completedList.add(p);
            System.out.println("請進診間: " + p);
        } else {
            System.out.println("目前無人等候。");
        }
    }

    void peekNext() {
        Patient p = waitingQueue.peekFirst();
        System.out.println("下一位: " + (p != null ? p : "無"));
    }

    void printCompleted() {
        System.out.println("當日完成清單: " + completedList);
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();
        clinic.register("P01", "Amy");
        clinic.register("P02", "Ben");
        clinic.register("P03", "Cara");
        
        clinic.peekNext();
        clinic.cancel("P02"); 
        clinic.cancel("P99"); 
        
        clinic.callNext();
        clinic.callNext();
        clinic.callNext();
        
        clinic.printCompleted();
    }
}