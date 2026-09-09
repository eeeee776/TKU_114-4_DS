import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class SimulationEvent {
    long time;
    String type;
    long sequence;

    SimulationEvent(long time, String type, long sequence) {
        this.time = time;
        this.type = type;
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "[" + time + "] " + type + " (Seq: " + sequence + ")";
    }
}

public class EventSimulationQueue {
    private PriorityQueue<SimulationEvent> queue;
    private Map<Long, SimulationEvent> eventMap;
    private long seqCounter;

    public EventSimulationQueue() {
        Comparator<SimulationEvent> comp = Comparator.comparingLong((SimulationEvent e) -> e.time)
                .thenComparingLong(e -> e.sequence);
        queue = new PriorityQueue<>(comp);
        eventMap = new HashMap<>();
        seqCounter = 0;
    }

    public long addEvent(long time, String type) {
        if (type == null || type.isBlank()) return -1;
        seqCounter++;
        SimulationEvent e = new SimulationEvent(time, type, seqCounter);
        queue.offer(e);
        eventMap.put(seqCounter, e);
        return seqCounter;
    }

    public boolean cancelEvent(long sequence) {
        SimulationEvent e = eventMap.remove(sequence);
        if (e != null) {
            queue.remove(e);
            return true;
        }
        return false;
    }

    public void runSimulation() {
        System.out.println("Starting simulation...");
        while (!queue.isEmpty()) {
            SimulationEvent e = queue.poll();
            eventMap.remove(e.sequence);
            System.out.println("Executing: " + e);
        }
        System.out.println("Simulation ended.");
    }

    public static void main(String[] args) {
        EventSimulationQueue sim = new EventSimulationQueue();
        
        sim.addEvent(100, "Login");
        long seqToCancel = sim.addEvent(150, "Purchase");
        sim.addEvent(150, "Click");
        sim.addEvent(50, "Init");

        System.out.println("Cancel event " + seqToCancel + ": " + sim.cancelEvent(seqToCancel));
        System.out.println("Cancel missing event: " + sim.cancelEvent(999));

        sim.runSimulation();
    }
}