import java.util.Comparator;
import java.util.PriorityQueue;

class Patient {
    String id;
    int severity;
    long arrivalTime;

    Patient(String id, int severity, long arrivalTime) {
        this.id = id;
        this.severity = severity;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return id + " (Severity: " + severity + ", Arrival: " + arrivalTime + ")";
    }
}

public class EmergencyTriageQueue {
    private PriorityQueue<Patient> queue;
    private long timeCounter;

    public EmergencyTriageQueue() {
        Comparator<Patient> comp = Comparator.comparingInt((Patient p) -> p.severity).reversed()
                .thenComparingLong(p -> p.arrivalTime)
                .thenComparing(p -> p.id);
        queue = new PriorityQueue<>(comp);
        timeCounter = 0;
    }

    public void register(String id, int severity) {
        if (id == null || id.isBlank()) return;
        Patient p = new Patient(id, severity, ++timeCounter);
        queue.offer(p);
        System.out.println("Registered: " + p);
    }

    public void peekNext() {
        Patient p = queue.peek();
        if (p == null) {
            System.out.println("Next: None");
        } else {
            System.out.println("Next: " + p);
        }
    }

    public void callNext() {
        Patient p = queue.poll();
        if (p == null) {
            System.out.println("Call: Queue is empty");
        } else {
            System.out.println("Call: " + p);
        }
    }

    public int size() {
        return queue.size();
    }

    public static void main(String[] args) {
        EmergencyTriageQueue triage = new EmergencyTriageQueue();
        triage.callNext();

        triage.register("P01", 3);
        triage.register("P02", 5);
        triage.register("P03", 5);
        triage.register("P04", 1);

        System.out.println("Total waiting: " + triage.size());
        triage.peekNext();

        while (triage.size() > 0) {
            triage.callNext();
        }
        triage.callNext();
    }
}