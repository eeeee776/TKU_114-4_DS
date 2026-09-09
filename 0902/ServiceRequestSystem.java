import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ServiceRequestSystem {
    public record Request(String id, int priority, long sequence) {}

    private final Map<String, Request> activeRequests = new HashMap<>();
    private final PriorityQueue<Request> queue = new PriorityQueue<>(
        Comparator.comparingInt(Request::priority).thenComparingLong(Request::sequence)
    );

    public boolean addRequest(String id, int priority, long sequence) {
        if (id == null || id.isBlank()) return false;
        String reqId = id.trim();
        if (activeRequests.containsKey(reqId)) return false;

        Request req = new Request(reqId, priority, sequence);
        activeRequests.put(reqId, req);
        queue.offer(req);
        return true;
    }

    public boolean cancelRequest(String id) {
        if (id == null || id.isBlank()) return false;
        String reqId = id.trim();
        if (!activeRequests.containsKey(reqId)) return false;

        activeRequests.remove(reqId);
        return true;
    }

    public Request processNext() {
        while (!queue.isEmpty()) {
            Request next = queue.poll();
            if (activeRequests.containsKey(next.id())) {
                activeRequests.remove(next.id());
                return next;
            }
        }
        return null;
    }

    public int pendingCount() {
        return activeRequests.size();
    }

    public static void main(String[] args) {
        ServiceRequestSystem sys = new ServiceRequestSystem();
        sys.addRequest("R01", 3, 100);
        sys.addRequest("R02", 1, 105);
        sys.addRequest("R03", 1, 101);
        sys.addRequest("R04", 5, 110);

        System.out.println("Pending Count: " + sys.pendingCount());
        System.out.println("Cancel R03: " + sys.cancelRequest("R03"));

        Request nextReq = sys.processNext();
        System.out.println("Process Next: " + (nextReq != null ? nextReq.id() : "null"));

        System.out.println("Pending Count: " + sys.pendingCount());
    }
}