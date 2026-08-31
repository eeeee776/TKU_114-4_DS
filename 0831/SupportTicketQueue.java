import java.util.Comparator;
import java.util.PriorityQueue;

public class SupportTicketQueue {
    public record Ticket(String id, int severity, long createdOrder) {
        public Ticket {
            if (id == null || id.isBlank()) {
                throw new IllegalArgumentException("Invalid ID");
            }
        }
    }

    public static void main(String[] args) {
        // severity 越大越優先；severity 相平時 createdOrder 越小越優先
        Comparator<Ticket> comparator = Comparator
                .comparingInt(Ticket::severity).reversed()
                .thenComparingLong(Ticket::createdOrder)
                .thenComparing(Ticket::id);

        PriorityQueue<Ticket> queue = new PriorityQueue<>(comparator);

        queue.offer(new Ticket("TCK-001", 2, 1001L));
        queue.offer(new Ticket("TCK-002", 5, 1002L));
        queue.offer(new Ticket("TCK-003", 5, 1000L));
        queue.offer(new Ticket("TCK-004", 1, 1004L));
        queue.offer(new Ticket("TCK-005", 2, 999L));

        while (!queue.isEmpty()) {
            Ticket t = queue.poll();
            System.out.println(t.id() + "|" + t.severity() + "|" + t.createdOrder());
        }
    }
}