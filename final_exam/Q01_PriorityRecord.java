import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Q01_PriorityRecord {
    public record Job(String id, int priority, long sequence) {}

    public static List<String> processOrder(List<Job> jobs) {
        if (jobs == null || jobs.isEmpty()) {
            return new ArrayList<>();
        }
        PriorityQueue<Job> pq = new PriorityQueue<>(
            Comparator.comparingInt(Job::priority)
                      .thenComparingLong(Job::sequence)
                      .thenComparing(Job::id)
        );
        for (Job j : jobs) {
            if (j != null) {
                pq.offer(j);
            }
        }
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll().id());
        }
        return result;
    }
}