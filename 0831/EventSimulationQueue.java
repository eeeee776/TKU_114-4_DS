import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class EventSimulationQueue {
    public record SimulationEvent(String eventId, long timestamp, String type, long sequence) {
        public SimulationEvent {
            if (eventId == null || eventId.isBlank()) {
                throw new IllegalArgumentException("eventId cannot be empty");
            }
        }
    }

    private final PriorityQueue<SimulationEvent> eventQueue;
    private final List<String> executionLog;

    public EventSimulationQueue() {
        Comparator<SimulationEvent> order = Comparator
                .comparingLong(SimulationEvent::timestamp)
                .thenComparingLong(SimulationEvent::sequence)
                .thenComparing(SimulationEvent::eventId);
        this.eventQueue = new PriorityQueue<>(order);
        this.executionLog = new ArrayList<>();
    }

    public void schedule(String eventId, long timestamp, String type, long sequence) {
        eventQueue.offer(new SimulationEvent(eventId, timestamp, type, sequence));
    }

    public boolean cancel(String eventId) {
        if (eventId == null) return false;
        boolean removed = eventQueue.removeIf(e -> e.eventId().equals(eventId));
        if (removed) {
            executionLog.add("[CANCELLED] eventId=" + eventId);
        }
        return removed;
    }

    public void runSimulation() {
        while (!eventQueue.isEmpty()) {
            SimulationEvent current = eventQueue.poll();
            String logEntry = String.format("[EXECUTED] time=%d | seq=%d | id=%s | type=%s",
                    current.timestamp(), current.sequence(), current.eventId(), current.type());
            executionLog.add(logEntry);
        }
    }

    public List<String> getExecutionLog() {
        return List.copyOf(executionLog);
    }

    public static void main(String[] args) {
        EventSimulationQueue sim = new EventSimulationQueue();

        sim.schedule("EVT-A", 1000, "TIMER_EXPIRED", 1);
        sim.schedule("EVT-B", 500, "PACKET_RECEIVED", 2);
        sim.schedule("EVT-C", 1000, "IO_READY", 0); // 時間相同，但 sequence 較小
        sim.schedule("EVT-D", 800, "USER_CLICK", 3);
        sim.schedule("EVT-CANCEL-ME", 600, "BACKGROUND_PING", 4);

        // 取消事件測試
        sim.cancel("EVT-CANCEL-ME");

        // 執行模擬
        sim.runSimulation();

        // 輸出執行軌跡
        System.out.println("=== 模擬執行結果日誌 ===");
        for (String log : sim.getExecutionLog()) {
            System.out.println(log);
        }
    }
}