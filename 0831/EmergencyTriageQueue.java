import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class EmergencyTriageQueue {
    public record Patient(String chartNumber, int triageLevel, long arrivalOrder) {
        public Patient {
            if (chartNumber == null || chartNumber.isBlank()) {
                throw new IllegalArgumentException("chartNumber cannot be null or blank");
            }
            if (triageLevel <= 0) {
                throw new IllegalArgumentException("triageLevel must be positive");
            }
        }
    }

    private final PriorityQueue<Patient> queue;

    public EmergencyTriageQueue() {
        Comparator<Patient> comparator = Comparator
                .comparingInt(Patient::triageLevel)
                .thenComparingLong(Patient::arrivalOrder)
                .thenComparing(Patient::chartNumber);
        this.queue = new PriorityQueue<>(comparator);
    }

    public void register(String chartNumber, int triageLevel, long arrivalOrder) {
        queue.offer(new Patient(chartNumber, triageLevel, arrivalOrder));
    }

    public Patient peekNext() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Triage queue is empty");
        }
        return queue.peek();
    }

    public Patient callNext() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Triage queue is empty");
        }
        return queue.poll();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        EmergencyTriageQueue triage = new EmergencyTriageQueue();

        // 模擬掛號：(病歷號, 檢傷級數, 到達序號)
        triage.register("P001", 3, 101); // 輕症先到
        triage.register("P002", 1, 102); // 重症後到
        triage.register("P003", 2, 103); // 中度
        triage.register("P004", 1, 104); // 同樣 1 級，但較晚到
        triage.register("P005", 2, 100); // 2 級，但比 P003 更早到

        System.out.println("目前候診人數: " + triage.size());
        System.out.println("查看下一位最優先患者: " + triage.peekNext());

        System.out.println("\n--- 依序叫號診治 ---");
        while (!triage.isEmpty()) {
            Patient p = triage.callNext();
            System.out.printf("叫號: 病歷號=%s | 檢傷=%d級 | 到院序號=%d (剩餘: %d人)%n",
                    p.chartNumber(), p.triageLevel(), p.arrivalOrder(), triage.size());
        }

        // 測試空佇列例外
        try {
            triage.callNext();
        } catch (NoSuchElementException e) {
            System.out.println("\n成功捕獲空佇列例外: " + e.getMessage());
        }
    }
}