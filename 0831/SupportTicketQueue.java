import java.util.Comparator;
import java.util.PriorityQueue;

class Ticket {
    String id;
    int severity;
    long createdOrder;

    Ticket(String id, int severity, long createdOrder) {
        this.id = id;
        this.severity = severity;
        this.createdOrder = createdOrder;
    }
}

public class SupportTicketQueue {
    public static void main(String[] args) {
        Comparator<Ticket> comp = Comparator.comparingInt((Ticket t) -> t.severity).reversed()
                .thenComparingLong(t -> t.createdOrder);
        
        PriorityQueue<Ticket> pq = new PriorityQueue<>(comp);
        
        pq.offer(new Ticket("T01", 3, 100));
        pq.offer(new Ticket("T02", 5, 101));
        pq.offer(new Ticket("T03", 5, 99));
        pq.offer(new Ticket("T04", 1, 102));

        while (!pq.isEmpty()) {
            Ticket t = pq.poll();
            System.out.println(t.id + "|" + t.severity + "|" + t.createdOrder);
        }
    }
}